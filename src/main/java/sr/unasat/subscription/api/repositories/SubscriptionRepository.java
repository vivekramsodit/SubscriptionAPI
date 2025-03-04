package sr.unasat.subscription.api.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import sr.unasat.subscription.api.config.JPAConfig;
import sr.unasat.subscription.api.entities.Subscription;

import java.util.List;

public class SubscriptionRepository implements Repository<Subscription> {
    private EntityManager em;

    public SubscriptionRepository() {
        em = JPAConfig.getEntityManger();
    }


    @Override
    public List<Subscription> findAll() {
        String QUERY = "SELECT s from Subscription s" ;
        return em.createQuery(QUERY, Subscription.class).getResultList();
    }

    @Override
    public Subscription findById(Integer id) {
        return em.find(Subscription.class,id);
    }

    @Override
    public void save(Subscription subscription) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(subscription);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

    @Override
    public void update(Subscription entity) {

    }

    @Override
    public void delete(Integer id) {

    }
}
