package com.chan.service;

import java.io.UnsupportedEncodingException;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailHandler {

	private JavaMailSender mailSender; //이메일 전송 객체
    private MimeMessage message; //인코딩 방식 객체?
    private MimeMessageHelper messageHelper; // 이메일 전송 전 세팅을 위한 객체

    public MailHandler(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        message = this.mailSender.createMimeMessage();
        try {
			messageHelper = new MimeMessageHelper(message, true, "UTF-8"); //2번째 파라미터: 멀티파트 메시지 여부, 3번째: 인코딩 형식
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    }
    // 이메일 타이틀 설정
    public void setSubject(String subject) {
        try {
			messageHelper.setSubject(subject);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    }
    // 이메일 내용 설정
    public void setText(String htmlContent) {
        try {
			messageHelper.setText(htmlContent, true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    }
    // 발신자, 이메일 설정
    public void setFrom(String email, String name) {
        try {
			messageHelper.setFrom(email, name);
		} catch (UnsupportedEncodingException | MessagingException e) {
			e.printStackTrace();
		}
    }
    // 수신자 이메일 설정
    public void setTo(String email) {
        try {
			messageHelper.setTo(email);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
    }
    //데이터(사진등)를 보낼때 사용하는 객체
	public void addInline(String contentId, DataSource dataSource) { 
		try {
			messageHelper.addInline(contentId, dataSource); //파라미터명, 파일
		} catch (MessagingException e) {
			e.printStackTrace();
		} 
	}
    //메일 발송
    public void send() {
        mailSender.send(message);
    }

}