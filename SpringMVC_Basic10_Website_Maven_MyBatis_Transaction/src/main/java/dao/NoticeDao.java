package dao;

import java.sql.SQLException;
import java.util.List;

import vo.Notice;

//CRUD
public interface NoticeDao {
	//게시물 개수
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException;
	
	//전체 게시물
	public List<Notice> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException;
	
	//게시물 삭제
	public int delete(String seq) throws ClassNotFoundException, SQLException;
	
	//게시물 수정
	public int update(Notice notice) throws ClassNotFoundException, SQLException;
	
	//게시물 상세
	public Notice getNotice(String seq) throws ClassNotFoundException, SQLException;

	//게시물 입력
	public int insert(Notice n) throws ClassNotFoundException, SQLException;
	
	//트랜잭션 처리를 위한 update
	//사용자가 게시글을 작성시 글을 쓰면 member point 1점 증가시키기
	public int updateOfMemberPoint(String userid) throws ClassNotFoundException, SQLException;
}
