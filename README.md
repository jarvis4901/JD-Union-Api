# 京东联盟Api  v1.0

#### 京东联盟文档为1.0版本 提供下载的sdk为2.0 暂时不用 
#### 此项目基于京东联盟1.0版本文档的接口重新封装

#### 此文档只列出各个接口的调用示例，具体的请求和返回字段可以参照京东联盟的文档
#### 初始化
``````java
   DefaultJdClient jdClient = new DefaultJdClient(appKey, appSecret);
``````
#### 京粉精选商品查询接口 
##### jd.union.open.goods.jingfen.query
##### [官网文档](https://union.jd.com/openplatform/api/10417)

```java
  JingFenGoodsQueryRequest request = new JingFenGoodsQueryRequest();
  JFGoodsReq goodsReq = new JFGoodsReq();
  goodsReq.setEliteId(22);
  ...
  request.setGoodsReq(goodsReq);
  JingFenGoodsQueryResponse response = jdClient.execute(request);
```

---
#### 关键词商品查询接口 
##### jd.union.open.goods.query
##### [官网文档](https://union.jd.com/openplatform/api/10420)

```java
  GoodsQueryRequest request = new GoodsQueryRequest();
  GoodsReq goodsReqDTO=new GoodsReq();
  goodsReqDTO.setKeyword("手机");
  ...
  request.setGoodsReqDTO(goodsReqDTO);
  GoodsQueryResponse response = jdClient.execute(request);
```

---

#### 根据skuid查询商品信息接口 
##### jd.union.open.goods.promotiongoodsinfo.query
##### [官网文档](https://union.jd.com/openplatform/api/10422)

```java
  PromotionGoodsQueryRequest request = new PromotionGoodsQueryRequest();
  request.setSkuIds("34550928217");
  PromotionGoodsQueryResponse res = jdClient.execute(request);
```

---

#### 秒杀商品查询接口
##### jd.union.open.goods.seckill.query
##### [官网文档](https://union.jd.com/openplatform/api/10431)

```java
  SeckillGoodsQueryRequest request = new SeckillGoodsQueryRequest();
  SecKillGoodsReq goodsReq=new SecKillGoodsReq();
  request.setGoodsReq(goodsReq);
  SeckillGoodsQueryResponse response = jdClient.execute(request);
```

---

#### 学生价商品查询接口 
##### jd.union.open.goods.stuprice.query
##### [官网文档](https://union.jd.com/openplatform/api/10432)

```java
  StuPriceGoodsQueryRequest request = new StuPriceGoodsQueryRequest();
  StuPriceGoodsReq goodsReq=new StuPriceGoodsReq();
  request.setGoodsReq(goodsReq);
  StuPriceGoodsQueryResponse response = jdClient.execute(request);
```

---

#### 商品类目查询接口 
##### jd.union.open.category.goods.get
##### [官网文档](https://union.jd.com/openplatform/api/10434)

```java
  GoodsCategoryQueryRequest request = new GoodsCategoryQueryRequest();
  CategoryReq goodsReq=new CategoryReq();
  goodsReq.setParentId(1342);
  goodsReq.setGrade(2);
  request.setCategoryReq(goodsReq);
  GoodsCategoryQueryResponse response = jdClient.execute(request);
```

---

#### 商品详情查询接口
##### jd.union.open.goods.bigfield.query
##### [官网文档](https://union.jd.com/openplatform/api/11248)

```java
  GoodsDetailQueryRequest request = new GoodsDetailQueryRequest();
  BigFieldGoodsReq goodsReq=new BigFieldGoodsReq();
  request.setGoodsReq(goodsReq);
  GoodsDetailQueryResponse response = jdClient.execute(request);
```

---

#### 物料商品查询 
##### jd.union.open.goods.material.query
##### [官网文档](https://union.jd.com/openplatform/api/13625)

```java
  MaterialGoodsQueryRequest request = new MaterialGoodsQueryRequest();
  MaterialGoodsReq goodsReq=new MaterialGoodsReq();
  goodsReq.setEliteId(1);
  request.setGoodsReq(goodsReq);
  MaterialGoodsQueryResponse response = jdClient.execute(request);
```

---

#### 优惠券领取情况查询接口 
##### jd.union.open.coupon.query
##### [官网文档](https://union.jd.com/openplatform/api/10423)

```java
  CouponQueryRequest request = new CouponQueryRequest();
  List<String> couponUrls=new ArrayList<String>();
  couponUrls.add("http://coupon.m.jd.com/coupons/show.action?key=d440ff67ebf0489180b46fdd6714544f&roleId=35051701&to=mall.jd.com/index-813483.html");
  request.setCouponUrls(couponUrls);
  CouponQueryResponse response = jdClient.execute(request);
```

---

#### 网站/APP获取推广链接接口 
##### jd.union.open.promotion.common.get
##### [官网文档](https://union.jd.com/openplatform/api/10421)

```java
  CommonPromotionQueryRequest request = new CommonPromotionQueryRequest();
  PromotionCodeReq promotionCodeReq = new PromotionCodeReq();
  promotionCodeReq.setMaterialId("https://item.jd.com/23484023378.html");
  promotionCodeReq.setSiteId("435676");
  request.setPromotionCodeReq(promotionCodeReq);
  CommonPromotionQueryResponse response = jdClient.execute(request);
```

---

#### 社交媒体获取推广链接接口 
##### jd.union.open.promotion.bysubunionid.get
##### [官网文档](https://union.jd.com/openplatform/api/10424)

```java
  BySubUnionIdPromotionQueryRequest request = new BySubUnionIdPromotionQueryRequest();
  PromotionCodeReq promotionCodeReq = new PromotionCodeReq();
  promotionCodeReq.setMaterialId("https://item.jd.com/23484023378.html");
  request.setPromotionCodeReq(promotionCodeReq);
  BySubUnionIdPromotionQueryResponse response = jdClient.execute(request);
```

---

#### 工具商获取推广链接接口 
##### jd.union.open.promotion.byunionid.get
##### [官网文档](https://union.jd.com/openplatform/api/10425)

```java
  ByUnionIdPromotionQueryResquest request = new ByUnionIdPromotionQueryResquest();
  PromotionCodeReq promotionCodeReq = new PromotionCodeReq();
  promotionCodeReq.setMaterialId("https://item.jd.com/23484023378.html");
  promotionCodeReq.setUnionId(10000618);
  request.setPromotionCodeReq(promotionCodeReq);
  ByUnionIdPromotionQueryResponse response = jdClient.execute(request);
```

---

#### 订单查询接口 
##### jd.union.open.order.query
##### [官网文档](https://union.jd.com/openplatform/api/10419)

```java
   OrderQueryRequest request = new OrderQueryRequest();
   OrderReq orderReq=new OrderReq();
   orderReq.setTime("202007250959");
   orderReq.setType(2);
   orderReq.setPageNo(1);
   orderReq.setPageSize(20);
   request.setOrderReq(orderReq);
   OrderQueryResponse response = jdClient.execute(request);
```
---

#### 订单行查询接口
##### jd.union.open.order.row.query
##### [官网文档](https://union.jd.com/openplatform/api/12707)

```java
   OrderRowQueryRequest request = new OrderRowQueryRequest();
   OrderRowReq orderReq=new OrderRowReq();
   orderReq.setStartTime("2020-07-25 11:00:00");
   orderReq.setEndTime("2020-07-25 11:01:00");
   request.setOrderReq(orderReq);
   OrderRowQueryResponse response = jdClient.execute(request);
```
---

#### 奖励订单查询接口 
##### jd.union.open.order.bonus.query
##### [官网文档](https://union.jd.com/openplatform/api/11781)

```java
   BonusOrderQueryRequest request = new BonusOrderQueryRequest();
   BonusOrderReq orderReq=new BonusOrderReq();
   orderReq.setPageNo(1);
   orderReq.setPageSize(20);
   request.setOrderReq(orderReq);
   BonusOrderQueryResponse response = jdClient.execute(request);
```
---

#### 查询推广位 
##### jd.union.open.position.query
##### [官网文档](https://union.jd.com/openplatform/api/10428)

```java
  PositionQueryRequest request = new PositionQueryRequest();
  PositionReq positionReq=new PositionReq();
  request.setPositionReq(positionReq);
  PositionQueryResponse response = jdClient.execute(request);
```
---

#### 创建推广位 
##### jd.union.open.position.create
##### [官网文档](https://union.jd.com/openplatform/api/10429)

```java
  PositionCreateRequest request = new PositionCreateRequest();
  PositionReq positionReq=new PositionReq();
  request.setPositionReq(positionReq);
  PositionCreateResponse response = jdClient.execute(request);
```
---

#### 活动查询 
##### jd.union.open.activity.query
##### [官网文档](https://union.jd.com/openplatform/api/12667)

```java
  ActivityQueryRequest request = new ActivityQueryRequest();
  ActivityReq activityReq=new ActivityReq();
  request.setActivityReq(activityReq);
  ActivityQueryResponse response = jdClient.execute(request);
```
---

#### 礼金效果数据 
##### jd.union.open.statistics.giftcoupon.query
##### [官网文档](https://union.jd.com/openplatform/api/12248)

```java
  GiftCouponQueryRequest request = new GiftCouponQueryRequest();
  GiftCouponEffectDataReq giftCouponEffectDataReq = new GiftCouponEffectDataReq();
  request.setGiftCouponEffectDataReq(giftCouponEffectDataReq);
  GiftCouponQueryResponse response = jdClient.execute(request);
```
---

### 礼金创建 
##### jd.union.open.coupon.gift.get
##### [官网文档](https://union.jd.com/openplatform/api/12246)

```java
  CouponGiftCreateRequest request = new CouponGiftCreateRequest();
  CouponGiftCreateReq couponGiftCreateReq = new CouponGiftCreateReq();
  request.setCouponGiftCreateReq(couponGiftCreateReq);
  CouponGiftCreateResponse response = jdClient.execute(request);
```
---

#### 礼金停止 
##### jd.union.open.coupon.gift.stop
##### [官网文档](https://union.jd.com/openplatform/api/12240)

```java
   CouponGiftStopRequest request = new CouponGiftStopRequest();
   String giftCouponKey = "b22c7c7f14f6cXXX";
   request.setGiftCouponKey(giftCouponKey);
   CouponGiftStopResponse response = jdClient.execute(request);
```