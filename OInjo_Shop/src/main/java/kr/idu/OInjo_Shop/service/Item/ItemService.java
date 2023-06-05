package kr.idu.OInjo_Shop.service.Item;

import kr.idu.OInjo_Shop.dto.Item.BrandDTO;
import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.entity.Item.*;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.repository.Item.*;
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
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;

    public Long saveItem(ItemFormDTO itemFormDto) throws Exception{

        // 상품 등록
        ItemEntity item = itemFormDto.createItem();
        itemRepository.save(item);

        return item.getProductId();
    }

    public Long saveBrand(BrandDTO brandDTO) throws Exception{

        // 브랜드 등록
        BrandEntity brand = brandDTO.createBrand();
        brandRepository.save(brand);

        return brand.getBrandId();
    }

    public List<BrandDTO> findAllBrand() {
        List<BrandEntity> brandEntityList = brandRepository.findAll();
        // 여기서 findAll()은 repository에서 제공하는 메서드 [List 객체 넘어옴]
        // repository <=> entity로 주고 받기
        List<BrandDTO> brandDTOList = new ArrayList<>();
        for (BrandEntity brandEntity: brandEntityList) {
            // for each문 사용 memberDTOList => DTO 객체를 받음
            brandDTOList.add(BrandDTO.of(brandEntity));
            //entity list 객체를 dto로 변환 후 controller로 넘김
            // 아래와 같이 두줄로 가능
            //MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            //memberDTOList.add(memberDTO);
        }
        return brandDTOList;
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

    // 특정 상품 조회
    public ItemEntity itemView(Long id){
        return itemRepository.findById(id).get();
    }

}
