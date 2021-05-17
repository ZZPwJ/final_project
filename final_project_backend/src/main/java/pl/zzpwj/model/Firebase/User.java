package pl.zzpwj.model.Firebase;

import lombok.Data;

@Data
public class User {
    private String email;
    private String password;
    private String displayName;
}
