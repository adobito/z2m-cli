package xyz.bukutu.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import xyz.bukutu.cli.permitjoin.PermitJoinCommand;
import xyz.bukutu.mqtt.Zigbee2MqttClient;

@Command(name = "z2m-cli", version = "1.0",
        description = "Command line for Zigbee2MQTT actions")
public class App {

    @Spec
    CommandSpec spec;
    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(new App());
        cmd.addSubcommand("group", new GroupCommand());
        cmd.addSubcommand("groups", new GroupsCommand());
        cmd.addSubcommand("devices", new DevicesCommand());
        cmd.addSubcommand("permitJoin", new PermitJoinCommand());
        cmd.addSubcommand("help", new CommandLine.HelpCommand());

        if (args.length == 0) {
            cmd.execute("help");
            return;
        }

        cmd.parseWithHandler(new CommandLine.RunLast(), System.out, args);

        Zigbee2MqttClient.getInstance().disconnect();
    }

}