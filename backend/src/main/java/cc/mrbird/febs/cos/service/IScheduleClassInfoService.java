package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ScheduleClassInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 班级课表信息 service层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IScheduleClassInfoService extends IService<ScheduleClassInfo> {

    /**
     * 分页获取班级课表信息
     *
     * @param page              分页对象
     * @param scheduleClassInfo 班级课表信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> querySchedulePage(Page<ScheduleClassInfo> page, ScheduleClassInfo scheduleClassInfo);

    /**
     * 获取班级课表信息
     *
     * @param classId 班级ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryScheduleList(Integer classId);

    /**
     * 获取首页数据
     *
     * @return 结果
     */
    LinkedHashMap<String, Object> homeData();
}
