package Utils;

public class Opcodes {
	
	public enum Opcode {
		INFO(1), ERROR(2);
		
		private Integer i;
		private Opcode(Integer i) {
			this.setInt(i);
		}
		
		public Integer getInt() {
			return i;
		}
		
		public void setInt(Integer i) {
			this.i = i;
		}
	}
}
