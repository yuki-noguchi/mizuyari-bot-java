package bot.mizuyari.mizuyaribot.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.message.Message;
import bot.mizuyari.mizuyaribot.presentation.flexmessage.FlexMessageReply;

public abstract class EventHandler<EVENT extends Event> {

  @Autowired
  private LineMessagingClient client;

  public abstract void handle(EVENT event);

  protected void replyMessage(String replyToken, FlexMessageReply message) {
    client.replyMessage(new ReplyMessage(replyToken, message.getFlexMessage()));
  }

  protected void replyMessage(String replyToken, Message message) {
    client.replyMessage(new ReplyMessage(replyToken, message));
  }

  protected void pushMessage(String userId, FlexMessageReply message) {
    client.pushMessage(new PushMessage(userId, message.getFlexMessage()));
  }

  protected void pushMessage(String userId, Message message) {
    client.pushMessage(new PushMessage(userId, message));
  }
}
