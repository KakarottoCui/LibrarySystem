package tgc.edu.mcy.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import tgc.edu.mcy.custom.CommonRepository;
import tgc.edu.mcy.entity.Book;

@Repository
public interface BookRepository extends CommonRepository<Book, Integer> {

	public Book findByIsbn(String isbn);

	public List<Book> findByNumberAfter(int i);

	public List<Book> findByNumberAfterAndNameLikeOrIsbnLikeOrPressLikeOrAuthorLikeOrKindTypeLike(int a, String string, String string2,
			String string3, String string4, String string5);

}
