package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ScheduleClassInfo;
import cc.mrbird.febs.cos.entity.ScheduleScoreRecord;
import cc.mrbird.febs.cos.entity.ScheduleTemplateInfo;
import cc.mrbird.febs.cos.service.IScheduleClassInfoService;
import cc.mrbird.febs.cos.service.IScheduleScoreRecordService;
import cc.mrbird.febs.cos.service.IScheduleTemplateInfoService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    private final IScheduleClassInfoService scheduleClassInfoService;

    private final IScheduleScoreRecordService scheduleScoreRecordService;

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
        scheduleTemplateInfo.setCode("TEM-" + System.currentTimeMillis());
        scheduleTemplateInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(scheduleTemplateInfoService.save(scheduleTemplateInfo));
    }

    /**
     * 批量保存课表信息
     *
     * @return 结果
     */
    @GetMapping("/batchSaveSchedule")
    public R batchSaveSchedule() {
        // 获取所有课表模板信息
        List<ScheduleTemplateInfo> list = scheduleTemplateInfoService.list();
        if (CollectionUtil.isEmpty(list)) {
            throw new RuntimeException("请先添加课表模板信息");
        }
        String date = DateUtil.format(new Date(), "yyyy-MM");
        // 先删除本月课表
        List<ScheduleClassInfo> scheduleClassInfoListMonth = scheduleClassInfoService.list(Wrappers.<ScheduleClassInfo>lambdaQuery().apply("date_format(course_date, '%Y-%m') = date_format(curdate(), '%Y-%m')"));
        scheduleClassInfoService.remove(Wrappers.<ScheduleClassInfo>lambdaQuery().apply("date_format(course_date, '%Y-%m') = date_format(curdate(), '%Y-%m')"));
        // 删除成绩信息
        if (CollectionUtil.isNotEmpty(scheduleClassInfoListMonth)) {
            List<Integer> scheduleIds = scheduleClassInfoListMonth.stream().map(ScheduleClassInfo::getId).collect(Collectors.toList());
            scheduleScoreRecordService.remove(Wrappers.<ScheduleScoreRecord>lambdaQuery().in(ScheduleScoreRecord::getScheduleId, scheduleIds));
        }

        // 待添加的课表信息
        List<ScheduleClassInfo> scheduleClassInfoList = CollectionUtil.newArrayList();

        // 本月日期列表
        List<DateTime> dateList = DateUtil.rangeToList(DateUtil.beginOfMonth(new Date()), DateUtil.endOfMonth(new Date()), DateField.DAY_OF_MONTH);

        // 生成本月课表
        for (ScheduleTemplateInfo scheduleTemplate : list) {
            for (DateTime dateTime : dateList) {
                int weekday = DateUtil.dayOfWeek(dateTime);
                if (scheduleTemplate.getTypeDay().equals(StrUtil.toString(weekday))) {
                    ScheduleClassInfo scheduleClassInfo = new ScheduleClassInfo();
                    scheduleClassInfo.setCourseId(scheduleTemplate.getCourseId());
                    scheduleClassInfo.setClassId(scheduleTemplate.getClassId());
                    scheduleClassInfo.setCourseDate(DateUtil.formatDateTime(dateTime));
                    scheduleClassInfo.setStartTime(scheduleTemplate.getStartTime());
                    scheduleClassInfo.setEndTime(scheduleTemplate.getEndTime());
                    scheduleClassInfoList.add(scheduleClassInfo);
                }
            }
        }
        if (CollectionUtil.isNotEmpty(scheduleClassInfoList)) {
            scheduleClassInfoService.saveBatch(scheduleClassInfoList);
        }
        return R.ok(true);
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
