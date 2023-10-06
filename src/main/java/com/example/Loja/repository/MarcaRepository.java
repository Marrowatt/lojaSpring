<<<<<<< HEAD
//package com.example.Loja.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import com.example.Loja.model.Marca;
//
//public interface lojaRepository extends JpaRepository<Marca, Long>{
//
//	
//	
//}
=======
package com.example.Loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Loja.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{

	Marca findByName(String marca);
	
}
>>>>>>> 0bc14c190ef9b153b37253caef0b24fd9b54b209
