package com.worldline.sdco.techforumgradle.conference.list;

import android.content.Context;
import android.widget.LinearLayout;

import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.ViewById;
import com.worldline.sdco.techforumgradle.R;
import com.worldline.sdco.techforumgradle.rest.pojo.Conference;


@EViewGroup(R.layout.item_conference_list)
public class ConferenceListItemView extends LinearLayout {

	@ViewById
    android.widget.TextView header, content, theme;
	
	public ConferenceListItemView(Context context) {
		super(context);
		this.setOrientation(LinearLayout.VERTICAL);
	}
	
	public void bind(Conference c) {
		header.setText(String.format("%s - %s", c.getWhen().getStart(), c.getWhen().getEnd()));
		theme.setText(c.getTheme());
		content.setText(String.format("%s by %s", c.getTitle(), c.getAuthor()));
	}

}
