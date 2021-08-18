
package service.impl;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import model.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.PersonService;
import until.SessionFactoryUntil;

import java.util.List;

@Log4j
public class PersonServiceImpl implements PersonService {

    Session session;

    public PersonServiceImpl(){
        this.session = SessionFactoryUntil.createSession();
    }

    public List<Person> readAll(){
        log.info("Trying to get all persons");
        return session.createQuery("from Person").list();
    }

    public Person read(int id){
        log.info("Trying to get person");
        return session.get(Person.class, id);
    }

    public void create(Person person) {
        log.info("Try to create person");

        Transaction transaction = session.beginTransaction();

        try {
            session.persist(person);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }
        log.info("New person with name " + person.getFirstName() + " was created.");
    }

    public void delete(int id) {

        Transaction transaction = session.beginTransaction();

        try {
            Person person = this.read(id);
            session.delete(person);
            transaction.commit();

            log.info("Person with name " + person.getFirstName() + " was deleted.");

        } catch (Exception e){
            transaction.rollback();
        }
    }

    public Person readByFirstName(String firstName){
        log.info("Trying to get person about firstName");

        Query query = session.createQuery("From Person p WHERE p.first_name = :firstName");
        query.setParameter("first_name", firstName);
        return (Person) query.getSingleResult();
    }

}
