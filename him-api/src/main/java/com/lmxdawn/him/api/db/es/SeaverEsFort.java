package com.lmxdawn.him.api.db.es;//package com.mf.game.engine.db.es;
//
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.common.geo.GeoPoint;
//import org.elasticsearch.index.query.GeoBoundingBoxQueryBuilder;
//import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
//import org.elasticsearch.search.sort.SortOrder;
//import org.springframework.stereotype.Service;
//
//@Service("esFort")
//public class SeaverEsFort {
//    // 根据location坐标字段和当前位置，搜索1千米范围的数据
//
//    GeoDistanceQueryBuilder queryBuilder = QueryBuilders.geoDistanceQuery("location")
//
//            .distance("1km") //设置搜索距离为1千米
//
//// 设置当前位置
//
//            .point(new GeoPoint(39.889916, 116.379547));
//
//    //按距离排序
//
//// 创建SearchRequest对象
//
//    SearchRequest searchRequest = new SearchRequest();
//
//// 通过SearchSourceBuilder构建搜索参数
//
//    SearchSourceBuilder builder = new SearchSourceBuilder();
//
//// 设置前面创建的ES查询条件
//
//builder.query(queryBuilder);
//
//// 构建GeoDistanceSortBuilder设置按距离排序参数
//
//    GeoDistanceSortBuilder geoDistanceSortBuilder = SortBuilders.geoDistanceSort("location", new GeoPoint(39.889916, 116.379547));
//
//// 升序排序
//
//geoDistanceSortBuilder.order(SortOrder.ASC);
//
//// 设置排序参数
//
//builder.sort(geoDistanceSortBuilder);
//
//// 设置搜索请求参数
//
//searchRequest.source(builder);
//
//    //按矩形范围搜索
//
//// 根据location坐标字段和一个矩形范围，匹配文档
//
//    GeoBoundingBoxQueryBuilder queryBuilder = QueryBuilders.geoBoundingBoxQuery("location")
//
//            .setCorners( // 设置矩形坐标
//
//                    new GeoPoint(40.73, -74.1), // 设置矩形的左上角坐标
//
//                    new GeoPoint(40.717, -73.99) // 设置矩形的右下角坐标
//
//            );
//
//    //创建SearchRequest
//
//    //Java 所有的ES查询请求都是通过SearchRequest对象进行设置，因此需要实例化SearchRequest对象，设置query参数。
//
//    SearchRequest searchRequest = new SearchRequest();
//
//// 通过SearchSourceBuilder构建搜索参数
//
//    SearchSourceBuilder builder = new SearchSourceBuilder();
//
//// 设置query参数，绑定前面创建的Query对象
//
//builder.query(queryBuilder);
//
//// 设置SearchRequest搜索参数
//
//searchRequest.source(builder);
//
//    //执行请求
//
//// 执行ES请求
//
//    SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//
//}
