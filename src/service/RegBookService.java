package service;

import java.sql.SQLException;
import java.util.List;

import dao.RegBookDao;
import dao.RegBookDaoImpl;
import dto.RegBookDto;

public class RegBookService {
	RegBookDao regbookDao = new RegBookDaoImpl();
	
	/**
	 * Èñ¸Áµµ¼­¸ñ·Ï °Ë»ö
	 */
	public List<RegBookDto> selectRegBook() throws SQLException{
		List<RegBookDto> list = regbookDao.selectRegBook();
		return list;
	}

}
