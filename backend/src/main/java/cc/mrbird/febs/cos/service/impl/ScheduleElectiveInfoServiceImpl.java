package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.StudentInfoMapper;
import cc.mrbird.febs.cos.entity.CourseReserveInfo;
import cc.mrbird.febs.cos.entity.ScheduleElectiveInfo;
import cc.mrbird.febs.cos.dao.ScheduleElectiveInfoMapper;
import cc.mrbird.febs.cos.service.ICourseReserveInfoService;
import cc.mrbird.febs.cos.service.IScheduleElectiveInfoService;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 选修课表信息 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleElectiveInfoServiceImpl extends ServiceImpl<ScheduleElectiveInfoMapper, ScheduleElectiveInfo> implements IScheduleElectiveInfoService {

    private final ICourseReserveInfoService courseReserveInfoService;

    private final StudentInfoMapper studentInfoMapper;

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

    /**
     * 查询选修课表学生列表
     *
     * @param id 主键
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> queryScheduleElectiveStudentList(Integer id) {
        // 获取已审核的选修课信息
        List<CourseReserveInfo> reserveInfoList = courseReserveInfoService.list(Wrappers.<CourseReserveInfo>lambdaQuery()
                .eq(CourseReserveInfo::getElectiveId, id).eq(CourseReserveInfo::getStatus, "1"));
        if (CollectionUtil.isEmpty(reserveInfoList)) {
            return Collections.emptyList();
        }
        List<Integer> studentIds = reserveInfoList.stream().map(CourseReserveInfo::getStudentId).distinct().collect(Collectors.toList());
        return studentInfoMapper.queryStudentByIds(studentIds);
    }
}
