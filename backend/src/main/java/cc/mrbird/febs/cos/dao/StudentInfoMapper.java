package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ScheduleTemplateInfo;
import cc.mrbird.febs.cos.entity.StudentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 学生信息 mapper层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface StudentInfoMapper extends BaseMapper<StudentInfo> {

    /**
     * 分页获取学生信息
     *
     * @param page        分页对象
     * @param studentInfo 学生信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryStudentPage(Page<StudentInfo> page, @Param("studentInfo") StudentInfo studentInfo);

    /**
     * 根据ID查询学生信息
     *
     * @param ids 学生ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryStudentByIds(@Param("ids") List<Integer> ids);
}
