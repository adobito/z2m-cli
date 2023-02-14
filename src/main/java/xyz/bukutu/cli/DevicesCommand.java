package xyz.bukutu.cli;

import picocli.CommandLine.Command;

@Command(name = "devices", subcommands = {ShowDevicesCommand.class})
public class DevicesCommand implements Runnable {

    @Override
    public void run() {
    }
}
