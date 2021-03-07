package com.rest.rest_server.controller;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@Slf4j
public class JDBCController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/savePlan")
    public void savePlan(){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insert = "insert into campaign_plan(campaign_id,campaign_name,content) values(?,?,?)";
        List<Object[]> insertParam = new ArrayList<>();
        insertParam.add(new Object[]{"shao", "shao", "body"});
        jdbcTemplate.update(insert,insertParam,keyHolder);
        System.out.println("看下能否拿到最终的自增的id-------------->" + keyHolder.getKey().longValue());
    }

    @RequestMapping("/select")
    public Map select(){
        String resp = "";
        String campaignId = "1000001";
        List<Map<String, Object>> listMap = jdbcTemplate.queryForList("select * from campaign_plan where campaign_id = '"+campaignId+"'");
        resp += "[";
        for (Map map : listMap) {
            resp +=  map.get("content").toString() + ",";
            log.info("content={}",resp);
        }
        resp = resp.substring(0, resp.length() -1);
        resp += "]";
        System.out.println(resp);
        JSONArray jsonArray = JSONArray.parseArray(resp);
        Map<String, Object> map = new HashMap<>();
        map.put("code","200");
        map.put("msg","查询成功！");
        map.put("data",jsonArray);
        return map;
    }


    @RequestMapping("/delete")
    public Map delete(){
        String campaignId = "1000001";
        StringBuffer sb = new StringBuffer("delete from campaign_plan where campaign_id = '" + campaignId + "'");
        int count = jdbcTemplate.update(sb.toString());
        System.out.println(count);
        Map<String, Object> map = new HashMap<>();
        map.put("code","200");
        map.put("msg","查询成功！");
        return map;
    }
}