package com.yd.jd.domin;

import lombok.Data;

/**
 * 视频
 */
@Data
public class Video {
    private Integer width; //宽
    private Integer high; //高
    private String imageUrl; //视频图片地址 https://img.300hu.com/4c1f7a6atransbjngwcloud1oss/44128edd173016898433773569/imageSampleSnapshot/1555986468_406717890.100_2756.jpg
    private Integer videoType; //1:主图，2：商详
    private String playUrl; //播放地址 https://vod.https://vod.300hu.com/4c1f7a6atransbjngwcloud1oss/44128edd173016898433773569/v.f20.mp4?dockingId=2bc88c56-a44d-45c4-99b4-d9b68557e4e9storageSource=3.com/4c1f7a6atransbjngwcloud1oss/44128edd173016898433773569/v.f20.mp4?dockingId=2bc88c56-a44d-45c4-99b4-d9b68557e4e9storageSource=3
    private String playType; //low：标清，high：高清
    private Integer duration; //时长(单位:s)
}
