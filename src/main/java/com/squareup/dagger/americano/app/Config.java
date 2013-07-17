package com.squareup.dagger.americano.app;

import java.net.InetSocketAddress;
import java.net.Proxy;
import javax.inject.Singleton;

/** Application configuration. */
@Singleton
public final class Config {
  public static final String MOCK_BASE_URL = "https://mock.square.com";
  public static final String STAGING_BASE_URL = "https://staging.api.square.com";
  public static final String PRODUCTION_BASE_URL = "https://api.square.com";
  public static final String EMULATOR_BASE_URL = "http://10.0.2.2:8080";

  private final Proxy httpProxy;
  private final String baseUrl;

  private Config(Proxy httpProxy, String baseUrl) {
    this.httpProxy = httpProxy;
    this.baseUrl = baseUrl;
  }

  public Proxy getHttpProxy() {
    return httpProxy;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  @Override public boolean equals(Object other) {
    if (!(other instanceof Config)) {
      return false;
    }

    Config o = (Config) other;
    return o.httpProxy.address().equals(this.httpProxy.address())
        && o.baseUrl.equals(this.baseUrl);
  }

  @Override public int hashCode() {
    return httpProxy.hashCode() + 37 * baseUrl.hashCode();
  }

  public static class Builder {
    public Proxy httpProxy = null;
    public String baseUrl = STAGING_BASE_URL;

    public Builder httpProxy(Proxy httpProxy) {
      this.httpProxy = httpProxy;
      return this;
    }

    public Builder httpProxy(String httpProxy) {
      if (httpProxy.matches("[\\w.]+:\\d{1,4}")) throw new IllegalArgumentException();
      String[] parts = httpProxy.split(":");
      int port = parts.length > 1 ? Integer.parseInt(parts[1]) : 80;
      this.httpProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(parts[0], port));
      return this;
    }

    public Builder baseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
      return this;
    }

    public Config build() {
      return new Config(httpProxy, baseUrl);
    }
  }
}
