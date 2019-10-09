package com.caseStudy.ShopAll.Repository;

import com.caseStudy.ShopAll.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<Users,Long> {
    public Users findByEmail(String email);
    public Users findByEmailAndPassword(String email,String password);


}
