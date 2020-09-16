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
	   UserDto login(String userId, String userPwd) throws SQLException;

	   /**
	    * 포인트 충전
	    */
	   int userPoint(int point) throws SQLException;

	   /**
	    * 회원정보수정
	    */
	   int updateUserInfo(UserDto userDto) throws SQLException;

	   /**
	    * 회원정보탈퇴
	    */
	   int deleteUserInfo(UserDto userDto) throws SQLException;

}
