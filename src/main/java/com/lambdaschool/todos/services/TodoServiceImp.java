package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service(value = "todoService")
public class TodoServiceImp implements TodoService
{
   @Autowired
   private TodoRepository todorepos;

   @Override
   public List<Todos>
}
