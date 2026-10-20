package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.persistence.Asesor;
import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.DireccionC;
import com.keralty.aeconfiguraciones.persistence.EstadoContrato;
import com.keralty.aeconfiguraciones.persistence.FamiliaC;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.Sucursal;
import com.keralty.aeconfiguraciones.persistence.Tarifa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoCJpaRepository extends JpaRepository<ContratoC, Long>, ContratoCJpaRepositoryCustom {

    List<ContratoC> findBySolicitud(Solicitud solicitud);

    List<ContratoC> findByPersonaCContratante(PersonaC personaCContratante);

    List<ContratoC> findByDireccionesC(DireccionC direccionesC);

    List<ContratoC> findByBeneficiariosC(BeneficiarioC beneficiariosC);

    List<ContratoC> findByFamiliasC(FamiliaC familiasC);

    List<ContratoC> findByPeriodicidadC(Periodicidad periodicidadC);

    List<ContratoC> findByFormaPagoC(FormaPago formaPagoC);

    List<ContratoC> findByEstadoContratoC(EstadoContrato estadoContratoC);

    List<ContratoC> findByProductoC(Productos productoC);

    List<ContratoC> findByPlanC(Planes planC);

    List<ContratoC> findByTarifaC(Tarifa tarifaC);

    List<ContratoC> findByGrupoAsociadoC(GrupoAsociado grupoAsociadoC);

    List<ContratoC> findByMunicipioC(Municipio municipioC);

    List<ContratoC> findBySucursalC(Sucursal sucursalC);

    List<ContratoC> findByAsesorC(Asesor asesorC);
}
