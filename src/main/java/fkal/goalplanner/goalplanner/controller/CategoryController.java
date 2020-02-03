package fkal.goalplanner.goalplanner.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fkal.goalplanner.goalplanner.model.dto.CategoryDto;
import fkal.goalplanner.goalplanner.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RequestMapping(value = "/category")
@RestController
@RequiredArgsConstructor
@Api(tags = "CategoryController", value = "category")
public class CategoryController {

	private final CategoryService categoryService;
	
	@GetMapping
	@ApiOperation(value = "Returns all existing category")
	public ResponseEntity<List<CategoryDto>> getAllCategorys() {
		
		return ResponseEntity.ok(categoryService.getAllCategorys());
	}
	
	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Returns one category by its id")
	public ResponseEntity<CategoryDto> getCategoryById(
			@ApiParam(value = "Id of the category to return", required = true) @PathVariable(name = "id") String categoryId) {
	
		return ResponseEntity.ok(categoryService.getOneCategory(categoryId));
	}
	
}
