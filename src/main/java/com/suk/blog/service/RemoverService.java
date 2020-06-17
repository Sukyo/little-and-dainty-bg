package com.suk.blog.service;

import me.zhyd.hunter.config.HunterConfig;

import java.io.PrintWriter;

public interface RemoverService {

    void run(Integer categoryId, HunterConfig config, PrintWriter writer);

    void stop();

    void crawlSingle(Integer categoryId, String[] url, boolean convertImg, PrintWriter writer);
}
