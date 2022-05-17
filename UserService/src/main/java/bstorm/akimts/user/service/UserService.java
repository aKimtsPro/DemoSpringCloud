package bstorm.akimts.user.service;

import bstorm.akimts.user.dto.UserDTO;
import bstorm.akimts.user.mapper.UserMapper;
import bstorm.akimts.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername( username )
                .orElseThrow( () -> new UsernameNotFoundException("L'utilisateur n'existe pas") );
    }

}
