package com.worldline.sdco.techforumgradle.rest;


import com.googlecode.androidannotations.annotations.rest.Accept;
import com.googlecode.androidannotations.annotations.rest.Get;
import com.googlecode.androidannotations.annotations.rest.Post;
import com.googlecode.androidannotations.annotations.rest.Rest;
import com.googlecode.androidannotations.api.rest.MediaType;
import com.worldline.sdco.techforumgradle.rest.pojo.Conference;
import com.worldline.sdco.techforumgradle.rest.pojo.Message;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Rest(rootUrl = "http://techforum-mvand.rhcloud.com", converters = MappingJackson2HttpMessageConverter.class)
public interface RestClient {

	@Get("/conferences")
	@Accept(MediaType.APPLICATION_JSON)
	public Conference[] getConferences();
	
	@Get("/conferences/{id}")
	@Accept(MediaType.APPLICATION_JSON)
	public Conference getConferenceById(String id);
	
	@Get("/messages/feelbacks")
	@Accept(MediaType.APPLICATION_JSON)
	public Message[] getFeelbacks();
	
	@Get("/messages/comments/{id}")
	@Accept(MediaType.APPLICATION_JSON)
	public Message[] getComments(String id);

	@Post("/messages/comments")
	@Accept(MediaType.APPLICATION_JSON)
	public Message saveComment(Message message);
	
	@Post("/messages/feelbacks")
	@Accept(MediaType.APPLICATION_JSON)
	public ResponseEntity<Message> saveFeelback(Message message);
	
	
	
	

}
