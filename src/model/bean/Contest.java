package model.bean;

import java.sql.Timestamp;

public class Contest {
	private int contestId;
	private String title;
	private Timestamp date;
	private double duration;
	private String place;
	private int judgeId;
	
	public Contest() {
	}

	public int getContestId() {
		return contestId;
	}

	public void setContestId(int contestId) {
		this.contestId = contestId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getJudgeId() {
		return judgeId;
	}

	public void setJudgeId(int judgeId) {
		this.judgeId = judgeId;
	}

	@Override
	public String toString() {
		return "Contest [contestId=" + contestId + ", title=" + title + ", date=" + date + ", duration=" + duration
				+ ", place=" + place + ", judgeId=" + judgeId + "]";
	}

}
