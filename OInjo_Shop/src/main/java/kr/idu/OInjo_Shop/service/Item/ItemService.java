package kr.idu.OInjo_Shop.service.Item;

import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.entity.Item.*;
import kr.idu.OInjo_Shop.repository.Item.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemImgService itemImgService;

    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDTO itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{

        // 상품 등록
        ItemEntity item = itemFormDto.createItem();
        System.out.println("이것도 못했어");
        itemRepository.save(item);
        System.out.println("이건 되네");

        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImgEntity itemImg = new ItemImgEntity();
            itemImg.setItem(item);

            if(i == 0) {
                itemImg.setRepImgYn("Y");
                System.out.println("Y에러");
            } else {
                itemImg.setRepImgYn("N");
                System.out.println("N에러");
            }

            System.out.println("여기까지 왔어");
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
            System.out.println("이놈이 문제네");
        }

        return item.getItemId();
    }

    // ItemService.java

//...

    public Long updateItem(ItemFormDTO itemFormDTO) throws EntityNotFoundException {
        ItemEntity item = itemRepository.findById(itemFormDTO.getId()).orElseThrow(EntityNotFoundException::new);
        // .orElseThrow(Entity..) => itemId에 해당하는 상품이 레포지토리에 없을 경우,
        // EntityNotFoundException 발생
        item.updateItem(itemFormDTO);
        return item.getItemId();
    }

    // 특정 상품 조회
    public ItemEntity itemView(Long id){
        return itemRepository.findById(id).get();
    }

}
