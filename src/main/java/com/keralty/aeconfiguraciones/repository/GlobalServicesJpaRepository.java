package com.keralty.aeconfiguraciones.repository;

import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.persistence.Companyia;
import com.keralty.aeconfiguraciones.persistence.ContratoCombo;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.EntidadFinancieraC;
import com.keralty.aeconfiguraciones.persistence.Eps;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.EstadoCivil;
import com.keralty.aeconfiguraciones.persistence.EstadoContrato;
import com.keralty.aeconfiguraciones.persistence.EstadoPersona;
import com.keralty.aeconfiguraciones.persistence.FechaIniEPS;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.FrecuenciaAct;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.Pais;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Regional;
import com.keralty.aeconfiguraciones.persistence.Sucursal;
import com.keralty.aeconfiguraciones.persistence.TipoDireccion;
import com.keralty.aeconfiguraciones.persistence.TipoEntidadC;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.TipoInstitucionC;
import com.keralty.aeconfiguraciones.persistence.TipoSucursalC;
import java.sql.Date;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalServicesJpaRepository {

    Collection<Productos> assocOperator001(Long tGSINC1PRODUCTOptpatrNumero) throws SystemException;

    Boolean assocOperator002(Long tGSINC1PRODUCTOptpatrNumero) throws SystemException;

    Collection<Productos> assocOperator004(Long tGSINC1PLANptpatrNumero) throws SystemException;

    Boolean assocOperator005(Long tGSINC1PLANptpatrNumero) throws SystemException;

    Collection<Planes> assocOperator007(Long tGSINC1PLANAUXptpatrCodigo) throws SystemException;

    Boolean assocOperator008(Long tGSINC1PLANAUXptpatrCodigo) throws SystemException;

    Collection<Parentesco> assocOperator010(Long tGSINC1PARENTESCOptpatrCodigo) throws SystemException;

    Boolean assocOperator011(Long tGSINC1PARENTESCOptpatrCodigo) throws SystemException;

    Collection<Periodicidad> assocOperator013(Long tGSINC1PERIODICIDADptpatrCodigo) throws SystemException;

    Boolean assocOperator014(Long tGSINC1PERIODICIDADptpatrCodigo) throws SystemException;

    Collection<EstadoContrato> assocOperator016(Long tGSINC1ESTADOCONTRATOptpatrCodigo) throws SystemException;

    Boolean assocOperator017(Long tGSINC1ESTADOCONTRATOptpatrCodigo) throws SystemException;

    Collection<EstadoBeneficiario> assocOperator019(Long tGSINC1ESTADOBENEFICIARIOptpatrCodigo) throws SystemException;

    Boolean assocOperator020(Long tGSINC1ESTADOBENEFICIARIOptpatrCodigo) throws SystemException;

    Collection<Regional> assocOperator022(Long tGSINC1REGIONALptpatrCodigo) throws SystemException;

    Boolean assocOperator023(Long tGSINC1REGIONALptpatrCodigo) throws SystemException;

    Collection<Regional> assocOperator025(Long tGSINC1SUCURSALptpatrCodigo) throws SystemException;

    Boolean assocOperator026(Long tGSINC1SUCURSALptpatrCodigo) throws SystemException;

    Collection<Sucursal> assocOperator028(Long tGSINC1SUCURSALAUXptpatrCodigo) throws SystemException;

    Boolean assocOperator029(Long tGSINC1SUCURSALAUXptpatrCodigo) throws SystemException;

    Collection<GrupoAsociado> assocOperator031(Long tGSINC1GRUPOASOCAUX2ptpatrCodigo) throws SystemException;

    Boolean assocOperator032(Long tGSINC1GRUPOASOCAUX2ptpatrCodigo) throws SystemException;

    Collection<Planes> assocOperator033(String tGSINC1GRUPOASOCAUXptpatrCodigo, Productos tGSINC1GRUPOASOCAUXptpagrProducto) throws SystemException;

    Collection<Productos> assocOperator034(String tGSINC1GRUPOASOCptpatrNumero) throws SystemException;

    Collection<EstadoCivil> assocOperator036(Long tGSINC1ESTADOCIVILptpatrCodigo) throws SystemException;

    Boolean assocOperator037(Long tGSINC1ESTADOCIVILptpatrCodigo) throws SystemException;

    Collection<Departamento> assocOperator039(Long tGSINC1DEPARTAMENTOptpatrCodigo) throws SystemException;

    Boolean assocOperator040(Long tGSINC1DEPARTAMENTOptpatrCodigo) throws SystemException;

    Collection<Companyia> assocOperator047(Long tGSINC1COMPANIAptpatrCodigo) throws SystemException;

    Boolean assocOperator048(Long tGSINC1COMPANIAptpatrCodigo) throws SystemException;

    Collection<Municipio> assocOperator050(Long tGSINC1MUNICIPIOptpatrCodigo) throws SystemException;

    Boolean assocOperator051(Long tGSINC1MUNICIPIOptpatrCodigo) throws SystemException;

    Collection<TipoDireccion> assocOperator053(Long tGSINC1TIPODIRECCIONptpatrCodigo) throws SystemException;

    Boolean assocOperator054(Long tGSINC1TIPODIRECCIONptpatrCodigo) throws SystemException;

    Collection<EstadoPersona> assocOperator056(Long tGSINC1ESTADOPERSONAptpatrCodigo) throws SystemException;

    Boolean assocOperator057(Long tGSINC1ESTADOPERSONAptpatrCodigo) throws SystemException;

    Collection<FormaPago> assocOperator059(Long tGSINC1FORMAPAGOptpatrCodigo) throws SystemException;

    Boolean assocOperator060(Long tGSINC1FORMAPAGOptpatrCodigo) throws SystemException;

    Collection<TipoIdentificacion> assocOperator062(Long tGSINC1TIPOIDENTIFICACIONptpatrCodigo) throws SystemException;

    Boolean assocOperator063(Long tGSINC1TIPOIDENTIFICACIONptpatrCodigo) throws SystemException;

    Collection<Eps> assocOperator065(Long tGSINC1EPSptpatrCodigo) throws SystemException;

    Boolean assocOperator066(Long tGSINC1EPSptpatrCodigo) throws SystemException;

    Collection<Pais> assocOperator068(String tGSINC1PAISptpatrCodigo) throws SystemException;

    Boolean assocOperator069(String tGSINC1PAISptpatrCodigo) throws SystemException;

    Collection<EntidadFinancieraC> assocOperator079(Long tGSINC1ENTIDADFINptpatrCodigo) throws SystemException;

    Boolean assocOperator080(Long tGSINC1ENTIDADFINptpatrCodigo) throws SystemException;

    Collection<TipoEntidadC> assocOperator082(Long tGSINC1TIPOENTIDADptpatrCodigo) throws SystemException;

    Boolean assocOperator083(Long tGSINC1TIPOENTIDADptpatrCodigo) throws SystemException;

    Collection<TipoInstitucionC> assocOperator085(Long tGSINC1TIPOINSTITUCIONptpatrCodigo) throws SystemException;

    Boolean assocOperator086(Long tGSINC1TIPOINSTITUCIONptpatrCodigo) throws SystemException;

    Collection<TipoSucursalC> assocOperator088(Long tGSINC1TIPOSUCURSALptpatrCodigo) throws SystemException;

    Boolean assocOperator089(Long tGSINC1TIPOSUCURSALptpatrCodigo) throws SystemException;

    Long assocOperator139(String tGCREARCOMBOptpatrContratoIntegral, String tGCREARCOMBOptpatrContratoOdontologico) throws SystemException;

    Long assocOperator140(ContratoCombo tGEDITARCOMBOptpthisContratoCombo, String tGEDITARCOMBOptpContratoIntegral, String tGEDITARCOMBOptpContratoOdontologico) throws SystemException;

    Long assocOperator143(Long tGADDPLANTREPptpatrTipo, String tGADDPLANTREPptpatrNombreArchivoPDF) throws SystemException;

    Long assocOperator144(PlantillaReporte tGEDITPLANTREPptpthisPlantillaReporte, String tGEDITPLANTREPptpNombreArchivoPDF) throws SystemException;

    Long assocOperator145(Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato) throws SystemException;

    Long assocOperator146(Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato) throws SystemException;

    Long assocOperator147(Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato) throws SystemException;

    Long assocOperator148(Date tGCREARFINIEPSptpatrDesde, Date tGCREARFINIEPSptpatrHasta, Long tGCREARFINIEPSptpatrTramite, Long tGCREARFINIEPSptpatrTipoContrato) throws SystemException;

    Long assocOperator149(FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta) throws SystemException;

    Long assocOperator150(FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta) throws SystemException;

    Long assocOperator151(FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta) throws SystemException;

    Long assocOperator152(FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS, Date tGMODFINIEPSptpDesde, Date tGMODFINIEPSptpHasta) throws SystemException;

    Long assocOperator153(String tGCREARCONTEXENTOptpatrNumContrato) throws SystemException;

    Collection<FrecuenciaAct> assocOperator158() throws SystemException;

    Collection<FrecuenciaAct> assocOperator159(Long tGFIJARDATOSSINCptNumParametrica) throws SystemException;

    Collection<FrecuenciaAct> assocOperator160(Long tGFIJARDATOSSINCptNumParametrica) throws SystemException;

    Collection<FrecuenciaAct> assocOperator161(Long tGFIJARDATOSSINCptNumParametrica) throws SystemException;

    void refreshEntity(Object object);
}
