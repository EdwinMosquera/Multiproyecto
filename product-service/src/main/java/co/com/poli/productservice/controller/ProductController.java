package co.com.poli.productservice.controller;


import co.com.poli.productservice.helper.Response;
import co.com.poli.productservice.helper.ResponseBuild;
import co.com.poli.productservice.persistence.entity.Category;
import co.com.poli.productservice.persistence.entity.Product;
import co.com.poli.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;
    private final ResponseBuild build;
    @PostMapping
    public Response save(@Valid @RequestBody Product product, BindingResult result){
        if (result.hasErrors()) {
            return build.failed(format(result));
        }
        service.save(product);
        return build.success(product);
    }
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable("id") Long id){
        Product product = service.findById(id);
        service.delete(product);
        return build.success(product);
    }
    @GetMapping
    public Response findAll(){
        return build.success(service.findAll());
    }
    @GetMapping("/{id}")
    public Response findById(@PathVariable("id") Long id){
        return build.success(service.findById(id));
    }
    @GetMapping("/category/{id}")
    public Response findByCategory(@PathVariable("id") Long id){
        Category category = new Category();
        category.setId(id);
        return build.success(service.findByCategory(category));
    }

    private List<Map<String, String>> format(BindingResult result){
        return result.getFieldErrors()
                .stream().map(error -> {
                    Map<String, String> err = new HashMap<>();
                    err.put(error.getField(), error.getDefaultMessage());
                    return err;
                }).collect(Collectors.toList());
    }
}
