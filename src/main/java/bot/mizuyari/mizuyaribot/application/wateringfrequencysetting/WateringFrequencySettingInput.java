package bot.mizuyari.mizuyaribot.application.wateringfrequencysetting;

public class WateringFrequencySettingInput {

  private String userId;
  private int frequencyInDays;

  public WateringFrequencySettingInput(String userId, int frequencyInDays) {
    this.userId = userId;
    this.frequencyInDays = frequencyInDays;
  }

  public String getUserId() {
    return userId;
  }

  public int getFrequencyInDays() {
    return frequencyInDays;
  }
}
