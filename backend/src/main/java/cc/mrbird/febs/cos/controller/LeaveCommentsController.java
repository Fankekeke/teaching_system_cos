package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.LeaveComments;
import cc.mrbird.febs.cos.entity.StaffInfo;
import cc.mrbird.febs.cos.entity.StudentInfo;
import cc.mrbird.febs.cos.service.ILeaveCommentsService;
import cc.mrbird.febs.cos.service.IStaffInfoService;
import cc.mrbird.febs.cos.service.IStudentInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/leave-comments")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LeaveCommentsController {

    private final ILeaveCommentsService leaveCommentsService;

    private final IStaffInfoService staffInfoService;

    private final IStudentInfoService studentInfoService;


    /**
     * 分页获取留言信息
     *
     * @param page          分页对象
     * @param leaveComments 留言信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<LeaveComments> page, LeaveComments leaveComments) {
        return R.ok(leaveCommentsService.selectCommentPage(page, leaveComments));
    }

    /**
     * 设置留言状态
     *
     * @param id     主键
     * @param status 状态
     * @return 结果
     */
    @GetMapping("/setViewStatus")
    public R setViewStatus(Integer id, String status) {
        LeaveComments leaveComments = leaveCommentsService.getById(id);
        leaveComments.setStatus(status);
        return R.ok(leaveCommentsService.updateById(leaveComments));
    }

    /**
     * 获取留言信息
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(leaveCommentsService.list());
    }

    /**
     * 获取留言详细信息
     *
     * @param id ID
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(leaveCommentsService.getById(id));
    }

    /**
     * 新增留言信息
     *
     * @param leaveComments 留言信息
     * @return 结果
     */
    @PostMapping
    public R save(LeaveComments leaveComments) {
        // 设置用户ID
        StudentInfo studentInfo = studentInfoService.getOne(Wrappers.<StudentInfo>lambdaQuery().eq(StudentInfo::getUserId, leaveComments.getUserId()));
        if (studentInfo != null) {
            leaveComments.setUserId(studentInfo.getId());
        }
        leaveComments.setCreateDate(DateUtil.formatDateTime(new Date()));
        leaveComments.setStatus("0");
        return R.ok(leaveCommentsService.save(leaveComments));
    }

    /**
     * 修改留言信息
     *
     * @param leaveComments 留言信息
     * @return 结果
     */
    @PutMapping
    public R edit(LeaveComments leaveComments) {
        return R.ok(leaveCommentsService.updateById(leaveComments));
    }

    /**
     * 删除留言信息
     *
     * @param ids 主键IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(leaveCommentsService.removeByIds(ids));
    }
}
