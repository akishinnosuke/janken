package oit.is.z3417.kaizi.janken.model;

public class Janken {
  private String myHand;
  private String cpuHand = "Gu";
  private String result;

  public Janken(String myHand) {
    this.myHand = myHand;

    if ("Gu".equals(myHand)) {
      this.result = "Draw";
    } else if ("Choki".equals(myHand)) {
      this.result = "You Lose";
    } else if ("Pa".equals(myHand)) {
      this.result = "You Win!";
    }
  }

  public String getMyHand() {
    return myHand;
  }

  public String getCpuHand() {
    return cpuHand;
  }

  public String getResult() {
    return result;
  }
}
