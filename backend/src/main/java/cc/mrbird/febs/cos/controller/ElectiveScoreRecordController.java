package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.ElectiveScoreRecord;
import cc.mrbird.febs.cos.service.IElectiveScoreRecordService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 选修课分数记录 控制层
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/elective-score-record")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ElectiveScoreRecordController {

    private final IElectiveScoreRecordService electiveScoreRecordService;

    /**
     * 分页获取选修课分数记录信息
     *
     * @param page                分页对象
     * @param electiveScoreRecord 选修课分数记录信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<ElectiveScoreRecord> page, ElectiveScoreRecord electiveScoreRecord) {
        return R.ok(electiveScoreRecordService.queryScoreRecord(page, electiveScoreRecord));
    }

    /**
     * 查询选修课分数记录信息详情
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(electiveScoreRecordService.getById(id));
    }

    /**
     * 查询选修课分数记录信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(electiveScoreRecordService.list());
    }

    /**
     * 新增选修课分数记录信息
     *
     * @param electiveScoreRecord 选修课分数记录信息
     * @return 结果
     */
    @PostMapping
    public R save(ElectiveScoreRecord electiveScoreRecord) {
        return R.ok(electiveScoreRecordService.save(electiveScoreRecord));
    }

    /**
     * 修改选修课分数记录信息
     *
     * @param electiveScoreRecord 选修课分数记录信息
     * @return 结果
     */
    @PutMapping
    public R edit(ElectiveScoreRecord electiveScoreRecord) {
        return R.ok(electiveScoreRecordService.updateById(electiveScoreRecord));
    }

    /**
     * 删除选修课分数记录信息
     *
     * @param ids ids
     * @return 选修课分数记录信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(electiveScoreRecordService.removeByIds(ids));
    }
}
