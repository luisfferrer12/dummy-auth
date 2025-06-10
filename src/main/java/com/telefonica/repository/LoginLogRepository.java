package com.telefonica.repository;

import com.telefonica.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoginLogRepository extends JpaRepository<LoginLog, UUID> {
}