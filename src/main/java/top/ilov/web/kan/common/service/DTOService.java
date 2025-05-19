package top.ilov.web.kan.common.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ilov.web.kan.dto.ContentDTO;
import top.ilov.web.kan.dto.post.PostDTO;
import top.ilov.web.kan.entity.KanContent;
import top.ilov.web.kan.entity.KanPost;
import top.ilov.web.kan.mapper.KanContentMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DTOService {

    @Autowired
    private KanContentMapper contentMapper;

    public PostDTO postToPostDTO(KanPost post) {

        if (post == null) return null;

        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        postDTO.setIsEssence(post.getIsEssence() == 1);
        postDTO.setIsTop(post.getIsTop() == 1);

        List<KanContent> contents = contentMapper.selectList(new LambdaQueryWrapper<KanContent>()
                .eq(KanContent::getIsDel, "0")
                .eq(KanContent::getParentType, "0")
                .eq(KanContent::getParentId, post.getId()));
        postDTO.setContents(contents.stream().map(this::contentToContentDTO).collect(Collectors.toList()));

        return postDTO;

    }

    public ContentDTO contentToContentDTO(KanContent content) {

        if (content == null) return null;

        ContentDTO contentDTO = new ContentDTO();
        BeanUtils.copyProperties(content, contentDTO);
        return contentDTO;

    }
}
