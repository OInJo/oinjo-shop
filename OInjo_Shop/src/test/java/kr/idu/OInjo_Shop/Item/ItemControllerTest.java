package kr.idu.OInjo_Shop.Item;

import kr.idu.OInjo_Shop.constant.ItemSellStatus;
import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
import kr.idu.OInjo_Shop.entity.Item.ItemImgEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.BrandEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.CategoryEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.ColorEntity;
import kr.idu.OInjo_Shop.entity.Item.Relation.SizeEntity;
import kr.idu.OInjo_Shop.entity.Member.MemberEntity;
import kr.idu.OInjo_Shop.repository.Item.ItemImgRepository;
import kr.idu.OInjo_Shop.repository.Item.ItemRepository;
import kr.idu.OInjo_Shop.repository.Item.Relation.BrandRepository;
import kr.idu.OInjo_Shop.repository.Item.Relation.CategoryRepository;
import kr.idu.OInjo_Shop.repository.Item.Relation.ColorRepository;
import kr.idu.OInjo_Shop.repository.Item.Relation.SizeRepository;
import kr.idu.OInjo_Shop.service.Item.ItemImgService;
import kr.idu.OInjo_Shop.service.Item.ItemService;
import kr.idu.OInjo_Shop.service.Item.RelationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;

import java.util.stream.IntStream;

@SpringBootTest
public class ItemControllerTest {

    @Autowired
    ItemService itemService; // 아이템 및 아이템 이미지

    @Autowired
    ItemImgService itemImgService;

    @Autowired
    RelationService relationService; // 카테고리, 브랜드, 컬러, 사이즈

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemImgRepository itemImgRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    SizeRepository sizeRepository;

    @Test
    void saveBrandTest() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            BrandEntity brand = BrandEntity.builder()
                    .brandName("OInJoBrand" + i)
                    .build();
            brandRepository.save(brand);
        });
    }

    @Test
    void saveCategoryTest() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            CategoryEntity category = CategoryEntity.builder()
                    .categoryName("OInJoCategory" + i)
                    .build();
            categoryRepository.save(category);
        });
    }

    @Test
    void saveColorTest() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            ColorEntity color = ColorEntity.builder()
                    .colorName("OInJoColor" + i)
                    .build();
            colorRepository.save(color);
        });
    }

    @Test
    void saveSizeTest() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            SizeEntity size = SizeEntity.builder()
                    .sizeName("OInJoSize" + i)
                    .build();
            sizeRepository.save(size);
        });
    }

    @Test
    void saveItemTest() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            ItemEntity item = ItemEntity.builder()
                    .itemDetail("OInJoDetail"+i)
                    .itemStock(i)
                    .itemPrice(10000+i)
                    .itemName("OInJoItem"+i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .build();
            itemRepository.save(item);
        }); // 브랜드,카테고리,컬러,사이즈는 null
    }

    @Test
    void saveItemImgTest() {
        IntStream.rangeClosed(1, 3).forEach(i -> {
            ItemImgEntity itemImg = ItemImgEntity.builder()
                    .imgName("OInJoImgName"+i)
                    .oriName("OInJoImgOriName"+i)
                    .imgUrl("OInjoUrl"+i)
                    .repImgYn("Y")
                    .build();
            itemImgRepository.save(itemImg);
        }); // 아이템 ID는 null
    }


}




