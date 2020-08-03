package com.yd.jd.domin.resp;

import com.yd.jd.domin.*;
import lombok.Data;

@Data
public class GoodsDetailQueryResp {
    private long skuId; //商品id
    private String skuName; //商品名称
    private CategoryInfo categoryInfo; //目录信息
    private ImageInfo imageInfo; //图片信息
    private BaseBigFieldInfo baseBigFieldInfo; //基础大字段信息
    private BookBigFieldInfo bookBigFieldInfo; //图书大字段信息
    private VideoBigFieldInfo videoBigFieldInfo; //影音大字段信息
    private long mainSkuId; //自营主skuId
    private long productId; //非自营商品Id
    private int skuStatus; //sku上下架状态 1：上架(可搜索，可购买)， 0：下架(可通过skuid搜索，不可购买)， 2：可上架（可通过skuid搜索，不可购买）， 10：pop 删除（不可搜索，不可购买））
    private String owner; //是否自营。g=自营，p=pop
}
