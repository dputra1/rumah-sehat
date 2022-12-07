package apap.TA_C_SA_88.RumahSehat.security.services;

import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PasienDb pasienDb;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PasienModel pasienModel = pasienDb.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        System.out.println(pasienModel.getUsername());

        return UserDetailsImpl.build(pasienModel);
    }

}
