package com.educandoweb.course.repositories;

import com.educandoweb.course.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Compenet registration não necessário, pois, JpaRepository que ja esta registrado como um componete do Spring
public interface CategoryRepository extends JpaRepository<Category, Long> {


}