package com.khlf.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        String title,
        List<AuthorDTO> authors,
        List<String> subjects,
        Map<String, String> formats
) {}
