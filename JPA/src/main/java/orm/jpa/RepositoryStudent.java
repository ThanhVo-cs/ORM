package orm.jpa;

import orm.jpa.model.Student;

import javax.persistence.*;
import java.util.List;

public class RepositoryStudent {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public RepositoryStudent() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("create_pu");
        entityManager = this.entityManagerFactory.createEntityManager();
    }

    public Student add(Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(student);
        entityTransaction.commit();
        return student;
    }

    public Student find(Student student) {
        Student std = entityManager.find(Student.class, student.getId());
        return std;
    }

    public List<String> findFirstNames() {
        entityManager.getTransaction().begin();
        Query qr = entityManager.createNativeQuery("select first_name from student");
        entityManager.getTransaction().commit();
        return qr.getResultList();
    }

    public List<String> findLastNames() {
        entityManager.getTransaction().begin();
        Query qr = entityManager.createNativeQuery("select last_name from student");
        entityManager.getTransaction().commit();
        return qr.getResultList();
    }

    public Student update(Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Student stu = entityManager.find(Student.class, student.getId());
        stu.setFirstName("Duong");
        entityTransaction.commit();
        return stu;
    }

    public void delete(Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Student stu = entityManager.find(Student.class, student.getId());
        entityManager.remove(stu);
        entityTransaction.commit();
    }


    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
