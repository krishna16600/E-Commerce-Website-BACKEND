package com.caseStudy.ShopAll.Controller;

import com.caseStudy.ShopAll.Service.UserService;
import com.caseStudy.ShopAll.model.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*")
public class UserController {

    @Autowired
    UserService userS;

    @GetMapping("/getUsers")
    public List<Users> getAllUsers()
   {
       return userS.getUser();
   }
   @GetMapping("/loginUser")
   public String logIn(){
        return "\"logged in\"";
   }

   @GetMapping("/getUsers/{email}")
    public Users getuserById(String email)
   {
       return userS.getUserByEmail(email);
   }

  @PostMapping("/enterUsers")
  public Users addUser(@RequestBody Users user)
  {
      userS.addUserToDB(user);
      System.out.println(user.toString());
      return user;
  }
   @GetMapping("/user/{email}/{password}")
    public Users getNameAndPassword(@PathVariable("email") String email, @PathVariable("password") String password)
   {

       return userS.findByEmailAndPassword(email,password);
   }
}
