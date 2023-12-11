package com.leviethoang.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Category Dto information"
)
public class CategoryDto {
    private Long id;
    @Schema(
            name = "Blog Category name"
    )
    @NotEmpty(message = "name can not be empty")
    private String name;
    @Schema(
            name = "Blog Category description"
    )
    @NotEmpty(message = "description can not be empty")
    private String description;
    private List<PostDto> posts;
}
