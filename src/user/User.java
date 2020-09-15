package user;

import java.util.HashMap;
import java.util.Map;


	
	public class User {
		private String userId;
		private Map<String,Object> attributes; //��ٱ���
		
		
		public User() {}
		public User(String userId) {
			this.userId = userId;
			attributes = new HashMap<>();
		}
		public String getSessionId() {
			return userId;
		}
		
		//�߰�
		public void setAttribute(String name, Object value) {
			attributes.put(name,value);
		}
		
		//��ȸ- Map�� Key�� �ش��ϴ� valueã��
		public Object getAttribute(String name) {//cart
			return attributes.get(name);
		}
		
		//����
		public void removeAttribute(String name) {
			attributes.remove(name);
		}
		
		
		public void setSessionId(String sessionId) {
			this.userId = sessionId;
		}
		
		public Map<String, Object> getAttributes() {
			return attributes;
		}
		
		public void setAttributes(Map<String, Object> attributes) {
			this.attributes = attributes;
		}
		
		
		@Override
		public String toString() {
			return "User=" + userId + ", attributes=" + attributes + "]"+"\n";
		}
		
		
		@Override
		public int hashCode() {
			return userId.hashCode();
		}
		
		/**���� ��ü��� ���� hashCode�� ���ƾ��ϰ�,
		 * equals�� ����� true �����Ѵ�.
		 * 
		 * hashCode�� �ٸ��� ������ �ٸ� ��ü
		 * hashCode�� ������ ������ü�ϼ��� �ٸ���ü�ϼ����ִ�(equals�� ����)
		 * */
		@Override
		public boolean equals(Object obj) {
			User other = (User) obj;
			if(userId.equals(other.userId)) {
				return true;
			}else {
				return false;
			}
			
		}
		

}
