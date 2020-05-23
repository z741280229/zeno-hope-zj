package com.zeno.service.impl;

import com.zeno.mapper.PlayRecordMapper;
import com.zeno.model.PlayRecord;
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
    private PlayRecordMapper playRecordMapper;


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
            if (flag > 0){
                logger.info("record is success");
                return true;
            }else {
                logger.info("record is fail");
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
        return playRecordMapper.getPlayRecord();
    }
}
