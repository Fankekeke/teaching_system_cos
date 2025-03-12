package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ScheduleElectiveInfo;
import cc.mrbird.febs.cos.service.IScheduleElectiveInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 选修课表信息 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/schedule-elective-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleElectiveInfoController {

    private final IScheduleElectiveInfoService scheduleElectiveInfoService;

    /**
     * 分页获取选修课表信息
     *
     * @param page                 分页对象
     * @param scheduleElectiveInfo 选修课表信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ScheduleElectiveInfo> page, ScheduleElectiveInfo scheduleElectiveInfo) {
        return R.ok(scheduleElectiveInfoService.queryElectivePage(page, scheduleElectiveInfo));
    }

    /**
     * 查询选修课表信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(scheduleElectiveInfoService.getById(id));
    }

    /**
     * 查询选修课表信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(scheduleElectiveInfoService.list());
    }

    /**
     * 新增选修课表信息
     *
     * @param scheduleElectiveInfo 选修课表信息
     * @return 结果
     */
    @PostMapping
    public R save(ScheduleElectiveInfo scheduleElectiveInfo) {
        return R.ok(scheduleElectiveInfoService.save(scheduleElectiveInfo));
    }

    /**
     * 修改选修课表信息
     *
     * @param scheduleElectiveInfo 选修课表信息
     * @return 结果
     */
    @PutMapping
    public R edit(ScheduleElectiveInfo scheduleElectiveInfo) {
        return R.ok(scheduleElectiveInfoService.updateById(scheduleElectiveInfo));
    }

    /**
     * 删除选修课表信息
     *
     * @param ids ids
     * @return 选修课表信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(scheduleElectiveInfoService.removeByIds(ids));
    }
}
