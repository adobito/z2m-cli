package xyz.bukutu.mqtt;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
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
      client = new MqttClient(mqttConfig.getBrokerUrl(), clientId, persistence);
      this.options = new MqttConnectOptions();
      this.options.setUserName(mqttConfig.getUser());
      this.options.setPassword(mqttConfig.getPassword().toCharArray());
      client.connect(options);
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

  public String readTopic(String topic, long timeoutInSeconds) throws MqttException {
    CountDownLatch messageReceivedLatch = new CountDownLatch(1);
    final StringBuilder response = new StringBuilder();

    // Subscribe to the response topic
    client.subscribe(
        topic,
        new IMqttMessageListener() {
          @Override
          public void messageArrived(String topic, MqttMessage message) throws Exception {
            if (topic.equals(topic)) {
              response.append(new String(message.getPayload()));
              messageReceivedLatch.countDown(); // Signal that the message has been received
            }
          }
        });

    try {
      // Wait for the message or timeout
      boolean messageReceived = messageReceivedLatch.await(timeoutInSeconds, TimeUnit.SECONDS);
      if (!messageReceived) {
        throw new MqttException(new Throwable("Timed out waiting for response on topic " + topic));
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // Restore the interrupted status
      throw new MqttException(e);
    } finally {
      // Unsubscribe from the response topic to avoid receiving unnecessary messages
      client.unsubscribe(topic);
    }

    // Return the response
    return response.toString();
  }

  public String publishWithResponse(
      String publishTopic, String publishMessage, String responseTopic, long timeoutInSeconds)
      throws MqttException {
    publish(publishTopic, publishMessage);
    return readTopic(responseTopic, timeoutInSeconds);
  }
}
