package xyz.bukutu.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;
import xyz.bukutu.mqtt.Zigbee2MqttClient;

@Command(name = "add")
public class AddGroupCommand implements Runnable {
    private static final String TOPIC_NAME = "zigbee2mqtt/bridge/request/group/add";
    private static final String PAYLOAD_TEMPLATE = "%s";
    @ParentCommand
    private GroupsCommand parent;


    @Parameters(index = "0", description = "The group name")
    private String groupName;

    @Override
    public void run() {
        try {
            System.out.println("Add group command executed with group name " + groupName);
            Zigbee2MqttClient.getInstance().publish(TOPIC_NAME, generatePayloadString(groupName));
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String generatePayloadString(String groupName) {
        return String.format(PAYLOAD_TEMPLATE, groupName);
    }
}
