package net.atos.techforum.android.conference;

import java.util.List;

import net.atos.techforum.android.rest.pojo.Conference;

public interface ConferenceFinder {

	public List<Conference> findAll();
	public Conference find(String id);
	
}
