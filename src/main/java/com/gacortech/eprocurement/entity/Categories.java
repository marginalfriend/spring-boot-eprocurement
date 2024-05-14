package com.gacortech.eprocurement.entity;

import com.gacortech.eprocurement.constant.Tables;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = Tables.CATEGORIES)
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "category_name")
    private String name;

    @OneToMany(mappedBy = "categoryId", cascade = CascadeType.ALL)
    private List<Products> products;
}
