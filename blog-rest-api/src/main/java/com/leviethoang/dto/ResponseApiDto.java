package com.leviethoang.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApiDto {
    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totalPage;
    private long totalElement;
    private boolean isLast;
}
