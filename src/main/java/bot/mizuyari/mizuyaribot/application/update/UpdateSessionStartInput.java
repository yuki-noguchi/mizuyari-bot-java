package bot.mizuyari.mizuyaribot.application.update;

public class UpdateSessionStartInput {

  private String userId;
  private String plantName;

  public UpdateSessionStartInput(String userId, String plantName) {
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
