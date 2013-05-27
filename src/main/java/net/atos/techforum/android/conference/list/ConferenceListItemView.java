package net.atos.techforum.android.conference.list;

import net.atos.techforum.android.R;
import net.atos.techforum.android.rest.pojo.Conference;

import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.ViewById;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

@EViewGroup(R.layout.item_conference_list)
public class ConferenceListItemView extends LinearLayout {

	@ViewById
	TextView header, content, theme;
	
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
