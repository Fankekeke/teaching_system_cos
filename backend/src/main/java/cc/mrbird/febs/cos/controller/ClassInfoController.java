package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ClassInfo;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.*;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
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

    private final ITieInfoService tierInfoService;

    private final IStaffInfoService staffInfoService;

    private final IMajorInfoService majorInfoService;

    private final IStudentInfoService studentInfoService;

    private final ICourseInfoService courseInfoService;

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
     * 根据班级ID查询学生信息
     *
     * @param classId 班级ID
     * @return 结果
     */
    @GetMapping("/queryStudentByClassId")
    public R queryStudentByClassId(Integer classId) {
        return R.ok(classInfoService.queryStudentByClassId(classId));
    }

    /**
     * 查询班级详情
     *
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R queryClassDetail(@PathVariable("id") Integer id) {
        // 返回数据
        ClassInfo classInfo = classInfoService.getById(id);
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("classInfo", classInfo);
                put("tieInfo", tierInfoService.getById(classInfo.getTieId()));
                put("staffInfo", staffInfoService.getById(classInfo.getTeacherId()));
                put("majorInfo", majorInfoService.getById(classInfo.getMajorId()));
                put("studentInfo", studentInfoService.list(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getClassId, classInfo.getId())));
//                put("courseInfo", null);
            }
        };
        return R.ok(result);
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
        classInfo.setCode("CLA-" + System.currentTimeMillis());
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
