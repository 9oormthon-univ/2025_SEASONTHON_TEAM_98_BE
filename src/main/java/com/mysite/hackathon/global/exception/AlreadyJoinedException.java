package com.mysite.hackathon.global.exception;

/**
 * 이미 모임에 참여한 경우 발생하는 예외
 */
public class AlreadyJoinedException extends RuntimeException {
    public AlreadyJoinedException() {
        super("이미 참여한 모임입니다.");
    }
}
