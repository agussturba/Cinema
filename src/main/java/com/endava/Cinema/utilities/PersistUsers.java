package com.endava.Cinema.utilities;

import com.endava.Cinema.model.User;
import com.endava.Cinema.securityConfiguration.PasswordConfig;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PersistUsers {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.endava.jpa.basic");


    public static void adminUserPersist() {
        String adminRole = "ADMIN";
        userPersist(adminRole, "admin", "1234", "admin@mail.com");
    }

    public static void clientUserPersist() {
        String clientRole = "CLIENT";
        userPersist(clientRole, "client", "1234", "client@mail.com");
    }

    private static void userPersist(String role, String userName, String password, String mail) {
        PasswordConfig passwordConfig = new PasswordConfig();
        PasswordEncoder passwordEncoder = passwordConfig.getPasswordEncoder();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        User user = new User(userName, passwordEncoder.encode(password), true, mail);
        user.setRole(role);
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
