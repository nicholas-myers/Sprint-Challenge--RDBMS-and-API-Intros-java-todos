package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "todos")
public class Todos extends Auditable implements Serializable
{


   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long todoid;

   @ManyToOne
   @JoinColumn(name = "userid", nullable = false)
   @JsonIgnoreProperties(value = "todos", allowSetters = true)
   private User user;

   private String description;

   @Transient
   private boolean completed = false;

   public Todos() {
   }

   public Todos(User user, String description) {
      this.user = user;
      this.description = description;
   }

   public long getTodoid() {
      return todoid;
   }

   public void setTodoid(long todoid) {
      this.todoid = todoid;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public boolean isCompleted()
   {
      return completed;
   }

   public void setCompleted(boolean completed)
   {
      this.completed = completed;
   }

   @Override
   public String toString() {
      return "Todos{" +
              "todoid=" + todoid +
              ", description='" + description + '\'' +
              ", user=" + user +
              '}';
   }
}
