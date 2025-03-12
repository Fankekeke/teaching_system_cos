package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.MajorInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * 专业管理 service层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
public interface IMajorInfoService extends IService<MajorInfo> {

    /**
     * 分页获取专业管理信息
     *
     * @param page      分页对象
     * @param majorInfo 专业管理信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryMajorPage(Page<MajorInfo> page, MajorInfo majorInfo);
}
