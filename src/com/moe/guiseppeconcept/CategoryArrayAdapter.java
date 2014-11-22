package com.moe.guiseppeconcept;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryArrayAdapter extends ArrayAdapter {
	Context context;
	int resId;
	ArrayList<Category> list;
	
	public CategoryArrayAdapter(Context context, int textViewResourceId, List objects) {
		super(context, textViewResourceId, objects);
		this.list = (ArrayList<Category>) objects;
		this.context = context;
		this.resId = textViewResourceId;
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		CategoryHolder holder = null;
		
		if (row==null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(resId, parent, false);
			
			holder = new CategoryHolder();
			holder.titleView = (TextView) row.findViewById(R.id.name);
			holder.descriptionView = (TextView) row.findViewById(R.id.description);
			holder.imgView = (ImageView) row.findViewById(R.id.list_image);
			row.setTag(holder);
		} else {
			holder = (CategoryHolder) row.getTag();
		}
		
		holder.titleView.setText(list.get(position).name);
		holder.descriptionView.setText(list.get(position).description);
		holder.imgView.setBackgroundResource(R.drawable.pizza1);
	//	ImageDownloader imgDownloader = new ImageDownloader(holder.imgView);
		//imgDownloader.execute(list.get(position).imageURL); 
		
		
		return row;
	}
	
	
	private class CategoryHolder {
		TextView titleView;
		ImageView imgView;
		TextView descriptionView;
	}

}
