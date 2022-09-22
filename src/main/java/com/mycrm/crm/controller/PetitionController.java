package com.mycrm.crm.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycrm.crm.entity.Operator;
import com.mycrm.crm.entity.Petition;
import com.mycrm.crm.entity.PetitionVo;
import com.mycrm.crm.service.OperatorService;
import com.mycrm.crm.service.PetitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/petition")
@Api(tags = "投诉模块控制器")

public class PetitionController {
    @Autowired
    PetitionService petitionService;
    @Autowired
    OperatorService operatorService;

    @PostMapping("add")
    @ApiOperation(value = "添加投诉", notes = "增加一条投诉信息")
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

    @GetMapping("list")
    @ApiOperation(value = "查询已经处理操作", notes = "查询已经处理投诉信息")
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
    @ApiOperation(value = "查询未处理操作", notes = "查询未处理投诉信息")

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


    @PutMapping("addoutcome")
    @ApiOperation(value = "更新处理结果", notes = "更新他投诉信息的投诉结果")
    public Object addoutcome(@RequestBody Map<String, String> map)  {
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


    @GetMapping("getOper")
    @ApiOperation(value = "获得职工信息", notes = "获得职工信息")
    public Object getOper() {
        Map Rsmap = new HashMap();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("oid", "oname");
        List<Operator> list = operatorService.list(wrapper);
        Rsmap.put("data", list);
        Rsmap.put("code", 0001);// 0001添加成功
        return Rsmap;

    }


    @DeleteMapping("delete")
    @ApiOperation(value = "入库操作", notes = "增加一条入库信息")
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
