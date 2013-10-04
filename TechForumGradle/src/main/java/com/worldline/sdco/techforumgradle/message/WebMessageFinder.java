package com.worldline.sdco.techforumgradle.message;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.worldline.sdco.techforumgradle.rest.RestClient;
import com.worldline.sdco.techforumgradle.rest.pojo.Message;

import java.util.Arrays;
import java.util.List;

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
