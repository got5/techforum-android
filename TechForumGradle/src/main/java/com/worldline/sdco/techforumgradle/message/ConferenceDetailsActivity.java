package com.worldline.sdco.techforumgradle.message;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.worldline.sdco.techforumgradle.R;
import com.worldline.sdco.techforumgradle.conference.ConferenceFinder;
import com.worldline.sdco.techforumgradle.conference.WebConferenceFinder;
import com.worldline.sdco.techforumgradle.rest.pojo.Conference;


@EActivity(R.layout.activity_conference_details)
public class ConferenceDetailsActivity extends Activity {

	@Extra
	String id_conference;
		
	@Bean(WebConferenceFinder.class)
    ConferenceFinder conferenceFinder;
	
	@Bean(WebMessageFinder.class)
	MessageFinder messageFinder;
	
	Conference conference;
	
	@ViewById
    android.widget.TextView title, author, theme, when, where;
	
	@ViewById
    android.widget.ListView list;

	@Bean
	MessageListAdapter adapter;
	
	ProgressDialog dialog;
	
	@Background
	@AfterViews
	void init() {
		list.setAdapter(adapter);
		onRequest();
		conference = conferenceFinder.find(id_conference);
		if(conference != null) onResponse();
		else onError();
	}
	
	@UiThread
	void onRequest() {
		dialog = ProgressDialog.show(this, "Loading...", "Retrieving data from server");
	}
	
	@UiThread
	void onResponse() {
		dialog.cancel();
		title.setText(conference.getTitle());
		author.setText(conference.getAuthor());
		when.setText(String.format("@ %s -> %s", conference.getWhen().getStart(), conference.getWhen().getEnd()));
		where.setText("Room " + conference.getWhere());
		theme.setText(conference.getTheme());
	}
	
	@UiThread
	void onError() {
		dialog.cancel();
		Toast.makeText(this, "Server unavailable", Toast.LENGTH_LONG).show();
	}
	
}
