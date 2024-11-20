package com.khlf.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO {
    private String title;
    private List<AuthorDTO> authors;
    private List<String> subjects;
    private Map<String, String> formats;
}