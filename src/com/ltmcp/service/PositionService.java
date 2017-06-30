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
     * ��ȡλ����Ϣ
     * 
     * @param id
     * @return
     */
    Position getPosition(Integer id);

    /**
     * ͨ��������ȡ��ϸ��Ϣ
     * 
     * @param condition
     * @param pageBean
     * @return
     */
    PageBean searchPosition(PositionCondition condition, PageBean pageBean);

    /**
     * ����λ��ID��ȡ�˵���Ϣ
     * 
     * @param id
     * @param pageBean
     * @return
     */
    PageBean searchSealedByPositionId(Integer id, PageBean pageBean);

    /**
     * ����IDɾ��λ��
     * 
     * @param id
     */
    void delPositionById(Integer id) throws Exception;

    /**
     * ����������ȡPosition����
     * 
     * @param condition
     *            .
     * 
     * @return
     */
    List<Position> searchPositionByCondition(PositionCondition condition);

    /**
     * ���ݴ�������id����position����
     */
    void savePos(PositionExamine posExam);

    /**
     * ����µ�վ����Ϣ
     * 
     * @param Position
     */
    Integer addPosCardNumber(Integer dictId, String posName, String cardNumber,Integer areaid,String phoneMac);

    Integer addArea(Area area);
    Integer queryArea(String areaName);
    
    Integer queryCom_a(String comaName);//�ֹ�˾
    Integer addCom_a(Company coma);//�ֹ�˾
    
    Integer addCom(Province com);//�ܹ�˾
    
    List<Area> findAreaById(Integer id);
    List<Company> findCom_aById();
    List<Position> findPositionById(Integer id);
    List<Person> findpesonByposId(Integer posid);
    /**
     * ����վ����Ϣ
     * 
     * @param position
     */
    void updateNewPosition(Position position);

    /**
     * ���뷽��
     */
    void importDimensionalCode(DimensionalBarCode dimensonalBarCode);

    /**
     * ��ѯexcel���е�����
     */
    List<DimensionalBarCode> getAllByExcel(String file);

}
