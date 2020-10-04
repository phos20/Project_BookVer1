package service;

import java.sql.SQLException;
import java.util.List;

import dao.RegBookDao;
import dao.RegBookDaoImpl;
import dto.RegBookDto;

public class RegBookService {
	RegBookDao regbookDao = new RegBookDaoImpl();
	/** 
	 * 희망도서등록
	 */
	public void insertRegbook(RegBookDto wish) throws Exception {
		int result = regbookDao.insertRegbook(wish);
		if (result == 0)
			throw new Exception("등록되지 않았습니다.");
	}
	
	/**
	 * 희망도서목록 검색
	 */
	public List<RegBookDto> selectRegBook() throws SQLException {
		List<RegBookDto> list = regbookDao.selectRegBook();
		return list;
	}
}
