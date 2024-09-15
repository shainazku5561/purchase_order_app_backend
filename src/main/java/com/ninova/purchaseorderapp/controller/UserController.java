package com.ninova.purchaseorderapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninova.purchaseorderapp.entity.User;
import com.ninova.purchaseorderapp.service.ApprovalLevelService;
import com.ninova.purchaseorderapp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private ApprovalLevelService approvalLevelService;
    
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
       User userLogin = userService.registerUser(user);
       if(userLogin != null) {
    	   return ResponseEntity.ok("registered successfully.");
       }else {
    	   throw new RuntimeException("Invalid Username or password");
       }
        
    }
    
    @GetMapping("/users/me")
    public List<User> getUsers() {
        return userService.getUsers();
    }
    
    
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody User user) {
        return ResponseEntity.ok(userService.authenticate(user.getUsername(), user.getPassword()));
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
   
    
    @PostMapping("/purchase-orders/{id}/submit-for-approval")
    public void submitForApproval(@PathVariable Long id) {
        approvalLevelService.submitForApproval(id);
    }

   
}
