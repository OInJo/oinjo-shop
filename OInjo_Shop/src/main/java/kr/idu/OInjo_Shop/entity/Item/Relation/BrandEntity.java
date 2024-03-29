package kr.idu.OInjo_Shop.entity.Item.Relation;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="brand")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity {

    @Id
    @Column(name="brandId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long brandId;

    @Column( name="brandName", nullable=false, length=100 )
    private String brandName;

}
