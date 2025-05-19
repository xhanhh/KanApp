package top.ilov.web.kan.dto.post;

import lombok.Data;
import top.ilov.web.kan.dto.ContentDTO;

import java.util.List;

@Data
public class PostDTO {

    private Long id;
    private Long userId;
    private Long visitCount;
    private Long upvoteCount;
    private Long commentCount;
    private Long collectionCount;
    private Boolean isTop;
    private Boolean isEssence;
    private Long latestRepliedOn;
    private String ipLoc;
    private Long createdOn;
    private Long modifiedOn;
    private List<ContentDTO> contents;

}
