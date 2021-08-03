package kr.nzzi.test.ehcache1.model;

import lombok.Getter;

@Getter
public class Product {

    private Long id;
    private String name;

    public static Product of(Long id, String name) {
        Product product = new Product();
        product.id = id;
        product.name = name;
        return product;
    }
}
