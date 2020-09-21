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
	UserDto Login(String userId, String userPwd) throws SQLException;

	/**
	 * userId�� �ش��ϴ� ����Ʈ �˻�
	 */
	int getUserPoint(String userId) throws SQLException;
	/**
	 * ����Ʈ ����
	 */
	int userPoint(String userId, int point) throws SQLException;

	/**
	 * ȸ����������
	 */
	int updateUserInfo(UserDto userDto) throws SQLException;

	/**
	 * ȸ������Ż��
	 */
	int deleteUserInfo(UserDto userDto) throws SQLException;
	
	/**����Ʈ ����*/
	double Pay(double price, String userId)throws SQLException;
}
