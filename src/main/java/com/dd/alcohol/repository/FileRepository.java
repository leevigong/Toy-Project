package com.dd.alcohol.repository;

import com.dd.alcohol.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
