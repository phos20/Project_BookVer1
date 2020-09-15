package dao;

import java.sql.SQLException;
import java.util.List;

import dto.RegBookDto;

public interface RegBookDao {
	/**
	    * Èñ¸Áµµ¼­¸ñ·Ï °Ë»ö
	    */
	   List<RegBookDto> selectRegBook() throws SQLException;
	   
	   /**
	   * Èñ¸Áµµ¼­µî·Ï 
	   */
	   int insertRegbook(RegBookDto wish)throws Exception; 


}
