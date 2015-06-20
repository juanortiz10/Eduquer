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

import models.Item;
import models.Items;

/**
 * Created by juan on 10/06/15.
 */
public class DrawerAdapter extends ArrayAdapter {

    public DrawerAdapter(Context context, List objects){
        super(context,0 ,objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.adapter,null);
        }

        ImageView image= (ImageView)convertView.findViewById(R.id.icon);
        TextView name= (TextView)convertView.findViewById(R.id.name);

        Item item= (Item) getItem(position);
        image.setImageResource(item.getId());
        name.setText(item.getName());

        return convertView;
    }
}
