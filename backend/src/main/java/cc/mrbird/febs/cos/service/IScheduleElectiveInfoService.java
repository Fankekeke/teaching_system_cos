package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ScheduleElectiveInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 选修课表信息 service层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IScheduleElectiveInfoService extends IService<ScheduleElectiveInfo> {

    /**
     * 分页获取选修课表信息
     *
     * @param page                 分页对象
     * @param scheduleElectiveInfo 选修课表信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryElectivePage(Page<ScheduleElectiveInfo> page, ScheduleElectiveInfo scheduleElectiveInfo);

    /**
     * 查询选修课表学生列表
     *
     * @param id 主键
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryScheduleElectiveStudentList(Integer id);
}
