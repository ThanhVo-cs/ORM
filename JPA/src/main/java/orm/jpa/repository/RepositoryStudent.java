package orm.jpa.repository;

import orm.jpa.model.School;
import orm.jpa.model.Student;
import orm.jpa.model.Tutor;

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
        Query qr = entityManager.createNativeQuery("select first_name from student");
        return qr.getResultList();
    }

    public List<String> findLastNames() {
        Query qr = entityManager.createNativeQuery("select last_name from student");
        return qr.getResultList();
    }

    public Student findById(Long id) {
        Query query = entityManager.createNamedQuery("find student by id");
        query.setParameter("id", id);
        return (Student) query.getSingleResult();
    }
    public Student update(Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Student stu = entityManager.find(Student.class, student.getId());
        stu.setFirstName("Duong");
        entityTransaction.commit();
        return stu;
    }

    public Student updateFirstName(String firstName, Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("update student set first_name = '" + firstName + "' where id = " + id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        return findById(id);
    }

    public Student updatelastName(String lastName, Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("update student set last_name = '" + lastName + "' where id = " + id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        return findById(id);
    }
    public void delete(Student student) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Student stu = entityManager.find(Student.class, student.getId());
        entityManager.remove(stu);
        entityTransaction.commit();
    }

    public void deleteStudent(Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("delete from student where id = " + id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public List<Student> filterByFirstName(String keywork) {
        Query query = entityManager.createQuery("select st from Student st where st.firstName like '" + keywork + "%'");
        return query.getResultList();
    }

    public Long count() {
        Query query = entityManager.createQuery("select count(st) from Student st");
        return (Long) query.getSingleResult();
    }

    public List<Student> findSortingByFirstName() {
        Query query = entityManager.createQuery("select s from Student s order by s.firstName desc ");
        return query.getResultList();
    }

    public Tutor addTutors(Long id, Tutor tutor) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Student student = findById(id);
        student.setTutor(tutor);
        entityTransaction.commit();
        return tutor;
    }
    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
