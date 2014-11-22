package com.moe.guiseppeconcept;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
private static final String DB_NAME = "db";
static final String NAME="name";
static final String DESCRIPTION="description";
static final String URL = "url";
static final String IMG = "img";

	public DBHelper(Context context) {
		super(context, DB_NAME, null, 2);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table section (_id integer primary key, name text, description text, url text, img text);");
		db.execSQL("create table item (_id primary key, Section_id integer references section (id), name text, price real, description text, imageurl text)");
		ContentValues cv = new ContentValues();
		
		cv.put(NAME, "Pizzas");
		cv.put("_id", 12);
		cv.put(DESCRIPTION, "Pizza with various different options and toppings, available in slices or pies");
		cv.put(URL, "htpp://192.168.1.9:8000/pizza/pizza");
		cv.put(IMG, "htpp://192.168.1.9:8000/static/pizza");
		
		db.insert("section", NAME, cv);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists section");
		db.execSQL("drop table if exists item");
		onCreate(db);
		
	}

}
