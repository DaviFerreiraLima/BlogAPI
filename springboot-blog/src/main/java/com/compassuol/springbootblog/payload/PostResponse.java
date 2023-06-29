package com.compassuol.springbootblog.payload;

import com.compassuol.springbootblog.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private int PageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
