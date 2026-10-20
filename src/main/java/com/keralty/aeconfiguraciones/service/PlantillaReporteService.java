package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.global.PlantillaReporteConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaReporteOid;
import com.keralty.aeconfiguraciones.persistence.oid.ReportesDominioOid;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;
import com.keralty.aeconfiguraciones.persistence.ReportesDominio;
import com.keralty.aeconfiguraciones.repository.PlantillaReporteJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandSubQuery;
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
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class PlantillaReporteService extends AbstractService {

    PlantillaReporteJpaRepository repository;

    PlantillaReporteService plantillaReporteSrv;

    DominioService dominioSrv;

    ReportesDominioService reportesDominioSrv;

    @Inject
    public PlantillaReporteService(
            PlantillaReporteService plantillaReporteSrv,
            DominioService dominioSrv,
            ReportesDominioService reportesDominioSrv,
            PlantillaReporteJpaRepository repository) {
        this.plantillaReporteSrv = plantillaReporteSrv;
        this.dominioSrv = dominioSrv;
        this.reportesDominioSrv = reportesDominioSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, PlantillaReporteConstants.CLASS_NAME);
    }

    private List<PlantillaReporte> solvePlantillaReporteTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, PlantillaReporteConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solvePlantillaReporteTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, PlantillaReporteConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class PlantillaReporte
     *
     * @return Population of class PlantillaReporte
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PlantillaReporte> get() {
        List<PlantillaReporte> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class PlantillaReporte
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class PlantillaReporte
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<PlantillaReporte> instances = get(queryRequest);
        if(PlantillaReporteConstants.DSDSPLANTILLAREPORTE.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSPlantillaReporte())).collect(Collectors.toList());
        }
        if(PlantillaReporteConstants.DSICPLANTILLAREPORTE.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICPlantillaReporte())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class PlantillaReporte that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class PlantillaReporte matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PlantillaReporte> get(QueryRequest queryRequest) {
        return solvePlantillaReporteTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solvePlantillaReporteTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solvePlantillaReporteTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solvePlantillaReporteTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solvePlantillaReporteTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of PlantillaReporte by ID
     *
     * @param plantillaReporteidPlantillaReporte Identification field plantillaReporteidPlantillaReporte
     *
     * @return Instance of PlantillaReporte
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public PlantillaReporte getById(Long plantillaReporteidPlantillaReporte) {
        return getByOID(new PlantillaReporteOid(plantillaReporteidPlantillaReporte));
    }
    
    /*
     * Returns an instance of PlantillaReporte by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of PlantillaReporte
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public PlantillaReporte getByOID(PlantillaReporteOid oid) {
        Optional<PlantillaReporte> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of PlantillaReporte by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of PlantillaReporte
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public PlantillaReporte getByOIDWithHV(PlantillaReporteOid oid) {
        Optional<PlantillaReporte> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<PlantillaReporte> getInstanceByOid(PlantillaReporteOid oid) {
        return oid != null && oid.getPlantillaReporteidPlantillaReporte() != null
               ? repository.findById(oid.getPlantillaReporteidPlantillaReporte())
               : Optional.empty();
    }

    /*
     * Returns an instance of PlantillaReporte by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of PlantillaReporte
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public PlantillaReporte getByJsonOid(String jsonOid) {
        PlantillaReporteOid oid = new PlantillaReporteOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of PlantillaReporte related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of PlantillaReporte
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PlantillaReporte> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solvePlantillaReporteTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of PlantillaReporte related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of PlantillaReporte
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PlantillaReporte> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of PlantillaReporte
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of PlantillaReporte
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getPlantillaReporteDynamic(PlantillaReporteOid oid, String displaySetItems) {
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
    public String getSuppInfo(PlantillaReporteOid oid, String dsName) {
        PlantillaReporte instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(PlantillaReporte instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (PlantillaReporteConstants.DSICPLANTILLAREPORTE.equals(dsName))
            return instance.buildICPlantillaReporte();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(PlantillaReporte instance, String dsName) {
        if (PlantillaReporteConstants.DSDSPLANTILLAREPORTE.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSPlantillaReporte());
        if (PlantillaReporteConstants.DSICPLANTILLAREPORTE.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICPlantillaReporte());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (PlantillaReporteConstants.FILTER_NAME_FPLANTILLAREPORTE.equalsIgnoreCase(filterRequest.getName()))
            fPlantillaReporte(filterRequest, sql);
    }

    @Override
    protected void applyNavigationalFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if ("Dominio_TADDREPORTE_pt_p_agrPlantillaReporte_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431701422080423FiltNav15(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_PlantillaReporte".equals(filterRequest.getName()))
            suppInfo4ICPlantillaReporte(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void fPlantillaReporte(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Long vfTipo = filterRequest.hasFilterVariable(PlantillaReporteConstants.VAR_NAME_FPLANTILLAREPORTE_VFTIPO) ? (Long) filterRequest.getFilterVariable(PlantillaReporteConstants.VAR_NAME_FPLANTILLAREPORTE_VFTIPO, FilterVariableRequest.NAT) : null;
        Date vfFecha = filterRequest.hasFilterVariable(PlantillaReporteConstants.VAR_NAME_FPLANTILLAREPORTE_VFFECHA) ? (Date) filterRequest.getFilterVariable(PlantillaReporteConstants.VAR_NAME_FPLANTILLAREPORTE_VFFECHA, FilterVariableRequest.DATE) : null;

        // Tipo = vfTipo
        if (vfTipo != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PlantillaReporteConstants.ATTR_NAME_PLANTILLAREPORTETIPO, Constants.Type.NAT.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(vfTipo, Constants.Type.NAT.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        // FechaVigencia >= vfFecha
        if (vfFecha != null) {
            // '>=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator(">=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(PlantillaReporteConstants.ATTR_NAME_PLANTILLAREPORTEFECHAVIGENCIA, Constants.Type.DATE.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(vfFecha, Constants.Type.DATE.getTypeName()));
            conjunction.addOperand(comparison2);

        }        

        sql.addConjunction(conjunction);
    }

    private void clas1431701422080423FiltNav15(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Dominio dominioTADDREPORTEpthisDominio = filterRequest.hasFilterVariable(DominioConstants.ARG_NAME_TADDREPORTE_DOMINIOTADDREPORTEPTHISDOMINIO) ? dominioSrv.getByOID(new DominioOid().fromJsonString(toJson(filterRequest.getFilterVariable(DominioConstants.ARG_NAME_TADDREPORTE_DOMINIOTADDREPORTEPTHISDOMINIO)))) : null;

        // COUNT (ReportesDominio.Dominio) WHERE (ReportesDominio.Dominio = pthisDominio) = 0
        if (dominioTADDREPORTEpthisDominio != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
                // 'count' Association operator
                SQLWhereOperandSubQuery operandSubQueryAssocOperator000 = new SQLWhereOperandSubQuery(repository.assocOperator000Range1Query(dominioTADDREPORTEpthisDominio, sql));
            comparison1.setFirstOperand(operandSubQueryAssocOperator000);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(Long.valueOf(0), Constants.Type.NAT.getTypeName()));
            conjunction.addOperand(comparison1);

        }        
        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUPlantillaReporte
     *
     * @param queryRequest Query request
     * @return List of viewmodels for PlantillaReporte using displayset DSPlantillaReporte that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.plantillareporte.ds.DSPlantillaReporte> piuPIUPlantillaReporte(QueryRequest queryRequest) {
        return solvePlantillaReporteTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.plantillareporte.ds.DSPlantillaReporte::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUPlantillaReporte
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUPlantillaReporte
     */
    public int getCount4PIUPlantillaReporte(QueryRequest queryRequest) {
        return solvePlantillaReporteTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = plantillaReporteSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.CrearRequest request) throws ModelException {
        return plantillaReporteSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long plantillaReportecrearpatrTipo = request.getPlantillaReportecrearpatrTipo();
        String plantillaReportecrearpatrTitulo = request.getPlantillaReportecrearpatrTitulo();
        Date plantillaReportecrearpatrFechaVigencia = request.getPlantillaReportecrearpatrFechaVigencia();
        String plantillaReportecrearpatrRutaArchivos = request.getPlantillaReportecrearpatrRutaArchivos();
        String plantillaReportecrearpatrNombreArchivoPDF = request.getPlantillaReportecrearpatrNombreArchivoPDF();

        request.checkArguments();
        PlantillaReporte instance = new PlantillaReporte();
        instance.setPlantillaReporteidPlantillaReporte(Long.valueOf(0));
        instance.setPlantillaReporteTipo(plantillaReportecrearpatrTipo);
        instance.setPlantillaReporteTitulo(plantillaReportecrearpatrTitulo);
        instance.setPlantillaReporteFechaVigencia(plantillaReportecrearpatrFechaVigencia);
        instance.setPlantillaReporteRutaArchivos(plantillaReportecrearpatrRutaArchivos);
        instance.setPlantillaReporteNombreArchivoPDF(plantillaReportecrearpatrNombreArchivoPDF);

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPlantillaReporte());

        return response;
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
            com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = plantillaReporteSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.EliminarRequest request) throws ModelException {
        request.setPlantillaReporteeliminarpthisPlantillaReporteInstance(plantillaReporteSrv.getByOIDWithHV(request.getPlantillaReporteeliminarpthisPlantillaReporte()));
        return plantillaReporteSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PlantillaReporte plantillaReporteeliminarpthisPlantillaReporte = request.getPlantillaReporteeliminarpthisPlantillaReporteInstance();

        request.checkArguments();

        eliminarPreconditions(request);
        PlantillaReporte instance = plantillaReporteeliminarpthisPlantillaReporte;
        if(plantillaReporteeliminarpthisPlantillaReporte.getReportesDominio().size() > 0) {
            throw new MinCardinalityException(PlantillaReporteConstants.CLASS_ALIAS, PlantillaReporteConstants.ROLE_ALIAS_REPORTESDOMINIO);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICPlantillaReporte());

        return response;
    }

    /*
     * Checks service eliminar preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse eliminarCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setPlantillaReporteeliminarpthisPlantillaReporteInstance(plantillaReporteSrv.getByOIDWithHV(request.getPlantillaReporteeliminarpthisPlantillaReporte()));
        try {
            if("Clas_1623831347200006Pre_1".equals(request.getPreconditionId()))
                checkClas1623831347200006Pre1(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void eliminarPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.EliminarRequest request) 
            throws ModelException {
        checkClas1623831347200006Pre1(request);
    }

    private void checkClas1623831347200006Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.EliminarRequest request) 
            throws ModelException {
        // COUNT( ReportesDominio ) = 0

        PlantillaReporte plantillaReporteeliminarpthisPlantillaReporte = request.getPlantillaReporteeliminarpthisPlantillaReporteInstance();
        try {
            boolean preconditionHolds = (repository.assocOperator001(plantillaReporteeliminarpthisPlantillaReporte)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("No puede eliminarse el reporte por estar asociado a " + STDFunctions.intToStrFun(repository.assocOperator002(plantillaReporteeliminarpthisPlantillaReporte)) + " Dominios");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
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
            com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = plantillaReporteSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.ModificarRequest request) throws ModelException {
        request.setPlantillaReportemodificarpthisPlantillaReporteInstance(plantillaReporteSrv.getByOIDWithHV(request.getPlantillaReportemodificarpthisPlantillaReporte()));
        return plantillaReporteSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PlantillaReporte plantillaReportemodificarpthisPlantillaReporte = request.getPlantillaReportemodificarpthisPlantillaReporteInstance();
        String plantillaReportemodificarpTitulo = request.getPlantillaReportemodificarpTitulo();
        Date plantillaReportemodificarpFechaVigencia = request.getPlantillaReportemodificarpFechaVigencia();
        String plantillaReportemodificarpRutaArchivos = request.getPlantillaReportemodificarpRutaArchivos();
        String plantillaReportemodificarpNombreArchivoPDF = request.getPlantillaReportemodificarpNombreArchivoPDF();

        request.checkArguments();
        PlantillaReporte instance = plantillaReportemodificarpthisPlantillaReporte;
        instance.setPlantillaReporteTitulo(plantillaReportemodificarpTitulo);
        instance.setPlantillaReporteFechaVigencia(plantillaReportemodificarpFechaVigencia);
        instance.setPlantillaReporteRutaArchivos(plantillaReportemodificarpRutaArchivos);
        instance.setPlantillaReporteNombreArchivoPDF(plantillaReportemodificarpNombreArchivoPDF);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPlantillaReporte());

        return response;
    }


    private void suppInfo4ICPlantillaReporte(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(PlantillaReporteConstants.ATTR_NAME_PLANTILLAREPORTETITULO, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison1);

            SQLWhereConjunction conjunction = new SQLWhereConjunction();
            conjunction.addDisjunction(disjunction);
            select.addConjunction(conjunction);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCPlantillaReporte(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(PlantillaReporteConstants.CLASS_NAME, PlantillaReporteConstants.ATTR_FIELD_PLANTILLAREPORTEIDPLANTILLAREPORTE, false), true);
    }


    private void applyOCOCPlantillaReporte(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_PlantillaReporte".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(PlantillaReporteConstants.CLASS_NAME, PlantillaReporteConstants.ATTR_NAME_PLANTILLAREPORTETIPO, false), true);
            select.getOrderByPart().addItem(Constants.normalizePath(PlantillaReporteConstants.CLASS_NAME, PlantillaReporteConstants.ATTR_NAME_PLANTILLAREPORTEFECHAVIGENCIA, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildPlantillaReporteRelatedInstance(relatedInstanceJsonOid);
        }
        if (PlantillaReporteConstants.ROLE_NAME_REPORTESDOMINIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPlantillaReporteRelatedInstance4ReportesDominio(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildPlantillaReporteRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new PlantillaReporte(mapper.readValue(relatedInstanceJsonOid, PlantillaReporteOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPlantillaReporteRelatedInstance4ReportesDominio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ReportesDominio(mapper.readValue(relatedInstanceJsonOid, ReportesDominioOid.class)): ReportesDominio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            PlantillaReporteOid oid = new PlantillaReporteOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(PlantillaReporteConstants.ATTR_FIELD_PLANTILLAREPORTEIDPLANTILLAREPORTE, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getPlantillaReporteidPlantillaReporte(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(PlantillaReporteService.class).error(e.getMessage());
        }
    }

}
