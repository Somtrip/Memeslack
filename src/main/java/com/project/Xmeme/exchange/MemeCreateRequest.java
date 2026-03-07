package com.project.Xmeme.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemeCreateRequest {
    private String name;
    private String caption;
    private String url;
}
