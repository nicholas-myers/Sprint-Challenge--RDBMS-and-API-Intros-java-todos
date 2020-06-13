package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Transactional
@Service(value = "todoService")
public class TodoServiceImp implements TodoService
{
   @Autowired
   private TodoRepository todorepos;

   @Autowired
   private UserService userService;

   @Override
   public Todos save(long userid, String todoDescription)
   {
      User currentUser = userService.findUserById(userid);
      Todos newTodo = new Todos(currentUser, todoDescription);

      return todorepos.save(newTodo);
   }
}
