package xyz.bukutu.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;
import xyz.bukutu.mqtt.Zigbee2MqttClient;

@Command(name = "remove", description = "Remove a device by its friendly name or IEEE address")
public class DevicesRemoveCommand implements Runnable {
  private static final String REMOVE_TOPIC = "zigbee2mqtt/bridge/request/device/remove";
  private static final String RESPONSE_TOPIC = "zigbee2mqtt/bridge/response/device/remove";
  private static final long REQUEST_TIMEOUT_SECONDS = 15L;

  @CommandLine.Option(
      names = {"-f", "--force"},
      description = "The IEEE address of the device to remove.",
      arity = "0..1")
  private boolean force;

  @Spec CommandSpec spec;

  @CommandLine.Parameters(index = "0", description = "The device ID")
  private String deviceId;

  @Override
  public void run() {
    try {
      String payload = generatePayloadString(deviceId, force);
      var response =
          Zigbee2MqttClient.getInstance()
              .publishWithResponse(REMOVE_TOPIC, payload, RESPONSE_TOPIC, REQUEST_TIMEOUT_SECONDS);

      spec.commandLine().getOut().println("Response from Zigbee2MQTT: " + response);
    } catch (Exception e) {
      spec.commandLine().getErr().println("Error removing device: " + e.getCause().getMessage());
    }
  }

  private String generatePayloadString(String id, boolean force) {
    return String.format("{\"id\": \"%s\", \"force\": %s}", id, force);
  }
}
