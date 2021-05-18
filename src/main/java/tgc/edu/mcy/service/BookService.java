package tgc.edu.mcy.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import tgc.edu.mcy.custom.CommonService;
import tgc.edu.mcy.entity.Book;
import tgc.edu.mcy.entity.Kind;
import tgc.edu.mcy.form.BookForm;
import tgc.edu.mcy.repository.BookRepository;

@Service
public class BookService extends CommonService<Book, Integer> {
	@Autowired
	BookRepository bookDAO;
	@Autowired
	KindService kindDAO;
	private String path="D:\\Projects\\BS\\Book\\src\\main\\resources\\static\\static\\book";
	
	@Transactional
	public void save(BookForm form, MultipartFile file) throws Exception {
		Book model = new Book();
		if(form.getId() == null) {
			BeanUtils.copyProperties(form, model);
			Book book = bookDAO.findByIsbn(model.getIsbn());
			if(book != null) {
				book.setNumber(book.getNumber()+model.getNumber());
				
				Kind kind = kindDAO.findById(book.getKind().getId());
				kind.setNumber(kind.getNumber()+model.getNumber());
				kindDAO.save(kind);
				
				bookDAO.save(book);
			}else {
				//获取客户端上传文件名
				String filename = file.getOriginalFilename();
				//随机生成一个UUID
				String uuid = UUID.randomUUID().toString();
				model.setFilename(filename);
				model.setUuid(uuid);
				model.setLoanNumber(0);
				File path = new File(this.path);
				
				Kind kind = kindDAO.findById(model.getKind().getId());
				kind.setNumber(kind.getNumber()+model.getNumber());
				kindDAO.save(kind);
				
				bookDAO.save(model);
				if(!path.isDirectory()) {
					path.mkdir();
				}
				File saveFile = new File(path, uuid+".jpg");
				try {
					file.transferTo(saveFile);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
		}else {
			Book book = findById(form.getId());
			Integer str = book.getNumber();
			Integer str1 = form.getNumber();
			System.out.println(str+"==="+str1);
			Integer nubmer = str1-str;
			BeanUtils.copyProperties(form, model);
			model.setFilename(book.getFilename());
			model.setUuid(book.getUuid());
			
			Kind kind = kindDAO.findById(book.getKind().getId());
			String type = book.getKind().getType();
			String type1 = form.getKind().getType();
			System.out.println(type+"==="+type1);
			if(type.equals(type1)) {
				kind.setNumber(kind.getNumber()+nubmer);
				kindDAO.save(kind);
			}else {
				System.out.println(kind.getNumber()+"==="+kind.getType());
				kind.setNumber(kind.getNumber()-str);
				kindDAO.save(kind);
				Kind kind2 = kindDAO.findByType(form.getKind().getType());
				System.out.println(kind2.getNumber()+"==="+kind2.getType());
				kind2.setNumber(kind2.getNumber()+str1);
				kindDAO.save(kind2);
			}
			
			bookDAO.save(model);
		}
	}

	public void download(Integer id, HttpServletRequest request, HttpServletResponse response) {
		Book books = findById(id);
		String filename = books.getFilename();
		filename = getStr(request, filename);//文件名处理 兼容多个版本
		String uuid= books.getUuid()+".jpg";
		File file = new File(this.path,uuid);
		if(file.exists()) {
			FileInputStream fis;
			try {
				fis = new FileInputStream(file);
				response.setContentType("application/x-msdownload");
				response.addHeader("Content-Disposition", "attachment; filename=" + filename );
				ServletOutputStream out = response.getOutputStream();
				byte[] buf=new byte[2048];
				int n=0;
				while((n=fis.read(buf))!=-1){
					out.write(buf, 0, n);
				}
				fis.close();
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String getStr(HttpServletRequest request, String fileName) {
		String downloadFileName = null;
        String agent = request.getHeader("USER-AGENT");  
         try {
            	 if(agent != null && agent.toLowerCase().indexOf("firefox") > 0){
            		 downloadFileName = "=?UTF-8?B?" + Base64Utils.encodeToString(fileName.getBytes("UTF-8")) + "?=";
            	 }else{
            		 downloadFileName =  java.net.URLEncoder.encode(fileName, "UTF-8");
            	 }
		} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
		}
        return downloadFileName;
	}

	public List<Book> findByNumberAfter(int i) {
		return bookDAO.findByNumberAfter(i);
	}

	public List<Book> findByNumberAfterAndNameLikeOrIsbnLikeOrPressLikeOrAuthorLikeOrKindTypeLike(int a, String string, String string2,
			String string3, String string4, String string5) {
		return bookDAO.findByNumberAfterAndNameLikeOrIsbnLikeOrPressLikeOrAuthorLikeOrKindTypeLike(a, string, string2, string3, string4, string5);
	}
	
}
