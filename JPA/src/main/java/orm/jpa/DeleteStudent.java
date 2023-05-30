package orm.jpa;

import orm.jpa.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DeleteStudent {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("create_pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        Student std = entityManager.find(Student.class, 2L);
        entityTransaction.begin();
        entityManager.remove(std);
        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
