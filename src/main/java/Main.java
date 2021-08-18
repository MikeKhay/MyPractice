import model.Person;
import service.PersonService;
import service.impl.PersonServiceImpl;

public class Main {
    public static void main(String[] args) {
        PersonService personService = new PersonServiceImpl();

        Person person1 = new Person("Mike", "Khai", 24);
        Person person2 = new Person("Rylya", "Khai", 19);
        Person person3 = new Person("Oleg", "Ladnuk", 23);


//        personService.create(person3);

//        System.out.println(personService.readByFirstName("Oleg"));

//        System.out.println(personService.read(2));

        personService.delete(3);

        for (Person p : personService.readAll()){
            System.out.println(p.toString());
        }
    }
}
