package com.greedy.section02.provider;

import static com.greedy.common.Template.getSqlSession;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.greedy.common.MenuDTO;
import com.greedy.common.SearchCriteria;

public class SelectBuilderService {
	
	private SelectBuilderMapper mapper;

	public void testSimpleStatement() {
		
		SqlSession sqlSession = getSqlSession();
		mapper = sqlSession.getMapper(SelectBuilderMapper.class);
		
		List<MenuDTO> menuList = mapper.selectAllMenu();
		
		if(menuList != null && menuList.size() > 0) {
			for(MenuDTO menu : menuList) {
				System.out.println(menu);
			}
		} else {
			System.out.println("검색 결과가 존재하지 않습니다.");
		}
		
		sqlSession.close();
		
	}

	public void testDynamicStatement(SearchCriteria searchCriteria) {
		
		SqlSession sqlSession = getSqlSession();
		mapper = sqlSession.getMapper(SelectBuilderMapper.class);
		
		List<MenuDTO> menuList = mapper.searchMenuByCondition(searchCriteria);
		
		if(menuList != null && menuList.size() > 0) {
			for(MenuDTO menu : menuList) {
				System.out.println(menu);
			}
		} else {
			System.out.println("검색 결과가 존재하지 않습니다.");
		}
		
		sqlSession.close();
		
	}
	
}
