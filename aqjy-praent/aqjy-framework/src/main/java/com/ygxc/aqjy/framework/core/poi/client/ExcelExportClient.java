package com.ygxc.aqjy.framework.core.poi.client;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ygxc.aqjy.common.constant.BConst;
import com.ygxc.aqjy.common.exception.YgxcBusinessException;
import com.ygxc.aqjy.common.utils.Assist;
import com.ygxc.aqjy.framework.core.poi.constant.PoiType;
import com.ygxc.aqjy.framework.core.poi.constant.TableTheme;
import com.ygxc.aqjy.framework.core.poi.interfaces.IWriteDataCellHandle;
import com.ygxc.aqjy.framework.core.poi.interfaces.IWriteDataFetch;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

/**
 * excel导出管理类
 *
 * @author Qiaoxin.Hong
 */
public class ExcelExportClient<T> {

    /** 导出的输出流 */
    protected OutputStream outputStream;

    /** poi类型 */
    protected PoiType poiType = PoiType.XSSF;

    /** excel列标题集 */
    protected String[] colNames;

    /** 默认列宽度 */
    protected int colDefaultWidth = 14;

    /** excel列宽度集，默认乘256 */
    protected int[] colWidths;

    /** excel导入列数据处理 */
    protected IWriteDataCellHandle<T> writeDataCellHandle;

    /** excel样式 */
    protected TableTheme tableTheme = TableTheme.DEFAULT_STYLE;
    
    /** excel导出数据提取器 */
    protected IWriteDataFetch<T> writeDataFetch;

    /**
     * 导出excel
     */
    public void write(List<T> data) {
        setDataList(data);
        write();
    }

    /**
     * 导出excel
     */
    public void write() {
        // 导出前的验证
        if (!validate()) return;

        try {
            //创建workbook
            Workbook workbook = createWorkbook();
            //创建sheet
            Sheet sheet = workbook.createSheet();
            //默认列宽度
            sheet.setDefaultColumnWidth(colDefaultWidth);
            //自定义列宽度
            Assist.forEachOrdered(colWidths, (columnIndex, width) -> setColumnWidth(sheet, columnIndex, width));
            //当前行下标的滚动标识
            int rowRollIndex = 0;
            //数据提取索引
            int fetchIndex = 0;
            //列数量
            int colCount = getColRealCount();
            //列标题的相关处理
            Row colTitleRow = sheet.createRow(rowRollIndex);
            Assist.forEachOrdered(colNames, (index, colName) -> {
                Cell colTitleCell = colTitleRow.createCell(index);
                colTitleCell.setCellValue(colName);
                colTitleCell.setCellStyle(tableTheme.getColTitleCellStyle(workbook));
            });
            tableTheme.cellRowSettings(colTitleRow);
            rowRollIndex++; //行下标的滚动标识向前推一行        
            while (true) {
            	//提取数据
            	List<T> dataList = writeDataFetch.fetch(fetchIndex, rowRollIndex);
            	
            	//数据处理
                if (Assist.isNotEmpty(dataList)) {
                    //行循环
                    for (int i = 0; i < dataList.size(); i++) {
                        //创建行
                        Row row = sheet.createRow(rowRollIndex);

                        //当前行数据
                        T curItem = dataList.get(i);

                        //列循环
                        for (int j = 0; j < colCount; j++) {
                            //创建单元格
                            Cell cell = row.createCell(j);
                            cell.setCellStyle(tableTheme.getCellStyle(workbook));
                            //获取到单元格数据
                            Object val = writeDataCellHandle == null ? curItem : writeDataCellHandle.handleCell(curItem, i, j);

                            if (val == null) {
                                cell.setCellValue(BConst.EMPTY);
                            } else if (val instanceof Date) {
                                cell.setCellValue((Date) val);
                            } else if (val instanceof Integer) {
                                cell.setCellValue((Integer) val);
                            } else if (val instanceof Double) {
                                cell.setCellValue((Double) val);
                            } else if (val instanceof Float) {
                                cell.setCellValue((Float) val);
                            } else if (val instanceof Long) {
                                cell.setCellValue((Long) val);
                            } else {
                                cell.setCellValue(val.toString());
                            }
                        }
                        tableTheme.cellRowSettings(row);
                        rowRollIndex++; // 行下标的滚动标识向前推一行
                    }
                }
            	
            	//是否结束提取
            	boolean isOverFetch = writeDataFetch.over(fetchIndex, dataList);
            	if (isOverFetch) break;
            	
            	//数据提取索引+1
            	fetchIndex++;
			}

            //导出
            workbook.write(outputStream);
        } catch (Exception e) {
            throw new YgxcBusinessException("excel write error", e);
        }
    }

    /**
     * 导出操作前的验证
     *
     * @return
     */
    protected boolean validate() {
        Assist.notNull(outputStream, "excel write outputStream cannot be null");
        Assist.notEmpty(colNames, "excel write colNames cannot be null");
        Assist.notNull(poiType, "excel write poiType cannot be null");
        Assist.notNull(writeDataFetch, "excel write writeDataFetch cannot be null");
        return true;
    }

    /**
     * 根据poi类型创建Workbook
     *
     * @return
     */
    protected Workbook createWorkbook() {
        return PoiType.HSSF.equals(poiType) ? new HSSFWorkbook() : new XSSFWorkbook();
    }

    /**
     * 设置列宽度，width默认*256
     *
     * @param sheet
     * @param columnIndex
     * @param width
     */
    protected void setColumnWidth(Sheet sheet, Integer columnIndex, Integer width) {
        sheet.setColumnWidth(columnIndex, width * 256);
    }

    /**
     * 取得excel的实际列数量
     *
     * @return
     */
    public int getColRealCount() {
        return colNames.length;
    }
    
    /**
     * 设置数据列表
     * @param dataList
     * @return
     */
    public ExcelExportClient<T> setDataList(List<T> dataList) {
    	setWriteDataFetch(dataListToFetch(dataList));
        return this;
    }
    
    /**
     * 将数据列表转换为提取器
     * @param dataList
     */
    public static <T> IWriteDataFetch<T> dataListToFetch(List<T> dataList) {
    	return new IWriteDataFetch<T>() {

			@Override
			public List<T> fetch(int fetchIndex, int excelRowIndex) {
				return dataList;
			}
			
			@Override
			public boolean over(int fetchIndex, List<T> dataList) {
				return true;
			}
		};
	}

    public TableTheme getTableTheme() {
        return tableTheme;
    }

    public ExcelExportClient<T> setTableTheme(TableTheme tableTheme) {
    	if (tableTheme == null) {
    		tableTheme = TableTheme.DEFAULT_STYLE;
    	}
        this.tableTheme = tableTheme;
        return this;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public ExcelExportClient<T> setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
        return this;
    }

    public PoiType getPoiType() {
        return poiType;
    }

    public ExcelExportClient<T> setPoiType(PoiType poiType) {
        this.poiType = poiType;
        return this;
    }

    public String[] getColNames() {
        return colNames;
    }

    public ExcelExportClient<T> setColNames(String[] colNames) {
        this.colNames = colNames;
        return this;
    }

    public int getColDefaultWidth() {
        return colDefaultWidth;
    }

    public ExcelExportClient<T> setColDefaultWidth(int colDefaultWidth) {
        this.colDefaultWidth = colDefaultWidth;
        return this;
    }

    public int[] getColWidths() {
        return colWidths;
    }

    public ExcelExportClient<T> setColWidths(int[] colWidths) {
        this.colWidths = colWidths;
        return this;
    }

    public IWriteDataCellHandle<T> getWriteDataCellHandle() {
        return writeDataCellHandle;
    }

    public ExcelExportClient<T> setWriteDataCellHandle(IWriteDataCellHandle<T> writeDataCellHandle) {
        this.writeDataCellHandle = writeDataCellHandle;
        return this;
    }

	public IWriteDataFetch<T> getWriteDataFetch() {
		return writeDataFetch;
	}

	public ExcelExportClient<T> setWriteDataFetch(IWriteDataFetch<T> writeDataFetch) {
		this.writeDataFetch = writeDataFetch;
		return this;
	}
}
