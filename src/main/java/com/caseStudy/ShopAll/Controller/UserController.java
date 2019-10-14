package com.caseStudy.ShopAll.Controller;

import com.caseStudy.ShopAll.Service.UserService;
import com.caseStudy.ShopAll.model.Users;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200" , allowedHeaders = "*")
public class UserController {

    @Autowired
    UserService userS;

    @GetMapping("/getUsers")
    public List<Users> getAllUsers() {
        return userS.getUser();
    }

    @GetMapping("/loginUser")
    public String logIn() {
        return "\"logged in\"";
    }

    @GetMapping("/getUsers/{email}")
    public Users getuserById(String email) {
        return userS.getUserByEmail(email);
    }

    @PostMapping("/enterUsers")
    public Users addUser(@RequestBody Users user) {
        userS.addUserToDB(user);
        System.out.println(user.toString());
        return user;
    }

    @GetMapping("/user/{email}/{password}")
    public Users getNameAndPassword(@PathVariable("email") String email, @PathVariable("password") String password) {

        return userS.findByEmailAndPassword(email, password);
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            request.getSession().invalidate();
        }
        return "/home";

    }

    @GetMapping("/getUser")
    public Users getCurrentUser(Principal principal)
    {
       return userS.getuserProfile(principal);
    }
    @PostMapping("/editUser")
    public Users editCurrentUser(@RequestBody Users user)
    {
        return userS.editUserProfile(user);
    }
}
