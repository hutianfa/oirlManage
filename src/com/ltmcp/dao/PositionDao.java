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
     * ����һ��λ����Ϣ
     * 
     * @param position
     */
    void updatePosition(Position position);

    /**
     * ɾ��һ��λ����Ϣ
     * 
     * @param position
     */
    void deletePosition(Position position);

    /**
     * ��ѯID��ѯλ����Ϣ����ϸ��Ϣ
     * 
     * @param position
     * @return
     */
    Position queryPosition(Position position);

    /**
     * ��ѯλ����Ϣ���
     * 
     * @param currentPage
     *            ��ǰҳ
     * @param pageSize
     *            ҳ��С
     * @param condition
     *            ����
     * @return ����
     */
    List<Position> findPositions(Integer currentPage, Integer pageSize,PositionCondition condition);

    /**
     * ����
     * 
     * @param condition
     * @return
     */
    Integer queryPositionCountByCondition(PositionCondition condition);

    List<Position> findPositions();

    /**
     * ����IDɾ��λ��
     * 
     * @param id
     */
    void delPositionById(Integer id);

    /**
     * ����Ƿ����
     * 
     * @param id
     * @return
     */
    boolean isPositionexist(Integer id);

    /**
     * ����������ѯPosition����
     * 
     * @param condition
     * @return
     */
//    List<Position> findPositionsByCondition(PositionCondition condition);
    
    List<Position> findPositionsByCondition();

    /**
     * ����״̬�����ͨ����λ����Ϣ��ӵ�position��
     * 
     * @param state
     */

    public void addPos(PositionExamine posExam);

    /**
     * ����µ�վ����Ϣ
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
     * ����id��ѯ
     */
    //�ֹ�˾
    public List<Company> queryCom_aById();
    
    //��
    public List<Area> queryAreaById(Integer id);
    
    //�ֹ�˾
    public List<Position> queryPosiById(Integer id);
    
    public List<Person> queryPersonidById(Integer id);
    
    
    
    
    /**
     * ����վ����Ϣ
     * 
     * @param position
     */
    void updateNewPosition(Position position);

    /**
     * ��excel���е�������ӵ����ݿ�
     * 
     * @param dimensionalBarCode
     */
    void addDimensCode(DimensionalBarCode dimensionalBarCode);

}
