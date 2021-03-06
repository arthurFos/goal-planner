package fkal.goalplanner.goalplanner.service;

import fkal.goalplanner.goalplanner.model.bo.CategoryBo;
import fkal.goalplanner.goalplanner.model.bo.GoalBo;
import fkal.goalplanner.goalplanner.model.dao.CategoryRepository;
import fkal.goalplanner.goalplanner.model.dto.CategoryDto;
import fkal.goalplanner.goalplanner.model.dto.GoalDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final ModelMapper mapper = new ModelMapper();
	
	public List<CategoryDto> fetchAllCategories() {

		return categoryRepository.findAll().stream()
				.map(category -> mapCategoryBoToCategoryDto(category, new CategoryDto()))
				.collect(Collectors.toList());
	}

	public CategoryDto fetchOneCategory(String categoryId) throws NotFoundException {
		
		CategoryBo categoryBo = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new NotFoundException("Could not found the category with id -> " + categoryId));
		
		return mapper.map(categoryBo, CategoryDto.class);
	}
	
	private CategoryDto mapCategoryBoToCategoryDto(CategoryBo categoryBo, CategoryDto categoryDto) {
		categoryDto.setId(categoryBo.getCategoryId());
		categoryDto.setDescription(categoryBo.getDescription());
		categoryDto.setName(categoryBo.getName());
		categoryDto.setGoalDtos(categoryBo.getGoalBos().stream()
				.map(goal -> mapper.map(goal, GoalDto.class))
				.collect(Collectors.toList()));
		
		return categoryDto;
	}

	private CategoryBo mapCategoryDtoToCategoryBo(CategoryDto categoryDto, CategoryBo categoryBo) {
		categoryBo.setCategoryId(categoryDto.getId());
		categoryBo.setDescription(categoryDto.getDescription());
		categoryBo.setName(categoryDto.getName());
		categoryBo.setGoalBos(categoryDto.getGoalDtos().stream()
				.map(goal -> mapper.map(goal, GoalBo.class))
				.collect(Collectors.toList()));
		
		return categoryBo;
	}

}
