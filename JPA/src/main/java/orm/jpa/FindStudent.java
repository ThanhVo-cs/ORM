package orm.jpa;

import orm.jpa.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FindStudent {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("create_pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Student std = entityManager.find(Student.class, 2L);
        if (std != null) {
            System.out.println(std.toString());
        }
    }
}
