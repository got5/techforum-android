package net.atos.techforum.android.conference.list;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ItemClick;
import com.googlecode.androidannotations.annotations.ViewById;

import net.atos.techforum.android.R;
import net.atos.techforum.android.message.ConferenceDetailsActivity;
import net.atos.techforum.android.rest.pojo.Conference;

@EActivity(R.layout.activity_conference_list)
public class ConferenceListActivity extends Activity {

	@ViewById
	ListView list; 

	@Bean 
	ConferenceListAdapter adapter;

	@AfterViews
	@Background 
	void initAdapter() {
		list.setAdapter(adapter);
	}

	@ItemClick
	void listItemClicked(Conference c) {
		Intent intent = new Intent(this, ConferenceDetailsActivity.class);
                //ConferenceDetailsActivity.intent(this).get();
		intent.putExtra("id_conference", c.get_id());
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater();
		return true;
	}

	@Click
	void room1() {
		applyFilter(1);
	}

	@Click
	void room2() {
		applyFilter(2);
	}

	@Click
	void room3() {
		applyFilter(3);
	}

	@Click
	void room4() {
		applyFilter(4);
	}

	void applyFilter(int i) {
		ConferenceListAdapter adapter = (ConferenceListAdapter) list
				.getAdapter();
		adapter.getFilter().filter("room" + i);
		Log.i("FILTER","List filtered by room"+i);
	}
}
