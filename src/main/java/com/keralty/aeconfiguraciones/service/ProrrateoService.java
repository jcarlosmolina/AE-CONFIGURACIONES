package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.AesolicitudesConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.global.ProrrateoConstants;
import com.keralty.aeconfiguraciones.global.SolicitudConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProrrateoOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Prorrateo;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.repository.ProrrateoJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandNull;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathInstance;
import com.keralty.aeconfiguraciones.utils.Utilities;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;
import com.keralty.aeconfiguraciones.viewmodel.FilterVariableRequest;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class ProrrateoService extends AbstractService {

    ProrrateoJpaRepository repository;

    ProrrateoService prorrateoSrv;

    DominioService dominioSrv;

    PlanesService planesSrv;

    SolicitudService solicitudSrv;

    @Inject
    public ProrrateoService(
            ProrrateoService prorrateoSrv,
            DominioService dominioSrv,
            PlanesService planesSrv,
            SolicitudService solicitudSrv,
            ProrrateoJpaRepository repository) {
        this.prorrateoSrv = prorrateoSrv;
        this.dominioSrv = dominioSrv;
        this.planesSrv = planesSrv;
        this.solicitudSrv = solicitudSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, ProrrateoConstants.CLASS_NAME);
    }

    private List<Prorrateo> solveProrrateoTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, ProrrateoConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveProrrateoTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, ProrrateoConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Prorrateo
     *
     * @return Population of class Prorrateo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Prorrateo> get() {
        List<Prorrateo> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class Prorrateo
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class Prorrateo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<Prorrateo> instances = get(queryRequest);
        if(ProrrateoConstants.DSICPRORRATEO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICProrrateo())).collect(Collectors.toList());
        }
        if(ProrrateoConstants.DSDSPRORRATEO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSProrrateo())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class Prorrateo that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Prorrateo matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Prorrateo> get(QueryRequest queryRequest) {
        return solveProrrateoTypedQuery(queryRequest);
    }
    
    /*
     * Returns the list of instances that matches certain search criteria
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return List of instances that matches certain search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public QueryResponse getDynamic(QueryRequest queryRequest) {
        if (queryRequest.getDisplaySetName() == null || queryRequest.getDisplaySetName().isEmpty()) {
            return new PopulationResponseViewModel(solveProrrateoTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveProrrateoTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveProrrateoTypedQuery(queryRequest).stream()
                    .map(instance -> buildPreloadViewModel(instance, queryRequest.getDisplaySetName()))
                    .collect(Collectors.toList()));
        }
    }

    /*
     * Returns the population of a class with a certain structure
     *
     * @param relatedInstanceJsonOid Json Oid of the related instance
     *
     * @param relatedInstancePath Path to the related instance
     *
     * @param displaySetItems DisplaySet items for each returned instance
     *
     * @return List of instances
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public PopulationResponseViewModel getByRelatedDynamic(String relatedInstanceJsonOid, String relatedInstancePath, String displaySetItems) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstanceJsonOid))));
        return new PopulationResponseViewModel(solveProrrateoTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Prorrateo by ID
     *
     * @param dominioNumero Identification field dominioNumero
     *
     * @param prorrateoidProrrateo Identification field prorrateoidProrrateo
     *
     * @return Instance of Prorrateo
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Prorrateo getById(Long dominioNumero, Long prorrateoidProrrateo) {
        return getByOID(new ProrrateoOid(dominioNumero, prorrateoidProrrateo));
    }
    
    /*
     * Returns an instance of Prorrateo by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Prorrateo
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Prorrateo getByOID(ProrrateoOid oid) {
        Optional<Prorrateo> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Prorrateo by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Prorrateo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Prorrateo getByOIDWithHV(ProrrateoOid oid) {
        Optional<Prorrateo> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Prorrateo> getInstanceByOid(ProrrateoOid oid) {
        return oid != null && oid.getDominioNumero() != null
               ? repository.findById(oid)
               : Optional.empty();
    }

    /*
     * Returns an instance of Prorrateo by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Prorrateo
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Prorrateo getByJsonOid(String jsonOid) {
        ProrrateoOid oid = new ProrrateoOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Prorrateo related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Prorrateo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Prorrateo> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveProrrateoTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Prorrateo related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Prorrateo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Prorrateo> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Prorrateo
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Prorrateo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getProrrateoDynamic(ProrrateoOid oid, String displaySetItems) {
        return new InstanceResponseViewModel(getByOIDWithHV(oid), displaySetItems);
    }

    /*
     * Returns the supplementary info for a given instance
     *
     * @param oid Instance OID
     *
     * @param dsName DisplaySet name
     *
     * @return Supplementary info for a given instance
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public String getSuppInfo(ProrrateoOid oid, String dsName) {
        Prorrateo instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Prorrateo instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (ProrrateoConstants.DSICPRORRATEO.equals(dsName))
            return instance.buildICProrrateo();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(Prorrateo instance, String dsName) {
        if (ProrrateoConstants.DSICPRORRATEO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICProrrateo());
        if (ProrrateoConstants.DSDSPRORRATEO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSProrrateo());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyNavigationalFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if ("Ae_solicitudes_TNUEVAAFILIACION_ptProrrateo_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431956094976663FiltNav14(filterRequest, sql);
        if ("Solicitud_TMODENCABEZADO_ptProrrateo_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431957012480960FiltNav47(filterRequest, sql);
    }


    private void clas1431956094976663FiltNav14(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Planes aesolicitudesTNUEVAAFILIACIONptpagrPlan = filterRequest.hasFilterVariable(AesolicitudesConstants.ARG_NAME_TNUEVAAFILIACION_AESOLICITUDESTNUEVAAFILIACIONPTPAGRPLAN) ? planesSrv.getByOID(new PlanesOid().fromJsonString(toJson(filterRequest.getFilterVariable(AesolicitudesConstants.ARG_NAME_TNUEVAAFILIACION_AESOLICITUDESTNUEVAAFILIACIONPTPAGRPLAN)))) : null;

        // Dominio.PlanT = ptpagrPlan
        if (aesolicitudesTNUEVAAFILIACIONptpagrPlan != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(ProrrateoConstants.ROLE_NAME_DOMINIO + "." + DominioConstants.ROLE_NAME_PLANT, PlanesConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(AesolicitudesConstants.ARG_NAME_TNUEVAAFILIACION_AESOLICITUDESTNUEVAAFILIACIONPTPAGRPLAN, PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, aesolicitudesTNUEVAAFILIACIONptpagrPlan.getOid(), AesolicitudesConstants.ARG_NAME_TNUEVAAFILIACION_AESOLICITUDESTNUEVAAFILIACIONPTPAGRPLAN);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // Dia < getDay(systemDate())
        // '<' Comparison
        SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
        comparison2.setOperator("<");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(ProrrateoConstants.ATTR_NAME_PRORRATEODIA, Constants.Type.NAT.getTypeName());
        comparison2.setFirstOperand(operand113);
        
        // Operand 2
                if (STDFunctions.getDayFun(STDFunctions.systemDateFun()) != null) {
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(STDFunctions.getDayFun(STDFunctions.systemDateFun()), Constants.Type.INT.getTypeName()));
                } else {
                    comparison2.setSecondOperand(new SQLWhereOperandNull());
                    }
        conjunction.addOperand(comparison2);

        sql.addConjunction(conjunction);
    }

    private void clas1431957012480960FiltNav47(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Solicitud solicitudTMODENCABEZADOpthisSolicitud = filterRequest.hasFilterVariable(SolicitudConstants.ARG_NAME_TMODENCABEZADO_SOLICITUDTMODENCABEZADOPTHISSOLICITUD) ? solicitudSrv.getByOID(new SolicitudOid().fromJsonString(toJson(filterRequest.getFilterVariable(SolicitudConstants.ARG_NAME_TMODENCABEZADO_SOLICITUDTMODENCABEZADOPTHISSOLICITUD)))) : null;

        // Dominio.PlanT = pthisSolicitud.Aesolicitudes.PlanT
        if (solicitudTMODENCABEZADOpthisSolicitud != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(ProrrateoConstants.ROLE_NAME_DOMINIO + "." + DominioConstants.ROLE_NAME_PLANT, PlanesConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            if (solicitudTMODENCABEZADOpthisSolicitud.isNull() || solicitudTMODENCABEZADOpthisSolicitud.getAesolicitudes().getPlanT().isNull())
            {
            comparison1.setSecondOperand(new SQLWhereOperandNull());
            }
            else
            {
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(SolicitudConstants.ARG_NAME_TMODENCABEZADO_SOLICITUDTMODENCABEZADOPTHISSOLICITUD + "." + SolicitudConstants.ROLE_NAME_AESOLICITUDES + "." + AesolicitudesConstants.ROLE_NAME_PLANT, SolicitudConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, solicitudTMODENCABEZADOpthisSolicitud.getOid(), SolicitudConstants.ARG_NAME_TMODENCABEZADO_SOLICITUDTMODENCABEZADOPTHISSOLICITUD);
            comparison1.setSecondOperand(operand223);
            }
            conjunction.addOperand(comparison1);

        }        

        // Dia < getDay(systemDate())
        // '<' Comparison
        SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
        comparison2.setOperator("<");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(ProrrateoConstants.ATTR_NAME_PRORRATEODIA, Constants.Type.NAT.getTypeName());
        comparison2.setFirstOperand(operand113);
        
        // Operand 2
                if (STDFunctions.getDayFun(STDFunctions.systemDateFun()) != null) {
                    comparison2.setSecondOperand(new SQLWhereOperandLiteral(STDFunctions.getDayFun(STDFunctions.systemDateFun()), Constants.Type.INT.getTypeName()));
                } else {
                    comparison2.setSecondOperand(new SQLWhereOperandNull());
                    }
        conjunction.addOperand(comparison2);

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUProrrateo
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Prorrateo using displayset DSProrrateo that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.prorrateo.ds.DSProrrateo> piuPIUProrrateo(QueryRequest queryRequest) {
        return solveProrrateoTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.prorrateo.ds.DSProrrateo::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUProrrateo
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUProrrateo
     */
    public int getCount4PIUProrrateo(QueryRequest queryRequest) {
        return solveProrrateoTypedQuery(queryRequest).size();
    }

    // Service: crear

    /*
     * Entry point for service crear
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse crear(
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = prorrateoSrv.crearTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service crear
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse crearTxn(
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.CrearRequest request) throws ModelException {
        request.setProrrateocrearpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getProrrateocrearpagrDominio()));
        return prorrateoSrv.crearImplementation(request);
    }

    /*
     * Implements service crear
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse crearImplementation(
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio prorrateocrearpagrDominio = request.getProrrateocrearpagrDominioInstance();
        Long prorrateocrearpatrDia = request.getProrrateocrearpatrDia();
        Double prorrateocrearpatrPorcentaje = request.getProrrateocrearpatrPorcentaje();

        request.checkArguments();

        crearPreconditions(request);
        Prorrateo instance = new Prorrateo();
        instance.add2Dominio(prorrateocrearpagrDominio);

        instance.setProrrateoidProrrateo(Long.valueOf(0));
        instance.setProrrateoidProrrateo(repository.nextProrrateoidProrrateo(instance.getDominioNumero()));
        instance.setProrrateoDia(prorrateocrearpatrDia);
        instance.setProrrateoPorcentaje(prorrateocrearpatrPorcentaje);

        instance.setOid(instance.getOid());

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICProrrateo());

        return response;
    }

    /*
     * Checks service crear preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse crearCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setProrrateocrearpagrDominioInstance(dominioSrv.getByOIDWithHV(request.getProrrateocrearpagrDominio()));
        try {
            if("Clas_1547053432832527Pre_1".equals(request.getPreconditionId()))
                checkClas1547053432832527Pre1(request);
            if("Clas_1547053432832527Pre_2".equals(request.getPreconditionId()))
                checkClas1547053432832527Pre2(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void crearPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.CrearRequest request) 
            throws ModelException {
        checkClas1547053432832527Pre1(request);
        checkClas1547053432832527Pre2(request);
    }

    private void checkClas1547053432832527Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.CrearRequest request) 
            throws ModelException {
        // EXIST( pagrDominio.Prorrateos ) WHERE (pagrDominio.Prorrateos.Dia = patrDia) = false

        Dominio prorrateocrearpagrDominio = request.getProrrateocrearpagrDominioInstance();
        Long prorrateocrearpatrDia = request.getProrrateocrearpatrDia();
        try {
            boolean preconditionHolds = repository.assocOperator000(prorrateocrearpagrDominio, prorrateocrearpatrDia).equals(false);
            if (!preconditionHolds) throw new PreconditionException("Ya existe un porcentaje para el día indicado");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

    private void checkClas1547053432832527Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.CrearRequest request) 
            throws ModelException {
        // patrPorcentaje > 0

        Double prorrateocrearpatrPorcentaje = request.getProrrateocrearpatrPorcentaje();
        boolean preconditionHolds = prorrateocrearpatrPorcentaje > Utilities.convertToDouble(Utilities.convertToDouble(Long.valueOf(0)));
        if (!preconditionHolds) throw new PreconditionException("El porcentaje debe ser mayor que cero");
    }

    // Service: eliminar

    /*
     * Entry point for service eliminar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse eliminar(
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = prorrateoSrv.eliminarTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service eliminar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse eliminarTxn(
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.EliminarRequest request) throws ModelException {
        request.setProrrateoeliminarpthisProrrateoInstance(prorrateoSrv.getByOIDWithHV(request.getProrrateoeliminarpthisProrrateo()));
        return prorrateoSrv.eliminarImplementation(request);
    }

    /*
     * Implements service eliminar
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse eliminarImplementation(
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Prorrateo prorrateoeliminarpthisProrrateo = request.getProrrateoeliminarpthisProrrateoInstance();

        request.checkArguments();
        Prorrateo instance = prorrateoeliminarpthisProrrateo;


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICProrrateo());

        return response;
    }

    // Service: modificar

    /*
     * Entry point for service modificar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse modificar(
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = prorrateoSrv.modificarTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service modificar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse modificarTxn(
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.ModificarRequest request) throws ModelException {
        request.setProrrateomodificarpthisProrrateoInstance(prorrateoSrv.getByOIDWithHV(request.getProrrateomodificarpthisProrrateo()));
        return prorrateoSrv.modificarImplementation(request);
    }

    /*
     * Implements service modificar
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse modificarImplementation(
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Prorrateo prorrateomodificarpthisProrrateo = request.getProrrateomodificarpthisProrrateoInstance();
        Long prorrateomodificarpDia = request.getProrrateomodificarpDia();
        Double prorrateomodificarpPorcentaje = request.getProrrateomodificarpPorcentaje();

        request.checkArguments();

        modificarPreconditions(request);
        Prorrateo instance = prorrateomodificarpthisProrrateo;
        instance.setProrrateoDia(prorrateomodificarpDia);
        instance.setProrrateoPorcentaje(prorrateomodificarpPorcentaje);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICProrrateo());

        return response;
    }

    /*
     * Checks service modificar preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse modificarCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setProrrateomodificarpthisProrrateoInstance(prorrateoSrv.getByOIDWithHV(request.getProrrateomodificarpthisProrrateo()));
        try {
            if("Clas_1547053432832527Pre_4".equals(request.getPreconditionId()))
                checkClas1547053432832527Pre4(request);
            if("Clas_1547053432832527Pre_3".equals(request.getPreconditionId()))
                checkClas1547053432832527Pre3(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void modificarPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.ModificarRequest request) 
            throws ModelException {
        checkClas1547053432832527Pre4(request);
        checkClas1547053432832527Pre3(request);
    }

    private void checkClas1547053432832527Pre4 (
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.ModificarRequest request) 
            throws ModelException {
        // pDia = Dia OR EXIST( Dominio.Prorrateos ) WHERE (Dominio.Prorrateos.Dia = pDia) = false

        Long prorrateomodificarpDia = request.getProrrateomodificarpDia();
        Prorrateo prorrateomodificarpthisProrrateo = request.getProrrateomodificarpthisProrrateoInstance();
        try {
            boolean preconditionHolds = (prorrateomodificarpDia).equals(prorrateomodificarpthisProrrateo.getProrrateoDia()) || repository.assocOperator001(prorrateomodificarpthisProrrateo, prorrateomodificarpDia).equals(false);
            if (!preconditionHolds) throw new PreconditionException("Ya existe un porcentaje para el día indicado");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

    private void checkClas1547053432832527Pre3 (
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.ModificarRequest request) 
            throws ModelException {
        // pPorcentaje > 0

        Double prorrateomodificarpPorcentaje = request.getProrrateomodificarpPorcentaje();
        boolean preconditionHolds = prorrateomodificarpPorcentaje > Utilities.convertToDouble(Utilities.convertToDouble(Long.valueOf(0)));
        if (!preconditionHolds) throw new PreconditionException("El porcentaje debe ser mayor que cero");
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCProrrateo(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(ProrrateoConstants.CLASS_NAME, ProrrateoConstants.ROLE_NAME_DOMINIO + "." + DominioConstants.ATTR_FIELD_DOMINIONUMERO, false), true);
        select.getOrderByPart().addItem(Constants.normalizePath(ProrrateoConstants.CLASS_NAME, ProrrateoConstants.ATTR_FIELD_PRORRATEOIDPRORRATEO, false), true);
    }


    private void applyOCOCProrrateo(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_Prorrateo".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(ProrrateoConstants.CLASS_NAME, ProrrateoConstants.ATTR_NAME_PRORRATEODIA, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildProrrateoRelatedInstance(relatedInstanceJsonOid);
        }
        if (ProrrateoConstants.ROLE_NAME_DOMINIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildProrrateoRelatedInstance4Dominio(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildProrrateoRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Prorrateo(mapper.readValue(relatedInstanceJsonOid, ProrrateoOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildProrrateoRelatedInstance4Dominio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Dominio(mapper.readValue(relatedInstanceJsonOid, DominioOid.class)): Dominio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            ProrrateoOid oid = new ProrrateoOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(ProrrateoConstants.ROLE_NAME_DOMINIO + "." + DominioConstants.ATTR_FIELD_DOMINIONUMERO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getDominioNumero(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(ProrrateoConstants.ATTR_FIELD_PRORRATEOIDPRORRATEO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(oid.getProrrateoidProrrateo(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison2);
        } catch (Exception e) {
            LoggerFactory.getLogger(ProrrateoService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
