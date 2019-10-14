package com.caseStudy.ShopAll.Service;

import com.caseStudy.ShopAll.Repository.userRepository;
import com.caseStudy.ShopAll.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

@Component
public class UserService {
    @Autowired
    userRepository repo;

    public List<Users> getUser()
    {
        return repo.findAll();
    }
    public Users getUserByEmail(String email)
    {
        return repo.findByEmail(email);
    }

    public Users findByEmailAndPassword(String email,String password)
    {
        return repo.findByEmailAndPassword(email, password);
    }

    public String addUserToDB(Users user)
    {
        repo.save(user);
        return "added";
    }
    public Users findByUserId(Long userId)
    {
         return  repo.findByUserId(userId);
    }
    public Users getuserProfile(Principal principal){
            return repo.findByEmail(principal.getName());
    }
    public Users editUserProfile(Users user){
        Users ouser = repo.findByUserId(user.getUserId());
        ouser.setUserId(user.getUserId());
        ouser.setAddress(user.getAddress());
        ouser.setEmail(user.getEmail());
        ouser.setMobile(user.getMobile());
        ouser.setPassword(user.getPassword());
        ouser.setName(user.getName());
        repo.saveAndFlush(ouser);
        return ouser;
    }
}
