package com.dw.NAMANSOLOJAVA.Repository;

import com.dw.NAMANSOLOJAVA.model.Follow;
import com.dw.NAMANSOLOJAVA.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

   List<Follow> findByFollowing(User user);
   List<Follow> findByFollower(User user);
   boolean existsByFollowerAndFollowing(User follower, User following);
   Optional<Follow> findByFollowerAndFollowing(User follower, User following);
}
