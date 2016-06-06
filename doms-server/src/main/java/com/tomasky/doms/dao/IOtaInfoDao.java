package com.tomasky.doms.dao;

import com.tomasky.doms.dto.qunar.OtaInfoRefDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * DESC : 渠道
 * @author : 番茄木-ZLin
 * @data : 2015/6/19
 * @version: v1.0.0
 */
public interface IOtaInfoDao {

    //查询所有企业开通的ota ，以及OTA_ID
    List<OtaInfoRefDto> selectOtaInfoList();
}
