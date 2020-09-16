package dao;

import java.sql.SQLException;

import dto.UserDto;

public interface UserDao {
	/**
	    * ȸ�� ����
	    */
	   int signUp(UserDto userDto) throws SQLException;

	   /**
	    * �α���
	    */
	   UserDto login(String userId, String userPwd) throws SQLException;

	   /**
	    * ����Ʈ ����
	    */
	   int userPoint(int point) throws SQLException;

	   /**
	    * ȸ����������
	    */
	   int updateUserInfo(UserDto userDto) throws SQLException;

	   /**
	    * ȸ������Ż��
	    */
	   int deleteUserInfo(UserDto userDto) throws SQLException;

}
