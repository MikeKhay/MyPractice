package service;

import model.User;

import java.util.List;

public interface UserService {
    List<User> readAll();

    User read(int id);

    void create(User user);

    void delete(int id);

    User readByEmail(String email);
}
