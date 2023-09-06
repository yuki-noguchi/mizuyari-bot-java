package bot.mizuyari.mizuyaribot.application.wateringaddition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import bot.mizuyari.mizuyaribot.domain.UserRepository;

@Component
public class WateringRegistrationService {

  @Autowired
  UserRepository userRepository;

  @Transactional
  public void handle(WateringRegistrationInput input) {
    var user = userRepository.findById(input.getUserId()).get();
    user.registerWatering(input.getPlantName());

    userRepository.save(user);
  }
}
