package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ScheduleElectiveInfo;
import cc.mrbird.febs.cos.dao.ScheduleElectiveInfoMapper;
import cc.mrbird.febs.cos.service.IScheduleElectiveInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 选修课表信息 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class ScheduleElectiveInfoServiceImpl extends ServiceImpl<ScheduleElectiveInfoMapper, ScheduleElectiveInfo> implements IScheduleElectiveInfoService {

    /**
     * 分页获取选修课表信息
     *
     * @param page                 分页对象
     * @param scheduleElectiveInfo 选修课表信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryElectivePage(Page<ScheduleElectiveInfo> page, ScheduleElectiveInfo scheduleElectiveInfo) {
        return baseMapper.queryElectivePage(page, scheduleElectiveInfo);
    }
}
