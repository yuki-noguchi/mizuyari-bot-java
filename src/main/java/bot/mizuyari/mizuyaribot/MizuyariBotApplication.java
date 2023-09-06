package bot.mizuyari.mizuyaribot;

import java.lang.reflect.InvocationTargetException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MizuyariBotApplication {

  public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException,
      NoSuchMethodException, SecurityException {
    SpringApplication.run(MizuyariBotApplication.class, args);
  }
}
