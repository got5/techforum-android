package net.atos.techforum.android.message;

import net.atos.techforum.android.R;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.widget.ListView;

@EActivity(R.layout.activity_feelback_list)
public class FeelbackListActivity extends Activity {

	@ViewById
	ListView list;

	@Bean
	MessageListAdapter adapter;

	@AfterViews
	@Background
	void initAdapter() {
		list.setAdapter(adapter);
	}

}
