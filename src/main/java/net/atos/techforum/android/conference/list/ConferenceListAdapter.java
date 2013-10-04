package net.atos.techforum.android.conference.list;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;
import com.googlecode.androidannotations.annotations.UiThread;

import net.atos.techforum.android.conference.ConferenceFinder;
import net.atos.techforum.android.conference.WebConferenceFinder;
import net.atos.techforum.android.rest.pojo.Conference;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
 
@EBean
public class ConferenceListAdapter extends BaseAdapter implements Filterable {

	List<Conference> list = Collections.emptyList();
	List<Conference> completeList = Collections.emptyList();

	@Bean(WebConferenceFinder.class)
	ConferenceFinder conferenceFinder;

	@RootContext
	Context context;

	ProgressDialog dialog;
	
	@AfterInject
	@Background
	void init() {
		onRequest();
		List<Conference> result = conferenceFinder.findAll();
		// if web service is unavailable, list will be set to null
		if (result != null) {
			list = result;
			completeList = result;
			onResponse();
		} else
			onError();
	}
	
	@UiThread
	void onRequest() {
		dialog = ProgressDialog.show(context, "Loading...", "Retrieving data from server");
	}
	
	@UiThread
	void onResponse() {
		dialog.cancel();
		this.notifyDataSetChanged();
		this.getFilter().filter("room1");
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
	public Conference getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ConferenceListItemView conferenceItemView;
		if (convertView == null) {
			conferenceItemView = ConferenceListItemView_.build(context);
		} else {
			conferenceItemView = (ConferenceListItemView) convertView;
		}
		conferenceItemView.bind(getItem(position));
		return conferenceItemView;
	}
	
	private Filter filter;

	@Override
	public Filter getFilter() {
		if (filter == null)
			filter = new ConferenceFilter();
		return filter;
	}

	private class ConferenceFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();
			if (constraint == null || constraint.length() == 0) {
				results.values = list;
				results.count = list.size();
			} else {
				if(constraint.equals("room1"))
					results = performFilteringByRoom(1);
				else if(constraint.equals("room2"))
					results = performFilteringByRoom(2);
				else if(constraint.equals("room3"))
					results = performFilteringByRoom(3);
				else if(constraint.equals("room4"))
					results = performFilteringByRoom(4);
			}
			return results;
		}

		private FilterResults performFilteringByRoom(int room) {
			FilterResults results = new FilterResults();
			List<Conference> r_list = new LinkedList<Conference>();
			for (Conference c : completeList) {
				if (room == c.getWhere()) {
					r_list.add(c);
				}
			}
			Collections.sort(r_list);
			results.values = r_list;
			results.count = r_list.size();
			return results;
		}

		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			if (results.count == 0) {
				notifyDataSetInvalidated();
			} else {
				list = (List<Conference>) results.values;
				notifyDataSetChanged();
			}

		}

	}

}
