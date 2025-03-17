package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.TieInfo;
import cc.mrbird.febs.cos.service.ITieInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 系管理 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/tie-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TieInfoController {

    private final ITieInfoService tieInfoService;

    /**
     * 分页获取系管理
     *
     * @param page    分页对象
     * @param tieInfo 系管理
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<TieInfo> page, TieInfo tieInfo) {
        return R.ok(tieInfoService.queryTiePage(page, tieInfo));
    }

    /**
     * 查询系管理详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(tieInfoService.getById(id));
    }

    /**
     * 查询系管理列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(tieInfoService.list());
    }

    /**
     * 新增系管理
     *
     * @param tieInfo 系管理
     * @return 结果
     */
    @PostMapping
    public R save(TieInfo tieInfo) {
        tieInfo.setCode("TIE-" + System.currentTimeMillis());
        tieInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(tieInfoService.save(tieInfo));
    }

    /**
     * 修改系管理
     *
     * @param tieInfo 系管理
     * @return 结果
     */
    @PutMapping
    public R edit(TieInfo tieInfo) {
        return R.ok(tieInfoService.updateById(tieInfo));
    }

    /**
     * 删除系管理
     *
     * @param ids ids
     * @return 系管理
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(tieInfoService.removeByIds(ids));
    }
}
