package tech.kimari.userreview.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.kimari.userreview.entity.User;
import tech.kimari.userreview.entity.Validation;
import tech.kimari.userreview.repository.ValidationRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import static java.time.temporal.ChronoUnit.MINUTES;

@AllArgsConstructor
@Service
public class ValidationService {

    private ValidationRepository validationRepository;
    private NotificationService notificationService;

    public void save(User user) {
        Validation validation = new Validation();
        validation.setUser(user);
        Instant creation = Instant.now();
        validation.setCreation(creation);
        Instant expiration = creation.plus(10, MINUTES);
        validation.setExpiration(expiration);

        Random random = new Random();
        int randomInteger = random.nextInt(999999);
        String code = String.format("%06d", randomInteger);

        validation.setCode(code);
        this.validationRepository.save(validation);
        this.notificationService.send(validation);
    }

    public Validation readWithCode(String code) {
        return this.validationRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Your code is not valid !"));
    }
}
