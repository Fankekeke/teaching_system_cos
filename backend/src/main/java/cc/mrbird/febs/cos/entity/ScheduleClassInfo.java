package cc.mrbird.febs.cos.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 班级课表信息
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ScheduleClassInfo implements Serializable {

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
     * 所属班级
     */
    private Integer classId;

    /**
     * 课程ID
     */
    private Integer courseId;


}
