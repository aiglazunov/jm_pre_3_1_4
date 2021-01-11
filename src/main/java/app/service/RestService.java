package app.service;

import app.model.User;

public interface RestService {
    String getAllUsers();

    String addUser(User user, String session);

    String changeUser(User user, String session);

    String deleteUser(User user, String session);
}
