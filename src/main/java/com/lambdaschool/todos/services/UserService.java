package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.User;

import java.util.List;

public interface UserService
{
   List<User> findAllUsers();

   User findUserById(long userid);

   User save(User user);

   void delete(long userid);
}
