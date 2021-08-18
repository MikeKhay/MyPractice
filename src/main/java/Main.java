import model.User;
import service.impl.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("khajmike@gmail.com", "1111", "Mike", "Khai", "Admin");
        User user2 = new User("khajrylya@gmail.com", "1112", "Rylya", "Khai", "User");


        UserServiceImpl userService = new UserServiceImpl();

//        userService.create(user2);

//        User userId = userService.read(3);
//        System.out.println(userId.toString());

//        userService.delete(2);

        User userEmail = userService.readByEmail("khajmike@gmail.com");
        System.out.println(userEmail.toString());

//        List<User> users = userService.readAll();
//        for (User u : users){
//            System.out.println(u.toString());
//        }

    }
}
