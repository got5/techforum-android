package net.atos.techforum.android.message;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;

import net.atos.techforum.android.R;
import net.atos.techforum.android.conference.ConferenceFinder;
import net.atos.techforum.android.conference.WebConferenceFinder;
import net.atos.techforum.android.rest.pojo.Conference;

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
	TextView title, author, theme, when, where;
	
	@ViewById
	ListView list;

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
