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
        Box box = boxDao.getboxByBoxCode(boxRequestBo.getBoxCode());
        String status = box.getStatus();
        boxRequestBo.setOpenTime(box.getOpenTime());
//        判断修改后的状态跟原先的一不一样
        if (status.equals(boxRequestBo.getStatus())){
            boxRequestBo.setCloseTime(box.getCloseTime());
        }else if(!status.equals(boxRequestBo.getStatus()) && boxRequestBo.getStatus().equals("0")){
            boxRequestBo.setCloseTime(null);
        }else if (!status.equals(boxRequestBo.getStatus()) && boxRequestBo.getStatus().equals("1")){
            boxRequestBo.setCloseTime(new Date());
        }

        if (boxDao.getCountByname(boxRequestBo.getName())>0){
            int collectorId = boxDao.getcollectorIdByname(boxRequestBo.getName());
            boxRequestBo.setCollectorId(collectorId);
            boxDao.updateBox(boxRequestBo);
        }else {
            throw new BusinessException("该检测人员不存在", ResultCodeEnum.ERROR);
        }



    }

}

