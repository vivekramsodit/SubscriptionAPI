package sr.unasat.subscription.api.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfig {
    private static final String PERSISTENCE_UNIT_NAME = "subscriptionAPI";
    private static EntityManagerFactory emf ;
    private static EntityManager em ;


    public static   EntityManagerFactory getEntityMangerFactory(){
        if (emf == null ) {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return emf;
    }

    public static EntityManager getEntityManger(){
        if (em == null ) {
            em  = emf.createEntityManager();
        }
        return em;
    }

    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public static void closeEntityManager() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

}


