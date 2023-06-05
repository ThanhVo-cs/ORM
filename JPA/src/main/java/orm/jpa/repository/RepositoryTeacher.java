package orm.jpa.repository;

import orm.jpa.model.Teacher;
import orm.jpa.model.Tutor;

import javax.persistence.*;

public class RepositoryTeacher {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public RepositoryTeacher() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("create_pu");
        entityManager = this.entityManagerFactory.createEntityManager();
    }

    public Teacher add(Teacher teacher) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(teacher);
        entityTransaction.commit();
        return teacher;
    }

    public Teacher find(Teacher teacher) {
        Teacher std = entityManager.find(Teacher.class, teacher.getId());
        return std;
    }

    public Teacher update(Teacher teacher) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Teacher stu = entityManager.find(Teacher.class, teacher.getId());
        stu.setFirstName(teacher.getFirstName());
        stu.setLastName((teacher.getLastName()));
        entityTransaction.commit();
        return stu;
    }

    public void delete(Teacher teacher) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Tutor stu = entityManager.find(Tutor.class, teacher.getId());
        entityManager.remove(stu);
        entityTransaction.commit();
    }

    public void deleteTutor(Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("delete from teacher where id = " + id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
