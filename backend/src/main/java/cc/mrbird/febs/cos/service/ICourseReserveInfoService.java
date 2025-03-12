package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.CourseReserveInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 课程预约 service层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface ICourseReserveInfoService extends IService<CourseReserveInfo> {

    /**
     * 分页获取课程预约信息
     *
     * @param page              分页对象
     * @param courseReserveInfo 课程预约信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryResevePage(Page<CourseReserveInfo> page, CourseReserveInfo courseReserveInfo);
}
