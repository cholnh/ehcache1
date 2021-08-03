package kr.nzzi.test.ehcache1.service.impl;

import kr.nzzi.test.ehcache1.model.Product;
import kr.nzzi.test.ehcache1.service.ProductService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource(name="productServiceImpl")
    private ProductServiceImpl self;

    @Override
    @Cacheable(cacheNames = "testCache1")
    public Product findOne(Long id) {
        pause(1000);
        return Product.of(id, "product"+id);
    }

    /**
     * self-invocation 발생
     * @Cacheable 은 AOP 를 사용하므로 내부 호출로 인한 self-invocation 발생하여,
     * findOne 메서드의 어드바이스가 실행되지 않음. 즉 Cacheable 이 미작동.
     *
     * self-invocation 해결
     * 자기 자신을 빈으로 주입받아 프록시가 아닌 자신의 실체 메서드를 호출한다.
     * self.findOne 은 프록시를 접근하지 않으므로 어드바이스가 잘 실행된다.
     */
    @Override
    @Cacheable(cacheNames = "testCache1")
    public Set<Product> findMulti(Set<Long> ids) {
        Set<Product> resultSet = new LinkedHashSet<>();
        for (Long id : ids)
            resultSet.add(self.findOne(id));
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
