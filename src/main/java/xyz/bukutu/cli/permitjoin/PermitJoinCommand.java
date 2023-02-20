package xyz.bukutu.cli.permitjoin;

import picocli.CommandLine;

@CommandLine.Command(name = "permitJoin",
        description = "Enable, disable or show permit join.",
        subcommands = {
//                PermitJoinShowCommand.class,
                PermitJoinEnableCommand.class,
                PermitJoinDisableCommand.class})
public class PermitJoinCommand implements Runnable {

    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    @Override
    public void run() {
        // No action specified, print usage information
        this.spec.commandLine().usage(System.out);
    }
}
