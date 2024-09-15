package com.ninova.purchaseorderapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ninova.purchaseorderapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}

//
//    boolean existsByUsername(String username);
//
//    boolean existsByEmail(String email);
//
//    List<User> findByRole(String role);
//    
//    @Query("SELECT u FROM User u JOIN u.approvalWorkflows aw WHERE aw.id = :workflowId")
//    List<User> findByApprovalWorkflows_Id(@Param("workflowId") Long workflowId);
//}