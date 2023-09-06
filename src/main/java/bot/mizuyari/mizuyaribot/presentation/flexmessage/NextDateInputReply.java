package bot.mizuyari.mizuyaribot.presentation.flexmessage;

import java.time.LocalDate;

public class NextDateInputReply implements FlexMessageReply {

  @Override
  public String toJson() {
    var today = LocalDate.now();
    var tenYearsLater = today.plusYears(10);

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
                },
                {
                  "type": "text",
                  "text": "次の水やり日を選択してください。",
                  "margin": "md"
                }
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
                    "type": "datetimepicker",
                    "label": "日付を選択する",
                    "mode": "date",
                    "data": "action=nextDateRegistration",
                    "initial": "%1$tY-%1$tm-%1$td",
                    "min": "%1$tY-%1$tm-%1$td",
                    "max": "%2$tY-%2$tm-%2$td"
                  }
                },
                {
                  "type": "button",
                  "style": "link",
                  "height": "sm",
                  "action": {
                    "type": "postback",
                    "label": "登録をキャンセルする",
                    "data": "action=cancelRegistrationSession"
                  }
                }
              ],
              "flex": 0
            }
          }
          }
            """.formatted(today, tenYearsLater);
  }
}
