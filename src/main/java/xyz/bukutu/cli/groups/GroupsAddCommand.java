package xyz.bukutu.cli.groups;

import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;
import picocli.CommandLine.Spec;
import xyz.bukutu.mqtt.Zigbee2MqttClient;

@Command(name = "add")
public class GroupsAddCommand implements Runnable {
  private static final String TOPIC_NAME = "zigbee2mqtt/bridge/request/group/add";
  private static final String PAYLOAD_TEMPLATE = "%s";
  @ParentCommand private GroupsCommand parent;

  @Spec CommandSpec spec;

  @Parameters(index = "0", description = "The group name")
  private String groupName;

  @Override
  public void run() {
    try {
      spec.commandLine()
          .getOut()
          .println("Add group command executed with group name " + groupName);
      Zigbee2MqttClient.getInstance().publish(TOPIC_NAME, generatePayloadString(groupName));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static String generatePayloadString(String groupName) {
    return String.format(PAYLOAD_TEMPLATE, groupName);
  }
}
