package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;

import java.util.List;

public interface TodoService
{
   Todos findTodoById(long todoid);

   Todos save(long userid, String todoDescription);

   Todos update(long todoid, String todoDescription);
}
