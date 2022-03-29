package com.endava.Cinema.utilities;

import com.endava.Cinema.model.Seat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class PersistSeats {

    private final static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private final static char[] aplhabetArray = alphabet.toUpperCase().toCharArray();
    private static Seat previousSeat = null;
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.endava.jpa.basic");


    public static void seatsPersist() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        for (Integer rowNumber = 1; rowNumber <= 30; rowNumber++) {
            previousSeat = null;
            for (int seatNumber = 1; seatNumber < 25; seatNumber++) {
                if (seatNumber <= 5) {
                    persistSeat(rowNumber, "Left Section", seatNumber,entityManager);
                } else if (seatNumber > 5 && seatNumber <= 15) {
                    if (seatNumber == 6) {
                        previousSeat = null;
                    }
                    persistSeat(rowNumber, "Middle Section", seatNumber,entityManager);
                } else {
                    if (seatNumber == 16) {
                        previousSeat = null;
                    }
                    persistSeat(rowNumber, "Middle Section", seatNumber,entityManager);
                }
            }
        }
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    private static void persistSeat(Integer rowNumber, String section, Integer seatNumber,EntityManager entityManager) {

        char letter = aplhabetArray[seatNumber-1];
        Seat seat = new Seat(rowNumber.toString() + letter, section, previousSeat);
        entityManager.persist(seat);
        previousSeat = seat;

    }
}
