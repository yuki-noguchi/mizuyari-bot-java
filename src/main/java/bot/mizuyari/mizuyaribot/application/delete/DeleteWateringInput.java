package bot.mizuyari.mizuyaribot.application.delete;

public class DeleteWateringInput {

  private String userId;
  private String plantName;

  public DeleteWateringInput(String userId, String plantName) {
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
