package com.mexeer.todo.service;

import com.mexeer.todo.entity.User;
import com.mexeer.todo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements GenericService<User,Long>{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User create(User user) {
    if(userRepository.existsByEmail(user.getEmail())){
        // check if the email already exist in the system
        // true
        System.out.println("Email is already usingS");
        return null;
    }else{
        String password = Base64.getEncoder().encodeToString(user.getPassword().getBytes()); // use plain Base64
         user.setPassword(password);
         return userRepository.save(user);
    }


    }

    @Override
    public User update(Long id, User user) {
        Optional<User> currentUser = userRepository.findById(id);
        if(currentUser.isPresent()){
            currentUser.get().setFirstName(user.getFirstName());
            currentUser.get().setLastName(user.getLastName());
            currentUser.get().setGender(user.getGender());
            if(!currentUser.get().getEmail().equals(user.getEmail())){
                if(userRepository.existsByEmail(user.getEmail())){
                    // we have to mention that this email is already in use
                    System.out.println("Email is already in use.."); // best case is to throw exceptions from interface as well
                }
                currentUser.get().setEmail(user.getEmail());
            }
            if(currentUser.get().getPassword().equals(user.getPassword())){
                String encryptedPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
                currentUser.get().setPassword(encryptedPassword);
            }
            return userRepository.save(currentUser.get());
        }else{
            System.out.println("Update Failed .. ");
            return null;
        }
    }

    @Override
    public Optional<User> getById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long aLong) {

    }


}
