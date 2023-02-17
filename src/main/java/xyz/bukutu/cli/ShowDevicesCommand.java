package xyz.bukutu.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;
import xyz.bukutu.YamlParser;

@Command(name = "show", description = "Show all devices friendly names")
public class ShowDevicesCommand implements Runnable {

    @Spec
    CommandSpec spec;
        @Override
        public void run() {
            spec.commandLine().getOut().println(String.join("\n",YamlParser.getDeviceFriendlyNames()));
        }
    }