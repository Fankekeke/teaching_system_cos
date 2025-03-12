package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.TieInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 系管理 service层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface ITieInfoService extends IService<TieInfo> {

    /**
     * 分页获取系管理
     *
     * @param page    分页对象
     * @param tieInfo 系管理
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryTiePage(Page<TieInfo> page, TieInfo tieInfo);
}
