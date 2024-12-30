package xyz.bukutu.cli.devices;

import picocli.CommandLine.Command;

@Command(
    name = "devices",
    subcommands = {DevicesShowCommand.class, DevicesRemoveCommand.class})
public class DevicesCommand {}
