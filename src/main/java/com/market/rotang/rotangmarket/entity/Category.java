package com.market.rotang.rotangmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @SequenceGenerator(name = "categorySeq", sequenceName = "CATEGORY_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categorySeq")
    public Long id;
    @Column(unique = true)
    public String name;
    public String description;
    public String image;
}
