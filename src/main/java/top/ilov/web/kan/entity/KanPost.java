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
@TableName("kan_post")
public class KanPost {

    /**
     * 主题ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 浏览量
     */
    private Long visitCount;

    /**
     * 评论数
     */
    private Long commentCount;

    /**
     * 点赞数
     */
    private Long upvoteCount;

    /**
     * 收藏数
     */
    private Long collectionCount;

    /**
     * 是否置顶
     */
    private Byte isTop;

    /**
     * 是否精华
     */
    private Byte isEssence;

    /**
     * 最新回复时间
     */
    private Long latestRepliedOn;

    /**
     * IP地址
     */
    private String ip;

    /**
     * IP城市地址
     */
    private String ipLoc;

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
