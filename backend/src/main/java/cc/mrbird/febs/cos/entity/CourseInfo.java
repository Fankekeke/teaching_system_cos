package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 课程信息
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CourseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 编号
     */
    private String code;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 导师ID
     */
    private Integer staffId;

    /**
     * 年级
     */
    private String gradeId;

    /**
     * 时常
     */
    private String duration;

    /**
     * 上课地点
     */
    private String address;

    /**
     * 人数
     */
    private Integer peopleNum;

    /**
     * 类型（1.常规 2.选修）
     */
    private String type;

    /**
     * 系ID
     */
    private Integer tieId;

    /**
     * 所属专业
     */
    private Integer majorId;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 备注
     */
    private String content;


}
