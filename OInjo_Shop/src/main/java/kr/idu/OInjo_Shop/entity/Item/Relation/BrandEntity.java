package kr.idu.OInjo_Shop.entity.Item.Relation;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="brand")
@Getter
@Setter
public class BrandEntity {

    @Id
    @Column(name="brandId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long brandId;

    @Column( name="brandName", nullable=false, length=100 )
    private String brandName;

}
