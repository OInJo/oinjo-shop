package kr.idu.OInjo_Shop.entity.Item.Relation;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="category")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @Column(name="categoryId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    @Column( name="categoryName", nullable=false, length=100 )
    private String categoryName;

}
