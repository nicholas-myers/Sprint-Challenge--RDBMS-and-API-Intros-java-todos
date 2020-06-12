package com.lambdaschool.todos.controllers;

import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
   @Autowired
   private UserService userService;

   @GetMapping(value = "/users",
           produces = {"application/json"})
   public ResponseEntity<?> listAllUsers() {
      List<User> allUsers = userService.findAllUsers();
      return new ResponseEntity<>(allUsers,
              HttpStatus.OK);
   }

   @GetMapping(value = "/user/{userid}",
           produces = {"application/json"})
   public ResponseEntity<?> findUserById(@PathVariable Long userid) {
      User u = userService.findUserById(userid);
      return new ResponseEntity<>(u, HttpStatus.OK);
   }

   @PostMapping(value = "/user", consumes = {"application/json"})
   public ResponseEntity<?> addNewUser(@Valid @RequestBody User newUser)
   {
      newUser.setUserid(0);
      newUser = userService.save(newUser);
      HttpHeaders responseHeaders = new HttpHeaders();
      URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
              .path("/{userid}")
              .buildAndExpand(newUser.getUserid())
              .toUri();
      responseHeaders.setLocation(newUserURI);

      return new ResponseEntity<>(null,
              responseHeaders,
              HttpStatus.CREATED);
   }
}
