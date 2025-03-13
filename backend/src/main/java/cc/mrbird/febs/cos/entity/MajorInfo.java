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
 * 专业管理
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MajorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 所属系
     */
    private Integer tieId;

    /**
     * 专业编号
     */
    private String code;

    /**
     * 专业名称
     */
    private String name;

    /**
     * 学位门类
     */
    private String degreeCategory;

    /**
     * 修业年限
     */
    private String studyDuration;

    /**
     * 备注
     */
    private String content;

    /**
     * 创建时间
     */
    private String createDate;

    @TableField(exist = false)
    private String tieName;


}
