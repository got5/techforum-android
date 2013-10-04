package com.worldline.sdco.techforumgradle.message;

import android.app.Activity;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.worldline.sdco.techforumgradle.R;


@EActivity(R.layout.activity_feelback_list)
public class FeedbackListActivity extends Activity {

	@ViewById
    android.widget.ListView list;

	@Bean
	MessageListAdapter adapter;

	@AfterViews
	@Background
	void initAdapter() {
		list.setAdapter(adapter);
	}

}
