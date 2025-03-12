package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ScheduleScoreRecord;
import cc.mrbird.febs.cos.dao.ScheduleScoreRecordMapper;
import cc.mrbird.febs.cos.service.IScheduleScoreRecordService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 课程分数记录 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class ScheduleScoreRecordServiceImpl extends ServiceImpl<ScheduleScoreRecordMapper, ScheduleScoreRecord> implements IScheduleScoreRecordService {

    /**
     * 分页获取课程分数记录信息
     *
     * @param page                分页对象
     * @param scheduleScoreRecord 课程分数记录信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryShedulePage(Page<ScheduleScoreRecord> page, ScheduleScoreRecord scheduleScoreRecord) {
        return baseMapper.queryShedulePage(page, scheduleScoreRecord);
    }
}
