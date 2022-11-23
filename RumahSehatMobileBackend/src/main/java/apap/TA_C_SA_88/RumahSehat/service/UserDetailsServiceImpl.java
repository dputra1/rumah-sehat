package apap.TA_C_SA_88.RumahSehat.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.repository.PasienDb;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private PasienDb pasienDb;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    PasienModel pasien = pasienDb.findByUsername(username);
    Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
    grantedAuthorities.add(new SimpleGrantedAuthority(pasien.getRole()));
    return new User(pasien.getUsername(), pasien.getPassword(), grantedAuthorities);
  }
  
  
}

