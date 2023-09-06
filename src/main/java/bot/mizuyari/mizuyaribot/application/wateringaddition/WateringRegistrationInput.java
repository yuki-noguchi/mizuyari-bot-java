package bot.mizuyari.mizuyaribot.application.wateringaddition;

public class WateringRegistrationInput {

  private String userId;
  private String plantName;

  public WateringRegistrationInput(String userId, String plantName) {
    this.userId = userId;
    this.plantName = plantName;
  }

  public String getUserId() {
    return userId;
  }

  public String getPlantName() {
    return plantName;
  }
}
