package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.PasienModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import java.util.List;
import apap.TA_C_SA_88.RumahSehat.model.TagihanModel;
import apap.TA_C_SA_88.RumahSehat.repository.TagihanDb;

@Service
@Transactional
public class TagihanRestServiceImpl implements TagihanRestService {
    @Autowired
    private TagihanDb tagihanDb;

    @Autowired
    private PasienService pasienService;

    @Override
    public TagihanModel getTagihanByKode(String kode) {
        TagihanModel tagihan = tagihanDb.findByKode(kode);
        return tagihan;
    }

    @Override
    public TagihanModel updateStatusTagihan(TagihanModel newTagihan) {
        TagihanModel tagihan = getTagihanByKode(newTagihan.getKode());
        tagihan.setIsPaid(true);
        tagihan.setTanggalBayar(LocalDateTime.now());
        return tagihanDb.save(tagihan);
    }

	@Override
	public List<TagihanModel> getUserTagihan(String username) {
        PasienModel pasien = pasienService.getPasienByUsername(username);
        String code = pasien.getUuid();
        List<TagihanModel> listTagihan = (List<TagihanModel>) tagihanDb.findTagihanUser(code);
		return listTagihan;
    }
}

