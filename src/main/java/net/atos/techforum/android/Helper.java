package net.atos.techforum.android;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Helper {

	public static void resizeListView(ListView list) {
		ListAdapter adapter = list.getAdapter();
		if(adapter != null) {
			int totalHeight = 0;
			View item;
			for(int i = 0; i < adapter.getCount(); i++) {
				item = adapter.getView(i, null, list);
				item.measure(0, 0);
				totalHeight += item.getMeasuredHeight();
			}
			
			ViewGroup.LayoutParams params = list.getLayoutParams();
			params.height = totalHeight + (list.getDividerHeight() * (adapter.getCount() - 1));
			list.setLayoutParams(params);
		}
	}
}
