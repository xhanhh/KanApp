package top.ilov.web.kan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import top.ilov.web.kan.common.Result;
import top.ilov.web.kan.common.StatusCode;
import top.ilov.web.kan.common.TimeProvider;
import top.ilov.web.kan.common.service.DTOService;
import top.ilov.web.kan.dto.post.PostDTO;
import top.ilov.web.kan.dto.post.PostSubmitDTO;
import top.ilov.web.kan.entity.KanContent;
import top.ilov.web.kan.entity.KanPost;
import top.ilov.web.kan.mapper.KanContentMapper;
import top.ilov.web.kan.mapper.KanPostMapper;
import top.ilov.web.kan.service.IKanPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  主题 服务实现类
 * </p>
 *
 * @author xhanhh
 * @since 2025-05-18
 */
@Service
public class KanPostServiceImpl extends ServiceImpl<KanPostMapper, KanPost> implements IKanPostService, TimeProvider {

    @Autowired
    private DTOService dtoService;

    @Autowired
    private KanContentMapper contentMapper;

    @Override
    public Result<IPage<PostDTO>> getPostByPage(Byte orderBy, int page, int pageSize) {

        Page<KanPost> postPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<KanPost> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(KanPost::getIsDel, "0");
        wrapper.orderByDesc(KanPost::getIsTop);

        if (orderBy == null || orderBy == 0) {
            wrapper.orderByDesc(KanPost::getModifiedOn);
        }

        IPage<KanPost> postIPage = this.page(postPage, wrapper);
        IPage<PostDTO> postDTOIPage = postIPage.convert(entity -> dtoService.postToPostDTO(entity));

        return Result.success(postDTOIPage);

    }

    @Override
    public Result<String> readPost(Long postId) {

        if (postId == null || postId == 0) {
            return Result.error(StatusCode.EMPTY_BODY, "主题ID有误");
        }

        KanPost post = this.baseMapper.selectById(postId);
        if (post == null || post.getIsDel() == 1) {
            return Result.error(StatusCode.NOT_FOUND, "该主题不存在");
        }

        post.setVisitCount(post.getVisitCount() + 1);
        this.baseMapper.updateById(post);

        return Result.success("阅读标记成功");

    }

    @Override
    public Result<PostDTO> getPostById(Long postId) {

        if (postId == null || postId == 0) {
            return Result.error(StatusCode.EMPTY_BODY, "主题ID有误");
        }

        KanPost post = this.baseMapper.selectById(postId);
        if (post == null || post.getIsDel() == 1) {
            return Result.error(StatusCode.NOT_FOUND, "该主题不存在");
        }

        return Result.success(dtoService.postToPostDTO(post));

    }

    @Override
    public Result<String> addPost(PostSubmitDTO postSubmitDTO) {

        Long userId = 0L;

        if (StringUtils.isBlank(postSubmitDTO.getTitle()) ||
                StringUtils.isBlank(postSubmitDTO.getContent())) {
            return Result.error(StatusCode.EMPTY_BODY, "标题与内容为空");
        }

        KanPost post = new KanPost();
        post.setCreatedOn(getCurrentTime());
        post.setModifiedOn(getCurrentTime());
        post.setUserId(userId);
        this.baseMapper.insert(post);

        insertKanContent(postSubmitDTO.getTitle(), 100, (byte) 0, post.getId(), userId);
        insertKanContent(postSubmitDTO.getContent(), 101, (byte) 2, post.getId(), userId);

        return Result.success("提交成功");

    }

    private void insertKanContent(String content, int sort, byte type, Long parentId, Long userId) {
        KanContent kanContent = new KanContent();
        kanContent.setContent(content);
        kanContent.setSort(sort);
        kanContent.setUserId(userId);
        kanContent.setType(type);
        kanContent.setParentType((byte) 0);
        kanContent.setParentId(parentId);
        kanContent.setCreatedOn(getCurrentTime());
        kanContent.setModifiedOn(getCurrentTime());
        contentMapper.insert(kanContent);
    }


}
