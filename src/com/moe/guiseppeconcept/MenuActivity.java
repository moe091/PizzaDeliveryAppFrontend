package com.moe.guiseppeconcept;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MenuActivity extends Activity implements HttpReceiver {
ListView catList;
Button cart;
Button menu;
Button info;
ArrayList<Category> cats = new ArrayList<Category>();
CategoryArrayAdapter adapter; 

DBHelper dbHelper;
GetUrl fetcher;
CategoryReader catReader;
String defaultUrl = "http://192.168.1.9:8000/pizza/";
String url = "http://192.168.1.9:8000/pizza/";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);
		cart = (Button) findViewById(R.id.cartblack);
		menu = (Button) findViewById(R.id.menublack);
		info = (Button) findViewById(R.id.infoblack);
		
		
		catList = (ListView) findViewById(R.id.categoryList);
		catReader = new CategoryReader(catList);
		for (int i = 0; i < 20; i++) {
			cats.add(new Category(i));
		}
		
		menu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				menuClick();
				
			}
		});
		
		dbHelper = new DBHelper(this);
		Cursor cursor = dbHelper.getReadableDatabase().rawQuery("select name, description, url, img, _id from section", null);
		cursor.moveToFirst();
		System.out.println("DB VERSION: " + cursor.getInt(4) + ".  " + cursor.getString(0) + " - " + cursor.getString(1));
		
		
	}
	
	
	protected void menuClick() {
		menu.setBackgroundResource(R.drawable.menuwhite);
		
		
		fetcher = new GetUrl(this);
		fetcher.execute(url);
	}
	
	
	




	@Override
	public void getResult(List<Category> list) {
		if (list != null)
			catList.setAdapter(new CategoryArrayAdapter(this, R.layout.listitem, list));
		
	}
	@Override
	public List<Category> getList(InputStream stream) {
		return catReader.populateList(R.layout.listitem, stream, this);
	}

}
