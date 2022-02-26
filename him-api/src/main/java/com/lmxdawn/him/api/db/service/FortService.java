package com.lmxdawn.him.api.db.service;

import com.lmxdawn.him.api.db.entry.Fort;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.List;
import java.util.Random;

public class FortService {
    @Autowired
    private ElasticsearchTemplate esTemplate;

    //在地图上添加堡垒位置
    public void addFortPoint(Fort fort){

        Integer integer=new Random().nextInt(10000);
        fort.setFort_id(Long.valueOf(integer));
        IndexQuery indexQuery=new IndexQueryBuilder().withObject(fort).build();
        esTemplate.index(indexQuery);
    }
    //从当前位置获得一点范围的坐标点
    public List<Fort> geoDistance(GeoPoint centerPoint, String km){
        //中心点有前端获取当前人的地理位置或者输入一个中心点位置
        GeoDistanceQueryBuilder geoDistanceQueryBuilder= QueryBuilders.geoDistanceQuery("geo")
                .distance(km, DistanceUnit.KILOMETERS)
                .point(centerPoint);
        List<Fort> fortList=doSearchQuery(geoDistanceQueryBuilder);
        return fortList;
    }
    private List<Fort> doSearchQuery(QueryBuilder builder){
        NativeSearchQuery searchQueryBuilder=new NativeSearchQueryBuilder()
                .withQuery(builder)
                .build();
        AggregatedPage<Fort> pagefort=esTemplate.queryForPage(searchQueryBuilder,Fort.class);
        List<Fort> fortList= pagefort.getContent();
        return fortList;
    }
}
