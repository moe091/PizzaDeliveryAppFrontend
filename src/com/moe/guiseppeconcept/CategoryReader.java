package com.moe.guiseppeconcept;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.JsonReader;
import android.widget.ListView;

/**
 * Eventually I'll have an abstraction for the server and database to handle getting the list(doing the job of this class).
 * @author moe
 *
 */
public class CategoryReader {
private String tag = "CATEGORY_READER - ";
CategoryArrayAdapter adapter;
List<Category> catList;
int listId;
ListView list;
Context context;

	public CategoryReader(ListView list) {
		this.list = list;
	}
	public List<Category> populateList(int listId, InputStream is, Activity activity) {
		this.listId = listId;
		this.context = activity;
		try {
			getInputStream(is);
		} catch (IOException e) {
			System.out.println(tag + "populateList()");
			e.printStackTrace();
		}
		return catList;
	}
	
	public List readJson(JsonReader reader)  throws IOException {
		List messages = new ArrayList();
		System.out.println("JSON: BEGINNING>>");
		reader.beginArray();
		while (reader.hasNext()) {
			messages.add(readCategory(reader));
		}
		return messages;
	}
	
	private Category readCategory(JsonReader reader) throws IOException {
		String name = "";
		String catName = null;
		String url = null;
		String imgurl = null;
		String desc = null;
		int pk;
		
		reader.beginObject();
		while(reader.hasNext()) {
			name = reader.nextName();
			System.out.println(name);

			if (name.equals("fields")) {
				reader.beginObject();
				while (reader.hasNext()) {
					name = reader.nextName();
					if (name.equals("url")) {
						url = reader.nextString();
					} else if (name.equals("name")) {
						catName = (reader.nextString());
					} else if (name.equals("description")) {
						desc = reader.nextString();
					} else if (name.equals("imageurl")) {
						imgurl = reader.nextString();
					}

				}
				reader.endObject();
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return new Category(catName, url, desc, imgurl);
	}


	public void getInputStream(InputStream is) throws IOException {
		JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
		try {
			setCategoryList(readJson(reader));
		} finally {
			reader.close();
		}
		
	}


	private void setCategoryList(List<Category> cats) {
		ArrayList<Category> categories = (ArrayList) cats;
		ArrayList<String> mylist = new ArrayList<String>();
		
		for (int i = 0; i < cats.size(); i++) {
			cats.get(i).printCategory();
			mylist.add(cats.get(i).toString());
		}
		
		//adapter = new CategoryArrayAdapter(context, listId, cats);
		
		catList = cats;
	}
	
	public void finalize() {
		list.setAdapter(adapter);
	}
}
