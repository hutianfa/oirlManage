package com.ltmcp.mobile.biz.impl;

import java.sql.Timestamp;
import java.util.List;

import com.ltmcp.entity.Area;
import com.ltmcp.entity.Area_manager_inventory;
import com.ltmcp.entity.Dbc_BagCodeBind;
import com.ltmcp.entity.DimensionalBarCode;
import com.ltmcp.entity.Freeze;
import com.ltmcp.entity.Position;
import com.ltmcp.entity.Sealed;
import com.ltmcp.entity.Second_order;
import com.ltmcp.entity.position_inventory;
import com.ltmcp.entity.shoufa_person;
import com.ltmcp.mobile.biz.DistributionBiz;
import com.ltmcp.mobile.biz.SaveCodeBiz;
import com.ltmcp.mobile.dao.DimensionalBarCodeMobileDao;
import com.ltmcp.mobile.dao.DistributionDao;
import com.ltmcp.mobile.dao.impl.Dbc_BagCodeBindDaoImpl;
import com.ltmcp.mobile.dao.impl.DimensionalBarCodeMobileDaoImpl;

public class DistributionBizImpl implements DistributionBiz{
	private DistributionDao distributionDao;

	public DistributionDao getDistributionDao() {
		return distributionDao;
	}

	public void setDistributionDao(DistributionDao distributionDao) {
		this.distributionDao = distributionDao;
	}

	@Override
	public boolean checkDisLogin(String username, String password) {
		// TODO Auto-generated method stub
		return distributionDao.checkDisLogin(username, password);
	}

	@Override
	public List<shoufa_person> serachPersonAddressorName(String username,
			String password) {
		// TODO Auto-generated method stub
		return distributionDao.serachPersonAddressorName(username, password);
		
	}

	@Override
	public List<Area> serachAreaAddressor(int com_id) {
		// TODO Auto-generated method stub
		return distributionDao.serachAreaAddressor(com_id);
	}

	@Override
	public List<Position> serachPostionAddressor(int areaid) {
		// TODO Auto-generated method stub
		return distributionDao.serachPostionAddressor(areaid);
	}

	@Override
	public boolean checkBagCode(String bagCode) {
		// TODO Auto-generated method stub
		return distributionDao.checkBagCode(bagCode);
	}

	@Override
	public boolean checkBoxCode(String boxCode) {
		// TODO Auto-generated method stub
		return distributionDao.checkBoxCode(boxCode);
	}

	@Override
	public void saveSecond_order(Second_order so) {
		// TODO Auto-generated method stub
		distributionDao.saveSecond_order(so);
	}

	@Override
	public List<shoufa_person> findAddressByPianquId(int pianquId) {
		// TODO Auto-generated method stub
		return distributionDao.findAddressByPianquId(pianquId);
	}

	@Override
	public List<Position> findAddrressByZhandianId(int zhandianId) {
		// TODO Auto-generated method stub
		return distributionDao.findAddrressByZhandianId(zhandianId);
	}

	@Override
	public void saveArea_manager_inventory(Area_manager_inventory ami) {
		// TODO Auto-generated method stub
		distributionDao.saveArea_manager_inventory(ami);
	}

	@Override
	public void savePosition_inventory(position_inventory pi) {
		// TODO Auto-generated method stub
		distributionDao.savePosition_inventory(pi);
	}

	@Override
	public List<DimensionalBarCode> findBagCodeByCode(String code) {
		// TODO Auto-generated method stub
		return distributionDao.findBagCodeByCode(code);
	}

	@Override
	public List<Dbc_BagCodeBind> findBoxCodeByFindbagCode(String findbagCode) {
		// TODO Auto-generated method stub
		 return distributionDao.findBoxCodeByFindbagCode(findbagCode);
	}

	@Override
	public boolean checkCodeNeiMa(String code) {
		// TODO Auto-generated method stub
		 return distributionDao.checkCodeNeiMa(code);
	}

	@Override
	public List<position_inventory> findPositionBybagCode(String findbagCode) {
		// TODO Auto-generated method stub
		return distributionDao.findPositionBybagCode(findbagCode);
	}

	@Override
	public List<position_inventory> findPositionByboxCode(String findboxCode) {
		// TODO Auto-generated method stub
		return distributionDao.findPositionByboxCode(findboxCode);
	}

	@Override
	public List<Second_order> findSecondre_sh_addressInStationBybagCode(String findbagCode) {
		// TODO Auto-generated method stub
		return distributionDao.findSecondre_sh_addressInStationBybagCode(findbagCode);
	}

	@Override
	public List<Second_order> findSecondre_sh_addressInStationByboxCode(String findboxCode) {
		// TODO Auto-generated method stub
		 return distributionDao.findSecondre_sh_addressInStationByboxCode(findboxCode);
	}

	@Override
	public boolean checkCodeWaiMa(String code) {
		// TODO Auto-generated method stub
		return distributionDao.checkCodeWaiMa(code);
	}

	@Override
	public List<DimensionalBarCode> findBagCodeByWaimaCode(String code) {
		// TODO Auto-generated method stub
		return distributionDao.findBagCodeByWaimaCode(code);
	}

	@Override
	public List<Area> findComIdByPianquId(int pianquId) {
		// TODO Auto-generated method stub
		return distributionDao.findComIdByPianquId(pianquId);
	}

	@Override
	public List<shoufa_person> findcompanyAddressBycomId(int comId) {
		// TODO Auto-generated method stub
		return distributionDao.findcompanyAddressBycomId(comId);
	}

	@Override
	public List<Sealed> findSealedInformationBydimensionalBarCode_id(int dimensionalBarCode_id) {
		// TODO Auto-generated method stub
		return distributionDao.findSealedInformationBydimensionalBarCode_id(dimensionalBarCode_id);
	}

	@Override
	public List<Freeze> findFreezeBysealedId(int sealedId) {
		// TODO Auto-generated method stub
		return distributionDao.findFreezeBysealedId(sealedId);
	}

	@Override
	public List<Position> findPianQuIdByReceive_sh_address(String address) {
		// TODO Auto-generated method stub
		return distributionDao.findPianQuIdByReceive_sh_address(address);
	}

	@Override
	public List<shoufa_person> findPianQuIdByshoufa_personAddress(String address) {
		// TODO Auto-generated method stub
		return distributionDao.findPianQuIdByshoufa_personAddress(address);
	}

	@Override
	public List<shoufa_person> findPianquIdByName(String name) {
		
		return distributionDao.findPianquIdByName(name);
	}

	@Override
	public Integer findCountByArea_name(String name) {
		return distributionDao.findCountByArea_name(name);
	}

	@Override
	public Integer findCountByZhandian_name(String zhanDianName) {
		return distributionDao.findCountByZhandian_name(zhanDianName);
	}

	@Override
	public boolean checkBagCodeBySecondOrderAndPosition_inventory(String code) {
		return distributionDao.checkBagCodeBySecondOrderAndPosition_inventory(code);
	}

	@Override
	public boolean checkBoxCodeBySecondOrderAndPosition_inventory(String code) {
		return distributionDao.checkBoxCodeBySecondOrderAndPosition_inventory(code);
	}
	
	
}
