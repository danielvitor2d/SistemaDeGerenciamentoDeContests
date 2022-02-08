package src.model.bean;

public enum SubmissionStatus {
	AC("AC"), 
	WA("WA"), 
	TLE("TLE"), 
	RE("RE"),
	MLE("MLE");
	
	private String text;
	
	private SubmissionStatus(String text) {
		this.text = text;
	}
	
	public String toString() {
		return this.text;
	}
}
