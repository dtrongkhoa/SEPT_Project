package com.rmit.sept.bk_loginservices.services;

import com.rmit.sept.bk_loginservices.repositories.UserRepository;
import com.rmit.sept.bk_loginservices.exceptions.UsernameAlreadyExistsException;
import com.rmit.sept.bk_loginservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser){

        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            //Username has to be unique (exception)
            newUser.setUsername(newUser.getUsername());
            // Make sure that password and confirmPassword match
            // We don't persist or show the confirmPassword
            newUser.setConfirmPassword("");
            return userRepository.save(newUser);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
        }

    }

    @Transactional
    public User updateUser (String oldUsername, String newUsername, String fullName, String password){
        User user = userRepository.findByUsername(oldUsername);
        userRepository.editUserDetails(newUsername, fullName, bCryptPasswordEncoder.encode(password), oldUsername);
        user.setUsername(newUsername);
        user.setFullName(fullName);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return user;
    }

    @Transactional
    public boolean deleteUser (String username) {
        if (userRepository.findByUsername(username) != null) {
            userRepository.deleteUserByUsername(username);
            return true;
        }
        return false;
    }
    
    public Iterable<User> fetchAllUserData () {
    	return userRepository.findAll();
    }


}
