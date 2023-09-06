package bot.mizuyari.mizuyaribot.application.registration;

public class RegistrationSessionStartInput {

  private String userId;

  public RegistrationSessionStartInput(String userId) {
    this.userId = userId;
  }

  public String getUserId() {
    return userId;
  }
}
