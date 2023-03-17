package kr.idu.OInjo_Shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="product")
@Getter
@Setter
@ToString
public class ItemEntity {

    @Id
    @Column(name="productId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // 상품 코드

    @Column
    private String productName;

    @Column
    private long price;

    @Column
    private String status;

    @Column
    private String contents;

    @Column
    private Date date;

    @Column
    private String opt1;

    @Column
    private String opt2;

    @OneToOne
    @Column
    private category categoryId;

}
