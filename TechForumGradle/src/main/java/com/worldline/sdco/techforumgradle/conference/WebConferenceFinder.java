package com.worldline.sdco.techforumgradle.conference;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.rest.RestService;
import com.worldline.sdco.techforumgradle.rest.pojo.Conference;

import java.util.Arrays;
import java.util.List;

@EBean
public class WebConferenceFinder implements ConferenceFinder {

	@RestService
    com.worldline.sdco.techforumgradle.rest.RestClient restClient;

	public List<Conference> findAll() {
		return Arrays.asList(restClient.getConferences());
	}

	@Override
	public Conference find(String id) {
		System.out.println("IDDD " + id);
		return restClient.getConferenceById(id);
	}
}
