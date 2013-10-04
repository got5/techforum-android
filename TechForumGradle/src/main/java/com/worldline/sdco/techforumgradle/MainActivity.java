package com.worldline.sdco.techforumgradle;

import android.app.Activity;
import android.content.Intent;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.worldline.sdco.techforumgradle.conference.list.ConferenceListActivity;
import com.worldline.sdco.techforumgradle.map.MapActivity;
import com.worldline.sdco.techforumgradle.message.FeedbackListActivity;


@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
	
	@Click 
	void conferences() {
        startActivity(new Intent(this, ConferenceListActivity.class));
	} 
	  
	@Click
	void map() {
        startActivity(new Intent(this, MapActivity.class));
	}
	 
	@Click
	void feelbacks() {
        startActivity(new Intent(this, FeedbackListActivity.class));
	}
}
