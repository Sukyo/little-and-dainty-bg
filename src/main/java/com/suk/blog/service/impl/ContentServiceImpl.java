package com.suk.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suk.blog.entity.Content;
import com.suk.blog.mapper.ContentMapper;
import com.suk.blog.service.IContentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author suk
 * @since 2019-09-04
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {

}
