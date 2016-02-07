package com.resource;

import com.AppMetricsCollector;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Timer;
import com.model.UserModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

import static com.codahale.metrics.MetricRegistry.name;


/**
 * A simple Rest service to return the static list of users. We can change this to fetch the information from an actual
 * DB but dont want to focus on that as this example is to know how to use metrics api
 */

@Path("/users")
public class UserResource {

    private static UserModel userModel = new UserModel();

    private final Meter allUserRequest = AppMetricsCollector.METRIC_REGISTRY.meter("all-users-request");

    private final Meter userByIdRequest = AppMetricsCollector.METRIC_REGISTRY.meter("usersById-request");

    //Adding timer. So it will keep track of the timing.
    private final Timer timerResponse = AppMetricsCollector.METRIC_REGISTRY.timer(name(UserResource.class, "timerResponse"));

    static {

        UserModel.User user1 = new UserModel.User();
        user1.setUserId("UID1");
        user1.setFirstName("Bala");
        user1.setLastName("Samy");
        user1.setAge(20);

        UserModel.User user2 = new UserModel.User();
        user2.setUserId("UID2");
        user2.setFirstName("John");
        user2.setLastName("moody");
        user2.setAge(20);

        userModel.getUsers().put(user1.getUserId(), user1);
        userModel.getUsers().put(user2.getUserId(), user2);
    }

    /**
     * Fetch all the users
     * @return Collection of Users
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<UserModel.User> getAllUsers() {
        allUserRequest.mark();
        Timer.Context context = timerResponse.time();
        Collection<UserModel.User> userCollection = userModel.getUsers().values();
        context.stop();
        return userCollection;
    }

    /**
     * Search user list by userId
     * @param id
     * @return User
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserModel.User getUserById(@PathParam("id") final String id) {
        userByIdRequest.mark();
        Timer.Context context = timerResponse.time();
        UserModel.User user = userModel.getUsers().get(id);
        context.stop();
        return user;
    }

}
