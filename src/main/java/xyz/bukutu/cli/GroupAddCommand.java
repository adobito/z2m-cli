package xyz.bukutu.cli;

import org.eclipse.paho.client.mqttv3.MqttException;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import xyz.bukutu.mqtt.Zigbee2MqttClient;

@Command(name = "add", description = "Add a device to a group")
public class GroupAddCommand implements Runnable {

    private static final String TOPIC_NAME = "zigbee2mqtt/bridge/request/group/members/add";
    private static final String PAYLOAD_TEMPLATE = "{\"group\": \"<group>\", \"device\": \"<device>\"}";
    @CommandLine.ParentCommand
    private GroupCommand groupCommand;
    @Parameters(index = "0  ", description = "The device ID", arity = "1")
    private String deviceId;

    @Override
    public void run() {
        try {
            System.out.printf("Adding device %s to group%n", deviceId, groupCommand.getGroupId());
            Zigbee2MqttClient.getInstance().publish(TOPIC_NAME, generatePayloadTemplate(this.groupCommand.getGroupId(), this.deviceId));
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    private static String generatePayloadTemplate(String groupName, String deviceId) {
        return PAYLOAD_TEMPLATE.replace("<group>", groupName).replace("<device>", deviceId);
    }
}