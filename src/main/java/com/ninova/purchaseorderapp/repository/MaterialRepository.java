package com.ninova.purchaseorderapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ninova.purchaseorderapp.entity.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}