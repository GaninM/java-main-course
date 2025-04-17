package org.example.module2restful.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.module2restful.repository.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class UserRepository {

  private final SessionFactory sessionFactory;

  public List<User> findAll() {
    return sessionFactory.getCurrentSession()
        .createQuery("from User", User.class)
        .list();
  }

  public User findById(Long id) {
    return sessionFactory.getCurrentSession().get(User.class, id);
  }

  public User save(User user) {
    sessionFactory.getCurrentSession().saveOrUpdate(user);
    return user;
  }

  public void delete(User user) {
    sessionFactory.getCurrentSession().delete(user);
  }
}
