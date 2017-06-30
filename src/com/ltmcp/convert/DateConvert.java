package com.ltmcp.convert;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

public class DateConvert extends StrutsTypeConverter{

	private final DateFormat[] dfs = {
			new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"),
			new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"),
			new SimpleDateFormat("yyyyMMdd hh:mm:ss"),
			new SimpleDateFormat("yyyy年MM月dd日  hh:mm:ss"),
			new SimpleDateFormat("yyyyMMddhhmmss"), 
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
			new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"),
			new SimpleDateFormat("yyyyMMdd HH:mm:ss"),
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss"),
	};

	@Override
	public Object convertFromString(Map arg0, String[] value, Class arg2) {
		String datestr = value[0];
		for (int i = 0; i < dfs.length; i++) {
			try {
				return dfs[i].parse(datestr);
			} catch (ParseException e) {
				continue;
			}
		}
		throw new TypeConversionException("时间格式不正确!");
	}

	@Override
	public String convertToString(Map arg0, Object obj) {
		Timestamp date = (Timestamp) obj;
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

}
