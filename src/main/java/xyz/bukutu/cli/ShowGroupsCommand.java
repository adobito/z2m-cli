package xyz.bukutu.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.ParentCommand;
import xyz.bukutu.YamlParser;

@Command(name = "show", description = "Show all group names")
public class ShowGroupsCommand implements Runnable {
    @ParentCommand
    private GroupsCommand parent;
    @Spec
    CommandSpec spec;
    @Override
    public void run() {
        spec.commandLine().getOut().println(String.join("\n", YamlParser.getGroupNames()));
    }
}