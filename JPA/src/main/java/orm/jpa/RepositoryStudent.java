package orm.jpa;

import orm.jpa.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
