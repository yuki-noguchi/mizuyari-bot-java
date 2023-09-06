package bot.mizuyari.mizuyaribot.application.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import bot.mizuyari.mizuyaribot.domain.UserRepository;

@Component
public class UpdateSessionStartService {

  @Autowired
  UserRepository userRepository;

  @Transactional
  public void handle(UpdateSessionStartInput input) {
    var user = userRepository.findById(input.getUserId()).get();
    user.startUpdateSession(input.getPlantName());

    userRepository.save(user);
  }
}
