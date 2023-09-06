package bot.mizuyari.mizuyaribot.application.wateringnextdatesetting;

import java.time.LocalDate;

public class WateringNextDateSettingInput {

  private String userId;
  private LocalDate nextDate;

  public WateringNextDateSettingInput(String userId, LocalDate nextDate) {
    this.userId = userId;
    this.nextDate = nextDate;
  }

  public String getUserId() {
    return userId;
  }


  public LocalDate getNextDate() {
    return nextDate;
  }
}
