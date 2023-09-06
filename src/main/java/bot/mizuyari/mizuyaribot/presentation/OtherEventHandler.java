package bot.mizuyari.mizuyaribot.presentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@LineMessageHandler
public class OtherEventHandler extends EventHandler<Event> {

  private static final Logger logger = LoggerFactory.getLogger(OtherEventHandler.class);

  @Override
  @EventMapping
  public void handle(Event event) {
    logger.info("その他のイベントを受信しました。 イベント内容: {}", event);
  }

  @EventMapping
  public void handle(UnfollowEvent event) {
    logger.info("その他のイベントを受信しました。 イベント内容: {}", event);
  }
}
