package xyz.bukutu.cli.permitjoin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttException;
import picocli.CommandLine;
import xyz.bukutu.dto.ZigbeeInfo;
import xyz.bukutu.mqtt.Zigbee2MqttClient;

@CommandLine.Command(name = "show", description = "Show permit join status.")
public class PermitJoinShowCommand implements Runnable {
  private static final String CONFIG_TOPIC = "zigbee2mqtt/bridge/info";

  private static final long REQUEST_TIMEOUT_SECONDS = 5l;

  @CommandLine.Spec CommandLine.Model.CommandSpec spec;

  @Override
  public void run() {
    try {
      var response =
          Zigbee2MqttClient.getInstance().readTopic(CONFIG_TOPIC, REQUEST_TIMEOUT_SECONDS);
      var mapper = new ObjectMapper();
      var config = mapper.readValue(response, ZigbeeInfo.class);
      spec.commandLine().getOut().println(String.format("permit_join: %s", config.isPermitJoin()));
    } catch (MqttException e) {
      spec.commandLine().getOut().println("Error: " + e.getCause().getMessage());
    } catch (JsonProcessingException e) {
      spec.commandLine().getOut().println("Error: " + e.getMessage());
    }
  }
}
