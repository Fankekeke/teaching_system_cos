package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.NotifyInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
public interface INotifyInfoService extends IService<NotifyInfo> {

    /**
     * 分页获取消息通知信息
     *
     * @param page       分页对象
     * @param notifyInfo 消息通知信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> queryNotifyPage(Page<NotifyInfo> page, NotifyInfo notifyInfo);

    /**
     * 添加消息通知
     *
     * @param userId  用户ID
     * @param content 内容
     * @return 结果
     */
    boolean addNotify(Integer userId, String content);

    /**
     * 根据用户ID获取消息通知信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryNotifyByUser(Integer userId);
}
