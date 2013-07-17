package com.squareup.dagger.americano.app;

public final class Session {
  public final String emailAddress;
  public final String secret;

  public Session(String emailAddress, String secret) {
    this.emailAddress = emailAddress;
    this.secret = secret;
  }
}
