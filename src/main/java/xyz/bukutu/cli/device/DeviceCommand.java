package xyz.bukutu.cli.device;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
    name = "device",
    description = "Device related operations",
    subcommands = {DeviceRenameCommand.class})
public class DeviceCommand implements Runnable {

  @Parameters(index = "0", description = "Friendly name of device")
  private String friendlyName;

  @CommandLine.Spec CommandLine.Model.CommandSpec spec;

  public String getFriendlyName() {
    return this.friendlyName;
  }

  @Override
  public void run() {
    // No action specified, print usage information
    this.spec.commandLine().usage(System.out);
  }
}
