package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ClassInfo;
import cc.mrbird.febs.cos.entity.CourseInfo;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.*;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 课程信息 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/course-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseInfoController {

    private final IClassInfoService classInfoService;

    private final ITieInfoService tierInfoService;

    private final IStaffInfoService staffInfoService;

    private final IMajorInfoService majorInfoService;

    private final IStudentInfoService studentInfoService;

    private final ICourseInfoService courseInfoService;

    /**
     * 分页获取课程信息
     *
     * @param page       分页对象
     * @param courseInfo 课程信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<CourseInfo> page, CourseInfo courseInfo) {
        return R.ok(courseInfoService.queryCoursePage(page, courseInfo));
    }

    /**
     * 查询课程详情
     *
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R queryDetail(@PathVariable("id") Integer id) {
        // 返回数据
        CourseInfo courseInfo = courseInfoService.getById(id);
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("courseInfo", courseInfo);
//                put("classInfo", classInfo);
                put("tieInfo", tierInfoService.getById(courseInfo.getTieId()));
                put("staffInfo", staffInfoService.getById(courseInfo.getStaffId()));
                put("majorInfo", majorInfoService.getById(courseInfo.getMajorId()));
//                put("studentInfo", studentInfoService.list(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getClassId, classInfo.getId())));
            }
        };
        return R.ok(result);
    }

    /**
     * 查询课程信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(courseInfoService.getById(id));
    }

    /**
     * 查询课程信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(courseInfoService.list());
    }

    /**
     * 新增课程信息
     *
     * @param courseInfo 课程信息
     * @return 结果
     */
    @PostMapping
    public R save(CourseInfo courseInfo) {
        courseInfo.setCode("COUR-" + System.currentTimeMillis());
        courseInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(courseInfoService.save(courseInfo));
    }

    /**
     * 修改课程信息
     *
     * @param courseInfo 课程信息
     * @return 结果
     */
    @PutMapping
    public R edit(CourseInfo courseInfo) {
        return R.ok(courseInfoService.updateById(courseInfo));
    }

    /**
     * 删除课程信息
     *
     * @param ids ids
     * @return 课程信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(courseInfoService.removeByIds(ids));
    }
}
