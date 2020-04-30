package com.avlija.hotel.repository;

import org.springframework.data.repository.CrudRepository;

import com.avlija.hotel.model.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>{

}
