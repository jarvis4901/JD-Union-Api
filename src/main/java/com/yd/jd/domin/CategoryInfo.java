package com.yd.jd.domin;

import lombok.Data;

@Data
public class CategoryInfo {
    private long cid1; //一级类目ID
    private String cid1Name; //一级类目名称
    private long cid2;  //二级类目ID
    private String cid2Name; //二级类目名称
    private long cid3;  //三级类目ID
    private String cid3Name; //三级类目名称

}
