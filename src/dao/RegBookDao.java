package dao;

import java.sql.SQLException;
import java.util.List;

import dto.RegBookDto;

public interface RegBookDao {
	/**
	    * ���������� �˻�
	    */
	   List<RegBookDto> selectRegBook() throws SQLException;
	   
	   /**
	   * ���������� 
	   */
	   int insertRegbook(RegBookDto wish)throws Exception; 


}
