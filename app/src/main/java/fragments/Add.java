package fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.juan.eduquer.R;
import java.util.Date;
import helper.DataBaseHelper;
/**
 * Created by juan on 10/06/15.
 */
public class Add extends Fragment implements View.OnClickListener{
    private ImageButton btnSend;
    private EditText etWords;
    private SQLiteDatabase db;

    public Add(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState){
        View rootView = inflater.inflate(R.layout.add,container,false);
        setHasOptionsMenu(true);
        btnSend=(ImageButton)rootView.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
        etWords=(EditText)rootView.findViewById(R.id.etWords);
        return rootView;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        menu.clear();
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_menu,menu);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSend) {
            try {
                if(etWords.length()>0) {
                    Date time = new Date();
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity().getApplicationContext());
                    dataBaseHelper.putInformation(dataBaseHelper, etWords.getText().toString(), time);
                    Toast.makeText(getActivity().getApplicationContext(), "New word has been added correctly!", Toast.LENGTH_SHORT).show();
                    etWords.setText("");
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Please type word, phrase or title",Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {}
        }
    }
}
