package net.atos.techforum.android.message;

import java.util.Date;

import org.springframework.http.ResponseEntity;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EViewGroup;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.rest.RestService;

import net.atos.techforum.android.R;
import net.atos.techforum.android.rest.RestClient;
import net.atos.techforum.android.rest.pojo.Message;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

@EViewGroup(R.layout.item_message_form)
public class MessageForm extends LinearLayout {

	@ViewById
	EditText name, content;

	@RestService
	RestClient restClient;

	public MessageForm(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOrientation(LinearLayout.VERTICAL);
	}

	@Background
	@Click
	void submit() {
		String s_name = name.getText().toString();
		String s_content = content.getText().toString();
		if (s_name.equals("") || s_content.equals("")) {
			makeToast("Please fill both field befire submitting");
		} else {
			Date d = new Date();
			Message m = new Message();
			m.setName(s_name);
			m.setMsg(s_content);
			m.setDate(Message.SDF.format(d));

			ResponseEntity<Message> r = restClient.saveFeelback(m);
			makeToast("Thanks for your message");
		}
	}

	@UiThread
	void makeToast(String text) {
		Toast.makeText(this.getContext(), text, Toast.LENGTH_LONG).show();
	}

}
