package co.com.poli.productservice.service;

import co.com.poli.productservice.persistence.entity.Category;
import co.com.poli.productservice.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository repository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Category category) {
        repository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }
}
