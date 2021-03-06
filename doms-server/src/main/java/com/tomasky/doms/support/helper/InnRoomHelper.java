package com.tomasky.doms.support.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fanqie.util.HttpClientUtil;
import com.fanqie.util.JacksonUtil;
import com.fanqie.util.TomsConstants;
import com.tomasky.doms.model.RoomDetail;
import com.tomasky.doms.model.RoomStatusDetail;
import com.tomasky.doms.model.RoomTypeInfo;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * DESC : 获取oms客栈 房型  房态数据
 *
 * @author : 番茄木-ZLin
 * @data : 2015/12/17
 * @version: v1.0.0
 */
public class InnRoomHelper {

    private static final Logger log = LoggerFactory.getLogger(InnRoomHelper.class);

    /**
     * 获取oms房态数据
     *
     * @param roomStatusUrl oms房态url
     * @throws IOException
     */
    public static List<RoomStatusDetail> getRoomStatus(String roomStatusUrl) throws IOException {
        String httpGets = HttpClientUtil.httpGets(roomStatusUrl, null);
        JSONObject jsonObject = JSONObject.fromObject(httpGets);
        if (TomsConstants.SUCCESS.equals(jsonObject.get("status").toString()) && jsonObject.get("list") != null) {
            return JSON.parseObject(jsonObject.getString("list"), new TypeReference<List<RoomStatusDetail>>() {
            });
//            return  JacksonUtil.json2list(jsonObject.get("list").toString(), RoomStatusDetail.class);
        } else {
            log.info("oms 房态返回错误：" + jsonObject.get("message"));
        }
        return null;
    }

    /**
     * 获取oms房型数据
     *
     * @param roomTypeUrl oms房型 url
     * @throws IOException
     */
    public static List<RoomTypeInfo> getRoomTypeInfo(String roomTypeUrl) throws IOException {
        String roomTypeGets = HttpClientUtil.httpGets(roomTypeUrl, null);
        JSONObject jsonObject = JSONObject.fromObject(roomTypeGets);
        //房型
        if (TomsConstants.SUCCESS.equals(jsonObject.get("status").toString()) && jsonObject.get("list") != null) {
            return JSON.parseObject(jsonObject.getString("list"), new TypeReference<List<RoomTypeInfo>>() {
            });
//            return JacksonUtil.json2list(jsonObject.get("list").toString(), RoomTypeInfo.class);
        } else {
            log.info("oms 房型返回错误：" + jsonObject.get("message"));
        }
        return null;
    }


    /**
     * 更新每一个房型的房态
     *
     * @param list          房型集合
     * @param statusDetails
     */
    public static void updateRoomTypeInfo(List<RoomTypeInfo> list, List<RoomStatusDetail> statusDetails) {

        if (!CollectionUtils.isEmpty(list) && !CollectionUtils.isEmpty(statusDetails)) {
            for (RoomTypeInfo roomTypeInfo : list) {
                for (RoomStatusDetail detail : statusDetails) {
                    if (roomTypeInfo.getRoomTypeId().equals(detail.getRoomTypeId())) {
                        roomTypeInfo.setRoomDetail(detail.getRoomDetail());
                        roomTypeInfo.setRatePlanCode(detail.getRatePlanCode());
                    }
                }
            }
        }
    }

    /**
     * 获取oms 某一个房型的房态信息
     *
     * @param checkRoomUrl 单个房型 房态信息
     * @return
     * @throws IOException
     */
    public static List<RoomDetail> getRoomDetail(String checkRoomUrl) throws IOException {
        String roomTypeGets = HttpClientUtil.httpGets(checkRoomUrl, null);
        JSONObject jsonObject = JSONObject.fromObject(roomTypeGets);
        if (TomsConstants.SUCCESS.equals(jsonObject.get("status").toString())) {
            Object o1 = jsonObject.get("data");
            if (!JSONNull.getInstance().equals(o1)) {
                String data = jsonObject.getJSONArray("data").toString();
                if (!StringUtils.isEmpty(data)) {
                    return JacksonUtil.json2list(jsonObject.getJSONArray("data").toString(), RoomDetail.class);
                }
            }
        }
        return null;
    }


}
