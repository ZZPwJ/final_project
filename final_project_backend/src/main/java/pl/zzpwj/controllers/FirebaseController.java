package pl.zzpwj.controllers;

import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.zzpwj.model.Firebase.User;
import pl.zzpwj.model.SearchParameters;
import pl.zzpwj.services.FirebaseInitialize;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class FirebaseController {
    @Autowired
    FirebaseInitialize firebaseInitialize;

    @CrossOrigin
    @PostMapping(path="/user", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public String createUser(@RequestBody User user) {
        try {
            System.out.println("ABCCDA");
            return firebaseInitialize.createUser(user);
        } catch (FirebaseAuthException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping(path="/user/search/{token}")
    public void saveSearch(@PathVariable String token, @RequestBody SearchParameters searchParameters) {
        try {
            firebaseInitialize.saveSearch(token, searchParameters);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
    }
}
