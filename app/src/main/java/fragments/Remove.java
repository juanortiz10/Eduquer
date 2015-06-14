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
import android.widget.TextView;
import android.widget.Toast;

import com.example.juan.eduquer.R;

import org.w3c.dom.Text;

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
        final View rootView = inflater.inflate(R.layout.remove,container,false);
        listWords=(ListView)rootView.findViewById(R.id.listWords);
        DataBaseHelper dataBaseHelper= new DataBaseHelper(getActivity().getApplicationContext());
        final ArrayList myList;
        myList=dataBaseHelper.getALl();
        final WordsAdapter adapter=new WordsAdapter(getActivity().getApplicationContext(),myList);
        listWords.setAdapter(adapter);
        listWords.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView= (TextView)view.findViewById(R.id.name);
                String item= textView.getText().toString();
                showAlertDialog(item);

                return false;
            }
        });
        return rootView;
    }

    private void showAlertDialog(final String wordTo){
        final AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you want to delete this word?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                DataBaseHelper dataBaseHelper= new DataBaseHelper(getActivity().getApplicationContext());
                dataBaseHelper.deleteWord(dataBaseHelper,wordTo);
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
