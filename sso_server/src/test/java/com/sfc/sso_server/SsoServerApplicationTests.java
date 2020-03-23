package com.sfc.sso_server;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SsoServerApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /*@Test
    public void setRedis() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        //redis锁
        System.out.println(stringRedisTemplate.opsForValue().setIfAbsent("order_100000", "10000"));
        //设置超时时间
        stringRedisTemplate.opsForValue().set("test", "100",60*10, TimeUnit.SECONDS);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }*/




    @Test
    public void test() {
        int[] nums = {2,7,11,15,3,6,8};
        int target = 9;
        Object[] num = this.twoSum(nums,target);
        for(Object n:num){
            System.out.println("获取到和为9下标分别是：" + n);
        }
    }
    public Object[] twoSum(int[] nums, int target){
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<nums.length;i++){
            System.out.println("获取开始比的数字："+nums[i]);
            if((i+1)<nums.length && nums[i]+nums[i+1]==target) {
                list.add(i);
                list.add(i+1);
            }
            i=i+1;
        }
        Object[] num = list.toArray();
        return num;
    }

    @Test
    public void test2() {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        Arrays.sort(nums);
        int z=0;
        int j=0;
        for(int i = 0; i < nums.length-1; ++i){
            if(nums[i] != nums[i + 1]){
                if(z < j){
                    nums[z+1] = nums[j+1];
                    z++;
                    j++;
                }else{
                    z = i + 1;
                }
            }else{
                j = i + 1;
            }
        }
        for(int i = 0; i <= z; ++i){
            System.out.println("去重后的数字" + nums[i]);
        }
        System.out.println("去重后的数组长度" + (z+1));
    }

    @Test
    public void test3() {
        int[][] a;
        int n =5;
        a=new int[n][n];
        for(int i=0;i<n;i++){
            a[i][0]=1;
            a[i][i]=1;//第一行输出1，第二行输出1 1
            if(i>=2){//下面当行数大于2的时候开始计算第n行的第一位到最后一位之间的数据
                for(int j=1;j<i;j++) {
                    a[i][j] = a[i - 1][j] + a[i - 1][j - 1];/*这之间的每个
           数为上一行的同一列数和上遗憾的同一列数的前一列数之和，
           这样二维数据就完整的记录了杨辉三角形。*/
                }
            }
        }
        //下面是实现打印二维数组，即打印杨辉三角形
        for(int i=0;i<n;i++) {//打印n行
            for (int j = 0; j <= i; j++) {//打印每一行的里面的数据
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void test4(){
        String ruleId = null;
        if(ruleId!=null){
            System.out.println("!=null");
        }else{
            System.out.println("==null");
        }
    }

    @Test
    public void test5(){
        String ruleId = "CREATE TABLE tmp_groupquery_inner_950db48cf0524f8aadcd75d9953f0d99 STORED AS PARQUET AS  SELECT DISTINCT t0.member_id FROM plt_anta_member t0 INNER JOIN plt_anta_member_loyalty t1 ON t0.member_id = t1.member_id INNER JOIN tmp_meta_85008588258444d9926da9e627e66dda_1_Intersect_1 t2 ON t2.member_id = t0.member_id WHERE  ( t0.mobile = '18291957767' ) AND  ( t1.grade IN ('7') ) AND  ( exists (select * from shop_selector_result ruleResult where ruleResult.rules_id = 'c978d792-2fec-4771-a18c-19dd30657429' and ruleResult.datas = t0.reg_store_id) ) and （t0.mobile = '17610330766'）";
        String id = "000000-2fec-4771-a18c-19dd30657429";
        if(ruleId.indexOf("ruleResult.rules_id = '") > 0){
            String replaces = ruleId.substring(ruleId.indexOf("ruleResult.rules_id = '")+23);
            System.out.println("replaces=" + replaces);
            replaces = replaces.substring(0,replaces.indexOf("'"));

            System.out.println("replaces2=" + replaces);

            ruleId = ruleId.replace(replaces,id);

            ruleId = ruleId + "000";
            System.out.println(ruleId);
        }
    }

    @Test
    public void test6(){
        /*String ruleId = "{\"relationOperation\":\"AND\",\"globalFilterList\":[1,2],\"propertyConditionList\":[],\"behaviorConditionList\":[],\"groupingConditionList\":[{\"groupingConditionList\":[],\"propertyConditionList\":[{\"key\":\"normal_model@plt_anta_member.fullname\",\"operator\":\"Equal\",\"singleValueParameter\":{\"value\":\"sfc\",\"queryType\":\"字符输入\"}}],\"computeByResult\":false},{\"groupingConditionList\":[],\"propertyConditionList\":[{\"key\":\"normal_model@plt_anta_member.mobile\",\"operator\":\"Equal\",\"singleValueParameter\":{\"value\":\"17610330766\",\"queryType\":\"字符输入\"}}],\"computeByResult\":false},{\"groupingConditionList\":[],\"propertyConditionList\":[{\"key\":\"normal_model@plt_anta_member.reg_store_id\",\"operator\":\"In\",\"multiValueParameter\":{\"operator\":\"AND\",\"values\":[\"nodeRuleId\",\"view\",\"nodeRuleId:9c57a46a-2449-4a2d-809a-91d055b55ccb\"],\"queryType\":\"数据选择器类型\"}}],\"computeByResult\":false}],\"campaignConditionList\":[]}";
        JSONObject paramsObject = JSONObject.parseObject(ruleId);
        System.out.println(paramsObject);
        System.out.println(paramsObject.get("relationOperation"));*/
        int a = (int) (Math.random()*(300-100+1)+400);
        int b = (int) (Math.random()*(300-100+1)+400);
        int c = (int) (Math.random()*(300-100+1)+400);
        System.out.println(a + "-" + b + "-" + c);

        int aa = (int) (Math.random()*(0-100+1)+300);
        int bb = (int) (Math.random()*(0-100+1)+300);
        int cc = (int) (Math.random()*(0-100+1)+400);
        System.out.println(aa + "-" + bb + "-" + cc);
    }

}
