package pl.zzpwj.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.stereotype.Service;
import pl.zzpwj.model.Firebase.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class FirebaseInitialize {
    public FirebaseInitialize() throws IOException {
        File file = new File(
                Objects.requireNonNull(getClass().getClassLoader().getResource("firebase_key.json")).getFile()
        );

        FileInputStream serviceAccount = new FileInputStream(file);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://zzpwj-final-project-default-rtdb.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);
    }

        // Create user
    public String createUser(User user) throws FirebaseAuthException, IOException {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(user.getEmail())
                .setEmailVerified(false)
                .setPassword(user.getPassword())
                .setDisplayName(user.getDisplayName())
                .setDisabled(false);

        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

        // Generate token
        String token = FirebaseAuth.getInstance().createCustomToken(userRecord.getUid());

        // Create user node in database
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("users");
        Map<String, Object> userMap = new HashMap<>();
        userMap.put(userRecord.getUid(), "");
        dbReference.updateChildren(userMap, null);

        return token;
    }

    public void saveSearch(String userToken) throws IOException, FirebaseAuthException {
        File file = new File(
                Objects.requireNonNull(getClass().getClassLoader().getResource("firebase_key.json")).getFile()
        );

        FileInputStream serviceAccount = new FileInputStream(file);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://zzpwj-final-project-default-rtdb.firebaseio.com/")
                .build();

        FirebaseApp.initializeApp(options);


            FirebaseToken tokenDecoder = FirebaseAuth.getInstance().verifyIdToken(userToken);
            String uid = tokenDecoder.getUid();
            DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("users/" + uid);
            dbReference.child("trip").setValueAsync("Abc");
    }
}
