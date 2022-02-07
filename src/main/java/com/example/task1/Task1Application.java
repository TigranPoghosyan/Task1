package com.example.task1;

import com.example.task1.entity.Ticket;
import com.example.task1.entity.User;
import com.example.task1.repository.TicketRepository;
import com.example.task1.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Task1Application {

    public static void main(String[] args) {
        SpringApplication.run(Task1Application.class, args);
    }

    @Bean
    public CommandLineRunner mappingDemo(TicketRepository ticketRepository,
                                         UserRepository userRepository) {
        return args -> {

            // create a new user
            User user1 = new User(1, "Narine", "Gabrielyan", "developer", "senior","IT Department");
            User user2 = new User(2, "Tigran", "Stepanyan", "tester", "middle","IT Department");
            User user3 = new User(3, "Albert", "Danielyan", "hr", "junior","HR Department");
            User user4 = new User(4, "Artak", "Grigoryan", "finance manager", "senior","Finance & Accounting Department");

            // save the user
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            // create and save new tickets
            ticketRepository.save(new Ticket(1, "Fix Bug", "Immediately", false, user1));
            ticketRepository.save(new Ticket(2, "Find Bug", "Not urgent", false, user2));
            ticketRepository.save(new Ticket(3, "Find Senior Developer", "Solve within 5 days", false, user3));
            ticketRepository.save(new Ticket(4, "Financial problems", "Solve within 2 days", false, user4));
        };
    }
}
