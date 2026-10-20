package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.AreaMedica;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.persistence.LogGrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.oid.AreaMedicaOid;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.FuncionarioOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;
import com.keralty.aeconfiguraciones.persistence.oid.LogGrupoAsignacionOid;
import com.keralty.aeconfiguraciones.repository.GrupoAsignacionJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
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
public class GrupoAsignacionService extends AbstractService {

    GrupoAsignacionJpaRepository repository;

    GrupoAsignacionService grupoAsignacionSrv;

    DominioService dominioSrv;

    GrupoAsignacionNovedadService grupoAsignacionNovedadSrv;

    LogGrupoAsignacionService logGrupoAsignacionSrv;

    @Inject
    public GrupoAsignacionService(
            GrupoAsignacionService grupoAsignacionSrv,
            DominioService dominioSrv,
            GrupoAsignacionNovedadService grupoAsignacionNovedadSrv,
            LogGrupoAsignacionService logGrupoAsignacionSrv,
            GrupoAsignacionJpaRepository repository) {
        this.grupoAsignacionSrv = grupoAsignacionSrv;
        this.dominioSrv = dominioSrv;
        this.grupoAsignacionNovedadSrv = grupoAsignacionNovedadSrv;
        this.logGrupoAsignacionSrv = logGrupoAsignacionSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, GrupoAsignacionConstants.CLASS_NAME);
    }

    private List<GrupoAsignacion> solveGrupoAsignacionTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, GrupoAsignacionConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveGrupoAsignacionTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, GrupoAsignacionConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class GrupoAsignacion
     *
     * @return Population of class GrupoAsignacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<GrupoAsignacion> get() {
        List<GrupoAsignacion> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class GrupoAsignacion
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class GrupoAsignacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<GrupoAsignacion> instances = get(queryRequest);
        if(GrupoAsignacionConstants.DSDSGRUPOASIGNACION.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSGrupoAsignacion())).collect(Collectors.toList());
        }
        if(GrupoAsignacionConstants.DSICGRUPOASIGNACION.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICGrupoAsignacion())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class GrupoAsignacion that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class GrupoAsignacion matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<GrupoAsignacion> get(QueryRequest queryRequest) {
        return solveGrupoAsignacionTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveGrupoAsignacionTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveGrupoAsignacionTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveGrupoAsignacionTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveGrupoAsignacionTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of GrupoAsignacion by ID
     *
     * @param grupoAsignacionId Identification field grupoAsignacionId
     *
     * @return Instance of GrupoAsignacion
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public GrupoAsignacion getById(Long grupoAsignacionId) {
        return getByOID(new GrupoAsignacionOid(grupoAsignacionId));
    }
    
    /*
     * Returns an instance of GrupoAsignacion by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of GrupoAsignacion
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public GrupoAsignacion getByOID(GrupoAsignacionOid oid) {
        Optional<GrupoAsignacion> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of GrupoAsignacion by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of GrupoAsignacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public GrupoAsignacion getByOIDWithHV(GrupoAsignacionOid oid) {
        Optional<GrupoAsignacion> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<GrupoAsignacion> getInstanceByOid(GrupoAsignacionOid oid) {
        return oid != null && oid.getGrupoAsignacionId() != null
               ? repository.findById(oid.getGrupoAsignacionId())
               : Optional.empty();
    }

    /*
     * Returns an instance of GrupoAsignacion by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of GrupoAsignacion
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public GrupoAsignacion getByJsonOid(String jsonOid) {
        GrupoAsignacionOid oid = new GrupoAsignacionOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of GrupoAsignacion related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of GrupoAsignacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<GrupoAsignacion> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveGrupoAsignacionTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of GrupoAsignacion related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of GrupoAsignacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<GrupoAsignacion> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of GrupoAsignacion
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of GrupoAsignacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getGrupoAsignacionDynamic(GrupoAsignacionOid oid, String displaySetItems) {
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
    public String getSuppInfo(GrupoAsignacionOid oid, String dsName) {
        GrupoAsignacion instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(GrupoAsignacion instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (GrupoAsignacionConstants.DSICGRUPOASIGNACION.equals(dsName))
            return instance.buildICGrupoAsignacion();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(GrupoAsignacion instance, String dsName) {
        if (GrupoAsignacionConstants.DSDSGRUPOASIGNACION.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSGrupoAsignacion());
        if (GrupoAsignacionConstants.DSICGRUPOASIGNACION.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICGrupoAsignacion());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (GrupoAsignacionConstants.FILTER_NAME_FGRUPOASIGNACIONPORTIPO.equalsIgnoreCase(filterRequest.getName()))
            fGrupoAsignacionPorTipo(filterRequest, sql);
    }

    @Override
    protected void applyNavigationalFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if ("Dominio_TADDGRPASESORES_ptGrupo_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431701422080423FiltNav13(sql);
        if ("Dominio_TADDGRPMEDICO_ptGrupo_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431701422080423FiltNav14(sql);
        if ("GrupoAsignacionNovedad_TCREAR_pt_p_agrGrupoUsuarios_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1586852003840652FiltNav3(sql);
        if ("GrupoAsignacionNovedad_TASGGRUPO_ptGrupo_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1586852003840652FiltNav5(sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_GrupoAsignacion".equals(filterRequest.getName()))
            suppInfo4ICGrupoAsignacion(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void fGrupoAsignacionPorTipo(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Long vfTipo = filterRequest.hasFilterVariable(GrupoAsignacionConstants.VAR_NAME_FGRUPOASIGNACIONPORTIPO_VFTIPO) ? (Long) filterRequest.getFilterVariable(GrupoAsignacionConstants.VAR_NAME_FGRUPOASIGNACIONPORTIPO_VFTIPO, FilterVariableRequest.NAT) : null;

        // Tipo = vfTipo
        if (vfTipo != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(GrupoAsignacionConstants.ATTR_NAME_GRUPOASIGNACIONTIPO, Constants.Type.NAT.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(vfTipo, Constants.Type.NAT.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        sql.addConjunction(conjunction);
    }

    private void clas1431701422080423FiltNav13(SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();

        // Tipo = 1
        // '=' Comparison
        SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
        comparison1.setOperator("=");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(GrupoAsignacionConstants.ATTR_NAME_GRUPOASIGNACIONTIPO, Constants.Type.NAT.getTypeName());
        comparison1.setFirstOperand(operand112);
        
        // Operand 2
        comparison1.setSecondOperand(new SQLWhereOperandLiteral(Long.valueOf(1), Constants.Type.NAT.getTypeName()));
        conjunction.addOperand(comparison1);

        sql.addConjunction(conjunction);
    }

    private void clas1431701422080423FiltNav14(SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();

        // Tipo = 2
        // '=' Comparison
        SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
        comparison1.setOperator("=");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(GrupoAsignacionConstants.ATTR_NAME_GRUPOASIGNACIONTIPO, Constants.Type.NAT.getTypeName());
        comparison1.setFirstOperand(operand112);
        
        // Operand 2
        comparison1.setSecondOperand(new SQLWhereOperandLiteral(Long.valueOf(2), Constants.Type.NAT.getTypeName()));
        conjunction.addOperand(comparison1);

        sql.addConjunction(conjunction);
    }

    private void clas1586852003840652FiltNav3(SQLSelect sql) {
        clas1431701422080423FiltNav13(sql);
    }

    private void clas1586852003840652FiltNav5(SQLSelect sql) {
        clas1431701422080423FiltNav13(sql);
    }

    // IIU Methods

    /*
     * Returns a viewmodel for class GrupoAsignacion with displayset DSGrupoAsignacion for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class GrupoAsignacion with displayset DSGrupoAsignacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.ds.DSGrupoAsignacion iIUGrupoMDominio(GrupoAsignacionOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.ds.DSGrupoAsignacion(getByOIDWithHV(oid));
    }

    /*
     * Returns a viewmodel for class GrupoAsignacion with displayset DSGrupoAsignacion for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class GrupoAsignacion with displayset DSGrupoAsignacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.ds.DSGrupoAsignacion iIUGrupoMedicoMDominio(GrupoAsignacionOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.ds.DSGrupoAsignacion(getByOIDWithHV(oid));
    }


    /*
     * Solves the query needed for PIUGrupoAsignacion
     *
     * @param queryRequest Query request
     * @return List of viewmodels for GrupoAsignacion using displayset DSGrupoAsignacion that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.ds.DSGrupoAsignacion> piuPIUGrupoAsignacion(QueryRequest queryRequest) {
        return solveGrupoAsignacionTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.ds.DSGrupoAsignacion::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUGrupoAsignacion
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUGrupoAsignacion
     */
    public int getCount4PIUGrupoAsignacion(QueryRequest queryRequest) {
        return solveGrupoAsignacionTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUGrupoAsignacion1
     *
     * @param queryRequest Query request
     * @return List of viewmodels for GrupoAsignacion using displayset AutoDS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.ds.AutoDS> piuPIUGrupoAsignacion1(QueryRequest queryRequest) {
        return solveGrupoAsignacionTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.ds.AutoDS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUGrupoAsignacion1
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUGrupoAsignacion1
     */
    public int getCount4PIUGrupoAsignacion1(QueryRequest queryRequest) {
        return solveGrupoAsignacionTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.CrearRequest request) throws ModelException {
        return grupoAsignacionSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long grupoAsignacioncrearpatrTipo = request.getGrupoAsignacioncrearpatrTipo();
        String grupoAsignacioncrearpatrNombre = request.getGrupoAsignacioncrearpatrNombre();
        String grupoAsignacioncrearpatrDescripcion = request.getGrupoAsignacioncrearpatrDescripcion();

        request.checkArguments();
        GrupoAsignacion instance = new GrupoAsignacion();
        instance.setGrupoAsignacionId(Long.valueOf(0));
        instance.setGrupoAsignacionTipo(grupoAsignacioncrearpatrTipo);
        instance.setGrupoAsignacionNombre(grupoAsignacioncrearpatrNombre);
        instance.setGrupoAsignacionDescripcion(grupoAsignacioncrearpatrDescripcion);

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.EliminarRequest request) throws ModelException {
        request.setGrupoAsignacioneliminarpthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacioneliminarpthisGrupoAsignacion()));
        return grupoAsignacionSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion grupoAsignacioneliminarpthisGrupoAsignacion = request.getGrupoAsignacioneliminarpthisGrupoAsignacionInstance();

        request.checkArguments();
        GrupoAsignacion instance = grupoAsignacioneliminarpthisGrupoAsignacion;
        if(grupoAsignacioneliminarpthisGrupoAsignacion.getDominioAsesores().size() > 0) {
            throw new MinCardinalityException(GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ROLE_ALIAS_DOMINIOASESORES);
        }
        if(grupoAsignacioneliminarpthisGrupoAsignacion.getDominioAreaMedica().size() > 0) {
            throw new MinCardinalityException(GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ROLE_ALIAS_DOMINIOAREAMEDICA);
        }
        if(grupoAsignacioneliminarpthisGrupoAsignacion.getLogGrupoAsignacion().size() > 0) {
            throw new MinCardinalityException(GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ROLE_ALIAS_LOGGRUPOASIGNACION);
        }
        if(grupoAsignacioneliminarpthisGrupoAsignacion.getDominioCausalUsuario().size() > 0) {
            throw new MinCardinalityException(GrupoAsignacionConstants.CLASS_ALIAS, GrupoAsignacionConstants.ROLE_ALIAS_DOMINIOCAUSALUSUARIO);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.ModificarRequest request) throws ModelException {
        request.setGrupoAsignacionmodificarpthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionmodificarpthisGrupoAsignacion()));
        return grupoAsignacionSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion grupoAsignacionmodificarpthisGrupoAsignacion = request.getGrupoAsignacionmodificarpthisGrupoAsignacionInstance();
        String grupoAsignacionmodificarpNombre = request.getGrupoAsignacionmodificarpNombre();
        String grupoAsignacionmodificarpDescripcion = request.getGrupoAsignacionmodificarpDescripcion();

        request.checkArguments();
        GrupoAsignacion instance = grupoAsignacionmodificarpthisGrupoAsignacion;
        instance.setGrupoAsignacionNombre(grupoAsignacionmodificarpNombre);
        instance.setGrupoAsignacionDescripcion(grupoAsignacionmodificarpDescripcion);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

        return response;
    }

    // Service: AddFuncionarioGrupo

    /*
     * Entry point for service AddFuncionarioGrupo
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse addFuncionarioGrupo(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.AddFuncionarioGrupoRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.addFuncionarioGrupoTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service AddFuncionarioGrupo
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse addFuncionarioGrupoTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.AddFuncionarioGrupoRequest request) throws ModelException {
        request.setGrupoAsignacionAddFuncionarioGrupopthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionAddFuncionarioGrupopthisGrupoAsignacion()));
        request.setGrupoAsignacionAddFuncionarioGrupopevcFuncionarioInstance(funcionarioSrv.getByOIDWithHV(request.getGrupoAsignacionAddFuncionarioGrupopevcFuncionario()));
        return grupoAsignacionSrv.addFuncionarioGrupoImplementation(request, true);
    }

    /*
     * Implements service AddFuncionarioGrupo
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse addFuncionarioGrupoImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.AddFuncionarioGrupoRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion grupoAsignacionAddFuncionarioGrupopthisGrupoAsignacion = request.getGrupoAsignacionAddFuncionarioGrupopthisGrupoAsignacionInstance();
        Funcionario grupoAsignacionAddFuncionarioGrupopevcFuncionario = request.getGrupoAsignacionAddFuncionarioGrupopevcFuncionarioInstance();

        request.checkArguments();
        GrupoAsignacion instance = grupoAsignacionAddFuncionarioGrupopthisGrupoAsignacion;

        instance.getFuncionario().add(grupoAsignacionAddFuncionarioGrupopevcFuncionario);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.funcionario.siu.AddFuncionarioGrupoRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.funcionario.siu.AddFuncionarioGrupoRequest();
            relatedRequest.setFuncionarioAddFuncionarioGrupopthisFuncionarioInstance(grupoAsignacionAddFuncionarioGrupopevcFuncionario);
            relatedRequest.setFuncionarioAddFuncionarioGrupopevcGrupoAsignacionInstance(grupoAsignacionAddFuncionarioGrupopthisGrupoAsignacion);
            funcionarioSrv.addFuncionarioGrupoImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

        return response;
    }

    // Service: DelFuncionarioGrupo

    /*
     * Entry point for service DelFuncionarioGrupo
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delFuncionarioGrupo(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelFuncionarioGrupoRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.delFuncionarioGrupoTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelFuncionarioGrupo
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delFuncionarioGrupoTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelFuncionarioGrupoRequest request) throws ModelException {
        request.setGrupoAsignacionDelFuncionarioGrupopthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionDelFuncionarioGrupopthisGrupoAsignacion()));
        request.setGrupoAsignacionDelFuncionarioGrupopevcFuncionarioInstance(funcionarioSrv.getByOIDWithHV(request.getGrupoAsignacionDelFuncionarioGrupopevcFuncionario()));
        return grupoAsignacionSrv.delFuncionarioGrupoImplementation(request, true);
    }

    /*
     * Implements service DelFuncionarioGrupo
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delFuncionarioGrupoImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelFuncionarioGrupoRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion grupoAsignacionDelFuncionarioGrupopthisGrupoAsignacion = request.getGrupoAsignacionDelFuncionarioGrupopthisGrupoAsignacionInstance();
        Funcionario grupoAsignacionDelFuncionarioGrupopevcFuncionario = request.getGrupoAsignacionDelFuncionarioGrupopevcFuncionarioInstance();

        request.checkArguments();
        GrupoAsignacion instance = grupoAsignacionDelFuncionarioGrupopthisGrupoAsignacion;

        instance.getFuncionario().remove(grupoAsignacionDelFuncionarioGrupopevcFuncionario);
        grupoAsignacionDelFuncionarioGrupopevcFuncionario.getGrupoAsignacion().remove(instance);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.funcionario.siu.DelFuncionarioGrupoRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.funcionario.siu.DelFuncionarioGrupoRequest();
            relatedRequest.setFuncionarioDelFuncionarioGrupopthisFuncionarioInstance(grupoAsignacionDelFuncionarioGrupopevcFuncionario);
            relatedRequest.setFuncionarioDelFuncionarioGrupopevcGrupoAsignacionInstance(grupoAsignacionDelFuncionarioGrupopthisGrupoAsignacion);
            funcionarioSrv.delFuncionarioGrupoImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

        return response;
    }

    // Service: InsGrupoAsesores

    /*
     * Entry point for service InsGrupoAsesores
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insGrupoAsesores(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.InsGrupoAsesoresRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.insGrupoAsesoresTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsGrupoAsesores
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insGrupoAsesoresTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.InsGrupoAsesoresRequest request) throws ModelException {
        request.setGrupoAsignacionInsGrupoAsesorespthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionInsGrupoAsesorespthisGrupoAsignacion()));
        request.setGrupoAsignacionInsGrupoAsesorespevcDominioInstance(dominioSrv.getByOIDWithHV(request.getGrupoAsignacionInsGrupoAsesorespevcDominio()));
        return grupoAsignacionSrv.insGrupoAsesoresImplementation(request, true);
    }

    /*
     * Implements service InsGrupoAsesores
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insGrupoAsesoresImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.InsGrupoAsesoresRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion grupoAsignacionInsGrupoAsesorespthisGrupoAsignacion = request.getGrupoAsignacionInsGrupoAsesorespthisGrupoAsignacionInstance();
        Dominio grupoAsignacionInsGrupoAsesorespevcDominio = request.getGrupoAsignacionInsGrupoAsesorespevcDominioInstance();

        request.checkArguments();
        GrupoAsignacion instance = grupoAsignacionInsGrupoAsesorespthisGrupoAsignacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsGrupoAsesoresRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsGrupoAsesoresRequest();
            relatedRequest.setDominioInsGrupoAsesorespthisDominioInstance(grupoAsignacionInsGrupoAsesorespevcDominio);
            relatedRequest.setDominioInsGrupoAsesorespevcGrupoAsignacionInstance(grupoAsignacionInsGrupoAsesorespthisGrupoAsignacion);
            dominioSrv.insGrupoAsesoresImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

        return response;
    }

    // Service: DelGrupoAsesores

    /*
     * Entry point for service DelGrupoAsesores
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delGrupoAsesores(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelGrupoAsesoresRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.delGrupoAsesoresTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelGrupoAsesores
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delGrupoAsesoresTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelGrupoAsesoresRequest request) throws ModelException {
        request.setGrupoAsignacionDelGrupoAsesorespthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionDelGrupoAsesorespthisGrupoAsignacion()));
        request.setGrupoAsignacionDelGrupoAsesorespevcDominioInstance(dominioSrv.getByOIDWithHV(request.getGrupoAsignacionDelGrupoAsesorespevcDominio()));
        return grupoAsignacionSrv.delGrupoAsesoresImplementation(request, true);
    }

    /*
     * Implements service DelGrupoAsesores
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delGrupoAsesoresImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelGrupoAsesoresRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion grupoAsignacionDelGrupoAsesorespthisGrupoAsignacion = request.getGrupoAsignacionDelGrupoAsesorespthisGrupoAsignacionInstance();
        Dominio grupoAsignacionDelGrupoAsesorespevcDominio = request.getGrupoAsignacionDelGrupoAsesorespevcDominioInstance();

        request.checkArguments();
        GrupoAsignacion instance = grupoAsignacionDelGrupoAsesorespthisGrupoAsignacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelGrupoAsesoresRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelGrupoAsesoresRequest();
            relatedRequest.setDominioDelGrupoAsesorespthisDominioInstance(grupoAsignacionDelGrupoAsesorespevcDominio);
            relatedRequest.setDominioDelGrupoAsesorespevcGrupoAsignacionInstance(grupoAsignacionDelGrupoAsesorespthisGrupoAsignacion);
            dominioSrv.delGrupoAsesoresImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

        return response;
    }

    // Service: InsGrupoAreaMedica

    /*
     * Entry point for service InsGrupoAreaMedica
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insGrupoAreaMedica(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.InsGrupoAreaMedicaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.insGrupoAreaMedicaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsGrupoAreaMedica
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insGrupoAreaMedicaTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.InsGrupoAreaMedicaRequest request) throws ModelException {
        request.setGrupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion()));
        request.setGrupoAsignacionInsGrupoAreaMedicapevcDominioInstance(dominioSrv.getByOIDWithHV(request.getGrupoAsignacionInsGrupoAreaMedicapevcDominio()));
        return grupoAsignacionSrv.insGrupoAreaMedicaImplementation(request, true);
    }

    /*
     * Implements service InsGrupoAreaMedica
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insGrupoAreaMedicaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.InsGrupoAreaMedicaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion = request.getGrupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacionInstance();
        Dominio grupoAsignacionInsGrupoAreaMedicapevcDominio = request.getGrupoAsignacionInsGrupoAreaMedicapevcDominioInstance();

        request.checkArguments();
        GrupoAsignacion instance = grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsGrupoAreaMedicaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsGrupoAreaMedicaRequest();
            relatedRequest.setDominioInsGrupoAreaMedicapthisDominioInstance(grupoAsignacionInsGrupoAreaMedicapevcDominio);
            relatedRequest.setDominioInsGrupoAreaMedicapevcGrupoAsignacionInstance(grupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacion);
            dominioSrv.insGrupoAreaMedicaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

        return response;
    }

    // Service: DelGrupoAreaMedica

    /*
     * Entry point for service DelGrupoAreaMedica
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delGrupoAreaMedica(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelGrupoAreaMedicaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.delGrupoAreaMedicaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelGrupoAreaMedica
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delGrupoAreaMedicaTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelGrupoAreaMedicaRequest request) throws ModelException {
        request.setGrupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion()));
        request.setGrupoAsignacionDelGrupoAreaMedicapevcDominioInstance(dominioSrv.getByOIDWithHV(request.getGrupoAsignacionDelGrupoAreaMedicapevcDominio()));
        return grupoAsignacionSrv.delGrupoAreaMedicaImplementation(request, true);
    }

    /*
     * Implements service DelGrupoAreaMedica
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delGrupoAreaMedicaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelGrupoAreaMedicaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion = request.getGrupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacionInstance();
        Dominio grupoAsignacionDelGrupoAreaMedicapevcDominio = request.getGrupoAsignacionDelGrupoAreaMedicapevcDominioInstance();

        request.checkArguments();
        GrupoAsignacion instance = grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelGrupoAreaMedicaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelGrupoAreaMedicaRequest();
            relatedRequest.setDominioDelGrupoAreaMedicapthisDominioInstance(grupoAsignacionDelGrupoAreaMedicapevcDominio);
            relatedRequest.setDominioDelGrupoAreaMedicapevcGrupoAsignacionInstance(grupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacion);
            dominioSrv.delGrupoAreaMedicaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

        return response;
    }

    // Service: InsGrupoUsuarios

    /*
     * Entry point for service InsGrupoUsuarios
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insGrupoUsuarios(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.InsGrupoUsuariosRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.insGrupoUsuariosTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsGrupoUsuarios
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insGrupoUsuariosTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.InsGrupoUsuariosRequest request) throws ModelException {
        request.setGrupoAsignacionInsGrupoUsuariospthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionInsGrupoUsuariospthisGrupoAsignacion()));
        request.setGrupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedadInstance(grupoAsignacionNovedadSrv.getByOIDWithHV(request.getGrupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad()));
        return grupoAsignacionSrv.insGrupoUsuariosImplementation(request, true);
    }

    /*
     * Implements service InsGrupoUsuarios
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insGrupoUsuariosImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.InsGrupoUsuariosRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion grupoAsignacionInsGrupoUsuariospthisGrupoAsignacion = request.getGrupoAsignacionInsGrupoUsuariospthisGrupoAsignacionInstance();
        GrupoAsignacionNovedad grupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad = request.getGrupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedadInstance();

        request.checkArguments();
        GrupoAsignacion instance = grupoAsignacionInsGrupoUsuariospthisGrupoAsignacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.InsGrupoUsuariosRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.InsGrupoUsuariosRequest();
            relatedRequest.setGrupoAsignacionNovedadInsGrupoUsuariospthisGrupoAsignacionNovedadInstance(grupoAsignacionInsGrupoUsuariospevcGrupoAsignacionNovedad);
            relatedRequest.setGrupoAsignacionNovedadInsGrupoUsuariospevcGrupoAsignacionInstance(grupoAsignacionInsGrupoUsuariospthisGrupoAsignacion);
            grupoAsignacionNovedadSrv.insGrupoUsuariosImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

        return response;
    }

    // Service: DelGrupoUsuarios

    /*
     * Entry point for service DelGrupoUsuarios
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delGrupoUsuarios(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelGrupoUsuariosRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.delGrupoUsuariosTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelGrupoUsuarios
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delGrupoUsuariosTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelGrupoUsuariosRequest request) throws ModelException {
        request.setGrupoAsignacionDelGrupoUsuariospthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionDelGrupoUsuariospthisGrupoAsignacion()));
        request.setGrupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedadInstance(grupoAsignacionNovedadSrv.getByOIDWithHV(request.getGrupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad()));
        return grupoAsignacionSrv.delGrupoUsuariosImplementation(request, true);
    }

    /*
     * Implements service DelGrupoUsuarios
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delGrupoUsuariosImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelGrupoUsuariosRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion grupoAsignacionDelGrupoUsuariospthisGrupoAsignacion = request.getGrupoAsignacionDelGrupoUsuariospthisGrupoAsignacionInstance();
        GrupoAsignacionNovedad grupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad = request.getGrupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedadInstance();

        request.checkArguments();
        GrupoAsignacion instance = grupoAsignacionDelGrupoUsuariospthisGrupoAsignacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.DelGrupoUsuariosRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacionnovedad.siu.DelGrupoUsuariosRequest();
            relatedRequest.setGrupoAsignacionNovedadDelGrupoUsuariospthisGrupoAsignacionNovedadInstance(grupoAsignacionDelGrupoUsuariospevcGrupoAsignacionNovedad);
            relatedRequest.setGrupoAsignacionNovedadDelGrupoUsuariospevcGrupoAsignacionInstance(grupoAsignacionDelGrupoUsuariospthisGrupoAsignacion);
            grupoAsignacionNovedadSrv.delGrupoUsuariosImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

        return response;
    }

    // Service: TELIMINAR

    /*
     * Entry point for service TELIMINAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tELIMINAR(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TELIMINARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.tELIMINARTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TELIMINAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tELIMINARTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TELIMINARRequest request) throws ModelException, SystemException {
        request.setGrupoAsignacionTELIMINARpthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionTELIMINARpthisGrupoAsignacion()));
        return grupoAsignacionSrv.tELIMINARImplementation(request);
    }

    /*
     * Implements service TELIMINAR
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tELIMINARImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TELIMINARRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion grupoAsignacionTELIMINARpthisGrupoAsignacion = request.getGrupoAsignacionTELIMINARpthisGrupoAsignacionInstance();

        request.checkArguments();

        tELIMINARPreconditions(request);
        GrupoAsignacion instance = grupoAsignacionTELIMINARpthisGrupoAsignacion;

        for(LogGrupoAsignacion step1Entity : repository.assocOperator002(grupoAsignacionTELIMINARpthisGrupoAsignacion)) {
            com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.EliminarRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.EliminarRequest();
            step1Request.setLogGrupoAsignacioneliminarpthisLogGrupoAsignacionInstance(step1Entity);
            logGrupoAsignacionSrv.eliminarImplementation(step1Request);
        }

        com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.EliminarRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.EliminarRequest();
        step2Request.setGrupoAsignacioneliminarpthisGrupoAsignacionInstance(grupoAsignacionTELIMINARpthisGrupoAsignacion);
        grupoAsignacionSrv.eliminarImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

        return response;
    }

    /*
     * Checks service TELIMINAR preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse tELIMINARCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TELIMINARRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setGrupoAsignacionTELIMINARpthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionTELIMINARpthisGrupoAsignacion()));
        try {
            if("Clas_1586850168832947Pre_1".equals(request.getPreconditionId()))
                checkClas1586850168832947Pre1(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tELIMINARPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TELIMINARRequest request) 
            throws ModelException {
        checkClas1586850168832947Pre1(request);
    }

    private void checkClas1586850168832947Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TELIMINARRequest request) 
            throws ModelException {
        // COUNT( DominioAreaMedica ) = 0 AND COUNT( DominioAsesores ) = 0

        GrupoAsignacion grupoAsignacionTELIMINARpthisGrupoAsignacion = request.getGrupoAsignacionTELIMINARpthisGrupoAsignacionInstance();
        try {
            boolean preconditionHolds = (repository.assocOperator000(grupoAsignacionTELIMINARpthisGrupoAsignacion)).equals(Long.valueOf(0)) && (repository.assocOperator001(grupoAsignacionTELIMINARpthisGrupoAsignacion)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("No puede eliminarse el grupo por estar asignado a un Dominio");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    // Service: TCREAR

    /*
     * Entry point for service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tCREAR(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.tCREARTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tCREARTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TCREARRequest request) throws ModelException {
        return grupoAsignacionSrv.tCREARImplementation(request);
    }

    /*
     * Implements service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tCREARImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TCREARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long grupoAsignacionTCREARptpatrTipo = request.getGrupoAsignacionTCREARptpatrTipo();
        String grupoAsignacionTCREARptpatrNombre = request.getGrupoAsignacionTCREARptpatrNombre();
        String grupoAsignacionTCREARptpatrDescripcion = request.getGrupoAsignacionTCREARptpatrDescripcion();

        request.checkArguments();
        GrupoAsignacion instance;

        com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.CrearRequest();
        step1Request.setGrupoAsignacioncrearpatrTipo(grupoAsignacionTCREARptpatrTipo);
        step1Request.setGrupoAsignacioncrearpatrNombre(grupoAsignacionTCREARptpatrNombre);
        step1Request.setGrupoAsignacioncrearpatrDescripcion(grupoAsignacionTCREARptpatrDescripcion);
        instance = (GrupoAsignacion) grupoAsignacionSrv.crearImplementation(step1Request).getInstance();

        com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest();
        step2Request.setLogGrupoAsignacionTCREARptpagrGrupoAsignacionInstance(instance);
        step2Request.setLogGrupoAsignacionTCREARptpatrAccion("Modificacion");
        step2Request.setLogGrupoAsignacionTCREARptpatrValor(instance.getGrupoAsignacionNombre() + " " + instance.getGrupoAsignacionDescripcion());
        logGrupoAsignacionSrv.tCREARImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

        return response;
    }

    // Service: TMODIFICAR

    /*
     * Entry point for service TMODIFICAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tMODIFICAR(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TMODIFICARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.tMODIFICARTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TMODIFICAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tMODIFICARTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TMODIFICARRequest request) throws ModelException {
        request.setGrupoAsignacionTMODIFICARpthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionTMODIFICARpthisGrupoAsignacion()));
        return grupoAsignacionSrv.tMODIFICARImplementation(request);
    }

    /*
     * Implements service TMODIFICAR
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tMODIFICARImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TMODIFICARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String grupoAsignacionTMODIFICARptpNombre = request.getGrupoAsignacionTMODIFICARptpNombre();
        String grupoAsignacionTMODIFICARptpDescripcion = request.getGrupoAsignacionTMODIFICARptpDescripcion();
        GrupoAsignacion grupoAsignacionTMODIFICARpthisGrupoAsignacion = request.getGrupoAsignacionTMODIFICARpthisGrupoAsignacionInstance();

        request.checkArguments();
        GrupoAsignacion instance = grupoAsignacionTMODIFICARpthisGrupoAsignacion;

        com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.ModificarRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.ModificarRequest();
        step1Request.setGrupoAsignacionmodificarpthisGrupoAsignacionInstance(grupoAsignacionTMODIFICARpthisGrupoAsignacion);
        step1Request.setGrupoAsignacionmodificarpNombre(grupoAsignacionTMODIFICARptpNombre);
        step1Request.setGrupoAsignacionmodificarpDescripcion(grupoAsignacionTMODIFICARptpDescripcion);
        grupoAsignacionSrv.modificarImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest();
        step2Request.setLogGrupoAsignacionTCREARptpagrGrupoAsignacionInstance(grupoAsignacionTMODIFICARpthisGrupoAsignacion);
        step2Request.setLogGrupoAsignacionTCREARptpatrAccion("Modificacion");
        step2Request.setLogGrupoAsignacionTCREARptpatrValor(grupoAsignacionTMODIFICARpthisGrupoAsignacion.getGrupoAsignacionNombre() + " " + grupoAsignacionTMODIFICARpthisGrupoAsignacion.getGrupoAsignacionDescripcion());
        logGrupoAsignacionSrv.tCREARImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

        return response;
    }

    // Service: TADDUSUARIO

    /*
     * Entry point for service TADDUSUARIO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tADDUSUARIO(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TADDUSUARIORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.tADDUSUARIOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TADDUSUARIO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tADDUSUARIOTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TADDUSUARIORequest request) throws ModelException {
        request.setGrupoAsignacionTADDUSUARIOpthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionTADDUSUARIOpthisGrupoAsignacion()));
        request.setGrupoAsignacionTADDUSUARIOptFuncionarioInstance(funcionarioSrv.getByOIDWithHV(request.getGrupoAsignacionTADDUSUARIOptFuncionario()));
        request.setGrupoAsignacionTADDUSUARIOptAreaMedicaInstance(areaMedicaSrv.getByOIDWithHV(request.getGrupoAsignacionTADDUSUARIOptAreaMedica()));
        return grupoAsignacionSrv.tADDUSUARIOImplementation(request);
    }

    /*
     * Implements service TADDUSUARIO
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tADDUSUARIOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TADDUSUARIORequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Funcionario grupoAsignacionTADDUSUARIOptFuncionario = request.getGrupoAsignacionTADDUSUARIOptFuncionarioInstance();
        AreaMedica grupoAsignacionTADDUSUARIOptAreaMedica = request.getGrupoAsignacionTADDUSUARIOptAreaMedicaInstance();
        GrupoAsignacion grupoAsignacionTADDUSUARIOpthisGrupoAsignacion = request.getGrupoAsignacionTADDUSUARIOpthisGrupoAsignacionInstance();

        request.checkArguments();

        tADDUSUARIOPreconditions(request);
        GrupoAsignacion instance = grupoAsignacionTADDUSUARIOpthisGrupoAsignacion;

        /* Action 1: IF (ptFuncionario <> NULL) */
        boolean ifCondition1 = (grupoAsignacionTADDUSUARIOptFuncionario != null && !grupoAsignacionTADDUSUARIOptFuncionario.isNull());
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.AddFuncionarioGrupoRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.AddFuncionarioGrupoRequest();
            step10Request.setGrupoAsignacionAddFuncionarioGrupopthisGrupoAsignacionInstance(grupoAsignacionTADDUSUARIOpthisGrupoAsignacion);
            step10Request.setGrupoAsignacionAddFuncionarioGrupopevcFuncionarioInstance(grupoAsignacionTADDUSUARIOptFuncionario);
            grupoAsignacionSrv.addFuncionarioGrupoImplementation(step10Request, true);
            /* Action 11: IF (ptFuncionario.UsuarioLDAP <> NULL) */
            boolean ifCondition11 = grupoAsignacionTADDUSUARIOptFuncionario.getFuncionarioUsuarioLDAP() != null;
            if (ifCondition11) {
                com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest step110Request = new com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest();
                step110Request.setLogGrupoAsignacionTCREARptpagrGrupoAsignacionInstance(grupoAsignacionTADDUSUARIOpthisGrupoAsignacion);
                step110Request.setLogGrupoAsignacionTCREARptpatrAccion("Agregar usuario");
                step110Request.setLogGrupoAsignacionTCREARptpatrValor(grupoAsignacionTADDUSUARIOptFuncionario.getFuncionarioUsuarioLDAP());
                logGrupoAsignacionSrv.tCREARImplementation(step110Request);
            } else {
                com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest step111Request = new com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest();
                step111Request.setLogGrupoAsignacionTCREARptpagrGrupoAsignacionInstance(grupoAsignacionTADDUSUARIOpthisGrupoAsignacion);
                step111Request.setLogGrupoAsignacionTCREARptpatrAccion("Agregar usuario");
                step111Request.setLogGrupoAsignacionTCREARptpatrValor(grupoAsignacionTADDUSUARIOptFuncionario.getFuncionarioUsuario());
                logGrupoAsignacionSrv.tCREARImplementation(step111Request);
            }
        } else {
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.AddFuncionarioGrupoRequest step12Request = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.AddFuncionarioGrupoRequest();
            step12Request.setGrupoAsignacionAddFuncionarioGrupopthisGrupoAsignacionInstance(grupoAsignacionTADDUSUARIOpthisGrupoAsignacion);
            step12Request.setGrupoAsignacionAddFuncionarioGrupopevcFuncionarioInstance(grupoAsignacionTADDUSUARIOptAreaMedica.getFuncionarioFacet());
            grupoAsignacionSrv.addFuncionarioGrupoImplementation(step12Request, true);
            /* Action 13: IF (ptAreaMedica.UsuarioLDAP <> NULL) */
            boolean ifCondition13 = grupoAsignacionTADDUSUARIOptAreaMedica.getFuncionarioUsuarioLDAP() != null;
            if (ifCondition13) {
                com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest step130Request = new com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest();
                step130Request.setLogGrupoAsignacionTCREARptpagrGrupoAsignacionInstance(grupoAsignacionTADDUSUARIOpthisGrupoAsignacion);
                step130Request.setLogGrupoAsignacionTCREARptpatrAccion("Agregar usuario");
                step130Request.setLogGrupoAsignacionTCREARptpatrValor(grupoAsignacionTADDUSUARIOptAreaMedica.getFuncionarioUsuarioLDAP());
                logGrupoAsignacionSrv.tCREARImplementation(step130Request);
            } else {
                com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest step131Request = new com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest();
                step131Request.setLogGrupoAsignacionTCREARptpagrGrupoAsignacionInstance(grupoAsignacionTADDUSUARIOpthisGrupoAsignacion);
                step131Request.setLogGrupoAsignacionTCREARptpatrAccion("Agregar usuario");
                step131Request.setLogGrupoAsignacionTCREARptpatrValor(grupoAsignacionTADDUSUARIOptAreaMedica.getFuncionarioUsuario());
                logGrupoAsignacionSrv.tCREARImplementation(step131Request);
            }
        }

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

        return response;
    }

    /*
     * Checks service TADDUSUARIO preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse tADDUSUARIOCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TADDUSUARIORequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setGrupoAsignacionTADDUSUARIOpthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionTADDUSUARIOpthisGrupoAsignacion()));
        request.setGrupoAsignacionTADDUSUARIOptFuncionarioInstance(funcionarioSrv.getByOIDWithHV(request.getGrupoAsignacionTADDUSUARIOptFuncionario()));
        request.setGrupoAsignacionTADDUSUARIOptAreaMedicaInstance(areaMedicaSrv.getByOIDWithHV(request.getGrupoAsignacionTADDUSUARIOptAreaMedica()));
        try {
            if("Clas_1586850168832947Pre_2".equals(request.getPreconditionId()))
                checkClas1586850168832947Pre2(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tADDUSUARIOPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TADDUSUARIORequest request) 
            throws ModelException {
        checkClas1586850168832947Pre2(request);
    }

    private void checkClas1586850168832947Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TADDUSUARIORequest request) 
            throws ModelException {
        // ptFuncionario <> NULL OR ptAreaMedica <> NULL

        Funcionario grupoAsignacionTADDUSUARIOptFuncionario = request.getGrupoAsignacionTADDUSUARIOptFuncionarioInstance();
        AreaMedica grupoAsignacionTADDUSUARIOptAreaMedica = request.getGrupoAsignacionTADDUSUARIOptAreaMedicaInstance();
        boolean preconditionHolds = (grupoAsignacionTADDUSUARIOptFuncionario != null && !grupoAsignacionTADDUSUARIOptFuncionario.isNull()) || (grupoAsignacionTADDUSUARIOptAreaMedica != null && !grupoAsignacionTADDUSUARIOptAreaMedica.isNull());
        if (!preconditionHolds) throw new PreconditionException("Debe indicar el usuario a añadir");
    }

    // Service: TDELUSUARIO

    /*
     * Entry point for service TDELUSUARIO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tDELUSUARIO(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TDELUSUARIORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = grupoAsignacionSrv.tDELUSUARIOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TDELUSUARIO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tDELUSUARIOTxn(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TDELUSUARIORequest request) throws ModelException {
        request.setGrupoAsignacionTDELUSUARIOpthisGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getGrupoAsignacionTDELUSUARIOpthisGrupoAsignacion()));
        request.setGrupoAsignacionTDELUSUARIOptFuncionarioInstance(funcionarioSrv.getByOIDWithHV(request.getGrupoAsignacionTDELUSUARIOptFuncionario()));
        return grupoAsignacionSrv.tDELUSUARIOImplementation(request);
    }

    /*
     * Implements service TDELUSUARIO
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tDELUSUARIOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.TDELUSUARIORequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Funcionario grupoAsignacionTDELUSUARIOptFuncionario = request.getGrupoAsignacionTDELUSUARIOptFuncionarioInstance();
        GrupoAsignacion grupoAsignacionTDELUSUARIOpthisGrupoAsignacion = request.getGrupoAsignacionTDELUSUARIOpthisGrupoAsignacionInstance();

        request.checkArguments();
        GrupoAsignacion instance = grupoAsignacionTDELUSUARIOpthisGrupoAsignacion;

        com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelFuncionarioGrupoRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelFuncionarioGrupoRequest();
        step1Request.setGrupoAsignacionDelFuncionarioGrupopthisGrupoAsignacionInstance(grupoAsignacionTDELUSUARIOpthisGrupoAsignacion);
        step1Request.setGrupoAsignacionDelFuncionarioGrupopevcFuncionarioInstance(grupoAsignacionTDELUSUARIOptFuncionario);
        grupoAsignacionSrv.delFuncionarioGrupoImplementation(step1Request, true);

        /* Action 2: IF (ptFuncionario.UsuarioLDAP <> NULL) */
        boolean ifCondition2 = grupoAsignacionTDELUSUARIOptFuncionario.getFuncionarioUsuarioLDAP() != null;
        if (ifCondition2) {
            com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest step20Request = new com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest();
            step20Request.setLogGrupoAsignacionTCREARptpagrGrupoAsignacionInstance(grupoAsignacionTDELUSUARIOpthisGrupoAsignacion);
            step20Request.setLogGrupoAsignacionTCREARptpatrAccion("Eliminar usuario");
            step20Request.setLogGrupoAsignacionTCREARptpatrValor(grupoAsignacionTDELUSUARIOptFuncionario.getFuncionarioUsuarioLDAP());
            logGrupoAsignacionSrv.tCREARImplementation(step20Request);
        } else {
            com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest step21Request = new com.keralty.aeconfiguraciones.viewmodel.loggrupoasignacion.siu.TCREARRequest();
            step21Request.setLogGrupoAsignacionTCREARptpagrGrupoAsignacionInstance(grupoAsignacionTDELUSUARIOpthisGrupoAsignacion);
            step21Request.setLogGrupoAsignacionTCREARptpatrAccion("Eliminar usuario");
            step21Request.setLogGrupoAsignacionTCREARptpatrValor(grupoAsignacionTDELUSUARIOptFuncionario.getFuncionarioUsuario());
            logGrupoAsignacionSrv.tCREARImplementation(step21Request);
        }

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICGrupoAsignacion());

        return response;
    }


    private void suppInfo4ICGrupoAsignacion(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(GrupoAsignacionConstants.ATTR_NAME_GRUPOASIGNACIONNOMBRE, Constants.Type.STRING.getTypeName()));
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
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(GrupoAsignacionConstants.CLASS_NAME, GrupoAsignacionConstants.ATTR_FIELD_GRUPOASIGNACIONID, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildGrupoAsignacionRelatedInstance(relatedInstanceJsonOid);
        }
        if (GrupoAsignacionConstants.ROLE_NAME_DOMINIOASESORES.equalsIgnoreCase(relatedInstancePathFirstItem) || GrupoAsignacionConstants.ROLE_NAME_DOMINIOAREAMEDICA.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildGrupoAsignacionRelatedInstance4Dominio(relatedInstanceJsonOid, remainingPath);
        }
        if (GrupoAsignacionConstants.ROLE_NAME_LOGGRUPOASIGNACION.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildGrupoAsignacionRelatedInstance4LogGrupoAsignacion(relatedInstanceJsonOid, remainingPath);
        }
        if (GrupoAsignacionConstants.ROLE_NAME_DOMINIOCAUSALUSUARIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildGrupoAsignacionRelatedInstance4GrupoAsignacionNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (GrupoAsignacionConstants.ROLE_NAME_FUNCIONARIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildGrupoAsignacionRelatedInstance4Funcionario(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildGrupoAsignacionRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new GrupoAsignacion(mapper.readValue(relatedInstanceJsonOid, GrupoAsignacionOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildGrupoAsignacionRelatedInstance4Dominio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Dominio(mapper.readValue(relatedInstanceJsonOid, DominioOid.class)): Dominio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildGrupoAsignacionRelatedInstance4LogGrupoAsignacion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new LogGrupoAsignacion(mapper.readValue(relatedInstanceJsonOid, LogGrupoAsignacionOid.class)): LogGrupoAsignacion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildGrupoAsignacionRelatedInstance4GrupoAsignacionNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new GrupoAsignacionNovedad(mapper.readValue(relatedInstanceJsonOid, GrupoAsignacionNovedadOid.class)): GrupoAsignacionNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildGrupoAsignacionRelatedInstance4Funcionario(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Funcionario(mapper.readValue(relatedInstanceJsonOid, FuncionarioOid.class)): Funcionario.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            GrupoAsignacionOid oid = new GrupoAsignacionOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(GrupoAsignacionConstants.ATTR_FIELD_GRUPOASIGNACIONID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getGrupoAsignacionId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(GrupoAsignacionService.class).error(e.getMessage());
        }
    }

}
