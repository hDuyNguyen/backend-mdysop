package com.project.mdyshop.dto.request;

import com.project.mdyshop.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCartItemRequest {

    private Product product;
    private String size;
    private Long quantity;
    private Long price;
}
