package com.telkom.recruitment.controller;

import com.telkom.recruitment.domain.User;
import com.telkom.recruitment.domain.UserEntry;
import com.telkom.recruitment.repository.RoleRepository;
import com.telkom.recruitment.repository.UserRepository;
import com.telkom.recruitment.service.impl.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository repo;
    @Autowired
    private CounterService counter;
    @Autowired
    private RoleRepository role;



    @RequestMapping(method=RequestMethod.GET,value = "/users")
    public List<User> getAll() {
        return repo.findAll();
    }


    @RequestMapping(method=RequestMethod.GET, value="/users/{id}")
    public User getUser(@PathVariable String id) {
        return repo.findByUserId(id);
    }


    @RequestMapping(method=RequestMethod.POST, value = "/users")
    public User create(@AuthenticationPrincipal @RequestBody UserEntry entry) {
        String uuidConvert = Long.toString(counter.getNextUserIdSequence());
        uuidConvert = ""+ UUID.randomUUID();
        User user = new User(entry);
        user.setUserId(uuidConvert);
        user.setRoleId(role.findByIsDefault(true).getRoleId());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return repo.save(user);
    }


    @RequestMapping(method=RequestMethod.DELETE, value="/users/{id}")
    public void delete(@AuthenticationPrincipal @PathVariable String id) {
        User user = repo.findByUserId(id);
        repo.delete(user.getId());
    }


    @RequestMapping(method=RequestMethod.PUT, value="/users/{id}")
    public User update(@AuthenticationPrincipal @PathVariable String id, @RequestBody UserEntry entry) {
        User update = repo.findByUserId(id);
        update.setEmail(entry.getEmail());
        update.setFullName(entry.getFullName());
        if (entry.getPassword() != "") {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(entry.getPassword());
            update.setPassword(hashedPassword);
        }
        return repo.save(update);
    }
}