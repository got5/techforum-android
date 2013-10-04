package com.worldline.sdco.techforumgradle.message;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.worldline.sdco.techforumgradle.Helper;
import com.worldline.sdco.techforumgradle.rest.pojo.Message;

import java.util.Collections;
import java.util.List;

@EBean
public class MessageListAdapter extends BaseAdapter {

	List<Message> list = Collections.emptyList();

	@RootContext
    android.content.Context context;
	
	@RootContext
    android.app.Activity activity;

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
		} else if(activity instanceof FeedbackListActivity) {
			Log.i("MessageAdapter", "Instanceof activity : FeedbackListActivity");
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
			Helper.resizeListView(((ConferenceDetailsActivity) activity).list);
		else if(activity instanceof FeedbackListActivity)
			Helper.resizeListView(((FeedbackListActivity)activity).list);
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
            // codez bruno
            LayoutInflater inflater = LayoutInflater.from(context);
            itemView = (MessageListItemView) inflater.inflate(parent.getRootView().getId(), null);
            // codez bruno
			//itemView = MessageListItemView_.build(context);
		} else {
			itemView = (MessageListItemView) convertView;
		}
		itemView.bind(getItem(position));
		return itemView;
	}

}
