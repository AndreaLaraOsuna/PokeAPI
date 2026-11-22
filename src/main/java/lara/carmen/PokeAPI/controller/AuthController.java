package lara.carmen.PokeAPI.controller;

import lara.carmen.PokeAPI.dto.LoginRequest;
import lara.carmen.PokeAPI.dto.LoginResponse;
import lara.carmen.PokeAPI.model.User;
import lara.carmen.PokeAPI.repository.UserRepository;
import lara.carmen.PokeAPI.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request ) {
        User user = userRepository.findByEmail(request.getMail());

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "credenciales inváñidas"));
        }

        if(!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "credenciales inváñidas"));
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole());

        return  ResponseEntity.ok(new LoginResponse(token));
    }
}
