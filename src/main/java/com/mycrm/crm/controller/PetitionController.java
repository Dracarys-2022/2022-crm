package com.mycrm.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Operator;
import com.mycrm.crm.entity.Petition;
import com.mycrm.crm.entity.PetitionVo;
import com.mycrm.crm.service.OperatorService;
import com.mycrm.crm.service.PetitionService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/petition")
public class PetitionController {
    @Autowired
    PetitionService petitionService;
    @Autowired
    OperatorService operatorService;

    @RequestMapping("add")
    public Object add(@RequestBody Petition petition) {
        Map Rsmap = new HashMap();
        if (petitionService.save(petition)) {
            Rsmap.put("code", 0001);// 0001添加成功
            Rsmap.put("msg", "信息记录成功");
        } else {
            Rsmap.put("code", 0002);// 0002添加失败
            Rsmap.put("msg", "信息记录失败");
        }
        return Rsmap;
    }

    @RequestMapping("list")
    public Object list(@RequestParam int current, @RequestParam int size) {
        Map Rsmap = new HashMap();
        int total = petitionService.total();
        Page<PetitionVo> page = new Page<>(current, size);
        IPage<PetitionVo> list = petitionService.listPetition(page);
        int num = petitionService.num();
        Rsmap.put("overtotal", total);
        Rsmap.put("num", num);
        Rsmap.put("total", total);
        Rsmap.put("data", list);
        Rsmap.put("code", 0001);// 0001添加成功
        Rsmap.put("msg", "查询成功");
        return Rsmap;
    }

    @RequestMapping("listNull")
    public Object listNull(@RequestParam int current, @RequestParam int size) {
        Map Rsmap = new HashMap();
        int total = petitionService.totalNull();
        Page<PetitionVo> page = new Page<>(current, size);
        IPage<PetitionVo> list = petitionService.listPetitionNull(page);
        int num = petitionService.num();
        int overtotal = petitionService.total();
        Rsmap.put("overtotal", overtotal);
        Rsmap.put("num", num);
        Rsmap.put("total", total);
        Rsmap.put("data", list);
        Rsmap.put("code", 0001);// 0001添加成功
        Rsmap.put("msg", "查询成功");
        return Rsmap;
    }

    @RequestMapping("addoutcome")
    public Object addoutcome(@RequestBody Map<String, String> map) throws JSONException {
        String peid = map.get("peid");
        String outcome = map.get("outcome");
        System.out.println(peid + outcome);
        Map Rsmap = new HashMap();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("peid", peid);
        Petition petition = petitionService.getOne(wrapper);
        petition.setOutcome(outcome);
        petitionService.update(petition, wrapper);
        Rsmap.put("code", 0001);// 0001添加成功
        Rsmap.put("msg", "录入结果成功");
        return Rsmap;
    }

    @RequestMapping("getOper")
    public Object getOper() {
        Map Rsmap = new HashMap();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("oid", "oname");
        List<Operator> list = operatorService.list(wrapper);
        Rsmap.put("data", list);
        Rsmap.put("code", 0001);// 0001添加成功
        return Rsmap;

    }

    @RequestMapping("delete")
    public Object delete(@RequestParam int peid,@RequestParam int current, @RequestParam int size) {
        Map Rsmap = new HashMap();
        QueryWrapper<Petition> wrapper = new QueryWrapper<Petition>();
        wrapper.eq("peid", peid);
        if (petitionService.remove(wrapper))
            Rsmap.put("code", 0001);
        else
            Rsmap.put("code", 0002);
        int total = petitionService.totalNull();
        Page<PetitionVo> page = new Page<>(current, size);
        IPage<PetitionVo> list = petitionService.listPetitionNull(page);
        int num = petitionService.num();
        int overtotal = petitionService.total();
        Rsmap.put("overtotal", overtotal);
        Rsmap.put("num", num);
        Rsmap.put("total", total);
        Rsmap.put("data", list);
        return Rsmap;
    }
}
