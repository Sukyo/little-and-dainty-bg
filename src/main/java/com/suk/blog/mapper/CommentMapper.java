package com.suk.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suk.blog.entity.Comment;
import com.suk.blog.entity.vo.CommentConditionVo;

import java.util.List;

/**
 * 文章评论 数据层
 *
 * @author suk
 * @date 2019-09-27
 */
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    List<Comment> selectComments(CommentConditionVo vo);
}