package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import apap.TA_C_SA_88.RumahSehat.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import apap.TA_C_SA_88.RumahSehat.model.TagihanModel;
import apap.TA_C_SA_88.RumahSehat.repository.TagihanDb;

@Service
@Transactional
public class TagihanRestServiceImpl implements TagihanRestService {
    @Autowired
    private TagihanDb tagihanDb;

	@Override
	public List<TagihanModel> getAllTagihan() {
		// TODO Auto-generated method stub
        // Authentication auth = pasienRestService.getAuthentication();
        // SecurityContextHolder.getContext().setAuthentication(auth);
        // UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        // String username = userDetails.getUsername();
        // PasienModel pasien = pasienService.getPasienByUsername(username);
        // System.out.println(pasienService.viewAllPasienAppointment(pasien));
        return tagihanDb.findAll();
	}
    
}
