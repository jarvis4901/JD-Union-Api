package com.yd.jd.domin.resp;

import lombok.Data;

@Data
public class OrderRowResp {
    private String id;//编辑唯一订单行
    private long orderId;//订单号
    private long parentId;//父单的订单ID，仅当发生订单拆分时返回， 0：未拆分，有值则表示此订单为子订单
    private String orderTime;//下单时间,格式yyyy-MM-dd HH:mm:ss
    private String finishTime;//完成时间（购买用户确认收货时间）,格式yyyy-MM-dd HH:mm:ss
    private String modifyTime;//更新时间,格式yyyy-MM-dd HH:mm:ss
    private int orderEmt;//下单设备 1.pc 2.无线 默认2
    private int plus;//plus会员 1:是，0:否
    private long unionId;//推客ID
    private long skuId;//商品ID
    private String skuName;//商品名称
    private int skuReturnNum;//商品已退货数量
    private int skuFrozenNum;//商品售后中数量
    private double price;//商品单价
    private double commissionRate;//佣金比例(投放的广告主计划比例)
    private double subSideRate;//一级分成比例
    private double subsidyRate;//一级补贴比例
    private double finalRate;//最终比例( (一级分佣比例+一级分成比例)*二级分佣比例)
    private double estimateCosPrice;//预估计佣金额，即用户下单的金额(已扣除优惠券、白条、支付优惠、进口税，未扣除红包和京豆)，有时会误扣除运费券金额，完成结算时会在实际计佣金额中更正。如订单完成前发生退款，此金额也会更新。
    private double estimateFee;//推客的预估佣金（预估计佣金额*佣金比例*最终比例），如订单完成前发生退款，此金额也会更新。
    private double actualCosPrice;//实际计算佣金的金额。订单完成后，会将误扣除的运费券金额更正。如订单完成后发生退款，此金额会更新。
    private double actualFee;//推客分得的实际佣金（实际计佣金额*佣金比例*最终比例）。如订单完成后发生退款，此金额会更新。
    /**
     * sku维度的有效码（-1：未知,2.无效-拆单,3.无效-取消,4.无效-京东帮帮主订单,5.无效-账号异常,
     * 6.无效-赠品类目不返佣,7.无效-校园订单,8.无效-企业订单,9.无效-团购订单,10.无效-开增值税专用发票订单,
     * 11.无效-乡村推广员下单,12.无效-自己推广自己下单,13.无效-违规订单,14.无效-来源与备案网址不符,15.待付款,16.已付款,
     * 17.已完成,18.已结算（5.9号不再支持结算状态回写展示））,20.无效-复购订单对应的首购订单无效,21.无效-云店订单
     */
    private int validCode;//sku维度的有效码
    private int traceType;//同跨店：2同店 3跨店
    private long positionId;//推广位ID
    private long siteId;//应用id（网站id、appid、社交媒体id）
    private String unionAlias;//母账号简称
    private String pid;//格式:子推客ID_子站长应用ID_子推客推广位ID
    private long cid1;//一级类目id
    private long cid2;//二级类目id
    private long cid3;//三级类目id
    private String subUnionId;//子联盟ID(需要联系运营开放白名单才能拿到数据)
    /**
     * 联盟标签数据（整型的二进制字符串，目前返回16位：0000000000000001。数据从右向左进行，每一位为1表示符合联盟的标签特征，
     * 第1位：红包，第2位：组合推广，第3位：拼购，
     * 第5位：有效首次购（0000000000011XXX表示有效首购，最终奖励活动结算金额会结合订单状态判断，以联盟后台对应活动效果数据报表https://union.jd.com/active为准）,
     * 第8位：复购订单，第9位：礼金，第10位：联盟礼金，
     * 第11位：推客礼金。例如：0000000000000001:红包订单，0000000000000010:组合推广订单，0000000000000100:拼购订单，0000000000011000:有效首购，0000000000000111：红包+组合推广+拼购等）
     */
    private long popId;//商家ID
    private String ext1;//推客生成推广链接时传入的扩展字段（需要联系运营开放白名单才能拿到数据）。
    private String payMonth;//预估结算时间 格式：yyyyMMdd，默认：0，表示最新的预估结算日期
    private long cpActId;//招商团活动id
    private int unionRole;//站长角色：1 推客 2 团长
    private double giftCouponOcsAmount;//礼金分摊金额
    private String giftCouponKey;//礼金批次ID
    private String balanceExt;//计佣扩展信息，表示结算月:每月实际佣金变化情况，格式：{20191020:10,20191120:-2}，注意：有完成时间的，才会有这个值
    private String sign;//数据签名，用来核对出参数据是否被修改，入参fields中写入sign时返回

}
