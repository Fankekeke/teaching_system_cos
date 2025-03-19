package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.CourseInfo;
import cc.mrbird.febs.cos.entity.CourseReserveInfo;
import cc.mrbird.febs.cos.entity.ScheduleElectiveInfo;
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
 * 课程预约预约 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/course-reserve-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseReserveInfoController {

    private final ICourseReserveInfoService courseReserveInfoService;

    private final IStudentInfoService studentInfoService;

    private final ICourseInfoService courseInfoService;

    private final IScheduleElectiveInfoService scheduleElectiveInfoService;

    private final IClassInfoService classInfoService;

    private final ITieInfoService tierInfoService;

    private final IStaffInfoService staffInfoService;

    private final IMajorInfoService majorInfoService;

    /**
     * 分页获取课程预约信息
     *
     * @param page              分页对象
     * @param courseReserveInfo 课程预约信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<CourseReserveInfo> page, CourseReserveInfo courseReserveInfo) {
        return R.ok(courseReserveInfoService.queryResevePage(page, courseReserveInfo));
    }

    /**
     * 查询课程预约详情
     *
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R queryDetail(@PathVariable("id") Integer id) {
        // 返回数据
        CourseReserveInfo courseReserveInfo = courseReserveInfoService.getById(id);
        StudentInfo studentInfo = studentInfoService.getById(courseReserveInfo.getStudentId());
        CourseInfo courseInfo = courseInfoService.getById(courseReserveInfo.getCourseId());
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("courseInfo", courseInfo);
                put("classInfo", classInfoService.getById(studentInfo.getClassId()));
                put("tieInfo", tierInfoService.getById(courseInfo.getTieId()));
                put("staffInfo", staffInfoService.getById(courseInfo.getStaffId()));
                put("majorInfo", majorInfoService.getById(courseInfo.getMajorId()));
                put("studentInfo", studentInfo);
            }
        };
        return R.ok(result);
    }

    /**
     * 查询课程预约信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(courseReserveInfoService.getById(id));
    }

    /**
     * 查询课程预约信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(courseReserveInfoService.list());
    }

    /**
     * 新增课程预约信息
     *
     * @param courseReserveInfo 课程预约信息
     * @return 结果
     */
    @PostMapping
    public R save(CourseReserveInfo courseReserveInfo) {
        // 更新学生ID
        StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, courseReserveInfo.getStudentId()));
        if (studentInfo != null) {
            courseReserveInfo.setStudentId(studentInfo.getId());
        }

        // 获取课程信息
        CourseInfo courseInfo = courseInfoService.getById(courseReserveInfo.getCourseId());
        // 获取课表
        ScheduleElectiveInfo scheduleElectiveInfo = scheduleElectiveInfoService.getById(courseReserveInfo.getElectiveId());
        if (DateUtil.parse(scheduleElectiveInfo.getCourseDate() + " " + scheduleElectiveInfo.getStartTime()).getTime() < System.currentTimeMillis()) {
            return R.error("课程预约时间已结束，无法预约");
        }

        // 获取当前已预约的人数
        int count = courseReserveInfoService.count(Wrappers.<CourseReserveInfo>lambdaQuery().eq(CourseReserveInfo::getElectiveId, courseReserveInfo.getElectiveId()).eq(CourseReserveInfo::getStatus, "1"));
        if (count >= courseInfo.getPeopleNum()) {
            return R.error("该课程已满员，无法预约");
        }
        return R.ok(courseReserveInfoService.save(courseReserveInfo));
    }

    /**
     * 审核预约
     *
     * @param courseReserveInfo 课程预约信息
     * @return 结果
     */
    @PutMapping("/auditReserve")
    public R auditReserve(CourseReserveInfo courseReserveInfo) {
        return R.ok(courseReserveInfoService.auditReserve(courseReserveInfo));
    }

    /**
     * 查询课程预约信息
     *
     * @param electiveId 选课ID
     * @return 结果
     */
    @GetMapping("/queryCourseReserveInfo")
    public R queryCourseReserveInfo(Integer electiveId) {
        return R.ok(courseReserveInfoService.queryCourseReserveInfo(electiveId));
    }

    /**
     * 修改课程预约信息
     *
     * @param courseReserveInfo 课程预约信息
     * @return 结果
     */
    @PutMapping
    public R edit(CourseReserveInfo courseReserveInfo) {
        return R.ok(courseReserveInfoService.updateById(courseReserveInfo));
    }

    /**
     * 删除课程预约信息
     *
     * @param ids ids
     * @return 课程预约信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(courseReserveInfoService.removeByIds(ids));
    }
}
