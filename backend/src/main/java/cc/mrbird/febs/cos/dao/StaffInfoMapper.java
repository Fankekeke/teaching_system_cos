package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.StaffInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface StaffInfoMapper extends BaseMapper<StaffInfo> {

    /**
     * 分页获取导师信息
     *
     * @param page          分页对象
     * @param staffInfo 导师信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectStaffPage(Page<StaffInfo> page, @Param("staffInfo") StaffInfo staffInfo);

    /**
     * 查询导师课表信息
     *
     * @param staffId 导师ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryScheduleByStaffId1(@Param("staffId") Integer staffId);

    /**
     * 查询导师课表信息
     *
     * @param staffId 导师ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryScheduleByStaffId2(@Param("staffId") Integer staffId);
    /**
     * 查询导师信息
     *
     * @param enterpriseId 校企id
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectStaffList(@Param("enterpriseId") Integer enterpriseId);


    /**
     * 查询导师信息
     *
     * @param enterpriseId 校企id
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectStaffListUser(@Param("enterpriseId") Integer enterpriseId, @Param("staffId") Integer staffId);

    /**
     * 查询导师信息
     *
     * @param ids ids
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> selectStaffListByUserIds(@Param("ids") List<String> ids);
}
