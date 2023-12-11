package com.leviethoang.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Comment Dto information"
)
public class CommentDto {
    private Long id;
    @Schema(
            name = "Blog Comment Name"
    )
    @NotEmpty(message = "name can not be empty")
    private String name;
    @Schema(
            name = "Blog Comment Email"
    )
    @NotEmpty(message = "email can not be empty")
    @Email(message = "email invalid. Please try again")
    private String email;
    @Schema(
            name = "Blog Comment Body"
    )
    @NotEmpty(message = "body can not be empty")
    private String body;
}
