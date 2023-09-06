package bot.mizuyari.mizuyaribot.presentation.flexmessage;

import java.util.List;
import java.util.stream.Collectors;

public class InitialReply implements FlexMessageReply {

  private List<String> additionalMessages;

  public InitialReply(String... additionalMessages) {
    this.additionalMessages = List.of(additionalMessages);
  }

  @Override
  public String toJson() {
    return """
        {
          "type": "flex",
          "altText" : "水やりリマインダー",
          "contents" : {
            "type": "bubble",
            "body": {
              "type": "box",
              "layout": "vertical",
              "contents": [
                {
                  "type": "text",
                  "text": "水やりリマインダー",
                  "weight": "bold",
                  "size": "xl"
                }
                %s
              ]
            },
            "footer": {
              "type": "box",
              "layout": "vertical",
              "spacing": "sm",
              "contents": [
                {
                  "type": "button",
                  "style": "primary",
                  "height": "sm",
                  "action": {
                    "type": "postback",
                    "label": "植物を登録する",
                    "data": "action=registrationSessionStart",
                    "inputOption": "openKeyboard"
                  }
                },
                {
                  "type": "button",
                  "style": "link",
                  "height": "sm",
                  "action": {
                    "type": "postback",
                    "label": "登録した植物一覧を確認する",
                    "data": "action=listWaterings"
                  }
                }
              ],
              "flex": 0
            }
          }
          }
            """.formatted(additionalMessagesToJson());
  }

  private String additionalMessagesToJson() {
    var json = additionalMessages.stream().map(message -> {
      return """
          {
            "type": "text",
            "text": "%s",
            "margin": "md"
          }
            """.formatted(message);
    }).collect(Collectors.joining(","));

    if (additionalMessages.isEmpty()) {
      return "";
    }

    return "," + json;
  }
}
