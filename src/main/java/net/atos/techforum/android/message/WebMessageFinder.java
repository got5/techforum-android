package net.atos.techforum.android.message;

import java.util.Arrays;
import java.util.List;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.rest.RestService;

import net.atos.techforum.android.rest.RestClient;
import net.atos.techforum.android.rest.pojo.Message;

@EBean
public class WebMessageFinder implements MessageFinder {

	@RestService
	RestClient restClient;

	@Override
	public List<Message> findAllFeelbacks() {
		return Arrays.asList(restClient.getFeelbacks());
	}

	@Override
	public List<Message> findAllComments(String id) {
		return Arrays.asList(restClient.getComments(id));
	}

}
