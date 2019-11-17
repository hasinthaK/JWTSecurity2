package lk.jwtsecurity.method2.controllers;

import lk.jwtsecurity.method2.models.loginUser;
import lk.jwtsecurity.method2.models.user;
import lk.jwtsecurity.method2.repositories.userRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class userController {

    private static final Logger log = LoggerFactory.getLogger(userController.class);

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private userRepository userRepo;

    @RequestMapping(value = "/getusers", method = RequestMethod.GET)
    public List<user> getUsers(){
        log.info("Current saved users returned");
        return userRepo.findAll();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public user register(@RequestBody @Valid user newUser){
        newUser.set_id(ObjectId.get()); // set new ObjectId to the user
        String pass = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(pass);
        userRepo.save(newUser);
        String logString = "New user saved to Database with username -> "+ newUser.getUsername();
        log.info(logString);
        return new user(null, newUser.getfName(), newUser.getUsername(), null, newUser.getRoles().toString());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createToken(@RequestBody loginUser user){

    }

}

//    Spring boot security JWT implementation method 2.
//
//        http://localhost:8080/login with credentials to aquire a JWT.
//
//        http://localhost:8080/register with credentials to sign up.
