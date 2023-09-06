package bot.mizuyari.mizuyaribot.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import bot.mizuyari.mizuyaribot.application.userfollow.UserFollowInput;
import bot.mizuyari.mizuyaribot.application.userfollow.UserFollowService;
import bot.mizuyari.mizuyaribot.presentation.flexmessage.InitialReply;

@LineMessageHandler
public class FollowEventHandler extends EventHandler<FollowEvent> {

  @Autowired
  UserFollowService userFollowService;

  @Override
  @EventMapping
  public void handle(FollowEvent event) {
    userFollowService.handle(new UserFollowInput(event.getSource().getUserId()));
    replyMessage(event.getReplyToken(),
        new InitialReply("フォローありがとうございます。", "以下のメニューから操作を", "開始してください。"));
    return;
  }
}
