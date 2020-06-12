package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class User
{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long userid;

   private String username;
   private String primaryemail;

   @OneToMany(mappedBy = "user",
   cascade = CascadeType.ALL,
   orphanRemoval = true)
   private List<Todos> todos = new ArrayList<>();
}
