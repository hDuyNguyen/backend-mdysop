package com.project.mdyshop.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.project.mdyshop.config.GrantedAuthorityDeserializer;

@JsonDeserialize(using = GrantedAuthorityDeserializer.class)
public enum Role {
    ADMIN,
    SHOP,
    USER

}
