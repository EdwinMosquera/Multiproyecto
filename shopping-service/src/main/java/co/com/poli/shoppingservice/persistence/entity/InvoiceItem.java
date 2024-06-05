package co.com.poli.shoppingservice.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "invoice_items")
@Getter
@Setter
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive
    private Double quantity;
    @Positive
    private Double price;
    private Long productId;
    @Transient
    @Positive
    private Double subTotal;

    public Double getSubTotal(){
        if(this.price>0 && this.quantity>0){
            return this.quantity*this.price;
        }
        return (double) 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItem that = (InvoiceItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
