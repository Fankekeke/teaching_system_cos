package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ScheduleElectiveInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 选修课表信息 mapper层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface ScheduleElectiveInfoMapper extends BaseMapper<ScheduleElectiveInfo> {

    /**
     * 分页获取选修课表信息
     *
     * @param page                 分页对象
     * @param scheduleElectiveInfo 选修课表信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryElectivePage(Page<ScheduleElectiveInfo> page, @Param("scheduleElectiveInfo") ScheduleElectiveInfo scheduleElectiveInfo);
}
