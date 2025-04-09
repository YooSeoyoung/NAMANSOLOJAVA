package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.model.ReComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommentRepository extends JpaRepository<ReComment,Long> {
 List<ReComment> findByCommentIdIn(List<Long> commentIds);

 void deleteAllByCommentId(Long commentId);

List<ReComment> findByCommentId(Long id);
}
