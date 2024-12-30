package xyz.bukutu;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;

import xyz.bukutu.dto.MqttConfig;

public class YamlParser {
  private static final String FILE_LOCATION;
  private static final ObjectMapper MAPPER;

  static {
    MAPPER = new ObjectMapper(new YAMLFactory());
    String configPath = System.getProperty(Constants.CONFIG_PATH_PROPERTY_LABEL);
    if (configPath == null) {
      configPath = Constants.DEFAULT_CONFIG_PATH;
    }
    FILE_LOCATION = configPath;
  }

  public static MqttConfig getMqttConfig() {
    try {
      var yaml = MAPPER.readValue(new File(FILE_LOCATION), JsonNode.class);
      var mqtt = (ObjectNode) yaml.get("mqtt");
      MqttConfig mqttConfig = MAPPER.treeToValue(mqtt, MqttConfig.class);
      if (mqttConfig.getBrokerUrl().startsWith("mqtt")) {
        mqttConfig.setBrokerUrl(mqttConfig.getBrokerUrl().replaceFirst("mqtt", "tcp"));
      }
      if (!mqttConfig.getBrokerUrl().contains(":")) {
        mqttConfig.setBrokerUrl(mqttConfig.getBrokerUrl() + ":1883");
      }
      return mqttConfig;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
