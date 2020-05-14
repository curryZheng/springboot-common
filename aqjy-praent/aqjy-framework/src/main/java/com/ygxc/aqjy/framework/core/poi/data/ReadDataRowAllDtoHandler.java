package com.ygxc.aqjy.framework.core.poi.data;

import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;

import com.ygxc.aqjy.framework.core.poi.interfaces.IReadDataAllDtoHandle;
import com.ygxc.aqjy.framework.core.poi.interfaces.IReadDataRowDtoHandle;

/**
 * excel导入数据处理 - 封装所有数据对象，并进行处理
 * 
 * @author Qiaoxin.Hong
 *
 */
public class ReadDataRowAllDtoHandler<T> extends ReadDataRowDtoHandler<T> {
	
	/** 数据处理器 */
	protected IReadDataAllDtoHandle<T> readDataAllDtoHandle;
	
	/** 数据列表 */
	protected List<T> dtoList = new ArrayList<>();
	
	public ReadDataRowAllDtoHandler() {
		this.readDataRowDtoHandle = createReadDataRowDtoHandle();
	}
	
	public ReadDataRowAllDtoHandler(IReadDataAllDtoHandle<T> readDataRowDtoListHandle, Class<T> dtoClass, List<String> fieldList) {
		this();
		this.readDataAllDtoHandle = readDataRowDtoListHandle;
		this.fieldList = fieldList;
		this.dtoClass = dtoClass;
	}
	
	/**
	 * 所有行处理结束，既全部处理结束
	 */
	@Override
	public void handleOver() {
		readDataAllDtoHandle.handleRow(dtoList);
	}
	
	/**
	 * 创建excel导入行数据处理 - 封装一行数据对象，并进行处理
	 */
	protected IReadDataRowDtoHandle<T> createReadDataRowDtoHandle() {
		return new IReadDataRowDtoHandle<T>() {
			@Override
			public void handleRow(int rowIndex, T dto, Row row) {
				dtoList.add(dto);
			}
		};
	}
	
	
	

	public IReadDataAllDtoHandle<T> getReadDataAllDtoHandle() {
		return readDataAllDtoHandle;
	}

	public void setReadDataAllDtoHandle(IReadDataAllDtoHandle<T> readDataAllDtoHandle) {
		this.readDataAllDtoHandle = readDataAllDtoHandle;
	}

	public List<T> getDtoList() {
		return dtoList;
	}

	public void setDtoList(List<T> dtoList) {
		this.dtoList = dtoList;
	}
}
