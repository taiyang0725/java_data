package pro_0130;

public enum Gender {
     MALE,FEMALE;
     private String name;
     
     public void setName(String name){
    	 switch (this) {
		case MALE:
			if(name.equals("男")){
				this.name=name;
			}else {
				System.out.println("参数有误");
				return;
			}
			
			break;
        case FEMALE:
        	if(name.equals("女")){
				this.name=name;
			}else {
				System.out.println("参数有误");
				return;
			}
			
			break;

		default:
			break;
		}
     }
     public String getName(){
    	 return this.name;
     }
}
