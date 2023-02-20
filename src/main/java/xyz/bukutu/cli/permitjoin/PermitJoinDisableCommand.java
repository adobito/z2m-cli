package xyz.bukutu.cli.permitjoin;

import org.eclipse.paho.client.mqttv3.MqttException;
import picocli.CommandLine;
import xyz.bukutu.mqtt.Zigbee2MqttClient;

@CommandLine.Command(name = "disable",
        description = "Disable permit join.")
public class PermitJoinDisableCommand implements Runnable {

    private static final String TOPIC_NAME = "zigbee2mqtt/bridge/config/permit_join";

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    @Override
    public void run() {
        try {
            final var mqtt = Zigbee2MqttClient.getInstance();
            mqtt.publish(TOPIC_NAME, false + "");
            this.spec.commandLine().getOut().println("Permit Join Disabled");
        } catch (final MqttException e) {
            throw new RuntimeException(e);
        }
    }
}

