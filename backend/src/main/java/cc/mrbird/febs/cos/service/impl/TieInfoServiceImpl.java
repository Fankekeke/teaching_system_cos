package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.TieInfo;
import cc.mrbird.febs.cos.dao.TieInfoMapper;
import cc.mrbird.febs.cos.service.ITieInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 系管理 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class TieInfoServiceImpl extends ServiceImpl<TieInfoMapper, TieInfo> implements ITieInfoService {

    /**
     * 分页获取系管理
     *
     * @param page    分页对象
     * @param tieInfo 系管理
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryTiePage(Page<TieInfo> page, TieInfo tieInfo) {
        return baseMapper.queryTiePage(page, tieInfo);
    }
}
