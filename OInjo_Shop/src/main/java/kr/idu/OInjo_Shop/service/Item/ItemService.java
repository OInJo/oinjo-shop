package kr.idu.OInjo_Shop.service.Item;

import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Page.PageRequestDTO;
import kr.idu.OInjo_Shop.dto.Page.PageResultDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ItemService {
    Long saveItem(ItemFormDTO itemFormDto, List<MultipartFile> itemImgFileList) throws Exception;
    Long updateItem(ItemFormDTO itemFormDTO, List<MultipartFile> itemImgFileList) throws EntityNotFoundException, IOException;
    List<ItemFormDTO> findAllItem();
    ItemFormDTO getItemDetail(Long itemId);
    void deleteItemById(Long id);
    PageResultDTO<ItemFormDTO, Object[]> getAllItemList(PageRequestDTO pageRequestDTO);
}
