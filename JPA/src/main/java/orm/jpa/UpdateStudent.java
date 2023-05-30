package orm.jpa;

import orm.jpa.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UpdateStudent {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("create_pu");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        Student stu = entityManager.find(Student.class, 2L);
        entityTransaction.begin();
        stu.setFirstName("Thi");

//        entityManager.createQuery("update student set first_name = 'THI' where id = 2").executeUpdate();
        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

}