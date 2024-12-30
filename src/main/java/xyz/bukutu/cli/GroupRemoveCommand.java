package xyz.bukutu.cli;

import org.eclipse.paho.client.mqttv3.MqttException;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;
import picocli.CommandLine.Spec;
import xyz.bukutu.mqtt.Zigbee2MqttClient;

@Command(name = "remove", description = "Remove a device from a group")
public class GroupRemoveCommand implements Runnable {

  private static final String TOPIC_NAME = "zigbee2mqtt/bridge/request/group/members/remove";
  private static final String PAYLOAD_TEMPLATE =
      "{\"group\": \"<group>\", \"device\": \"<device>\"}";
  @ParentCommand private GroupCommand groupCommand;

  @Spec CommandSpec spec;

  @Parameters(index = "0", description = "The device ID")
  private String deviceId;

  @Override
  public void run() {
    try {
      spec.commandLine()
          .getOut()
          .printf("Removing device %s from group %s%n", deviceId, groupCommand.getGroupId());
      Zigbee2MqttClient.getInstance()
          .publish(
              TOPIC_NAME, generatePayloadTemplate(this.groupCommand.getGroupId(), this.deviceId));
    } catch (MqttException e) {
      throw new RuntimeException(e);
    }
  }

  private static String generatePayloadTemplate(String groupName, String deviceId) {
    return PAYLOAD_TEMPLATE.replace("<group>", groupName).replace("<device>", deviceId);
  }
}
