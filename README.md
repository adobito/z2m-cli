# z2m-cli

Small CLI tool for some basic zigbee2mqtt interaractions.

## Commands

`groups show`

Show all groups friendly names from config file

`groups add <groupName>`

Add a new group to zigbee2mqtt

`devices show`

Show all devices friendly names from config file

`group <groupName> add <deviceName>`

Add a device to a specified group

`group <groupName< remove <deviceName>`

Remove a device from a specified group

`permitJoin enable`

Enables permit_join

`permitJoin disable`

Disables permit_join

`device <deviceName> rename <newDeviceName>`

Renames a device to a new friendly name

## Usage

You can run the shadowJar from Gradle to generate a JAR file. This jar file can then be run with the following command:

`java -jar /path/to/jar/file.jar <args>`

The default path for the Zigbee2Mqtt `configuration.yaml` is `/opt/zigbee2mqtt/data/configuration.yaml`. Alternatively,
you can specify the location to your zigbee2mqtt config file using by passing the jar the `z2m.configPath` system
property.

`java -jar -Dz2m.configPath=/path/to/z2m/config /path/to/jar/file.jar <args>`

You can also add the `z2m-cli.sh` from the base folder script to your PATH and use it to call it like any traditional
CLI like this:

`z2m-cli groups show`

## Requirements

Java 11