package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ScheduleTemplateInfo;
import cc.mrbird.febs.cos.service.IScheduleTemplateInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 课表模板 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/schedule-template-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleTemplateInfoController {

    private final IScheduleTemplateInfoService scheduleTemplateInfoService;

    /**
     * 分页获取课表模板信息
     *
     * @param page                 分页对象
     * @param scheduleTemplateInfo 课表模板信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ScheduleTemplateInfo> page, ScheduleTemplateInfo scheduleTemplateInfo) {
        return R.ok(scheduleTemplateInfoService.queryTemplatePage(page, scheduleTemplateInfo));
    }

    /**
     * 查询课表模板信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(scheduleTemplateInfoService.getById(id));
    }

    /**
     * 查询课表模板信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(scheduleTemplateInfoService.list());
    }

    /**
     * 新增课表模板信息
     *
     * @param scheduleTemplateInfo 课表模板信息
     * @return 结果
     */
    @PostMapping
    public R save(ScheduleTemplateInfo scheduleTemplateInfo) {
        scheduleTemplateInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(scheduleTemplateInfoService.save(scheduleTemplateInfo));
    }

    /**
     * 修改课表模板信息
     *
     * @param scheduleTemplateInfo 课表模板信息
     * @return 结果
     */
    @PutMapping
    public R edit(ScheduleTemplateInfo scheduleTemplateInfo) {
        return R.ok(scheduleTemplateInfoService.updateById(scheduleTemplateInfo));
    }

    /**
     * 删除课表模板信息
     *
     * @param ids ids
     * @return 课表模板信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(scheduleTemplateInfoService.removeByIds(ids));
    }
}
