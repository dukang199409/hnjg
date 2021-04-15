package com.bsl.common.pojo;

import com.itextpdf.text.Font;

/**
 * 生成pdf head和footer
 * @author huangyl
 * @date 2019年5月13日  
 *
 */
public class PDFCell {
	//值
	private String value;
	//字体
	private Font font;
	//对齐方式
	private int align;
	//占多少列
	private int colspan;
	//占多少行
	private int rowspan;
	public int getRowspan() {
		return rowspan;
	}
	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	//是否有边框
	private boolean boderFlag;
	public String getValue() {
		return value;
	}
	public Font getFont() {
		return font;
	}
	public int getAlign() {
		return align;
	}
	public int getColspan() {
		return colspan;
	}
	public boolean isBoderFlag() {
		return boderFlag;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	public void setAlign(int align) {
		this.align = align;
	}
	public void setColspan(int colspan) {
		this.colspan = colspan;
	}
	public void setBoderFlag(boolean boderFlag) {
		this.boderFlag = boderFlag;
	}
	public PDFCell(String value, Font font, int align, int colspan, boolean boderFlag) {
		super();
		this.value = value == null ? "" : value;
		this.font = font;
		this.align = align;
		this.colspan = colspan;
		this.boderFlag = boderFlag;
		this.rowspan = 1;
	}
	public PDFCell(String value, Font font, int align, int colspan, boolean boderFlag,int rowspan) {
		super();
		this.value = value == null ? "" : value;
		this.font = font;
		this.align = align;
		this.colspan = colspan;
		this.boderFlag = boderFlag;
		this.rowspan = rowspan;
		
	}
	
	public PDFCell(Integer value, Font font, int align, int colspan, boolean boderFlag) {
		super();
		this.value = value == null ? "" : String.valueOf(value);
		this.font = font;
		this.align = align;
		this.colspan = colspan;
		this.boderFlag = boderFlag;
	}
	
	public PDFCell(Float value, Font font, int align, int colspan, boolean boderFlag) {
		super();
		this.value = value == null ? "" : String.valueOf(value);
		this.font = font;
		this.align = align;
		this.colspan = colspan;
		this.boderFlag = boderFlag;
	}
}
