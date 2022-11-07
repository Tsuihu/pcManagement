package com.higher.pcmanagement.service.impl;

import com.higher.pcmanagement.dao.BoxDao;
import com.higher.pcmanagement.exception.BusinessException;
import com.higher.pcmanagement.pojo.Box;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import com.higher.pcmanagement.pojo.bo.PageResultBo;
import com.higher.pcmanagement.service.BoxService;
import com.higher.pcmanagement.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    public PageResultBo<Box> getPageBox(PageRequestBo pageRequestBo) {
        if(pageRequestBo.getPageIndex()<0){
            pageRequestBo.setPageIndex(0);
        }
        List<Box> list = boxDao.getPageBox(pageRequestBo);
        Integer count = boxDao.getPageBoxCount();
        PageResultBo<Box> pageResultBo=new PageResultBo<>(list,count);
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
     * @param box
     */
    @Override
    public void addBox(Box box) throws BusinessException {
        if (boxDao.getCountByBoxcode(box.getBoxCode())>=1){
            throw new BusinessException("该箱编码已存在", ResultCodeEnum.ERROR);
        }
        boxDao.addBox(box);
    }

    /**
     * 修改箱子
     * @param box
     */
    @Override
    public void updateBox(Box box) {
        boxDao.updateBox(box);
    }
}
