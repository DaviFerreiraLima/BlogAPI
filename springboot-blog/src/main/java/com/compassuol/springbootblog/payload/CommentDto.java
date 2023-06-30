package com.compassuol.springbootblog.payload;

import com.compassuol.springbootblog.entity.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {

    private long id;

    @NotEmpty(message = "Name should not be null or empty")
    private String name;
    @NotEmpty(message = "Email should not be null or empty")
    @Email(message = "The email shoud be valid")
    private String email;

    @NotEmpty(message = "The body should not be empty")
    @Size(min = 10,message = "Comment body must be minimum 10 characters")
    private String body;
}
