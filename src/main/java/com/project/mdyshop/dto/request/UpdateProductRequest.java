package com.project.mdyshop.dto.request;

import com.project.mdyshop.model.Size;
import com.project.mdyshop.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {

    private String name;

    private String description;

    private String imageUrl;

    private Set<Size> sizes = new HashSet<>();

    private List<Tag> tags;
}
