package com.greedy.section02.provider;

import org.apache.ibatis.jdbc.SQL;

import com.greedy.common.MenuDTO;

public class SqlBuilderProvider {
	
	public String registMenu(MenuDTO menu) {
		
		SQL sql = new SQL();
		
		sql.INSERT_INTO("TBL_MENU A")
			.VALUES("A.MENU_CODE", "SEQ_MENU_CODE.NEXTVAL")
			.VALUES("A.MENU_NAME", "#{ name }")
			.VALUES("A.MENU_PRICE", "#{ price }")
			.VALUES("A.CATEGORY_CODE", "#{ categoryCode }")
			.VALUES("A.ORDERABLE_STATUS", "#{ orderableStatus }");
		
		return sql.toString();
		
	}
	
	public String modifyMenu(MenuDTO menu) {
		
		SQL sql = new SQL();
		
		/* 모든 컬럼을 업데이트 하는 경우 이렇게 작성할 수 있다.
		 * 하지만 동적 SQL을 작성하기는 힘들다
		 * */
//		sql.UPDATE("TBL_MENU A")
//			.SET("A.MENU_NAME = #{ name }"
//				, "A.MENU_PRICE = #{ price }"
//				, "A.CATEGORY_CODE = #{ categoryCode }"
//				, "A.ORDERABLE_STATUS = #{ orderableStatus }")
//			.WHERE("A.MENU_CODE = #{ code }");
		
		/* 비어있지 않은 값만 업데이트 되는 동적 쿼리를 작성한다. */
		sql.UPDATE("TBL_MENU A");
		
		if(menu.getName() != null && !"".equals(menu.getName())) {
			sql.SET("A.MENU_NAME = #{ name }");
		}
		
		if(menu.getPrice() > 0) {
			sql.SET("A.MENU_PRICE = #{ price }");
		}
		
		if(menu.getCategoryCode() > 0) {
			sql.SET("A.CATEGORY_CODE = #{ categoryCode }");
		}
		
		if(menu.getOrderableStatus() != null && !"".equals(menu.getOrderableStatus())) {
			sql.SET("A.ORDERABLE_STATUS = #{ orderableStatus }");
		}
		
		sql.WHERE("A.MENU_CODE = #{ code }");
		
		return sql.toString();
		
	}
	
	public String deleteMenu() {
		
		SQL sql = new SQL();
		
		sql.DELETE_FROM("TBL_MENU A")
			.WHERE("A.MENU_CODE = #{ code }");
		
		return sql.toString();
		
	}
}
