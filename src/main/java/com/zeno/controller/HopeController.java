package com.zeno.controller;

import com.zeno.model.CountRecord;
import com.zeno.model.PlayRecord;
import com.zeno.model.VisitRecord;
import com.zeno.service.PlayRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: zeno-hope-zj
 * @description:
 * @author: Mr.Zeno
 * @create: 2020-05-22 12:05
 **/
@Controller
public class HopeController {


    @Autowired
    private PlayRecordService playRecordService;

    @RequestMapping(value = "/record/play",method = RequestMethod.GET)
    @ResponseBody
    public String recordPlay(HttpServletRequest request){
        if (playRecordService.addPlayRecord(request)){
            return "0";
        }else {
            return "-1";
        }
    }

    @RequestMapping(value = "/record/visit",method = RequestMethod.GET)
    @ResponseBody
    public String visitRecord(HttpServletRequest request){
        if (playRecordService.addVisitRecord(request)){
            return "0";
        }else {
            return "-1";
        }
    }

    @RequestMapping(value = "play/list",method = RequestMethod.GET)
    @ResponseBody
    public List<PlayRecord> getPlayRecord(){
        return playRecordService.getPlayRecord();
    }


    @RequestMapping(value = "visit/list",method = RequestMethod.GET)
    @ResponseBody
    public List<VisitRecord> getVisitRecord(){
        return playRecordService.getVisitRecord();
    }

    @RequestMapping(value = "count/list",method = RequestMethod.GET)
    @ResponseBody
    public List<CountRecord> getCountRecord(){
        return playRecordService.getCountRecord();
    }


}
