package xyz.bukutu.cli;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "group", subcommands = {GroupAddCommand.class, GroupRemoveCommand.class})
public class GroupCommand implements Runnable {

    @Parameters(index = "0", description = "The group ID", arity = "1")
    private String groupId;

    public String getGroupId() {
        return groupId;
    }

    @Override
    public void run() {
        System.out.println("Listing devices for group " + groupId);
    }

    public static void main(String[] args) {
        CommandLine.run(new GroupCommand(), args);
    }
}
