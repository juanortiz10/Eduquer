package recycler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.example.juan.eduquer.R;
import java.util.ArrayList;
import java.util.List;
import models.Items;

/**
 * Created by juan on 19/07/15.
 */
public class Recycler extends Activity {
    RecyclerView rv;
    List<Items> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);
        rv=(RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm=new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        init();
        RVAdapter adapter=new RVAdapter(items);
        rv.setAdapter(adapter);
    }

    private void init(){
        items=new ArrayList<>();
        //persons.add(new Person("Juan Ortiz","20 years old",R.drawable.person));
        //persons.add(new Person("Juan Ortiz","20 years old",R.drawable.person));
        //persons.add(new Person("Juan Ortiz","20 years old",R.drawable.person));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

