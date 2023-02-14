package xyz.bukutu;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import xyz.bukutu.records.Device;
import xyz.bukutu.records.Group;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class YamlParser {
    private static final String FILE_LOCATION;
    private static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper(new YAMLFactory());
        ConfigReader configReader = new ConfigReader();
        FILE_LOCATION = configReader.getProperty("yaml.path");
    }

    public static List<String> getGroupNames() {
        try {
            var yaml = MAPPER.readValue(new File(FILE_LOCATION), JsonNode.class);
            var groups = (ObjectNode) yaml.get("groups");
            var groupNames = new ArrayList<String>();
            for (JsonNode groupNode : groups) {
                var group = MAPPER.treeToValue(groupNode, Group.class);
                groupNames.add(group.getFriendlyName());
            }
            return groupNames;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getDeviceFriendlyNames() {
        try {
            var yaml = MAPPER.readValue(new File(FILE_LOCATION), JsonNode.class);
            var devices = (ObjectNode) yaml.get("devices");
            TypeFactory typeFactory = MAPPER.getTypeFactory();
            JavaType keyType = typeFactory.constructType(String.class);
            JavaType valueType = typeFactory.constructType(Device.class);
            JavaType mapType = typeFactory.constructParametricType(Map.class, keyType, valueType);
            Map<String, Device> devicesObject = MAPPER.treeToValue(devices, mapType);
            var friendlyNames = devicesObject.entrySet().stream().map(entry -> entry.getValue().getFriendlyName()).collect(Collectors.toList());
            return friendlyNames;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}