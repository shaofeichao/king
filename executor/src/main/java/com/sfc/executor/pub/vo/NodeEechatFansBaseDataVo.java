package com.sfc.executor.pub.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NodeEechatFansBaseDataVo {
    private String nodeCode;//节点编号
    private String nodeName;//节点名称
    private String subject;//节点主题
    private String messageType;//节点消息类型1图文消息2文字3图片
    private String authAppid;//1图文:授权公众号appid
    private String materialId;//1图文:素材id
    private String materialName;//1图文:素材名称
    private String materialType;//1图文:素材类型
    private String textBody;//2文字:文字消息内容
    private String imageUrl;//3图片:图片地址
    private String outType;//输出控制，only_success(仅发送成功)，all(全部)
    private String remark;//备注
    private String createTime;//创建时间
    private Date updateTime;//修改时间
}