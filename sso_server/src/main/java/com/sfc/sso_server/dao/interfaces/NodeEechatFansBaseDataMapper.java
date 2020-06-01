package com.sfc.sso_server.dao.interfaces;

import com.sfc.sso_server.entity.NodeEechatFansBaseData;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeEechatFansBaseDataMapper {

    int insert(NodeEechatFansBaseData nodeEechatFansBaseData);

}