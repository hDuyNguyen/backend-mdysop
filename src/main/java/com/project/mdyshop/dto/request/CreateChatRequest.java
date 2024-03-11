package com.project.mdyshop.dto.request;

import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRequest {

    private User user;

    private Shop shop;
}
