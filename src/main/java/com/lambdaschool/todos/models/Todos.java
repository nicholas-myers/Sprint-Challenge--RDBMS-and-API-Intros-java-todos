package com.lambdaschool.todos.models;

import javax.persistence.*;

public class Todos
{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long todoid;

   private String description;
   private boolean completed;

   @ManyToOne
   @JoinColumn(name = "todos", nullable = false)
   private User user;
}
