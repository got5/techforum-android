package com.worldline.sdco.techforumgradle.conference;


import com.worldline.sdco.techforumgradle.rest.pojo.Conference;

import java.util.List;

public interface ConferenceFinder {

	public List<Conference> findAll();
	public Conference find(String id);
	
}
