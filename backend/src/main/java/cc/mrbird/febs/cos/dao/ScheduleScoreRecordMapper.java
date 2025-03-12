package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ScheduleScoreRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 课程分数记录 mapper层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface ScheduleScoreRecordMapper extends BaseMapper<ScheduleScoreRecord> {

    /**
     * 分页获取课程分数记录信息
     *
     * @param page                分页对象
     * @param scheduleScoreRecord 课程分数记录信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryShedulePage(Page<ScheduleScoreRecord> page, @Param("scheduleScoreRecord") ScheduleScoreRecord scheduleScoreRecord);
}
