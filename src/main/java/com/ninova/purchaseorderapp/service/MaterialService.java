package com.ninova.purchaseorderapp.service;

import java.util.List;
import java.util.Optional;

import com.ninova.purchaseorderapp.entity.Material;

public interface MaterialService {
    List<Material> getAllMaterials();
    Optional<Material> getMaterialById(Long id);
    Material createMaterial(Material material);
    Material updateMaterial(Long id, Material material);
	Material saveMaterial(Material material);
	void deleteMaterial(Long id);
}
