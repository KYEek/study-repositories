package member.model;



import user.domain.MemberDTO;

public interface MemberDAO {

	//일반 멤버 회원가입
	public int registeMember(MemberDTO company);
	
}
