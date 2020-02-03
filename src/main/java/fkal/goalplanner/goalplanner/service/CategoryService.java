package fkal.goalplanner.goalplanner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import fkal.goalplanner.goalplanner.model.dao.CategoryRepository;
import fkal.goalplanner.goalplanner.model.dto.CategoryDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	
	public List<CategoryDto> getAllCategorys() {
		return null;
	}

	public CategoryDto getOneCategory(String categoryId) {
		return null;
	}

}
