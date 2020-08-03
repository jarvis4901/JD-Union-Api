package com.yd.jd.domin.resp;

import lombok.Data;

import java.util.Map;

@Data
public class PositionCreateResp {
    private Map<String, String> resultList;
    private long siteId; //站点Id
    private long type; //联盟类型
    private long unionId; //联盟ID
}
