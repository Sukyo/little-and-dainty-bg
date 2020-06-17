package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.Comment;
import com.suk.blog.entity.vo.CommentConditionVo;

import java.util.List;

/**
 * 文章评论 服务层
 *
 * @author suk
 * @date 2019-09-27
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 分页查询
     *
     * @param iPage
     * @param comment
     * @return
     */

    IPage<Comment> pageList(IPage<Comment> iPage, Comment comment);

    /**
     * 不分页查询
     *
     * @param comment
     * @return
     */
    List<Comment> list(Comment comment);

    List<Comment> selectComments(CommentConditionVo vo);
}
