package src.model.bean;

public class ViewTeam {
  private String student01;
  private String student02;
  private String student03;
  private String coach;

  public ViewTeam() {}

  public String getStudent01() {
    return student01;
  }
  public void setStudent01(String student01) {
    this.student01 = student01;
  }
  public String getStudent02() {
    return student02;
  }
  public void setStudent02(String student02) {
    this.student02 = student02;
  }
  public String getStudent03() {
    return student03;
  }
  public void setStudent03(String student03) {
    this.student03 = student03;
  }
  public String getCoach() {
    return coach;
  }
  public void setCoach(String coach) {
    this.coach = coach;
  }
  @Override
  public String toString() {
    return "ViewTeam [coach=" + coach + ", student01=" + student01 + ", student02=" + student02 + ", student03="
        + student03 + "]";
  }
  
}
