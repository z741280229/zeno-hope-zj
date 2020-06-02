package com.zeno.service.impl;

import com.zeno.mapper.RecordMapper;
import com.zeno.model.CountRecord;
import com.zeno.model.PlayRecord;
import com.zeno.model.VisitRecord;
import com.zeno.service.PlayRecordService;
import com.zeno.utils.BaseUtil;
import com.zeno.utils.DateUtil;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


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
        }catch (DataAccessException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error(TAG,e);
            return false;
        }catch (NullPointerException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
        }catch (DataAccessException e ){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error(TAG,e);
            return false;
        }catch (NullPointerException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error(TAG,e);
            return false;
        }
    }


    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    private boolean countRecord(String ip, String time,Integer type) throws DataAccessException{
        if (type == null || ip == null || "".equals(ip)){
            logger.info("type or ip is null");
            return false;
        }
        CountRecord countRecord = playRecordMapper.getCountRecord(type,ip);
        if (countRecord != null){
            countRecord.setType(type);
            if(countRecord.getOprCount() == null){
                logger.info("oprCount is null");
                return false;
            }
            countRecord.setOprCount(countRecord.getOprCount() + 1);
            countRecord.setCurTime(time);
            countRecord.setIp(ip);
            int i = playRecordMapper.updateCountRecord(countRecord);
            if (i > 0){
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
            if (record.getType()!= null && record.getType().equals(1)){
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

            if (record.getType() != null && record.getType().equals(0)){
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



    @Override
    public List<PlayRecord> getPlayRecord() {
        try{
            logger.info("get record list");
            return playRecordMapper.getAllPlayRecord();
        }catch (DataAccessException e){
            logger.error("get play record is fail",e);
            return null;
        }

    }

    @Override
    public List<CountRecord> getCountRecord() {
        try{
            logger.info("get count list");
            return playRecordMapper.getAllCountRecord();
        }catch (DataAccessException e){
            logger.info("get count list is fail",e);
            return null;
        }
    }

    @Override
    public List<VisitRecord> getVisitRecord() {
        try{
            logger.info("get visit list");
            return playRecordMapper.getAllVisitRecord();
        }catch (DataAccessException e){
            logger.error("get visit list is fail",e);
            return null;
        }

    }

}
