package kr.nzzi.test.ehcache1.service;

import kr.nzzi.test.ehcache1.model.Product;

import java.util.Set;

public interface ProductService {
    Product findOne(Long id);
    Set<Product> findMulti(Set<Long> ids);
}
