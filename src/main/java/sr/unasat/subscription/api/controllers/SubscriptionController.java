package sr.unasat.subscription.api.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sr.unasat.subscription.api.dto.SubscriptionDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Path("/subscriptions")
public class SubscriptionController {

    private static final List<SubscriptionDTO> subscriptions = new ArrayList<>();
    private static final AtomicLong idCounter = new AtomicLong();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SubscriptionDTO> getAllSubscriptions() {
        return subscriptions;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubscription(@PathParam("id") long id) {
        SubscriptionDTO subscription = subscriptions.stream()
                .filter(sub -> sub.getId() == id)
                .findFirst()
                .orElse(null);
        if (subscription != null) {
            return Response.ok(subscription).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSubscription(SubscriptionDTO subscription) {
        subscription.setId(idCounter.incrementAndGet());
        subscriptions.add(subscription);
        return Response.status(Response.Status.CREATED).entity(subscription).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSubscription(@PathParam("id") long id, SubscriptionDTO updatedSubscription) {
        SubscriptionDTO existingSubscription = subscriptions.stream()
                .filter(sub -> sub.getId() == id)
                .findFirst()
                .orElse(null);
        if (existingSubscription != null) {
            existingSubscription.setFirstname(updatedSubscription.getFirstname());
            existingSubscription.setLastname(updatedSubscription.getLastname());
            existingSubscription.setEmail(updatedSubscription.getEmail());
            existingSubscription.setPhonenumber(updatedSubscription.getPhonenumber());
            return Response.ok(existingSubscription).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSubscription(@PathParam("id") long id) {
        boolean removed = subscriptions.removeIf(sub -> sub.getId() == id);
        if (removed) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
