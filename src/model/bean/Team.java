package model.bean;

public class Team {
	private int teamId;
	private String teamName;
	private String teamPhotoUrl;
	private int studentId01;
	private int studentId02;
	private int studentId03;
	private int coachId;
	
	public Team() {
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamPhotoUrl() {
		return teamPhotoUrl;
	}

	public void setTeamPhotoUrl(String teamPhotoUrl) {
		this.teamPhotoUrl = teamPhotoUrl;
	}

	public int getStudentId01() {
		return studentId01;
	}

	public void setStudentId01(int studentId01) {
		this.studentId01 = studentId01;
	}

	public int getStudentId02() {
		return studentId02;
	}

	public void setStudentId02(int studentId02) {
		this.studentId02 = studentId02;
	}

	public int getStudentId03() {
		return studentId03;
	}

	public void setStudentId03(int studentId03) {
		this.studentId03 = studentId03;
	}

	public int getCoachId() {
		return coachId;
	}

	public void setCoachId(int coachId) {
		this.coachId = coachId;
	}

	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", teamName=" + teamName + ", teamPhotoUrl=" + teamPhotoUrl + ", studentId01="
				+ studentId01 + ", studentId02=" + studentId02 + ", studentId03=" + studentId03 + ", coachId=" + coachId
				+ "]";
	}
	
}
