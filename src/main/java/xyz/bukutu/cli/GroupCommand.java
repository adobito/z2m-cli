package xyz.bukutu.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(
    name = "group",
    description = "",
    subcommands = {GroupAddCommand.class, GroupRemoveCommand.class})
public class GroupCommand implements Runnable {

  @Parameters(index = "0", description = "The group ID", arity = "1")
  private String groupId;

  @Spec CommandSpec spec;

  public String getGroupId() {
    return groupId;
  }

  @Override
  public void run() {
    spec.commandLine().getOut().println("Listing devices for group " + groupId);
  }
}
