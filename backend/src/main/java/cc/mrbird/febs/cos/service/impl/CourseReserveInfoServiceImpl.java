package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.StudentInfoMapper;
import cc.mrbird.febs.cos.entity.CourseReserveInfo;
import cc.mrbird.febs.cos.dao.CourseReserveInfoMapper;
import cc.mrbird.febs.cos.entity.NotifyInfo;
import cc.mrbird.febs.cos.service.ICourseReserveInfoService;
import cc.mrbird.febs.cos.service.INotifyInfoService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 课程预约 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseReserveInfoServiceImpl extends ServiceImpl<CourseReserveInfoMapper, CourseReserveInfo> implements ICourseReserveInfoService {

    private final INotifyInfoService notifyInfoService;

    private final StudentInfoMapper studentInfoMapper;

    /**
     * 分页获取课程预约信息
     *
     * @param page              分页对象
     * @param courseReserveInfo 课程预约信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryResevePage(Page<CourseReserveInfo> page, CourseReserveInfo courseReserveInfo) {
        return baseMapper.queryResevePage(page, courseReserveInfo);
    }

    /**
     * 获取课程预约列表
     *
     * @param studentId 学生ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> queryScheduleElectiveList(Integer studentId) {
        return baseMapper.queryScheduleElectiveList(studentId);
    }

    /**
     * 审核预约
     *
     * @param courseReserveInfo 课程预约信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean auditReserve(CourseReserveInfo courseReserveInfo) {
        if ("1".equals(courseReserveInfo.getStatus())) {
            NotifyInfo notifyInfo = new NotifyInfo(courseReserveInfo.getStudentId(), DateUtil.formatDateTime(new Date()), "1", "你好 您预约的课程申请已通过，请前往报道");
            notifyInfoService.save(notifyInfo);
        } else if ("2".equals(courseReserveInfo.getStatus())) {
            NotifyInfo notifyInfo = new NotifyInfo(courseReserveInfo.getStudentId(), DateUtil.formatDateTime(new Date()), "1", "你好 您预约的课程申请已被驳回，请查看详细驳回信息");
            notifyInfoService.save(notifyInfo);
        }
        courseReserveInfo.setAuditDate(DateUtil.formatDateTime(new Date()));
        return this.updateById(courseReserveInfo);
    }

    /**
     * 查询课程预约信息
     *
     * @param electiveId 选课ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> queryCourseReserveInfo(Integer electiveId) {
        // 查询课程预约信息
        List<CourseReserveInfo> list = baseMapper.selectList(Wrappers.<CourseReserveInfo>lambdaQuery().eq(CourseReserveInfo::getElectiveId, electiveId).eq(CourseReserveInfo::getStatus, "1"));
        if (CollectionUtil.isEmpty(list)) {
            return Collections.emptyList();
        }

        // 获取学生ID
        List<Integer> studentIds = list.stream().map(CourseReserveInfo::getStudentId).collect(Collectors.toList());
        return studentInfoMapper.queryStudentByIds(studentIds);
    }

    /**
     * 查询课程预约信息
     *
     * @param studentId 学生ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> queryCourseReserveInfoByStudent(Integer studentId) {
        return null;
    }
}
