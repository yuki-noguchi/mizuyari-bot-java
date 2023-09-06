package bot.mizuyari.mizuyaribot.presentation.flexmessage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.model.message.FlexMessage;

public interface FlexMessageReply {

  String toJson();

  default FlexMessage getFlexMessage() {
    ObjectMapper objectMapper =
        new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    try {
      return objectMapper.readValue(toJson(), FlexMessage.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  };
}
