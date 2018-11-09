package com.chan.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chan.domain.QnaVO;
import com.chan.pagination.Criteria;
import com.chan.persistence.QnaDAO;

@Service
public class QnaService {
	
	@Autowired
	private QnaDAO qnaDao;
	
	@Transactional
	public int writeQna(QnaVO vo) {
		if(vo.getGroupno() == null) {
			vo.setSeq(0);
			vo.setDepth(0);
			qnaDao.insert(vo);
			qnaDao.updateGroup(vo.getQno());
		} else {
			vo.setSeq(vo.getSeq() + 1);
			vo.setDepth(vo.getDepth() + 1);
			System.out.println(vo.getSeq());
		}
			
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < vo.getDepth(); i++) {
			sb.append("RE: ");
		}
		sb.append(vo.getTitle());
		vo.setTitle(sb.toString());
		
		qnaDao.insert(vo);
		
		return vo.getQno();
	}

	public List<HashMap<String, Object>> readAllQna(Criteria cri) {
		return qnaDao.readAll(cri);
	}

	public int totalCount(Criteria cri) {
		return qnaDao.countAll(cri);
	}

	public void viewUpdate(Integer qno) {
		qnaDao.viewUpdate(qno);
	}

	public HashMap<String, Object> readPost(Integer qno) {
		return qnaDao.read(qno);
	}
	
}
