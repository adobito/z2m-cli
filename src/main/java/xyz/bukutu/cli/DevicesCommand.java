package xyz.bukutu.cli;

import picocli.CommandLine.Command;

@Command(
    name = "devices",
    subcommands = {DevicesShowCommand.class, DevicesRemoveCommand.class})
public class DevicesCommand {}
