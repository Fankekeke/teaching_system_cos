package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ScheduleClassInfo;
import cc.mrbird.febs.cos.entity.ScheduleScoreRecord;
import cc.mrbird.febs.cos.service.IScheduleClassInfoService;
import cc.mrbird.febs.cos.service.IScheduleScoreRecordService;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    private final IScheduleClassInfoService scheduleClassInfoService;

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
     * 保存课程分数记录
     *
     * @param scheduleScoreRecord 课程分数记录
     * @return 结果
     */
    @PostMapping("/batchSaveScore")
    @Transactional(rollbackFor = Exception.class)
    public R batchSaveScore(ScheduleScoreRecord scheduleScoreRecord) throws FebsException {
        if (StrUtil.isEmpty(scheduleScoreRecord.getScoreDataStr())) {
            throw new FebsException("学生成绩不能为空");
        }
        // 获取课表信息
        ScheduleClassInfo scheduleClassInfo = scheduleClassInfoService.getById(scheduleScoreRecord.getScheduleId());
        scheduleClassInfo.setStatus("1");
        scheduleClassInfoService.updateById(scheduleClassInfo);
        List<ScheduleScoreRecord> recordList = JSONUtil.toList(scheduleScoreRecord.getScoreDataStr(), ScheduleScoreRecord.class);
        return R.ok(scheduleScoreRecordService.saveBatch(recordList));
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
