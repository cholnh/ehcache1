package kr.nzzi.test.ehcache1.service.impl;

import kr.nzzi.test.ehcache1.model.Product;
import kr.nzzi.test.ehcache1.service.ProductService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    @Cacheable(cacheNames = "testCache1")
    public Product findOne(Long id) {
        pause(1000);
        return Product.of(id, "product"+id);
    }

    @Override
    @Cacheable(cacheNames = "testCache1")
    public Set<Product> findMulti(Set<Long> ids) {

        Set<Product> resultSet = new LinkedHashSet<>();

        for (Long id : ids) {
            resultSet.add(findOne(id));
        }

        return resultSet;
    }

    private void pause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
