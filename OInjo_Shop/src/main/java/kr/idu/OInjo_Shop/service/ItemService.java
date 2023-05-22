package kr.idu.OInjo_Shop.service;

import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.entity.Item.*;
import kr.idu.OInjo_Shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;


    public Long saveItem(ItemFormDTO itemFormDto) throws Exception{

        // 상품 등록
        ItemEntity item = itemFormDto.createItem();
        itemRepository.save(item);

        return item.getProductId();
    }

    // ItemService.java

//...

    public Long updateItem(Long itemId, String productName, int productPrice, int productStock, String productStatus,
                           String productDetail, BrandEntity brand, ColorEntity color, SizeEntity size,
                           CategoryEntity category) throws EntityNotFoundException {
        ItemEntity item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        // .orElseThrow(Entity..) => itemId에 해당하는 상품이 레포지토리에 없을 경우,
        // EntityNotFoundException 발생
        item.updateItem(productName, productPrice, productStock, productStatus, productDetail,
                brand, color, size, category);

        return item.getProductId();
    }

}
