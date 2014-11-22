package com.moe.guiseppeconcept;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

/*
client = new DefaultHttpClient();
//post = new HttpPost(url);
request = new HttpGet(url);

try {
	HttpResponse response = client.execute(request);
	analyzeResponse(response);
} catch (ClientProtocolException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}*/

public class GetUrl extends AsyncTask<String, Integer, List<Category>> {
private final String tag = "GETURL - ";
HttpReceiver receiver;
HttpClient client;
HttpGet request;

	public GetUrl(HttpReceiver receiver) {
		this.receiver = receiver;
		client = new DefaultHttpClient();
	}
	@Override
	protected List<Category> doInBackground(String... params) {
		request = new HttpGet(params[0]);
		String body = null;
		InputStream stream = null;
		List<Category> list = null;
		try {
			HttpResponse response = client.execute(request);
			stream = analyzeResponse(response);
			list = receiver.getList(stream);
		} catch (ClientProtocolException e) {
			System.out.println(tag + "ClientProtocolException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(tag + "IOException");
			System.out.println(tag + e.toString());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	protected void onPostExecute(List<Category> result) {
		receiver.getResult(result);
		/*try {
			receiver.getStream(result);
		} catch (IOException e) {
			System.out.println(tag + "Receiver class threw ioexception in onPostExecute method");
			e.printStackTrace();
		}*/
	}
	
	private InputStream analyzeResponse(HttpResponse response) {
		/*BufferedReader reader;
		StringBuilder builder = new StringBuilder();
		
		String line;
		
		try {
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			
			while((line = reader.readLine()) != null) 
				builder.append(line);
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return builder.toString();*/
		try {
			return response.getEntity().getContent();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}




























