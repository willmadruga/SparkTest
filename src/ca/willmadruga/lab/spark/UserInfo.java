package ca.willmadruga.lab.spark;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by wmadruga on 13/03/16.
 */
public class UserInfo {

    @NotNull
    private String username;
    @NotNull
    @Pattern(regexp = ".*") // TODO: build a password pattern with alpha-numeric + symbols + at least one Uppercase letter.
    private String password;
    @NotNull
    @Pattern(regexp = ".*") // TODO: grab email pattern...
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
