package com.project.mdyshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryRequest {

    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;
}

