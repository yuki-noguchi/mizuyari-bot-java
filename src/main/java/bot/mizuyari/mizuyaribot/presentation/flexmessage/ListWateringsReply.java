package bot.mizuyari.mizuyaribot.presentation.flexmessage;

import java.time.format.DateTimeFormatter;
import bot.mizuyari.mizuyaribot.domain.Watering;

public class ListWateringsReply implements FlexMessageReply {

  private final Watering watering;

  public ListWateringsReply(Watering watering) {
    this.watering = watering;
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
                  "text": "%s",
                  "weight": "bold",
                  "size": "xl"
                },
                {
                  "type": "text",
                  "text": "・%d日ごとに水やり",
                  "margin": "md"
                },
                {
                  "type": "text",
                  "text": "・次は%s"
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
                    "type": "postback",
                    "label": "登録情報を編集する",
                    "data": "action=updateSessionStart&plantName=%s",
                    "inputOption": "openKeyboard"
                  }
                },
                {
                  "type": "button",
                  "style": "link",
                  "height": "sm",
                  "action": {
                    "type": "postback",
                    "label": "削除する",
                    "data": "action=deleteWatering&plantName=%s"
                  }
                }
              ],
              "flex": 0
            }
          }
          }
            """.formatted(watering.getPlantName(), watering.getFrequencyInDays(),
        watering.getNextDate().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")),
        watering.getPlantName(), watering.getPlantName());
  }
}
