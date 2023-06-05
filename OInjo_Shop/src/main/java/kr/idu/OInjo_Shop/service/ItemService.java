package kr.idu.OInjo_Shop.service;

import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
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

    public Long updateItem(ItemFormDTO itemFormDto) throws Exception{

        // 상품 수정
        ItemEntity item = itemRepository.findById(itemFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);

        return item.getProductId();
    }

    // 특정 상품 조회
    public ItemEntity itemView(Long id){
        return itemRepository.findById(id).get();
    }
}
