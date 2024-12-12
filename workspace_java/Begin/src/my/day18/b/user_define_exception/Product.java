package my.day18.b.user_define_exception;

public interface Product {
	// 주문받기1
	void order(int jumun_su) throws Jango_lack_Exception;
	//주문받기2
	void jumun(int jumun_su) throws Jango_lack_Exception;
}
