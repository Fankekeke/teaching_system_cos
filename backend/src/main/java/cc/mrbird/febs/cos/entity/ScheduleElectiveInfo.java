package cc.mrbird.febs.cos.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 选修课表信息
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ScheduleElectiveInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 课程日期
     */
    private String courseDate;

    /**
     * 课程开始时间
     */
    private String startTime;

    /**
     * 课程结束时间
     */
    private String endTime;

    /**
     * 课程ID
     */
    private Integer courseId;

    /**
     * 选修备注
     */
    private String content;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 状态（0.未打分 1.已打分）
     */
    private String status;

    @TableField(exist = false)
    private String className;
    @TableField(exist = false)
    private Integer staffId;
    @TableField(exist = false)
    private String courseName;
    @TableField(exist = false)
    private String majorName;
    @TableField(exist = false)
    private String staffName;

}
