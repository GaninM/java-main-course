package com.reksoft;

import com.reksoft.model.UserEntity;
import com.reksoft.orm.JabaORM;

public class Bootstrap {
  public static void main(String[] args) throws Exception {

    JabaORM orm = new JabaORM("jdbc:postgresql://localhost:5432/module11", "postgres", "postgres");

    System.out.println("/////////////////////////////////////////////////////////////////////////");
    System.out.println("All users(expected 4 users): " + orm.getAll(UserEntity.class));

    System.out.println("\n///////////////////////////////////////////////////////////////////////");
    System.out.println("Find user by ID = 1");
    System.out.println(orm.getById(UserEntity.class, 1));

    System.out.println("\n///////////////////////////////////////////////////////////////////////");
    System.out.println("Delete user by ID = 3");
    orm.deleteById(UserEntity.class, 3);
    System.out.println("All users(expected 3 users): " + orm.getAll(UserEntity.class));


    System.out.println("\n///////////////////////////////////////////////////////////////////////");
    System.out.println("Create new user");
    UserEntity newUser = new UserEntity();
    newUser.setName("New User");
    newUser.setAge(100);
    orm.save(newUser);
    System.out.println("Find new user with ID = 5: " + orm.getById(UserEntity.class, 5));

    System.out.println("\n///////////////////////////////////////////////////////////////////////");
    System.out.println("Update user with ID = 1");
    UserEntity updatedUser = orm.getById(UserEntity.class, 1);
    updatedUser.setName("Updated user name");
    updatedUser.setAge(777);
    orm.update(updatedUser);
    System.out.println("Find updated user with ID = 1: " + orm.getById(UserEntity.class, 1));
  }
}

