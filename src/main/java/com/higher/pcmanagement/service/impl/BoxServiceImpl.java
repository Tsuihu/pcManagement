package com.higher.pcmanagement.service.impl;

import com.higher.pcmanagement.dao.BoxDao;
import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Box;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.BoxRequestBo;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.service.BoxService;
import com.higher.pcmanagement.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

//@Service
@Component
public class BoxServiceImpl implements BoxService {
    @Autowired
    BoxDao boxDao;

    /**
     * 分页查询
     * @param pageRequestBo
     * @return
     */
    @Override
    public PageResultBo<BoxRequestBo> getPageBox(PageRequestBo pageRequestBo) {
        if(pageRequestBo.getPageIndex()<0){
            pageRequestBo.setPageIndex(0);
        }
        List<BoxRequestBo> list = boxDao.getPageBox(pageRequestBo);
        Integer count = boxDao.getPageBoxCount();
        PageResultBo<BoxRequestBo> pageResultBo=new PageResultBo<>(list,count);
        return pageResultBo;
    }

    /**
     * 删除箱子
     * @param boxId
     */
    @Override
    public void deleteBox(Integer boxId) {
        boxDao.deleteBox(boxId);
    }

    /**
     * 添加箱子
     * @param boxRequestBo
     */
    @Override
    public void addBox(BoxRequestBo boxRequestBo) throws BusinessException {
        if (boxDao.getCountByBoxcode(boxRequestBo.getBoxCode())<1){
            if(boxDao.getCountByname(boxRequestBo.getName())>=1){
                if (boxDao.getCountByPointName(boxRequestBo.getPointName())>=1){
                    int collectorId = boxDao.getcollectorIdByname(boxRequestBo.getName());
                    int pointId = boxDao.getpointIdByPointName(boxRequestBo.getPointName());
                    boxRequestBo.setOpenTime(new Date());
                    boxRequestBo.setCollectorId(collectorId);
                    boxRequestBo.setPointId(pointId);
                    boxRequestBo.setStatus("0");
                    boxDao.addBox(boxRequestBo);
                }else {
                    throw new BusinessException("该检测地点不存在", ResultCodeEnum.ERROR);
                }
            }else {
                throw new BusinessException("该检测人员名字不存在", ResultCodeEnum.ERROR);
            }
        }else {
            throw new BusinessException("该箱编码已存在", ResultCodeEnum.ERROR);
        }
    }

    /**
     * 修改箱子
     * @param boxRequestBo
     */
    @Override
    public void updateBox(BoxRequestBo boxRequestBo) throws BusinessException {
//        通过前端返回的Boxid，获取此id修改前的所有数据
        Box box = boxDao.getboxByBoxId(boxRequestBo.getBoxId());
//        获取修改前的状态
        String status = box.getStatus();
//        将之前的开箱时间赋值给现在修改的开箱时间,因为开箱时间不会便
        boxRequestBo.setOpenTime(box.getOpenTime());
//        进行判断,如果现在输入的箱子编号与之前的不一样,并且,现在输入的箱子编号在数据库中存在,则抛异常
        if (!box.getBoxCode().equals(boxRequestBo.getBoxCode()) && boxDao.getCountByBoxcode(boxRequestBo.getBoxCode())>0){
            throw new BusinessException("该箱子编号已存在", ResultCodeEnum.ERROR);
        }//如果,现在输入的箱子编号和之前的一样,或者,(现在输入的箱子编号与之前的不一样,并且现在输入的箱子编号在数据库中没有重复),则可以修改
        else if (box.getBoxCode().equals(boxRequestBo.getBoxCode()) || (!box.getBoxCode().equals(boxRequestBo.getBoxCode()) && boxDao.getCountByBoxcode(boxRequestBo.getBoxCode())==0)){

//        判断修改后的状态跟原先的一不一样,设置封箱时间
            if (status.equals(boxRequestBo.getStatus())){
                boxRequestBo.setCloseTime(box.getCloseTime());
            }else if(!status.equals(boxRequestBo.getStatus()) && boxRequestBo.getStatus().equals("0")){
                boxRequestBo.setCloseTime(null);
            }else if (!status.equals(boxRequestBo.getStatus()) && boxRequestBo.getStatus().equals("1")){
                boxRequestBo.setCloseTime(new Date());
            }

//            判断地点和检测人员是否存在
            if(boxDao.getCountByPointName(boxRequestBo.getPointName())>0){
                if (boxDao.getCountByname(boxRequestBo.getName())>0){
                    int collectorId = boxDao.getcollectorIdByname(boxRequestBo.getName());
                    int pointId = boxDao.getpointIdByPointName(boxRequestBo.getPointName());
                    boxRequestBo.setCollectorId(collectorId);
                    boxRequestBo.setPointId(pointId);
                    boxDao.updateBox(boxRequestBo);
                }else {
                    throw new BusinessException("该检测人员不存在", ResultCodeEnum.ERROR);
                }
            }else {
                throw new BusinessException("该检测地点不存在", ResultCodeEnum.ERROR);
            }
        }
    }
}

