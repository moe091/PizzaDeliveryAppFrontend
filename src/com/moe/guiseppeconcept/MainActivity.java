package com.moe.guiseppeconcept;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
Button browse;
Button previous;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		browse = (Button) findViewById(R.id.browsebutton);
		browse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				browseClick();
				
			}
		});
	}

	protected void browseClick() {
		Intent i = new Intent(this, MenuActivity.class);
		startActivity(i);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
