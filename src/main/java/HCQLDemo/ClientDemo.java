package HCQLDemo;

import java.util.List;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Load configuration
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Insert records
        Customer customer1 = new Customer();
        customer1.setName("Manish Raj");
        customer1.setEmail("manish@gmail.com");
        customer1.setAge(20);
        customer1.setLocation("Hyderabad");

        Customer customer2 = new Customer();
        customer2.setName("Ayush");
        customer2.setEmail("ayush@gmail.com");
        customer2.setAge(19);
        customer2.setLocation("Hyderabad");

        session.persist(customer1);
        session.persist(customer2);

        transaction.commit();

        System.out.println("CriteriaBuilder Queries:");

        CriteriaBuilder builder = session.getCriteriaBuilder();

        
        CriteriaQuery<Customer> equalQuery = builder.createQuery(Customer.class);
        Root<Customer> equalRoot = equalQuery.from(Customer.class);
        equalQuery.select(equalRoot).where(builder.equal(equalRoot.get("name"), "Manish Raj"));
        List<Customer> equalResult = session.createQuery(equalQuery).getResultList();
        System.out.println("Equal: " + equalResult);

        
        CriteriaQuery<Customer> notEqualQuery = builder.createQuery(Customer.class);
        Root<Customer> notEqualRoot = notEqualQuery.from(Customer.class);
        notEqualQuery.select(notEqualRoot).where(builder.notEqual(notEqualRoot.get("location"), "Hyderabad"));
        List<Customer> notEqualResult = session.createQuery(notEqualQuery).getResultList();
        System.out.println("Not Equal: " + notEqualResult);

        
        CriteriaQuery<Customer> lessThanQuery = builder.createQuery(Customer.class);
        Root<Customer> lessThanRoot = lessThanQuery.from(Customer.class);
        lessThanQuery.select(lessThanRoot).where(builder.lessThan(lessThanRoot.get("age"), 20));
        List<Customer> lessThanResult = session.createQuery(lessThanQuery).getResultList();
        System.out.println("Less Than: " + lessThanResult);

       
        CriteriaQuery<Customer> greaterThanQuery = builder.createQuery(Customer.class);
        Root<Customer> greaterThanRoot = greaterThanQuery.from(Customer.class);
        greaterThanQuery.select(greaterThanRoot).where(builder.greaterThan(greaterThanRoot.get("age"), 20));
        List<Customer> greaterThanResult = session.createQuery(greaterThanQuery).getResultList();
        System.out.println("Greater Than: " + greaterThanResult);

        
        CriteriaQuery<Customer> betweenQuery = builder.createQuery(Customer.class);
        Root<Customer> betweenRoot = betweenQuery.from(Customer.class);
        betweenQuery.select(betweenRoot).where(builder.between(betweenRoot.get("age"), 20, 35));
        List<Customer> betweenResult = session.createQuery(betweenQuery).getResultList();
        System.out.println("Between: " + betweenResult);

 
        CriteriaQuery<Customer> likeQuery = builder.createQuery(Customer.class);
        Root<Customer> likeRoot = likeQuery.from(Customer.class);
        likeQuery.select(likeRoot).where(builder.like(likeRoot.get("email"), "%gmail.com"));
        List<Customer> likeResult = session.createQuery(likeQuery).getResultList();
        System.out.println("Like: " + likeResult);

        session.close();
        sessionFactory.close();
    }
}