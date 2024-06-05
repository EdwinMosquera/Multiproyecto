package co.com.poli.productservice.service;

import co.com.poli.productservice.persistence.entity.Category;
import co.com.poli.productservice.persistence.entity.Product;
import co.com.poli.productservice.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository repository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Product product) {
        repository.save(product);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Product product) {
        repository.delete(product);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return repository.findByCategory(category);
    }
}
