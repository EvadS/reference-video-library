package com.se.video.library.dao.repository;

import com.se.video.library.dao.models.FileItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface FileRepository extends JpaRepository<FileItem, Long> {

}

