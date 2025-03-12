package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ScheduleScoreRecord;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 课程分数记录 service层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IScheduleScoreRecordService extends IService<ScheduleScoreRecord> {

    /**
     * 分页获取课程分数记录信息
     *
     * @param page                分页对象
     * @param scheduleScoreRecord 课程分数记录信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryShedulePage(Page<ScheduleScoreRecord> page, ScheduleScoreRecord scheduleScoreRecord);
}
