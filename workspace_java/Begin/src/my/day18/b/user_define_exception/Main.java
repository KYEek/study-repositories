package my.day18.b.user_define_exception;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Product_imple p1 = new Product_imple();
		p1.setProd_name("새우깡");
		p1.setJango(100);
		
		Product p2 = new Product_imple("감자깡", 50);
		Product p3 = new Product_imple("양파링", 150);
		
		Product[] prod_arr = new Product_imple[3];
		prod_arr[0] = p1;
		prod_arr[1] = p2;
		prod_arr[2] = p3;
		
		for (Product prod : prod_arr) {
			//System.out.println(prod.toString());
			System.out.println(prod);
		}//end of for-----------------------------
		
		/*
		 * 1. 제품명 : 새우깡
		 * 2. 잔고량 : 100

		 * 1. 제품명 : 감자깡
		 * 2. 잔고량 : 50
		 
		 * 1. 제품명 : 양파링
		 * 2. 잔고량 : 150
		 */
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		
		try {
			prod_arr[0].order(40);
		} catch (Jango_lack_Exception e) {
			//e.printStackTrace();
			System.out.println("예외 메시지 : " + e.getMessage());
		}

		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		try {
			prod_arr[2].jumun(200);
		} catch (Jango_lack_Exception e) {
			//e.printStackTrace();
			System.out.println("예외메시지 : " + e.getMessage());
		}
		//예외메시지 : >> 잔고량이 주문량 보다 적으므로 주문이 불가합니다.
		
		//예외메시지 : >> 양파링은 잔고량이 150개 인데 주문량이 200개 라서 잔고부족으로 주문이 불가합니다.
	}

}
