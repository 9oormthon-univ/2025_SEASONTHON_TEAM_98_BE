package com.mysite.hackathon.global.exception;

/**
 * 모임을 찾을 수 없을 때 발생하는 예외
 */
public class MeetingNotFoundException extends RuntimeException {
    public MeetingNotFoundException(Long id) {
        super("존재하지 않는 모임입니다.");
    }
}
