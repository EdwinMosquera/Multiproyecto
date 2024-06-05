package co.com.poli.customerservice.controller;

import co.com.poli.customerservice.helper.Response;
import co.com.poli.customerservice.helper.ResponseBuild;
import co.com.poli.customerservice.persistence.entity.Customer;
import co.com.poli.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final ResponseBuild build;

    @PostMapping
    public Response save(@Valid @RequestBody Customer customer, BindingResult result){
        if (result.hasErrors()) {
            return build.failed(format(result));
        }
        customerService.save(customer);
        return build.success(customer);
    }
    @DeleteMapping("/{nit}")
    public Response delete(@PathVariable("nit") String nit){
        Customer customer = (Customer) findByNit(nit).getData();
        if(customer==null){
            return build.success("El cliente no existe");
        }
        customerService.delete(customer);
        return build.success(customer);
    }

    @GetMapping
    public Response findAll(){
        return build.success(customerService.findAll());
    }

    @GetMapping("/nit/{nit}")
    public Response findByNit(@PathVariable("nit") String nit){
        return build.success(customerService.findByNit(nit));
    }

    @GetMapping("/{id}")
    public Response findByID(@PathVariable("id") Long id){
        return build.success(customerService.findById(id));
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
