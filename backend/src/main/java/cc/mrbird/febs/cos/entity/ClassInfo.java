package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 班级管理
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ClassInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 班级编号
     */
    private String code;

    /**
     * 班级名称
     */
    private String name;

    /**
     * 备注
     */
    private String content;

    /**
     * 所属系
     */
    private Integer tieId;

    /**
     * 所属专业
     */
    private Integer majorId;

    /**
     * 年级
     */
    private String gradeYaer;

    /**
     * 管理导师
     */
    private Integer teacherId;

    /**
     * 创建时间
     */
    private String createDate;

    @TableField(exist = false)
    private String staffName;


}
