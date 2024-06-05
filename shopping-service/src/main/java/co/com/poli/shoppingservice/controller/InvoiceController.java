package co.com.poli.shoppingservice.controller;

import co.com.poli.shoppingservice.helper.Response;
import co.com.poli.shoppingservice.helper.ResponseBuild;
import co.com.poli.shoppingservice.persistence.entity.Invoice;
import co.com.poli.shoppingservice.service.InvoiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shoppings")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final ResponseBuild build;

    @PostMapping()
    public Response save(@Valid @RequestBody Invoice invoice, BindingResult result) {
        if (result.hasErrors()) {
            return build.failed(format(result));
        }
        invoiceService.save(invoice);
        return build.success(invoice);
    }

    @DeleteMapping("/{numberInvoice}")
    public Response delete(@PathVariable("numberInvoice") String numberInvoice) {
        Invoice invoice = invoiceService.findByNumberInvoice(numberInvoice);
        if (invoice == null) {
            return build.success("Factura no encontrada");
        }
        invoiceService.delete(invoice);
        return build.success(invoice);
    }

    @GetMapping("/{numberInvoice}")
      public Response getByNumberInvoice(@PathVariable("numberInvoice") String numberInvoice) {
        Invoice invoice = invoiceService.findByNumberInvoice(numberInvoice);
        if (invoice == null) {
            return build.success("Factura no encontrada");
        }
        return build.success(invoice);
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
