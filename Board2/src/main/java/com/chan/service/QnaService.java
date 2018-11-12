package com.chan.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chan.domain.QnaVO;
import com.chan.pagination.Criteria;
import com.chan.persistence.QnaDAO;
import com.chan.persistence.RecommendDAO;

@Service
public class QnaService {
	
	@Autowired
	private QnaDAO qnaDao;
	@Autowired
	private RecommendDAO recommendDao;
	
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
			
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < vo.getDepth(); i++) {
				sb.append("RE: ");
			}
			sb.append(vo.getTitle());
			vo.setTitle(sb.toString());
			qnaDao.insert(vo);
		
		}
		
		return vo.getQno();
	}

	public List<HashMap<String, Object>> readAllQna(Criteria cri) {
		return qnaDao.readAll(cri);
	}

	public int totalCount(Criteria cri) {
		return qnaDao.countAll(cri);
	}

	public void viewUpdate(Integer qno, HttpSession session) {
		if(session.getAttribute("mno") == null) {
			qnaDao.viewUpdate(qno);
		} else if(!session.getAttribute("mno").equals(qnaDao.read(qno).get("qno"))) {
			qnaDao.viewUpdate(qno);
		}
	}

	public HashMap<String, Object> readPost(Integer qno) {
		return qnaDao.read(qno);
	}
	
	public void updatePost(QnaVO vo) {
		qnaDao.update(vo);
	}

	public void deletePost(Integer qno) {
		qnaDao.delete(qno);
		
	}

	public void updateRecommend(Integer qno, Integer mno, boolean recom, Integer type) {
		if(recommendDao.select(qno, mno, type) != 0) {
			return;
		}
		
		if (recom) {
			qnaDao.increaserecom(qno);
		} else {
			qnaDao.decreaserecom(qno);
		}
		recommendDao.insert(qno, mno, type);

		
	}
	
}
