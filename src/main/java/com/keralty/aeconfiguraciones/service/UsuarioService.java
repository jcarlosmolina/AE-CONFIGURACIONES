package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.AesolicitudesConstants;
import com.keralty.aeconfiguraciones.global.AsesorConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.SolicitudConstants;
import com.keralty.aeconfiguraciones.global.TipoIdentificacionConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.global.UsuarioConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.AnexoMat;
import com.keralty.aeconfiguraciones.persistence.AnexoNeo;
import com.keralty.aeconfiguraciones.persistence.Asesor;
import com.keralty.aeconfiguraciones.persistence.Companyia;
import com.keralty.aeconfiguraciones.persistence.CuestionarioP;
import com.keralty.aeconfiguraciones.persistence.Eps;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.oid.AnexoMatOid;
import com.keralty.aeconfiguraciones.persistence.oid.AnexoNeoOid;
import com.keralty.aeconfiguraciones.persistence.oid.CompanyiaOid;
import com.keralty.aeconfiguraciones.persistence.oid.CuestionarioPOid;
import com.keralty.aeconfiguraciones.persistence.oid.EpsOid;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioOid;
import com.keralty.aeconfiguraciones.persistence.oid.ParentescoOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaOid;
import com.keralty.aeconfiguraciones.persistence.oid.PreexistenciaOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoIdentificacionOid;
import com.keralty.aeconfiguraciones.persistence.oid.UsuarioOid;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.persistence.Persona;
import com.keralty.aeconfiguraciones.persistence.Preexistencia;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import com.keralty.aeconfiguraciones.repository.UsuarioJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathInstance;
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
public class UsuarioService extends AbstractService {

    UsuarioJpaRepository repository;

    UsuarioService usuarioSrv;

    EpsService epsSrv;

    ParentescoService parentescoSrv;

    CompanyiaService companyiaSrv;

    TipoIdentificacionService tipoIdentificacionSrv;

    EstadoBeneficiarioService estadoBeneficiarioSrv;

    SolicitudService solicitudSrv;

    CuestionarioPService cuestionarioPSrv;

    AnexoMatService anexoMatSrv;

    AnexoNeoService anexoNeoSrv;

    PreexistenciaService preexistenciaSrv;

    PersonaService personaSrv;

    UserFunctions userFunctionsSrv;

    @Inject
    public UsuarioService(
            UsuarioService usuarioSrv,
            EpsService epsSrv,
            ParentescoService parentescoSrv,
            CompanyiaService companyiaSrv,
            TipoIdentificacionService tipoIdentificacionSrv,
            EstadoBeneficiarioService estadoBeneficiarioSrv,
            SolicitudService solicitudSrv,
            CuestionarioPService cuestionarioPSrv,
            AnexoMatService anexoMatSrv,
            AnexoNeoService anexoNeoSrv,
            PreexistenciaService preexistenciaSrv,
            PersonaService personaSrv,
            UserFunctions userFunctionsSrv,
            UsuarioJpaRepository repository) {
        this.usuarioSrv = usuarioSrv;
        this.epsSrv = epsSrv;
        this.parentescoSrv = parentescoSrv;
        this.companyiaSrv = companyiaSrv;
        this.tipoIdentificacionSrv = tipoIdentificacionSrv;
        this.estadoBeneficiarioSrv = estadoBeneficiarioSrv;
        this.solicitudSrv = solicitudSrv;
        this.cuestionarioPSrv = cuestionarioPSrv;
        this.anexoMatSrv = anexoMatSrv;
        this.anexoNeoSrv = anexoNeoSrv;
        this.preexistenciaSrv = preexistenciaSrv;
        this.personaSrv = personaSrv;
        this.userFunctionsSrv = userFunctionsSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, UsuarioConstants.CLASS_NAME);
    }

    private List<Usuario> solveUsuarioTypedQuery(QueryRequest request) {
        boolean hasInMemoryFilters = hasInMemoryFilters(request);
        int requestedPageSize = request.getPageSize();
        int pageSize = requestedPageSize >= 0
                && requestedPageSize <= Constants.MAX_BLOCKSIZE
                ? requestedPageSize
                : Constants.DEFAULT_BLOCKSIZE;
        int pageNumber = Math.max(1, request.getPageNumber());

    	List<Usuario> results = repository.solveQuery(processQueryRequest(request, UsuarioConstants.CLASS_NAME), (hasInMemoryFilters ? 0 : pageSize), (hasInMemoryFilters ? 1 : pageNumber)).stream()
                .filter(instance -> hasInMemoryFilters ? applyMemoryFilters(request, instance) : true).collect(Collectors.toList()); 

        if (hasInMemoryFilters && results != null && !results.isEmpty()) {
            long fromInstance = ((long) pageNumber - 1) * pageSize;
            long toInstance = (long) pageNumber * pageSize;
            if (pageSize == 0) {
               fromInstance = 0;
               toInstance = results.size();
            }
            int from = (int) Math.min(fromInstance, results.size());
            int to = (int) Math.min(toInstance, results.size());
            results = (fromInstance > results.size() - 1)
                    ? new ArrayList<>()
                    : results.subList(from, Math.max(from, to));
        }

        return results;
    }
    
    private int solveUsuarioTypedCountQuery(QueryRequest request) {
        return hasInMemoryFilters(request)
               ? repository.solveQuery(processQueryRequest(request, UsuarioConstants.CLASS_NAME)).stream().filter(instance -> applyMemoryFilters(request, instance)).collect(Collectors.toList()).size()
               : repository.solveCountQuery(processCountQueryRequest(request, UsuarioConstants.CLASS_NAME));
    }

    private boolean hasInMemoryFilters(QueryRequest queryRequest) {
        for(FilterRequest filterRequest : queryRequest.getFilters()) {
            if (hasInMemoryConjunctions(filterRequest)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasInMemoryConjunctions(FilterRequest filterRequest) {
        if (UsuarioConstants.FILTER_NAME_IFUSUARIOSINCUESMED.equalsIgnoreCase(filterRequest.getName())) {
            return true;
        }
        return false;
    }

    /*
     * Returns the population of class Usuario
     *
     * @return Population of class Usuario
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Usuario> get() {
        List<Usuario> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class Usuario
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class Usuario
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<Usuario> instances = get(queryRequest);
        if(UsuarioConstants.DSICUSUARIO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICUsuario())).collect(Collectors.toList());
        }
        if(UsuarioConstants.DSDSUSUARIOPIUUSUARIOSCONT.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSUsuarioPIUUsuariosCont())).collect(Collectors.toList());
        }
        if(UsuarioConstants.DSDSUSUARIOPIUDESTATUSSOL.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSUsuarioPIUDeStatusSol())).collect(Collectors.toList());
        }
        if(UsuarioConstants.DSDSUSUARIOPOLIZAINTER.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSUsuarioPolizaInter())).collect(Collectors.toList());
        }
        if(UsuarioConstants.DSDSUSUARIONULL.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSUsuarioNUll())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class Usuario that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Usuario matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Usuario> get(QueryRequest queryRequest) {
        return solveUsuarioTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveUsuarioTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveUsuarioTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveUsuarioTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveUsuarioTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Usuario by ID
     *
     * @param aesolicitudesidsolicitud Identification field aesolicitudesidsolicitud
     *
     * @param usuarioIdentificador Identification field usuarioIdentificador
     *
     * @return Instance of Usuario
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Usuario getById(Long aesolicitudesidsolicitud, Long usuarioIdentificador) {
        return getByOID(new UsuarioOid(aesolicitudesidsolicitud, usuarioIdentificador));
    }
    
    /*
     * Returns an instance of Usuario by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Usuario
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Usuario getByOID(UsuarioOid oid) {
        Optional<Usuario> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Usuario by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Usuario
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Usuario getByOIDWithHV(UsuarioOid oid) {
        Optional<Usuario> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Usuario> getInstanceByOid(UsuarioOid oid) {
        return oid != null && oid.getAesolicitudesidsolicitud() != null
               ? repository.findById(oid)
               : Optional.empty();
    }

    /*
     * Returns an instance of Usuario by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Usuario
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Usuario getByJsonOid(String jsonOid) {
        UsuarioOid oid = new UsuarioOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Usuario related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Usuario
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Usuario> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveUsuarioTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Usuario related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Usuario
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Usuario> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Usuario
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Usuario
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getUsuarioDynamic(UsuarioOid oid, String displaySetItems) {
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
    public String getSuppInfo(UsuarioOid oid, String dsName) {
        Usuario instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Usuario instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (UsuarioConstants.DSICUSUARIO.equals(dsName))
            return instance.buildICUsuario();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(Usuario instance, String dsName) {
        if (UsuarioConstants.DSICUSUARIO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICUsuario());
        if (UsuarioConstants.DSDSUSUARIOPIUUSUARIOSCONT.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSUsuarioPIUUsuariosCont());
        if (UsuarioConstants.DSDSUSUARIOPIUDESTATUSSOL.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSUsuarioPIUDeStatusSol());
        if (UsuarioConstants.DSDSUSUARIOPOLIZAINTER.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSUsuarioPolizaInter());
        if (UsuarioConstants.DSDSUSUARIONULL.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSUsuarioNUll());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (UsuarioConstants.FILTER_NAME_IFUSUARIOSINCUESMED.equalsIgnoreCase(filterRequest.getName()))
            iFUsuarioSinCuesMed(filterRequest, sql);
        if (UsuarioConstants.FILTER_NAME_FUSUARIOSPOLIZAINTER.equalsIgnoreCase(filterRequest.getName()))
            fUsuariosPolizaInter(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_Usuario".equals(filterRequest.getName()))
            suppInfo4ICUsuario(filterRequest.getFilterVariable("searchText").toString(), sql);
    }

    private boolean applyMemoryFilters(QueryRequest queryRequest, Usuario instance) {
        for (FilterRequest filterRequest : queryRequest.getFilters()) {
            if (!applyMemoryFilter(filterRequest, instance))
                return false;
        }
        return true;
    }
    
    private boolean applyMemoryFilter(FilterRequest filterRequest, Usuario instance) {
        return applyMemoryQueryFilter(filterRequest, instance);
    }

    private boolean applyMemoryQueryFilter(FilterRequest filterRequest, Usuario entity) {
        if (UsuarioConstants.FILTER_NAME_IFUSUARIOSINCUESMED.equalsIgnoreCase(filterRequest.getName()))
            return inMemoryIFUsuarioSinCuesMed(entity);
        return true;
    }


    private void iFUsuarioSinCuesMed(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Solicitud voSolicitud = filterRequest.hasFilterVariable(UsuarioConstants.VAR_NAME_IFUSUARIOSINCUESMED_VOSOLICITUD) ? solicitudSrv.getByOID(new SolicitudOid().fromJsonString(toJson(filterRequest.getFilterVariable(UsuarioConstants.VAR_NAME_IFUSUARIOSINCUESMED_VOSOLICITUD)))) : null;

        // Solicitud = voSolicitud
        if (voSolicitud != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(UsuarioConstants.ROLE_NAME_SOLICITUD, SolicitudConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(UsuarioConstants.VAR_NAME_IFUSUARIOSINCUESMED_VOSOLICITUD, SolicitudConstants.CLASS_NAME, SolicitudConstants.CLASS_NAME, voSolicitud.getOid(), UsuarioConstants.VAR_NAME_IFUSUARIOSINCUESMED_VOSOLICITUD);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        sql.addConjunction(conjunction);
    }

    private void fUsuariosPolizaInter(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Date vfDesde = filterRequest.hasFilterVariable(UsuarioConstants.VAR_NAME_FUSUARIOSPOLIZAINTER_VFDESDE) ? (Date) filterRequest.getFilterVariable(UsuarioConstants.VAR_NAME_FUSUARIOSPOLIZAINTER_VFDESDE, FilterVariableRequest.DATE) : null;
        Date vfHasta = filterRequest.hasFilterVariable(UsuarioConstants.VAR_NAME_FUSUARIOSPOLIZAINTER_VFHASTA) ? (Date) filterRequest.getFilterVariable(UsuarioConstants.VAR_NAME_FUSUARIOSPOLIZAINTER_VFHASTA, FilterVariableRequest.DATE) : null;
        TipoIdentificacion vfTipoDoc = filterRequest.hasFilterVariable(UsuarioConstants.VAR_NAME_FUSUARIOSPOLIZAINTER_VFTIPODOC) ? tipoIdentificacionSrv.getByOID(new TipoIdentificacionOid().fromJsonString(toJson(filterRequest.getFilterVariable(UsuarioConstants.VAR_NAME_FUSUARIOSPOLIZAINTER_VFTIPODOC)))) : null;
        String vfNumIdent = filterRequest.hasFilterVariable(UsuarioConstants.VAR_NAME_FUSUARIOSPOLIZAINTER_VFNUMIDENT) ? (String) filterRequest.getFilterVariable(UsuarioConstants.VAR_NAME_FUSUARIOSPOLIZAINTER_VFNUMIDENT, FilterVariableRequest.STRING) : null;

        // Solicitud.Estado <> 4
        // '<>' Comparison
        SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
        comparison1.setOperator("<>");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(UsuarioConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ATTR_NAME_SOLICITUDESTADO, Constants.Type.INT.getTypeName());
        comparison1.setFirstOperand(operand112);
        
        // Operand 2
        comparison1.setSecondOperand(new SQLWhereOperandLiteral(Long.valueOf(4), Constants.Type.NAT.getTypeName()));
        conjunction.addOperand(comparison1);


        // PolizaInternacional = true
        // '=' Comparison
        SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
        comparison2.setOperator("=");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(UsuarioConstants.ATTR_NAME_USUARIOPOLIZAINTERNACIONAL, Constants.Type.BOOL.getTypeName());
        comparison2.setFirstOperand(operand113);
        
        // Operand 2
        comparison2.setSecondOperand(new SQLWhereOperandLiteral(Boolean.TRUE, Constants.Type.BOOL.getTypeName()));
        conjunction.addOperand(comparison2);


        // Solicitud.Aesolicitudes.Fechaasignacion >= vfDesde
        if (vfDesde != null) {
            // '>=' Comparison
            SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
            comparison3.setOperator(">=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(UsuarioConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AESOLICITUDES + "." + AesolicitudesConstants.ATTR_NAME_AESOLICITUDESFECHAASIGNACION, Constants.Type.DATE.getTypeName());
            comparison3.setFirstOperand(operand114);
            
            // Operand 2
            comparison3.setSecondOperand(new SQLWhereOperandLiteral(vfDesde, Constants.Type.DATE.getTypeName()));
            conjunction.addOperand(comparison3);

        }        

        // Solicitud.Aesolicitudes.Fechaasignacion <= vfHasta
        if (vfHasta != null) {
            // '<=' Comparison
            SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
            comparison4.setOperator("<=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(UsuarioConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AESOLICITUDES + "." + AesolicitudesConstants.ATTR_NAME_AESOLICITUDESFECHAASIGNACION, Constants.Type.DATE.getTypeName());
            comparison4.setFirstOperand(operand115);
            
            // Operand 2
            comparison4.setSecondOperand(new SQLWhereOperandLiteral(vfHasta, Constants.Type.DATE.getTypeName()));
            conjunction.addOperand(comparison4);

        }        

        // TipoIdentificacion = vfTipoDoc
        if (vfTipoDoc != null) {
            // '=' Comparison
            SQLWhereComparison comparison5 = new SQLWhereComparison(sql);
            comparison5.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand116 = new SQLWhereOperandPathInstance(UsuarioConstants.ROLE_NAME_TIPOIDENTIFICACION, TipoIdentificacionConstants.CLASS_NAME);
            comparison5.setFirstOperand(operand116);
            
            // Operand 2
            SQLWhereOperandOVParameter operand227 = new SQLWhereOperandOVParameter(UsuarioConstants.VAR_NAME_FUSUARIOSPOLIZAINTER_VFTIPODOC, TipoIdentificacionConstants.CLASS_NAME, TipoIdentificacionConstants.CLASS_NAME, vfTipoDoc.getOid(), UsuarioConstants.VAR_NAME_FUSUARIOSPOLIZAINTER_VFTIPODOC);
            comparison5.setSecondOperand(operand227);
            conjunction.addOperand(comparison5);

        }        

        // NumIdentificacion LIKE "%" + vfNumIdent
        if (vfNumIdent != null) {
            // 'like' Comparison
            SQLWhereComparison comparison6 = new SQLWhereComparison(sql);
            comparison6.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand117 = new SQLWhereOperandPathAttribute(UsuarioConstants.ATTR_NAME_USUARIONUMIDENTIFICACION, Constants.Type.STRING.getTypeName());
            comparison6.setFirstOperand(operand117);
            
            // Operand 2
            comparison6.setSecondOperand(new SQLWhereOperandLiteral("%" + vfNumIdent, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison6);

        }        

        sql.addConjunction(conjunction);
    }

    private boolean inMemoryIFUsuarioSinCuesMed(Usuario entity) {
        boolean result = true;

        // drvCuesMedOK = false
        try {
            result = entity.getUsuariodrvCuesMedOK().equals(false);
        } catch(Exception e) {
           LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
        return result;
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUUsuariosDeContrato
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Usuario using displayset DSUsuarioPIUUsuariosCont that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.usuario.ds.DSUsuarioPIUUsuariosCont> piuPIUUsuariosDeContrato(QueryRequest queryRequest) {
        return solveUsuarioTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.usuario.ds.DSUsuarioPIUUsuariosCont::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUUsuariosDeContrato
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUUsuariosDeContrato
     */
    public int getCount4PIUUsuariosDeContrato(QueryRequest queryRequest) {
        return solveUsuarioTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUUsuarioDePIUStatusSol
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Usuario using displayset DSUsuarioPIUDeStatusSol that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.usuario.ds.DSUsuarioPIUDeStatusSol> piuPIUUsuarioDePIUStatusSol(QueryRequest queryRequest) {
        return solveUsuarioTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.usuario.ds.DSUsuarioPIUDeStatusSol::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUUsuarioDePIUStatusSol
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUUsuarioDePIUStatusSol
     */
    public int getCount4PIUUsuarioDePIUStatusSol(QueryRequest queryRequest) {
        return solveUsuarioTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUUsuariosPolInternacional
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Usuario using displayset DSUsuarioPolizaInter that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.usuario.ds.DSUsuarioPolizaInter> piuPIUUsuariosPolInternacional(QueryRequest queryRequest) {
        return solveUsuarioTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.usuario.ds.DSUsuarioPolizaInter::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUUsuariosPolInternacional
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUUsuariosPolInternacional
     */
    public int getCount4PIUUsuariosPolInternacional(QueryRequest queryRequest) {
        return solveUsuarioTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUUsuarioNull
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Usuario using displayset DSUsuarioNUll that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.usuario.ds.DSUsuarioNUll> piuPIUUsuarioNull(QueryRequest queryRequest) {
        return solveUsuarioTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.usuario.ds.DSUsuarioNUll::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUUsuarioNull
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUUsuarioNull
     */
    public int getCount4PIUUsuarioNull(QueryRequest queryRequest) {
        return solveUsuarioTypedQuery(queryRequest).size();
    }

    // Service: InsEPSUsuario

    /*
     * Entry point for service InsEPSUsuario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insEPSUsuario(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsEPSUsuarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioSrv.insEPSUsuarioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsEPSUsuario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insEPSUsuarioTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsEPSUsuarioRequest request) throws ModelException {
        request.setUsuarioInsEPSUsuariopthisAttrUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getUsuarioInsEPSUsuariopthisAttrUsuario()));
        request.setUsuarioInsEPSUsuariopevcEpsInstance(epsSrv.getByOIDWithHV(request.getUsuarioInsEPSUsuariopevcEps()));
        return usuarioSrv.insEPSUsuarioImplementation(request, true);
    }

    /*
     * Implements service InsEPSUsuario
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insEPSUsuarioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsEPSUsuarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Usuario usuarioInsEPSUsuariopthisAttrUsuario = request.getUsuarioInsEPSUsuariopthisAttrUsuarioInstance();
        Eps usuarioInsEPSUsuariopevcEps = request.getUsuarioInsEPSUsuariopevcEpsInstance();

        request.checkArguments();
        Usuario instance = usuarioInsEPSUsuariopthisAttrUsuario;

        instance.add2Eps(usuarioInsEPSUsuariopevcEps);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.eps.siu.InsEPSUsuarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.eps.siu.InsEPSUsuarioRequest();
            relatedRequest.setEpsInsEPSUsuariopthisEPSInstance(usuarioInsEPSUsuariopevcEps);
            relatedRequest.setEpsInsEPSUsuariopevcUsuarioInstance(usuarioInsEPSUsuariopthisAttrUsuario);
            epsSrv.insEPSUsuarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuario());

        return response;
    }

    // Service: InsParentescoUsuario

    /*
     * Entry point for service InsParentescoUsuario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insParentescoUsuario(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsParentescoUsuarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioSrv.insParentescoUsuarioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsParentescoUsuario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insParentescoUsuarioTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsParentescoUsuarioRequest request) throws ModelException {
        request.setUsuarioInsParentescoUsuariopthisAttrUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getUsuarioInsParentescoUsuariopthisAttrUsuario()));
        request.setUsuarioInsParentescoUsuariopevcParentescoInstance(parentescoSrv.getByOIDWithHV(request.getUsuarioInsParentescoUsuariopevcParentesco()));
        return usuarioSrv.insParentescoUsuarioImplementation(request, true);
    }

    /*
     * Implements service InsParentescoUsuario
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insParentescoUsuarioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsParentescoUsuarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Usuario usuarioInsParentescoUsuariopthisAttrUsuario = request.getUsuarioInsParentescoUsuariopthisAttrUsuarioInstance();
        Parentesco usuarioInsParentescoUsuariopevcParentesco = request.getUsuarioInsParentescoUsuariopevcParentescoInstance();

        request.checkArguments();
        Usuario instance = usuarioInsParentescoUsuariopthisAttrUsuario;

        instance.add2Parentesco(usuarioInsParentescoUsuariopevcParentesco);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.InsParentescoUsuarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.InsParentescoUsuarioRequest();
            relatedRequest.setParentescoInsParentescoUsuariopthisParentescoInstance(usuarioInsParentescoUsuariopevcParentesco);
            relatedRequest.setParentescoInsParentescoUsuariopevcUsuarioInstance(usuarioInsParentescoUsuariopthisAttrUsuario);
            parentescoSrv.insParentescoUsuarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuario());

        return response;
    }

    // Service: InsCompanyiaUsuario

    /*
     * Entry point for service InsCompanyiaUsuario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insCompanyiaUsuario(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsCompanyiaUsuarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioSrv.insCompanyiaUsuarioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsCompanyiaUsuario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insCompanyiaUsuarioTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsCompanyiaUsuarioRequest request) throws ModelException {
        request.setUsuarioInsCompanyiaUsuariopthisAttrUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getUsuarioInsCompanyiaUsuariopthisAttrUsuario()));
        request.setUsuarioInsCompanyiaUsuariopevcCompanyiaInstance(companyiaSrv.getByOIDWithHV(request.getUsuarioInsCompanyiaUsuariopevcCompanyia()));
        return usuarioSrv.insCompanyiaUsuarioImplementation(request, true);
    }

    /*
     * Implements service InsCompanyiaUsuario
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insCompanyiaUsuarioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsCompanyiaUsuarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Usuario usuarioInsCompanyiaUsuariopthisAttrUsuario = request.getUsuarioInsCompanyiaUsuariopthisAttrUsuarioInstance();
        Companyia usuarioInsCompanyiaUsuariopevcCompanyia = request.getUsuarioInsCompanyiaUsuariopevcCompanyiaInstance();

        request.checkArguments();
        Usuario instance = usuarioInsCompanyiaUsuariopthisAttrUsuario;

        instance.add2Companyia(usuarioInsCompanyiaUsuariopevcCompanyia);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.InsCompanyiaUsuarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.companyia.siu.InsCompanyiaUsuarioRequest();
            relatedRequest.setCompanyiaInsCompanyiaUsuariopthisCompanyiaInstance(usuarioInsCompanyiaUsuariopevcCompanyia);
            relatedRequest.setCompanyiaInsCompanyiaUsuariopevcUsuarioInstance(usuarioInsCompanyiaUsuariopthisAttrUsuario);
            companyiaSrv.insCompanyiaUsuarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuario());

        return response;
    }

    // Service: DelCompanyiaUsuario

    /*
     * Entry point for service DelCompanyiaUsuario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delCompanyiaUsuario(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelCompanyiaUsuarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioSrv.delCompanyiaUsuarioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelCompanyiaUsuario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delCompanyiaUsuarioTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelCompanyiaUsuarioRequest request) throws ModelException {
        request.setUsuarioDelCompanyiaUsuariopthisAttrUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getUsuarioDelCompanyiaUsuariopthisAttrUsuario()));
        request.setUsuarioDelCompanyiaUsuariopevcCompanyiaInstance(companyiaSrv.getByOIDWithHV(request.getUsuarioDelCompanyiaUsuariopevcCompanyia()));
        return usuarioSrv.delCompanyiaUsuarioImplementation(request, true);
    }

    /*
     * Implements service DelCompanyiaUsuario
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delCompanyiaUsuarioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelCompanyiaUsuarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Usuario usuarioDelCompanyiaUsuariopthisAttrUsuario = request.getUsuarioDelCompanyiaUsuariopthisAttrUsuarioInstance();
        Companyia usuarioDelCompanyiaUsuariopevcCompanyia = request.getUsuarioDelCompanyiaUsuariopevcCompanyiaInstance();

        request.checkArguments();
        Usuario instance = usuarioDelCompanyiaUsuariopthisAttrUsuario;

        instance.add2Companyia(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.DelCompanyiaUsuarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.companyia.siu.DelCompanyiaUsuarioRequest();
            relatedRequest.setCompanyiaDelCompanyiaUsuariopthisCompanyiaInstance(usuarioDelCompanyiaUsuariopevcCompanyia);
            relatedRequest.setCompanyiaDelCompanyiaUsuariopevcUsuarioInstance(usuarioDelCompanyiaUsuariopthisAttrUsuario);
            companyiaSrv.delCompanyiaUsuarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuario());

        return response;
    }

    // Service: DelParentescorUsuario

    /*
     * Entry point for service DelParentescorUsuario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delParentescorUsuario(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelParentescorUsuarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioSrv.delParentescorUsuarioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelParentescorUsuario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delParentescorUsuarioTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelParentescorUsuarioRequest request) throws ModelException {
        request.setUsuarioDelParentescorUsuariopthisAttrUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getUsuarioDelParentescorUsuariopthisAttrUsuario()));
        request.setUsuarioDelParentescorUsuariopevcParentescoInstance(parentescoSrv.getByOIDWithHV(request.getUsuarioDelParentescorUsuariopevcParentesco()));
        return usuarioSrv.delParentescorUsuarioImplementation(request, true);
    }

    /*
     * Implements service DelParentescorUsuario
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delParentescorUsuarioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelParentescorUsuarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Usuario usuarioDelParentescorUsuariopthisAttrUsuario = request.getUsuarioDelParentescorUsuariopthisAttrUsuarioInstance();
        Parentesco usuarioDelParentescorUsuariopevcParentesco = request.getUsuarioDelParentescorUsuariopevcParentescoInstance();

        request.checkArguments();
        Usuario instance = usuarioDelParentescorUsuariopthisAttrUsuario;

        instance.add2Parentesco(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.DelParentescorUsuarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.DelParentescorUsuarioRequest();
            relatedRequest.setParentescoDelParentescorUsuariopthisParentescoInstance(usuarioDelParentescorUsuariopevcParentesco);
            relatedRequest.setParentescoDelParentescorUsuariopevcUsuarioInstance(usuarioDelParentescorUsuariopthisAttrUsuario);
            parentescoSrv.delParentescorUsuarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuario());

        return response;
    }

    // Service: DelEPSUsuario

    /*
     * Entry point for service DelEPSUsuario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delEPSUsuario(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelEPSUsuarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioSrv.delEPSUsuarioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelEPSUsuario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delEPSUsuarioTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelEPSUsuarioRequest request) throws ModelException {
        request.setUsuarioDelEPSUsuariopthisAttrUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getUsuarioDelEPSUsuariopthisAttrUsuario()));
        request.setUsuarioDelEPSUsuariopevcEpsInstance(epsSrv.getByOIDWithHV(request.getUsuarioDelEPSUsuariopevcEps()));
        return usuarioSrv.delEPSUsuarioImplementation(request, true);
    }

    /*
     * Implements service DelEPSUsuario
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delEPSUsuarioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelEPSUsuarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Usuario usuarioDelEPSUsuariopthisAttrUsuario = request.getUsuarioDelEPSUsuariopthisAttrUsuarioInstance();
        Eps usuarioDelEPSUsuariopevcEps = request.getUsuarioDelEPSUsuariopevcEpsInstance();

        request.checkArguments();
        Usuario instance = usuarioDelEPSUsuariopthisAttrUsuario;

        instance.add2Eps(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.eps.siu.DelEPSUsuarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.eps.siu.DelEPSUsuarioRequest();
            relatedRequest.setEpsDelEPSUsuariopthisEPSInstance(usuarioDelEPSUsuariopevcEps);
            relatedRequest.setEpsDelEPSUsuariopevcUsuarioInstance(usuarioDelEPSUsuariopthisAttrUsuario);
            epsSrv.delEPSUsuarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuario());

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
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.CambTipoIdUsuarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioSrv.cambTipoIdUsuarioTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.CambTipoIdUsuarioRequest request) throws ModelException {
        request.setUsuarioCambTipoIdUsuariopthisUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getUsuarioCambTipoIdUsuariopthisUsuario()));
        request.setUsuarioCambTipoIdUsuariopevcTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getUsuarioCambTipoIdUsuariopevcTipoIdentificacion()));
        return usuarioSrv.cambTipoIdUsuarioImplementation(request, true);
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
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.CambTipoIdUsuarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Usuario usuarioCambTipoIdUsuariopthisUsuario = request.getUsuarioCambTipoIdUsuariopthisUsuarioInstance();
        TipoIdentificacion usuarioCambTipoIdUsuariopevcTipoIdentificacion = request.getUsuarioCambTipoIdUsuariopevcTipoIdentificacionInstance();

        request.checkArguments();
        Usuario instance = usuarioCambTipoIdUsuariopthisUsuario;

        instance.add2TipoIdentificacion(usuarioCambTipoIdUsuariopevcTipoIdentificacion);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdUsuarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdUsuarioRequest();
            relatedRequest.setTipoIdentificacionCambTipoIdUsuariopthisTipoIdentificacionInstance(usuarioCambTipoIdUsuariopevcTipoIdentificacion);
            relatedRequest.setTipoIdentificacionCambTipoIdUsuariopevcUsuarioInstance(usuarioCambTipoIdUsuariopthisUsuario);
            tipoIdentificacionSrv.cambTipoIdUsuarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuario());

        return response;
    }

    // Service: InsEstadoBeneficiarioCORE

    /*
     * Entry point for service InsEstadoBeneficiarioCORE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insEstadoBeneficiarioCORE(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsEstadoBeneficiarioCORERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioSrv.insEstadoBeneficiarioCORETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsEstadoBeneficiarioCORE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insEstadoBeneficiarioCORETxn(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsEstadoBeneficiarioCORERequest request) throws ModelException {
        request.setUsuarioInsEstadoBeneficiarioCOREpthisUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getUsuarioInsEstadoBeneficiarioCOREpthisUsuario()));
        request.setUsuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getUsuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario()));
        return usuarioSrv.insEstadoBeneficiarioCOREImplementation(request, true);
    }

    /*
     * Implements service InsEstadoBeneficiarioCORE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insEstadoBeneficiarioCOREImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsEstadoBeneficiarioCORERequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Usuario usuarioInsEstadoBeneficiarioCOREpthisUsuario = request.getUsuarioInsEstadoBeneficiarioCOREpthisUsuarioInstance();
        EstadoBeneficiario usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario = request.getUsuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance();

        request.checkArguments();
        Usuario instance = usuarioInsEstadoBeneficiarioCOREpthisUsuario;

        instance.add2EstadoBeneficiario(usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioCORERequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioCORERequest();
            relatedRequest.setEstadoBeneficiarioInsEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance(usuarioInsEstadoBeneficiarioCOREpevcEstadoBeneficiario);
            relatedRequest.setEstadoBeneficiarioInsEstadoBeneficiarioCOREpevcUsuarioInstance(usuarioInsEstadoBeneficiarioCOREpthisUsuario);
            estadoBeneficiarioSrv.insEstadoBeneficiarioCOREImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuario());

        return response;
    }

    // Service: DelEstadoBeneficiarioCORE

    /*
     * Entry point for service DelEstadoBeneficiarioCORE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delEstadoBeneficiarioCORE(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelEstadoBeneficiarioCORERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioSrv.delEstadoBeneficiarioCORETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelEstadoBeneficiarioCORE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delEstadoBeneficiarioCORETxn(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelEstadoBeneficiarioCORERequest request) throws ModelException {
        request.setUsuarioDelEstadoBeneficiarioCOREpthisUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getUsuarioDelEstadoBeneficiarioCOREpthisUsuario()));
        request.setUsuarioDelEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getUsuarioDelEstadoBeneficiarioCOREpevcEstadoBeneficiario()));
        return usuarioSrv.delEstadoBeneficiarioCOREImplementation(request, true);
    }

    /*
     * Implements service DelEstadoBeneficiarioCORE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delEstadoBeneficiarioCOREImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelEstadoBeneficiarioCORERequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Usuario usuarioDelEstadoBeneficiarioCOREpthisUsuario = request.getUsuarioDelEstadoBeneficiarioCOREpthisUsuarioInstance();
        EstadoBeneficiario usuarioDelEstadoBeneficiarioCOREpevcEstadoBeneficiario = request.getUsuarioDelEstadoBeneficiarioCOREpevcEstadoBeneficiarioInstance();

        request.checkArguments();
        Usuario instance = usuarioDelEstadoBeneficiarioCOREpthisUsuario;

        instance.add2EstadoBeneficiario(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.DelEstadoBeneficiarioCORERequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.DelEstadoBeneficiarioCORERequest();
            relatedRequest.setEstadoBeneficiarioDelEstadoBeneficiarioCOREpthisEstadoBeneficiarioInstance(usuarioDelEstadoBeneficiarioCOREpevcEstadoBeneficiario);
            relatedRequest.setEstadoBeneficiarioDelEstadoBeneficiarioCOREpevcUsuarioInstance(usuarioDelEstadoBeneficiarioCOREpthisUsuario);
            estadoBeneficiarioSrv.delEstadoBeneficiarioCOREImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuario());

        return response;
    }


    @Override
    protected void applyHVFilter(SQLSelect sql) throws SystemException {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        SQLWhereDisjunction disjunction = new SQLWhereDisjunction();
        if (getAgentRoles().contains(AsesorConstants.CLASS_NAME)) applyHVFilter4Asesor(sql, disjunction);
        conjunction.addDisjunction(disjunction);
        sql.addConjunction(conjunction);
    }

    private void applyHVFilter4Asesor(SQLSelect sql, SQLWhereDisjunction disjunction) {
        // Solicitud.Agente = agent OR Solicitud.Agente.CodJefe = Agent.Usuario
        Asesor agentInstance = getAgentAsesor();
    {
        // 'or' Logical operator
        SQLWhereDisjunction comparison1 = new SQLWhereDisjunction();
    
        // Operand 1
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(UsuarioConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AGENTE, FuncionarioConstants.CLASS_NAME);
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            SQLWhereOperandOVParameter operand224 = new SQLWhereOperandOVParameter(Constants.AGENTKEYWORD, AsesorConstants.CLASS_NAME, AsesorConstants.CLASS_NAME, agentInstance.getOid(), Constants.AGENTKEYWORD);
            comparison2.setSecondOperand(operand224);
            comparison1.addOperand(comparison2);
    
        // Operand 2
            // '=' Comparison
            SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
            comparison4.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(UsuarioConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AGENTE + "." + FuncionarioConstants.ATTR_NAME_FUNCIONARIOCODJEFE, Constants.Type.STRING.getTypeName());
            comparison4.setFirstOperand(operand115);
            
            // Operand 2
            comparison4.setSecondOperand(new SQLWhereOperandLiteral(agentInstance.getFuncionarioUsuario(), Constants.Type.STRING.getTypeName()));
            comparison1.addOperand(comparison4);
        
        disjunction.addOperand(comparison1);
    }

    }


    private void suppInfo4ICUsuario(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(UsuarioConstants.ROLE_NAME_TIPOIDENTIFICACION + "." + TipoIdentificacionConstants.ATTR_NAME_TIPOIDENTIFICACIONNOMBRECORTO, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison1);

            SQLWhereComparison comparison2 = new SQLWhereComparison(select);
            comparison2.setOperator("LIKE");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(UsuarioConstants.ATTR_NAME_USUARIONUMIDENTIFICACION, Constants.Type.STRING.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison2);

            SQLWhereConjunction conjunction = new SQLWhereConjunction();
            conjunction.addDisjunction(disjunction);
            select.addConjunction(conjunction);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCPorId(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(UsuarioConstants.CLASS_NAME, UsuarioConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AESOLICITUDES + "." + AesolicitudesConstants.ATTR_FIELD_AESOLICITUDESIDSOLICITUD, false), true);
        select.getOrderByPart().addItem(Constants.normalizePath(UsuarioConstants.CLASS_NAME, UsuarioConstants.ATTR_FIELD_USUARIOIDENTIFICADOR, false), true);
    }


    private void applyOCOCPorId(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_PorId".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(UsuarioConstants.CLASS_NAME, UsuarioConstants.ATTR_NAME_USUARIOIDENTIFICADOR, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildUsuarioRelatedInstance(relatedInstanceJsonOid);
        }
        if (UsuarioConstants.ROLE_NAME_CUESTIONARIOP.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildUsuarioRelatedInstance4CuestionarioP(relatedInstanceJsonOid, remainingPath);
        }
        if (UsuarioConstants.ROLE_NAME_ANEXOMAT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildUsuarioRelatedInstance4AnexoMat(relatedInstanceJsonOid, remainingPath);
        }
        if (UsuarioConstants.ROLE_NAME_ANEXONEO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildUsuarioRelatedInstance4AnexoNeo(relatedInstanceJsonOid, remainingPath);
        }
        if (UsuarioConstants.ROLE_NAME_PREEXISTENCIAS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildUsuarioRelatedInstance4Preexistencia(relatedInstanceJsonOid, remainingPath);
        }
        if (UsuarioConstants.ROLE_NAME_SOLICITUD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildUsuarioRelatedInstance4Solicitud(relatedInstanceJsonOid, remainingPath);
        }
        if (UsuarioConstants.ROLE_NAME_USUARIOPERSONA.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildUsuarioRelatedInstance4Persona(relatedInstanceJsonOid, remainingPath);
        }
        if (UsuarioConstants.ROLE_NAME_EPS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildUsuarioRelatedInstance4Eps(relatedInstanceJsonOid, remainingPath);
        }
        if (UsuarioConstants.ROLE_NAME_PARENTESCO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildUsuarioRelatedInstance4Parentesco(relatedInstanceJsonOid, remainingPath);
        }
        if (UsuarioConstants.ROLE_NAME_COMPANYIA.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildUsuarioRelatedInstance4Companyia(relatedInstanceJsonOid, remainingPath);
        }
        if (UsuarioConstants.ROLE_NAME_TIPOIDENTIFICACION.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildUsuarioRelatedInstance4TipoIdentificacion(relatedInstanceJsonOid, remainingPath);
        }
        if (UsuarioConstants.ROLE_NAME_ESTADOBENEFICIARIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildUsuarioRelatedInstance4EstadoBeneficiario(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildUsuarioRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Usuario(mapper.readValue(relatedInstanceJsonOid, UsuarioOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildUsuarioRelatedInstance4CuestionarioP(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new CuestionarioP(mapper.readValue(relatedInstanceJsonOid, CuestionarioPOid.class)): CuestionarioP.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildUsuarioRelatedInstance4AnexoMat(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new AnexoMat(mapper.readValue(relatedInstanceJsonOid, AnexoMatOid.class)): AnexoMat.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildUsuarioRelatedInstance4AnexoNeo(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new AnexoNeo(mapper.readValue(relatedInstanceJsonOid, AnexoNeoOid.class)): AnexoNeo.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildUsuarioRelatedInstance4Preexistencia(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Preexistencia(mapper.readValue(relatedInstanceJsonOid, PreexistenciaOid.class)): Preexistencia.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildUsuarioRelatedInstance4Solicitud(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Solicitud(mapper.readValue(relatedInstanceJsonOid, SolicitudOid.class)): Solicitud.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildUsuarioRelatedInstance4Persona(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Persona(mapper.readValue(relatedInstanceJsonOid, PersonaOid.class)): Persona.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildUsuarioRelatedInstance4Eps(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Eps(mapper.readValue(relatedInstanceJsonOid, EpsOid.class)): Eps.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildUsuarioRelatedInstance4Parentesco(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Parentesco(mapper.readValue(relatedInstanceJsonOid, ParentescoOid.class)): Parentesco.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildUsuarioRelatedInstance4Companyia(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Companyia(mapper.readValue(relatedInstanceJsonOid, CompanyiaOid.class)): Companyia.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildUsuarioRelatedInstance4TipoIdentificacion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoIdentificacion(mapper.readValue(relatedInstanceJsonOid, TipoIdentificacionOid.class)): TipoIdentificacion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildUsuarioRelatedInstance4EstadoBeneficiario(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new EstadoBeneficiario(mapper.readValue(relatedInstanceJsonOid, EstadoBeneficiarioOid.class)): EstadoBeneficiario.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            UsuarioOid oid = new UsuarioOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(UsuarioConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AESOLICITUDES + "." + AesolicitudesConstants.ATTR_FIELD_AESOLICITUDESIDSOLICITUD, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getAesolicitudesidsolicitud(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(UsuarioConstants.ATTR_FIELD_USUARIOIDENTIFICADOR, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(oid.getUsuarioIdentificador(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison2);
        } catch (Exception e) {
            LoggerFactory.getLogger(UsuarioService.class).error(e.getMessage());
        }
    }

}
