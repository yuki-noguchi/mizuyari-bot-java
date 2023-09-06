package bot.mizuyari.mizuyaribot.application.listwaterings;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import bot.mizuyari.mizuyaribot.domain.UserRepository;
import bot.mizuyari.mizuyaribot.domain.Watering;

@Component
public class ListWateringsService {

  @Autowired
  UserRepository userRepository;

  @Transactional
  public List<Watering> handle(ListWateringsInput input) {
    var user = userRepository.findById(input.getUserId()).get();

    return user.getWaterings();
  }
}
