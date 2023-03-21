package kr.idu.OInjo_Shop.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="category")
@Getter
@Setter
public class categoryEntity {

    @Id
    @Column(name="categoryId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer categoryId;

    @Column( name="categoryName", nullable=false, length=100 )
    private String categoryName;

}
