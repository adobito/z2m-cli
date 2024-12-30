package xyz.bukutu.cli.device;

import org.eclipse.paho.client.mqttv3.MqttException;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import xyz.bukutu.mqtt.Zigbee2MqttClient;

@Command(name = "rename", description = "Rename a device")
public class DeviceRenameCommand implements Runnable {

  private static final String TOPIC_NAME = "zigbee2mqtt/bridge/request/device/rename";
  private static final String PAYLOAD_TEMPLATE = "{\"from\": \"%s\", \"to\": \"%s\"} ";

  @Parameters(index = "0", description = "New friendly name of device")
  private String newFriendlyName;

  @CommandLine.ParentCommand private DeviceCommand deviceCommand;
  @CommandLine.Spec CommandLine.Model.CommandSpec spec;

  @Override
  public void run() {
    try {
      // TODO: Validate if deviceID exists
      final var mqtt = Zigbee2MqttClient.getInstance();
      mqtt.publish(
          TOPIC_NAME,
          String.format(
              PAYLOAD_TEMPLATE, this.deviceCommand.getFriendlyName(), this.newFriendlyName));
      this.spec.commandLine().getOut().println("Renamed Successfully");
    } catch (final MqttException e) {
      throw new RuntimeException(e);
    }
  }
}
