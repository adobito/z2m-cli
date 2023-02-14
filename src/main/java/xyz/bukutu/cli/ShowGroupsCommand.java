package xyz.bukutu.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;
import xyz.bukutu.YamlParser;

@Command(name = "show")
public class ShowGroupsCommand implements Runnable {
    @ParentCommand
    private GroupsCommand parent;

    @Override
    public void run() {
        System.out.println(String.join("\n", YamlParser.getGroupNames()));
    }
}