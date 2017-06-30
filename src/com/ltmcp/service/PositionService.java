package com.ltmcp.service;

import java.util.List;

import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.PositionCondition;
import com.ltmcp.condition.PositionExamineCondition;
import com.ltmcp.entity.Company;
import com.ltmcp.entity.Area;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Province;
import com.ltmcp.entity.DimensionalBarCode;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.PositionExamine;
import com.ltmcp.entity.Sealed;

public interface PositionService {

    /**
     * 获取位置信息
     * 
     * @param id
     * @return
     */
    Position getPosition(Integer id);

    /**
     * 通过条件获取详细信息
     * 
     * @param condition
     * @param pageBean
     * @return
     */
    PageBean searchPosition(PositionCondition condition, PageBean pageBean);

    /**
     * 根据位置ID获取运单信息
     * 
     * @param id
     * @param pageBean
     * @return
     */
    PageBean searchSealedByPositionId(Integer id, PageBean pageBean);

    /**
     * 根据ID删除位置
     * 
     * @param id
     */
    void delPositionById(Integer id) throws Exception;

    /**
     * 根据条件获取Position集合
     * 
     * @param condition
     *            .
     * 
     * @return
     */
    List<Position> searchPositionByCondition(PositionCondition condition);

    /**
     * 根据传过来的id保存position数据
     */
    void savePos(PositionExamine posExam);

    /**
     * 添加新的站点信息
     * 
     * @param Position
     */
    Integer addPosCardNumber(Integer dictId, String posName, String cardNumber,Integer areaid,String phoneMac);

    Integer addArea(Area area);
    Integer queryArea(String areaName);
    
    Integer queryCom_a(String comaName);//分公司
    Integer addCom_a(Company coma);//分公司
    
    Integer addCom(Province com);//总公司
    
    List<Area> findAreaById(Integer id);
    List<Company> findCom_aById();
    List<Position> findPositionById(Integer id);
    List<Person> findpesonByposId(Integer posid);
    /**
     * 更新站点信息
     * 
     * @param position
     */
    void updateNewPosition(Position position);

    /**
     * 导入方法
     */
    void importDimensionalCode(DimensionalBarCode dimensonalBarCode);

    /**
     * 查询excel表中的数据
     */
    List<DimensionalBarCode> getAllByExcel(String file);

}
