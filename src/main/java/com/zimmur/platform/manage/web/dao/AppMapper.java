package com.zimmur.platform.manage.web.dao;

import com.zimmur.platform.manage.web.domain.model.App;
import com.zimmur.platform.manage.web.domain.model.AppExample;
import com.zimmur.platform.manage.web.domain.vo.AppVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppMapper {
    int countByExample(AppExample example);

    int deleteByExample(AppExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(App record);

    int insertSelective(App record);

    List<App> selectByExample(AppExample example);

    App selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") App record, @Param("example") AppExample example);

    int updateByExample(@Param("record") App record, @Param("example") AppExample example);

    int updateByPrimaryKeySelective(App record);

    int updateByPrimaryKey(App record);

	/**
	* <p>Title: queryAppInfo</p>
	* <p>Description: 查询应用信息以及其赋予的角色</p>
	* @param pageNo
	* @param pageSize
	* @param keyword
	* @return
	*/
	List<AppVo> queryAppInfo(@Param("pageBeginNo")Integer pageBeginNo,@Param("pageSize") Integer pageSize,@Param("keyword") String keyword);

	/**
	* <p>Title: countByParam</p>
	* <p>Description:查询应用信息记录数 </p>
	* @param keyword
	* @return
	*/
	int countByParam(@Param("keyword") String keyword);
}