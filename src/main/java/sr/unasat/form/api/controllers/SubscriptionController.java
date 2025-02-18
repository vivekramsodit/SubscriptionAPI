package sr.unasat.form.api.controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import sr.unasat.form.api.dto.SubscriptionDTO;

@Path("subscription")
public class SubscriptionController {
    private List<SubscriptionDTO> subscriptionList = new ArrayList<>();

    @Path("/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SubscriptionDTO> findAll() {
        return subscriptionList;
    }


    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SubscriptionDTO add(SubscriptionDTO subscription) {
        subscriptionList.add(subscription);
        return subscription;
    }
}
