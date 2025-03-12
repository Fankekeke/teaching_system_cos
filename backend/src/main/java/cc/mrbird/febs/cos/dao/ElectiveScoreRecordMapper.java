package cc.mrbird.febs.cos.dao;

import cc.mrbird.febs.cos.entity.ElectiveScoreRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 选修课分数记录 mapper层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface ElectiveScoreRecordMapper extends BaseMapper<ElectiveScoreRecord> {

    /**
     * 分页获取选修课分数记录信息
     *
     * @param page                分页对象
     * @param electiveScoreRecord 选修课分数记录信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryScoreRecord(Page<ElectiveScoreRecord> page, @Param("electiveScoreRecord") ElectiveScoreRecord electiveScoreRecord);
}
