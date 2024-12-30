package xyz.bukutu.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

@Command(
    name = "groups",
    subcommands = {GroupsShowCommand.class, AddGroupCommand.class})
public class GroupsCommand implements Runnable {
  @Spec CommandSpec spec;

  @Override
  public void run() {
    spec.commandLine().getOut().println("Groups command executed");
  }
}
