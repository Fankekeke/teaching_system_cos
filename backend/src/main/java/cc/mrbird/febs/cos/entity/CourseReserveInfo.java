package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 课程预约
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseReserveInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学生ID
     */
    private Integer studentId;

    /**
     * 选修课表ID
     */
    private Integer electiveId;

    /**
     * 课程ID
     */
    private Integer courseId;

    /**
     * 申请备注
     */
    private String content;

    /**
     * 状态（0.审核 1.通过 2.驳回）
     */
    private String status;

    /**
     * 驳回原因
     */
    private String reason;

    /**
     * 审核时间
     */
    private String auditDate;


}
