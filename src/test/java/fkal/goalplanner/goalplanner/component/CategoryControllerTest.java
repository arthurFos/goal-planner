package fkal.goalplanner.goalplanner.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import fkal.goalplanner.goalplanner.config.Routes;
import fkal.goalplanner.goalplanner.controller.CategoryController;
import fkal.goalplanner.goalplanner.model.bo.CategoryBo;
import fkal.goalplanner.goalplanner.model.dto.CategoryDto;
import fkal.goalplanner.goalplanner.service.CategoryService;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CategoryController.class)
@ActiveProfiles("dev")
public class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private CategoryService categoryService;

    private CategoryDto categoryDto;

    @Before
    public void init() {
        categoryDto = new CategoryDto();
        categoryDto.setId("46db999c-4bd4-11ea-b77f-2e728ce88125");
        categoryDto.setName("Health");
        categoryDto.setDescription("The objectives based on this category related to building a healthy life");
        categoryDto.setGoalDtos(null);
    }

    @Test
    public void should_returnOk_whenValidPath() throws Exception {
        mockMvc.perform(get(Routes.CATEGORIES).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get(Routes.CATEGORY_BY_ID, categoryDto.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenCategory_shouldReturnSameCategory_whenGetCategoryById () throws Exception {
        when(categoryService.fetchOneCategory(categoryDto.getId())).thenReturn(categoryDto);
        MvcResult mvcResult = mockMvc.perform(get(Routes.CATEGORY_BY_ID, categoryDto.getId()))
                .andReturn();

        String response =  mvcResult.getResponse().getContentAsString();

        assertThat(objectMapper.writeValueAsString(categoryDto)).isEqualToIgnoringWhitespace(response);
    }

    @Test
    public void shouldReturnNoFound_WhencategoryIdNotUUID() throws Exception {
        when(categoryService.fetchOneCategory("1")).thenReturn(null);

        MvcResult mvcResult = mockMvc.perform(get(Routes.CATEGORY_BY_ID, "1"))
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
    }
}

