package com.squareup.dagger.americano.service;

import com.squareup.dagger.americano.app.Session;

public interface LoginService {
  Session login(String emailAddress, String password);
}
