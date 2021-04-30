package DI_06_Spring;

public class ArticleService {
	//클라이언트 요청에 따라서
	//DAO 객체 생성, 함수 호출
	
	//글쓰기, 목록보기 , ....
	
	//ArticleService는 ArticleDao가 필요하다(의존)
	private ArticleDao articledao;
	public ArticleService(ArticleDao articledao) {
		this.articledao = articledao;
		System.out.println("ArticleService 생성자호출");
	}
	
	//글쓰기 서비스
	public void write(Article article) {
		this.articledao.insert(article); //insert 호출
	}
}
