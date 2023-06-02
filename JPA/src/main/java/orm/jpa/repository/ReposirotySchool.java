package orm.jpa.repository;

import orm.jpa.model.School;
import orm.jpa.model.Student;

import javax.persistence.*;
import java.util.List;

public class ReposirotySchool {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public ReposirotySchool() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("create_pu");
        entityManager = this.entityManagerFactory.createEntityManager();
    }

    public School add(School school) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(school);
        entityTransaction.commit();
        return school;
    }

    public School find(Long id) {
        School std = entityManager.find(School.class, id);
        return std;
    }

    public School update(School school) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        School stu = entityManager.find(School.class, school.getId());
        stu.setName(school.getName());
        stu.setCity((school.getCity()));
        entityTransaction.commit();
        return stu;
    }

    public void delete(School school) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        School stu = entityManager.find(School.class, school.getId());
        entityManager.remove(stu);
        entityTransaction.commit();
    }

    public void deleteSchool(Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("delete from school where id = " + id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public void addStudent(Long id, Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        School school = find(id);
        if (school != null) {
            school.getStudents().add(student);
        }
        entityManager.persist(school);
        entityTransaction.commit();
    }

    public void removeStudent(Long id, Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        School school = find(id);
        if (school != null) {
            school.getStudents().remove((student));
        }
        entityManager.persist(school);
        entityTransaction.commit();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
