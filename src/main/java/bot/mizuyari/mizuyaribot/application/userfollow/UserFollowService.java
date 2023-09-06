package bot.mizuyari.mizuyaribot.application.userfollow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import bot.mizuyari.mizuyaribot.domain.User;
import bot.mizuyari.mizuyaribot.domain.UserRepository;

@Component
public class UserFollowService {

  @Autowired
  UserRepository userRepository;

  @Transactional
  public void handle(UserFollowInput input) {
    var user = new User(input.getUserId());
    userRepository.save(user);
  }
}
