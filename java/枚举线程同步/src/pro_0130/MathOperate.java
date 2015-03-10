package pro_0130;

public enum MathOperate {
	PLUS,MINUS,TIMES,DIVIDE;
	
	double eval(double x,double y){
		
		switch (this) {
		case PLUS:
			return x+y;
			
		case MINUS:
			return x-y;
		
		case TIMES:
			return x*y;
		
		case DIVIDE:
			return x/y;
			
		default:
			return 0;
		}	
	}
	public static void main(String[] args) {
		System.out.println(MathOperate.PLUS.eval(3, 4));
		System.out.println(MathOperate.MINUS.eval(3, 4));
		System.out.println(MathOperate.TIMES.eval(3, 4));
		System.out.println(MathOperate.DIVIDE.eval(3, 4));
	}
}
