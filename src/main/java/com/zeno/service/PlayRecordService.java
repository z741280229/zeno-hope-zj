package com.zeno.service;


import com.zeno.model.PlayRecord;

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

    List<PlayRecord> getPlayRecord();
}
