package bot.mizuyari.mizuyaribot.application.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import bot.mizuyari.mizuyaribot.domain.UserRepository;

@Component
public class RegistrationSessionCancelService {

  @Autowired
  UserRepository userRepository;

  @Transactional
  public void handle(RegistrationSessionCancelInput input) {
    var user = userRepository.findById(input.getUserId()).get();
    user.cancelRegistrationSession();

    userRepository.save(user);
  }
}
