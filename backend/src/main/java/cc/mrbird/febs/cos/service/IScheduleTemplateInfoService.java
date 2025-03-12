package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ScheduleTemplateInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 课表模板 service层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IScheduleTemplateInfoService extends IService<ScheduleTemplateInfo> {

    /**
     * 分页获取课表模板信息
     *
     * @param page                 分页对象
     * @param scheduleTemplateInfo 课表模板信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryTemplatePage(Page<ScheduleTemplateInfo> page, ScheduleTemplateInfo scheduleTemplateInfo);
}
