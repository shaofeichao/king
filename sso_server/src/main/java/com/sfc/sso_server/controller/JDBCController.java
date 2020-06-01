package com.sfc.sso_server.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sfc.sso_server.entity.users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Slf4j
public class JDBCController {

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String insertSql = "insert into campaign_plan(campaign_id,campaign_name,plan_id,content,creater_by,creater_time) values(?,?,?,?,?,?)";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getUsers")
    public void getDbType(){
        String sql = "select * from user";
        List<users> users =  jdbcTemplate.query(sql,new BeanPropertyRowMapper(users.class));
        System.out.print(users.size());
    }

    @RequestMapping("/save")
    public void save(){
        String campaignId = "1000001";
        String jsonData = "[{\n" +
                "\t\"name\": \"执行计划1\",\n" +
                "\t\"planId\": 1,\n" +
                "\t\"campId\": 1000001,\n" +
                "\t\"actionNodes\": [{\"nodeId\":\"10901201\",\"nodeType\":\"tdiscountECANTA\"}],\n" +
                "\t\"communicationNodes\": [{\"nodeId\":\"10901202\",\"nodeType\":\" tcommunicateSMS\"}]\n" +
                "}, {\n" +
                "\t\"name\": \"执行计划2\",\n" +
                "\t\"planId\": 2,\n" +
                "\t\"campId\": 1000001,\n" +
                "   \"actionNodes\": [{\"nodeId\":\"10901203\",\"nodeType\":\" tdiscountISANTA\"}],\n" +
                "\t\"communicationNodes\": [{\"nodeId\":\"10901204\",\"nodeType\":\" tcommunicateEDM\"}]\n" +
                "}]\n";
        log.info("扁平化执行计划管理save.campaignId={}.jsonData={}.", campaignId, jsonData);
        JSONArray jsonArray = JSONArray.parseArray(jsonData);
        List<Object[]> insertParam = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = (JSONObject) jsonArray.get(i);
            String jsonStr = JSON.toJSONString(obj);
            insertParam.add(new Object[]{campaignId, obj.get("name"), i + 1, jsonStr, "sfc", format.format(new Date())});
        }
        int[] count = jdbcTemplate.batchUpdate(insertSql, insertParam);
        log.info("count=={}.", count.length);
    }

    @RequestMapping("/savePlan")
    public void savePlan(){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String insert = "insert into campaign_plan(campaign_id,campaign_name,content) values(?,?,?)";
        List<Object[]> insertParam = new ArrayList<>();
        insertParam.add(new Object[]{"shao", "shao", "body"});
        jdbcTemplate.update(insert,insertParam,keyHolder);
        System.out.println("看下能否拿到最终的自增的id-------------->" + keyHolder.getKey().longValue());
    }

    @RequestMapping("/savePlans")
    public Map saveUserWithKey() throws  Exception{
        String sql="insert into campaign_plan(campaign_id,campaign_name,content) values(?,?,?)";
        Object[] o = new Object[]{"shao", "shao", "body"};
        System.out.println(">>>"+o.length);
        KeyHolder keyHolder=new GeneratedKeyHolder();
        int resRow=jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql,new String[]{"id"}); //指定 id 为主键
                ps.setString(1, o[0].toString());
                ps.setString(2, o[1].toString());
                ps.setString(3, o[2].toString());
                return ps;
            }
        },keyHolder);
        System.out.println("操作结果记录数：  "+resRow+" 主键: "+keyHolder.getKey());
        Map<String, Object> map = new HashMap();
        List<Integer> listPlanId = new ArrayList<>();
        listPlanId.add(Integer.parseInt(keyHolder.getKey().toString()));
        listPlanId.add(1000);
        map.put("planId", listPlanId);
        return map;
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

    @PostMapping(value = "/fileUpload")
    public String fileUpload(@RequestParam("fileName") MultipartFile file){
        log.info(">>>>>",file.getName());
        return "";
    }


}
