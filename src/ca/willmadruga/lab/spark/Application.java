package ca.willmadruga.lab.spark;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.port;

/**
 * Created by wmadruga on 13/03/16.
 */
public class Application {

    private static UserSession userSession;

    public static void main(String args[]) {


        // server port.
        port(8080);

        get("/logout", (req, res) -> {
            if (userSession != null) {
                userSession.setUser(null);
            }
            res.status(HttpURLConnection.HTTP_OK);
            res.body("User is logged out.");

            return res.body();
        });

        post("/login", (req, res) -> {
            try {

                final UserService service = new UserService();
                final ObjectMapper mapper = new ObjectMapper();
                final UserInfo user = mapper.readValue(req.body(), UserInfo.class);

                if (service.validate(user)) {
                    userSession = service.login(user);
                    if (userSession != null) {
                        res.redirect("/private");
                    }

                }
                res.status(HttpURLConnection.HTTP_UNAUTHORIZED);
                res.body("Unauthorized.");

            } catch (final JsonParseException jpe) {
                res.status(HttpURLConnection.HTTP_BAD_REQUEST);
                res.body("Invalid request.");
            }
            return res.body();
        });

        get("/private", (req, res) -> {

            if (userSession == null || (userSession != null && !userSession.isLogged())) {
                res.status(HttpURLConnection.HTTP_UNAUTHORIZED);
                res.body("Unauthorized.");
                return res.body();
            }

            res.status(HttpURLConnection.HTTP_OK);
            res.body("Need to setup a page for that as well... " + userSession.getUser().getUsername() + ".");
            return res.body();
        });

    }

}
