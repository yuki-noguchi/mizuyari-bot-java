package bot.mizuyari.mizuyaribot.application.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import bot.mizuyari.mizuyaribot.domain.UserRepository;

@Component
public class RegistrationSessionStartService {

  @Autowired
  UserRepository userRepository;

  @Transactional
  public void handle(RegistrationSessionStartInput input) {
    var user = userRepository.findById(input.getUserId()).get();
    user.startRegistrationSession();

    userRepository.save(user);
  }
}
