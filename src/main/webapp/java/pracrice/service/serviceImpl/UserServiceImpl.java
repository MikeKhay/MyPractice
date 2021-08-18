package pracrice.service.serviceImpl;

import pracrice.myException.DuplicateUserException;
import pracrice.dao.User;
import pracrice.jdbc.ConnectJDBC;
import pracrice.service.UserService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectJDBC.getConnection();
                Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id, first_name, last_name, age FROM user")){
            while (resultSet.next()){
                users.add(new User(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age")));
            }
            return users;
        }
    }

    @Override
    public User getUserById(int id) throws SQLException, ClassNotFoundException {
        User user = null;

        ResultSet resultSet = null;
        try (Connection connection = ConnectJDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE id = ?")
        ){
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new  User(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"));
            }
        }
        resultSet.close();

        return user;
    }

    @Override
    public void createUser(User user) throws SQLException, ClassNotFoundException, DuplicateUserException {
            try(Connection connection = ConnectJDBC.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (id, first_name, last_name, age) VALUES(?,?,?,?)"))
            {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setString(2, user.getFirstName());
                preparedStatement.setString(3, user.getLastName());
                preparedStatement.setInt(4, user.getAge());
                preparedStatement.executeUpdate();
            }
    }

    @Override
    public void updateUser(int id, String firstName, String lastName, int age) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectJDBC.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Update user SET first_name = ?, last_name = ?, age = ? WHERE id = ?"))
        {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();

        }
    }

    @Override
    public void deleteUser(int id) throws SQLException, ClassNotFoundException {
        if (isExist(id)){
            try (Connection connection = ConnectJDBC.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id = ?"))
            {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public void printAllUsers(List<User> list) {
        for (User u : list){
            System.out.println(u.toString());
        }
    }

    private boolean isExist(int userId) throws SQLException, ClassNotFoundException {
        boolean flag = false;
        for (User u : getAll()){
            if (u.getId() == userId){
                flag = true;
            }
        }
        return flag;
    }
}
