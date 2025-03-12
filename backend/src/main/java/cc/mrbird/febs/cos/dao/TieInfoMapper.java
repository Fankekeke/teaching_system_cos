package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.TieInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 系管理 mapper层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface TieInfoMapper extends BaseMapper<TieInfo> {

    /**
     * 分页获取系管理
     *
     * @param page    分页对象
     * @param tieInfo 系管理
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryTiePage(Page<TieInfo> page, @Param("tieInfo") TieInfo tieInfo);
}
