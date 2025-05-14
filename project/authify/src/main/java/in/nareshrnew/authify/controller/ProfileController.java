package in.nareshrnew.authify.controller;

import in.nareshrnew.authify.io.ProfileRequest;
import in.nareshrnew.authify.io.ProfileResponse;
import in.nareshrnew.authify.service.EmailService;
import in.nareshrnew.authify.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
// Example Java (Spring Boot) CORS setting
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")

public class ProfileController {

    private final ProfileService profileService;
    private final EmailService emailService;

    // Example Java (Spring Boot) CORS setting


    @PostMapping("/register")
    // Example Java (Spring Boot) CORS setting
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")

    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse register(@Valid @RequestBody ProfileRequest request) {
        ProfileResponse response = profileService.createProfile(request);
        emailService.sendWelcomeEmail(response.getEmail(), response.getName());
        return response;
    }

    @GetMapping("/profile")
    public ProfileResponse getProfile(@CurrentSecurityContext(expression = "authentication?.name") String email) {
        return profileService.getProfile(email);
    }
}
