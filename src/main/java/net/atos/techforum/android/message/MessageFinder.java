package net.atos.techforum.android.message;

import net.atos.techforum.android.rest.pojo.Message;

import java.util.List;

public interface MessageFinder {
	
	public List<Message> findAllFeelbacks();
	public List<Message> findAllComments(String id);
}
