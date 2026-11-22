package lara.carmen.PokeAPI.service;

import lara.carmen.PokeAPI.dto.UserRequest;
import lara.carmen.PokeAPI.exception.ResourceNotFoundException;
import lara.carmen.PokeAPI.model.User;
import lara.carmen.PokeAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserRequest request){
        if (userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("El email ya fue utilizado.");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado."));
    }
}
