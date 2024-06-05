package co.com.poli.productservice.controller;

import co.com.poli.productservice.helper.Response;
import co.com.poli.productservice.helper.ResponseBuild;
import co.com.poli.productservice.persistence.entity.Category;
import co.com.poli.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;
    private final ResponseBuild build;
    @PostMapping
    public Response save(@RequestBody Category category){
        service.save(category);
        return build.success(category);
    }
    @GetMapping
    public Response findAll(){
        return build.success(service.findAll());
    }
}

