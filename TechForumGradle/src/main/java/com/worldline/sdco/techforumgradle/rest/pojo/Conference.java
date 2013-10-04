package com.worldline.sdco.techforumgradle.rest.pojo;

public class Conference implements Comparable<Conference> {

	public class When {
		private String _start, _end;

		public String getStart() {
			return _start;
		}

		public String getEnd() {
			return _end;
		}

		public void setStart(String start) {
			this._start = start;
		}

		public void setEnd(String end) {
			this._end = end;
		}

	}

	private String _id;
	private String title;
	private String author;
	private When when;
	private int where;
	private int day;
	private String theme;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public When getWhen() {
		return when;
	}

	public void setWhen(When when) {
		this.when = when;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getWhere() {
		return where;
	}

	public void setWhere(int where) {
		this.where = where;
	}
	
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	@Override
	public int compareTo(Conference another) {
		if(this.getDay() < another.getDay()) return -1;
		else if(this.getDay() > another.getDay()) return 1;
		else return this.getWhen().getStart().compareTo(another.getWhen().getStart());
	}

}
