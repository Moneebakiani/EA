package edu.miu.cs.cs544.server;

import edu.miu.cs.cs544.server.domain.User;
import edu.miu.cs.cs544.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




@SpringBootApplication
public class Server {

//    @Autowired
//    UserRepository userRepository;
//
//    @PostConstruct
//    public  void initUser(){
//        List<User> users= Stream.of(
//               new User(101,"user1","pass","user1@gmail.com"),
//               new User(102,"user2","pass","user2@gmail.com"),
//               new User(103,"user3","pass","user3@gmail.com")
//
//        ).collect(Collectors.toList());
//
//        userRepository.saveAll(users);
//    }
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
