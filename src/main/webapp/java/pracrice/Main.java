package pracrice;

import pracrice.dao.User;
import pracrice.jdbc.ConnectJDBC;
import pracrice.myException.DuplicateUserException;
import pracrice.service.serviceImpl.UserServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, DuplicateUserException {

        UserServiceImpl userService = new UserServiceImpl();

//        userService.deleteUser(10);

//        userService.updateUser(12, "HabUpdate", "NomyrUpdate", 19);

//        User userById = userService.getUserById(12);
//        System.out.println(userById.toString());

//        User user = new User(12, "Habib", "Nomyr", 39);
//        userService.createUser(user);

        List<User> all = userService.getAll();
        userService.printAllUsers(all);
    }
}
