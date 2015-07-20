package fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.juan.eduquer.R;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import algorithms.CheckProgress;
import helper.DataBaseHelper;
import models.Item;
import models.Items;
import models.Result;
import models.Search;
import recycler.RVAdapter;

/**
 * Created by juan on 10/06/15.
 */
public class Look extends Fragment implements{
    public int seconds=0;
    private final Search search = new Search();
 //   private ListView listViewResults;
    private Result result = new Result();
    private ArrayList<Items> results=new ArrayList();
    private ArrayList<Item> words;
//    private SearchAdapter searchAdapter;
    public String textToSearch;
    private ImageView imvNo;
    private TextView tvNo;
    Timer timer=new Timer();
    RecyclerView rv;
    List<Items> items=new ArrayList<>();

    public Look(){}

    final Handler handlerTask = new Handler();

    final Runnable updateItemsTask = new Runnable(){
        public void run(){
            updateItems();
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState){
        View rootView = inflater.inflate(R.layout.recycler,container,false);
        setHasOptionsMenu(true);

        rv=(RecyclerView)rootView.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm=new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(llm);

        DataBaseHelper dataBaseHelper=new DataBaseHelper(getActivity().getApplicationContext());
        if(dataBaseHelper.getALl().size()>0) {
            search();
        }else{
            imvNo=(ImageView)rootView.findViewById(R.id.imvNo);
            imvNo.setImageResource(R.drawable.empty);
            tvNo=(TextView)rootView.findViewById(R.id.tvNo);
            tvNo.setText(getResources().getString(R.string.emptylist));
        }
        return rootView;
    }

    @Override
    public void onStart() {
        DataBaseHelper dataBaseHelper=new DataBaseHelper(getActivity().getApplicationContext());
        if(dataBaseHelper.getALl().size()>0)
            timer.schedule(new Time(), 0, 2000);
        dataBaseHelper.close();
        super.onStart();
    }

    @Override
    public void onStop() {
        DataBaseHelper dataBaseHelper=new DataBaseHelper(getActivity().getApplicationContext());
        if(dataBaseHelper.getALl().size()>0)
            update();
        dataBaseHelper.close();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        DataBaseHelper dataBaseHelper=new DataBaseHelper(getActivity().getApplicationContext());
        if(dataBaseHelper.getALl().size()>0)
            timer.cancel();
        dataBaseHelper.close();
        super.onDestroy();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        menu.clear();
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.menu_main,menu);
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
            Toast.makeText(getActivity().getApplicationContext(),getResources().getString(R.string.problems), Toast.LENGTH_SHORT).show();
        }
        RVAdapter adapter=new RVAdapter(results);
        rv.setAdapter(adapter);
    }

    private void search(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity().getApplicationContext());
        this.words = new ArrayList();
        this.words = dataBaseHelper.getALl();
        if (words.size() > 0) {
            result.getItems().clear();
            int random = (int) (Math.random() * (this.words.size()-1 + 1) + 0);
            textToSearch = this.words.get(random).getName();
            Thread t = new Thread() {
                public void run() {
                    result = updateResult(textToSearch);
                    handlerTask.post(updateItemsTask);
                }
            };

            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.searching_) + textToSearch, Toast.LENGTH_SHORT).show();
            t.start();
        }else{
            Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.emptylist),Toast.LENGTH_SHORT).show();
        }
        dataBaseHelper.close();
    }
/*
    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Intent intent=new Intent(getActivity(),Webview.class);
        intent.putExtra("link", (result.getItems().get(arg2).getLink()));
        startActivityForResult(intent,1);
    }*/
    class Time extends TimerTask {
        @Override
        public void run() {
            seconds++;
        }
    }

   private void update() {
       DataBaseHelper dataBaseHelper=new DataBaseHelper(getActivity().getApplicationContext());
       CheckProgress checkProgress = new CheckProgress();
       int add=checkProgress.checkSeconds(seconds);
       int oldValue=dataBaseHelper.getProgress(dataBaseHelper,textToSearch);
       dataBaseHelper.setProgress(dataBaseHelper,oldValue+add,textToSearch);
       dataBaseHelper.close();
   }
}


