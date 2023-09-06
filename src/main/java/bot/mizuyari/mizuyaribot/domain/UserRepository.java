package bot.mizuyari.mizuyaribot.domain;

import java.util.Optional;

public interface UserRepository {

  public Optional<User> findById(String userId);

  public User save(User user);
}
