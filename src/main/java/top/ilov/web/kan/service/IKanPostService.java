package top.ilov.web.kan.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.ilov.web.kan.common.Result;
import top.ilov.web.kan.dto.post.PostDTO;
import top.ilov.web.kan.dto.post.PostSubmitDTO;
import top.ilov.web.kan.entity.KanPost;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xhanhh
 * @since 2025-05-18
 */
public interface IKanPostService extends IService<KanPost> {

    Result<IPage<PostDTO>> getPostByPage(Byte orderBy, int page, int pageSize);
    Result<String> readPost(Long postId);
    Result<String> addPost(PostSubmitDTO postSubmitDTO, String token);
    Result<PostDTO> getPostById(Long postId);

}
