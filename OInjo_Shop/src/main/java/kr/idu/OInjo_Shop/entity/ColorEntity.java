package kr.idu.OInjo_Shop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="color")
@Getter
@Setter
public class ColorEntity {

    @Id
    @Column(name="colorId")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer colorId;

    @Column( name="colorName", nullable=false, length=100 )
    private String colorName;

}
