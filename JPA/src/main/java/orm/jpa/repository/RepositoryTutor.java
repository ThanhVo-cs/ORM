package orm.jpa.repository;

import orm.jpa.model.School;
import orm.jpa.model.Tutor;

import javax.persistence.*;

public class RepositoryTutor {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public RepositoryTutor() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("create_pu");
        entityManager = this.entityManagerFactory.createEntityManager();
    }

    public Tutor add(Tutor tutor) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(tutor);
        entityTransaction.commit();
        return tutor;
    }

    public Tutor find(Tutor tutor) {
        Tutor std = entityManager.find(Tutor.class, tutor.getId());
        return std;
    }

    public Tutor update(Tutor tutor) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Tutor stu = entityManager.find(Tutor.class, tutor.getId());
        stu.setFirstName(tutor.getFirstName());
        stu.setLastName((tutor.getLastName()));
        entityTransaction.commit();
        return stu;
    }

    public void delete(Tutor tutor) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Tutor stu = entityManager.find(Tutor.class, tutor.getId());
        entityManager.remove(stu);
        entityTransaction.commit();
    }

    public void deleteTutor(Long id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("delete from tutor where id = " + id);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
