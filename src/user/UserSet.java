package user;

import java.util.HashSet;
import java.util.Set;

public class UserSet {
	private static UserSet us = new UserSet();
	private Set<User> set;
	
	private UserSet() {
		set = new HashSet<>();
	}
	
	public static UserSet getInstance() {
		return us;
	}
	
	
	/**
	 * 사용자 찾기
	 * */
	public User get(String sessionId) {
		for(User session : set) {
			if(session.getSessionId().equals(sessionId)) {
				return session;
			}
		}
		return null;
	}
	
	//세션 객체들 반환
		public Set<User> getSet(){
			return set;
		}
	
		/**
		 * 로그인 된 사용자 추가
		 * */
		public void add(User session) {
			set.add(session);
		}
		
	/**
	 * 사용자 제거 - 로그아웃
	 * */
	public void remove(User session) {
		set.remove(session);
	}
	

}
