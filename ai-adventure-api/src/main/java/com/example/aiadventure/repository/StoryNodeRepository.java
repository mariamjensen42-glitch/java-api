package com.example.aiadventure.repository;

import com.example.aiadventure.model.StoryNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryNodeRepository extends JpaRepository<StoryNode, Long> {

    List<StoryNode> findByGameId(Long gameId);

    List<StoryNode> findByParentNodeId(Long parentNodeId);

}
