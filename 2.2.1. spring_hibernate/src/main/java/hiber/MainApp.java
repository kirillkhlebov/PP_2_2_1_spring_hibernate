package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        Car car1 = new Car("Volvo", 22);
        Car car2 = new Car("Lada", 44);
        Car car3 = new Car("Cherry", 1);
        Car car4 = new Car("Test", 123);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        List<User> users = userService.listUsers();
        printUsers(users);

        User userByCar = userService.findUsersByCar("Volvo", 22);
        System.out.println(userByCar.toString());

        context.close();
    }

    public static void printUsers(List<User> userList) {
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }
}
