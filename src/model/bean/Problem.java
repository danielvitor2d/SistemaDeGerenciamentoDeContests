package src.model.bean;

public class Problem {
	private int problemId;
	private String title;
	private String description;
	private double timelimit;
	private String sampleInputProblem;
	private String sampleOutputProblem;
	private int contestId;
	
	public Problem() {}

	public int getProblemId() {
		return problemId;
	}

	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTimelimit() {
		return timelimit;
	}

	public void setTimelimit(double timelimit) {
		this.timelimit = timelimit;
	}

	public String getSampleInputProblem() {
		return sampleInputProblem;
	}

	public void setSampleInputProblem(String sampleInputProblem) {
		this.sampleInputProblem = sampleInputProblem;
	}

	public String getSampleOutputProblem() {
		return sampleOutputProblem;
	}

	public void setSampleOutputProblem(String sampleOutputProblem) {
		this.sampleOutputProblem = sampleOutputProblem;
	}

	public int getContestId() {
		return contestId;
	}

	public void setContestId(int contestId) {
		this.contestId = contestId;
	}

	@Override
	public String toString() {
		return "Problem [problemId=" + problemId + ", title=" + title + ", description=" + description + ", timelimit=" + timelimit
				+ ", sampleInputProblem=" + sampleInputProblem + ", sampleOutputProblem=" + sampleOutputProblem
				+ ", contestId=" + contestId + "]";
	}

}
