package fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.juan.eduquer.R;

import java.util.ArrayList;
import java.util.List;

import adapter.WordsAdapter;
import helper.DataBaseHelper;
import models.Item;

/**
 * Created by juan on 10/06/15.
 */
public class Remove extends Fragment {
    private ListView listWords;

    public Remove(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState){
        View rootView = inflater.inflate(R.layout.remove,container,false);
        listWords=(ListView)rootView.findViewById(R.id.listWords);
        DataBaseHelper dataBaseHelper= new DataBaseHelper(getActivity().getApplicationContext());
        ArrayList myList;
        myList=dataBaseHelper.getALl();
        listWords.setAdapter(new WordsAdapter(getActivity().getApplicationContext(),myList));
        listWords.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                showAlertDialog();
                return false;
            }
        });
        return rootView;
    }

    private void showAlertDialog(){
        final AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you want to delete this word?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(getActivity().getApplicationContext(), "Under Construction", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                dialog.cancel();
            }
        });
        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }
}
