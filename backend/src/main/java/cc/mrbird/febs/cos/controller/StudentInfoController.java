package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.BulletinInfo;
import cc.mrbird.febs.cos.entity.CourseReserveInfo;
import cc.mrbird.febs.cos.entity.ScheduleClassInfo;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.*;
import cc.mrbird.febs.system.service.UserService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
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
 * 学生信息 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/student-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentInfoController {

    private final IStudentInfoService studentInfoService;

    private final UserService usersService;

    private final IScheduleClassInfoService scheduleClassInfoService;

    private final ICourseReserveInfoService courseReserveInfoService;

    private final IBulletinInfoService bulletinInfoService;

    /**
     * 分页获取学生信息
     *
     * @param page        分页对象
     * @param studentInfo 学生信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<StudentInfo> page, StudentInfo studentInfo) {
        return R.ok(studentInfoService.queryStudentPage(page, studentInfo));
    }

    /**
     * 查询用户信息详情【公告信息】
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/selectBulletinDetail/{id}")
    public R selectBulletinDetail(@PathVariable("id") Integer id) {
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("user", null);
                put("bulletin", Collections.emptyList());
            }
        };
        StudentInfo userInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, id));
        if (userInfo == null) {
            return R.ok(result);
        }
        result.put("user", userInfo);

        if (userInfo.getClassId() == null) {
            return R.ok(Collections.emptyList());
        }
        // 根据班级ID查询课程信息
        List<LinkedHashMap<String, Object>> scheduleList = scheduleClassInfoService.queryScheduleList(userInfo.getClassId());
        // 根据学生ID获取通过预约选课

        List<LinkedHashMap<String, Object>> reserveList = courseReserveInfoService.queryScheduleElectiveList(id);
        result.put("order", CollectionUtil.addAll(scheduleList, reserveList));

        // 公告信息
        List<BulletinInfo> bulletinInfoList = bulletinInfoService.list(Wrappers.<BulletinInfo>lambdaQuery().eq(BulletinInfo::getRackUp, "1"));
        result.put("bulletin", bulletinInfoList);
        return R.ok(result);
    }

    /**
     * 根据用户ID查询学生信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @GetMapping("/queryStudentByUserId")
    public R queryStudentByUserId(Integer userId) {
        return R.ok(studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, userId)));
    }

    /**
     * 查询学生信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(studentInfoService.getById(id));
    }

    /**
     * 查询学生信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(studentInfoService.list());
    }

    /**
     * 根据学生ID查询课程信息
     *
     * @param studentId 学生信息
     * @return 结果
     */
    @GetMapping("/queryCourseByStudentId")
    public R queryCourseByStudentId(Integer studentId) {
        // 获取学生信息
        StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, studentId));
        if (studentInfo == null) {
            return R.ok(Collections.emptyList());
        }
        if (studentInfo.getClassId() == null) {
            return R.ok(Collections.emptyList());
        }
        // 根据班级ID查询课程信息
        List<LinkedHashMap<String, Object>> scheduleList = scheduleClassInfoService.queryScheduleList(studentInfo.getClassId());
        // 根据学生ID获取通过预约选课
        List<LinkedHashMap<String, Object>> reserveList = courseReserveInfoService.queryScheduleElectiveList(studentId);
        return R.ok(CollectionUtil.addAll(scheduleList, reserveList));
    }

    /**
     * 新增学生信息
     *
     * @param studentInfo 学生信息
     * @return 结果
     */
    @PostMapping
    public R save(StudentInfo studentInfo) throws Exception {
        studentInfo.setCode("STU-" + System.currentTimeMillis());
        studentInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        usersService.registStudent(studentInfo.getCode(), "1234qwer", studentInfo);
        return R.ok(true);
    }

    /**
     * 修改学生信息
     *
     * @param studentInfo 学生信息
     * @return 结果
     */
    @PutMapping
    public R edit(StudentInfo studentInfo) {
        return R.ok(studentInfoService.updateById(studentInfo));
    }

    /**
     * 删除学生信息
     *
     * @param ids ids
     * @return 学生信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(studentInfoService.removeByIds(ids));
    }
}
