package app.daos;

import app.entities.User;
import app.persistence.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDAO implements IDAO<User>{

    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    @Override
    public User getById(Integer id) {
        try (EntityManager em = emf.createEntityManager()){
            return em.find(User.class ,id);
        }
    }

    @Override
    public Set<User> getAll() {
        try (EntityManager em = emf.createEntityManager()){
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
            List<User> userList = query.getResultList();
            return userList.stream().collect(Collectors.toSet());
        }
    }

    @Override
    public void create(User user) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }
    }

    @Override
    public void update(User user) {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }
    }

    @Override
    public void delete(User user) {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            getById(user.getId());
            em.remove(user);
            em.getTransaction().commit();
        }
    }

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
       /* userDAO.create(
                User
                        .builder()
                        .username("user1")
                        .password("Password123")
                        .email("user1@mail.com")
                        .build()
        );

        */
        // userDAO.update(new User(4,"lol","lol","lol"));

        // userDAO.delete(new User(1, "lol", "lol", "lol"));

        userDAO.getAll().forEach(System.out::println);

        System.out.println("find: " + userDAO.getById(3));

    }


}
