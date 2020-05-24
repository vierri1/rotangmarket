package com.market.rotang.rotangmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @SequenceGenerator(name = "productSeq", sequenceName = "PRODUCT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSeq")
    public Long id;
    public Long categoryId;
    @Column(unique = true)
    public String name;
    public String description;
    public BigDecimal price;
    @OneToMany(mappedBy = "productId")
    public List<ProductImage> images;
}


