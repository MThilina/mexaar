package com.mexeer.todo.service;

import com.mexeer.todo.entity.User;
import com.mexeer.todo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class UserService implements GenericService<User,Long>{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User create(User user) throws Exception{
    if(userRepository.existsByEmail(user.getEmail())){
        // check if the email already exist in the system
        throw new IllegalArgumentException("Email is already using ");
    }else{
        String password = encryptPassword(user); // use plain Base64
         user.setPassword(password);
         return userRepository.save(user);
    }


    }

    @Override
    public User update(Long id, User user) throws Exception{
        // Check if user exists before updating
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found."));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setGender(user.getGender());

        // Update email if it's changed and not in use
        if (!existingUser.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }
        existingUser.setEmail(user.getEmail());

        // Encrypt the password if updated
        if (!user.getPassword().isEmpty() && !existingUser.getPassword().equals(user.getPassword())) {
            existingUser.setPassword(encryptPassword(user));
        }

        return userRepository.save(existingUser);
    }


    @Override
    public Optional<User> getById(Long id) throws Exception{
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAll() throws Exception{
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws Exception{
        // Check if user exists before deletion
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID " + id + " not found."));

        // Cascade delete the user's ToDo lists (done by cascade setting in the entity)
        userRepository.delete(user);
    }

/******************** Private Mehtod *******************************************/

    private static String encryptPassword(User user) {
        return Base64.getEncoder().encodeToString(user.getPassword().getBytes());
    }


}
