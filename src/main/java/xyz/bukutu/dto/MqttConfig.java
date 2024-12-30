package xyz.bukutu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class MqttConfig {

  @JsonProperty private String user;
  @JsonProperty private String password;

  @JsonProperty("server")
  private String brokerUrl;

  @JsonProperty("base_topic")
  private String baseTopic;

  public MqttConfig() {}

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getBrokerUrl() {
    return brokerUrl;
  }

  public void setBrokerUrl(String brokerUrl) {
    this.brokerUrl = brokerUrl;
  }

  public String getBaseTopic() {
    return baseTopic;
  }

  public void setBaseTopic(String baseTopic) {
    this.baseTopic = baseTopic;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MqttConfig that = (MqttConfig) o;
    return Objects.equals(user, that.user)
        && Objects.equals(password, that.password)
        && Objects.equals(brokerUrl, that.brokerUrl)
        && Objects.equals(baseTopic, that.baseTopic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, password, brokerUrl, baseTopic);
  }

  @Override
  public String toString() {
    return "MqttConfig{"
        + "user='"
        + user
        + '\''
        + ", password='"
        + password
        + '\''
        + ", brokerUrl='"
        + brokerUrl
        + '\''
        + ", baseTopic='"
        + baseTopic
        + '\''
        + '}';
  }
}
