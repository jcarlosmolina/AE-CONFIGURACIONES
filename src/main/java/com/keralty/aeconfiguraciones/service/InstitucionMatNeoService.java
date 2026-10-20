package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.InstitucionMatNeoConstants;
import com.keralty.aeconfiguraciones.global.UsuarioConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.AnexoMat;
import com.keralty.aeconfiguraciones.persistence.AnexoNeo;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.AnexoMatOid;
import com.keralty.aeconfiguraciones.persistence.oid.AnexoNeoOid;
import com.keralty.aeconfiguraciones.persistence.oid.InstitucionMatNeoOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoIdentificacionOid;
import com.keralty.aeconfiguraciones.persistence.oid.UsuarioOid;
import com.keralty.aeconfiguraciones.persistence.Tarifa;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import com.keralty.aeconfiguraciones.repository.InstitucionMatNeoJpaRepository;
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
public class InstitucionMatNeoService extends AbstractService {

    InstitucionMatNeoJpaRepository repository;

    InstitucionMatNeoService institucionMatNeoSrv;

    TarifaService tarifaSrv;

    TipoIdentificacionService tipoIdentificacionSrv;

    MunicipioService municipioSrv;

    AnexoMatService anexoMatSrv;

    AnexoNeoService anexoNeoSrv;

    UsuarioService usuarioSrv;

    @Inject
    public InstitucionMatNeoService(
            InstitucionMatNeoService institucionMatNeoSrv,
            TarifaService tarifaSrv,
            TipoIdentificacionService tipoIdentificacionSrv,
            MunicipioService municipioSrv,
            AnexoMatService anexoMatSrv,
            AnexoNeoService anexoNeoSrv,
            UsuarioService usuarioSrv,
            InstitucionMatNeoJpaRepository repository) {
        this.institucionMatNeoSrv = institucionMatNeoSrv;
        this.tarifaSrv = tarifaSrv;
        this.tipoIdentificacionSrv = tipoIdentificacionSrv;
        this.municipioSrv = municipioSrv;
        this.anexoMatSrv = anexoMatSrv;
        this.anexoNeoSrv = anexoNeoSrv;
        this.usuarioSrv = usuarioSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, InstitucionMatNeoConstants.CLASS_NAME);
    }

    private List<InstitucionMatNeo> solveInstitucionMatNeoTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, InstitucionMatNeoConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveInstitucionMatNeoTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, InstitucionMatNeoConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class InstitucionMatNeo
     *
     * @return Population of class InstitucionMatNeo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<InstitucionMatNeo> get() {
        List<InstitucionMatNeo> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class InstitucionMatNeo
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class InstitucionMatNeo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<InstitucionMatNeo> instances = get(queryRequest);
        if(InstitucionMatNeoConstants.DSDSINSTITUCIONMATNEO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSInstitucionMatNeo())).collect(Collectors.toList());
        }
        if(InstitucionMatNeoConstants.DSICINSTITUCIONMATNEO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICInstitucionMatNeo())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class InstitucionMatNeo that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class InstitucionMatNeo matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<InstitucionMatNeo> get(QueryRequest queryRequest) {
        return solveInstitucionMatNeoTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveInstitucionMatNeoTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveInstitucionMatNeoTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveInstitucionMatNeoTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveInstitucionMatNeoTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of InstitucionMatNeo by ID
     *
     * @param institucionMatNeoCodigo Identification field institucionMatNeoCodigo
     *
     * @return Instance of InstitucionMatNeo
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public InstitucionMatNeo getById(String institucionMatNeoCodigo) {
        return getByOID(new InstitucionMatNeoOid(institucionMatNeoCodigo));
    }
    
    /*
     * Returns an instance of InstitucionMatNeo by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of InstitucionMatNeo
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public InstitucionMatNeo getByOID(InstitucionMatNeoOid oid) {
        Optional<InstitucionMatNeo> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of InstitucionMatNeo by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of InstitucionMatNeo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstitucionMatNeo getByOIDWithHV(InstitucionMatNeoOid oid) {
        Optional<InstitucionMatNeo> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<InstitucionMatNeo> getInstanceByOid(InstitucionMatNeoOid oid) {
        return oid != null && oid.getInstitucionMatNeoCodigo() != null
               ? repository.findById(oid.getInstitucionMatNeoCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of InstitucionMatNeo by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of InstitucionMatNeo
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public InstitucionMatNeo getByJsonOid(String jsonOid) {
        InstitucionMatNeoOid oid = new InstitucionMatNeoOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of InstitucionMatNeo related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of InstitucionMatNeo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<InstitucionMatNeo> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveInstitucionMatNeoTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of InstitucionMatNeo related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of InstitucionMatNeo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<InstitucionMatNeo> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of InstitucionMatNeo
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of InstitucionMatNeo
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getInstitucionMatNeoDynamic(InstitucionMatNeoOid oid, String displaySetItems) {
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
    public String getSuppInfo(InstitucionMatNeoOid oid, String dsName) {
        InstitucionMatNeo instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(InstitucionMatNeo instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (InstitucionMatNeoConstants.DSICINSTITUCIONMATNEO.equals(dsName))
            return instance.buildICInstitucionMatNeo();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(InstitucionMatNeo instance, String dsName) {
        if (InstitucionMatNeoConstants.DSDSINSTITUCIONMATNEO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSInstitucionMatNeo());
        if (InstitucionMatNeoConstants.DSICINSTITUCIONMATNEO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICInstitucionMatNeo());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyNavigationalFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if ("Usuario_TCREAROMODANEXOMAT_pt_InstitucionMat_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1432633737216826FiltNav14(filterRequest, sql);
        if ("Usuario_TCREAROMODANEXONEO_pt_InstitucionNeo_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1432633737216826FiltNav15(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_InstitucionMatNeo".equals(filterRequest.getName()))
            suppInfo4ICInstitucionMatNeo(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void clas1432633737216826FiltNav14(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Usuario usuarioTCREAROMODANEXOMATpthisUsuario = filterRequest.hasFilterVariable(UsuarioConstants.ARG_NAME_TCREAROMODANEXOMAT_USUARIOTCREAROMODANEXOMATPTHISUSUARIO) ? usuarioSrv.getByOID(new UsuarioOid().fromJsonString(toJson(filterRequest.getFilterVariable(UsuarioConstants.ARG_NAME_TCREAROMODANEXOMAT_USUARIOTCREAROMODANEXOMATPTHISUSUARIO)))) : null;

        // ParaMaternidad = true
        // '=' Comparison
        SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
        comparison1.setOperator("=");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(InstitucionMatNeoConstants.ATTR_NAME_INSTITUCIONMATNEOPARAMATERNIDAD, Constants.Type.BOOL.getTypeName());
        comparison1.setFirstOperand(operand112);
        
        // Operand 2
        comparison1.setSecondOperand(new SQLWhereOperandLiteral(Boolean.TRUE, Constants.Type.BOOL.getTypeName()));
        conjunction.addOperand(comparison1);


        // FechaFinal >= pthisUsuario.Solicitud.Aesolicitudes.Fechaasignacion
        if (usuarioTCREAROMODANEXOMATpthisUsuario != null) {
            // '>=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator(">=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(InstitucionMatNeoConstants.ATTR_NAME_INSTITUCIONMATNEOFECHAFINAL, Constants.Type.DATE.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(usuarioTCREAROMODANEXOMATpthisUsuario.getSolicitud().getAesolicitudes().getAesolicitudesFechaasignacion(), Constants.Type.DATE.getTypeName()));
            conjunction.addOperand(comparison2);

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1432633737216826FiltNav15(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Usuario usuarioTCREAROMODANEXONEOpthisUsuario = filterRequest.hasFilterVariable(UsuarioConstants.ARG_NAME_TCREAROMODANEXONEO_USUARIOTCREAROMODANEXONEOPTHISUSUARIO) ? usuarioSrv.getByOID(new UsuarioOid().fromJsonString(toJson(filterRequest.getFilterVariable(UsuarioConstants.ARG_NAME_TCREAROMODANEXONEO_USUARIOTCREAROMODANEXONEOPTHISUSUARIO)))) : null;

        // ParaNeonatal = true
        // '=' Comparison
        SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
        comparison1.setOperator("=");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(InstitucionMatNeoConstants.ATTR_NAME_INSTITUCIONMATNEOPARANEONATAL, Constants.Type.BOOL.getTypeName());
        comparison1.setFirstOperand(operand112);
        
        // Operand 2
        comparison1.setSecondOperand(new SQLWhereOperandLiteral(Boolean.TRUE, Constants.Type.BOOL.getTypeName()));
        conjunction.addOperand(comparison1);


        // FechaFinal >= pthisUsuario.Solicitud.Aesolicitudes.Fechaasignacion
        if (usuarioTCREAROMODANEXONEOpthisUsuario != null) {
            // '>=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator(">=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(InstitucionMatNeoConstants.ATTR_NAME_INSTITUCIONMATNEOFECHAFINAL, Constants.Type.DATE.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(usuarioTCREAROMODANEXONEOpthisUsuario.getSolicitud().getAesolicitudes().getAesolicitudesFechaasignacion(), Constants.Type.DATE.getTypeName()));
            conjunction.addOperand(comparison2);

        }        
        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUInstitucionMatNeo
     *
     * @param queryRequest Query request
     * @return List of viewmodels for InstitucionMatNeo using displayset DSInstitucionMatNeo that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.ds.DSInstitucionMatNeo> piuPIUInstitucionMatNeo(QueryRequest queryRequest) {
        return solveInstitucionMatNeoTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.ds.DSInstitucionMatNeo::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUInstitucionMatNeo
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUInstitucionMatNeo
     */
    public int getCount4PIUInstitucionMatNeo(QueryRequest queryRequest) {
        return solveInstitucionMatNeoTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = institucionMatNeoSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CrearRequest request) throws ModelException {
        request.setInstitucionMatNeocrearpagrTarifaInstance(tarifaSrv.getByOIDWithHV(request.getInstitucionMatNeocrearpagrTarifa()));
        request.setInstitucionMatNeocrearpagrTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(new TipoIdentificacionOid().fromJsonString(request.getInstitucionMatNeocrearpagrTipoIdentificacion())));
        request.setInstitucionMatNeocrearpagrMunicipioInstance(municipioSrv.getByOIDWithHV(request.getInstitucionMatNeocrearpagrMunicipio()));
        return institucionMatNeoSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Tarifa institucionMatNeocrearpagrTarifa = request.getInstitucionMatNeocrearpagrTarifaInstance();
        TipoIdentificacion institucionMatNeocrearpagrTipoIdentificacion = request.getInstitucionMatNeocrearpagrTipoIdentificacionInstance();
        Municipio institucionMatNeocrearpagrMunicipio = request.getInstitucionMatNeocrearpagrMunicipioInstance();
        String institucionMatNeocrearpatrNumIdentificacion = request.getInstitucionMatNeocrearpatrNumIdentificacion();
        String institucionMatNeocrearpatrNombre = request.getInstitucionMatNeocrearpatrNombre();
        Boolean institucionMatNeocrearpatrParaMaternidad = request.getInstitucionMatNeocrearpatrParaMaternidad();
        Boolean institucionMatNeocrearpatrParaNeonatal = request.getInstitucionMatNeocrearpatrParaNeonatal();
        Date institucionMatNeocrearpatrFechaInicial = request.getInstitucionMatNeocrearpatrFechaInicial();
        Date institucionMatNeocrearpatrFechaFinal = request.getInstitucionMatNeocrearpatrFechaFinal();
        String institucionMatNeocrearpatrCodigo = request.getInstitucionMatNeocrearpatrCodigo();

        request.checkArguments();
        InstitucionMatNeo instance = new InstitucionMatNeo();
        instance.add2Tarifa(institucionMatNeocrearpagrTarifa);

        instance.add2TipoIdentificacion(institucionMatNeocrearpagrTipoIdentificacion);

        instance.add2Municipio(institucionMatNeocrearpagrMunicipio);

        instance.setInstitucionMatNeoNumIdentificacion(institucionMatNeocrearpatrNumIdentificacion);
        instance.setInstitucionMatNeoNombre(institucionMatNeocrearpatrNombre);
        instance.setInstitucionMatNeoParaMaternidad(institucionMatNeocrearpatrParaMaternidad);
        instance.setInstitucionMatNeoParaNeonatal(institucionMatNeocrearpatrParaNeonatal);
        instance.setInstitucionMatNeoFechaInicial(institucionMatNeocrearpatrFechaInicial);
        instance.setInstitucionMatNeoFechaFinal(institucionMatNeocrearpatrFechaFinal);
        instance.setInstitucionMatNeoCodigo(institucionMatNeocrearpatrCodigo);
        if (institucionMatNeoSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(InstitucionMatNeoConstants.CLASS_ALIAS);
        }

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICInstitucionMatNeo());

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
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = institucionMatNeoSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.EliminarRequest request) throws ModelException {
        request.setInstitucionMatNeoeliminarpthisInstitucionMatNeoInstance(institucionMatNeoSrv.getByOIDWithHV(request.getInstitucionMatNeoeliminarpthisInstitucionMatNeo()));
        return institucionMatNeoSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        InstitucionMatNeo institucionMatNeoeliminarpthisInstitucionMatNeo = request.getInstitucionMatNeoeliminarpthisInstitucionMatNeoInstance();

        request.checkArguments();

        eliminarPreconditions(request);
        InstitucionMatNeo instance = institucionMatNeoeliminarpthisInstitucionMatNeo;
        if(institucionMatNeoeliminarpthisInstitucionMatNeo.getAnexoMat().size() > 0) {
            throw new MinCardinalityException(InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ROLE_ALIAS_ANEXOMAT);
        }
        if(institucionMatNeoeliminarpthisInstitucionMatNeo.getAnexoNeo().size() > 0) {
            throw new MinCardinalityException(InstitucionMatNeoConstants.CLASS_ALIAS, InstitucionMatNeoConstants.ROLE_ALIAS_ANEXONEO);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICInstitucionMatNeo());

        return response;
    }

    /*
     * Checks service eliminar preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse eliminarCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setInstitucionMatNeoeliminarpthisInstitucionMatNeoInstance(institucionMatNeoSrv.getByOIDWithHV(request.getInstitucionMatNeoeliminarpthisInstitucionMatNeo()));
        try {
            if("Clas_1463993499648846Pre_1".equals(request.getPreconditionId()))
                checkClas1463993499648846Pre1(request);
            if("Clas_1463993499648846Pre_2".equals(request.getPreconditionId()))
                checkClas1463993499648846Pre2(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void eliminarPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.EliminarRequest request) 
            throws ModelException {
        checkClas1463993499648846Pre1(request);
        checkClas1463993499648846Pre2(request);
    }

    private void checkClas1463993499648846Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.EliminarRequest request) 
            throws ModelException {
        //  COUNT(AnexoMat) = 0

        InstitucionMatNeo institucionMatNeoeliminarpthisInstitucionMatNeo = request.getInstitucionMatNeoeliminarpthisInstitucionMatNeoInstance();
        try {
            boolean preconditionHolds = (repository.assocOperator000(institucionMatNeoeliminarpthisInstitucionMatNeo)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Existen Anexos para maternidad relacionados con esta institución. No es posible eliminar la institución");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    private void checkClas1463993499648846Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.EliminarRequest request) 
            throws ModelException {
        //  COUNT(AnexoNeo) = 0

        InstitucionMatNeo institucionMatNeoeliminarpthisInstitucionMatNeo = request.getInstitucionMatNeoeliminarpthisInstitucionMatNeoInstance();
        try {
            boolean preconditionHolds = (repository.assocOperator001(institucionMatNeoeliminarpthisInstitucionMatNeo)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Existen Anexos para neonatal relacionados con esta institución. No es posible eliminar la institución");
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
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = institucionMatNeoSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.ModificarRequest request) throws ModelException {
        request.setInstitucionMatNeomodificarpthisInstitucionMatNeoInstance(institucionMatNeoSrv.getByOIDWithHV(request.getInstitucionMatNeomodificarpthisInstitucionMatNeo()));
        return institucionMatNeoSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        InstitucionMatNeo institucionMatNeomodificarpthisInstitucionMatNeo = request.getInstitucionMatNeomodificarpthisInstitucionMatNeoInstance();
        String institucionMatNeomodificarpNumIdentificacion = request.getInstitucionMatNeomodificarpNumIdentificacion();
        String institucionMatNeomodificarpNombre = request.getInstitucionMatNeomodificarpNombre();
        Boolean institucionMatNeomodificarpParaMaternidad = request.getInstitucionMatNeomodificarpParaMaternidad();
        Boolean institucionMatNeomodificarpParaNeonatal = request.getInstitucionMatNeomodificarpParaNeonatal();
        Date institucionMatNeomodificarpFechaInicial = request.getInstitucionMatNeomodificarpFechaInicial();
        Date institucionMatNeomodificarpFechaFinal = request.getInstitucionMatNeomodificarpFechaFinal();

        request.checkArguments();
        InstitucionMatNeo instance = institucionMatNeomodificarpthisInstitucionMatNeo;
        instance.setInstitucionMatNeoNumIdentificacion(institucionMatNeomodificarpNumIdentificacion);
        instance.setInstitucionMatNeoNombre(institucionMatNeomodificarpNombre);
        instance.setInstitucionMatNeoParaMaternidad(institucionMatNeomodificarpParaMaternidad);
        instance.setInstitucionMatNeoParaNeonatal(institucionMatNeomodificarpParaNeonatal);
        instance.setInstitucionMatNeoFechaInicial(institucionMatNeomodificarpFechaInicial);
        instance.setInstitucionMatNeoFechaFinal(institucionMatNeomodificarpFechaFinal);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICInstitucionMatNeo());

        return response;
    }

    // Service: CambiarInsInstitucionMaternal

    /*
     * Entry point for service CambiarInsInstitucionMaternal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambiarInsInstitucionMaternal(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarInsInstitucionMaternalRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = institucionMatNeoSrv.cambiarInsInstitucionMaternalTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambiarInsInstitucionMaternal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambiarInsInstitucionMaternalTxn(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarInsInstitucionMaternalRequest request) throws ModelException {
        request.setInstitucionMatNeoCambiarInsInstitucionMaternalpthisInstitucionMatNeoInstance(institucionMatNeoSrv.getByOIDWithHV(request.getInstitucionMatNeoCambiarInsInstitucionMaternalpthisInstitucionMatNeo()));
        request.setInstitucionMatNeoCambiarInsInstitucionMaternalpevcAnexoMatInstance(anexoMatSrv.getByOIDWithHV(request.getInstitucionMatNeoCambiarInsInstitucionMaternalpevcAnexoMat()));
        return institucionMatNeoSrv.cambiarInsInstitucionMaternalImplementation(request, true);
    }

    /*
     * Implements service CambiarInsInstitucionMaternal
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambiarInsInstitucionMaternalImplementation(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarInsInstitucionMaternalRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        InstitucionMatNeo institucionMatNeoCambiarInsInstitucionMaternalpthisInstitucionMatNeo = request.getInstitucionMatNeoCambiarInsInstitucionMaternalpthisInstitucionMatNeoInstance();
        AnexoMat institucionMatNeoCambiarInsInstitucionMaternalpevcAnexoMat = request.getInstitucionMatNeoCambiarInsInstitucionMaternalpevcAnexoMatInstance();

        request.checkArguments();
        InstitucionMatNeo instance = institucionMatNeoCambiarInsInstitucionMaternalpthisInstitucionMatNeo;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.anexomat.siu.CambiarInsInstitucionMaternalRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.anexomat.siu.CambiarInsInstitucionMaternalRequest();
            relatedRequest.setAnexoMatCambiarInsInstitucionMaternalpthisAnexoMatInstance(institucionMatNeoCambiarInsInstitucionMaternalpevcAnexoMat);
            relatedRequest.setAnexoMatCambiarInsInstitucionMaternalpevcInstitucionMatNeoInstance(institucionMatNeoCambiarInsInstitucionMaternalpthisInstitucionMatNeo);
            anexoMatSrv.cambiarInsInstitucionMaternalImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICInstitucionMatNeo());

        return response;
    }

    // Service: CambiarInsInstitucionNeonatal

    /*
     * Entry point for service CambiarInsInstitucionNeonatal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambiarInsInstitucionNeonatal(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarInsInstitucionNeonatalRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = institucionMatNeoSrv.cambiarInsInstitucionNeonatalTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambiarInsInstitucionNeonatal
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambiarInsInstitucionNeonatalTxn(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarInsInstitucionNeonatalRequest request) throws ModelException {
        request.setInstitucionMatNeoCambiarInsInstitucionNeonatalpthisInstitucionMatNeoInstance(institucionMatNeoSrv.getByOIDWithHV(request.getInstitucionMatNeoCambiarInsInstitucionNeonatalpthisInstitucionMatNeo()));
        request.setInstitucionMatNeoCambiarInsInstitucionNeonatalpevcAnexoNeoInstance(anexoNeoSrv.getByOIDWithHV(request.getInstitucionMatNeoCambiarInsInstitucionNeonatalpevcAnexoNeo()));
        return institucionMatNeoSrv.cambiarInsInstitucionNeonatalImplementation(request, true);
    }

    /*
     * Implements service CambiarInsInstitucionNeonatal
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambiarInsInstitucionNeonatalImplementation(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarInsInstitucionNeonatalRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        InstitucionMatNeo institucionMatNeoCambiarInsInstitucionNeonatalpthisInstitucionMatNeo = request.getInstitucionMatNeoCambiarInsInstitucionNeonatalpthisInstitucionMatNeoInstance();
        AnexoNeo institucionMatNeoCambiarInsInstitucionNeonatalpevcAnexoNeo = request.getInstitucionMatNeoCambiarInsInstitucionNeonatalpevcAnexoNeoInstance();

        request.checkArguments();
        InstitucionMatNeo instance = institucionMatNeoCambiarInsInstitucionNeonatalpthisInstitucionMatNeo;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.anexoneo.siu.CambiarInsInstitucionNeonatalRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.anexoneo.siu.CambiarInsInstitucionNeonatalRequest();
            relatedRequest.setAnexoNeoCambiarInsInstitucionNeonatalpthisAnexoNeoInstance(institucionMatNeoCambiarInsInstitucionNeonatalpevcAnexoNeo);
            relatedRequest.setAnexoNeoCambiarInsInstitucionNeonatalpevcInstitucionMatNeoInstance(institucionMatNeoCambiarInsInstitucionNeonatalpthisInstitucionMatNeo);
            anexoNeoSrv.cambiarInsInstitucionNeonatalImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICInstitucionMatNeo());

        return response;
    }

    // Service: CambiarTipoIdentificacion

    /*
     * Entry point for service CambiarTipoIdentificacion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambiarTipoIdentificacion(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarTipoIdentificacionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = institucionMatNeoSrv.cambiarTipoIdentificacionTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambiarTipoIdentificacion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambiarTipoIdentificacionTxn(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarTipoIdentificacionRequest request) throws ModelException {
        request.setInstitucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeoInstance(institucionMatNeoSrv.getByOIDWithHV(request.getInstitucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo()));
        request.setInstitucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getInstitucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion()));
        return institucionMatNeoSrv.cambiarTipoIdentificacionImplementation(request, true);
    }

    /*
     * Implements service CambiarTipoIdentificacion
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambiarTipoIdentificacionImplementation(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarTipoIdentificacionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        InstitucionMatNeo institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo = request.getInstitucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeoInstance();
        TipoIdentificacion institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion = request.getInstitucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacionInstance();

        request.checkArguments();
        InstitucionMatNeo instance = institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo;

        instance.add2TipoIdentificacion(institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambiarTipoIdentificacionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambiarTipoIdentificacionRequest();
            relatedRequest.setTipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacionInstance(institucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacion);
            relatedRequest.setTipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeoInstance(institucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeo);
            tipoIdentificacionSrv.cambiarTipoIdentificacionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICInstitucionMatNeo());

        return response;
    }

    // Service: CambiarTarifa

    /*
     * Entry point for service CambiarTarifa
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambiarTarifa(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarTarifaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = institucionMatNeoSrv.cambiarTarifaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambiarTarifa
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambiarTarifaTxn(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarTarifaRequest request) throws ModelException {
        request.setInstitucionMatNeoCambiarTarifapthisInstitucionMatNeoInstance(institucionMatNeoSrv.getByOIDWithHV(request.getInstitucionMatNeoCambiarTarifapthisInstitucionMatNeo()));
        request.setInstitucionMatNeoCambiarTarifapevcTarifaInstance(tarifaSrv.getByOIDWithHV(request.getInstitucionMatNeoCambiarTarifapevcTarifa()));
        return institucionMatNeoSrv.cambiarTarifaImplementation(request, true);
    }

    /*
     * Implements service CambiarTarifa
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambiarTarifaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarTarifaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        InstitucionMatNeo institucionMatNeoCambiarTarifapthisInstitucionMatNeo = request.getInstitucionMatNeoCambiarTarifapthisInstitucionMatNeoInstance();
        Tarifa institucionMatNeoCambiarTarifapevcTarifa = request.getInstitucionMatNeoCambiarTarifapevcTarifaInstance();

        request.checkArguments();
        InstitucionMatNeo instance = institucionMatNeoCambiarTarifapthisInstitucionMatNeo;

        instance.add2Tarifa(institucionMatNeoCambiarTarifapevcTarifa);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CambiarTarifaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CambiarTarifaRequest();
            relatedRequest.setTarifaCambiarTarifapthisTarifaInstance(institucionMatNeoCambiarTarifapevcTarifa);
            relatedRequest.setTarifaCambiarTarifapevcInstitucionMatNeoInstance(institucionMatNeoCambiarTarifapthisInstitucionMatNeo);
            tarifaSrv.cambiarTarifaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICInstitucionMatNeo());

        return response;
    }

    // Service: CambiarMunicipioInst

    /*
     * Entry point for service CambiarMunicipioInst
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambiarMunicipioInst(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarMunicipioInstRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = institucionMatNeoSrv.cambiarMunicipioInstTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambiarMunicipioInst
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambiarMunicipioInstTxn(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarMunicipioInstRequest request) throws ModelException {
        request.setInstitucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeoInstance(institucionMatNeoSrv.getByOIDWithHV(request.getInstitucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo()));
        request.setInstitucionMatNeoCambiarMunicipioInstpevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getInstitucionMatNeoCambiarMunicipioInstpevcMunicipio()));
        return institucionMatNeoSrv.cambiarMunicipioInstImplementation(request, true);
    }

    /*
     * Implements service CambiarMunicipioInst
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambiarMunicipioInstImplementation(
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarMunicipioInstRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        InstitucionMatNeo institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo = request.getInstitucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeoInstance();
        Municipio institucionMatNeoCambiarMunicipioInstpevcMunicipio = request.getInstitucionMatNeoCambiarMunicipioInstpevcMunicipioInstance();

        request.checkArguments();
        InstitucionMatNeo instance = institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo;

        instance.add2Municipio(institucionMatNeoCambiarMunicipioInstpevcMunicipio);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.CambiarMunicipioInstRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.CambiarMunicipioInstRequest();
            relatedRequest.setMunicipioCambiarMunicipioInstpthisMunicipioInstance(institucionMatNeoCambiarMunicipioInstpevcMunicipio);
            relatedRequest.setMunicipioCambiarMunicipioInstpevcInstitucionMatNeoInstance(institucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeo);
            municipioSrv.cambiarMunicipioInstImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICInstitucionMatNeo());

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
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.TMODIFICARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = institucionMatNeoSrv.tMODIFICARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.TMODIFICARRequest request) throws ModelException {
        request.setInstitucionMatNeoTMODIFICARpthisInstitucionMatNeoInstance(institucionMatNeoSrv.getByOIDWithHV(request.getInstitucionMatNeoTMODIFICARpthisInstitucionMatNeo()));
        request.setInstitucionMatNeoTMODIFICARptpevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getInstitucionMatNeoTMODIFICARptpevcMunicipio()));
        request.setInstitucionMatNeoTMODIFICARptpevcTarifaInstance(tarifaSrv.getByOIDWithHV(request.getInstitucionMatNeoTMODIFICARptpevcTarifa()));
        request.setInstitucionMatNeoTMODIFICARptpevcTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(new TipoIdentificacionOid().fromJsonString(request.getInstitucionMatNeoTMODIFICARptpevcTipoIdentificacion())));
        return institucionMatNeoSrv.tMODIFICARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.TMODIFICARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        String institucionMatNeoTMODIFICARptpNumIdentificacion = request.getInstitucionMatNeoTMODIFICARptpNumIdentificacion();
        String institucionMatNeoTMODIFICARptpNombre = request.getInstitucionMatNeoTMODIFICARptpNombre();
        Boolean institucionMatNeoTMODIFICARptpParaMaternidad = request.getInstitucionMatNeoTMODIFICARptpParaMaternidad();
        Boolean institucionMatNeoTMODIFICARptpParaNeonatal = request.getInstitucionMatNeoTMODIFICARptpParaNeonatal();
        Date institucionMatNeoTMODIFICARptpFechaInicial = request.getInstitucionMatNeoTMODIFICARptpFechaInicial();
        Date institucionMatNeoTMODIFICARptpFechaFinal = request.getInstitucionMatNeoTMODIFICARptpFechaFinal();
        Municipio institucionMatNeoTMODIFICARptpevcMunicipio = request.getInstitucionMatNeoTMODIFICARptpevcMunicipioInstance();
        Tarifa institucionMatNeoTMODIFICARptpevcTarifa = request.getInstitucionMatNeoTMODIFICARptpevcTarifaInstance();
        TipoIdentificacion institucionMatNeoTMODIFICARptpevcTipoIdentificacion = request.getInstitucionMatNeoTMODIFICARptpevcTipoIdentificacionInstance();
        InstitucionMatNeo institucionMatNeoTMODIFICARpthisInstitucionMatNeo = request.getInstitucionMatNeoTMODIFICARpthisInstitucionMatNeoInstance();

        request.checkArguments();
        InstitucionMatNeo instance = institucionMatNeoTMODIFICARpthisInstitucionMatNeo;

        com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.ModificarRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.ModificarRequest();
        step1Request.setInstitucionMatNeomodificarpthisInstitucionMatNeoInstance(institucionMatNeoTMODIFICARpthisInstitucionMatNeo);
        step1Request.setInstitucionMatNeomodificarpNumIdentificacion(institucionMatNeoTMODIFICARptpNumIdentificacion);
        step1Request.setInstitucionMatNeomodificarpNombre(institucionMatNeoTMODIFICARptpNombre);
        step1Request.setInstitucionMatNeomodificarpParaMaternidad(institucionMatNeoTMODIFICARptpParaMaternidad);
        step1Request.setInstitucionMatNeomodificarpParaNeonatal(institucionMatNeoTMODIFICARptpParaNeonatal);
        step1Request.setInstitucionMatNeomodificarpFechaInicial(institucionMatNeoTMODIFICARptpFechaInicial);
        step1Request.setInstitucionMatNeomodificarpFechaFinal(institucionMatNeoTMODIFICARptpFechaFinal);
        institucionMatNeoSrv.modificarImplementation(step1Request);

        /* Action 2: IF (Municipio <> ptpevcMunicipio) */
        boolean ifCondition2 = !(institucionMatNeoTMODIFICARpthisInstitucionMatNeo.getMunicipio().equalsObject(institucionMatNeoTMODIFICARptpevcMunicipio));
        if (ifCondition2) {
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarMunicipioInstRequest step20Request = new com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarMunicipioInstRequest();
            step20Request.setInstitucionMatNeoCambiarMunicipioInstpthisInstitucionMatNeoInstance(institucionMatNeoTMODIFICARpthisInstitucionMatNeo);
            step20Request.setInstitucionMatNeoCambiarMunicipioInstpevcMunicipioInstance(institucionMatNeoTMODIFICARptpevcMunicipio);
            institucionMatNeoSrv.cambiarMunicipioInstImplementation(step20Request, true);
        }

        /* Action 3: IF (Tarifa <> ptpevcTarifa) */
        boolean ifCondition3 = !(institucionMatNeoTMODIFICARpthisInstitucionMatNeo.getTarifa().equalsObject(institucionMatNeoTMODIFICARptpevcTarifa));
        if (ifCondition3) {
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarTarifaRequest step30Request = new com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarTarifaRequest();
            step30Request.setInstitucionMatNeoCambiarTarifapthisInstitucionMatNeoInstance(institucionMatNeoTMODIFICARpthisInstitucionMatNeo);
            step30Request.setInstitucionMatNeoCambiarTarifapevcTarifaInstance(institucionMatNeoTMODIFICARptpevcTarifa);
            institucionMatNeoSrv.cambiarTarifaImplementation(step30Request, true);
        }

        /* Action 4: IF (TipoIdentificacion <> ptpevcTipoIdentificacion) */
        boolean ifCondition4 = !(institucionMatNeoTMODIFICARpthisInstitucionMatNeo.getTipoIdentificacion().equalsObject(institucionMatNeoTMODIFICARptpevcTipoIdentificacion));
        if (ifCondition4) {
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarTipoIdentificacionRequest step40Request = new com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarTipoIdentificacionRequest();
            step40Request.setInstitucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeoInstance(institucionMatNeoTMODIFICARpthisInstitucionMatNeo);
            step40Request.setInstitucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacionInstance(institucionMatNeoTMODIFICARptpevcTipoIdentificacion);
            institucionMatNeoSrv.cambiarTipoIdentificacionImplementation(step40Request, true);
        }

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICInstitucionMatNeo());

        return response;
    }


    private void suppInfo4ICInstitucionMatNeo(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(InstitucionMatNeoConstants.ATTR_NAME_INSTITUCIONMATNEONOMBRE, Constants.Type.STRING.getTypeName()));
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
        select.getOrderByPart().addItem(Constants.normalizePath(InstitucionMatNeoConstants.CLASS_NAME, InstitucionMatNeoConstants.ATTR_FIELD_INSTITUCIONMATNEOCODIGO, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildInstitucionMatNeoRelatedInstance(relatedInstanceJsonOid);
        }
        if (InstitucionMatNeoConstants.ROLE_NAME_ANEXOMAT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildInstitucionMatNeoRelatedInstance4AnexoMat(relatedInstanceJsonOid, remainingPath);
        }
        if (InstitucionMatNeoConstants.ROLE_NAME_ANEXONEO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildInstitucionMatNeoRelatedInstance4AnexoNeo(relatedInstanceJsonOid, remainingPath);
        }
        if (InstitucionMatNeoConstants.ROLE_NAME_TIPOIDENTIFICACION.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildInstitucionMatNeoRelatedInstance4TipoIdentificacion(relatedInstanceJsonOid, remainingPath);
        }
        if (InstitucionMatNeoConstants.ROLE_NAME_TARIFA.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildInstitucionMatNeoRelatedInstance4Tarifa(relatedInstanceJsonOid, remainingPath);
        }
        if (InstitucionMatNeoConstants.ROLE_NAME_MUNICIPIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildInstitucionMatNeoRelatedInstance4Municipio(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildInstitucionMatNeoRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new InstitucionMatNeo(mapper.readValue(relatedInstanceJsonOid, InstitucionMatNeoOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildInstitucionMatNeoRelatedInstance4AnexoMat(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new AnexoMat(mapper.readValue(relatedInstanceJsonOid, AnexoMatOid.class)): AnexoMat.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildInstitucionMatNeoRelatedInstance4AnexoNeo(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new AnexoNeo(mapper.readValue(relatedInstanceJsonOid, AnexoNeoOid.class)): AnexoNeo.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildInstitucionMatNeoRelatedInstance4TipoIdentificacion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoIdentificacion(mapper.readValue(relatedInstanceJsonOid, TipoIdentificacionOid.class)): TipoIdentificacion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildInstitucionMatNeoRelatedInstance4Tarifa(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Tarifa(mapper.readValue(relatedInstanceJsonOid, TarifaOid.class)): Tarifa.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildInstitucionMatNeoRelatedInstance4Municipio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Municipio(mapper.readValue(relatedInstanceJsonOid, MunicipioOid.class)): Municipio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            InstitucionMatNeoOid oid = new InstitucionMatNeoOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(InstitucionMatNeoConstants.ATTR_FIELD_INSTITUCIONMATNEOCODIGO, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getInstitucionMatNeoCodigo(), Constants.Type.STRING.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(InstitucionMatNeoService.class).error(e.getMessage());
        }
    }

}
