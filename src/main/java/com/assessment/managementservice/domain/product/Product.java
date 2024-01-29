package com.assessment.managementservice.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "book_title", nullable = false)
    private String bookTitle;

    @Column(name = "book_price", nullable = false)
    private Double bookPrice;

    @Column(name = "book_quantity", nullable = false)
    private Integer bookQuantity;

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookQuantity=" + bookQuantity +
                '}';
    }
}
