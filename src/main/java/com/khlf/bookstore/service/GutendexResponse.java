package com.khlf.bookstore.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.khlf.bookstore.dto.BookDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponse {
    private List<BookDTO> results;
}