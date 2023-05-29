package orm.jpa;

import orm.jpa.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CreateStudent {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("create_pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Student student = new Student();
        student.setFirstName("Thanh");
        student.setLastName("Vo");

        entityManager.persist(student);

        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
