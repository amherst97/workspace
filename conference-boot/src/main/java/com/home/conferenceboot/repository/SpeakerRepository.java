package com.home.conferenceboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.conferenceboot.model.Speaker;

public interface SpeakerRepository extends JpaRepository<Speaker, Long>{

}
