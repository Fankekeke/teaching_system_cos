package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.BulletinInfo;
import cc.mrbird.febs.cos.entity.ScheduleTemplateInfo;
import cc.mrbird.febs.cos.entity.StaffInfo;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.dao.StudentInfoMapper;
import cc.mrbird.febs.cos.service.IBulletinInfoService;
import cc.mrbird.febs.cos.service.IStudentInfoService;
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

/**
 * 学生信息 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoMapper, StudentInfo> implements IStudentInfoService {

    private final IBulletinInfoService bulletinInfoService;

    /**
     * 分页获取学生信息
     *
     * @param page                 分页对象
     * @param studentInfo 学生信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryStudentPage(Page<StudentInfo> page, StudentInfo studentInfo) {
        return baseMapper.queryStudentPage(page, studentInfo);
    }

    /**
     * 查询用户信息详情【公告信息】
     *
     * @param userId 主键ID
     * @return 结果
     */
    @Override
    public LinkedHashMap<String, Object> selectBulletinDetail(Integer userId) {
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("user", null);
                put("bulletin", Collections.emptyList());
            }
        };
        StudentInfo userInfo = this.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, userId));
        if (userInfo == null) {
            return result;
        }
        result.put("user", userInfo);

        // 公告信息
        List<BulletinInfo> bulletinInfoList = bulletinInfoService.list(Wrappers.<BulletinInfo>lambdaQuery().eq(BulletinInfo::getRackUp, "1"));
        result.put("bulletin", bulletinInfoList);
        return result;
    }
}
