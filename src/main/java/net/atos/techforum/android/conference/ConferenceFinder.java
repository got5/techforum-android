package net.atos.techforum.android.conference;

import net.atos.techforum.android.rest.pojo.Conference;

import java.util.List;

public interface ConferenceFinder {

	public List<Conference> findAll();
	public Conference find(String id);
	
}
