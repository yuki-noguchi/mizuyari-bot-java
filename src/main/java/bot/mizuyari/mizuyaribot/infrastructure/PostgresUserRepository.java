package bot.mizuyari.mizuyaribot.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import bot.mizuyari.mizuyaribot.domain.User;
import bot.mizuyari.mizuyaribot.domain.UserRepository;

@Repository
public interface PostgresUserRepository extends UserRepository, JpaRepository<User, String> {

}
