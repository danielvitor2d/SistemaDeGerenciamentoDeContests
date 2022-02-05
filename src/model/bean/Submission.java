package model.bean;

import java.sql.Timestamp;

public class Submission {
	private int submissionId;
	private String status;
	private Timestamp timestamp;
	private String sourceCode;
	private int contestId;
	private int problemId;
	private int teamId;
	private int judgeId;
	
	public Submission() {
	}

	public int getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(int submissionId) {
		this.submissionId = submissionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public int getContestId() {
		return contestId;
	}

	public void setContestId(int contestId) {
		this.contestId = contestId;
	}

	public int getProblemId() {
		return problemId;
	}

	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getJudgeId() {
		return judgeId;
	}

	public void setJudgeId(int judgeId) {
		this.judgeId = judgeId;
	}

	@Override
	public String toString() {
		return "Submission [submissionId=" + submissionId + ", status=" + status + ", timestamp=" + timestamp
				+ ", sourceCode=" + sourceCode + ", contestId=" + contestId + ", problemId=" + problemId + ", teamId="
				+ teamId + ", judgeId=" + judgeId + "]";
	}

}
