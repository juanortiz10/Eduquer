package fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.juan.eduquer.R;
import java.util.ArrayList;
import adapter.WordsAdapter;
import helper.DataBaseHelper;
/**
 * Created by juan on 10/06/15.
 */

public class Remove extends Fragment {
    private ListView listWords;
    private TextView noContent;
    private ImageView imvNoContent;
    private ArrayList myList;
    public Remove(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState){
        setHasOptionsMenu(true);
        final View rootView = inflater.inflate(R.layout.remove,container,false);

        listWords=(ListView)rootView.findViewById(R.id.listWords);
        noContent=(TextView)rootView.findViewById(R.id.noContent);
        DataBaseHelper dataBaseHelper= new DataBaseHelper(getActivity().getApplicationContext());

        myList=dataBaseHelper.getALl();
        if(myList.size()<1) {
            imvNoContent = (ImageView) rootView.findViewById(R.id.imvNocontent);
            imvNoContent.setImageResource(R.drawable.trash);
            noContent.setText(R.string.empty);
        }
        final WordsAdapter adapter=new WordsAdapter(getActivity().getApplicationContext(),myList);
        listWords.setAdapter(adapter);
        listWords.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView= (TextView)view.findViewById(R.id.name);
                String item= textView.getText().toString();
                showAlertDialog(item,adapter);
                return false;
            }
        });
        return rootView;
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        menu.clear();
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_menu,menu);
    }

    private void showAlertDialog(final String wordTo, final ArrayAdapter adapter){
        final AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());

        builder.setMessage(getResources().getString(R.string.doyouwant));
        builder.setCancelable(true);
        builder.setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                DataBaseHelper dataBaseHelper= new DataBaseHelper(getActivity().getApplicationContext());
                dataBaseHelper.deleteWord(dataBaseHelper,wordTo);
                adapter.clear();
                myList=dataBaseHelper.getALl();

                if (myList.size()<1)
                    noContent.setText(R.string.empty);

                adapter.notifyDataSetChanged();
                adapter.addAll(myList);
                Toast.makeText(getActivity().getApplicationContext(),wordTo+getResources().getString(R.string.deleted),Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                dialog.cancel();
            }
        });
        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }
}
