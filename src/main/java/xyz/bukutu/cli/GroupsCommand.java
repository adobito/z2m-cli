package xyz.bukutu.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

@Command(name = "groups", subcommands = { ShowGroupsCommand.class, AddGroupCommand.class })
public class GroupsCommand implements Runnable {
    @Spec
    CommandSpec spec;
    @Override
    public void run() {
        spec.commandLine().getOut().println("Groups command executed");
    }
}