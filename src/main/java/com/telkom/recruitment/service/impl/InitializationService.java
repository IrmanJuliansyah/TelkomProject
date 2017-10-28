package com.telkom.recruitment.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.telkom.recruitment.domain.Role;
import com.telkom.recruitment.domain.User;
import com.telkom.recruitment.repository.RoleRepository;
import com.telkom.recruitment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public final class InitializationService implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private CounterService counter;



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (roleRepo.findAll().size() == 0) {
            roleRepo.save(new Role(1L, "ROLE_USER", true));
            roleRepo.save(new Role(2L, "ROLE_ADMIN", false));
            roleRepo.save(new Role(3L, "ROLE_PERUSAHAAN", false));
        }
        if (userRepo.findAll().size() == 0) {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode("123456");
            String uuidConvert = Long.toString(counter.getNextUserIdSequence());
            uuidConvert = ""+ UUID.randomUUID();
            userRepo.save(new User(uuidConvert, "admin","admin@admin.com","","",hashedPassword, 2L));
        }

    }

}
