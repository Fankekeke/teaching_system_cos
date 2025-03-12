package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.CourseReserveInfo;
import cc.mrbird.febs.cos.service.ICourseReserveInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
        return R.ok(courseReserveInfoService.save(courseReserveInfo));
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
