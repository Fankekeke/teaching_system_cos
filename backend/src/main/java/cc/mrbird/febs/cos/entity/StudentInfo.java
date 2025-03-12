package cc.mrbird.febs.cos.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 学生信息
 *
 * @author FanK fan1ke2ke@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StudentInfo implements Serializable {

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
     * 学生姓名
     */
    private String name;

    /**
     * 性别（1.男 2.女）
     */
    private String sex;

    /**
     * 专业ID
     */
    private Integer majorId;

    /**
     * 所在班级
     */
    private Integer classId;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 邮件地址
     */
    private String email;

    /**
     * 备注
     */
    private String content;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 头像
     */
    private String images;

    /**
     * 所属账户
     */
    private Integer userId;


}
