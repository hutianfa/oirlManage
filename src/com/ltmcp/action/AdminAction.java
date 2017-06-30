package com.ltmcp.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ltmcp.comm.Comm;
import com.ltmcp.comm.PageBean;
import com.ltmcp.condition.AdminCondition;
import com.ltmcp.entity.Admin;
import com.ltmcp.entity.Power;
import com.ltmcp.service.AdminService;
import com.ltmcp.service.ExcRecordService;
import com.ltmcp.service.PowerService;
import com.ltmcp.service.SealedService;
import com.ltmcp.util.RegexTool;

public class AdminAction extends BaseAction {

	private Admin admin; // 登录的用户

	private AdminService adminService;

	private PowerService powerService;

	private Integer excTreatedCount; // 已经处理的异常条数
	private Integer excUntreatedCount; // 未处理的异常条数
	private Integer wayBillUnfinishedCount; // 未完成的运单条数
	private Integer wayBillCompletedCount; // 已经完成的运单条数
	private Integer wayBillExcrecordCount; // 出现异常的运单
	private ExcRecordService excRecordService; // 异常处理类
	private SealedService sealedService; // 运单处理类
	private String code;
	private PageBean pageBean;
	private List<Admin> generalManagerlist;// 普通管理员列表
	private AdminCondition condition;
	private Integer id;
	private String name;
	private String pwd;// 旧密码
	private String newPwd;// 新密码
	private String confirmPwd;// 确认密码

	public static final Logger logger = Logger.getLogger(AdminAction.class);
	/**
	 * 处理登录
	 * 
	 * @return
	 */
	public String doLogin() {
	
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(date);
		
		HttpSession session = super.getSession();
		String ccode = (String) session.getAttribute("ccode");
		if (ccode == null || code == null) {
			
			logger.debug("web=="+name+"验证码为空");
			return "input";
		}

		if (!ccode.equals(code)) { // 验证码不正确
			super.setMessage("log_msg", "验证码输入错误!");
			logger.debug("web==（"+name+"）验证码输入错误!");
			return "input";
		}
		try {
			Admin admin = adminService.getAdmin(this.admin); // 获取当前登录用户的信息	
			//System.out.println(admin.getAdmName());
			List<Power> powers = powerService.searchPowers(admin); // 根据当前用户角色获取角色所对应的所有权限信息
			List<Power> menus = powerService.searchPowerMenus(powers); // 获得当前用户的菜单信息
			adminService.saveAdminInfoToSession(admin, powers, menus); // 保存用户信息到session中
			
			if (null == admin) {
				super.setMessage("log_msg", "用户名或者密码输入错误!");
				System.out.println("web端:"+admin.getAdmName()+"---("+time+")登录失败！");
				return "input";
			} else if(admin != null ){
				System.out.println("web端:"+admin.getAdmName()+"---("+time+")已登录！");
			}
		} catch (Exception e) {
			logger.error("web==（"+name+"）验证码输入错误!"+e);
			return "input";
		}
		logger.debug("web==（"+name+"）登录成功!");
		return SUCCESS;
	}

	/**
	 * 进入主页
	 * 
	 * @return
	 */
	public String main() {
		try {
			excTreatedCount = excRecordService.getTreatedCount();// 获取已处理的异常条数
			excUntreatedCount = excRecordService.getUnTreatedCount();// 获取未处理的异常条数
			wayBillUnfinishedCount = sealedService.getWayBillUnfinishedCount();// 获取未完成的运单条数
			wayBillCompletedCount = sealedService.getWayBillCompletedCount();// 获取已经完成的运单条数
			wayBillExcrecordCount = sealedService.getWayBillExcCount();// 获取出现异常的运单
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("web==（"+name+"）进入主页异常!"+e);
			return INPUT;
		}

	}

	/**
	 * 退出（注销登录）
	 * 
	 * @return
	 */
	public String logout() {
		HttpSession session = super.getSession();
		session.removeAttribute(Comm.CURRENT_ADMIN);
		session.removeAttribute(Comm.CURRENT_ADMIN_ALL_POWER);
		session.removeAttribute(Comm.CURRENT_ADMIN_MENU_NAME);
		logger.debug("web==（"+name+"）退出登录!");
		return SUCCESS;
	}

	/**
	 * 普通管理员列表
	 * 
	 * @return
	 */
	public String listGeneralManager() {
		pageBean = adminService.searchList(pageBean, condition);
		generalManagerlist = pageBean.getList();
		return SUCCESS;
	}

	/**
	 * 删除普通管理员
	 */
	public void delGeneralManager() {
		try {
			if (null != id) {
				adminService.delGeneralmanager(id);
				super.getResponse().getWriter().print(0);// 成功
			} else {
				super.getResponse().getWriter().print(1);// 失败
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("web=0=（"+name+"）删除普通管理员失败!"+e);
			try {
				super.getResponse().getWriter().print(1);
			} catch (IOException e1) {
				e1.printStackTrace();
				logger.error("web=1=（"+name+"）删除普通管理员失败!"+e1);
			}// 失败
		}
	}

	/**
	 * 添加普通管理员
	 * 
	 * @return
	 */
	public void addGeneralManager() {
		if (validateGeneralManager(admin) == true) {
			try {
				adminService.addAdmin(admin,condition);
				super.getPringWriter().print(0);
			} catch (Exception e) {
				e.printStackTrace();
				super.getPringWriter().print(1);
			}
		} else {
			super.getPringWriter().print(1);
		}
	}

	public boolean validateGeneralManager(Admin admin) {
		if (admin == null) {
			return false;
		}
		if (null != admin.getAdmName() && !"".equals(admin.getAdmName())) {
			if (RegexTool.regexString("(^[a-zA-Z]{1,}[0-9a-zA-Z_]{1,}$)",
					admin.getAdmName()) == false) {
				return false;
			}
		} else {
			return false;
		}

		if (null != admin.getAdmPassword()
				&& !"".equals(admin.getAdmPassword())) {
			if (RegexTool.regexString("(^[a-zA-Z]{1,}[0-9a-zA-Z_]{1,}$)",
					admin.getAdmPassword()) == false) {
				return false;
			}
		} else {
			return false;
		}
//		if (null == admin.getAdmSex()) {
//			return false;
//		}
		return true;
	}

	/**
	 * 修改密码
	 * 
	 * @throws Exception
	 */
	public void updatePwd() throws Exception {
		try {
			adminService.updateAdminPwd(pwd, newPwd, confirmPwd);
			super.getResponse().getWriter().print(0);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				super.getResponse().getWriter().print(1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public String updatepwd() {
		return SUCCESS;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	public PowerService getPowerService() {
		return powerService;
	}

	public void setPowerService(PowerService powerService) {
		this.powerService = powerService;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public Integer getExcTreatedCount() {
		return excTreatedCount;
	}

	public void setExcTreatedCount(Integer excTreatedCount) {
		this.excTreatedCount = excTreatedCount;
	}

	public Integer getExcUntreatedCount() {
		return excUntreatedCount;
	}

	public void setExcUntreatedCount(Integer excUntreatedCount) {
		this.excUntreatedCount = excUntreatedCount;
	}

	public Integer getWayBillUnfinishedCount() {
		return wayBillUnfinishedCount;
	}

	public void setWayBillUnfinishedCount(Integer wayBillUnfinishedCount) {
		this.wayBillUnfinishedCount = wayBillUnfinishedCount;
	}

	public Integer getWayBillCompletedCount() {
		return wayBillCompletedCount;
	}

	public void setWayBillCompletedCount(Integer wayBillCompletedCount) {
		this.wayBillCompletedCount = wayBillCompletedCount;
	}

	public ExcRecordService getExcRecordService() {
		return excRecordService;
	}

	public void setExcRecordService(ExcRecordService excRecordService) {
		this.excRecordService = excRecordService;
	}

	public SealedService getSealedService() {
		return sealedService;
	}

	public void setSealedService(SealedService sealedService) {
		this.sealedService = sealedService;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getWayBillExcrecordCount() {
		return wayBillExcrecordCount;
	}

	public void setWayBillExcrecordCount(Integer wayBillExcrecordCount) {
		this.wayBillExcrecordCount = wayBillExcrecordCount;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public List<Admin> getGeneralManagerlist() {
		return generalManagerlist;
	}

	public void setGeneralManagerlist(List<Admin> generalManagerlist) {
		this.generalManagerlist = generalManagerlist;
	}

	public AdminCondition getCondition() {
		return condition;
	}

	public void setCondition(AdminCondition condition) {
		this.condition = condition;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
