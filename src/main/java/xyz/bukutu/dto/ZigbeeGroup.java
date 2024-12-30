package xyz.bukutu.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZigbeeGroup {

  @JsonProperty("friendly_name")
  private String friendlyName;

  private int id;

  private List<ZigbeeGroupMember> members;

  private List<Object> scenes; // Assuming scenes will be an empty list or objects

  // Getters and setters
  public String getFriendlyName() {
    return friendlyName;
  }

  public void setFriendlyName(String friendlyName) {
    this.friendlyName = friendlyName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<ZigbeeGroupMember> getMembers() {
    return members;
  }

  public void setMembers(List<ZigbeeGroupMember> members) {
    this.members = members;
  }

  public List<Object> getScenes() {
    return scenes;
  }

  public void setScenes(List<Object> scenes) {
    this.scenes = scenes;
  }
}
