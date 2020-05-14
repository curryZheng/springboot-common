package com.ygxc.aqjy.framework.core.poi.data;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.ygxc.aqjy.common.exception.YgxcBusinessException;
import com.ygxc.aqjy.framework.core.poi.interfaces.IWriteDataCellHandle;


/**
 * excel导出列数据处理，属性反射处理
 * 
 * @author Qiaoxin.Hong
 *
 */
public class WriteDataCellPropertyHandle<T> implements IWriteDataCellHandle<T> {
	
	/** 属性集 */
	protected String[] fields;
	
	/** 缓存属性解析后的method */
	protected Map<String, Method> propertyMethodMap = new HashMap<String, Method>();
	
	public WriteDataCellPropertyHandle(String[] fields) {
		setFields(fields);
	}

	/**
	 * 列数据处理
	 * @param obj
	 * @param rowIndex
	 * @param cellIndex
	 */
	@Override
	public Object handleCell(T obj, int rowIndex, int cellIndex) {
		String property = fields[cellIndex];
		try {
			Method method = propertyMethodMap.get(property);
			//method不存在，生成method
			if (method == null) {
				PropertyDescriptor pdes = new PropertyDescriptor(property, obj.getClass());
				method = pdes.getReadMethod();
				propertyMethodMap.put(property, method);
			}
			return method.invoke(obj);
		} catch (Exception e) {
			throw new YgxcBusinessException("excel write property get error " + property);
		}
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}
}
