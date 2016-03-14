package ca.willmadruga.lab.spark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by wmadruga on 13/03/16.
 */
public class UserService {

    final Logger LOG = LoggerFactory.getLogger(UserService.class);

    public UserSession login(final UserInfo user) {
        LOG.info("Log in user");

        if (user.getUsername().contentEquals("admin") && user.getPassword().contentEquals("Admin@123")) {
            final UserSession session = new UserSession();
            session.setUser(user);
            return session;
        }
        return null;
    }

    public boolean validate(final UserInfo user) {
        LOG.info("Validate user info");
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        final Set<ConstraintViolation<UserInfo>> constraintViolations = validator.validate(user);

        if (!constraintViolations.isEmpty()) {
            LOG.info("Constraints violations: ");
            constraintViolations.forEach(item -> LOG.info("{} - {}", item.getPropertyPath(), item.getMessage()));
        }
        return constraintViolations.isEmpty();

    }
}
