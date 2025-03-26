package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.ClassInfo;
import cc.mrbird.febs.cos.entity.StudentInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 班级管理 service层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IClassInfoService extends IService<ClassInfo> {

    /**
     * 分页获取班级信息
     *
     * @param page      分页对象
     * @param classInfo 班级信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryClassPage(Page<ClassInfo> page, ClassInfo classInfo);

    /**
     * 根据班级ID查询学生信息
     *
     * @param classId 班级ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryStudentByClassId(Integer classId);
}
