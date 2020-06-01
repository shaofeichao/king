package com.sfc.sso_server.dao.interfaces;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SequenceMapper {
    String getCardNo();
}