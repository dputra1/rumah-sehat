package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.TagihanModel;
import java.util.List;

public interface TagihanRestService {
    List<TagihanModel> getAllTagihan();
    List<TagihanModel> getUserTagihan(String username);
}
