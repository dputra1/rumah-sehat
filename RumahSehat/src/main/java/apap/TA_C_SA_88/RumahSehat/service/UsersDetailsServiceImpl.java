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

import apap.TA_C_SA_88.RumahSehat.model.UserModel;
import apap.TA_C_SA_88.RumahSehat.repository.*;

@Service
public class UsersDetailsServiceImpl implements UserDetailsService{
    @Autowired
    ApotekerDb apotekerDb;

    @Autowired
    DokterDb dokterDb;

    @Autowired
    PasienDb pasienDb;

    @Autowired
    AdminDb adminDb;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = apotekerDb.findByUsername(username);
        if(user == null) {
            user = dokterDb.findByUsername(username);
        }
        if(user == null) {
            user = pasienDb.findByUsername(username);
        }
        if(user == null) {
            user = adminDb.findByUsername(username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
