package sr.unasat.subscription.api.services;

import sr.unasat.subscription.api.entities.Subscription;
import sr.unasat.subscription.api.repositories.SubscriptionRepository;

import java.util.List;

public class SubscriptionService {
    private SubscriptionRepository subscriptionRepository;


    public SubscriptionService() {
        subscriptionRepository = new SubscriptionRepository();
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public Subscription findById(Integer id){
        return subscriptionRepository.findById(id);
    }

    public void saveSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    public Subscription saveSubscriptionWithPara(String firstname, String lastname, String email , String phonenumber) {
        Subscription subscription = new Subscription();
        subscription.setFirstname(firstname);
        subscription.setLastname(lastname);
        subscription.setEmail(email);
        subscription.setPhonenumber(phonenumber);
        subscriptionRepository.save(subscription);

        return subscription;
    }

}
