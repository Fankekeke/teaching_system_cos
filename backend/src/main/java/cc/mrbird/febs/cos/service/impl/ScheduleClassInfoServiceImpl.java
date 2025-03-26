package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.BulletinInfo;
import cc.mrbird.febs.cos.entity.ScheduleClassInfo;
import cc.mrbird.febs.cos.dao.ScheduleClassInfoMapper;
import cc.mrbird.febs.cos.service.*;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 班级课表信息 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleClassInfoServiceImpl extends ServiceImpl<ScheduleClassInfoMapper, ScheduleClassInfo> implements IScheduleClassInfoService {

    private final IStudentInfoService studentInfoService;

    private final IStaffInfoService staffInfoService;

    private final IScheduleElectiveInfoService scheduleElectiveInfoService;

    private final IBulletinInfoService bulletinInfoService;

    private final IClassInfoService classInfoService;

    private final IMajorInfoService majorInfoService;



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

    /**
     * 获取班级课表信息
     *
     * @param classId 班级ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> queryScheduleList(Integer classId) {
        return baseMapper.queryScheduleList(classId);
    }

    /**
     * 获取首页数据
     *
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> homeData() {

        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("staffNum", 0);
                put("studentNum", 0);
                put("majorNum", 0);
                put("classNum", 0);
            }
        };

        result.put("staffNum", staffInfoService.count());
        result.put("studentNum", studentInfoService.count());
        result.put("majorNum", majorInfoService.count());
        result.put("classNum", classInfoService.count());

        int year = DateUtil.thisYear();
        int month = DateUtil.thisMonth() + 1;
        // 本月班级课表数量
        result.put("monthNum", baseMapper.selectDataByMonth(year, month));
        // 本月选课数量
        result.put("monthAlertNum", baseMapper.selectAlertByMonth(year, month));

        // 本年班级课表数量
        result.put("yearNum", baseMapper.selectDataByMonth(year, null));
        // 本年选课数量
        result.put("yearAlertNum", baseMapper.selectAlertByMonth(year, null));

        // 近十天班级课数量数量
        result.put("numDayList", baseMapper.selectDataNumWithinDays(null));
        // 近十天选课数量
        result.put("alertDayList", baseMapper.selectAlertNumWithinDays(null));
        // 公告信息
        result.put("bulletin", bulletinInfoService.list(Wrappers.<BulletinInfo>lambdaQuery().eq(BulletinInfo::getRackUp, 1)));

        return result;
    }
}
