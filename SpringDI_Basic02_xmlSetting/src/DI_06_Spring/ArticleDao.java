package DI_06_Spring;


//MYsql, Oracle이 사용하는 동일한(공통) 추상함수 만들고 구현하도록 강제할 것
public interface ArticleDao {
	void insert(Article article);
}
