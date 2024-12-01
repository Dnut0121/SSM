package com.example.shoppingmall.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users") // 테이블 이름 설정
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uSeq; // 기본키

    @Column(name = "id", nullable = false, unique = true) // 중복 허용 불가
    private String id; // 사용자 아이디

    @Column(name = "pw", nullable = false)
    private String pw; // 비밀번호

    @Column(name = "rrn" ,nullable = false, unique = true) // 고유한 주민등록번호
    private int rrn;
    
    @Column(name = "size")
    private int size; // 추가 정보 (의미에 따라 이름 변경 고려 가능)
}
