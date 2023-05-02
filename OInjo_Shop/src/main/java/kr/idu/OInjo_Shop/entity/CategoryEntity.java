package kr.idu.OInjo_Shop.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="category")
@Getter
@Setter
public class CategoryEntity {

    @Id
    @Column(name="categoryId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer categoryId;

    @Column( name="categoryName", nullable=false, length=100 )
    private String categoryName;

}
