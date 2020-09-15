package user;

import java.util.HashMap;
import java.util.Map;


	
	public class User {
		private String userId;
		private Map<String,Object> attributes; //장바구니
		
		
		public User() {}
		public User(String userId) {
			this.userId = userId;
			attributes = new HashMap<>();
		}
		public String getSessionId() {
			return userId;
		}
		
		//추가
		public void setAttribute(String name, Object value) {
			attributes.put(name,value);
		}
		
		//조회- Map에 Key에 해당하는 value찾기
		public Object getAttribute(String name) {//cart
			return attributes.get(name);
		}
		
		//제거
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
		
		/**같은 객체라는 뜻은 hashCode가 같아야하고,
		 * equals의 결과가 true 여야한다.
		 * 
		 * hashCode가 다르면 무조건 다른 객체
		 * hashCode가 같으면 같은객체일수도 다른객체일수도있다(equals에 따라서)
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
