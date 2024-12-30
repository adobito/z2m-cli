package xyz.bukutu.cli.devices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.paho.client.mqttv3.MqttException;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;
import xyz.bukutu.dto.ZigbeeDevice;
import xyz.bukutu.mqtt.Zigbee2MqttClient;

@Command(name = "show", description = "Show all devices friendly names")
public class DevicesShowCommand implements Runnable {
  private static final String DEVICES_TOPIC = "zigbee2mqtt/bridge/devices";

  private static final long REQUEST_TIMEOUT_SECONDS = 5l;

  @Spec CommandSpec spec;

  @Override
  public void run() {
    try {
      var response =
          Zigbee2MqttClient.getInstance().readTopic(DEVICES_TOPIC, REQUEST_TIMEOUT_SECONDS);
      var mapper = new ObjectMapper();
      var devices = mapper.readValue(response, new TypeReference<List<ZigbeeDevice>>() {});
      spec.commandLine()
          .getOut()
          .println(
              String.join(
                  "\n",
                  devices.stream()
                      .map(ZigbeeDevice::getFriendlyName)
                      .filter(name -> !name.equals("Coordinator"))
                      .collect(Collectors.toList())));
    } catch (MqttException e) {
      spec.commandLine().getOut().println("Error: " + e.getCause().getMessage());
    } catch (JsonProcessingException e) {
      spec.commandLine().getOut().println("Error: " + e.getMessage());
    }
  }
}
