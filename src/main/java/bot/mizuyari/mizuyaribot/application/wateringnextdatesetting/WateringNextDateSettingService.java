package bot.mizuyari.mizuyaribot.application.wateringnextdatesetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import bot.mizuyari.mizuyaribot.domain.UserRepository;

@Component
public class WateringNextDateSettingService {

  @Autowired
  UserRepository userRepository;

  @Transactional
  public void handle(WateringNextDateSettingInput input) {
    var user = userRepository.findById(input.getUserId()).get();
    user.setWateringNextDate(input.getNextDate());

    userRepository.save(user);
  }
}
