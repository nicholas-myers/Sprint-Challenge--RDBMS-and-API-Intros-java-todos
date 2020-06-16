package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "todos")
public class Todos extends Auditable
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

   public Date getCreatedDate(){
      return createdDate;
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
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass())
         return false;
      Todos todos = (Todos) o;
      return getTodoid() == todos.getTodoid() &&
              isCompleted() == todos.isCompleted() &&
              getUser().equals(todos.getUser()) &&
              getDescription().equals(todos.getDescription());
   }

   @Override
   public int hashCode()
   {
      return Objects.hash(getTodoid(), getUser(), getDescription(), isCompleted());
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
