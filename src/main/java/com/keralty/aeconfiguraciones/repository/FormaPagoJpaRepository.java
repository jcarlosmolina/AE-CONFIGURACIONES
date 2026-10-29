package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagoJpaRepository extends JpaRepository<FormaPago, Long>, FormaPagoJpaRepositoryCustom {

    List<FormaPago> findByDominios(Dominio dominios);

    List<FormaPago> findBySolicitudes(Solicitud solicitudes);

    List<FormaPago> findByContratosC(ContratoC contratosC);
}
