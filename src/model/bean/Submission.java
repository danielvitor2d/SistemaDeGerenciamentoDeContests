package src.model.bean;

import java.sql.Timestamp;

public class Submission {
	private int submissionId;
	private SubmissionStatus status;
	private Timestamp timestamp;
	private String sourceCode;
	private int problemId;
	private int teamId;
	
	public Submission() {}

	public int getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(int submissionId) {
		this.submissionId = submissionId;
	}

	public SubmissionStatus getStatus() {
		return status;
	}

	public void setStatus(SubmissionStatus status) {
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

	@Override
	public String toString() {
		return "Submission [submissionId=" + submissionId + ", status=" + status + ", timestamp=" + timestamp
				+ ", sourceCode=" + sourceCode + ", problemId=" + problemId + ", teamId=" + teamId + "]";
	}

}
