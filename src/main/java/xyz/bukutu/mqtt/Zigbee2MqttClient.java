package xyz.bukutu.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import xyz.bukutu.ConfigReader;

public class Zigbee2MqttClient {
    private static Zigbee2MqttClient instance = null;

    private final ConfigReader configReader;

    private MqttClient client;

    private Zigbee2MqttClient() {
        this.configReader = new ConfigReader();
        try {
            MemoryPersistence persistence = new MemoryPersistence();
            client = new MqttClient(configReader.getProperty("mqtt.brokerUrl"),
                    configReader.getProperty("mqtt.clientId"), persistence);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(configReader.getProperty("mqtt.username"));
            options.setPassword(configReader.getProperty("mqtt.password").toCharArray());
            client.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Zigbee2MqttClient getInstance() {
        if (instance == null) {
            instance = new Zigbee2MqttClient();
        }
        return instance;
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
