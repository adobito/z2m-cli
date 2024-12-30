package xyz.bukutu.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZigbeeInfo {

  private String commit;
  private String logLevel;

  @JsonProperty("permit_join")
  private boolean permitJoin;

  private String version;

  // Getters and setters
  public String getCommit() {
    return commit;
  }

  public void setCommit(String commit) {
    this.commit = commit;
  }

  public String getLogLevel() {
    return logLevel;
  }

  public void setLogLevel(String logLevel) {
    this.logLevel = logLevel;
  }

  public boolean isPermitJoin() {
    return permitJoin;
  }

  public void setPermitJoin(boolean permitJoin) {
    this.permitJoin = permitJoin;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}
