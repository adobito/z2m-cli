package xyz.bukutu.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import xyz.bukutu.Constants;
import xyz.bukutu.YamlParser;
import xyz.bukutu.dto.MqttConfig;

public class Zigbee2MqttClient {
    private static Zigbee2MqttClient instance = null;

    private MqttClient client;
    private MqttConnectOptions options;

    private Zigbee2MqttClient() {
        String clientId = System.getProperty(Constants.CLIENT_ID_PROPERTY_LABEL);
        if (clientId == null) {
            clientId = Constants.DEFAULT_CLIENT_ID;
        }
        try {
            MemoryPersistence persistence = new MemoryPersistence();
            MqttConfig mqttConfig = YamlParser.getMqttConfig();
            client = new MqttClient(mqttConfig.getBrokerUrl(),
                    clientId, persistence);
            this.options = new MqttConnectOptions();
            this.options.setUserName(mqttConfig.getUser());
            this.options.setPassword(mqttConfig.getPassword().toCharArray());
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized Zigbee2MqttClient getInstance() {
        if (instance == null) {
            instance = new Zigbee2MqttClient();
        }
        return instance;
    }

    public static void connect() {
        try {
            getInstance().client.connect(getInstance().options);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }
    public static void disconnect() {
        try {
            getInstance().client.disconnect();
        } catch (Exception e) {
            new RuntimeException(e);
        }
    }

    public void publish(String topic, String message) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        client.publish(topic, mqttMessage);
    }
}
