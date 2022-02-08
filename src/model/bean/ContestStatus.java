package src.model.bean;

public enum ContestStatus {
	NOT_STARTED("NOT_STARTED"),
	RUNNING("RUNNING"),
	FINISHED("FINISHED");
	
	private String text;
	
	private ContestStatus(String text) {
		this.text = text;
	}
	
	public String toString() {
		return this.text;
	}
}
