package sr.unasat.subscription.api.controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import sr.unasat.subscription.api.dto.SubscriptionDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Path("/subscriptions")
public class SubscriptionController {

    private static final List<SubscriptionDTO> subscriptions = new ArrayList<>(Arrays.asList(
            new SubscriptionDTO(1, "John", "Doe", "john.doe@example.com", "1234567890"),
            new SubscriptionDTO(2, "Jane", "Smith", "jane.smith@example.com", "0987654321"),
            new SubscriptionDTO(3, "Alice", "Johnson", "alice.johnson@example.com", "1122334455"),
            new SubscriptionDTO(4, "Bob", "Brown", "bob.brown@example.com", "5566778899"),
            new SubscriptionDTO(5, "Charlie", "Davis", "charlie.davis@example.com", "6677889900")
    ));
    private static int idCounter = 0;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SubscriptionDTO> getAllSubscriptions() {
        return subscriptions;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubscription(@PathParam("id") long id) {
        for (SubscriptionDTO sub : subscriptions) {
            if (sub.getId() == id) {
                return Response.ok(sub).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSubscription(SubscriptionDTO subscription) {
        subscription.setId(++idCounter);
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
