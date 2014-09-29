package in.bmsit.bmsit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mayank on 28-09-2014.
 */
public class CustomAdapter extends ArrayAdapter {
    public CustomAdapter(Context context,ArrayList<Card> list){
        super(context,0,list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Card card= (Card) getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.card_row,parent,false);
            TextView tit = (TextView) convertView.findViewById(R.id.listTitle);
            TextView bod = (TextView) convertView.findViewById(R.id.listBody);
            tit.setText(card.title);
            bod.setText(card.body);
        }

        return convertView;
    }
}