package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.Comment;
import com.suk.blog.entity.vo.CommentConditionVo;
import com.suk.blog.mapper.CommentMapper;
import com.suk.blog.service.ICommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 文章评论 服务层实现
 *
 * @author suk
 * @date 2019-09-27
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    CommentMapper commentMapper;

    /**
     * 分页查询
     *
     * @param iPage
     * @param comment
     * @return
     */
    @Override
    public IPage<Comment> pageList(IPage<Comment> iPage, Comment comment) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<Comment>();
        queryWrapper.ge(comment.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(comment.getParams().get(Constants.START_TIME).toString()), Comment::getCreateTime, comment.getParams().get(Constants.START_TIME))
                .le(comment.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(comment.getParams().get(Constants.END_TIME).toString()), Comment::getCreateTime, comment.getParams().get(Constants.END_TIME));

        page(iPage, queryWrapper);
        return iPage;
    }

    /**
     * 不分页查询
     *
     * @param comment
     * @return
     */
    @Override
    public List<Comment> list(Comment comment) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<Comment>();
        queryWrapper.ge(comment.getParams().get(Constants.START_TIME) != null && StringUtils.isNoneBlank(comment.getParams().get(Constants.START_TIME).toString()), Comment::getCreateTime, comment.getParams().get(Constants.START_TIME))
                .le(comment.getParams().get(Constants.END_TIME) != null && StringUtils.isNoneBlank(comment.getParams().get(Constants.END_TIME).toString()), Comment::getCreateTime, comment.getParams().get(Constants.END_TIME));
        List<Comment> list = list(queryWrapper);
        return list;
    }

    @Override
    public List<Comment> selectComments(CommentConditionVo vo) {
        return commentMapper.selectComments(vo);
    }
}
