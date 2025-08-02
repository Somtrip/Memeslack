package com.project.Xmeme.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "memes")
public class MemesEntity {
    @Id
    private String id;
    private String name;
    private String url;
    private String caption;
    private LocalTime createdAt;
}