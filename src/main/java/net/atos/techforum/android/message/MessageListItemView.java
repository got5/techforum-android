package net.atos.techforum.android.message;

import android.content.Context;
import android.text.format.DateFormat;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.ViewById;

import net.atos.techforum.android.R;
import net.atos.techforum.android.rest.pojo.Message;

@EViewGroup(R.layout.item_message_list)
public class MessageListItemView extends LinearLayout {
	
	@ViewById
	TextView name, date, content;
	
	public MessageListItemView(Context context) {
		super(context);
		this.setOrientation(LinearLayout.VERTICAL);
	}
	
	public void bind(Message m) {
		name.setText(m.getName());
		date.setText(DateFormat.format("@ kk:mm - dd/MM/yyyy",m.getDate()));
		content.setText(m.getMsg());
	}

}
