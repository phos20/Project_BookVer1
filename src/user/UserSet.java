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
	 * ����� ã��
	 * */
	public User get(String sessionId) {
		for(User session : set) {
			if(session.getSessionId().equals(sessionId)) {
				return session;
			}
		}
		return null;
	}
	
	//���� ��ü�� ��ȯ
		public Set<User> getSet(){
			return set;
		}
	
		/**
		 * �α��� �� ����� �߰�
		 * */
		public void add(User session) {
			set.add(session);
		}
		
	/**
	 * ����� ���� - �α׾ƿ�
	 * */
	public void remove(User session) {
		set.remove(session);
	}
	

}
