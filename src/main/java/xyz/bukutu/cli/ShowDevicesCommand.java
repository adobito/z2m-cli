package xyz.bukutu.cli;

import picocli.CommandLine;
import xyz.bukutu.YamlParser;

@CommandLine.Command(name = "show", description = "Show device information")
public class ShowDevicesCommand implements Runnable {

        @Override
        public void run() {
            System.out.println(String.join("\n",YamlParser.getDeviceFriendlyNames()));
        }
    }