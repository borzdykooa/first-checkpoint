package com.borzdykooa.wservice;

import com.borzdykooa.converter.UserDetailsConverter;
import com.borzdykooa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserWebServiceImpl implements UserWebService {

    private final UserService userService;
    private final UserDetailsConverter detailsConverter;

    @Autowired
    public UserWebServiceImpl(UserService userService, UserDetailsConverter detailsConverter) {
        this.userService = userService;
        this.detailsConverter = detailsConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return Optional.of(name)
                .map(userService::findByLogin)
                .map(detailsConverter::convert)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist!"));
    }
}
