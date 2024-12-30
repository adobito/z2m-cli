package xyz.bukutu.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;

public class Group {
  @JsonProperty("friendly_name")
  private String friendlyName;

  private String[] devices;

  public Group() {}

  public Group(String friendlyName, String[] devices) {
    this.friendlyName = friendlyName;
    this.devices = devices;
  }

  public String getFriendlyName() {
    return friendlyName;
  }

  public void setFriendlyName(String friendlyName) {
    this.friendlyName = friendlyName;
  }

  public String[] getDevices() {
    return devices;
  }

  public void setDevices(String[] devices) {
    this.devices = devices;
  }

  @Override
  public String toString() {
    return "Group{"
        + "friendlyName='"
        + friendlyName
        + '\''
        + ", devices="
        + Arrays.toString(devices)
        + '}';
  }
}
