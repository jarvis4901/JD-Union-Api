package com.yd.jd.domin;

import lombok.Data;

/**
 * 评论
 */
@Data
public class Comment {
    private String content; //评价内容
    private UrlInfo[] imageList; //图片集合
}
