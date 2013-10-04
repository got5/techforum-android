package com.worldline.sdco.techforumgradle.message;


import com.worldline.sdco.techforumgradle.rest.pojo.Message;

import java.util.List;

public interface MessageFinder {
	
	public List<Message> findAllFeelbacks();
	public List<Message> findAllComments(String id);
}
