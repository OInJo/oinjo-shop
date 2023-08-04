package kr.idu.OInjo_Shop.service.Item;

import kr.idu.OInjo_Shop.dto.Item.ItemImgDTO;
import kr.idu.OInjo_Shop.entity.Item.ItemImgEntity;
import kr.idu.OInjo_Shop.repository.Item.ItemImgRepository;
import kr.idu.OInjo_Shop.service.File.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;
import javax.persistence.EntityNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {


    @Value("/Users/minwook/Documents/GitHub/OInjo_Shop/OInjo_Shop/src/main/resources/static/productImg")

    private String itemImgLocation;
    private final ItemImgRepository itemImgRepository;
    private final FileService fileService;

    public void saveItemImg(ItemImgEntity itemImg, MultipartFile multipartFile) throws IOException {
        String oriImgName = multipartFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(itemImgLocation, oriImgName, multipartFile.getBytes());
            imgUrl = "/productImg/" + imgName;
        }

        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);

    }

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws IOException {

        if(!itemImgFile.isEmpty()) {
            ItemImgEntity itemImg = itemImgRepository.findById(itemImgId).orElseThrow(EntityNotFoundException::new);

            // 기존 파일 삭제
            if(!StringUtils.isEmpty(itemImg.getImgName())) {
                fileService.deleteFile(itemImgLocation + "/" + itemImg.getImgName());
            }

            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            String imgUrl = "/productImg/" + imgName;

            itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        }
    }

    public List<ItemImgDTO> findAllItemImg() {
        List<ItemImgEntity> itemImgEntityList = itemImgRepository.findAll();
        List<ItemImgDTO> itemImgDTOList = new ArrayList<>();
        for (ItemImgEntity itemImgEntity: itemImgEntityList) {
            itemImgDTOList.add(ItemImgDTO.of(itemImgEntity));
        }
        return itemImgDTOList;
    }

    public List<ItemImgDTO> findItemImgByItemId(Long itemId) {
        List<ItemImgEntity> itemImgEntityList = itemImgRepository.findByItemItemId(itemId);
        List<ItemImgDTO> itemImgDTOList = new ArrayList<>();
        for (ItemImgEntity itemImgEntity: itemImgEntityList) {
            itemImgDTOList.add(ItemImgDTO.of(itemImgEntity));
        }
        return itemImgDTOList;
    }

    public ItemImgEntity findFirstItemImgByItemId(Long itemId) {
        ItemImgEntity itemImgEntity = itemImgRepository.findFirstByItemItemId(itemId);
        if (itemImgEntity != null) {
            return itemImgEntity;
        } else {
            // 첫 번째 이미지가 없는 경우에 대한 처리를 원하는 경우 추가합니다.
            // 예를 들어, null을 반환하거나 기본 이미지를 반환할 수 있습니다.
            return null;
        }
    }


}