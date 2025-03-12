package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.MajorInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 专业管理 mapper层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface MajorInfoMapper extends BaseMapper<MajorInfo> {

    /**
     * 分页获取专业管理信息
     *
     * @param page      分页对象
     * @param majorInfo 专业管理信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryMajorPage(Page<MajorInfo> page, @Param("majorInfo") MajorInfo majorInfo);
}
