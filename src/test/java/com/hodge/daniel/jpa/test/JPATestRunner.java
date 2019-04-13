package com.hodge.daniel.jpa.test;

import com.hodge.daniel.jpa.entity.Car;
import com.hodge.daniel.jpa.entity.Dog;
import org.junit.Test;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPATestRunner {

    @Test
    public void insertNewCar() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("autos");
        EntityManager em = emf.createEntityManager();
        for (int i = 0; i < 1000; i++) {
            em.getTransaction().begin();

            Car car = new Car();
            car.setColor("Black and Red");
            car.setYear(2018);
            em.persist(car);

            em.getTransaction().commit();
        }
        em.close();
    }

    @Test
    public void insertNewDog() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("autos");
        EntityManager em = emf.createEntityManager();
        for (int i = 0; i < 10; i++) {
            em.getTransaction().begin();

            Dog kind = new Dog();
            kind.setBread("Chocolate Lab");
            kind.setAge(4);
            em.persist(kind);

            em.getTransaction().commit();
        }
        em.close();
    }
}
