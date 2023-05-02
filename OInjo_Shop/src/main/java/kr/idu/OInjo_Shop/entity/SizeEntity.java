package kr.idu.OInjo_Shop.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="size")
@Getter
@Setter
public class SizeEntity {

    @Id
    @Column(name="sizeId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer sizeId;

    @Column( name="sizeName", nullable=false, length=100 )
    private String sizeName;

}
