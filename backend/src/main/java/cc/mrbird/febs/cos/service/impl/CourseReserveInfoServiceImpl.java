package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.CourseReserveInfo;
import cc.mrbird.febs.cos.dao.CourseReserveInfoMapper;
import cc.mrbird.febs.cos.service.ICourseReserveInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 课程预约 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class CourseReserveInfoServiceImpl extends ServiceImpl<CourseReserveInfoMapper, CourseReserveInfo> implements ICourseReserveInfoService {

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
}
