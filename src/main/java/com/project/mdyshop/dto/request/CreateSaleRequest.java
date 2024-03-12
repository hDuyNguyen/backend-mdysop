package com.project.mdyshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSaleRequest {

    private String name;

    private String timeStart;

    private String timeEnd;

    private String description;

    private String discountType;

    private Long discountNumber;
}
