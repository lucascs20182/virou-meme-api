package org.serratec.viroumemeapi.security;

import java.util.Optional;

import org.serratec.viroumemeapi.entities.ClienteEntity;
import org.serratec.viroumemeapi.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

	@Autowired
	ClienteRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<ClienteEntity> cliente = repository.findByUsername(username);
		if (cliente.isEmpty()) {
			System.out.println("Cliente Not Found");
			return null;
		}
		return new UserSS(cliente.get().getId(), cliente.get().getUsername(), cliente.get().getSenha());
	}

}
