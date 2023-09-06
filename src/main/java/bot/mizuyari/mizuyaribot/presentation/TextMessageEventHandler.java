package bot.mizuyari.mizuyaribot.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import bot.mizuyari.mizuyaribot.application.userfollow.UserFollowService;
import bot.mizuyari.mizuyaribot.application.wateringaddition.WateringRegistrationInput;
import bot.mizuyari.mizuyaribot.application.wateringaddition.WateringRegistrationService;
import bot.mizuyari.mizuyaribot.application.wateringfrequencysetting.WateringFrequencySettingInput;
import bot.mizuyari.mizuyaribot.application.wateringfrequencysetting.WateringFrequencySettingService;
import bot.mizuyari.mizuyaribot.domain.User;
import bot.mizuyari.mizuyaribot.domain.UserRepository;
import bot.mizuyari.mizuyaribot.domain.WateringStatus;
import bot.mizuyari.mizuyaribot.presentation.flexmessage.InitialReply;
import bot.mizuyari.mizuyaribot.presentation.flexmessage.NextDateInputReply;
import bot.mizuyari.mizuyaribot.presentation.flexmessage.WateringRegistrationReply;

@LineMessageHandler
public class TextMessageEventHandler extends EventHandler<MessageEvent<TextMessageContent>> {

  @Autowired
  UserFollowService userFollowService;
  @Autowired
  WateringRegistrationService wateringRegistrationService;
  @Autowired
  WateringFrequencySettingService wateringFrequencySettingService;
  @Autowired
  UserRepository userRepository;

  @Override
  @EventMapping
  public void handle(MessageEvent<TextMessageContent> event) {
    String userId = event.getSource().getUserId();

    User user = userRepository.findById(userId).get();

    switch (user.getStatus()) {
      case STANDBY -> {
        replyMessage(event.getReplyToken(), new InitialReply());
      }
      case PROCESSING -> {
        if (user.getWaterings().stream()
            .anyMatch(watering -> watering.getStatus() == WateringStatus.WAITING_FOR_PLANT_NAME)) {
          wateringRegistrationService
              .handle(new WateringRegistrationInput(userId, event.getMessage().getText()));

          replyMessage(event.getReplyToken(), new WateringRegistrationReply());
          return;
        }
        if (user.getWaterings().stream().anyMatch(
            watering -> watering.getStatus() == WateringStatus.WAITING_FOR_FREQUENCY_IN_DAYS)) {
          try {
            var frequency = Integer.parseInt(event.getMessage().getText());
            wateringFrequencySettingService
                .handle(new WateringFrequencySettingInput(userId, frequency));

            replyMessage(event.getReplyToken(), new NextDateInputReply());
          } catch (NumberFormatException e) {
            replyMessage(event.getReplyToken(), new TextMessage("半角数字で入力してください。 例: 90"));
          }
        }
      }
      default -> {
        // NOP
      }
    }
  }
}
