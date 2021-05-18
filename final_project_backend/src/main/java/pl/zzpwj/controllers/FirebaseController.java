package pl.zzpwj.controllers;

import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.zzpwj.model.Firebase.User;
import pl.zzpwj.services.FirebaseInitialize;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class FirebaseController {
    @Autowired
    FirebaseInitialize firebaseInitialize;

    @PostMapping(path="/user", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody User user) {
        try {
            return firebaseInitialize.createUser(user);
        } catch (FirebaseAuthException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(path="/user/search/{token}")
    public void saveSearch(@PathVariable String token) throws IOException {
        try {
            firebaseInitialize.saveSearch(token);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
    }
}
