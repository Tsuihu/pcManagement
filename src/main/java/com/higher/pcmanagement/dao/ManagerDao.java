package com.higher.pcmanagement.dao;

import com.higher.pcmanagement.pojo.Collector;
import com.higher.pcmanagement.pojo.Manager;
import com.higher.pcmanagement.pojo.Point;
import com.higher.pcmanagement.pojo.bo.PageRequestBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

//@Repository
@Component
public interface ManagerDao {
    // 登录
    public Manager getManagerByTel(String tel);
    // 注册
    public void addManager(Manager manager);
    // 检查手机号是否存在
    int checkManagerRepeat(String tel);
    int checkManagerIdCard(@Param("idcard") String idcard);
    Manager getManagerIdCard(@Param("idcard") String idcard);



    //分页查询，
    List<Manager> getPageManager(PageRequestBo model);

    //查询每页信息的行数
    Integer getPageManagerCount();

    void deleteManager(@Param("managerId") Integer managerId);


    List<Manager> getLikeNameIdCard(@Param("name") String name);

    void updateManager(@Param("managerId") Integer managerId,@Param("name") String name
            , @Param("idcard") String idcard,@Param("tel") String tel,@Param("password") String password);

}
