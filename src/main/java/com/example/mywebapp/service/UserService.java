package com.example.mywebapp.service;

import com.example.mywebapp.user.User;
import com.example.mywebapp.user.UserNotFoundException;
import com.example.mywebapp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    public List<User> listAll(){
        return (List<User>) repo.findAll();

    }

    public void save(User user){
        repo.save(user);

    }

    public User get(Integer id) throws UserNotFoundException {
        try {
            Optional<User> result = repo.findById(id);
            if(result.isPresent()){
                return result.get();
            }
        }
        catch (Exception e){


        }

        throw new UserNotFoundException("Could not find any users with ID" + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0){
            throw new UserNotFoundException("Could not find any users with ID" + id);
        }
        repo.deleteById(id);
    }
}
