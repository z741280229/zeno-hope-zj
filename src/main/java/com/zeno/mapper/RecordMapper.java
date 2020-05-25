package com.zeno.mapper;

import com.zeno.model.CountRecord;
import com.zeno.model.PlayRecord;
import com.zeno.model.VisitRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: zeno-hope-zj
 * @description:
 * @author: Mr.Zeno
 * @create: 2020-05-22 12:14
 **/
public interface RecordMapper {

    int addPlayRecord(@Param("ip") String ip, @Param("time") String time);

    int addVisitRecord(@Param("ip") String ip, @Param("time") String time);

    int addCountRecord(@Param("record") CountRecord record);

    int updateCountRecord(@Param("record") CountRecord record);

    CountRecord getCountRecord(@Param("type") Integer type);

    List<PlayRecord> getAllPlayRecord();

    List<CountRecord> getAllCountRecord();

    List<VisitRecord> getAllVisitRecord();
}
