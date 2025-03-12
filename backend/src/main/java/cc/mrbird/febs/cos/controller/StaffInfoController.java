package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.StaffInfo;
import cc.mrbird.febs.cos.service.IStaffInfoService;
import cc.mrbird.febs.system.service.UserService;
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
@RequestMapping("/cos/staff-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StaffInfoController {

    private final IStaffInfoService staffInfoService;

    private final UserService userService;

    /**
     * 分页获取员工信息
     *
     * @param page          分页对象
     * @param staffInfo 员工信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<StaffInfo> page, StaffInfo staffInfo) {
        return R.ok(staffInfoService.selectStaffPage(page, staffInfo));
    }

    /**
     * 查询用户信息详情【公告信息】
     *
     * @param id 主键ID
     * @return 结果
     */
    @GetMapping("/selectBulletinDetail/{id}")
    public R selectBulletinDetail(@PathVariable("id") Integer id) {
        return R.ok(staffInfoService.selectBulletinDetail(id));
    }

    /**
     * 获取员工列表
     *
     * @param enterpriseId 校企ID
     * @return 结果
     */
    @GetMapping("/queryStaffList")
    public R queryStaffList(@RequestParam(value = "enterpriseId") Integer enterpriseId) {
        return R.ok(staffInfoService.selectStaffList(enterpriseId));
    }

    /**
     * 获取员工列表
     *
     * @param staffId 校企ID
     * @return 结果
     */
    @GetMapping("/queryStaffList/staff")
    public R queryStaffListByStaff(@RequestParam(value = "staffId") Integer staffId) {
        StaffInfo staffInfo = staffInfoService.getOne(Wrappers.<StaffInfo>lambdaQuery().eq(StaffInfo::getUserId, staffId));
        return R.ok(staffInfoService.queryStaffListByStaff(staffInfo.getEnterpriseId(), staffInfo.getId()));
    }

    /**
     * 获取员工信息
     *
     * @return 结果
     */
    @GetMapping("/userInfo/detail/{userId}")
    public R queryStaffByUserId(@PathVariable("userId") Integer userId) {
        return R.ok(staffInfoService.queryStaffByUserId(userId));
    }

    /**
     * 获取员工信息
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(staffInfoService.list(Wrappers.<StaffInfo>lambdaQuery().eq(StaffInfo::getDelFlag, 0)));
    }

    /**
     * 获取员工详细信息
     *
     * @param id ID
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(staffInfoService.getById(id));
    }

    /**
     * 新增员工信息
     *
     * @param staffInfo 员工信息
     * @return 结果
     */
    @PostMapping
    public R save(StaffInfo staffInfo) throws Exception {
        staffInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        staffInfo.setCode("STF-" + System.currentTimeMillis());
        userService.registStaff(staffInfo.getCode(), "1234qwer", staffInfo);
        return R.ok(true);
    }

    /**
     * 修改员工信息
     *
     * @param staffInfo 员工信息
     * @return 结果
     */
    @PutMapping
    public R edit(StaffInfo staffInfo) {
        return R.ok(staffInfoService.updateById(staffInfo));
    }

    /**
     * 删除员工信息
     *
     * @param ids 主键IDS
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(staffInfoService.removeByIds(ids));
    }
}
