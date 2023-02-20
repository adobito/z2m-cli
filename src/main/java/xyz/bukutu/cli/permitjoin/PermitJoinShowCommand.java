package xyz.bukutu.cli.permitjoin;

import picocli.CommandLine;
import xyz.bukutu.YamlParser;

@CommandLine.Command(name = "show",
        description = "Show permit join status.")
public class PermitJoinShowCommand implements Runnable {
    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    @Override
    public void run() {
        this.spec.commandLine().getOut().println("permit_join: " + YamlParser.isPermitJoinEnabled());
    }
}
