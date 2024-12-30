package xyz.bukutu.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZigbeeDevice {
  @JsonProperty("date_code")
  private String dateCode;

  @JsonProperty("disabled")
  private boolean disabled;

  @JsonProperty("friendly_name")
  private String friendlyName;

  @JsonProperty("ieee_address")
  private String ieeeAddress;

  @JsonProperty("interview_completed")
  private boolean interviewCompleted;

  @JsonProperty("interviewing")
  private boolean interviewing;

  @JsonProperty("manufacturer")
  private String manufacturer;

  @JsonProperty("model_id")
  private String modelId;

  @JsonProperty("network_address")
  private int networkAddress;

  @JsonProperty("power_source")
  private String powerSource;

  @JsonProperty("software_build_id")
  private String softwareBuildId;

  @JsonProperty("supported")
  private boolean supported;

  @JsonProperty("type")
  private String type;

  // Getters and setters
  public String getDateCode() {
    return dateCode;
  }

  public void setDateCode(String dateCode) {
    this.dateCode = dateCode;
  }

  public boolean isDisabled() {
    return disabled;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  public String getFriendlyName() {
    return friendlyName;
  }

  public void setFriendlyName(String friendlyName) {
    this.friendlyName = friendlyName;
  }

  public String getIeeeAddress() {
    return ieeeAddress;
  }

  public void setIeeeAddress(String ieeeAddress) {
    this.ieeeAddress = ieeeAddress;
  }

  public boolean isInterviewCompleted() {
    return interviewCompleted;
  }

  public void setInterviewCompleted(boolean interviewCompleted) {
    this.interviewCompleted = interviewCompleted;
  }

  public boolean isInterviewing() {
    return interviewing;
  }

  public void setInterviewing(boolean interviewing) {
    this.interviewing = interviewing;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getModelId() {
    return modelId;
  }

  public void setModelId(String modelId) {
    this.modelId = modelId;
  }

  public int getNetworkAddress() {
    return networkAddress;
  }

  public void setNetworkAddress(int networkAddress) {
    this.networkAddress = networkAddress;
  }

  public String getPowerSource() {
    return powerSource;
  }

  public void setPowerSource(String powerSource) {
    this.powerSource = powerSource;
  }

  public String getSoftwareBuildId() {
    return softwareBuildId;
  }

  public void setSoftwareBuildId(String softwareBuildId) {
    this.softwareBuildId = softwareBuildId;
  }

  public boolean isSupported() {
    return supported;
  }

  public void setSupported(boolean supported) {
    this.supported = supported;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "ZigbeeDevice{"
        + "dateCode='"
        + dateCode
        + '\''
        + ", disabled="
        + disabled
        + ", friendlyName='"
        + friendlyName
        + '\''
        + ", ieeeAddress='"
        + ieeeAddress
        + '\''
        + ", interviewCompleted="
        + interviewCompleted
        + ", interviewing="
        + interviewing
        + ", manufacturer='"
        + manufacturer
        + '\''
        + ", modelId='"
        + modelId
        + '\''
        + ", networkAddress="
        + networkAddress
        + ", powerSource='"
        + powerSource
        + '\''
        + ", softwareBuildId='"
        + softwareBuildId
        + '\''
        + ", supported="
        + supported
        + ", type='"
        + type
        + '\''
        + '}';
  }
}
