package dao;

import java.sql.SQLException;

import dto.UserDto;

public interface UserDao {
	/**
	 * 회원 가입
	 */
	int signUp(UserDto userDto) throws SQLException;

	/**
	 * 로그인
	 */
	UserDto Login(String userId, String userPwd) throws SQLException;

	/**
	 * userId에 해당하는 포인트 검색
	 */
	int getUserPoint(String userId) throws SQLException;
	/**
	 * 포인트 충전
	 */
	int userPoint(String userId, int point) throws SQLException;

	/**
	 * 회원정보수정
	 */
	int updateUserInfo(UserDto userDto) throws SQLException;

	/**
	 * 회원정보탈퇴
	 */
	int deleteUserInfo(UserDto userDto) throws SQLException;
	
	/**포인트 차감*/
	double Pay(double price, String userId)throws SQLException;
}
