package bot.mizuyari.mizuyaribot.application.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import bot.mizuyari.mizuyaribot.domain.UserRepository;

@Component
public class DeleteWateringService {

  @Autowired
  UserRepository userRepository;

  @Transactional
  public void handle(DeleteWateringInput input) {
    var user = userRepository.findById(input.getUserId()).get();
    user.deleteWatering(input.getPlantName());
    userRepository.save(user);
  }
}
