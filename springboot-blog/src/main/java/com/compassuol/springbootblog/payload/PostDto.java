package com.compassuol.springbootblog.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
        private long id;
        @NotEmpty
        @Size(min = 2, message = "Post title shoud have at least 2 characters")
        private String title;

        @NotEmpty
        @Size(min = 10, message = "Post description shoud have at least 10 characters")
        private String description;

        @NotEmpty
        private String content;

        private Set<CommentDto> comments;

        private long categoryId;
    }


