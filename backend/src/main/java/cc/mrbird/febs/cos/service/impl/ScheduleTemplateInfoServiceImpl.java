package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ScheduleTemplateInfo;
import cc.mrbird.febs.cos.dao.ScheduleTemplateInfoMapper;
import cc.mrbird.febs.cos.service.IScheduleTemplateInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 课表模板 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class ScheduleTemplateInfoServiceImpl extends ServiceImpl<ScheduleTemplateInfoMapper, ScheduleTemplateInfo> implements IScheduleTemplateInfoService {

    /**
     * 分页获取课表模板信息
     *
     * @param page                 分页对象
     * @param scheduleTemplateInfo 课表模板信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryTemplatePage(Page<ScheduleTemplateInfo> page, ScheduleTemplateInfo scheduleTemplateInfo) {
        return baseMapper.queryTemplatePage(page, scheduleTemplateInfo);
    }
}
