package com.zeno.service;


import com.zeno.model.CountRecord;
import com.zeno.model.PlayRecord;
import com.zeno.model.VisitRecord;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: zeno-hope-zj
 * @description:
 * @author: Mr.Zeno
 * @create: 2020-05-22 12:11
 **/
public interface PlayRecordService {

    boolean addPlayRecord(HttpServletRequest request);

    boolean addVisitRecord(HttpServletRequest request);

    List<PlayRecord> getPlayRecord();

    List<CountRecord> getCountRecord();

    List<VisitRecord> getVisitRecord();
}
