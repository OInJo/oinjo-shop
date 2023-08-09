package kr.idu.OInjo_Shop.service.Item.ItemImg;

import kr.idu.OInjo_Shop.dto.Item.ItemImgDTO;
import kr.idu.OInjo_Shop.entity.Item.ItemImgEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ItemImgService {

    void saveItemImg(ItemImgEntity itemImg, MultipartFile multipartFile) throws IOException;
    void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws IOException;
    List<ItemImgDTO> findAllItemImg();
    List<ItemImgDTO> findItemImgByItemId(Long itemId);
}
