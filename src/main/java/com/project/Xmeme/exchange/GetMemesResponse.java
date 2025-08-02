package com.project.Xmeme.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetMemesResponse {
    private String id;
    private String name;
    private String caption;
    private String url;
}
