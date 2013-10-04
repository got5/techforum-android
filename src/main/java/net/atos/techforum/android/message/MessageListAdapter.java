package net.atos.techforum.android.message;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;
import com.googlecode.androidannotations.annotations.UiThread;

import net.atos.techforum.android.Helper;
import net.atos.techforum.android.rest.pojo.Message;

import java.util.Collections;
import java.util.List;

@EBean
public class MessageListAdapter extends BaseAdapter {

	List<Message> list = Collections.emptyList();

	@RootContext
	Context context;
	
	@RootContext
	Activity activity;

	@Bean(WebMessageFinder.class)
	MessageFinder messageFinder;

	ProgressDialog dialog;

	@AfterInject
	@Background
	void init() {
		onRequest();
		List<Message> result = null;
		if(activity instanceof ConferenceDetailsActivity) {
			Log.i("MessageAdapter", "Instanceof activity : ConferenceDetailsActivity");
			result = messageFinder.findAllComments(((ConferenceDetailsActivity)activity).id_conference);
		} else if(activity instanceof FeelbackListActivity) {
			Log.i("MessageAdapter", "Instanceof activity : FeelbackListActivity");
			result = messageFinder.findAllFeelbacks();
		}
		Collections.reverse(result);
		// if web service is unavailable, list will be set to null
		if (result != null) {
			list = result;
			onResponse();
		} else {
			onError();
		}
	}

	@UiThread
	void onRequest() {
		dialog = ProgressDialog.show(context, "Loading...",
				"Retrieving data from server");
		dialog.setCancelable(true);
	}

	
	@UiThread
	void onResponse() {
		dialog.cancel();
		this.notifyDataSetChanged();
		if(activity instanceof ConferenceDetailsActivity)
			Helper.resizeListView(((ConferenceDetailsActivity)activity).list);
		else if(activity instanceof FeelbackListActivity)
			Helper.resizeListView(((FeelbackListActivity)activity).list);
	}

	@UiThread
	void onError() {
		dialog.cancel();
		Toast.makeText(context, "Server unavailable", Toast.LENGTH_LONG).show();
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Message getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MessageListItemView itemView;
		if (convertView == null) {
			itemView = MessageListItemView_.build(context);
		} else {
			itemView = (MessageListItemView) convertView;
		}
		itemView.bind(getItem(position));
		return itemView;
	}

}
