package com.zeno.mapper;

import com.zeno.model.PlayRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: zeno-hope-zj
 * @description:
 * @author: Mr.Zeno
 * @create: 2020-05-22 12:14
 **/
public interface PlayRecordMapper {

    int addPlayRecord(@Param("ip") String ip, @Param("time") String time);

    List<PlayRecord> getPlayRecord();
}
