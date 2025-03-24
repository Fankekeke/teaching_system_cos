package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ScheduleClassInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 班级课表信息 mapper层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface ScheduleClassInfoMapper extends BaseMapper<ScheduleClassInfo> {

    /**
     * 分页获取班级课表信息
     *
     * @param page              分页对象
     * @param scheduleClassInfo 班级课表信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> querySchedulePage(Page<ScheduleClassInfo> page, @Param("scheduleClassInfo") ScheduleClassInfo scheduleClassInfo);

    Integer selectDataByMonth(@Param("year") Integer year, @Param("month") Integer month);

    Integer selectAlertByMonth(@Param("year") Integer year, @Param("month") Integer month);

    List<LinkedHashMap<String, Object>> selectDataNumWithinDays(@Param("userId") Integer userId);

    List<LinkedHashMap<String, Object>> selectAlertNumWithinDays(@Param("userId") Integer userId);
}
