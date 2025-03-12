package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.MajorInfo;
import cc.mrbird.febs.cos.dao.MajorInfoMapper;
import cc.mrbird.febs.cos.service.IMajorInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

/**
 * 专业管理 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class MajorInfoServiceImpl extends ServiceImpl<MajorInfoMapper, MajorInfo> implements IMajorInfoService {

    /**
     * 分页获取专业管理信息
     *
     * @param page      分页对象
     * @param majorInfo 专业管理信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryMajorPage(Page<MajorInfo> page, MajorInfo majorInfo) {
        return baseMapper.queryMajorPage(page, majorInfo);
    }
}
