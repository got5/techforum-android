package net.atos.techforum.android;

import net.atos.techforum.android.conference.list.ConferenceListActivity_;
import net.atos.techforum.android.map.MapActivity_;
import net.atos.techforum.android.message.FeelbackListActivity_;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;

import android.app.Activity;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {
	
	@Click 
	void conferences() { 
		ConferenceListActivity_.intent(this).start();
	} 
	  
	@Click
	void map() { 
		MapActivity_.intent(this).start();
	}
	 
	@Click
	void feelbacks() {
		FeelbackListActivity_.intent(this).start(); 
	}
}
