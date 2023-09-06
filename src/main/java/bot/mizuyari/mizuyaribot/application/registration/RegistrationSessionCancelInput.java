package bot.mizuyari.mizuyaribot.application.registration;

public class RegistrationSessionCancelInput {

  private String userId;

  public RegistrationSessionCancelInput(String userId) {
    this.userId = userId;
  }

  public String getUserId() {
    return userId;
  }
}
