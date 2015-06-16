package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.juan.eduquer.R;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import adapter.TutorialsInfoAdapter;
import models.Result;
import models.Search;

/**
 * Created by juan on 10/06/15.
 */
public class Look extends Fragment implements OnClickListener, OnItemClickListener{

    private final Search search = new Search();
    private ImageButton ibDoSearch;
    private TextView tvSearch;
    private ListView lvResults;
    private Result result = new Result();

    public Look(){}

    final Handler handler = new Handler();
    final Runnable updateItems = new Runnable(){
        public void run(){
            updateItemsInUI();
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState){
        View rootView = inflater.inflate(R.layout.look,container,false);
        lvResults = (ListView)  rootView.findViewById(R.id.lvResults);
        lvResults.setOnItemClickListener(this);
        tvSearch = (TextView) rootView.findViewById(R.id.tvSeach);
        ibDoSearch = (ImageButton)rootView.findViewById(R.id.ibSearch);
        ibDoSearch.setOnClickListener((android.view.View.OnClickListener) this);
        return rootView;
    }

    protected Result updateResult(String keywords){
        Result newResult = new Result();
        try {
            newResult = search.doSearch(keywords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newResult;
    }

    protected void updateItemsInUI() {
        if(0 == result.getItems().size()){
            Toast.makeText(getActivity().getApplicationContext(), "No se ha encontrado ningún resultado", Toast.LENGTH_SHORT).show();
        }
        TutorialsInfoAdapter tutorialsInfoAdapter = new TutorialsInfoAdapter(getActivity().getApplicationContext(), result.getItems());
        lvResults.setAdapter(tutorialsInfoAdapter);
    }





    @Override
    public void onClick(View arg0) {
        switch(arg0.getId())
        {
            case R.id.ibSearch :
                final String textToSearch = tvSearch.getText().toString().trim();
                Thread t = new Thread(){
                    public void run(){
                        result = updateResult(textToSearch);
                        handler.post(updateItems);
                    }
                };
                Toast.makeText(getActivity().getApplicationContext(),"Realizando búsqueda",Toast.LENGTH_SHORT).show();
                t.start();
                hideInputMethod();
                break;
        }
    }

    private void hideInputMethod(){
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(tvSearch.getWindowToken(), 0);
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getItems().get(arg2).getLink()));
        startActivity(intent);
    }

}

