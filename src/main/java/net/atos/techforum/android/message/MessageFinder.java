package net.atos.techforum.android.message;

import java.util.List;

import net.atos.techforum.android.rest.pojo.Message;

public interface MessageFinder {
	
	public List<Message> findAllFeelbacks();
	public List<Message> findAllComments(String id);
}
