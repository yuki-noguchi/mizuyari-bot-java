package bot.mizuyari.mizuyaribot.application.wateringfrequencysetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import bot.mizuyari.mizuyaribot.domain.UserRepository;

@Component
public class WateringFrequencySettingService {

  @Autowired
  UserRepository userRepository;

  @Transactional
  public void handle(WateringFrequencySettingInput input) {
    var user = userRepository.findById(input.getUserId()).get();
    user.setWateringFrequencyInDays(input.getFrequencyInDays());

    userRepository.save(user);
  }
}
