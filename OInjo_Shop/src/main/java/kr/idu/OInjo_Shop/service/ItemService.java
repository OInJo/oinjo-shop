package kr.idu.OInjo_Shop.service;

import kr.idu.OInjo_Shop.dto.ItemFormDto;
import kr.idu.OInjo_Shop.entity.ItemEntity;
import kr.idu.OInjo_Shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Long saveItem(ItemFormDto itemFormDto) throws Exception{

        // 상품 등록
        ItemEntity item = itemFormDto.createItem();
        itemRepository.save(item);

        return item.getId();
    }

    public Long updateItem(ItemFormDto itemFormDto) throws Exception{

        // 상품 수정
        ItemEntity item = itemRepository.findById(itemFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);

        return item.getId();
    }
}
