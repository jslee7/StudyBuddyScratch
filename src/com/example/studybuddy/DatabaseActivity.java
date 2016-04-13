package com.example.studybuddy;

import android.app.Activity;
import android.os.Bundle;

public class DatabaseActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstancesState){
		super.onCreate(savedInstancesState);
		//setContentView(R.layout.main);
		DBAdapter db = new DBAdapter(this);
	}
	
}

