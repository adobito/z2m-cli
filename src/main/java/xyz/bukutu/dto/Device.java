package xyz.bukutu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Device {
  @JsonProperty("friendly_name")
  private String friendlyName;

  public Device() {}

  public String getFriendlyName() {
    return friendlyName;
  }

  public void setFriendlyName(String friendlyName) {
    this.friendlyName = friendlyName;
  }

  @Override
  public String toString() {
    return "Device{" + "friendlyName='" + friendlyName + '\'' + '}';
  }
}
