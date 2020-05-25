package com.zeno.service.impl;

import com.zeno.mapper.RecordMapper;
import com.zeno.model.CountRecord;
import com.zeno.model.PlayRecord;
import com.zeno.model.VisitRecord;
import com.zeno.service.PlayRecordService;
import com.zeno.utils.BaseUtil;
import com.zeno.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @program: zeno-hope-zj
 * @description:
 * @author: Mr.Zeno
 * @create: 2020-05-22 12:12
 **/
@Service
public class PlayRecordServiceImpl implements PlayRecordService {


    private static final Logger logger = LoggerFactory.getLogger(PlayRecordServiceImpl.class);
    public static final String TAG = PlayRecordServiceImpl.class.getSimpleName();

    @Autowired
    private RecordMapper playRecordMapper;


    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public boolean addPlayRecord(HttpServletRequest request) {
        try {
            String ip = BaseUtil.getIP(request);
            String time = DateUtil.formatDate(new Date());
            if (ip == null || "".equals(ip)){
                logger.info("ip is null");
            }
            if (time == null || "".equals(time)){
                logger.info("time is null");
            }
            logger.info("ip is {},time is {}",ip,time);
            int flag = playRecordMapper.addPlayRecord(ip, time);
            boolean res = countRecord(ip, time, 1);
            if (flag > 0 && res){
                logger.info("play record and count is success");
                return true;
            }else {
                logger.info("play record and count is fail");
                return false;
            }
        }catch (Exception e){
            logger.error(TAG,e);
            return false;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public boolean addVisitRecord(HttpServletRequest request) {
        try {
            String ip = BaseUtil.getIP(request);
            String time = DateUtil.formatDate(new Date());
            if (ip == null || "".equals(ip)){
                logger.info("ip is null");
            }
            if (time == null || "".equals(time)){
                logger.info("time is null");
            }
            logger.info("ip is {},time is {}",ip,time);
            int flag = playRecordMapper.addVisitRecord(ip, time);
            boolean res = countRecord(ip, time, 0);
            if (flag > 0 && res){
                logger.info("visit record and count is success");
                return true;
            }else {
                logger.info("visit record and count is fail");
                return false;
            }
        }catch (Exception e){
            logger.error(TAG,e);
            return false;
        }
    }

    @Override
    public List<PlayRecord> getPlayRecord() {
        logger.info("get record list");
        return playRecordMapper.getAllPlayRecord();
    }

    @Override
    public List<CountRecord> getCountRecord() {
        logger.info("get count list");
        return playRecordMapper.getAllCountRecord();
    }

    @Override
    public List<VisitRecord> getVisitRecord() {
        logger.info("get visit list");
        return playRecordMapper.getAllVisitRecord();
    }


    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    private boolean countRecord(String ip, String time,Integer type) {
        CountRecord countRecord = playRecordMapper.getCountRecord(type);
        if (countRecord != null){
            countRecord.setType(type);
            countRecord.setOprCount(countRecord.getOprCount() + 1);
            countRecord.setCurTime(time);
            countRecord.setIp(ip);
            int i = playRecordMapper.updateCountRecord(countRecord);
            if (i > 0){
                logger.info(countRecord.toString());
                logger.info("update play count is success");
                return true;
            }else {
                logger.info(countRecord.toString());
                logger.info("update play count is fail");
                return false;
            }
        }else {
            CountRecord record = new CountRecord();
            record.setIp(ip);
            record.setType(type);
            record.setCurTime(time);
            record.setOprCount(1);
            if (type != null && type.equals(1)){
                record.setTypeName("play record");
                int i = playRecordMapper.addCountRecord(record);
                if (i > 0){
                    logger.info("insert play record first is success");
                    return true;
                }else {
                    logger.info("insert play record first is fail");
                    return false;
                }
            }

            if (type != null && type.equals(0)){
                record.setTypeName("visit record");
                int i = playRecordMapper.addCountRecord(record);
                if (i > 0){
                    logger.info("insert visit record first is success");
                    return true;
                }else {
                    logger.info("insert visit record first is fail");
                    return false;
                }
            }
            logger.info("instert record first is fail");
            return false;
        }
    }


}
