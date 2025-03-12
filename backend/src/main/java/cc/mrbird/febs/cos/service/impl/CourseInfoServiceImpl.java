package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.CourseInfo;
import cc.mrbird.febs.cos.dao.CourseInfoMapper;
import cc.mrbird.febs.cos.service.ICourseInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 课程信息 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class CourseInfoServiceImpl extends ServiceImpl<CourseInfoMapper, CourseInfo> implements ICourseInfoService {

    /**
     * 分页获取课程信息
     *
     * @param page       分页对象
     * @param courseInfo 课程信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryCoursePage(Page<CourseInfo> page, CourseInfo courseInfo) {
        return baseMapper.queryCoursePage(page, courseInfo);
    }
}
