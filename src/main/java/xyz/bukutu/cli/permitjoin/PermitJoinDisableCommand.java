package xyz.bukutu.cli.permitjoin;

import org.eclipse.paho.client.mqttv3.MqttException;
import picocli.CommandLine;
import xyz.bukutu.mqtt.Zigbee2MqttClient;

@CommandLine.Command(name = "disable", description = "Disable permit join.")
public class PermitJoinDisableCommand implements Runnable {

  private static final String REQUEST_TOPIC = "zigbee2mqtt/bridge/request/permit_join";

  private static final String RESPONSE_TOPIC = "zigbee2mqtt/bridge/response/permit_join";

  private static final long REQUEST_TIMEOUT_SECONDS = 5l;
  @CommandLine.Spec private CommandLine.Model.CommandSpec spec;

  @Override
  public void run() {
    try {
      final var mqtt = Zigbee2MqttClient.getInstance();
      var response =
          mqtt.publishWithResponse(
              REQUEST_TOPIC, false + "", RESPONSE_TOPIC, REQUEST_TIMEOUT_SECONDS);
      this.spec.commandLine().getOut().println(response);
    } catch (MqttException e) {
      spec.commandLine().getOut().println("Error: " + e.getCause().getMessage());
    }
  }
}
