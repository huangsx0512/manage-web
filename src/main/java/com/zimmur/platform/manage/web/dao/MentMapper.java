package com.zimmur.platform.manage.web.dao;

import com.zimmur.platform.manage.web.domain.model.Ment;
import com.zimmur.platform.manage.web.domain.model.MentExample;
import com.zimmur.platform.manage.web.domain.vo.MentVo;
import com.zimmur.platform.manage.web.util.PageUtil;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MentMapper {
    int countByExample(MentExample example);

    int deleteByExample(MentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Ment record);

    int insertSelective(Ment record);

    List<Ment> selectByExample(MentExample example);

    Ment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Ment record, @Param("example") MentExample example);

    int updateByExample(@Param("record") Ment record, @Param("example") MentExample example);

    int updateByPrimaryKeySelective(Ment record);

    int updateByPrimaryKey(Ment record);

	/**
	* <p>Title: queryMentInfoByRoleId</p>
	* <p>Description:根据角色id查询角色权限</p>
	* @param roleId
	* @return
	*/
	List<MentVo> queryMentInfoByRoleId(@Param("roleId")Integer roleId);

	/**
	* <p>Title: queryMentInfo</p>
	* <p>Description: 查询权限信息</p>
	* @param pageNo
	* @param pageSize
	* @param keyword
	* @return
	*/
	List<Ment> queryMentInfo(@Param("pageBeginNo")Integer pageBeginNo, @Param("pageSize")Integer pageSize, @Param("keyword")String keyword);

	/**
	* <p>Title: countByParam</p>
	* <p>Description: 查询权限信息记录数</p>
	* @param keyword
	* @return
	*/
	int countByParam(@Param("keyword")String keyword);
}