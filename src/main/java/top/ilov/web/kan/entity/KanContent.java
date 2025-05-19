package top.ilov.web.kan.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author xhanhh
 * @since 2025-05-18
 */
@Getter
@Setter
@ToString
@TableName("kan_content")
public class KanContent {

    /**
     * 内容ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级内容ID
     */
    private Long parentId;

    /**
     * 父级内容类型：0主题
     */
    private Byte parentType;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 内容
     */
    private String content;

    /**
     * 类型：1图片，2文本
     */
    private Byte type;

    /**
     * 排序，越小越靠前
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private Long createdOn;

    /**
     * 修改时间
     */
    private Long modifiedOn;

    /**
     * 删除时间
     */
    private Long deletedOn;

    /**
     * 是否删除 0 为未删除、1 为已删除
     */
    private Byte isDel;
}
