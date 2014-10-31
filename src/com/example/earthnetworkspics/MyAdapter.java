package com.example.earthnetworkspics;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MyAdapter extends ArrayAdapter{
	
	Context ctxt;
	private List<Pic> lp;
	
	public MyAdapter(Context c, List<Pic> lp){
		super(c, 0, lp);
		ctxt = c;
		this.lp = lp;
	}
	
	@Override
	public Pic getItem(int id){
		return lp.get(id);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		 LayoutInflater inflater = (LayoutInflater) ctxt
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View rowView = inflater.inflate(R.layout.item, parent, false);
		    TextView tv = (TextView)rowView.findViewById(R.id.name);
		    ImageView img = (ImageView)rowView.findViewById(R.id.pix);
		    tv.setText(lp.get(position).getName());
		    Picasso.with(ctxt).load(lp.get(position).getUrl()).into(img);
		    return rowView;		 
	}
}