package com.leviethoang.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Post Dto information"
)
public class PostDto {
    private Long id;
    @Schema(
            description = "Blog Post Title"
    )
    @NotEmpty(message = "title can not be empty")
    private String title;
    @Schema(
            description = "Blog Post Description"
    )
    @NotEmpty(message = "description can not be empty")
    private String description;
    @Schema(
            description = "Blog Post Content"
    )
    @NotEmpty(message = "content can not be empty")
    private String content;
    private Set<CommentDto> comments;
    private Long categoryId;
}
