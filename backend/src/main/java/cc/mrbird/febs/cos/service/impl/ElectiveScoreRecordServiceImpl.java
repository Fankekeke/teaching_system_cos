package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.entity.ElectiveScoreRecord;
import cc.mrbird.febs.cos.dao.ElectiveScoreRecordMapper;
import cc.mrbird.febs.cos.service.IElectiveScoreRecordService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 选修课分数记录 实现层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Service
public class ElectiveScoreRecordServiceImpl extends ServiceImpl<ElectiveScoreRecordMapper, ElectiveScoreRecord> implements IElectiveScoreRecordService {

    /**
     * 分页获取选修课分数记录信息
     *
     * @param page                分页对象
     * @param electiveScoreRecord 选修课分数记录信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> queryScoreRecord(Page<ElectiveScoreRecord> page, ElectiveScoreRecord electiveScoreRecord) {
        return baseMapper.queryScoreRecord(page, electiveScoreRecord);
    }

    /**
     * 查询选修课分数记录信息
     *
     * @param electiveId 选修课ID
     * @return 结果
     */
    @Override
    public List<LinkedHashMap<String, Object>> queryElectiveScoreRecord(Integer electiveId) {
        return baseMapper.queryElectiveScoreRecord(electiveId);
    }
}
