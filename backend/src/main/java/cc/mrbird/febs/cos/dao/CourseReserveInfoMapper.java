package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.CourseReserveInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 课程预约 mapper层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface CourseReserveInfoMapper extends BaseMapper<CourseReserveInfo> {

    /**
     * 分页获取课程预约信息
     *
     * @param page              分页对象
     * @param courseReserveInfo 课程预约信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryResevePage(Page<CourseReserveInfo> page, @Param("courseReserveInfo") CourseReserveInfo courseReserveInfo);

    /**
     * 获取课程预约列表
     *
     * @param studentId 学生ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryScheduleElectiveList(@Param("studentId") Integer studentId);
}
