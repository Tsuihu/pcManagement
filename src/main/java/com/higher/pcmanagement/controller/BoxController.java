package com.higher.pcmanagement.controller;

import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Box;
import com.higher.pcmanagement.pojo.People;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.BoxRequestBo;
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

    /**
     * 分页查询
     * @param pageRequestBo pageIndex：第几页，pageSize：每页多少条
     * @return
     */
    @PostMapping("/getPageBox.do")
    public ResultModel<PageResultBo<BoxRequestBo>> getPageBox(@RequestBody PageRequestBo pageRequestBo){
        PageResultBo<BoxRequestBo> box= boxService.getPageBox(pageRequestBo);
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, box, "分页查询成功");
    }

    /**
     * 箱子编码的全查
     * @return
     */
    @PostMapping("/getAllBoxCode.do")
    public ResultModel<List<Box>> getAllBoxCode(){
        List<Box> listBoxCode=boxService.getAllBoxCode();
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, listBoxCode, "全查箱子编号成功");
    }



    /**
     * 删除箱子
     * @param box boxId:需要前端传递
     * @return
     */
    @PostMapping("/deleteBox.do")
    public ResultModel<Box> deleteBox(Box box){
        boxService.deleteBox(box.getBoxId());
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, box, "删除成功");
    }

    /**
     * 添加箱子
     * @param boxRequestBo，里面的所有参数都需要前端传递,包括检测人名字，检测地点名字(boxId,name,pointName,status)
     * @return
     */
    @PostMapping("/addBox.do")
    public ResultModel<BoxRequestBo> addBox(@RequestBody BoxRequestBo boxRequestBo) throws BusinessException {
        boxService.addBox(boxRequestBo);
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, boxRequestBo, "添加成功");
    }

    /**
     * 修改箱子信息
     * @param boxRequestBo,修改的参数都需要前端传递
     * @return
     */
    @PostMapping("/updateBox.do")
    public ResultModel<BoxRequestBo> updateBox(@RequestBody BoxRequestBo boxRequestBo) throws BusinessException {
        boxRequestBo.setTesterId(1);
        boxRequestBo.setTransferId(1);
        boxRequestBo.setTestOrganiationId(1);
        boxService.updateBox(boxRequestBo);
        return  new ResultModel<>(ResultCodeEnum.SUCCESS, boxRequestBo, "修改成功");
    }

    /**
     * 模糊查询
     * @param boxCode ，根据编码模糊查询
     * @return
     */
    @PostMapping("getLikeCode.do")
    ResultModel<List<BoxRequestBo>> getLikeCode(String boxCode){
        List<BoxRequestBo> likeCode = boxService.getLikeCode(boxCode);
        return new ResultModel<>(ResultCodeEnum.SUCCESS,likeCode,"条件查询成功");
    }
}
