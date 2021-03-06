package com.tomasky.doms.service;

import com.tomasky.doms.dto.push.SyncRoomCountBo;
import com.tomasky.doms.dto.push.SyncRoomPriceBo;

import java.util.List;
import java.util.Map;

/**
 * Create by jame
 * Date: 2016/6/12
 * Version: 1.0
 * Description: 同步去呼呼房态接口
 */
public interface IQunarRoomStatusPushService {
    /**
     * 同步去呼呼单房量接口
     * @param syncRoomCountBo
     */
    void syncRoomCount(SyncRoomCountBo syncRoomCountBo);

    /**
     * 同步去呼呼单房价接口
     * @param syncRoomPriceBo
     */
    void syncRoomPrice(SyncRoomPriceBo syncRoomPriceBo);

    /**
     * 批量同步去呼呼房价接口
     * @param syncRoomPriceBos
     */
    void batchSyncRoomPrice(List<SyncRoomPriceBo> syncRoomPriceBos);
}
