package bot.mizuyari.mizuyaribot.presentation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import bot.mizuyari.mizuyaribot.application.delete.DeleteWateringInput;
import bot.mizuyari.mizuyaribot.application.delete.DeleteWateringService;
import bot.mizuyari.mizuyaribot.application.listwaterings.ListWateringsInput;
import bot.mizuyari.mizuyaribot.application.listwaterings.ListWateringsService;
import bot.mizuyari.mizuyaribot.application.registration.RegistrationSessionCancelInput;
import bot.mizuyari.mizuyaribot.application.registration.RegistrationSessionCancelService;
import bot.mizuyari.mizuyaribot.application.registration.RegistrationSessionStartInput;
import bot.mizuyari.mizuyaribot.application.registration.RegistrationSessionStartService;
import bot.mizuyari.mizuyaribot.application.update.UpdateSessionStartInput;
import bot.mizuyari.mizuyaribot.application.update.UpdateSessionStartService;
import bot.mizuyari.mizuyaribot.application.wateringnextdatesetting.WateringNextDateSettingInput;
import bot.mizuyari.mizuyaribot.application.wateringnextdatesetting.WateringNextDateSettingService;
import bot.mizuyari.mizuyaribot.presentation.flexmessage.InitialReply;
import bot.mizuyari.mizuyaribot.presentation.flexmessage.ListWateringsReply;
import bot.mizuyari.mizuyaribot.presentation.flexmessage.RegistrationSessionStartReply;

@LineMessageHandler
public class PostbackEventHandler extends EventHandler<PostbackEvent> {

  @Autowired
  RegistrationSessionStartService registrationSessionStartService;
  @Autowired
  WateringNextDateSettingService wateringNextDateSettingService;
  @Autowired
  RegistrationSessionCancelService registrationSessionCanceltService;
  @Autowired
  ListWateringsService listWateringsService;
  @Autowired
  UpdateSessionStartService updateSessionStartService;
  @Autowired
  DeleteWateringService deleteWateringService;

  @Override
  @EventMapping
  public void handle(PostbackEvent event) {
    String userId = event.getSource().getUserId();

    var data = parseData(event);

    switch (data.get("action")) {
      case "registrationSessionStart" -> {
        registrationSessionStartService.handle(new RegistrationSessionStartInput(userId));

        replyMessage(event.getReplyToken(), new RegistrationSessionStartReply());
      }
      case "nextDateRegistration" -> {
        var date = LocalDate.parse(event.getPostbackContent().getParams().get("date"),
            DateTimeFormatter.ISO_DATE);
        wateringNextDateSettingService.handle(new WateringNextDateSettingInput(userId, date));

        replyMessage(event.getReplyToken(), new TextMessage("登録が完了しました。"));
        pushMessage(userId, new InitialReply());
      }
      case "cancelRegistrationSession" -> {
        registrationSessionCanceltService.handle(new RegistrationSessionCancelInput(userId));

        replyMessage(event.getReplyToken(), new TextMessage("登録をキャンセルしました。"));
        pushMessage(userId, new InitialReply());
      }
      case "listWaterings" -> {
        var waterings = listWateringsService.handle(new ListWateringsInput(userId));

        if (waterings.isEmpty()) {
          replyMessage(event.getReplyToken(), new TextMessage("まだ登録されていません。先に植物を登録をしてください。"));
          pushMessage(userId, new InitialReply());
          return;
        }

        waterings.forEach(watering -> {
          pushMessage(userId, new ListWateringsReply(watering));
        });
      }
      case "updateSessionStart" -> {
        updateSessionStartService
            .handle(new UpdateSessionStartInput(userId, data.get("plantName")));

        replyMessage(event.getReplyToken(), new RegistrationSessionStartReply());
      }
      case "deleteWatering" -> {
        deleteWateringService.handle(new DeleteWateringInput(userId, data.get("plantName")));

        replyMessage(event.getReplyToken(), new TextMessage(data.get("plantName") + "を削除しました。"));
        pushMessage(userId, new InitialReply());
      }
      default -> {
        // NOP
      }
    }
  }

  private Map<String, String> parseData(PostbackEvent event) {
    return Stream.of(event.getPostbackContent().getData().split("&")).map(pair -> pair.split("="))
        .collect(Collectors.toMap(keyValue -> keyValue[0], keyValue -> keyValue[1]));
  }
}
