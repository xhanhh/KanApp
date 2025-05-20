package top.ilov.web.kan.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.ilov.web.kan.annotation.LoginRequire;
import top.ilov.web.kan.common.Result;
import top.ilov.web.kan.dto.post.PostDTO;
import top.ilov.web.kan.dto.post.PostSubmitDTO;
import top.ilov.web.kan.service.IKanPostService;

/**
 *  主题内容 前端控制器
 *
 * @author xhanhh
 * @since 2025-05-18
 */
@RestController
@RequestMapping("/v1/post")
public class KanPostController {

    @Autowired
    private IKanPostService postService;

    @GetMapping
    public Result<IPage<PostDTO>> getPostByPage(@RequestParam(value = "orderBy", required = false, defaultValue = "0") Byte orderBy,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                @RequestParam(value = "page_size", required = false, defaultValue = "10") int pageSize) {

        if (pageSize > 30) {
            pageSize = 30;
        }
        return postService.getPostByPage(orderBy, page, pageSize);

    }

    @PostMapping("/read")
    public Result<String> readPost(@RequestParam(value = "id") Long postId) {
        return postService.readPost(postId);
    }

    @GetMapping("/{id}")
    public Result<PostDTO> getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @LoginRequire
    @PostMapping
    public Result<String> addPost(@RequestBody PostSubmitDTO postSubmitDTO,
                                  @RequestHeader(value = "Authorization", required = false) String token) {
        return postService.addPost(postSubmitDTO, token);
    }

}
