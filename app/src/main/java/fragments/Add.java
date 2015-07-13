package fragments;

import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.juan.eduquer.R;
import helper.DataBaseHelper;
import models.Item;

/**
 * Created by juan on 10/06/15.
 */
public class Add extends Fragment implements View.OnClickListener{
    private ImageButton btnSend;
    private EditText etWords;
    private SQLiteDatabase db;
    public String word;
    public Add(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState){
        View rootView = inflater.inflate(R.layout.add,container,false);
        setHasOptionsMenu(true);
        btnSend=(ImageButton)rootView.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);
        etWords=(EditText)rootView.findViewById(R.id.etWords);
        setRetainInstance(true);
        return rootView;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSend) {
            try {
                if(etWords.length()>0) {
                    word=etWords.getText().toString();
                    new CheckDataBase().execute();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),getResources().getString(R.string.please),Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {}
        }
    }

    class CheckDataBase extends AsyncTask<String,String,Boolean> {
        ProgressDialog progressDialog;

        @Override
        protected void onPostExecute(Boolean s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if(!s) Toast.makeText(getActivity().getApplicationContext(),getResources().getString(R.string.wordadded),Toast.LENGTH_SHORT).show();
            else Toast.makeText(getActivity().getApplicationContext(),"Error "+word+getResources().getString(R.string.already),Toast.LENGTH_SHORT).show();
            etWords.setText("");
        }

        @Override
        protected Boolean doInBackground(String... params) {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity().getApplicationContext());
            for (Item i : dataBaseHelper.getALl()) {
                if (i.getName().equals(word)) {
                    dataBaseHelper.close();
                    return true;
                }
            }
            dataBaseHelper.putInformation(dataBaseHelper, etWords.getText().toString());
            dataBaseHelper.close();
            return false;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog=new ProgressDialog(getActivity());
            progressDialog.setMessage(getResources().getString(R.string.wait));
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();
        }
    }
}
