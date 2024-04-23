package com.example.mywebapp;

import com.example.mywebapp.user.User;
import com.example.mywebapp.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("test1@test.com");
        user.setPassword("12345");
        user.setFirstName("Test");
        user.setLastName("User");

        User savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
        

    }

    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user : users) {
            System.out.println(user);

        }
    }

   @Test
  public void testUpdate() {
      Integer userId = 12;
       Optional<User> optionalUSer = repo.findById(userId);
       User user = optionalUSer.get();
       user.setPassword("hello987654");
       repo.save(user);

       User updatedUser = repo.findById(userId).get();
       Assertions.assertThat(updatedUser.getPassword()).isEqualTo("hello987654");


   }

   @Test
    public void testGet(){
        Integer userId = 13;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
       System.out.println(optionalUser.get());
   }

   @Test
    public void testDelete(){
        Integer userId = 17;
        repo.deleteById(userId);

        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
   }

}
