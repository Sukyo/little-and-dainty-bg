package com.suk.blog.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.google.common.collect.Maps;
import com.suk.blog.aspectj.annotation.Log;
import com.suk.blog.common.response.AjaxResult;
import com.suk.blog.common.response.TableDataInfo;
import com.suk.blog.constant.Constants;
import com.suk.blog.entity.Comment;
import com.suk.blog.entity.SysUser;
import com.suk.blog.entity.vo.CommentConditionVo;
import com.suk.blog.enums.LogType;
import com.suk.blog.service.ICommentService;
import com.suk.blog.util.DateUtil;
import com.suk.blog.util.IpUtil;
import com.suk.blog.util.ServletUtils;
import com.suk.blog.util.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 文章评论 信息操作处理
 *
 * @author suk
 * @date 2019-09-27
 */
@Controller
@RequestMapping("/blog/comment")
public class CommentController extends BaseController {
    private String prefix = "blog/comment";

    @Autowired
    private ICommentService commentService;

    @RequiresPermissions("blog:comment:view")
    @GetMapping()
    public String comment() {
        return prefix + "/comment";
    }

    /**
     * 查询文章评论列表
     */
    @RequiresPermissions("blog:comment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CommentConditionVo comment) {
        List<Comment> comments = commentService.selectComments(comment);
        return getDataTable(comments);
    }


    /**
     * 导出文章评论列表
     */
    @RequiresPermissions("blog:comment:export")
    @GetMapping("/export")
    @ResponseBody
    public void export(Comment comment, HttpServletRequest request, HttpServletResponse response) {
        List<Comment> list = commentService.list(comment);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        String title = "文章评论";
        Map<String, Object> map = Maps.newHashMap();
        ExportParams params = new ExportParams(title, title, ExcelType.HSSF);
        map.put(NormalExcelConstants.DATA_LIST, list);
        map.put(NormalExcelConstants.CLASS, Comment.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put("fileName", DateUtil.getNowDateTime());
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }

    /**
     * 新增文章评论
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存文章评论
     */
    @RequiresPermissions("blog:comment:add")
    @Log(title = "文章评论", logType = LogType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Comment comment) {
        return toAjax(commentService.save(comment));
    }

    /**
     * 修改文章评论
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        Comment comment = commentService.getById(id);
        mmap.put("comment", comment);
        return prefix + "/edit";
    }

    /**
     * 修改保存文章评论
     */
    @RequiresPermissions("blog:comment:edit")
    @Log(title = "文章评论", logType = LogType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Comment comment) {
        return toAjax(commentService.updateById(comment));
    }

    /**
     * 删除文章评论
     */
    @RequiresPermissions("blog:comment:remove")
    @Log(title = "文章评论", logType = LogType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (StringUtils.isNoneBlank(ids)) {
            String[] split = ids.split(",");
            return toAjax(commentService.removeByIds(Arrays.asList(split)));
        }
        return error();
    }

    @RequiresPermissions("blog:comment:audit")
    @Log(title = "文章评论", logType = LogType.AUDIT)
    @PostMapping("/audit")
    @ResponseBody
    public AjaxResult audit(Comment bizComment, String replyContent) {
        try {
            commentService.updateById(bizComment);
            if (StringUtils.isNotBlank(replyContent)) {
                Comment replyComment = new Comment();
                replyComment.setPid(bizComment.getId());
                replyComment.setSid(bizComment.getSid());
                replyComment.setContent(replyContent);
                completeComment(replyComment);
                commentService.save(replyComment);
            }
            return success("审核成功");
        } catch (Exception e) {
            return success("审核失败");
        }
    }


    private void completeComment(Comment comment) {
        HttpServletRequest request = ServletUtils.getRequest();
        SysUser user = ShiroUtils.getSysUser();
        comment.setUserId(user.getId().toString());
        comment.setNickname(user.getUserName());
        comment.setEmail(user.getEmail());
        comment.setAvatar(user.getAvatar());
        comment.setIp(IpUtil.getIpAddr(request));
        comment.setStatus(Constants.STATUS_VALID);
    }

}
