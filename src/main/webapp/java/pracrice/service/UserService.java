package pracrice.service;

import pracrice.dao.User;
import pracrice.myException.DuplicateUserException;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> getAll() throws SQLException, ClassNotFoundException;

    User getUserById(int id) throws SQLException, ClassNotFoundException;

    void createUser(User user) throws SQLException, ClassNotFoundException, DuplicateUserException;

    void updateUser(int id, String firstName,String lastName, int age) throws SQLException, ClassNotFoundException;

    void deleteUser(int id) throws SQLException, ClassNotFoundException;

    void printAllUsers(List<User> list);
}
