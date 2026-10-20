package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.DireccionConstants;
import com.keralty.aeconfiguraciones.global.PersonaConstants;
import com.keralty.aeconfiguraciones.global.SolicitudConstants;
import com.keralty.aeconfiguraciones.global.TipoDireccionConstants;
import com.keralty.aeconfiguraciones.global.UsuarioConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.Direccion;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;
import com.keralty.aeconfiguraciones.persistence.oid.DireccionOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PaisOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoDireccionOid;
import com.keralty.aeconfiguraciones.persistence.oid.TitularOid;
import com.keralty.aeconfiguraciones.persistence.oid.UsuarioOid;
import com.keralty.aeconfiguraciones.persistence.Pais;
import com.keralty.aeconfiguraciones.persistence.Persona;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.TipoDireccion;
import com.keralty.aeconfiguraciones.persistence.Titular;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import com.keralty.aeconfiguraciones.repository.DireccionJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandNull;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class DireccionService extends AbstractService {

    DireccionJpaRepository repository;

    DireccionService direccionSrv;

    TipoDireccionService tipoDireccionSrv;

    MunicipioService municipioSrv;

    DepartamentoService departamentoSrv;

    PaisService paisSrv;

    PersonaService personaSrv;

    SolicitudService solicitudSrv;

    UsuarioService usuarioSrv;

    TitularService titularSrv;

    @Inject
    public DireccionService(
            DireccionService direccionSrv,
            TipoDireccionService tipoDireccionSrv,
            MunicipioService municipioSrv,
            DepartamentoService departamentoSrv,
            PaisService paisSrv,
            PersonaService personaSrv,
            SolicitudService solicitudSrv,
            UsuarioService usuarioSrv,
            TitularService titularSrv,
            DireccionJpaRepository repository) {
        this.direccionSrv = direccionSrv;
        this.tipoDireccionSrv = tipoDireccionSrv;
        this.municipioSrv = municipioSrv;
        this.departamentoSrv = departamentoSrv;
        this.paisSrv = paisSrv;
        this.personaSrv = personaSrv;
        this.solicitudSrv = solicitudSrv;
        this.usuarioSrv = usuarioSrv;
        this.titularSrv = titularSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, DireccionConstants.CLASS_NAME);
    }

    private List<Direccion> solveDireccionTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, DireccionConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveDireccionTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, DireccionConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Direccion
     *
     * @return Population of class Direccion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Direccion> get() {
        List<Direccion> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class Direccion
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class Direccion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<Direccion> instances = get(queryRequest);
        if(DireccionConstants.DSICDIRECCION.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICDireccion())).collect(Collectors.toList());
        }
        if(DireccionConstants.DSDSDIRECCIONNULL.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSDireccionNull())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class Direccion that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Direccion matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Direccion> get(QueryRequest queryRequest) {
        return solveDireccionTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveDireccionTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveDireccionTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveDireccionTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveDireccionTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Direccion by ID
     *
     * @param direccionIdentificador Identification field direccionIdentificador
     *
     * @return Instance of Direccion
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Direccion getById(Long direccionIdentificador) {
        return getByOID(new DireccionOid(direccionIdentificador));
    }
    
    /*
     * Returns an instance of Direccion by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Direccion
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Direccion getByOID(DireccionOid oid) {
        Optional<Direccion> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Direccion by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Direccion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Direccion getByOIDWithHV(DireccionOid oid) {
        Optional<Direccion> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Direccion> getInstanceByOid(DireccionOid oid) {
        return oid != null && oid.getDireccionIdentificador() != null
               ? repository.findById(oid.getDireccionIdentificador())
               : Optional.empty();
    }

    /*
     * Returns an instance of Direccion by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Direccion
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Direccion getByJsonOid(String jsonOid) {
        DireccionOid oid = new DireccionOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Direccion related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Direccion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Direccion> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveDireccionTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Direccion related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Direccion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Direccion> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Direccion
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Direccion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getDireccionDynamic(DireccionOid oid, String displaySetItems) {
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
    public String getSuppInfo(DireccionOid oid, String dsName) {
        Direccion instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Direccion instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (DireccionConstants.DSICDIRECCION.equals(dsName))
            return instance.buildICDireccion();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(Direccion instance, String dsName) {
        if (DireccionConstants.DSICDIRECCION.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICDireccion());
        if (DireccionConstants.DSDSDIRECCIONNULL.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSDireccionNull());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (DireccionConstants.FILTER_NAME_FDIRECCIONPORPERSONAYTIPO.equalsIgnoreCase(filterRequest.getName()))
            fDireccionPorPErsonaYTipo(filterRequest, sql);
    }

    @Override
    protected void applyNavigationalFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if ("Persona_TADDUSUARIOASOL_ptDireccionesTitular_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431886233600805FiltNav13(filterRequest, sql);
        if ("Solicitud_TMODDIRECCION_pt_Direcciones_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431957012480960FiltNav3(filterRequest, sql);
        if ("Usuario_TMODUSUARIO_pt_Direccion_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1432633737216826FiltNav2(filterRequest, sql);
        if ("Usuario_TMODUSUARIO_pt_DireccionUsuario_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1432633737216826FiltNav11(filterRequest, sql);
    }


    private void fDireccionPorPErsonaYTipo(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Persona vfPer = filterRequest.hasFilterVariable(DireccionConstants.VAR_NAME_FDIRECCIONPORPERSONAYTIPO_VFPER) ? personaSrv.getByOID(new PersonaOid().fromJsonString(toJson(filterRequest.getFilterVariable(DireccionConstants.VAR_NAME_FDIRECCIONPORPERSONAYTIPO_VFPER)))) : null;
        TipoDireccion vfTipo = filterRequest.hasFilterVariable(DireccionConstants.VAR_NAME_FDIRECCIONPORPERSONAYTIPO_VFTIPO) ? tipoDireccionSrv.getByOID(new TipoDireccionOid().fromJsonString(toJson(filterRequest.getFilterVariable(DireccionConstants.VAR_NAME_FDIRECCIONPORPERSONAYTIPO_VFTIPO)))) : null;

        // Persona = vfPer
        if (vfPer != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(DireccionConstants.ROLE_NAME_PERSONA, PersonaConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(DireccionConstants.VAR_NAME_FDIRECCIONPORPERSONAYTIPO_VFPER, PersonaConstants.CLASS_NAME, PersonaConstants.CLASS_NAME, vfPer.getOid(), DireccionConstants.VAR_NAME_FDIRECCIONPORPERSONAYTIPO_VFPER);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // TipoDireccion = vfTipo
        if (vfTipo != null) {
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(DireccionConstants.ROLE_NAME_TIPODIRECCION, TipoDireccionConstants.CLASS_NAME);
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            SQLWhereOperandOVParameter operand224 = new SQLWhereOperandOVParameter(DireccionConstants.VAR_NAME_FDIRECCIONPORPERSONAYTIPO_VFTIPO, TipoDireccionConstants.CLASS_NAME, TipoDireccionConstants.CLASS_NAME, vfTipo.getOid(), DireccionConstants.VAR_NAME_FDIRECCIONPORPERSONAYTIPO_VFTIPO);
            comparison2.setSecondOperand(operand224);
            conjunction.addOperand(comparison2);

        }        

        sql.addConjunction(conjunction);
    }

    private void clas1431886233600805FiltNav13(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Solicitud personaTADDUSUARIOASOLptSolicitud = filterRequest.hasFilterVariable(PersonaConstants.ARG_NAME_TADDUSUARIOASOL_PERSONATADDUSUARIOASOLPTSOLICITUD) ? solicitudSrv.getByOID(new SolicitudOid().fromJsonString(toJson(filterRequest.getFilterVariable(PersonaConstants.ARG_NAME_TADDUSUARIOASOL_PERSONATADDUSUARIOASOLPTSOLICITUD)))) : null;

        // Persona = ptSolicitud.TitularPersona
        if (personaTADDUSUARIOASOLptSolicitud != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(DireccionConstants.ROLE_NAME_PERSONA, PersonaConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            if (personaTADDUSUARIOASOLptSolicitud.isNull() || personaTADDUSUARIOASOLptSolicitud.getTitularPersona().isNull())
            {
            comparison1.setSecondOperand(new SQLWhereOperandNull());
            }
            else
            {
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(PersonaConstants.ARG_NAME_TADDUSUARIOASOL_PERSONATADDUSUARIOASOLPTSOLICITUD + "." + SolicitudConstants.ROLE_NAME_TITULARPERSONA, SolicitudConstants.CLASS_NAME, PersonaConstants.CLASS_NAME, personaTADDUSUARIOASOLptSolicitud.getOid(), PersonaConstants.ARG_NAME_TADDUSUARIOASOL_PERSONATADDUSUARIOASOLPTSOLICITUD);
            comparison1.setSecondOperand(operand223);
            }
            conjunction.addOperand(comparison1);

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1431957012480960FiltNav3(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Solicitud solicitudTMODDIRECCIONpthisSolicitud = filterRequest.hasFilterVariable(SolicitudConstants.ARG_NAME_TMODDIRECCION_SOLICITUDTMODDIRECCIONPTHISSOLICITUD) ? solicitudSrv.getByOID(new SolicitudOid().fromJsonString(toJson(filterRequest.getFilterVariable(SolicitudConstants.ARG_NAME_TMODDIRECCION_SOLICITUDTMODDIRECCIONPTHISSOLICITUD)))) : null;

        // Persona = pthisSolicitud.ContratantePersona
        if (solicitudTMODDIRECCIONpthisSolicitud != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(DireccionConstants.ROLE_NAME_PERSONA, PersonaConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            if (solicitudTMODDIRECCIONpthisSolicitud.isNull() || solicitudTMODDIRECCIONpthisSolicitud.getContratantePersona().isNull())
            {
            comparison1.setSecondOperand(new SQLWhereOperandNull());
            }
            else
            {
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(SolicitudConstants.ARG_NAME_TMODDIRECCION_SOLICITUDTMODDIRECCIONPTHISSOLICITUD + "." + SolicitudConstants.ROLE_NAME_CONTRATANTEPERSONA, SolicitudConstants.CLASS_NAME, PersonaConstants.CLASS_NAME, solicitudTMODDIRECCIONpthisSolicitud.getOid(), SolicitudConstants.ARG_NAME_TMODDIRECCION_SOLICITUDTMODDIRECCIONPTHISSOLICITUD);
            comparison1.setSecondOperand(operand223);
            }
            conjunction.addOperand(comparison1);

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1432633737216826FiltNav2(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Usuario usuarioTMODUSUARIOpthisUsuario = filterRequest.hasFilterVariable(UsuarioConstants.ARG_NAME_TMODUSUARIO_USUARIOTMODUSUARIOPTHISUSUARIO) ? usuarioSrv.getByOID(new UsuarioOid().fromJsonString(toJson(filterRequest.getFilterVariable(UsuarioConstants.ARG_NAME_TMODUSUARIO_USUARIOTMODUSUARIOPTHISUSUARIO)))) : null;

        // Persona = pthisUsuario.Solicitud.TitularPersona
        if (usuarioTMODUSUARIOpthisUsuario != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(DireccionConstants.ROLE_NAME_PERSONA, PersonaConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            if (usuarioTMODUSUARIOpthisUsuario.isNull() || usuarioTMODUSUARIOpthisUsuario.getSolicitud().getTitularPersona().isNull())
            {
            comparison1.setSecondOperand(new SQLWhereOperandNull());
            }
            else
            {
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(UsuarioConstants.ARG_NAME_TMODUSUARIO_USUARIOTMODUSUARIOPTHISUSUARIO + "." + UsuarioConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_TITULARPERSONA, UsuarioConstants.CLASS_NAME, PersonaConstants.CLASS_NAME, usuarioTMODUSUARIOpthisUsuario.getOid(), UsuarioConstants.ARG_NAME_TMODUSUARIO_USUARIOTMODUSUARIOPTHISUSUARIO);
            comparison1.setSecondOperand(operand223);
            }
            conjunction.addOperand(comparison1);

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1432633737216826FiltNav11(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Usuario usuarioTMODUSUARIOpthisUsuario = filterRequest.hasFilterVariable(UsuarioConstants.ARG_NAME_TMODUSUARIO_USUARIOTMODUSUARIOPTHISUSUARIO) ? usuarioSrv.getByOID(new UsuarioOid().fromJsonString(toJson(filterRequest.getFilterVariable(UsuarioConstants.ARG_NAME_TMODUSUARIO_USUARIOTMODUSUARIOPTHISUSUARIO)))) : null;

        // Persona = pthisUsuario.UsuarioPersona
        if (usuarioTMODUSUARIOpthisUsuario != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(DireccionConstants.ROLE_NAME_PERSONA, PersonaConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(UsuarioConstants.ARG_NAME_TMODUSUARIO_USUARIOTMODUSUARIOPTHISUSUARIO + "." + UsuarioConstants.ROLE_NAME_USUARIOPERSONA, UsuarioConstants.CLASS_NAME, PersonaConstants.CLASS_NAME, usuarioTMODUSUARIOpthisUsuario.getOid(), UsuarioConstants.ARG_NAME_TMODUSUARIO_USUARIOTMODUSUARIOPTHISUSUARIO);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        
        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUDireccionNull
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Direccion using displayset DSDireccionNull that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.direccion.ds.DSDireccionNull> piuPIUDireccionNull(QueryRequest queryRequest) {
        return solveDireccionTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.direccion.ds.DSDireccionNull::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUDireccionNull
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUDireccionNull
     */
    public int getCount4PIUDireccionNull(QueryRequest queryRequest) {
        return solveDireccionTypedQuery(queryRequest).size();
    }

    // Service: InsTipoDireccion

    /*
     * Entry point for service InsTipoDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTipoDireccion(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.InsTipoDireccionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionSrv.insTipoDireccionTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTipoDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTipoDireccionTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.InsTipoDireccionRequest request) throws ModelException {
        request.setDireccionInsTipoDireccionpthisDireccionInstance(direccionSrv.getByOIDWithHV(request.getDireccionInsTipoDireccionpthisDireccion()));
        request.setDireccionInsTipoDireccionpevcTipoDireccionInstance(tipoDireccionSrv.getByOIDWithHV(request.getDireccionInsTipoDireccionpevcTipoDireccion()));
        return direccionSrv.insTipoDireccionImplementation(request, true);
    }

    /*
     * Implements service InsTipoDireccion
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTipoDireccionImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.InsTipoDireccionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Direccion direccionInsTipoDireccionpthisDireccion = request.getDireccionInsTipoDireccionpthisDireccionInstance();
        TipoDireccion direccionInsTipoDireccionpevcTipoDireccion = request.getDireccionInsTipoDireccionpevcTipoDireccionInstance();

        request.checkArguments();
        Direccion instance = direccionInsTipoDireccionpthisDireccion;

        instance.add2TipoDireccion(direccionInsTipoDireccionpevcTipoDireccion);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu.InsTipoDireccionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu.InsTipoDireccionRequest();
            relatedRequest.setTipoDireccionInsTipoDireccionpthisTipoDireccionInstance(direccionInsTipoDireccionpevcTipoDireccion);
            relatedRequest.setTipoDireccionInsTipoDireccionpevcDireccionInstance(direccionInsTipoDireccionpthisDireccion);
            tipoDireccionSrv.insTipoDireccionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDireccion());

        return response;
    }

    // Service: DelTipoDireccion

    /*
     * Entry point for service DelTipoDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTipoDireccion(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.DelTipoDireccionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionSrv.delTipoDireccionTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTipoDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTipoDireccionTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.DelTipoDireccionRequest request) throws ModelException {
        request.setDireccionDelTipoDireccionpthisDireccionInstance(direccionSrv.getByOIDWithHV(request.getDireccionDelTipoDireccionpthisDireccion()));
        request.setDireccionDelTipoDireccionpevcTipoDireccionInstance(tipoDireccionSrv.getByOIDWithHV(request.getDireccionDelTipoDireccionpevcTipoDireccion()));
        return direccionSrv.delTipoDireccionImplementation(request, true);
    }

    /*
     * Implements service DelTipoDireccion
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTipoDireccionImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.DelTipoDireccionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Direccion direccionDelTipoDireccionpthisDireccion = request.getDireccionDelTipoDireccionpthisDireccionInstance();
        TipoDireccion direccionDelTipoDireccionpevcTipoDireccion = request.getDireccionDelTipoDireccionpevcTipoDireccionInstance();

        request.checkArguments();
        Direccion instance = direccionDelTipoDireccionpthisDireccion;

        instance.add2TipoDireccion(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu.DelTipoDireccionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu.DelTipoDireccionRequest();
            relatedRequest.setTipoDireccionDelTipoDireccionpthisTipoDireccionInstance(direccionDelTipoDireccionpevcTipoDireccion);
            relatedRequest.setTipoDireccionDelTipoDireccionpevcDireccionInstance(direccionDelTipoDireccionpthisDireccion);
            tipoDireccionSrv.delTipoDireccionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDireccion());

        return response;
    }

    // Service: InsMunicipioDireccion

    /*
     * Entry point for service InsMunicipioDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insMunicipioDireccion(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.InsMunicipioDireccionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionSrv.insMunicipioDireccionTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsMunicipioDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insMunicipioDireccionTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.InsMunicipioDireccionRequest request) throws ModelException {
        request.setDireccionInsMunicipioDireccionpthisDireccionInstance(direccionSrv.getByOIDWithHV(request.getDireccionInsMunicipioDireccionpthisDireccion()));
        request.setDireccionInsMunicipioDireccionpevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getDireccionInsMunicipioDireccionpevcMunicipio()));
        return direccionSrv.insMunicipioDireccionImplementation(request, true);
    }

    /*
     * Implements service InsMunicipioDireccion
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insMunicipioDireccionImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.InsMunicipioDireccionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Direccion direccionInsMunicipioDireccionpthisDireccion = request.getDireccionInsMunicipioDireccionpthisDireccionInstance();
        Municipio direccionInsMunicipioDireccionpevcMunicipio = request.getDireccionInsMunicipioDireccionpevcMunicipioInstance();

        request.checkArguments();
        Direccion instance = direccionInsMunicipioDireccionpthisDireccion;

        instance.add2Municipio(direccionInsMunicipioDireccionpevcMunicipio);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioDireccionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioDireccionRequest();
            relatedRequest.setMunicipioInsMunicipioDireccionpthisMunicipioInstance(direccionInsMunicipioDireccionpevcMunicipio);
            relatedRequest.setMunicipioInsMunicipioDireccionpevcDireccionInstance(direccionInsMunicipioDireccionpthisDireccion);
            municipioSrv.insMunicipioDireccionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDireccion());

        return response;
    }

    // Service: DelMunicipioDireccion

    /*
     * Entry point for service DelMunicipioDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delMunicipioDireccion(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.DelMunicipioDireccionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionSrv.delMunicipioDireccionTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelMunicipioDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delMunicipioDireccionTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.DelMunicipioDireccionRequest request) throws ModelException {
        request.setDireccionDelMunicipioDireccionpthisDireccionInstance(direccionSrv.getByOIDWithHV(request.getDireccionDelMunicipioDireccionpthisDireccion()));
        request.setDireccionDelMunicipioDireccionpevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getDireccionDelMunicipioDireccionpevcMunicipio()));
        return direccionSrv.delMunicipioDireccionImplementation(request, true);
    }

    /*
     * Implements service DelMunicipioDireccion
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delMunicipioDireccionImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.DelMunicipioDireccionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Direccion direccionDelMunicipioDireccionpthisDireccion = request.getDireccionDelMunicipioDireccionpthisDireccionInstance();
        Municipio direccionDelMunicipioDireccionpevcMunicipio = request.getDireccionDelMunicipioDireccionpevcMunicipioInstance();

        request.checkArguments();
        Direccion instance = direccionDelMunicipioDireccionpthisDireccion;

        instance.add2Municipio(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioDireccionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioDireccionRequest();
            relatedRequest.setMunicipioDelMunicipioDireccionpthisMunicipioInstance(direccionDelMunicipioDireccionpevcMunicipio);
            relatedRequest.setMunicipioDelMunicipioDireccionpevcDireccionInstance(direccionDelMunicipioDireccionpthisDireccion);
            municipioSrv.delMunicipioDireccionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDireccion());

        return response;
    }

    // Service: InsDepartamentoDireccion

    /*
     * Entry point for service InsDepartamentoDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insDepartamentoDireccion(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.InsDepartamentoDireccionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionSrv.insDepartamentoDireccionTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsDepartamentoDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insDepartamentoDireccionTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.InsDepartamentoDireccionRequest request) throws ModelException {
        request.setDireccionInsDepartamentoDireccionpthisDireccionInstance(direccionSrv.getByOIDWithHV(request.getDireccionInsDepartamentoDireccionpthisDireccion()));
        request.setDireccionInsDepartamentoDireccionpevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getDireccionInsDepartamentoDireccionpevcDepartamento()));
        return direccionSrv.insDepartamentoDireccionImplementation(request, true);
    }

    /*
     * Implements service InsDepartamentoDireccion
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insDepartamentoDireccionImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.InsDepartamentoDireccionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Direccion direccionInsDepartamentoDireccionpthisDireccion = request.getDireccionInsDepartamentoDireccionpthisDireccionInstance();
        Departamento direccionInsDepartamentoDireccionpevcDepartamento = request.getDireccionInsDepartamentoDireccionpevcDepartamentoInstance();

        request.checkArguments();
        Direccion instance = direccionInsDepartamentoDireccionpthisDireccion;

        instance.add2Departamento(direccionInsDepartamentoDireccionpevcDepartamento);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoDireccionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoDireccionRequest();
            relatedRequest.setDepartamentoInsDepartamentoDireccionpthisDepartamentoInstance(direccionInsDepartamentoDireccionpevcDepartamento);
            relatedRequest.setDepartamentoInsDepartamentoDireccionpevcDireccionInstance(direccionInsDepartamentoDireccionpthisDireccion);
            departamentoSrv.insDepartamentoDireccionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDireccion());

        return response;
    }

    // Service: DelDepartamentoDireccion

    /*
     * Entry point for service DelDepartamentoDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delDepartamentoDireccion(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.DelDepartamentoDireccionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionSrv.delDepartamentoDireccionTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelDepartamentoDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delDepartamentoDireccionTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.DelDepartamentoDireccionRequest request) throws ModelException {
        request.setDireccionDelDepartamentoDireccionpthisDireccionInstance(direccionSrv.getByOIDWithHV(request.getDireccionDelDepartamentoDireccionpthisDireccion()));
        request.setDireccionDelDepartamentoDireccionpevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getDireccionDelDepartamentoDireccionpevcDepartamento()));
        return direccionSrv.delDepartamentoDireccionImplementation(request, true);
    }

    /*
     * Implements service DelDepartamentoDireccion
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delDepartamentoDireccionImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.DelDepartamentoDireccionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Direccion direccionDelDepartamentoDireccionpthisDireccion = request.getDireccionDelDepartamentoDireccionpthisDireccionInstance();
        Departamento direccionDelDepartamentoDireccionpevcDepartamento = request.getDireccionDelDepartamentoDireccionpevcDepartamentoInstance();

        request.checkArguments();
        Direccion instance = direccionDelDepartamentoDireccionpthisDireccion;

        instance.add2Departamento(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoDireccionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoDireccionRequest();
            relatedRequest.setDepartamentoDelDepartamentoDireccionpthisDepartamentoInstance(direccionDelDepartamentoDireccionpevcDepartamento);
            relatedRequest.setDepartamentoDelDepartamentoDireccionpevcDireccionInstance(direccionDelDepartamentoDireccionpthisDireccion);
            departamentoSrv.delDepartamentoDireccionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDireccion());

        return response;
    }

    // Service: InsPaisDireccion

    /*
     * Entry point for service InsPaisDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPaisDireccion(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.InsPaisDireccionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionSrv.insPaisDireccionTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPaisDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPaisDireccionTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.InsPaisDireccionRequest request) throws ModelException {
        request.setDireccionInsPaisDireccionpthisDireccionInstance(direccionSrv.getByOIDWithHV(request.getDireccionInsPaisDireccionpthisDireccion()));
        request.setDireccionInsPaisDireccionpevcPaisInstance(paisSrv.getByOIDWithHV(request.getDireccionInsPaisDireccionpevcPais()));
        return direccionSrv.insPaisDireccionImplementation(request, true);
    }

    /*
     * Implements service InsPaisDireccion
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPaisDireccionImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.InsPaisDireccionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Direccion direccionInsPaisDireccionpthisDireccion = request.getDireccionInsPaisDireccionpthisDireccionInstance();
        Pais direccionInsPaisDireccionpevcPais = request.getDireccionInsPaisDireccionpevcPaisInstance();

        request.checkArguments();
        Direccion instance = direccionInsPaisDireccionpthisDireccion;

        instance.add2Pais(direccionInsPaisDireccionpevcPais);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.InsPaisDireccionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.pais.siu.InsPaisDireccionRequest();
            relatedRequest.setPaisInsPaisDireccionpthisPaisInstance(direccionInsPaisDireccionpevcPais);
            relatedRequest.setPaisInsPaisDireccionpevcDireccionInstance(direccionInsPaisDireccionpthisDireccion);
            paisSrv.insPaisDireccionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDireccion());

        return response;
    }

    // Service: DelPaisDireccion

    /*
     * Entry point for service DelPaisDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delPaisDireccion(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.DelPaisDireccionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = direccionSrv.delPaisDireccionTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelPaisDireccion
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delPaisDireccionTxn(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.DelPaisDireccionRequest request) throws ModelException {
        request.setDireccionDelPaisDireccionpthisDireccionInstance(direccionSrv.getByOIDWithHV(request.getDireccionDelPaisDireccionpthisDireccion()));
        request.setDireccionDelPaisDireccionpevcPaisInstance(paisSrv.getByOIDWithHV(request.getDireccionDelPaisDireccionpevcPais()));
        return direccionSrv.delPaisDireccionImplementation(request, true);
    }

    /*
     * Implements service DelPaisDireccion
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delPaisDireccionImplementation(
            com.keralty.aeconfiguraciones.viewmodel.direccion.siu.DelPaisDireccionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Direccion direccionDelPaisDireccionpthisDireccion = request.getDireccionDelPaisDireccionpthisDireccionInstance();
        Pais direccionDelPaisDireccionpevcPais = request.getDireccionDelPaisDireccionpevcPaisInstance();

        request.checkArguments();
        Direccion instance = direccionDelPaisDireccionpthisDireccion;

        instance.add2Pais(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.DelPaisDireccionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.pais.siu.DelPaisDireccionRequest();
            relatedRequest.setPaisDelPaisDireccionpthisPaisInstance(direccionDelPaisDireccionpevcPais);
            relatedRequest.setPaisDelPaisDireccionpevcDireccionInstance(direccionDelPaisDireccionpthisDireccion);
            paisSrv.delPaisDireccionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDireccion());

        return response;
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
        select.getOrderByPart().addItem(Constants.normalizePath(DireccionConstants.CLASS_NAME, DireccionConstants.ATTR_FIELD_DIRECCIONIDENTIFICADOR, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildDireccionRelatedInstance(relatedInstanceJsonOid);
        }
        if (DireccionConstants.ROLE_NAME_PERSONA.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDireccionRelatedInstance4Persona(relatedInstanceJsonOid, remainingPath);
        }
        if (DireccionConstants.ROLE_NAME_TIPODIRECCION.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDireccionRelatedInstance4TipoDireccion(relatedInstanceJsonOid, remainingPath);
        }
        if (DireccionConstants.ROLE_NAME_MUNICIPIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDireccionRelatedInstance4Municipio(relatedInstanceJsonOid, remainingPath);
        }
        if (DireccionConstants.ROLE_NAME_DEPARTAMENTO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDireccionRelatedInstance4Departamento(relatedInstanceJsonOid, remainingPath);
        }
        if (DireccionConstants.ROLE_NAME_PAIS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDireccionRelatedInstance4Pais(relatedInstanceJsonOid, remainingPath);
        }
        if (DireccionConstants.ROLE_NAME_CONTRATO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDireccionRelatedInstance4Solicitud(relatedInstanceJsonOid, remainingPath);
        }
        if (DireccionConstants.ROLE_NAME_TITULAR.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDireccionRelatedInstance4Titular(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildDireccionRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Direccion(mapper.readValue(relatedInstanceJsonOid, DireccionOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDireccionRelatedInstance4Persona(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Persona(mapper.readValue(relatedInstanceJsonOid, PersonaOid.class)): Persona.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDireccionRelatedInstance4TipoDireccion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoDireccion(mapper.readValue(relatedInstanceJsonOid, TipoDireccionOid.class)): TipoDireccion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDireccionRelatedInstance4Municipio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Municipio(mapper.readValue(relatedInstanceJsonOid, MunicipioOid.class)): Municipio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDireccionRelatedInstance4Departamento(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Departamento(mapper.readValue(relatedInstanceJsonOid, DepartamentoOid.class)): Departamento.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDireccionRelatedInstance4Pais(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Pais(mapper.readValue(relatedInstanceJsonOid, PaisOid.class)): Pais.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDireccionRelatedInstance4Solicitud(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Solicitud(mapper.readValue(relatedInstanceJsonOid, SolicitudOid.class)): Solicitud.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDireccionRelatedInstance4Titular(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Titular(mapper.readValue(relatedInstanceJsonOid, TitularOid.class)): Titular.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            DireccionOid oid = new DireccionOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(DireccionConstants.ATTR_FIELD_DIRECCIONIDENTIFICADOR, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getDireccionIdentificador(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(DireccionService.class).error(e.getMessage());
        }
    }

}
