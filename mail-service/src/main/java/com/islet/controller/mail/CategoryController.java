package com.islet.controller.mail;

import com.islet.common.web.Result;
import com.islet.controller.AbstractController;
import com.islet.domain.dto.mail.CategoryKeywordSaveOrUpdateDTO;
import com.islet.domain.dto.mail.CategorySaveOrUpdateDTO;
import com.islet.domain.vo.mail.CategoryKeywordListVO;
import com.islet.domain.vo.mail.CategoryListVO;
import com.islet.service.mail.ICategoryKeywordService;
import com.islet.service.mail.ICategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tangJM.
 * @since 2021-10-11
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends AbstractController {

    @Resource
    private ICategoryService categoryService;

    @Resource
    private ICategoryKeywordService categoryKeywordService;

    /**
     * 分类列表
     * @return
     * @throws Exception
     */
    @GetMapping("/category_list")
    @ResponseBody
    public Result<List<CategoryListVO>> categoryList() {
        return Result.success(categoryService.categoryList(super.getUserId()));
    }

    /**
     * 新增分类
     * @param dto
     * @return
     */
    @PostMapping("/save_category")
    @ResponseBody
    public Result<Long> saveCategory(@RequestBody @Valid CategorySaveOrUpdateDTO dto) {
        return Result.success(categoryService.saveCategory(dto));
    }

    /**
     * 更新分类
     * @param dto
     * @return
     */
    @PostMapping("/update_category")
    @ResponseBody
    public Result<Boolean> updateCategory(@RequestBody @Valid CategorySaveOrUpdateDTO dto) {
        return Result.success(categoryService.updateCategory(dto));
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @PostMapping("/delete_category")
    @ResponseBody
    public Result<Boolean> deleteCategory(@RequestBody @NotNull(message = "主键不能为空") Long id) {
        return Result.success(categoryService.deleteCategory(id, super.getUserId(), super.getCreateName()));
    }

    /**
     * 分类关键字列表
     */
    @GetMapping("category_keyword_list")
    @ResponseBody
    public Result<List<CategoryKeywordListVO>> categoryKeywordList(@NotNull(message = "主键不能为空") Long id) {
        return Result.success(categoryService.categoryKeywordList(id, super.getUserId()));
    }

    /**
     * 新增分类关键字
     * @param dto
     * @return
     */
    @PostMapping("/save_category_keyword")
    @ResponseBody
    public Result<Long> saveCategoryKeyword(@RequestBody @Valid CategoryKeywordSaveOrUpdateDTO dto) {
        return Result.success(categoryKeywordService.saveCategoryKeyword(dto));
    }

    /**
     * 更新分类关键字
     * @param dto
     * @return
     */
    @PostMapping("/update_category_keyword")
    @ResponseBody
    public Result<Boolean> updateCategory(@RequestBody @Valid CategoryKeywordSaveOrUpdateDTO dto) {
        return Result.success(categoryKeywordService.updateCategoryKeyword(dto));
    }

    /**
     * 删除分类关键字
     * @param id
     * @return
     */
    @PostMapping("/delete_category_keyword")
    @ResponseBody
    public Result<Boolean> deleteCategoryKeyword(@RequestBody @NotNull(message = "主键不能为空") Long id) {
        return Result.success(categoryKeywordService.deleteCategoryKeyword(id, super.getUserId(), super.getCreateName()));
    }

}
