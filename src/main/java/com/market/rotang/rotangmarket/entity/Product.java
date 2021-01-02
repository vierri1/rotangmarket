package com.market.rotang.rotangmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(unique = true)
    private String name;

    private String description;

    private BigDecimal price;

    private Boolean stock;

    @OneToMany(mappedBy = "productId", fetch = FetchType.EAGER)
    private List<ProductImage> images;

    public ProductImage getMainImage() {
        return CollectionUtils.isEmpty(images)
                ? null
                : images.get(0);
    }

    public ProductImage getAltImage() {
        return CollectionUtils.isEmpty(images) || images.size() < 2
                ? null
                : images.get(1);
    }
}


