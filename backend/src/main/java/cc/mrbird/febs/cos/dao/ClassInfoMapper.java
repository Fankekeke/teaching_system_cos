package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ClassInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 班级管理 mapper层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface ClassInfoMapper extends BaseMapper<ClassInfo> {

    /**
     * 分页获取班级信息
     *
     * @param page      分页对象
     * @param classInfo 班级信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryClassPage(Page<ClassInfo> page, @Param("classInfo") ClassInfo classInfo);
}
