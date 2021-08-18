package service.impl;

import lombok.extern.log4j.Log4j2;
import model.User;
import service.UserService;
import unit.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Log4j2
public class UserServiceImpl implements UserService {

    EntityManager entityManager;

    public UserServiceImpl(){
        this.entityManager = FactoryManager.getEntityManager();
    }


    @Override
    public List<User> readAll() {
        log.info("Trying to get all users");
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    @Override
    public User read(int id) {
        log.info("Trying to get user");
        return entityManager.find(User.class, id);
    }

    @Override
    public void create(User user) {
        log.info("Trying to create user");

        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }

        entityManager.persist(user);

        entityManager.getTransaction().commit();

        log.info("New user with email " + user.getEmail() + " was create.");
    }

    @Override
    public void delete(int id) {

        log.info("Trying to delete user");

        if (!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }

        User user = entityManager.find(User.class, id);

        entityManager.remove(user);
        entityManager.getTransaction().commit();

        log.info("User with email " + user.getEmail() + " was remote.");

    }

    @Override
    public User readByEmail(String email) {
        log.info("Trying to get user");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> from = criteriaQuery.from(User.class);

        criteriaQuery.select(from);
        criteriaQuery.where(criteriaBuilder.equal(from.get("email"), email));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
