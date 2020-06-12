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

   @Override
   public Todos save(Todos todo)
   {
      Todos newTodo = new Todos();
      if (todo.getTodoid() != 0) {
         todorepos.findById(todo.getTodoid())
                 .orElseThrow(() -> new EntityNotFoundException("Todo " + todo.getTodoid() + " Not Found"));

         newTodo.setTodoid(todo.getTodoid());
      }
      newTodo.setDescription(todo.getDescription());

      return todorepos.save(newTodo);
   }
}
