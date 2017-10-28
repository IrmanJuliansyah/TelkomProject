package com.telkom.recruitment.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.telkom.recruitment.domain.Role;
import com.telkom.recruitment.domain.User;
import com.telkom.recruitment.repository.RoleRepository;
import com.telkom.recruitment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s does not exist!", email));
        }
        return new UserRepositoryUserDetails(user);
    }

    private final class UserRepositoryUserDetails extends User implements UserDetails {

        private static final long serialVersionUID = 1L;

        private UserRepositoryUserDetails(User user) {
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<Role> roles = new ArrayList<Role>();
            roles.add(repo.findByRoleId(getRoleId()));
            return roles;
        }

        @Override
        public String getUsername() {
            return super.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

    }
}
