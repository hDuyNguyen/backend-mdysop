package com.project.mdyshop.dto.request;

import com.project.mdyshop.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddProductToCategoryShop {

    List<Long> productsId;

    Long categoryShopId;

}
