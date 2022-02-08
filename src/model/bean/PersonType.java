package src.model.bean;

public enum PersonType {
	COACH("COACH"),
	JUDGE("JUDGE"),
	STUDENT("STUDENT");
	
	private String text;
	
	private PersonType(String text) {
		this.text = text;
	}
	
	public String toString() {
		return this.text;
	}
}
