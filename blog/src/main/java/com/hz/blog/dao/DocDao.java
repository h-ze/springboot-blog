package com.hz.blog.dao;

import com.hz.blog.entity.Document;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DocDao {
    int addDoc(Document document);
    int deleteDoc(String docId);
    int updateDoc(Document document);
    Document getDoc(String docId);
    List<Document> getDocs(String userId);
    List<Document> getDocsPage(String userId);

    int addDocs(@Param("documents") List<Document> documents);
}
