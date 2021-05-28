package ctr.bot.globalresults.dto;

import java.util.Map;

public class Tunnel {

  public String name;
  public String uri;
  public String public_url;
  public String proto;
  public Map<String, String> config;
  public Map<String, Map<String, String>> metrics;

  public Tunnel() {}
}
