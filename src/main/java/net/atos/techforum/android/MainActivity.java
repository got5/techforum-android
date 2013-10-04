package net.atos.techforum.android;

import android.app.Activity;
import android.content.Intent;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;

import net.atos.techforum.android.conference.list.ConferenceListActivity;
import net.atos.techforum.android.map.MapActivity;
import net.atos.techforum.android.message.FeelbackListActivity;

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
        startActivity(new Intent(this, FeelbackListActivity.class));
	}
}
