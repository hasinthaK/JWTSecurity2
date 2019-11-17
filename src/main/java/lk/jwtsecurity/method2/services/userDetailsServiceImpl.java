package lk.jwtsecurity.method2.services;

import lk.jwtsecurity.method2.models.user;
import lk.jwtsecurity.method2.models.userDetailsImpl;
import lk.jwtsecurity.method2.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userDetailsServiceImpl implements UserDetailsService {

    @Autowired
    userRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        user user = userRepo.finduserByUsername(s);

        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new userDetailsImpl(user);
    }
}
