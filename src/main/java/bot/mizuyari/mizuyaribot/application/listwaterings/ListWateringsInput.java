package bot.mizuyari.mizuyaribot.application.listwaterings;

public class ListWateringsInput {

  private String userId;

  public ListWateringsInput(String userId) {
    this.userId = userId;
  }

  public String getUserId() {
    return userId;
  }
}
