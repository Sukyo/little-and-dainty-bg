package com.suk.blog.controller.front;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suk.blog.common.response.AjaxResult;
import com.suk.blog.constant.Constants;
import com.suk.blog.controller.BaseController;
import com.suk.blog.entity.ArticleLook;
import com.suk.blog.entity.Comment;
import com.suk.blog.entity.Love;
import com.suk.blog.entity.vo.CommentConditionVo;
import com.suk.blog.service.IArticleLookService;
import com.suk.blog.service.ICommentService;
import com.suk.blog.service.ILoveService;
import com.suk.blog.util.DateUtil;
import com.suk.blog.util.IpUtil;
import com.suk.blog.util.MD5;
import com.suk.blog.util.XssKillerUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/suk/blog/api")
public class BlogApiController extends BaseController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IArticleLookService articleLookService;
    @Autowired
    private ILoveService loveService;

    @PostMapping("comments")
    public PageInfo<Comment> getComments(CommentConditionVo vo) {
        PageHelper.startPage(vo.getPageNo(), vo.getPageSize());
        List<Comment> comments = commentService.selectComments(vo);
        PageInfo<Comment> pages = new PageInfo<>(comments);
        return pages;
    }

    @PostMapping("comment/save")
    public AjaxResult saveComment(HttpServletRequest request, Comment comment) throws UnsupportedEncodingException {
        if (org.springframework.util.StringUtils.isEmpty(comment.getNickname())) {
            return error("请输入昵称");
        }
        String content = comment.getContent();
        if (!XssKillerUtil.isValid(content)) {
            return error("内容不合法");
        }
        content = XssKillerUtil.clean(content.trim()).replaceAll("(<p><br></p>)|(<p></p>)", "");
        Date date = new Date();
        comment.setContent(content);
        comment.setIp(IpUtil.getIpAddr(request));
        comment.setCreateTime(date);
        comment.setUpdateTime(date);
        if (StringUtils.isNotBlank(comment.getQq())) {
            comment.setAvatar("http://q1.qlogo.cn/g?b=qq&nk=" + comment.getQq() + "&s=100");
        } else if (StringUtils.isNotBlank(comment.getEmail())) {
            String entry = MD5.md5Hex(comment.getEmail());
            comment.setAvatar("http://www.gravatar.com/avatar/" + entry + "?d=mp");
        }
        boolean save = commentService.save(comment);
        if (save) {
            return success("评论提交成功,系统正在审核");
        } else {
            return error("评论提交失败");
        }
    }

    ;

    @PostMapping("article/look")
    public AjaxResult checkLook(HttpServletRequest request, Integer articleId) {
        /*浏览次数*/
        Date date = new Date();
        String ip = IpUtil.getIpAddr(request);
        int checkCount = articleLookService.checkArticleLook(articleId, ip, DateUtil.addSeconds(date, -(60 * 60 * 1000)));
        if (checkCount == 0) {
            ArticleLook articleLook = new ArticleLook();
            articleLook.setArticleId(articleId);
            articleLook.setUserIp(ip);
            articleLook.setLookTime(date);
            articleLook.setCreateTime(date);
            articleLook.setUpdateTime(date);
            articleLookService.save(articleLook);
            return success("浏览次数+1");
        } else {
            return error("一小时内重复浏览不增加次数哦");
        }
    }

    ;

    @PostMapping("love")
    public AjaxResult love(HttpServletRequest request, Integer bizId, Integer bizType) {
        Date date = new Date();
        String ip = IpUtil.getIpAddr(request);
        Love bizLove = loveService.checkLove(bizId, ip);
        if (bizLove == null) {
            bizLove = new Love();
            bizLove.setBizId(bizId);
            bizLove.setBizType(bizType);
            bizLove.setUserIp(ip);
            bizLove.setStatus(Constants.STATUS_VALID);
            bizLove.setCreateTime(date);
            bizLove.setUpdateTime(date);
            loveService.save(bizLove);
            return success("点赞成功");
        } else {
            return error("您已赞过了哦~");
        }
    }

}
