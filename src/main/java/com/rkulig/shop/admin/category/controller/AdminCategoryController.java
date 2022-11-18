package com.rkulig.shop.admin.category.controller;

import com.github.slugify.Slugify;
import com.rkulig.shop.admin.category.controller.dto.AdminCategoryDto;
import com.rkulig.shop.admin.category.model.AdminCategory;
import com.rkulig.shop.admin.category.service.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final static Long EMPTY_ID = null;

    private final AdminCategoryService adminCategoryService;

    @GetMapping
    public List<AdminCategory> getCategories(){
        return adminCategoryService.getCategories();
    }

    @GetMapping("/{id}")
    public AdminCategory getCategory(@PathVariable Long id){
        return adminCategoryService.getCategory(id);
    }

    @PostMapping
    public AdminCategory createCategory(@RequestBody AdminCategoryDto adminCategoryDto){
        return adminCategoryService.createCategory(mapToAdminCategory(EMPTY_ID, adminCategoryDto));
    }

    @PutMapping("/{id}")
    public AdminCategory updateCategory(@PathVariable Long id, @RequestBody AdminCategoryDto adminCategoryDto){
        return adminCategoryService.updateCategory(mapToAdminCategory(id, adminCategoryDto));
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        adminCategoryService.deleteCategory(id);
    }

    private AdminCategory mapToAdminCategory(Long id, AdminCategoryDto adminCategoryDto) {
        return AdminCategory.builder()
                .id(id)
                .name(adminCategoryDto.getName())
                .description(adminCategoryDto.getDescription())
                .slug(slugifyCategoryName(adminCategoryDto.getSlug()))
                .build();
    }

    private String slugifyCategoryName(String slug) {
        Slugify slg = Slugify.builder().customReplacement("_","-").build();
        return slg.slugify(slug);
    }


}
