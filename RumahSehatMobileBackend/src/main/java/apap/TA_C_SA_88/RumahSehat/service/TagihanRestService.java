package apap.TA_C_SA_88.RumahSehat.service;

import apap.TA_C_SA_88.RumahSehat.model.TagihanModel;
import java.util.List;

public interface TagihanRestService {
    List<TagihanModel> getAllTagihan();
    TagihanModel getTagihanByKode(String kode);
    TagihanModel updateStatusTagihan(TagihanModel newTagihan);
    List<TagihanModel> getUserTagihan(String username);
}
