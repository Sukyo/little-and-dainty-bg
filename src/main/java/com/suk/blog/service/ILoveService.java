package com.suk.blog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.suk.blog.entity.Love;

import java.util.List;

/**
 * biz_love 服务层
 *
 * @author suk
 * @date 2019-09-27
 */
public interface ILoveService extends IService<Love> {

    /**
     * 分页查询
     *
     * @param iPage
     * @param love
     * @return
     */

    IPage<Love> pageList(IPage<Love> iPage, Love love);

    /**
     * 不分页查询
     *
     * @param love
     * @return
     */
    List<Love> list(Love love);

    Love checkLove(Integer bizId, String userIp);
}
