package com.example.demo.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Shoe;

public interface ShoeRepository extends JpaRepository<Shoe, Long> {
}

