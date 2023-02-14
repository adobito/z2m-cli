package xyz.bukutu.cli;

import picocli.CommandLine;
import xyz.bukutu.mqtt.Zigbee2MqttClient;

@CommandLine.Command()
public class App {

    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(new App());

        if (args.length == 0) {
            cmd.usage(System.out);
            return;
        }
        cmd.addSubcommand("group", new GroupCommand());
        cmd.addSubcommand("groups", new GroupsCommand());
        cmd.addSubcommand("devices", new DevicesCommand());
        cmd.execute(args);

        Zigbee2MqttClient.getInstance().disconnect();
    }
}