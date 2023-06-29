package com.compassuol.springbootblog.payload;

import com.compassuol.springbootblog.entity.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class CommentDto {

    private long id;

    private String name;

    private String email;

    private String body;
}
