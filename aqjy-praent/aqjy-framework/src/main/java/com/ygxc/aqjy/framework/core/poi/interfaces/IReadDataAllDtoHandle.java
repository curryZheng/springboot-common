package com.ygxc.aqjy.framework.core.poi.interfaces;

import java.util.List;

/**
 * excel导入数据处理 - 封装所有数据对象，并进行处理
 * 
 * @author Qiaoxin.Hong
 *
 */
public interface IReadDataAllDtoHandle<T> {

	/**
	 * 数据处理
	 * @param dtoList 数据列表
	 */
	void handleRow(List<T> dtoList);
}
