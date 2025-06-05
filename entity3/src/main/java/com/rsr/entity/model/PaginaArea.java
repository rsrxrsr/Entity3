package com.rsr.entity.model;

import java.util.List;
import jakarta.persistence.Embedded;
import lombok.Data;

@Data
public class PaginaArea {
	List<AreaDto> content;
	@Embedded
	Pagina page;
	
}
