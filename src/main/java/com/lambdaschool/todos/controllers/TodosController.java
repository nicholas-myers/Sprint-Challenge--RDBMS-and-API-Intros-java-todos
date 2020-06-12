package com.lambdaschool.todos.controllers;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/todos")
public class TodosController {
   @Autowired
   private TodoService todoService;

   @PostMapping(value = "/user/{userid}", consumes = {"application/json"})
   public ResponseEntity<?> addNewTodo(@PathVariable Long userid, @Valid @RequestBody Todos newTodo) throws URISyntaxException {
      Todos addTodo = todoService.save(userid, newTodo);
      HttpHeaders responseHeaders = new HttpHeaders();
      URI newTodoURI = ServletUriComponentsBuilder.fromCurrentServletMapping()
              .path("/todos/todo/{todoid}")
              .buildAndExpand(addTodo.getTodoid())
              .toUri();
      responseHeaders.setLocation(newTodoURI);

      return new ResponseEntity<>(null,
              responseHeaders,
              HttpStatus.CREATED);
   }
}
