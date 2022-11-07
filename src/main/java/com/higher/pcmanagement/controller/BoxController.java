package com.higher.pcmanagement.controller;

import com.higher.pcmanagement.pojo.Box;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.service.BoxService;
import com.higher.pcmanagement.util.ResultCodeEnum;
import com.higher.pcmanagement.util.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/box")
public class BoxController {
    @Autowired
    BoxService boxService;

    @PostMapping("/getPageBox.do")
    public ResultModel<PageResultBo<Box>> getPageBox(@RequestBody PageRequestBo pageRequestBo){
        PageResultBo<Box> box= boxService.getPageBox(pageRequestBo);
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, box, "");
    }

    @PostMapping("/deleteBox.do")
    public ResultModel<Box> deleteBox(Box box){
        boxService.deleteBox(box.getBoxId());
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, box, "");
    }

    @PostMapping("/addBox.do")
    public ResultModel<Box> addBox(@RequestBody Box box){
        boxService.addBox(box);
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, box, "");
    }

    @PostMapping("/updateBox.do")
    public ResultModel<Box> updateBox(@RequestBody Box box){
        boxService.updateBox(box);
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, box, "");
    }
}
