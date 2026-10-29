package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.TipoIdentificacionConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.AnexoC;
import com.keralty.aeconfiguraciones.persistence.Asesor;
import com.keralty.aeconfiguraciones.persistence.Contratante;
import com.keralty.aeconfiguraciones.persistence.FamiliaNovedad;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.NovedadCT;
import com.keralty.aeconfiguraciones.persistence.oid.AnexoCOid;
import com.keralty.aeconfiguraciones.persistence.oid.AsesorOid;
import com.keralty.aeconfiguraciones.persistence.oid.ContratanteOid;
import com.keralty.aeconfiguraciones.persistence.oid.FamiliaNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.InstitucionMatNeoOid;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadCTOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaCOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoIdentificacionOid;
import com.keralty.aeconfiguraciones.persistence.oid.TitularOid;
import com.keralty.aeconfiguraciones.persistence.oid.UsuarioNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.UsuarioOid;
import com.keralty.aeconfiguraciones.persistence.Persona;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.Titular;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import com.keralty.aeconfiguraciones.persistence.UsuarioNovedad;
import com.keralty.aeconfiguraciones.repository.TipoIdentificacionJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
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
public class TipoIdentificacionService extends AbstractService {

    TipoIdentificacionJpaRepository repository;

    TipoIdentificacionService tipoIdentificacionSrv;

    PersonaService personaSrv;

    ContratanteService contratanteSrv;

    UsuarioService usuarioSrv;

    TitularService titularSrv;

    InstitucionMatNeoService institucionMatNeoSrv;

    PersonaCService personaCSrv;

    AnexoCService anexoCSrv;

    SolicitudNovedadService solicitudNovedadSrv;

    FamiliaNovedadService familiaNovedadSrv;

    UsuarioNovedadService usuarioNovedadSrv;

    NovedadCTService novedadCTSrv;

    @Inject
    public TipoIdentificacionService(
            TipoIdentificacionService tipoIdentificacionSrv,
            PersonaService personaSrv,
            ContratanteService contratanteSrv,
            UsuarioService usuarioSrv,
            TitularService titularSrv,
            InstitucionMatNeoService institucionMatNeoSrv,
            PersonaCService personaCSrv,
            AnexoCService anexoCSrv,
            SolicitudNovedadService solicitudNovedadSrv,
            FamiliaNovedadService familiaNovedadSrv,
            UsuarioNovedadService usuarioNovedadSrv,
            NovedadCTService novedadCTSrv,
            TipoIdentificacionJpaRepository repository) {
        this.tipoIdentificacionSrv = tipoIdentificacionSrv;
        this.personaSrv = personaSrv;
        this.contratanteSrv = contratanteSrv;
        this.usuarioSrv = usuarioSrv;
        this.titularSrv = titularSrv;
        this.institucionMatNeoSrv = institucionMatNeoSrv;
        this.personaCSrv = personaCSrv;
        this.anexoCSrv = anexoCSrv;
        this.solicitudNovedadSrv = solicitudNovedadSrv;
        this.familiaNovedadSrv = familiaNovedadSrv;
        this.usuarioNovedadSrv = usuarioNovedadSrv;
        this.novedadCTSrv = novedadCTSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, TipoIdentificacionConstants.CLASS_NAME);
    }

    private List<TipoIdentificacion> solveTipoIdentificacionTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, TipoIdentificacionConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveTipoIdentificacionTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, TipoIdentificacionConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class TipoIdentificacion
     *
     * @return Population of class TipoIdentificacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoIdentificacion> get() {
        List<TipoIdentificacion> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class TipoIdentificacion
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class TipoIdentificacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<TipoIdentificacion> instances = get(queryRequest);
        if(TipoIdentificacionConstants.DSICTIPOIDENTIFICACION.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICTipoIdentificacion())).collect(Collectors.toList());
        }
        if(TipoIdentificacionConstants.DSDSTIPOIDENTIFICACIONNULL.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSTipoIdentificacionNull())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class TipoIdentificacion that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class TipoIdentificacion matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoIdentificacion> get(QueryRequest queryRequest) {
        return solveTipoIdentificacionTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveTipoIdentificacionTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveTipoIdentificacionTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveTipoIdentificacionTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveTipoIdentificacionTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of TipoIdentificacion by ID
     *
     * @param tipoIdentificacionCodigo Identification field tipoIdentificacionCodigo
     *
     * @return Instance of TipoIdentificacion
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoIdentificacion getById(Long tipoIdentificacionCodigo) {
        return getByOID(new TipoIdentificacionOid(tipoIdentificacionCodigo));
    }
    
    /*
     * Returns an instance of TipoIdentificacion by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoIdentificacion
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoIdentificacion getByOID(TipoIdentificacionOid oid) {
        Optional<TipoIdentificacion> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of TipoIdentificacion by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoIdentificacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public TipoIdentificacion getByOIDWithHV(TipoIdentificacionOid oid) {
        Optional<TipoIdentificacion> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<TipoIdentificacion> getInstanceByOid(TipoIdentificacionOid oid) {
        return oid != null && oid.getTipoIdentificacionCodigo() != null
               ? repository.findById(oid.getTipoIdentificacionCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of TipoIdentificacion by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of TipoIdentificacion
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoIdentificacion getByJsonOid(String jsonOid) {
        TipoIdentificacionOid oid = new TipoIdentificacionOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of TipoIdentificacion related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TipoIdentificacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoIdentificacion> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveTipoIdentificacionTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of TipoIdentificacion related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TipoIdentificacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoIdentificacion> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of TipoIdentificacion
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of TipoIdentificacion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getTipoIdentificacionDynamic(TipoIdentificacionOid oid, String displaySetItems) {
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
    public String getSuppInfo(TipoIdentificacionOid oid, String dsName) {
        TipoIdentificacion instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(TipoIdentificacion instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (TipoIdentificacionConstants.DSICTIPOIDENTIFICACION.equals(dsName))
            return instance.buildICTipoIdentificacion();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(TipoIdentificacion instance, String dsName) {
        if (TipoIdentificacionConstants.DSICTIPOIDENTIFICACION.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICTipoIdentificacion());
        if (TipoIdentificacionConstants.DSDSTIPOIDENTIFICACIONNULL.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSTipoIdentificacionNull());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (TipoIdentificacionConstants.FILTER_NAME_IFTIPOIDENTIFICACIONNOMBREC.equalsIgnoreCase(filterRequest.getName()))
            iFTipoIdentificacionNombreC(filterRequest, sql);
    }

    @Override
    protected void applyNavigationalFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if ("Persona_TADDUSUARIOASOL_pt_p_agrTipoIdentificacion_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431886233600805FiltNav10(sql);
        if ("Solicitud_TINDICARCONTRATANTE2_pt_p_evcTipoIdentificacion_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431957012480960FiltNav32(sql);
        if ("Solicitud_TINDICARTITULAR2_pt_p_agrTipoIdentificacion_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431957012480960FiltNav31(sql);
        if ("Usuario_TMODUSUARIO_pt_p_evcTipoIdentificacion_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1432633737216826FiltNav3(sql);
        if ("UsuarioNovedad_TCAMBIARTIPOIDENT_pt_p_evcTipoIdentificacion_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1581442138112529FiltNav4(sql);
        if ("TGValidadorUsuarios_ptTipoDoc_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            iGl1658391625728650FiltNav1(sql);
        if ("Persona_F_PersonaConsultarCORE_vo_TipoIdentificacion_NavFilter".equalsIgnoreCase(filterRequest.getName()))
            clas1431886233600805FiltNav6(sql);
        if ("Persona_F_PersonaConsultarCORENat_vo_TipoIdentificacion_NavFilter".equalsIgnoreCase(filterRequest.getName()))
            clas1431886233600805FiltNav8(sql);
    }


    private void iFTipoIdentificacionNombreC(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        String vNombreCorto = filterRequest.hasFilterVariable(TipoIdentificacionConstants.VAR_NAME_IFTIPOIDENTIFICACIONNOMBREC_VNOMBRECORTO) ? (String) filterRequest.getFilterVariable(TipoIdentificacionConstants.VAR_NAME_IFTIPOIDENTIFICACIONNOMBREC_VNOMBRECORTO, FilterVariableRequest.STRING) : null;

        // NombreCorto = vNombreCorto
        if (vNombreCorto != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoIdentificacionConstants.ATTR_NAME_TIPOIDENTIFICACIONNOMBRECORTO, Constants.Type.STRING.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(vNombreCorto, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        sql.addConjunction(conjunction);
    }

    private void clas1431886233600805FiltNav10(SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();

        // NIT = false
        // '=' Comparison
        SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
        comparison1.setOperator("=");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoIdentificacionConstants.ATTR_NAME_TIPOIDENTIFICACIONNIT, Constants.Type.BOOL.getTypeName());
        comparison1.setFirstOperand(operand112);
        
        // Operand 2
        comparison1.setSecondOperand(new SQLWhereOperandLiteral(Boolean.FALSE, Constants.Type.BOOL.getTypeName()));
        conjunction.addOperand(comparison1);

        sql.addConjunction(conjunction);
    }

    private void clas1431957012480960FiltNav32(SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();

        // ValidoContratante = true
        // '=' Comparison
        SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
        comparison1.setOperator("=");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoIdentificacionConstants.ATTR_NAME_TIPOIDENTIFICACIONVALIDOCONTRATANTE, Constants.Type.BOOL.getTypeName());
        comparison1.setFirstOperand(operand112);
        
        // Operand 2
        comparison1.setSecondOperand(new SQLWhereOperandLiteral(Boolean.TRUE, Constants.Type.BOOL.getTypeName()));
        conjunction.addOperand(comparison1);

        sql.addConjunction(conjunction);
    }

    private void clas1431957012480960FiltNav31(SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();

        // ValidoTitular = true
        // '=' Comparison
        SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
        comparison1.setOperator("=");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoIdentificacionConstants.ATTR_NAME_TIPOIDENTIFICACIONVALIDOTITULAR, Constants.Type.BOOL.getTypeName());
        comparison1.setFirstOperand(operand112);
        
        // Operand 2
        comparison1.setSecondOperand(new SQLWhereOperandLiteral(Boolean.TRUE, Constants.Type.BOOL.getTypeName()));
        conjunction.addOperand(comparison1);

        sql.addConjunction(conjunction);
    }

    private void clas1432633737216826FiltNav3(SQLSelect sql) {
        clas1431886233600805FiltNav10(sql);
    }

    private void clas1581442138112529FiltNav4(SQLSelect sql) {
        clas1431886233600805FiltNav10(sql);
    }

    private void iGl1658391625728650FiltNav1(SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();

        // VisibleValidadorUsu = true
        // '=' Comparison
        SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
        comparison1.setOperator("=");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(TipoIdentificacionConstants.ATTR_NAME_TIPOIDENTIFICACIONVISIBLEVALIDADORUSU, Constants.Type.BOOL.getTypeName());
        comparison1.setFirstOperand(operand112);
        
        // Operand 2
        comparison1.setSecondOperand(new SQLWhereOperandLiteral(Boolean.TRUE, Constants.Type.BOOL.getTypeName()));
        conjunction.addOperand(comparison1);

        sql.addConjunction(conjunction);
    }

    private void clas1431886233600805FiltNav6(SQLSelect sql) {
        clas1431957012480960FiltNav32(sql);
    }

    private void clas1431886233600805FiltNav8(SQLSelect sql) {
        clas1431957012480960FiltNav32(sql);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUTipoIdentificacion
     *
     * @param queryRequest Query request
     * @return List of viewmodels for TipoIdentificacion using displayset AutoDS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.ds.AutoDS> piuPIUTipoIdentificacion(QueryRequest queryRequest) {
        return solveTipoIdentificacionTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.ds.AutoDS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUTipoIdentificacion
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUTipoIdentificacion
     */
    public int getCount4PIUTipoIdentificacion(QueryRequest queryRequest) {
        return solveTipoIdentificacionTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUTipoIdentificacionNull
     *
     * @param queryRequest Query request
     * @return List of viewmodels for TipoIdentificacion using displayset DSTipoIdentificacionNull that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.ds.DSTipoIdentificacionNull> piuPIUTipoIdentificacionNull(QueryRequest queryRequest) {
        return solveTipoIdentificacionTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.ds.DSTipoIdentificacionNull::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUTipoIdentificacionNull
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUTipoIdentificacionNull
     */
    public int getCount4PIUTipoIdentificacionNull(QueryRequest queryRequest) {
        return solveTipoIdentificacionTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CrearRequest request) throws ModelException {
        return tipoIdentificacionSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long tipoIdentificacioncrearpatrCodigo = request.getTipoIdentificacioncrearpatrCodigo();
        String tipoIdentificacioncrearpatrDescripcion = request.getTipoIdentificacioncrearpatrDescripcion();
        String tipoIdentificacioncrearpatrNombreCorto = request.getTipoIdentificacioncrearpatrNombreCorto();
        Boolean tipoIdentificacioncrearpatrValidoContratante = request.getTipoIdentificacioncrearpatrValidoContratante();
        Boolean tipoIdentificacioncrearpatrValidoTitular = request.getTipoIdentificacioncrearpatrValidoTitular();
        Boolean tipoIdentificacioncrearpatrDiplomatico = request.getTipoIdentificacioncrearpatrDiplomatico();
        Boolean tipoIdentificacioncrearpatrNIT = request.getTipoIdentificacioncrearpatrNIT();
        Boolean tipoIdentificacioncrearpatrHijoDe = request.getTipoIdentificacioncrearpatrHijoDe();
        Boolean tipoIdentificacioncrearpatrRequiereEPS = request.getTipoIdentificacioncrearpatrRequiereEPS();
        Boolean tipoIdentificacioncrearpatrMenorSinIdentificar = request.getTipoIdentificacioncrearpatrMenorSinIdentificar();
        Boolean tipoIdentificacioncrearpatrEsPasaporte = request.getTipoIdentificacioncrearpatrEsPasaporte();
        Boolean tipoIdentificacioncrearpatrVisibleValidadorUsu = request.getTipoIdentificacioncrearpatrVisibleValidadorUsu();

        request.checkArguments();
        TipoIdentificacion instance = new TipoIdentificacion();
        instance.setTipoIdentificacionCodigo(tipoIdentificacioncrearpatrCodigo);
        instance.setTipoIdentificacionDescripcion(tipoIdentificacioncrearpatrDescripcion);
        instance.setTipoIdentificacionNombreCorto(tipoIdentificacioncrearpatrNombreCorto);
        instance.setTipoIdentificacionValidoContratante(tipoIdentificacioncrearpatrValidoContratante);
        instance.setTipoIdentificacionValidoTitular(tipoIdentificacioncrearpatrValidoTitular);
        instance.setTipoIdentificacionDiplomatico(tipoIdentificacioncrearpatrDiplomatico);
        instance.setTipoIdentificacionNIT(tipoIdentificacioncrearpatrNIT);
        instance.setTipoIdentificacionHijoDe(tipoIdentificacioncrearpatrHijoDe);
        instance.setTipoIdentificacionRequiereEPS(tipoIdentificacioncrearpatrRequiereEPS);
        instance.setTipoIdentificacionMenorSinIdentificar(tipoIdentificacioncrearpatrMenorSinIdentificar);
        instance.setTipoIdentificacionEsPasaporte(tipoIdentificacioncrearpatrEsPasaporte);
        instance.setTipoIdentificacionVisibleValidadorUsu(tipoIdentificacioncrearpatrVisibleValidadorUsu);
        if (tipoIdentificacionSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(TipoIdentificacionConstants.CLASS_ALIAS);
        }

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

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
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.EliminarRequest request) throws ModelException {
        request.setTipoIdentificacioneliminarpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(new TipoIdentificacionOid().fromJsonString(request.getTipoIdentificacioneliminarpthisTipoIdentificacion())));
        return tipoIdentificacionSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacioneliminarpthisTipoIdentificacion = request.getTipoIdentificacioneliminarpthisTipoIdentificacionInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacioneliminarpthisTipoIdentificacion;
        if(tipoIdentificacioneliminarpthisTipoIdentificacion.getAsesores().size() > 0) {
            throw new MinCardinalityException(TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ROLE_ALIAS_ASESORES);
        }
        if(tipoIdentificacioneliminarpthisTipoIdentificacion.getPersonas().size() > 0) {
            throw new MinCardinalityException(TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ROLE_ALIAS_PERSONAS);
        }
        if(tipoIdentificacioneliminarpthisTipoIdentificacion.getPersonasC().size() > 0) {
            throw new MinCardinalityException(TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ROLE_ALIAS_PERSONASC);
        }
        if(tipoIdentificacioneliminarpthisTipoIdentificacion.getUsuarioS().size() > 0) {
            throw new MinCardinalityException(TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ROLE_ALIAS_USUARIOS);
        }
        if(tipoIdentificacioneliminarpthisTipoIdentificacion.getContratantes().size() > 0) {
            throw new MinCardinalityException(TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ROLE_ALIAS_CONTRATANTES);
        }
        if(tipoIdentificacioneliminarpthisTipoIdentificacion.getAnexosC().size() > 0) {
            throw new MinCardinalityException(TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ROLE_ALIAS_ANEXOSC);
        }
        if(tipoIdentificacioneliminarpthisTipoIdentificacion.getTitulares().size() > 0) {
            throw new MinCardinalityException(TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ROLE_ALIAS_TITULARES);
        }
        if(tipoIdentificacioneliminarpthisTipoIdentificacion.getInstitucionMatNeo().size() > 0) {
            throw new MinCardinalityException(TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ROLE_ALIAS_INSTITUCIONMATNEO);
        }
        if(tipoIdentificacioneliminarpthisTipoIdentificacion.getSolicitudesNovedad().size() > 0) {
            throw new MinCardinalityException(TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ROLE_ALIAS_SOLICITUDESNOVEDAD);
        }
        if(tipoIdentificacioneliminarpthisTipoIdentificacion.getFamiliasNovedad().size() > 0) {
            throw new MinCardinalityException(TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ROLE_ALIAS_FAMILIASNOVEDAD);
        }
        if(tipoIdentificacioneliminarpthisTipoIdentificacion.getUsuariosNovedad().size() > 0) {
            throw new MinCardinalityException(TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ROLE_ALIAS_USUARIOSNOVEDAD);
        }
        if(tipoIdentificacioneliminarpthisTipoIdentificacion.getUsuarioNovedad().size() > 0) {
            throw new MinCardinalityException(TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ROLE_ALIAS_USUARIONOVEDAD);
        }
        if(tipoIdentificacioneliminarpthisTipoIdentificacion.getNovedadesCT().size() > 0) {
            throw new MinCardinalityException(TipoIdentificacionConstants.CLASS_ALIAS, TipoIdentificacionConstants.ROLE_ALIAS_NOVEDADESCT);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

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
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.ModificarRequest request) throws ModelException {
        request.setTipoIdentificacionmodificarpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(new TipoIdentificacionOid().fromJsonString(request.getTipoIdentificacionmodificarpthisTipoIdentificacion())));
        return tipoIdentificacionSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionmodificarpthisTipoIdentificacion = request.getTipoIdentificacionmodificarpthisTipoIdentificacionInstance();
        Boolean tipoIdentificacionmodificarpValidoContratante = request.getTipoIdentificacionmodificarpValidoContratante();
        Boolean tipoIdentificacionmodificarpValidoTitular = request.getTipoIdentificacionmodificarpValidoTitular();
        Boolean tipoIdentificacionmodificarpDiplomatico = request.getTipoIdentificacionmodificarpDiplomatico();
        Boolean tipoIdentificacionmodificarpNIT = request.getTipoIdentificacionmodificarpNIT();
        Boolean tipoIdentificacionmodificarpHijoDe = request.getTipoIdentificacionmodificarpHijoDe();
        Boolean tipoIdentificacionmodificarpRequiereEPS = request.getTipoIdentificacionmodificarpRequiereEPS();
        Boolean tipoIdentificacionmodificarpMenorSinIdentificar = request.getTipoIdentificacionmodificarpMenorSinIdentificar();
        Boolean tipoIdentificacionmodificarpEsPasaporte = request.getTipoIdentificacionmodificarpEsPasaporte();
        Boolean tipoIdentificacionmodificarpVisibleValidadorUsuarios = request.getTipoIdentificacionmodificarpVisibleValidadorUsuarios();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionmodificarpthisTipoIdentificacion;
        instance.setTipoIdentificacionValidoContratante(tipoIdentificacionmodificarpValidoContratante);
        instance.setTipoIdentificacionValidoTitular(tipoIdentificacionmodificarpValidoTitular);
        instance.setTipoIdentificacionDiplomatico(tipoIdentificacionmodificarpDiplomatico);
        instance.setTipoIdentificacionNIT(tipoIdentificacionmodificarpNIT);
        instance.setTipoIdentificacionHijoDe(tipoIdentificacionmodificarpHijoDe);
        instance.setTipoIdentificacionRequiereEPS(tipoIdentificacionmodificarpRequiereEPS);
        instance.setTipoIdentificacionMenorSinIdentificar(tipoIdentificacionmodificarpMenorSinIdentificar);
        instance.setTipoIdentificacionEsPasaporte(tipoIdentificacionmodificarpEsPasaporte);
        instance.setTipoIdentificacionVisibleValidadorUsu(tipoIdentificacionmodificarpVisibleValidadorUsuarios);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: sincronizar

    /*
     * Entry point for service sincronizar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse sincronizar(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.SincronizarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.sincronizarTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service sincronizar
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse sincronizarTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.SincronizarRequest request) throws ModelException {
        request.setTipoIdentificacionsincronizarpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getTipoIdentificacionsincronizarpthisTipoIdentificacion()));
        return tipoIdentificacionSrv.sincronizarImplementation(request);
    }

    /*
     * Implements service sincronizar
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse sincronizarImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.SincronizarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionsincronizarpthisTipoIdentificacion = request.getTipoIdentificacionsincronizarpthisTipoIdentificacionInstance();
        String tipoIdentificacionsincronizarpDescripcion = request.getTipoIdentificacionsincronizarpDescripcion();
        String tipoIdentificacionsincronizarpNombreCorto = request.getTipoIdentificacionsincronizarpNombreCorto();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionsincronizarpthisTipoIdentificacion;
        instance.setTipoIdentificacionDescripcion(tipoIdentificacionsincronizarpDescripcion);
        instance.setTipoIdentificacionNombreCorto(tipoIdentificacionsincronizarpNombreCorto);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: CambTipoIdentificacion

    /*
     * Entry point for service CambTipoIdentificacion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambTipoIdentificacion(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdentificacionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.cambTipoIdentificacionTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambTipoIdentificacion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambTipoIdentificacionTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdentificacionRequest request) throws ModelException {
        request.setTipoIdentificacionCambTipoIdentificacionpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getTipoIdentificacionCambTipoIdentificacionpthisTipoIdentificacion()));
        request.setTipoIdentificacionCambTipoIdentificacionpevcPersonaInstance(personaSrv.getByOIDWithHV(request.getTipoIdentificacionCambTipoIdentificacionpevcPersona()));
        return tipoIdentificacionSrv.cambTipoIdentificacionImplementation(request, true);
    }

    /*
     * Implements service CambTipoIdentificacion
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambTipoIdentificacionImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdentificacionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionCambTipoIdentificacionpthisTipoIdentificacion = request.getTipoIdentificacionCambTipoIdentificacionpthisTipoIdentificacionInstance();
        Persona tipoIdentificacionCambTipoIdentificacionpevcPersona = request.getTipoIdentificacionCambTipoIdentificacionpevcPersonaInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionCambTipoIdentificacionpthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.CambTipoIdentificacionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.persona.siu.CambTipoIdentificacionRequest();
            relatedRequest.setPersonaCambTipoIdentificacionpthisPersonaInstance(tipoIdentificacionCambTipoIdentificacionpevcPersona);
            relatedRequest.setPersonaCambTipoIdentificacionpevcTipoIdentificacionInstance(tipoIdentificacionCambTipoIdentificacionpthisTipoIdentificacion);
            personaSrv.cambTipoIdentificacionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: CambTipoIdContratante

    /*
     * Entry point for service CambTipoIdContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambTipoIdContratante(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdContratanteRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.cambTipoIdContratanteTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambTipoIdContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambTipoIdContratanteTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdContratanteRequest request) throws ModelException {
        request.setTipoIdentificacionCambTipoIdContratantepthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getTipoIdentificacionCambTipoIdContratantepthisTipoIdentificacion()));
        request.setTipoIdentificacionCambTipoIdContratantepevcContratanteInstance(contratanteSrv.getByOIDWithHV(request.getTipoIdentificacionCambTipoIdContratantepevcContratante()));
        return tipoIdentificacionSrv.cambTipoIdContratanteImplementation(request, true);
    }

    /*
     * Implements service CambTipoIdContratante
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambTipoIdContratanteImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdContratanteRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionCambTipoIdContratantepthisTipoIdentificacion = request.getTipoIdentificacionCambTipoIdContratantepthisTipoIdentificacionInstance();
        Contratante tipoIdentificacionCambTipoIdContratantepevcContratante = request.getTipoIdentificacionCambTipoIdContratantepevcContratanteInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionCambTipoIdContratantepthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.contratante.siu.CambTipoIdContratanteRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.contratante.siu.CambTipoIdContratanteRequest();
            relatedRequest.setContratanteCambTipoIdContratantepthisContratanteInstance(tipoIdentificacionCambTipoIdContratantepevcContratante);
            relatedRequest.setContratanteCambTipoIdContratantepevcTipoIdentificacionInstance(tipoIdentificacionCambTipoIdContratantepthisTipoIdentificacion);
            contratanteSrv.cambTipoIdContratanteImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: CambTipoIdUsuario

    /*
     * Entry point for service CambTipoIdUsuario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambTipoIdUsuario(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdUsuarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.cambTipoIdUsuarioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambTipoIdUsuario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambTipoIdUsuarioTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdUsuarioRequest request) throws ModelException {
        request.setTipoIdentificacionCambTipoIdUsuariopthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getTipoIdentificacionCambTipoIdUsuariopthisTipoIdentificacion()));
        request.setTipoIdentificacionCambTipoIdUsuariopevcUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getTipoIdentificacionCambTipoIdUsuariopevcUsuario()));
        return tipoIdentificacionSrv.cambTipoIdUsuarioImplementation(request, true);
    }

    /*
     * Implements service CambTipoIdUsuario
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambTipoIdUsuarioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdUsuarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionCambTipoIdUsuariopthisTipoIdentificacion = request.getTipoIdentificacionCambTipoIdUsuariopthisTipoIdentificacionInstance();
        Usuario tipoIdentificacionCambTipoIdUsuariopevcUsuario = request.getTipoIdentificacionCambTipoIdUsuariopevcUsuarioInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionCambTipoIdUsuariopthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.CambTipoIdUsuarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.usuario.siu.CambTipoIdUsuarioRequest();
            relatedRequest.setUsuarioCambTipoIdUsuariopthisUsuarioInstance(tipoIdentificacionCambTipoIdUsuariopevcUsuario);
            relatedRequest.setUsuarioCambTipoIdUsuariopevcTipoIdentificacionInstance(tipoIdentificacionCambTipoIdUsuariopthisTipoIdentificacion);
            usuarioSrv.cambTipoIdUsuarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: CambTipoIdentificacionTitular

    /*
     * Entry point for service CambTipoIdentificacionTitular
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambTipoIdentificacionTitular(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdentificacionTitularRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.cambTipoIdentificacionTitularTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambTipoIdentificacionTitular
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambTipoIdentificacionTitularTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdentificacionTitularRequest request) throws ModelException {
        request.setTipoIdentificacionCambTipoIdentificacionTitularpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getTipoIdentificacionCambTipoIdentificacionTitularpthisTipoIdentificacion()));
        request.setTipoIdentificacionCambTipoIdentificacionTitularpevcTitularInstance(titularSrv.getByOIDWithHV(request.getTipoIdentificacionCambTipoIdentificacionTitularpevcTitular()));
        return tipoIdentificacionSrv.cambTipoIdentificacionTitularImplementation(request, true);
    }

    /*
     * Implements service CambTipoIdentificacionTitular
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambTipoIdentificacionTitularImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdentificacionTitularRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionCambTipoIdentificacionTitularpthisTipoIdentificacion = request.getTipoIdentificacionCambTipoIdentificacionTitularpthisTipoIdentificacionInstance();
        Titular tipoIdentificacionCambTipoIdentificacionTitularpevcTitular = request.getTipoIdentificacionCambTipoIdentificacionTitularpevcTitularInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionCambTipoIdentificacionTitularpthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.titular.siu.CambTipoIdentificacionTitularRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.titular.siu.CambTipoIdentificacionTitularRequest();
            relatedRequest.setTitularCambTipoIdentificacionTitularpthisTitularInstance(tipoIdentificacionCambTipoIdentificacionTitularpevcTitular);
            relatedRequest.setTitularCambTipoIdentificacionTitularpevcTipoIdentificacionInstance(tipoIdentificacionCambTipoIdentificacionTitularpthisTipoIdentificacion);
            titularSrv.cambTipoIdentificacionTitularImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: InsTipoIdentificacionAsesor

    /*
     * Entry point for service InsTipoIdentificacionAsesor
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTipoIdentificacionAsesor(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentificacionAsesorRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.insTipoIdentificacionAsesorTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTipoIdentificacionAsesor
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTipoIdentificacionAsesorTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentificacionAsesorRequest request) throws ModelException {
        request.setTipoIdentificacionInsTipoIdentificacionAsesorpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getTipoIdentificacionInsTipoIdentificacionAsesorpthisTipoIdentificacion()));
        request.setTipoIdentificacionInsTipoIdentificacionAsesorpevcAsesorInstance(asesorSrv.getByOIDWithHV(request.getTipoIdentificacionInsTipoIdentificacionAsesorpevcAsesor()));
        return tipoIdentificacionSrv.insTipoIdentificacionAsesorImplementation(request, true);
    }

    /*
     * Implements service InsTipoIdentificacionAsesor
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTipoIdentificacionAsesorImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentificacionAsesorRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionInsTipoIdentificacionAsesorpthisTipoIdentificacion = request.getTipoIdentificacionInsTipoIdentificacionAsesorpthisTipoIdentificacionInstance();
        Asesor tipoIdentificacionInsTipoIdentificacionAsesorpevcAsesor = request.getTipoIdentificacionInsTipoIdentificacionAsesorpevcAsesorInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionInsTipoIdentificacionAsesorpthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.asesor.siu.InsTipoIdentificacionAsesorRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.asesor.siu.InsTipoIdentificacionAsesorRequest();
            relatedRequest.setAsesorInsTipoIdentificacionAsesorpthisAsesorInstance(tipoIdentificacionInsTipoIdentificacionAsesorpevcAsesor);
            relatedRequest.setAsesorInsTipoIdentificacionAsesorpevcTipoIdentificacionInstance(tipoIdentificacionInsTipoIdentificacionAsesorpthisTipoIdentificacion);
            asesorSrv.insTipoIdentificacionAsesorImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: DelTipoIdentificacionAsesor

    /*
     * Entry point for service DelTipoIdentificacionAsesor
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTipoIdentificacionAsesor(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentificacionAsesorRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.delTipoIdentificacionAsesorTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTipoIdentificacionAsesor
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTipoIdentificacionAsesorTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentificacionAsesorRequest request) throws ModelException {
        request.setTipoIdentificacionDelTipoIdentificacionAsesorpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getTipoIdentificacionDelTipoIdentificacionAsesorpthisTipoIdentificacion()));
        request.setTipoIdentificacionDelTipoIdentificacionAsesorpevcAsesorInstance(asesorSrv.getByOIDWithHV(request.getTipoIdentificacionDelTipoIdentificacionAsesorpevcAsesor()));
        return tipoIdentificacionSrv.delTipoIdentificacionAsesorImplementation(request, true);
    }

    /*
     * Implements service DelTipoIdentificacionAsesor
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTipoIdentificacionAsesorImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentificacionAsesorRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionDelTipoIdentificacionAsesorpthisTipoIdentificacion = request.getTipoIdentificacionDelTipoIdentificacionAsesorpthisTipoIdentificacionInstance();
        Asesor tipoIdentificacionDelTipoIdentificacionAsesorpevcAsesor = request.getTipoIdentificacionDelTipoIdentificacionAsesorpevcAsesorInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionDelTipoIdentificacionAsesorpthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.asesor.siu.DelTipoIdentificacionAsesorRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.asesor.siu.DelTipoIdentificacionAsesorRequest();
            relatedRequest.setAsesorDelTipoIdentificacionAsesorpthisAsesorInstance(tipoIdentificacionDelTipoIdentificacionAsesorpevcAsesor);
            relatedRequest.setAsesorDelTipoIdentificacionAsesorpevcTipoIdentificacionInstance(tipoIdentificacionDelTipoIdentificacionAsesorpthisTipoIdentificacion);
            asesorSrv.delTipoIdentificacionAsesorImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

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
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambiarTipoIdentificacionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.cambiarTipoIdentificacionTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambiarTipoIdentificacionRequest request) throws ModelException {
        request.setTipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getTipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion()));
        request.setTipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeoInstance(institucionMatNeoSrv.getByOIDWithHV(request.getTipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo()));
        return tipoIdentificacionSrv.cambiarTipoIdentificacionImplementation(request, true);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambiarTipoIdentificacionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion = request.getTipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacionInstance();
        InstitucionMatNeo tipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo = request.getTipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeoInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarTipoIdentificacionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarTipoIdentificacionRequest();
            relatedRequest.setInstitucionMatNeoCambiarTipoIdentificacionpthisInstitucionMatNeoInstance(tipoIdentificacionCambiarTipoIdentificacionpevcInstitucionMatNeo);
            relatedRequest.setInstitucionMatNeoCambiarTipoIdentificacionpevcTipoIdentificacionInstance(tipoIdentificacionCambiarTipoIdentificacionpthisTipoIdentificacion);
            institucionMatNeoSrv.cambiarTipoIdentificacionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: CambTipoIdentificacionC

    /*
     * Entry point for service CambTipoIdentificacionC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambTipoIdentificacionC(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdentificacionCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.cambTipoIdentificacionCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambTipoIdentificacionC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambTipoIdentificacionCTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdentificacionCRequest request) throws ModelException {
        request.setTipoIdentificacionCambTipoIdentificacionCpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getTipoIdentificacionCambTipoIdentificacionCpthisTipoIdentificacion()));
        request.setTipoIdentificacionCambTipoIdentificacionCpevcPersonaCInstance(personaCSrv.getByOIDWithHV(request.getTipoIdentificacionCambTipoIdentificacionCpevcPersonaC()));
        return tipoIdentificacionSrv.cambTipoIdentificacionCImplementation(request, true);
    }

    /*
     * Implements service CambTipoIdentificacionC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambTipoIdentificacionCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdentificacionCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionCambTipoIdentificacionCpthisTipoIdentificacion = request.getTipoIdentificacionCambTipoIdentificacionCpthisTipoIdentificacionInstance();
        PersonaC tipoIdentificacionCambTipoIdentificacionCpevcPersonaC = request.getTipoIdentificacionCambTipoIdentificacionCpevcPersonaCInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionCambTipoIdentificacionCpthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.CambTipoIdentificacionCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.personac.siu.CambTipoIdentificacionCRequest();
            relatedRequest.setPersonaCCambTipoIdentificacionCpthisPersonaCInstance(tipoIdentificacionCambTipoIdentificacionCpevcPersonaC);
            relatedRequest.setPersonaCCambTipoIdentificacionCpevcTipoIdentificacionInstance(tipoIdentificacionCambTipoIdentificacionCpthisTipoIdentificacion);
            personaCSrv.cambTipoIdentificacionCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: InsTipoIdentificacionC

    /*
     * Entry point for service InsTipoIdentificacionC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTipoIdentificacionC(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentificacionCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.insTipoIdentificacionCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTipoIdentificacionC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTipoIdentificacionCTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentificacionCRequest request) throws ModelException {
        request.setTipoIdentificacionInsTipoIdentificacionCpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getTipoIdentificacionInsTipoIdentificacionCpthisTipoIdentificacion()));
        request.setTipoIdentificacionInsTipoIdentificacionCpevcAnexoCInstance(anexoCSrv.getByOIDWithHV(request.getTipoIdentificacionInsTipoIdentificacionCpevcAnexoC()));
        return tipoIdentificacionSrv.insTipoIdentificacionCImplementation(request, true);
    }

    /*
     * Implements service InsTipoIdentificacionC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTipoIdentificacionCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentificacionCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionInsTipoIdentificacionCpthisTipoIdentificacion = request.getTipoIdentificacionInsTipoIdentificacionCpthisTipoIdentificacionInstance();
        AnexoC tipoIdentificacionInsTipoIdentificacionCpevcAnexoC = request.getTipoIdentificacionInsTipoIdentificacionCpevcAnexoCInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionInsTipoIdentificacionCpthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.anexoc.siu.InsTipoIdentificacionCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.anexoc.siu.InsTipoIdentificacionCRequest();
            relatedRequest.setAnexoCInsTipoIdentificacionCpthisAnexoCInstance(tipoIdentificacionInsTipoIdentificacionCpevcAnexoC);
            relatedRequest.setAnexoCInsTipoIdentificacionCpevcTipoIdentificacionInstance(tipoIdentificacionInsTipoIdentificacionCpthisTipoIdentificacion);
            anexoCSrv.insTipoIdentificacionCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: DelTipoIdentificacionC

    /*
     * Entry point for service DelTipoIdentificacionC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTipoIdentificacionC(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentificacionCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.delTipoIdentificacionCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTipoIdentificacionC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTipoIdentificacionCTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentificacionCRequest request) throws ModelException {
        request.setTipoIdentificacionDelTipoIdentificacionCpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getTipoIdentificacionDelTipoIdentificacionCpthisTipoIdentificacion()));
        request.setTipoIdentificacionDelTipoIdentificacionCpevcAnexoCInstance(anexoCSrv.getByOIDWithHV(request.getTipoIdentificacionDelTipoIdentificacionCpevcAnexoC()));
        return tipoIdentificacionSrv.delTipoIdentificacionCImplementation(request, true);
    }

    /*
     * Implements service DelTipoIdentificacionC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTipoIdentificacionCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentificacionCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionDelTipoIdentificacionCpthisTipoIdentificacion = request.getTipoIdentificacionDelTipoIdentificacionCpthisTipoIdentificacionInstance();
        AnexoC tipoIdentificacionDelTipoIdentificacionCpevcAnexoC = request.getTipoIdentificacionDelTipoIdentificacionCpevcAnexoCInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionDelTipoIdentificacionCpthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.anexoc.siu.DelTipoIdentificacionCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.anexoc.siu.DelTipoIdentificacionCRequest();
            relatedRequest.setAnexoCDelTipoIdentificacionCpthisAnexoCInstance(tipoIdentificacionDelTipoIdentificacionCpevcAnexoC);
            relatedRequest.setAnexoCDelTipoIdentificacionCpevcTipoIdentificacionInstance(tipoIdentificacionDelTipoIdentificacionCpthisTipoIdentificacion);
            anexoCSrv.delTipoIdentificacionCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: InsTipoIdentContratante

    /*
     * Entry point for service InsTipoIdentContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTipoIdentContratante(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentContratanteRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.insTipoIdentContratanteTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTipoIdentContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTipoIdentContratanteTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentContratanteRequest request) throws ModelException {
        request.setTipoIdentificacionInsTipoIdentContratantepthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(new TipoIdentificacionOid().fromJsonString(request.getTipoIdentificacionInsTipoIdentContratantepthisTipoIdentificacion())));
        request.setTipoIdentificacionInsTipoIdentContratantepevcSolicitudNovedadInstance(solicitudNovedadSrv.getByOIDWithHV(request.getTipoIdentificacionInsTipoIdentContratantepevcSolicitudNovedad()));
        return tipoIdentificacionSrv.insTipoIdentContratanteImplementation(request, true);
    }

    /*
     * Implements service InsTipoIdentContratante
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTipoIdentContratanteImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentContratanteRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionInsTipoIdentContratantepthisTipoIdentificacion = request.getTipoIdentificacionInsTipoIdentContratantepthisTipoIdentificacionInstance();
        SolicitudNovedad tipoIdentificacionInsTipoIdentContratantepevcSolicitudNovedad = request.getTipoIdentificacionInsTipoIdentContratantepevcSolicitudNovedadInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionInsTipoIdentContratantepthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.solicitudnovedad.siu.InsTipoIdentContratanteRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.solicitudnovedad.siu.InsTipoIdentContratanteRequest();
            relatedRequest.setSolicitudNovedadInsTipoIdentContratantepthisSolicitudNovedadInstance(tipoIdentificacionInsTipoIdentContratantepevcSolicitudNovedad);
            relatedRequest.setSolicitudNovedadInsTipoIdentContratantepevcTipoIdentificacionInstance(tipoIdentificacionInsTipoIdentContratantepthisTipoIdentificacion);
            solicitudNovedadSrv.insTipoIdentContratanteImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: DelTipoIdentContratante

    /*
     * Entry point for service DelTipoIdentContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTipoIdentContratante(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentContratanteRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.delTipoIdentContratanteTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTipoIdentContratante
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTipoIdentContratanteTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentContratanteRequest request) throws ModelException {
        request.setTipoIdentificacionDelTipoIdentContratantepthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(new TipoIdentificacionOid().fromJsonString(request.getTipoIdentificacionDelTipoIdentContratantepthisTipoIdentificacion())));
        request.setTipoIdentificacionDelTipoIdentContratantepevcSolicitudNovedadInstance(solicitudNovedadSrv.getByOIDWithHV(request.getTipoIdentificacionDelTipoIdentContratantepevcSolicitudNovedad()));
        return tipoIdentificacionSrv.delTipoIdentContratanteImplementation(request, true);
    }

    /*
     * Implements service DelTipoIdentContratante
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTipoIdentContratanteImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentContratanteRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionDelTipoIdentContratantepthisTipoIdentificacion = request.getTipoIdentificacionDelTipoIdentContratantepthisTipoIdentificacionInstance();
        SolicitudNovedad tipoIdentificacionDelTipoIdentContratantepevcSolicitudNovedad = request.getTipoIdentificacionDelTipoIdentContratantepevcSolicitudNovedadInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionDelTipoIdentContratantepthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.solicitudnovedad.siu.DelTipoIdentContratanteRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.solicitudnovedad.siu.DelTipoIdentContratanteRequest();
            relatedRequest.setSolicitudNovedadDelTipoIdentContratantepthisSolicitudNovedadInstance(tipoIdentificacionDelTipoIdentContratantepevcSolicitudNovedad);
            relatedRequest.setSolicitudNovedadDelTipoIdentContratantepevcTipoIdentificacionInstance(tipoIdentificacionDelTipoIdentContratantepthisTipoIdentificacion);
            solicitudNovedadSrv.delTipoIdentContratanteImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: InsTipoIdentificacion

    /*
     * Entry point for service InsTipoIdentificacion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTipoIdentificacion(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentificacionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.insTipoIdentificacionTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTipoIdentificacion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTipoIdentificacionTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentificacionRequest request) throws ModelException {
        request.setTipoIdentificacionInsTipoIdentificacionpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(new TipoIdentificacionOid().fromJsonString(request.getTipoIdentificacionInsTipoIdentificacionpthisTipoIdentificacion())));
        request.setTipoIdentificacionInsTipoIdentificacionpevcFamiliaNovedadInstance(familiaNovedadSrv.getByOIDWithHV(request.getTipoIdentificacionInsTipoIdentificacionpevcFamiliaNovedad()));
        return tipoIdentificacionSrv.insTipoIdentificacionImplementation(request, true);
    }

    /*
     * Implements service InsTipoIdentificacion
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTipoIdentificacionImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentificacionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionInsTipoIdentificacionpthisTipoIdentificacion = request.getTipoIdentificacionInsTipoIdentificacionpthisTipoIdentificacionInstance();
        FamiliaNovedad tipoIdentificacionInsTipoIdentificacionpevcFamiliaNovedad = request.getTipoIdentificacionInsTipoIdentificacionpevcFamiliaNovedadInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionInsTipoIdentificacionpthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.familianovedad.siu.InsTipoIdentificacionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.familianovedad.siu.InsTipoIdentificacionRequest();
            relatedRequest.setFamiliaNovedadInsTipoIdentificacionpthisFamiliaNovedadInstance(tipoIdentificacionInsTipoIdentificacionpevcFamiliaNovedad);
            relatedRequest.setFamiliaNovedadInsTipoIdentificacionpevcTipoIdentificacionInstance(tipoIdentificacionInsTipoIdentificacionpthisTipoIdentificacion);
            familiaNovedadSrv.insTipoIdentificacionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: DelTipoIdentificacion

    /*
     * Entry point for service DelTipoIdentificacion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTipoIdentificacion(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentificacionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.delTipoIdentificacionTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTipoIdentificacion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTipoIdentificacionTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentificacionRequest request) throws ModelException {
        request.setTipoIdentificacionDelTipoIdentificacionpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(new TipoIdentificacionOid().fromJsonString(request.getTipoIdentificacionDelTipoIdentificacionpthisTipoIdentificacion())));
        request.setTipoIdentificacionDelTipoIdentificacionpevcFamiliaNovedadInstance(familiaNovedadSrv.getByOIDWithHV(request.getTipoIdentificacionDelTipoIdentificacionpevcFamiliaNovedad()));
        return tipoIdentificacionSrv.delTipoIdentificacionImplementation(request, true);
    }

    /*
     * Implements service DelTipoIdentificacion
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTipoIdentificacionImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentificacionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionDelTipoIdentificacionpthisTipoIdentificacion = request.getTipoIdentificacionDelTipoIdentificacionpthisTipoIdentificacionInstance();
        FamiliaNovedad tipoIdentificacionDelTipoIdentificacionpevcFamiliaNovedad = request.getTipoIdentificacionDelTipoIdentificacionpevcFamiliaNovedadInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionDelTipoIdentificacionpthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.familianovedad.siu.DelTipoIdentificacionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.familianovedad.siu.DelTipoIdentificacionRequest();
            relatedRequest.setFamiliaNovedadDelTipoIdentificacionpthisFamiliaNovedadInstance(tipoIdentificacionDelTipoIdentificacionpevcFamiliaNovedad);
            relatedRequest.setFamiliaNovedadDelTipoIdentificacionpevcTipoIdentificacionInstance(tipoIdentificacionDelTipoIdentificacionpthisTipoIdentificacion);
            familiaNovedadSrv.delTipoIdentificacionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: InsTipoIdentUsuNovedad

    /*
     * Entry point for service InsTipoIdentUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTipoIdentUsuNovedad(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentUsuNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.insTipoIdentUsuNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTipoIdentUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTipoIdentUsuNovedadTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentUsuNovedadRequest request) throws ModelException {
        request.setTipoIdentificacionInsTipoIdentUsuNovedadpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(new TipoIdentificacionOid().fromJsonString(request.getTipoIdentificacionInsTipoIdentUsuNovedadpthisTipoIdentificacion())));
        request.setTipoIdentificacionInsTipoIdentUsuNovedadpevcUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getTipoIdentificacionInsTipoIdentUsuNovedadpevcUsuarioNovedad()));
        return tipoIdentificacionSrv.insTipoIdentUsuNovedadImplementation(request, true);
    }

    /*
     * Implements service InsTipoIdentUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTipoIdentUsuNovedadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentUsuNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionInsTipoIdentUsuNovedadpthisTipoIdentificacion = request.getTipoIdentificacionInsTipoIdentUsuNovedadpthisTipoIdentificacionInstance();
        UsuarioNovedad tipoIdentificacionInsTipoIdentUsuNovedadpevcUsuarioNovedad = request.getTipoIdentificacionInsTipoIdentUsuNovedadpevcUsuarioNovedadInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionInsTipoIdentUsuNovedadpthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsTipoIdentUsuNovedadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsTipoIdentUsuNovedadRequest();
            relatedRequest.setUsuarioNovedadInsTipoIdentUsuNovedadpthisUsuarioNovedadInstance(tipoIdentificacionInsTipoIdentUsuNovedadpevcUsuarioNovedad);
            relatedRequest.setUsuarioNovedadInsTipoIdentUsuNovedadpevcTipoIdentificacionInstance(tipoIdentificacionInsTipoIdentUsuNovedadpthisTipoIdentificacion);
            usuarioNovedadSrv.insTipoIdentUsuNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: DelTipoIdentUsuNovedad

    /*
     * Entry point for service DelTipoIdentUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTipoIdentUsuNovedad(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentUsuNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.delTipoIdentUsuNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTipoIdentUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTipoIdentUsuNovedadTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentUsuNovedadRequest request) throws ModelException {
        request.setTipoIdentificacionDelTipoIdentUsuNovedadpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(new TipoIdentificacionOid().fromJsonString(request.getTipoIdentificacionDelTipoIdentUsuNovedadpthisTipoIdentificacion())));
        request.setTipoIdentificacionDelTipoIdentUsuNovedadpevcUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getTipoIdentificacionDelTipoIdentUsuNovedadpevcUsuarioNovedad()));
        return tipoIdentificacionSrv.delTipoIdentUsuNovedadImplementation(request, true);
    }

    /*
     * Implements service DelTipoIdentUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTipoIdentUsuNovedadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentUsuNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionDelTipoIdentUsuNovedadpthisTipoIdentificacion = request.getTipoIdentificacionDelTipoIdentUsuNovedadpthisTipoIdentificacionInstance();
        UsuarioNovedad tipoIdentificacionDelTipoIdentUsuNovedadpevcUsuarioNovedad = request.getTipoIdentificacionDelTipoIdentUsuNovedadpevcUsuarioNovedadInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionDelTipoIdentUsuNovedadpthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelTipoIdentUsuNovedadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelTipoIdentUsuNovedadRequest();
            relatedRequest.setUsuarioNovedadDelTipoIdentUsuNovedadpthisUsuarioNovedadInstance(tipoIdentificacionDelTipoIdentUsuNovedadpevcUsuarioNovedad);
            relatedRequest.setUsuarioNovedadDelTipoIdentUsuNovedadpevcTipoIdentificacionInstance(tipoIdentificacionDelTipoIdentUsuNovedadpthisTipoIdentificacion);
            usuarioNovedadSrv.delTipoIdentUsuNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: InsTipoIdCDUsuNovedad

    /*
     * Entry point for service InsTipoIdCDUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTipoIdCDUsuNovedad(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdCDUsuNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.insTipoIdCDUsuNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTipoIdCDUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTipoIdCDUsuNovedadTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdCDUsuNovedadRequest request) throws ModelException {
        request.setTipoIdentificacionInsTipoIdCDUsuNovedadpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(new TipoIdentificacionOid().fromJsonString(request.getTipoIdentificacionInsTipoIdCDUsuNovedadpthisTipoIdentificacion())));
        request.setTipoIdentificacionInsTipoIdCDUsuNovedadpevcUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getTipoIdentificacionInsTipoIdCDUsuNovedadpevcUsuarioNovedad()));
        return tipoIdentificacionSrv.insTipoIdCDUsuNovedadImplementation(request, true);
    }

    /*
     * Implements service InsTipoIdCDUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTipoIdCDUsuNovedadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdCDUsuNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionInsTipoIdCDUsuNovedadpthisTipoIdentificacion = request.getTipoIdentificacionInsTipoIdCDUsuNovedadpthisTipoIdentificacionInstance();
        UsuarioNovedad tipoIdentificacionInsTipoIdCDUsuNovedadpevcUsuarioNovedad = request.getTipoIdentificacionInsTipoIdCDUsuNovedadpevcUsuarioNovedadInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionInsTipoIdCDUsuNovedadpthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsTipoIdCDUsuNovedadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsTipoIdCDUsuNovedadRequest();
            relatedRequest.setUsuarioNovedadInsTipoIdCDUsuNovedadpthisUsuarioNovedadInstance(tipoIdentificacionInsTipoIdCDUsuNovedadpevcUsuarioNovedad);
            relatedRequest.setUsuarioNovedadInsTipoIdCDUsuNovedadpevcTipoIdentificacionInstance(tipoIdentificacionInsTipoIdCDUsuNovedadpthisTipoIdentificacion);
            usuarioNovedadSrv.insTipoIdCDUsuNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    // Service: DelTipoIdCDUsuNovedad

    /*
     * Entry point for service DelTipoIdCDUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTipoIdCDUsuNovedad(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdCDUsuNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIdentificacionSrv.delTipoIdCDUsuNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTipoIdCDUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTipoIdCDUsuNovedadTxn(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdCDUsuNovedadRequest request) throws ModelException {
        request.setTipoIdentificacionDelTipoIdCDUsuNovedadpthisTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(new TipoIdentificacionOid().fromJsonString(request.getTipoIdentificacionDelTipoIdCDUsuNovedadpthisTipoIdentificacion())));
        request.setTipoIdentificacionDelTipoIdCDUsuNovedadpevcUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getTipoIdentificacionDelTipoIdCDUsuNovedadpevcUsuarioNovedad()));
        return tipoIdentificacionSrv.delTipoIdCDUsuNovedadImplementation(request, true);
    }

    /*
     * Implements service DelTipoIdCDUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTipoIdCDUsuNovedadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdCDUsuNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIdentificacion tipoIdentificacionDelTipoIdCDUsuNovedadpthisTipoIdentificacion = request.getTipoIdentificacionDelTipoIdCDUsuNovedadpthisTipoIdentificacionInstance();
        UsuarioNovedad tipoIdentificacionDelTipoIdCDUsuNovedadpevcUsuarioNovedad = request.getTipoIdentificacionDelTipoIdCDUsuNovedadpevcUsuarioNovedadInstance();

        request.checkArguments();
        TipoIdentificacion instance = tipoIdentificacionDelTipoIdCDUsuNovedadpthisTipoIdentificacion;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelTipoIdCDUsuNovedadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelTipoIdCDUsuNovedadRequest();
            relatedRequest.setUsuarioNovedadDelTipoIdCDUsuNovedadpthisUsuarioNovedadInstance(tipoIdentificacionDelTipoIdCDUsuNovedadpevcUsuarioNovedad);
            relatedRequest.setUsuarioNovedadDelTipoIdCDUsuNovedadpevcTipoIdentificacionInstance(tipoIdentificacionDelTipoIdCDUsuNovedadpthisTipoIdentificacion);
            usuarioNovedadSrv.delTipoIdCDUsuNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIdentificacion());

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCTipoIdentificacion(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(TipoIdentificacionConstants.CLASS_NAME, TipoIdentificacionConstants.ATTR_FIELD_TIPOIDENTIFICACIONCODIGO, false), true);
    }


    private void applyOCOCTipoIdentificacion(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_TipoIdentificacion".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(TipoIdentificacionConstants.CLASS_NAME, TipoIdentificacionConstants.ATTR_NAME_TIPOIDENTIFICACIONNOMBRECORTO, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildTipoIdentificacionRelatedInstance(relatedInstanceJsonOid);
        }
        if (TipoIdentificacionConstants.ROLE_NAME_ASESORES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoIdentificacionRelatedInstance4Asesor(relatedInstanceJsonOid, remainingPath);
        }
        if (TipoIdentificacionConstants.ROLE_NAME_PERSONAS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoIdentificacionRelatedInstance4Persona(relatedInstanceJsonOid, remainingPath);
        }
        if (TipoIdentificacionConstants.ROLE_NAME_PERSONASC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoIdentificacionRelatedInstance4PersonaC(relatedInstanceJsonOid, remainingPath);
        }
        if (TipoIdentificacionConstants.ROLE_NAME_USUARIOS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoIdentificacionRelatedInstance4Usuario(relatedInstanceJsonOid, remainingPath);
        }
        if (TipoIdentificacionConstants.ROLE_NAME_CONTRATANTES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoIdentificacionRelatedInstance4Contratante(relatedInstanceJsonOid, remainingPath);
        }
        if (TipoIdentificacionConstants.ROLE_NAME_ANEXOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoIdentificacionRelatedInstance4AnexoC(relatedInstanceJsonOid, remainingPath);
        }
        if (TipoIdentificacionConstants.ROLE_NAME_TITULARES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoIdentificacionRelatedInstance4Titular(relatedInstanceJsonOid, remainingPath);
        }
        if (TipoIdentificacionConstants.ROLE_NAME_INSTITUCIONMATNEO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoIdentificacionRelatedInstance4InstitucionMatNeo(relatedInstanceJsonOid, remainingPath);
        }
        if (TipoIdentificacionConstants.ROLE_NAME_SOLICITUDESNOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoIdentificacionRelatedInstance4SolicitudNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (TipoIdentificacionConstants.ROLE_NAME_FAMILIASNOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoIdentificacionRelatedInstance4FamiliaNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (TipoIdentificacionConstants.ROLE_NAME_USUARIOSNOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem) || TipoIdentificacionConstants.ROLE_NAME_USUARIONOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoIdentificacionRelatedInstance4UsuarioNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (TipoIdentificacionConstants.ROLE_NAME_NOVEDADESCT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoIdentificacionRelatedInstance4NovedadCT(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildTipoIdentificacionRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new TipoIdentificacion(mapper.readValue(relatedInstanceJsonOid, TipoIdentificacionOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoIdentificacionRelatedInstance4Asesor(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Asesor(mapper.readValue(relatedInstanceJsonOid, AsesorOid.class)): Asesor.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoIdentificacionRelatedInstance4Persona(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Persona(mapper.readValue(relatedInstanceJsonOid, PersonaOid.class)): Persona.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoIdentificacionRelatedInstance4PersonaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PersonaC(mapper.readValue(relatedInstanceJsonOid, PersonaCOid.class)): PersonaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoIdentificacionRelatedInstance4Usuario(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Usuario(mapper.readValue(relatedInstanceJsonOid, UsuarioOid.class)): Usuario.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoIdentificacionRelatedInstance4Contratante(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Contratante(mapper.readValue(relatedInstanceJsonOid, ContratanteOid.class)): Contratante.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoIdentificacionRelatedInstance4AnexoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new AnexoC(mapper.readValue(relatedInstanceJsonOid, AnexoCOid.class)): AnexoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoIdentificacionRelatedInstance4Titular(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Titular(mapper.readValue(relatedInstanceJsonOid, TitularOid.class)): Titular.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoIdentificacionRelatedInstance4InstitucionMatNeo(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new InstitucionMatNeo(mapper.readValue(relatedInstanceJsonOid, InstitucionMatNeoOid.class)): InstitucionMatNeo.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoIdentificacionRelatedInstance4SolicitudNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new SolicitudNovedad(mapper.readValue(relatedInstanceJsonOid, SolicitudNovedadOid.class)): SolicitudNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoIdentificacionRelatedInstance4FamiliaNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new FamiliaNovedad(mapper.readValue(relatedInstanceJsonOid, FamiliaNovedadOid.class)): FamiliaNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoIdentificacionRelatedInstance4UsuarioNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new UsuarioNovedad(mapper.readValue(relatedInstanceJsonOid, UsuarioNovedadOid.class)): UsuarioNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoIdentificacionRelatedInstance4NovedadCT(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new NovedadCT(mapper.readValue(relatedInstanceJsonOid, NovedadCTOid.class)): NovedadCT.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            TipoIdentificacionOid oid = new TipoIdentificacionOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TipoIdentificacionConstants.ATTR_FIELD_TIPOIDENTIFICACIONCODIGO, Constants.Type.INT.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getTipoIdentificacionCodigo(), Constants.Type.INT.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(TipoIdentificacionService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
