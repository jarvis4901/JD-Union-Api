package com.yd.jd.domin.resp;

import lombok.Data;

@Data
public class PositionResp {
    private long id; //推广位ID
    private long siteId; //站点ID，如网站ID/appID/snsID
    private String spaceName; //推广位名称
    private long type; //站点类型(1网站推广位2.APP推广位3.社交媒体推广位4.聊天工具推广位)
}
