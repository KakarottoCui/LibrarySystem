package tgc.edu.mcy.custom;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tgc.edu.mcy.entity.Journal;
import tgc.edu.mcy.service.JournalService;

@Controller
public class JournalUtil {
	@Autowired
	private JournalService journalDAO;
	
	public void save(String username, String operationName, String reamark) {
		Journal journal = new Journal();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String data = df.format(new Date());
		journal.setTime(data);
		journal.setOperationName(operationName);
		journal.setReamark(reamark);
		journal.setUsername(username);		
		journalDAO.save(journal);
	}
}
