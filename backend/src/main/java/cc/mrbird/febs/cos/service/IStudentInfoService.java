package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ScheduleTemplateInfo;
import cc.mrbird.febs.cos.entity.StudentInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 学生信息 service层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IStudentInfoService extends IService<StudentInfo> {

    /**
     * 分页获取学生信息
     *
     * @param page                 分页对象
     * @param studentInfo 学生信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryStudentPage(Page<StudentInfo> page, StudentInfo studentInfo);
}
