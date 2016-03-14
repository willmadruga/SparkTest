package ca.willmadruga.lab.spark;

/**
 * Created by wmadruga on 13/03/16.
 */
public class UserSession {

    private UserInfo user;

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public boolean isLogged() {
        return (getUser() != null);
    }

}
