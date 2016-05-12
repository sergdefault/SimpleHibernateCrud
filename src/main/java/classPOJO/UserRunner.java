package classPOJO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


/**
 * Created by Цымбалюк Сергей on 06.05.2016.
 */
public class UserRunner {
        private static SessionFactory sessionFactory;

        public static void main(String[] args) {
            sessionFactory = new Configuration().configure().buildSessionFactory();

            UserRunner  userRunner= new UserRunner();

            userRunner.addUser( "Developer", 2);
            userRunner.addUser ("Developer", 2);
            userRunner.addUser( "Developer", 2);
        }

        public void addUser(String firstName, int age) {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;

            transaction = session.beginTransaction();
            User developer = new User(firstName, age);
            session.save(developer);
            transaction.commit();
            session.close();
        }

        public List listUsers() {
            Session session = this.sessionFactory.openSession();
            Transaction transaction = null;

            transaction = session.beginTransaction();
            List users = (List) session.createQuery("FROM User").list();

            transaction.commit();
            session.close();
            return users;
        }

        public void updateUser(int userId, int age) {
            Session session = this.sessionFactory.openSession();
            Transaction transaction = null;

            transaction = session.beginTransaction();
            User user = (User) session.get(User.class, userId);
            user.setAge(age);
            session.update(user);
            transaction.commit();
            session.close();
        }

        public void removeDeveloper(int userId) {
            Session session = this.sessionFactory.openSession();
            Transaction transaction = null;

            transaction = session.beginTransaction();
            User developer = (User) session.get(User.class, userId);
            session.delete(developer);
            transaction.commit();
            session.close();
        }

    }

