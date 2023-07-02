package kr.idu.OInjo_Shop.service.Item;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Item.ItemImgDTO;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.dto.Page.PageRequestDTO;
import kr.idu.OInjo_Shop.dto.Page.PageResultDTO;
import kr.idu.OInjo_Shop.entity.Item.*;
import kr.idu.OInjo_Shop.entity.Item.Relation.BrandEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.CategoryEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.entity.Member.QMemberEntity;
import kr.idu.OInjo_Shop.repository.Item.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
        itemRepository.save(item);

        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImgEntity itemImg = new ItemImgEntity();
            itemImg.setItem(item);

            if(i == 0) {
                itemImg.setRepImgYn("Y");
            } else {
                itemImg.setRepImgYn("N");
            }
            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getItemId();
    }

    // ItemService.java

    public Long updateItem(ItemFormDTO itemFormDTO, List<MultipartFile> itemImgFileList) throws EntityNotFoundException, IOException {
        // 상품 수정
        ItemEntity item = itemRepository.findById(itemFormDTO.getId()).orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDTO);

        List<Long> itemImgIds = itemFormDTO.getItemImgIds();

        // 이미지 수정
        for (int i = 0; i < itemImgFileList.size(); i++) {
            itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
        }
        return item.getItemId();
    }

    // 특정 상품 조회
    public ItemEntity findItemId(Long id){
        return itemRepository.findById(id).get();
    }

    public List<ItemFormDTO> findAllItem() {
        List<ItemEntity> itemEntityList = itemRepository.findAll();
        List<ItemFormDTO> itemDTOList = new ArrayList<>();
        for (ItemEntity itemEntity: itemEntityList) {
            itemDTOList.add(ItemFormDTO.of(itemEntity));
        }
        return itemDTOList;
    }

    public ItemFormDTO getItemDetail(Long itemId) {

        List<ItemImgEntity> itemImgList = itemImgRepository.findByItemItemId(itemId);
        List<ItemImgDTO> itemImgDTOList = new ArrayList<>();

        for(ItemImgEntity itemImg : itemImgList) {
            ItemImgDTO itemImgDTO = ItemImgDTO.of(itemImg);
            itemImgDTOList.add(itemImgDTO);
        }

        ItemEntity item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);

        ItemFormDTO itemFormDTO = ItemFormDTO.of(item);
        itemFormDTO.setItemImgDTOList(itemImgDTOList);

        return itemFormDTO;
    }

    public void deleteItemById(Long id) {
        itemImgRepository.deleteByItem(id);
        itemRepository.deleteById(id);
    }

    public PageResultDTO<ItemFormDTO, Object[]> getAllItemList(PageRequestDTO pageRequestDTO) {

        //Pageable pageable = pageRequestDTO.getPageable(Sort.by("itemId").descending());
        Page<Object[]> result = itemRepository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageRequestDTO.getPageable(Sort.by("itemId").descending()));
        Function<Object[], ItemFormDTO> fn = (entity -> ItemFormDTO.of((ItemEntity) entity[0]));
        return new PageResultDTO<>(result, fn, 5);
    }

}
