package com.example.Resturant.handle.Service;

import com.example.Resturant.handle.Entity.User;
import com.example.Resturant.handle.Entity.UserDetail;
import com.example.Resturant.handle.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDeatilService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUsername(username);

        if(user.isEmpty()){
            System.out.println("username not found");
        }

        return new UserDetail(user.get());
    }
}
