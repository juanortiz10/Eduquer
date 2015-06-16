package fragments;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
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
        btnSend=(ImageButton)rootView.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
        etWords=(EditText)rootView.findViewById(R.id.etWords);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSend) {
            try {
                Date time = new Date();
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity().getApplicationContext());
                dataBaseHelper.putInformation(dataBaseHelper, etWords.getText().toString(), time);
                Toast.makeText(getActivity().getApplicationContext(),"Palabra agregada exitosamente!", Toast.LENGTH_SHORT).show();
                etWords.setText("");
            } catch (Exception ex) {
            }

        }
    }
}
