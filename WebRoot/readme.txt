-------------------项目结构说明------------------------------
Ltmcp
	|
	|----src
	|     |
	|     |---com.ltmcp.action(Action类，负责处理页面请求和数据发送)
	|	  |			    |---AdminAction
	|	  |				|---BaseAction
	|	  |				|---CarActionAction
	|	  |				|---ExcRecordAction
	|	  |				|---HandleWaybillAction
	|	  |				|---PersonAction
	|	  |				|---PositionAction
	|	  |				|---ReportFormAction
	|	  | 
	|	  | 
	|	  |---com.ltmcp.comm 
	|	  | 			|---AdminComm.java
	|	  | 			|---Comm.java
	|	  | 			|---PageBean.java
	|	  | 			
	|	  | 			
	|	  |---com.ltmcp.condition 			
	|	  | 			|---CarCondition.java
	|	  | 			|---ExcRecordCondition.java
	|	  | 			|---PersonCondition.java
	|	  | 			|---PositionCondition.java
	|	  | 			|---SealedCondition.java
	|	  | 			|---
	|	  | 
	|	  | 
	|	  |---com.ltmcp.convert	
	|	  | 			|---DateConvert.java
	|	  | 
	|	  | 
	|	  |---com.ltmcp.dao 
	|	  | 			|---AdminDao.java
	|	  | 			|---BasDictDao.java
	|	  | 			|---BaseDao.java
	|	  | 			|---CarDao.java
	|	  | 			|---CompanyDao.java
	|	  | 			|---ExcRecordDao.java
	|	  | 			|---FreezeDao.java
	|	  | 			|---PersonDao.java
	|	  | 			|---PositionDao.java
	|	  | 			|---PowerDao.java
	|	  | 			|---RoleDao.java
	|	  | 			|---RolePowerDao.java
	|	  | 			|---SealedDao.java
	|	  | 
	|	  | 
	|	  |---com.ltmcp.dao.hibimpl
	|	  | 			|---AdminDaoHibImpl.java
	|	  | 			|---BasDictDaoHibImpl.java
	|	  | 			|---BaseDaoHibImpl.java
	|	  | 			|---CarDaoHibImpl.java
	|	  | 			|---CompanyDaoHibImpl.java
	|	  | 			|---ExcRecordDaoHibImpl.java
	|	  | 			|---FreezeDaoHibImpl.java
	|	  | 			|---PersonDaoHibImpl.java
	|	  | 			|---PositionDaoHibImpl.java
	|	  | 			|---PowerDaoHibImpl.java
	|	  | 			|---RoleDaoHibImpl.java
	|	  | 			|---RolePowerDaoHibImpl.java
	|	  | 			|---SealedDaoHibImpl.java
	|	  | 
	|	  | 
	|	  | 
	|	  | 
	|	  |---com.ltmcp.entity
	|	  | 			|---Admin.java
	|	  | 			|---BasDict.java
	|	  | 			|---Car.java
	|	  | 			|---Company.java
	|	  | 			|---ExcRecord.java
	|	  | 			|---Freeze.java
	|	  | 			|---Person.java
	|	  | 			|---Position.java
	|	  | 			|---Power.java
	|	  | 			|---Role.java
	|	  | 			|---RolePower.java
	|	  | 			|---Sealed.java
	|	  | 			|---Admin.hbm.xml
	|	  | 			|---BasDict.hbm.xml
	|	  | 			|---Car.hbm.xml
	|	  | 			|---Company.hbm.xml
	|	  | 			|---ExcRecord.hbm.xml
	|	  | 			|---Freeze.hbm.xml
	|	  | 			|---Person.hbm.xml
	|	  | 			|---Position.hbm.xml
	|	  | 			|---Role.hbm.xml
	|	  | 			|---Role.hbm.xml
	|	  | 			|---RolePower.hbm.xml
	|	  | 			|---Sealed.hbm.xml
	|	  | 
	|	  | 
	|	  | 
	|	  |---com.ltmcp.filter
	|	  | 			|---AdminFilter.java
	|	  | 			|---CodeFilter.java
	|	  | 			|---JspFilter.java
	|	  | 			|---WebFilter.java
	|	  | 
	|	  | 
	|	  | 
	|	  |---com.ltmcp.service
	|	  | 			|---AdminService.java
	|	  | 			|---BasDictService.java
	|	  | 			|---BaseService.java
	|	  | 			|---CarService.java
	|	  | 			|---CompanyService.java
	|	  | 			|---ExcRecordService.java
	|	  | 			|---FreezeService.java
	|	  | 			|---PersonService.java
	|	  | 			|---PositionService.java
	|	  | 			|---PowerService.java
	|	  | 			|---RolePowerService.java
	|	  | 			|---RoleService.java
	|	  | 			|---SealedService.java
	|	  | 			|---SealedService.java
	|	  | 
	|	  | 
	|	  | 
	|	  |---com.ltmcp.service.impl 
	|	  | 			|---AdminServiceImpl.java
	|	  | 			|---BasDictServiceImpl.java
	|	  | 			|---BaseServiceImpl.java
	|	  | 			|---CarServiceImpl.java
	|	  | 			|---CompanyServiceImpl.java
	|	  | 			|---ExcRecordServiceImpl.java
	|	  | 			|---FreezeServiceImpl.java
	|	  | 			|---PersonServiceImpl.java
	|	  | 			|---PositionServiceImpl.java
	|	  | 			|---PowerServiceImpl.java
	|	  | 			|---RolePowerServiceImpl.java
	|	  | 			|---RoleServiceImpl.java
	|	  | 			|---SealedServiceImpl.java
	|	  | 			|---SealedServiceImpl.java
	|	  | 
	|	  | 
	|	  |---com.ltmcp.util
	|	    			|---
	|	     			|---
	|	    			|---
	|	    			|---
	|	    			|---
	|	    			|---
	|	    			|---
	|	    			|---
	|	    			|---
	|	    			|---
	|	    			|---
	|	    			|---
	|	    			|---
	|	    			|---
	|	    			|---
	|	  
	|	   
	|
	|
	|----config
	|	  | 
	|	  |---applicationContext-action.xml
	|	  |---applicationContext-base.xml
	|	  |---applicationContext-dao.xml
	|	  |---applicationContext-service.xml
	|	  |---apply.properties
	|	  |---log4j.properties
	|	  |---struts.xml
	|	  |---xwork-conversion.properties
	|
	|
	|
	|
	|----WebRoot
	|	  |---admin
	|	  |		|---css
	|	  |			 |---employmanament.css
	|	  | 		
	|	  |		
	|	  |---car
	|	  |	   |---css
	|	  |	        |---carmanage.css
	|	  |			
	|	  |		
	|	  |---common
	|	  |		 |----css	
	|	  |		 |	   |---
	|	  |		 |	   |---
	|	  |		 |	   |---
	|	  |		 |	   
	|	  |		 |----img	
	|	  |		 |	   |---
	|	  |		 |	   |---
	|	  |		 |	   |---
	|	  |		 |	   |---
	|	  |		 |	   |---
	|	  |		 |	   |---
	|	  |		 |	   |---
	|	  |		 |	   |---
	|	  |		 |	   |---
	|	  |		 |	   
	|	  |		 |----js	
	|	  |			
	|	  |			
	|	  |			
	|	  |---css
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |---exception
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |---FusionCharts
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |---js
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |---jsp
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |---log
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |---META-INF
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |---positionmanage
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |---statement
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |---waybill
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |---WEB-INF
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |---data.xml
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |			
	|	  |---header.jsp
	|	  |---ie-css3.htc
	|	  |---image.jsp
	|	  |---index.jsp
	|	  |---login.jsp
	|	  |---reademe.txt
	|
	|
	|----hibernate.revent.xml
	
	
