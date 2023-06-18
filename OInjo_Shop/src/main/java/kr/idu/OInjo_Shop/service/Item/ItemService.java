package kr.idu.OInjo_Shop.service.Item;

import kr.idu.OInjo_Shop.dto.Item.Relation.BrandDTO;
import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.CategoryDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.ColorDTO;
import kr.idu.OInjo_Shop.dto.Item.Relation.SizeDTO;
import kr.idu.OInjo_Shop.entity.Item.*;
import kr.idu.OInjo_Shop.entity.Item.Relation.BrandEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.CategoryEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.ColorEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.SizeEntity;
import kr.idu.OInjo_Shop.repository.Item.*;
import kr.idu.OInjo_Shop.repository.Item.Relation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDTO itemFormDto) throws Exception{

        // 상품 등록
        ItemEntity item = itemFormDto.createItem();
        itemRepository.save(item);

        return item.getProductId();
    }

    // ItemService.java

//...

    public Long updateItem(ItemFormDTO itemFormDTO) throws EntityNotFoundException {
        ItemEntity item = itemRepository.findById(itemFormDTO.getId()).orElseThrow(EntityNotFoundException::new);
        // .orElseThrow(Entity..) => itemId에 해당하는 상품이 레포지토리에 없을 경우,
        // EntityNotFoundException 발생
        item.updateItem(itemFormDTO);
        return item.getProductId();
    }

    // 특정 상품 조회
    public ItemEntity itemView(Long id){
        return itemRepository.findById(id).get();
    }

}
