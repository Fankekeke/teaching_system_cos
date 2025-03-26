package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.StudentInfoMapper;
import cc.mrbird.febs.cos.entity.ClassInfo;
import cc.mrbird.febs.cos.dao.ClassInfoMapper;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.IClassInfoService;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 班级管理 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ClassInfoServiceImpl extends ServiceImpl<ClassInfoMapper, ClassInfo> implements IClassInfoService {

    private final StudentInfoMapper studentInfoMapper;

    /**
     * 分页获取班级信息
     *
     * @param page      分页对象
     * @param classInfo 班级信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryClassPage(Page<ClassInfo> page, ClassInfo classInfo) {
        return baseMapper.queryClassPage(page, classInfo);
    }

    /**
     * 根据班级ID查询学生信息
     *
     * @param classId 班级ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> queryStudentByClassId(Integer classId) {
        List<StudentInfo> studentInfos = studentInfoMapper.selectList(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getClassId, classId));
        if (CollectionUtil.isEmpty(studentInfos)) {
            return Collections.emptyList();
        }
        return studentInfoMapper.queryStudentByIds(studentInfos.stream().map(StudentInfo::getId).collect(Collectors.toList()));
    }
}
