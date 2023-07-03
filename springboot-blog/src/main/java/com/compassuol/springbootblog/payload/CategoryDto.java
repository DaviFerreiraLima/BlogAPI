package com.compassuol.springbootblog.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {


    private long id;

    @NotEmpty
    @Size(min = 2, message = "Category name should have at least 2 characters")
    private String name;
    @NotEmpty
    @Size(min = 10, message = "Category description should have at least 10 characters")
    private String description;
}
