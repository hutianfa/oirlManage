package com.ltmcp.dao;

import java.util.List;

import com.ltmcp.condition.PositionCondition;
import com.ltmcp.entity.Area;
import com.ltmcp.entity.Company;
import com.ltmcp.entity.DimensionalBarCode;
import com.ltmcp.entity.Person;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.PositionExamine;
import com.ltmcp.entity.Province;

public interface PositionDao {

    /**
     * 更新一条位置信息
     * 
     * @param position
     */
    void updatePosition(Position position);

    /**
     * 删除一条位置信息
     * 
     * @param position
     */
    void deletePosition(Position position);

    /**
     * 查询ID查询位置信息的详细信息
     * 
     * @param position
     * @return
     */
    Position queryPosition(Position position);

    /**
     * 查询位置信息结合
     * 
     * @param currentPage
     *            当前页
     * @param pageSize
     *            页大小
     * @param condition
     *            条件
     * @return 集合
     */
    List<Position> findPositions(Integer currentPage, Integer pageSize,PositionCondition condition);

    /**
     * 根据
     * 
     * @param condition
     * @return
     */
    Integer queryPositionCountByCondition(PositionCondition condition);

    List<Position> findPositions();

    /**
     * 根据ID删除位置
     * 
     * @param id
     */
    void delPositionById(Integer id);

    /**
     * 检查是否存在
     * 
     * @param id
     * @return
     */
    boolean isPositionexist(Integer id);

    /**
     * 根据条件查询Position集合
     * 
     * @param condition
     * @return
     */
//    List<Position> findPositionsByCondition(PositionCondition condition);
    
    List<Position> findPositionsByCondition();

    /**
     * 根据状态将审核通过的位置信息添加到position表
     * 
     * @param state
     */

    public void addPos(PositionExamine posExam);

    /**
     * 添加新的站点信息
     * 
     * @param position
     */
    public Integer insertCardNumber(Integer dictId, String posName,String cardNumber,Integer areaid,String phoneMac);

    public Integer insertArea(Area area);
    
    public Area findArea(String areaName);
    
    public Integer insertComa(Company coma);
    
    public Company findComa(String comaName);
    
    public Integer insertCom_a(Province com_a);
    
    
    /**
     * 根据id查询
     */
    //分公司
    public List<Company> queryCom_aById();
    
    //区
    public List<Area> queryAreaById(Integer id);
    
    //分公司
    public List<Position> queryPosiById(Integer id);
    
    public List<Person> queryPersonidById(Integer id);
    
    
    
    
    /**
     * 更新站点信息
     * 
     * @param position
     */
    void updateNewPosition(Position position);

    /**
     * 将excel表中的数据添加到数据库
     * 
     * @param dimensionalBarCode
     */
    void addDimensCode(DimensionalBarCode dimensionalBarCode);

}
