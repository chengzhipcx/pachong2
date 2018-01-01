package com.pcx.common;

import java.io.Serializable;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

/**
 * 带分页的抽象查询对象
 * 前台提交至后台的查询对象，均继承此对象
 * 在子对象中只定义条件字段即可
 *
 */
public abstract class AbstractPagebleQueryForm implements Serializable{

	private static final long serialVersionUID = -8194023637667338361L;
	
	//当前页
	private Integer page = 0 ;
	
	//每面显示条目数量
	private Integer rows = 10 ;
	
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = (page==null || page<1 )? 0:page-1;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Pageable getDefaultPageable(){
		return new PageRequest(page , rows) ;
	}
	
	public Pageable getDirectionPageable(Direction direction , String... properties){
		return new PageRequest(page , rows , direction , properties) ;
	}
	
	/**
	 * 查询条件对应的参数是否有值
	 * 若所有查询字段，均无对应的参数值，返回false
	 * 此抽象方法，由子类实现，子类判断参数情况
	 * @return
	 */
	public abstract boolean haveParameter() ;
	
	@Override
	public int hashCode() {
		return this.getPage().hashCode()+this.getRows().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof AbstractPagebleQueryForm)){
			return false ;
		}
		AbstractPagebleQueryForm objForm = (AbstractPagebleQueryForm)obj;
		return this.getPage() == objForm.getPage() && this.getRows()==objForm.getRows();
	}
	
}
