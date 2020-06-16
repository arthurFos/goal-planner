package fkal.goalplanner.goalplanner.controller;

import fkal.goalplanner.goalplanner.model.dto.CategoryDto;
import fkal.goalplanner.goalplanner.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/categories")
@RestController
@RequiredArgsConstructor
@Api(tags = "CategoryController", value = "categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @ApiOperation("Returns all existing categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {

        return ResponseEntity.ok(categoryService.fetchAllCategories());
    }

    @GetMapping("/{id}")
	@ApiOperation("Returns a category by its id")
    public ResponseEntity<CategoryDto> getCategoryById(
            @ApiParam(value = "categoryId", required = true) @PathVariable("id")
                    String categoryId) throws NotFoundException {

        return ResponseEntity.ok(categoryService.fetchOneCategory(categoryId));
    }
}
