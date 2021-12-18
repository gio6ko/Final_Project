package bg.softuni.final_project.service.impl;


import bg.softuni.final_project.model.entity.UserEntity;
import bg.softuni.final_project.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public ShopUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ShopUser loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No such user"));


        return mapToUserDetails(userEntity);
    }

    private static ShopUser mapToUserDetails(UserEntity user) {

        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().name()))
                .collect(Collectors.toList());


        return new ShopUser(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
