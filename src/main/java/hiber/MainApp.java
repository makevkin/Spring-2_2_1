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

      userService.add(new User("Сергей", "Иванов", "ivanov@mail.ru", new Car("KiaSportage", 123)));
      userService.add(new User("Игорь", "Петров", "petrov@mail.ru", new Car("Audi", 231)));
      userService.add(new User("Иван", "Сидоров", "sidorov@mail.ru", new Car("BMW", 555)));
      userService.add(new User("Алексей", "Федоров", "fedorov@mail.ru", new Car("Niva", 444)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println(("Car = "+ user.getUserCar()));
         System.out.println();
      }

      User user = userService.getUser("Audi", 231);
      System.out.println(user);
      context.close();
   }
}
