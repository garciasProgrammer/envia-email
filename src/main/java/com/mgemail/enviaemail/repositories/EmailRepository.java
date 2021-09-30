package com.mgemail.enviaemail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mgemail.enviaemail.models.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, Long>{

}
