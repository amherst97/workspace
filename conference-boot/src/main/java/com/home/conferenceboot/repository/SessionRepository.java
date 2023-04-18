package com.home.conferenceboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.conferenceboot.model.Session;

public interface SessionRepository extends JpaRepository<Session, Long>{

}
