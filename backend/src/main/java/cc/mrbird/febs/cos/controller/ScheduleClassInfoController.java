package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.*;
import cc.mrbird.febs.cos.service.*;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 班级课表信息 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/schedule-class-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleClassInfoController {

    private final IScheduleClassInfoService scheduleClassInfoService;

    private final IStudentInfoService studentInfoService;

    private final ICourseInfoService courseInfoService;

    private final IScheduleElectiveInfoService scheduleElectiveInfoService;

    private final IClassInfoService classInfoService;

    private final ITieInfoService tierInfoService;

    private final IStaffInfoService staffInfoService;

    private final IMajorInfoService majorInfoService;

    /**
     * 分页获取班级课表信息
     *
     * @param page              分页对象
     * @param scheduleClassInfo 班级课表信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ScheduleClassInfo> page, ScheduleClassInfo scheduleClassInfo) {
        return R.ok(scheduleClassInfoService.querySchedulePage(page, scheduleClassInfo));
    }

    /**
     * 查询班级课表详情
     *
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R queryDetail(@PathVariable("id") Integer id) {
        // 返回数据
        ScheduleClassInfo scheduleClassInfo = scheduleClassInfoService.getById(id);
        CourseInfo courseInfo = courseInfoService.getById(scheduleClassInfo.getCourseId());
        ClassInfo classInfo = classInfoService.getById(scheduleClassInfo.getClassId());
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("scheduleClassInfo", scheduleClassInfo);
                put("courseInfo", courseInfo);
                put("classInfo", classInfo);
                put("tieInfo", tierInfoService.getById(courseInfo.getTieId()));
                put("staffInfo", staffInfoService.getById(courseInfo.getStaffId()));
                put("majorInfo", majorInfoService.getById(courseInfo.getMajorId()));
                put("studentInfo", studentInfoService.list(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getClassId, classInfo.getId())));
            }
        };
        return R.ok(result);
    }

    /**
     * 查询班级课表信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(scheduleClassInfoService.getById(id));
    }

    /**
     * 查询班级课表信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(scheduleClassInfoService.list());
    }

    /**
     * 新增班级课表信息
     *
     * @param scheduleClassInfo 班级课表信息
     * @return 结果
     */
    @PostMapping
    public R save(ScheduleClassInfo scheduleClassInfo) {
        return R.ok(scheduleClassInfoService.save(scheduleClassInfo));
    }

    /**
     * 修改班级课表信息
     *
     * @param scheduleClassInfo 班级课表信息
     * @return 结果
     */
    @PutMapping
    public R edit(ScheduleClassInfo scheduleClassInfo) {
        return R.ok(scheduleClassInfoService.updateById(scheduleClassInfo));
    }

    /**
     * 删除班级课表信息
     *
     * @param ids ids
     * @return 班级课表信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(scheduleClassInfoService.removeByIds(ids));
    }
}
