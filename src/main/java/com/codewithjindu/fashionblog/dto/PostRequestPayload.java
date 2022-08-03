package com.codewithjindu.fashionblog.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostRequestPayload {
    private String title;
    private String content;
}
