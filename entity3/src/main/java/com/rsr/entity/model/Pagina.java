package com.rsr.entity.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Pagina {
	int size;
	long totalElements;
	int totalPages;
	int number;
}
