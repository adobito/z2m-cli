package xyz.bukutu.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
class ZigbeeGroupMember {

  private int endpoint;

  @JsonProperty("ieee_address")
  private String ieeeAddress;

  // Getters and setters
  public int getEndpoint() {
    return endpoint;
  }

  public void setEndpoint(int endpoint) {
    this.endpoint = endpoint;
  }

  public String getIeeeAddress() {
    return ieeeAddress;
  }

  public void setIeeeAddress(String ieeeAddress) {
    this.ieeeAddress = ieeeAddress;
  }
}
