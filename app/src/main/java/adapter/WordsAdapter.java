package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juan.eduquer.R;

import java.util.List;

import algorithms.CheckProgress;
import helper.DataBaseHelper;
import models.Item;
import models.Items;

/**
 * Created by juan on 12/06/15.
 */
public class WordsAdapter extends ArrayAdapter {
    DataBaseHelper dop= new DataBaseHelper(getContext());
    public WordsAdapter(Context context, List objects){
        super(context,0 ,objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.wordsadapter,null);
        }

        TextView name= (TextView)convertView.findViewById(R.id.name);
        ImageView image= (ImageView)convertView.findViewById(R.id.starIcon);
        ImageView image2= (ImageView)convertView.findViewById(R.id.imProgress);

        Item item= (Item) getItem(position);
        name.setText(item.getName());
        image.setImageResource(R.drawable.star);

        int progress=dop.getProgress(dop,item.getName());

        if(progress==0){image2.setImageResource(R.drawable.bar0);}
        else if(progress>0 && progress<3){ image2.setImageResource(R.drawable.bar1);}
        else if(progress>3 && progress<7){ image2.setImageResource(R.drawable.bar2);}
        else if(progress>7 && progress<11){ image2.setImageResource(R.drawable.bar3);}
        return convertView;
    }
}
