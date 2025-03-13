package cc.mrbird.febs.cos.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 选修课分数记录
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ElectiveScoreRecord implements Serializable {

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
     * 课程ID
     */
    private Integer courseId;

    /**
     * 选修课表ID
     */
    private Integer electiveId;

    /**
     * 分数
     */
    private Integer score;

    @TableField(exist = false)
    private String studentName;

    @TableField(exist = false)
    private String courseName;

    @TableField(exist = false)
    private String majorName;

}
