package xyz.bukutu.cli;

import picocli.CommandLine;

@CommandLine.Command(name = "groups", subcommands = { ShowGroupsCommand.class, AddGroupCommand.class })
public class GroupsCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Groups command executed");
    }
}