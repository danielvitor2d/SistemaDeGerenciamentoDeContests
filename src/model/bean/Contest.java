package src.model.bean;

import java.sql.Date;

public class Contest {
	private int contestId;
	private String title;
	private ContestStatus status;
	private Date dateTime;
	private double duration;
	private String place;
	private int judgeId;
	
	public Contest() {}

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

	public ContestStatus getStatus() {
		return status;
	}

	public void setStatus(ContestStatus status) {
		this.status = status;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
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
		return "Contest [contestId=" + contestId + ", dateTime=" + dateTime + ", duration=" + duration + ", judgeId="
				+ judgeId + ", place=" + place + ", status=" + status + ", title=" + title + "]";
	}

}
