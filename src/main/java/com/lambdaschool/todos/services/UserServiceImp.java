package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repositories.TodoRepository;
import com.lambdaschool.todos.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImp implements UserService {
   @Autowired
   private UserRepository userrepos;

   @Override
   public List<User> findAllUsers() {
      List<User> list = new ArrayList<>();

      userrepos.findAll()
              .iterator()
              .forEachRemaining(list::add);
      return list;
   }

   @Override
   public User findUserById(long userid)
           throws EntityNotFoundException {
      return userrepos.findById(userid)
              .orElseThrow(() -> new EntityNotFoundException("User " + userid + " Not Found"));
   }

   @Transactional
   @Override
   public User save(User user) {
      User newUser = new User();
      if (user.getUserid() != 0) {
         userrepos.findById(user.getUserid())
                 .orElseThrow(() -> new EntityNotFoundException("User " + user.getUserid() + " Not Found"));

         newUser.setUserid(user.getUserid());
      }

      newUser.setUsername(user.getUsername());
      newUser.setPassword(user.getPassword());
      newUser.setPrimaryemail(user.getPrimaryemail());

      newUser.getTodos().clear();
         for (Todos t : user.getTodos()) {
            Todos newTodos = new Todos(newUser, t.getDescription());
            newUser.getTodos().add(newTodos);
         }
      return userrepos.save(newUser);
   }
}
