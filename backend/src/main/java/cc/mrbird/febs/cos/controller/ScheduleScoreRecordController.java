package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ScheduleScoreRecord;
import cc.mrbird.febs.cos.service.IScheduleScoreRecordService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程分数记录 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/schedule-score-record")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleScoreRecordController {

    private final IScheduleScoreRecordService scheduleScoreRecordService;

    /**
     * 分页获取课程分数记录信息
     *
     * @param page                分页对象
     * @param scheduleScoreRecord 课程分数记录信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ScheduleScoreRecord> page, ScheduleScoreRecord scheduleScoreRecord) {
        return R.ok(scheduleScoreRecordService.queryShedulePage(page, scheduleScoreRecord));
    }

    /**
     * 查询课程分数记录信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(scheduleScoreRecordService.getById(id));
    }

    /**
     * 查询课程分数记录信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(scheduleScoreRecordService.list());
    }

    /**
     * 根据课程ID查询课程分数记录信息
     *
     * @param scheduleId 课程ID
     * @return 结果
     */
    @GetMapping("/queryScheduleScoreRecord")
    public R queryScheduleScoreRecord(Integer scheduleId) {
        return R.ok(scheduleScoreRecordService.queryScheduleScoreRecord(scheduleId));
    }

    /**
     * 新增课程分数记录信息
     *
     * @param scheduleScoreRecord 课程分数记录信息
     * @return 结果
     */
    @PostMapping
    public R save(ScheduleScoreRecord scheduleScoreRecord) {
        return R.ok(scheduleScoreRecordService.save(scheduleScoreRecord));
    }

    /**
     * 修改课程分数记录信息
     *
     * @param scheduleScoreRecord 课程分数记录信息
     * @return 结果
     */
    @PutMapping
    public R edit(ScheduleScoreRecord scheduleScoreRecord) {
        return R.ok(scheduleScoreRecordService.updateById(scheduleScoreRecord));
    }

    /**
     * 删除课程分数记录信息
     *
     * @param ids ids
     * @return 课程分数记录信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(scheduleScoreRecordService.removeByIds(ids));
    }
}
