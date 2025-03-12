package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ClassInfo;
import cc.mrbird.febs.cos.service.IClassInfoService;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 班级管理 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/class-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClassInfoController {

    private final IClassInfoService classInfoService;

    /**
     * 分页获取班级信息
     *
     * @param page      分页对象
     * @param classInfo 班级信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ClassInfo> page, ClassInfo classInfo) {
        return R.ok(classInfoService.queryClassPage(page, classInfo));
    }

    /**
     * 查询班级信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(classInfoService.getById(id));
    }

    /**
     * 查询班级信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(classInfoService.list());
    }

    /**
     * 新增班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    @PostMapping
    public R save(ClassInfo classInfo) {
        classInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(classInfoService.save(classInfo));
    }

    /**
     * 修改班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    @PutMapping
    public R edit(ClassInfo classInfo) {
        return R.ok(classInfoService.updateById(classInfo));
    }

    /**
     * 删除班级信息
     *
     * @param ids ids
     * @return 班级信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(classInfoService.removeByIds(ids));
    }
}
