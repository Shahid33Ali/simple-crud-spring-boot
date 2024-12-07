package com.example.crudapp.demo.serivces;

import com.example.crudapp.demo.models.User;
import com.example.crudapp.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public User addUser(User user){
        if(userRepo.existsByEmail(user.getEmail())){
            throw new IllegalArgumentException("Email already in use");
        }
        return userRepo.save(user);
    }
    public List<User> getUsers(){
        return userRepo.findAll();
    }
    public User getUserById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }
    public User updateUser(Long id,User userDetails){

    User existingUser=getUserById(id);
        // Update fields only if they are not null
        if (userDetails.getName() != null) {
            existingUser.setName(userDetails.getName());
        }
        if (userDetails.getEmail() != null) {
            if(userRepo.existsByEmail(userDetails.getEmail())){
                throw new IllegalArgumentException("Email already in use");
            }
            existingUser.setEmail(userDetails.getEmail());
        }

        // Save the updated user back to the repository
        return userRepo.save(existingUser);
    }
    public void deleteUser(Long id){
        User existinguser=getUserById(id);
        userRepo.delete(existinguser);
    }

}
