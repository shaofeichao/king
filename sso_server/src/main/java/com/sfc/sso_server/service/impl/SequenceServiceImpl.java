package com.sfc.sso_server.service.impl;

import com.sfc.sso_server.dao.interfaces.SequenceMapper;
import com.sfc.sso_server.service.interfaces.ISequenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SequenceServiceImpl implements ISequenceService {

    @Autowired
    private SequenceMapper sequenceMapper;

    @Override
    public String getCardNo() {
        return sequenceMapper.getCardNo();
    }
}