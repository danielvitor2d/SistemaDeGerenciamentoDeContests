package model.bean;

public class TeamAndContestRegistered {
  private String teamName;
  private int sum;
  public String getTeamName() {
    return teamName;
  }
  public void setTeamName(String teamName) {
    this.teamName = teamName;
  }
  public int getSum() {
    return sum;
  }
  public void setSum(int sum) {
    this.sum = sum;
  }
  @Override
  public String toString() {
    return "TeamAndContestRegistered [sum=" + sum + ", teamName=" + teamName + "]";
  }
}
