package edu.calpoly.csc365.example1.service;

import edu.calpoly.csc365.example1.dao.UserDao;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class AuthenticationService {
  UserDao userDao = null;

  public AuthenticationService(UserDao userDao) {
    this.userDao = userDao;
  }

  public Boolean authenticate(String name, String pass) {
    Boolean authenticated = false;
    authenticated = userDao.authenticate(name, pass);
    return authenticated;
  }

  public static Cookie getLoginCookie(HttpServletRequest request) {
    Cookie loginCookie = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("user")) {
          loginCookie = cookie;
          break;
        }
      }
    }
    return loginCookie;
  }

  public static Cookie createLoginCookie(String name) {
    // create cookie
    Cookie loginCookie = new Cookie("user", name);
    //setting cookie to expiry in 30 mins
    loginCookie.setMaxAge(5*60);
    return loginCookie;
  }
}
