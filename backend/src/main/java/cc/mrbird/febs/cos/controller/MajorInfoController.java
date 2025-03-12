package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.MajorInfo;
import cc.mrbird.febs.cos.service.IMajorInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 专业管理 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/major-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MajorInfoController {

    private final IMajorInfoService majorInfoService;

    /**
     * 分页获取专业管理信息
     *
     * @param page      分页对象
     * @param majorInfo 专业管理信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<MajorInfo> page, MajorInfo majorInfo) {
        return R.ok(majorInfoService.queryMajorPage(page, majorInfo));
    }

    /**
     * 查询专业管理信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(majorInfoService.getById(id));
    }

    /**
     * 查询专业管理信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(majorInfoService.list());
    }

    /**
     * 新增专业管理信息
     *
     * @param majorInfo 专业管理信息
     * @return 结果
     */
    @PostMapping
    public R save(MajorInfo majorInfo) {
        majorInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(majorInfoService.save(majorInfo));
    }

    /**
     * 修改专业管理信息
     *
     * @param majorInfo 专业管理信息
     * @return 结果
     */
    @PutMapping
    public R edit(MajorInfo majorInfo) {
        return R.ok(majorInfoService.updateById(majorInfo));
    }

    /**
     * 删除专业管理信息
     *
     * @param ids ids
     * @return 专业管理信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(majorInfoService.removeByIds(ids));
    }
}
