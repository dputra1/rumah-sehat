package apap.TA_C_SA_88.RumahSehat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.TA_C_SA_88.RumahSehat.model.TagihanModel;
import apap.TA_C_SA_88.RumahSehat.repository.TagihanDb;

@Service
@Transactional
public class TagihanServiceImpl implements TagihanRestService {
    @Autowired
    TagihanDb tagihanDb;

    @Override
    public List<TagihanModel> getAllTagihan() {
        // TODO Auto-generated method stub
        return tagihanDb.findAll();
    }
    
}
