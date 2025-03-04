package sr.unasat.subscription.api.mappers;

import sr.unasat.subscription.api.dto.SubscriptionDTO;
import sr.unasat.subscription.api.entities.Subscription;

public class SubscriptionMapper {

    public SubscriptionDTO toDTO(Subscription subscription) {
        return new SubscriptionDTO(
                subscription.getId(),
                subscription.getFirstname(),
                subscription.getLastname(),
                subscription.getEmail(),
                subscription.getPhonenumber()
        );
    }

    public Subscription toEntity(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = new Subscription();
        subscription.setId(subscriptionDTO.getId());
        subscription.setFirstname(subscriptionDTO.getFirstname());
        subscription.setLastname(subscriptionDTO.getLastname());
        subscription.setEmail(subscriptionDTO.getEmail());
        subscription.setPhonenumber(subscriptionDTO.getPhonenumber());
        return subscription;
    }
}
