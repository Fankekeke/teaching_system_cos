package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ScheduleClassInfo;
import cc.mrbird.febs.cos.dao.ScheduleClassInfoMapper;
import cc.mrbird.febs.cos.service.IScheduleClassInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 班级课表信息 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class ScheduleClassInfoServiceImpl extends ServiceImpl<ScheduleClassInfoMapper, ScheduleClassInfo> implements IScheduleClassInfoService {

    /**
     * 分页获取班级课表信息
     *
     * @param page              分页对象
     * @param scheduleClassInfo 班级课表信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> querySchedulePage(Page<ScheduleClassInfo> page, ScheduleClassInfo scheduleClassInfo) {
        return baseMapper.querySchedulePage(page, scheduleClassInfo);
    }
}
