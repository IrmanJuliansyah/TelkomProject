package com.telkom.recruitment.controller;

import com.telkom.recruitment.domain.CorporateEntry;
import com.telkom.recruitment.domain.User;
import com.telkom.recruitment.helper.HateoasResource;
import com.telkom.recruitment.helper.ValidationIdHelper;
import com.telkom.recruitment.repository.RoleRepository;
import com.telkom.recruitment.repository.UserRepository;
import com.telkom.recruitment.service.impl.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.UUID;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api")
public class CorporateController {

    @Autowired
    private UserRepository repo;
    @Autowired
    private CounterService counter;
    @Autowired
    private RoleRepository role;

    @GetMapping(value = "/corporate")
    public ResponseEntity<?> getCorporates(){
        HateoasResource hateoasResource = new HateoasResource(repo.findAll());
        hateoasResource.add(linkTo(methodOn(CorporateController.class).getCorporates()).withSelfRel());
        return new ResponseEntity<>(hateoasResource, HttpStatus.OK);
    }

    @GetMapping(value = "/corporate/{id}")
    public User getCorporate(@PathVariable("id") String id){

        return this.repo.findByUserId(id);
    }

    @PostMapping(value = "/corporate")
    public ResponseEntity<?> save(@RequestBody @Valid CorporateEntry corporateEntry){
        String uuidConvert = Long.toString(counter.getNextUserIdSequence());
        uuidConvert = ""+UUID.randomUUID();

        User user = new User(corporateEntry);
        user.setUserId(uuidConvert);
        user.setRoleId(3L);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        User user1 = repo.save(user);

        HateoasResource hateoasResource = new HateoasResource(user1);
        hateoasResource.add(linkTo(methodOn(CorporateController.class).getCorporate(user1.getUserId())).withSelfRel());

        return new ResponseEntity<>(hateoasResource, HttpStatus.CREATED);
    }

    @PutMapping(value = "/corporate/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody @Valid CorporateEntry corporateEntry){
        User update = repo.findByUserId(id);
        update.setFullName(corporateEntry.getFullName());
        update.setNameCorp(corporateEntry.getNameCorp());
        update.setDeskripsiCorp(corporateEntry.getDeskripsiCorp());
        if (corporateEntry.getPassword() != "") {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(corporateEntry.getPassword());
            update.setPassword(hashedPassword);
        }
        User user1 = repo.save(update);
        HateoasResource hateoasResource = new HateoasResource(user1);
        hateoasResource.add(linkTo(methodOn(CorporateController.class).getCorporate(user1.getUserId())).withSelfRel());

        return new ResponseEntity<>(hateoasResource, HttpStatus.OK);
    }

    @DeleteMapping(value = "/corporate/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        User user = repo.findByUserId(id);
        repo.delete(user.getId());
        HateoasResource hateoasResource = new HateoasResource(null);
        return new ResponseEntity<>(hateoasResource, HttpStatus.OK);
    }

}
