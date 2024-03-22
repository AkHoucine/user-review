package tech.kimari.userreview.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import tech.kimari.userreview.entity.Role;
import tech.kimari.userreview.entity.User;
import tech.kimari.userreview.entity.Validation;
import tech.kimari.userreview.enumeration.RoleType;
import tech.kimari.userreview.repository.UserRepository;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ValidationService validationService;

    public void signin(User user) {

        if(!user.getEmail().contains("@") && !user.getEmail().contains(".")) {
            throw new RuntimeException("Your email is not valid !");
        }

        Optional<User> optionalUser = this.userRepository.findByEmail(user.getEmail());
        if(optionalUser.isPresent()) {
            throw new RuntimeException("Your email is already used !");
        }

        String cryptedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(cryptedPassword);
        Role userRole = new Role();
        userRole.setName(RoleType.USER);
        user.setRole(userRole);
        user = this.userRepository.save(user);
        this.validationService.save(user);
    }

    public void activation(Map<String, String> activation) {
        Validation validation = this.validationService.readWithCode(activation.get("code"));
        if(Instant.now().isAfter((validation.getExpiration()))){
            throw new RuntimeException("Your code is expired !");
        }
        User userActivated = this.userRepository.findById(validation.getUser().getId()).orElseThrow(() -> new RuntimeException("User unknown !"));
        userActivated.setActive(true);
        this.userRepository.save(userActivated);
    }
}
