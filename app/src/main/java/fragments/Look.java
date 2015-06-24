package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.juan.eduquer.R;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import adapter.SearchAdapter;
import helper.DataBaseHelper;
import models.Item;
import models.Items;
import models.Result;
import models.Search;

/**
 * Created by juan on 10/06/15.
 */
public class Look extends Fragment implements OnItemClickListener{

    private final Search search = new Search();
    private ListView listViewResults;
    private Result result = new Result();
    private ArrayList<Items> results=new ArrayList();
    private ArrayList<Item> words;
    private SearchAdapter searchAdapter;
    public Look(){}

    final Handler handlerTask = new Handler();
    final Runnable updateItemsTask = new Runnable(){
        public void run(){
            updateItems();
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState){
        View rootView = inflater.inflate(R.layout.look,container,false);
        setHasOptionsMenu(true);
        listViewResults = (ListView)  rootView.findViewById(R.id.lvResults);
        listViewResults.setOnItemClickListener(this);
        search();
        searchAdapter=new SearchAdapter(getActivity().getApplicationContext(),results);
        return rootView;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        menu.clear();
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.fragment_menu,menu);
    }

    protected Result updateResult(String keywords){
        Result newResult = new Result();
        try {
            newResult = search.doSearch(keywords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        results.addAll(newResult.getItems());
        return newResult;
    }

    protected void updateItems() {
        if(0 == result.getItems().size()){
            Toast.makeText(getActivity().getApplicationContext(), "We've had some problems please reload the app", Toast.LENGTH_SHORT).show();
        }
        listViewResults.setAdapter(searchAdapter);
        searchAdapter.notifyDataSetChanged();
    }

    private void search(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity().getApplicationContext());
        this.words = new ArrayList();
        this.words = dataBaseHelper.getALl();
        if (words.size() > 0) {
            result.getItems().clear();
            //Verifica la fuerza de la palabra, la menor sera lanzada
            int random = (int) (Math.random() * (this.words.size()-1 + 1) + 0);
            final String textToSearch = this.words.get(random).getName();
            Thread t = new Thread() {
                public void run() {
                    result = updateResult(textToSearch);
                    handlerTask.post(updateItemsTask);
                }
            };

            Toast.makeText(getActivity().getApplicationContext(), "Searching... " + textToSearch, Toast.LENGTH_SHORT).show();
            t.start();
        }else{
            Toast.makeText(getActivity().getApplicationContext(), "Your word's list is empty",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(result.getItems().get(arg2).getLink()));
        startActivity(intent);
    }
}

