package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.AesolicitudesConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.PersonaConstants;
import com.keralty.aeconfiguraciones.global.SolicitudConstants;
import com.keralty.aeconfiguraciones.global.TipoIdentificacionConstants;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.global.UsuarioConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.Direccion;
import com.keralty.aeconfiguraciones.persistence.EstadoCivil;
import com.keralty.aeconfiguraciones.persistence.EstadoPersona;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;
import com.keralty.aeconfiguraciones.persistence.oid.DireccionOid;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoCivilOid;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoPersonaOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoIdentificacionOid;
import com.keralty.aeconfiguraciones.persistence.oid.UsuarioOid;
import com.keralty.aeconfiguraciones.persistence.Persona;
import com.keralty.aeconfiguraciones.persistence.Solicitud;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import com.keralty.aeconfiguraciones.repository.PersonaJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
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
public class PersonaService extends AbstractService {

    PersonaJpaRepository repository;

    PersonaService personaSrv;

    DepartamentoService departamentoSrv;

    MunicipioService municipioSrv;

    EstadoCivilService estadoCivilSrv;

    EstadoPersonaService estadoPersonaSrv;

    TipoIdentificacionService tipoIdentificacionSrv;

    SolicitudService solicitudSrv;

    DireccionService direccionSrv;

    UsuarioService usuarioSrv;

    UserFunctions userFunctionsSrv;

    @Inject
    public PersonaService(
            PersonaService personaSrv,
            DepartamentoService departamentoSrv,
            MunicipioService municipioSrv,
            EstadoCivilService estadoCivilSrv,
            EstadoPersonaService estadoPersonaSrv,
            TipoIdentificacionService tipoIdentificacionSrv,
            SolicitudService solicitudSrv,
            DireccionService direccionSrv,
            UsuarioService usuarioSrv,
            UserFunctions userFunctionsSrv,
            PersonaJpaRepository repository) {
        this.personaSrv = personaSrv;
        this.departamentoSrv = departamentoSrv;
        this.municipioSrv = municipioSrv;
        this.estadoCivilSrv = estadoCivilSrv;
        this.estadoPersonaSrv = estadoPersonaSrv;
        this.tipoIdentificacionSrv = tipoIdentificacionSrv;
        this.solicitudSrv = solicitudSrv;
        this.direccionSrv = direccionSrv;
        this.usuarioSrv = usuarioSrv;
        this.userFunctionsSrv = userFunctionsSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, PersonaConstants.CLASS_NAME);
    }

    private List<Persona> solvePersonaTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, PersonaConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solvePersonaTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, PersonaConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Persona
     *
     * @return Population of class Persona
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Persona> get() {
        List<Persona> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class Persona
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class Persona
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<Persona> instances = get(queryRequest);
        if(PersonaConstants.DSICPERSONA.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICPersona())).collect(Collectors.toList());
        }
        if(PersonaConstants.DSDSPERSONAPIU.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSPersonaPIU())).collect(Collectors.toList());
        }
        if(PersonaConstants.DSIC2PERSONA.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildIC2Persona())).collect(Collectors.toList());
        }
        if(PersonaConstants.DSICPERSONANOMBRESAPELLIDOS.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICPersonaNombresApellidos())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class Persona that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Persona matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Persona> get(QueryRequest queryRequest) {
        return solvePersonaTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solvePersonaTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solvePersonaTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solvePersonaTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solvePersonaTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Persona by ID
     *
     * @param personaIdentificador Identification field personaIdentificador
     *
     * @return Instance of Persona
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Persona getById(Long personaIdentificador) {
        return getByOID(new PersonaOid(personaIdentificador));
    }
    
    /*
     * Returns an instance of Persona by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Persona
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Persona getByOID(PersonaOid oid) {
        Optional<Persona> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Persona by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Persona
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Persona getByOIDWithHV(PersonaOid oid) {
        Optional<Persona> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Persona> getInstanceByOid(PersonaOid oid) {
        return oid != null && oid.getPersonaIdentificador() != null
               ? repository.findById(oid.getPersonaIdentificador())
               : Optional.empty();
    }

    /*
     * Returns an instance of Persona by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Persona
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Persona getByJsonOid(String jsonOid) {
        PersonaOid oid = new PersonaOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Persona related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Persona
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Persona> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solvePersonaTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Persona related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Persona
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Persona> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Persona
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Persona
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getPersonaDynamic(PersonaOid oid, String displaySetItems) {
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
    public String getSuppInfo(PersonaOid oid, String dsName) {
        Persona instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Persona instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (PersonaConstants.DSICPERSONA.equals(dsName))
            return instance.buildICPersona();
        if (PersonaConstants.DSICPERSONANOMBRESAPELLIDOS.equals(dsName))
            return instance.buildICPersonaNombresApellidos();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(Persona instance, String dsName) {
        if (PersonaConstants.DSICPERSONA.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICPersona());
        if (PersonaConstants.DSDSPERSONAPIU.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSPersonaPIU());
        if (PersonaConstants.DSIC2PERSONA.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildIC2Persona());
        if (PersonaConstants.DSICPERSONANOMBRESAPELLIDOS.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICPersonaNombresApellidos());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (PersonaConstants.FILTER_NAME_FPERSONAVARIOS.equalsIgnoreCase(filterRequest.getName()))
            fPersonaVarios(filterRequest, sql);
        if (PersonaConstants.FILTER_NAME_FPERSONACONSULTARCORE.equalsIgnoreCase(filterRequest.getName()))
            fPersonaConsultarCORE(filterRequest, sql);
        if (PersonaConstants.FILTER_NAME_FPERSONACONSULTARCORENAT.equalsIgnoreCase(filterRequest.getName()))
            fPersonaConsultarCORENat(filterRequest, sql);
        if (PersonaConstants.FILTER_NAME_IFPERSONACODIGOCORE.equalsIgnoreCase(filterRequest.getName()))
            iFPersonaCodigoCORE(filterRequest, sql);
        if (PersonaConstants.FILTER_NAME_IFPERSONAPORTIPOYNUMDOC.equalsIgnoreCase(filterRequest.getName()))
            iFPersonaPorTipoYNumDoc(filterRequest, sql);
    }

    @Override
    protected void applyNavigationalFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if ("Solicitud_TSUBIRDOCUMENTOATO_ptPersona_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1431957012480960FiltNav74(filterRequest, sql);
        if ("Solicitud_F_SolicitudConsultaRadicado_voPersona_NavFilter".equalsIgnoreCase(filterRequest.getName()))
            clas1431957012480960FiltNav75(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_Persona".equals(filterRequest.getName()))
            suppInfo4ICPersona(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void fPersonaVarios(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        String vPrimerNombre = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONAVARIOS_VPRIMERNOMBRE) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONAVARIOS_VPRIMERNOMBRE, FilterVariableRequest.STRING) : null;
        String vPrimerApellido = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONAVARIOS_VPRIMERAPELLIDO) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONAVARIOS_VPRIMERAPELLIDO, FilterVariableRequest.STRING) : null;
        String vTipoPersona = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONAVARIOS_VTIPOPERSONA) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONAVARIOS_VTIPOPERSONA, FilterVariableRequest.STRING) : null;
        String vRazonSocial = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONAVARIOS_VRAZONSOCIAL) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONAVARIOS_VRAZONSOCIAL, FilterVariableRequest.STRING) : null;
        TipoIdentificacion voTipoIdentificacion = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONAVARIOS_VOTIPOIDENTIFICACION) ? tipoIdentificacionSrv.getByOID(new TipoIdentificacionOid().fromJsonString(toJson(filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONAVARIOS_VOTIPOIDENTIFICACION)))) : null;
        String vSegundoApellido = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONAVARIOS_VSEGUNDOAPELLIDO) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONAVARIOS_VSEGUNDOAPELLIDO, FilterVariableRequest.STRING) : null;

        // PrimerNombre LIKE "%" + vPrimerNombre
        if (vPrimerNombre != null) {
            // 'like' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONAPRIMERNOMBRE, Constants.Type.STRING.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral("%" + vPrimerNombre, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        // PrimerApellido LIKE "%" + vPrimerApellido
        if (vPrimerApellido != null) {
            // 'like' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONAPRIMERAPELLIDO, Constants.Type.STRING.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            comparison2.setSecondOperand(new SQLWhereOperandLiteral("%" + vPrimerApellido, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison2);

        }        

        // TipoPersona = vTipoPersona
        if (vTipoPersona != null) {
            // '=' Comparison
            SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
            comparison3.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONATIPOPERSONA, Constants.Type.STRING.getTypeName());
            comparison3.setFirstOperand(operand114);
            
            // Operand 2
            comparison3.setSecondOperand(new SQLWhereOperandLiteral(vTipoPersona, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison3);

        }        

        // RazonSocial LIKE "%" + vRazonSocial
        if (vRazonSocial != null) {
            // 'like' Comparison
            SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
            comparison4.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONARAZONSOCIAL, Constants.Type.STRING.getTypeName());
            comparison4.setFirstOperand(operand115);
            
            // Operand 2
            comparison4.setSecondOperand(new SQLWhereOperandLiteral("%" + vRazonSocial, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison4);

        }        

        // TipoIdentificacion = voTipoIdentificacion
        if (voTipoIdentificacion != null) {
            // '=' Comparison
            SQLWhereComparison comparison5 = new SQLWhereComparison(sql);
            comparison5.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand116 = new SQLWhereOperandPathInstance(PersonaConstants.ROLE_NAME_TIPOIDENTIFICACION, TipoIdentificacionConstants.CLASS_NAME);
            comparison5.setFirstOperand(operand116);
            
            // Operand 2
            SQLWhereOperandOVParameter operand227 = new SQLWhereOperandOVParameter(PersonaConstants.VAR_NAME_FPERSONAVARIOS_VOTIPOIDENTIFICACION, TipoIdentificacionConstants.CLASS_NAME, TipoIdentificacionConstants.CLASS_NAME, voTipoIdentificacion.getOid(), PersonaConstants.VAR_NAME_FPERSONAVARIOS_VOTIPOIDENTIFICACION);
            comparison5.setSecondOperand(operand227);
            conjunction.addOperand(comparison5);

        }        

        // SegundoApellido LIKE "%" + vSegundoApellido
        if (vSegundoApellido != null) {
            // 'like' Comparison
            SQLWhereComparison comparison6 = new SQLWhereComparison(sql);
            comparison6.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand117 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONASEGUNDOAPELLIDO, Constants.Type.STRING.getTypeName());
            comparison6.setFirstOperand(operand117);
            
            // Operand 2
            comparison6.setSecondOperand(new SQLWhereOperandLiteral("%" + vSegundoApellido, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison6);

        }        

        sql.addConjunction(conjunction);
    }

    private void fPersonaConsultarCORE(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        TipoIdentificacion voTipoIdentificacion = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VOTIPOIDENTIFICACION) ? tipoIdentificacionSrv.getByOID(new TipoIdentificacionOid().fromJsonString(toJson(filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VOTIPOIDENTIFICACION)))) : null;
        String vNumIdentificacion = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VNUMIDENTIFICACION) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VNUMIDENTIFICACION, FilterVariableRequest.STRING) : null;
        String vPrimerNomORazon = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VPRIMERNOMORAZON) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VPRIMERNOMORAZON, FilterVariableRequest.STRING) : null;
        String vSegundoNombre = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VSEGUNDONOMBRE) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VSEGUNDONOMBRE, FilterVariableRequest.STRING) : null;
        String vPrimerApe = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VPRIMERAPE) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VPRIMERAPE, FilterVariableRequest.STRING) : null;
        String vSegundoApe = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VSEGUNDOAPE) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VSEGUNDOAPE, FilterVariableRequest.STRING) : null;
        Date vFechaNacimiento = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VFECHANACIMIENTO) ? (Date) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VFECHANACIMIENTO, FilterVariableRequest.DATE) : null;

        // TipoIdentificacion = voTipoIdentificacion
        if (voTipoIdentificacion != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(PersonaConstants.ROLE_NAME_TIPOIDENTIFICACION, TipoIdentificacionConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VOTIPOIDENTIFICACION, TipoIdentificacionConstants.CLASS_NAME, TipoIdentificacionConstants.CLASS_NAME, voTipoIdentificacion.getOid(), PersonaConstants.VAR_NAME_FPERSONACONSULTARCORE_VOTIPOIDENTIFICACION);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // NumIdentificacion = vNumIdentificacion
        if (vNumIdentificacion != null) {
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONANUMIDENTIFICACION, Constants.Type.STRING.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(vNumIdentificacion, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison2);

        }        

        // (PrimerNombre LIKE "%" + vPrimerNomORazon OR RazonSocial LIKE "%" + vPrimerNomORazon)
        if (vPrimerNomORazon != null) {
        {
            // 'or' Logical operator
            SQLWhereDisjunction comparison3 = new SQLWhereDisjunction();
        
            // Operand 1
                    // 'like' Comparison
                    SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
                    comparison4.setOperator("like");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONAPRIMERNOMBRE, Constants.Type.STRING.getTypeName());
                    comparison4.setFirstOperand(operand115);
                    
                    // Operand 2
                    comparison4.setSecondOperand(new SQLWhereOperandLiteral("%" + vPrimerNomORazon, Constants.Type.STRING.getTypeName()));
                    comparison3.addOperand(comparison4);
        
            // Operand 2
                    // 'like' Comparison
                    SQLWhereComparison comparison6 = new SQLWhereComparison(sql);
                    comparison6.setOperator("like");
                    
                    // Operand 1
                    SQLWhereOperandPathAttribute operand117 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONARAZONSOCIAL, Constants.Type.STRING.getTypeName());
                    comparison6.setFirstOperand(operand117);
                    
                    // Operand 2
                    comparison6.setSecondOperand(new SQLWhereOperandLiteral("%" + vPrimerNomORazon, Constants.Type.STRING.getTypeName()));
                    comparison3.addOperand(comparison6);
            
            conjunction.addOperand(comparison3);
        }

        }        

        // SegundoNombre LIKE "%" + vSegundoNombre
        if (vSegundoNombre != null) {
            // 'like' Comparison
            SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
            comparison4.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONASEGUNDONOMBRE, Constants.Type.STRING.getTypeName());
            comparison4.setFirstOperand(operand115);
            
            // Operand 2
            comparison4.setSecondOperand(new SQLWhereOperandLiteral("%" + vSegundoNombre, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison4);

        }        

        // PrimerApellido LIKE "%" + vPrimerApe
        if (vPrimerApe != null) {
            // 'like' Comparison
            SQLWhereComparison comparison5 = new SQLWhereComparison(sql);
            comparison5.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand116 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONAPRIMERAPELLIDO, Constants.Type.STRING.getTypeName());
            comparison5.setFirstOperand(operand116);
            
            // Operand 2
            comparison5.setSecondOperand(new SQLWhereOperandLiteral("%" + vPrimerApe, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison5);

        }        

        // SegundoApellido LIKE "%" + vSegundoApe
        if (vSegundoApe != null) {
            // 'like' Comparison
            SQLWhereComparison comparison6 = new SQLWhereComparison(sql);
            comparison6.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand117 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONASEGUNDOAPELLIDO, Constants.Type.STRING.getTypeName());
            comparison6.setFirstOperand(operand117);
            
            // Operand 2
            comparison6.setSecondOperand(new SQLWhereOperandLiteral("%" + vSegundoApe, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison6);

        }        

        // FechaNacimiento = vFechaNacimiento
        if (vFechaNacimiento != null) {
            // '=' Comparison
            SQLWhereComparison comparison7 = new SQLWhereComparison(sql);
            comparison7.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand118 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONAFECHANACIMIENTO, Constants.Type.DATE.getTypeName());
            comparison7.setFirstOperand(operand118);
            
            // Operand 2
            comparison7.setSecondOperand(new SQLWhereOperandLiteral(vFechaNacimiento, Constants.Type.DATE.getTypeName()));
            conjunction.addOperand(comparison7);

        }        

        sql.addConjunction(conjunction);
    }

    private void fPersonaConsultarCORENat(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        TipoIdentificacion voTipoIdentificacion = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VOTIPOIDENTIFICACION) ? tipoIdentificacionSrv.getByOID(new TipoIdentificacionOid().fromJsonString(toJson(filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VOTIPOIDENTIFICACION)))) : null;
        String vNumIdentificacion = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VNUMIDENTIFICACION) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VNUMIDENTIFICACION, FilterVariableRequest.STRING) : null;
        String vPrimerNombre = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VPRIMERNOMBRE) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VPRIMERNOMBRE, FilterVariableRequest.STRING) : null;
        String vSegundoNombre = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VSEGUNDONOMBRE) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VSEGUNDONOMBRE, FilterVariableRequest.STRING) : null;
        String vPrimerApe = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VPRIMERAPE) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VPRIMERAPE, FilterVariableRequest.STRING) : null;
        String vSegundoApe = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VSEGUNDOAPE) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VSEGUNDOAPE, FilterVariableRequest.STRING) : null;
        Date vFechaNacimiento = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VFECHANACIMIENTO) ? (Date) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VFECHANACIMIENTO, FilterVariableRequest.DATE) : null;

        // TipoIdentificacion = voTipoIdentificacion
        if (voTipoIdentificacion != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(PersonaConstants.ROLE_NAME_TIPOIDENTIFICACION, TipoIdentificacionConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VOTIPOIDENTIFICACION, TipoIdentificacionConstants.CLASS_NAME, TipoIdentificacionConstants.CLASS_NAME, voTipoIdentificacion.getOid(), PersonaConstants.VAR_NAME_FPERSONACONSULTARCORENAT_VOTIPOIDENTIFICACION);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // NumIdentificacion = vNumIdentificacion
        if (vNumIdentificacion != null) {
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONANUMIDENTIFICACION, Constants.Type.STRING.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(vNumIdentificacion, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison2);

        }        

        // PrimerNombre LIKE "%" + vPrimerNombre
        if (vPrimerNombre != null) {
            // 'like' Comparison
            SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
            comparison3.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONAPRIMERNOMBRE, Constants.Type.STRING.getTypeName());
            comparison3.setFirstOperand(operand114);
            
            // Operand 2
            comparison3.setSecondOperand(new SQLWhereOperandLiteral("%" + vPrimerNombre, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison3);

        }        

        // SegundoNombre LIKE "%" + vSegundoNombre
        if (vSegundoNombre != null) {
            // 'like' Comparison
            SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
            comparison4.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONASEGUNDONOMBRE, Constants.Type.STRING.getTypeName());
            comparison4.setFirstOperand(operand115);
            
            // Operand 2
            comparison4.setSecondOperand(new SQLWhereOperandLiteral("%" + vSegundoNombre, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison4);

        }        

        // PrimerApellido LIKE "%" + vPrimerApe
        if (vPrimerApe != null) {
            // 'like' Comparison
            SQLWhereComparison comparison5 = new SQLWhereComparison(sql);
            comparison5.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand116 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONAPRIMERAPELLIDO, Constants.Type.STRING.getTypeName());
            comparison5.setFirstOperand(operand116);
            
            // Operand 2
            comparison5.setSecondOperand(new SQLWhereOperandLiteral("%" + vPrimerApe, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison5);

        }        

        // SegundoApellido LIKE "%" + vSegundoApe
        if (vSegundoApe != null) {
            // 'like' Comparison
            SQLWhereComparison comparison6 = new SQLWhereComparison(sql);
            comparison6.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand117 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONASEGUNDOAPELLIDO, Constants.Type.STRING.getTypeName());
            comparison6.setFirstOperand(operand117);
            
            // Operand 2
            comparison6.setSecondOperand(new SQLWhereOperandLiteral("%" + vSegundoApe, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison6);

        }        

        // FechaNacimiento = vFechaNacimiento
        if (vFechaNacimiento != null) {
            // '=' Comparison
            SQLWhereComparison comparison7 = new SQLWhereComparison(sql);
            comparison7.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand118 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONAFECHANACIMIENTO, Constants.Type.DATE.getTypeName());
            comparison7.setFirstOperand(operand118);
            
            // Operand 2
            comparison7.setSecondOperand(new SQLWhereOperandLiteral(vFechaNacimiento, Constants.Type.DATE.getTypeName()));
            conjunction.addOperand(comparison7);

        }        

        sql.addConjunction(conjunction);
    }

    private void iFPersonaCodigoCORE(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Long vCodigo = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_IFPERSONACODIGOCORE_VCODIGO) ? (Long) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_IFPERSONACODIGOCORE_VCODIGO, FilterVariableRequest.INT) : null;

        // Codigo = vCodigo
        if (vCodigo != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONACODIGO, Constants.Type.INT.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(vCodigo, Constants.Type.INT.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        sql.addConjunction(conjunction);
    }

    private void iFPersonaPorTipoYNumDoc(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        String vNomCorto = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_IFPERSONAPORTIPOYNUMDOC_VNOMCORTO) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_IFPERSONAPORTIPOYNUMDOC_VNOMCORTO, FilterVariableRequest.STRING) : null;
        String vNumDoc = filterRequest.hasFilterVariable(PersonaConstants.VAR_NAME_IFPERSONAPORTIPOYNUMDOC_VNUMDOC) ? (String) filterRequest.getFilterVariable(PersonaConstants.VAR_NAME_IFPERSONAPORTIPOYNUMDOC_VNUMDOC, FilterVariableRequest.STRING) : null;

        // TipoIdentificacion.NombreCorto = vNomCorto
        if (vNomCorto != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(PersonaConstants.ROLE_NAME_TIPOIDENTIFICACION + "." + TipoIdentificacionConstants.ATTR_NAME_TIPOIDENTIFICACIONNOMBRECORTO, Constants.Type.STRING.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(vNomCorto, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        // NumIdentificacion = vNumDoc
        if (vNumDoc != null) {
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONANUMIDENTIFICACION, Constants.Type.STRING.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(vNumDoc, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison2);

        }        

        sql.addConjunction(conjunction);
    }

    private void clas1431957012480960FiltNav74(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Solicitud solicitudTSUBIRDOCUMENTOATOpthisSolicitud = filterRequest.hasFilterVariable(SolicitudConstants.ARG_NAME_TSUBIRDOCUMENTOATO_SOLICITUDTSUBIRDOCUMENTOATOPTHISSOLICITUD) ? solicitudSrv.getByOID(new SolicitudOid().fromJsonString(toJson(filterRequest.getFilterVariable(SolicitudConstants.ARG_NAME_TSUBIRDOCUMENTOATO_SOLICITUDTSUBIRDOCUMENTOATOPTHISSOLICITUD)))) : null;

        // Usuarios.Solicitud.Aesolicitudes.idsolicitud = pthisSolicitud.Aesolicitudes.idsolicitud OR (pthisSolicitud.ContratantePersona.Identificador = Identificador AND (Codigo = NULL OR Codigo = 0 OR pthisSolicitud.GrupoAsociado.Codigo >= 0 OR (pthisSolicitud.Tramite = 1 AND TipoIdentificacion.NIT = true))) OR (pthisSolicitud.TitularPersona.Identificador = Identificador AND (Codigo = NULL OR Codigo = 0 OR pthisSolicitud.Tramite = 1 OR pthisSolicitud.NumFamilia = NULL))
        if (solicitudTSUBIRDOCUMENTOATOpthisSolicitud != null) {
        {
            // 'or' Logical operator
            SQLWhereDisjunction comparison1 = new SQLWhereDisjunction();
        
            // Operand 1
                {
                    // 'or' Logical operator
                    SQLWhereDisjunction comparison2 = new SQLWhereDisjunction();
                
                    // Operand 1
                            // '=' Comparison
                            SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
                            comparison3.setOperator("=");
                            
                            // Operand 1
                            SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(PersonaConstants.ROLE_NAME_USUARIOS + "." + UsuarioConstants.ROLE_NAME_SOLICITUD + "." + SolicitudConstants.ROLE_NAME_AESOLICITUDES + "." + AesolicitudesConstants.ATTR_NAME_AESOLICITUDESIDSOLICITUD, Constants.Type.INT.getTypeName());
                            comparison3.setFirstOperand(operand114);
                            
                            // Operand 2
                            comparison3.setSecondOperand(new SQLWhereOperandLiteral(solicitudTSUBIRDOCUMENTOATOpthisSolicitud.getAesolicitudes().getAesolicitudesidsolicitud(), Constants.Type.INT.getTypeName()));
                            comparison2.addOperand(comparison3);
                
                    // Operand 2
                        {
                            // 'and' Logical operator
                            SQLWhereConjunction comparison5 = new SQLWhereConjunction();
                        
                            // Operand 1
                                    // '=' Comparison
                                    SQLWhereComparison comparison6 = new SQLWhereComparison(sql);
                                    comparison6.setOperator("=");
                                    
                                    // Operand 1
                                    if (solicitudTSUBIRDOCUMENTOATOpthisSolicitud.getContratantePersona().getPersonaIdentificador()!= null){
                                    comparison6.setFirstOperand(new SQLWhereOperandLiteral(solicitudTSUBIRDOCUMENTOATOpthisSolicitud.getContratantePersona().getPersonaIdentificador(), Constants.Type.INT.getTypeName()));
                                    } else {
                                    comparison6.setFirstOperand(new SQLWhereOperandNull());
                                    }
                                    
                                    // Operand 2
                                    SQLWhereOperandPathAttribute operand228 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONAIDENTIFICADOR, Constants.Type.INT.getTypeName());
                                    comparison6.setSecondOperand(operand228);
                                    comparison5.addOperand(comparison6);
                        
                            // Operand 2
                                {
                                    // 'or' Logical operator
                                    SQLWhereDisjunction comparison8 = new SQLWhereDisjunction();
                                
                                    // Operand 1
                                        {
                                            // 'or' Logical operator
                                            SQLWhereDisjunction comparison9 = new SQLWhereDisjunction();
                                        
                                            // Operand 1
                                                {
                                                    // 'or' Logical operator
                                                    SQLWhereDisjunction comparison10 = new SQLWhereDisjunction();
                                                
                                                    // Operand 1
                                                            // '=' Comparison
                                                            SQLWhereComparison comparison11 = new SQLWhereComparison(sql);
                                                            comparison11.setOperator("=");
                                                            
                                                            // Operand 1
                                                            SQLWhereOperandPathAttribute operand1112 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONACODIGO, Constants.Type.INT.getTypeName());
                                                            comparison11.setFirstOperand(operand1112);
                                                            
                                                            // Operand 2
                                                            comparison11.setSecondOperand(new SQLWhereOperandNull());
                                                            comparison10.addOperand(comparison11);
                                                
                                                    // Operand 2
                                                            // '=' Comparison
                                                            SQLWhereComparison comparison12 = new SQLWhereComparison(sql);
                                                            comparison12.setOperator("=");
                                                            
                                                            // Operand 1
                                                            SQLWhereOperandPathAttribute operand1113 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONACODIGO, Constants.Type.INT.getTypeName());
                                                            comparison12.setFirstOperand(operand1113);
                                                            
                                                            // Operand 2
                                                            comparison12.setSecondOperand(new SQLWhereOperandLiteral(Long.valueOf(0), Constants.Type.NAT.getTypeName()));
                                                            comparison10.addOperand(comparison12);
                                                    
                                                    comparison9.addOperand(comparison10);
                                                }
                                        
                                            // Operand 2
                                                    // '>=' Comparison
                                                    SQLWhereComparison comparison13 = new SQLWhereComparison(sql);
                                                    comparison13.setOperator(">=");
                                                    
                                                    // Operand 1
                                                    if (solicitudTSUBIRDOCUMENTOATOpthisSolicitud.getGrupoAsociado().getGrupoAsociadoCodigo()!= null){
                                                    comparison13.setFirstOperand(new SQLWhereOperandLiteral(solicitudTSUBIRDOCUMENTOATOpthisSolicitud.getGrupoAsociado().getGrupoAsociadoCodigo(), Constants.Type.INT.getTypeName()));
                                                    } else {
                                                    comparison13.setFirstOperand(new SQLWhereOperandNull());
                                                    }
                                                    
                                                    // Operand 2
                                                    comparison13.setSecondOperand(new SQLWhereOperandLiteral(Long.valueOf(0), Constants.Type.NAT.getTypeName()));
                                                    comparison9.addOperand(comparison13);
                                            
                                            comparison8.addOperand(comparison9);
                                        }
                                
                                    // Operand 2
                                        {
                                            // 'and' Logical operator
                                            SQLWhereConjunction comparison14 = new SQLWhereConjunction();
                                        
                                            // Operand 1
                                                    // '=' Comparison
                                                    SQLWhereComparison comparison15 = new SQLWhereComparison(sql);
                                                    comparison15.setOperator("=");
                                                    
                                                    // Operand 1
                                                    comparison15.setFirstOperand(new SQLWhereOperandLiteral(solicitudTSUBIRDOCUMENTOATOpthisSolicitud.getSolicitudTramite(), Constants.Type.NAT.getTypeName()));
                                                    
                                                    // Operand 2
                                                    comparison15.setSecondOperand(new SQLWhereOperandLiteral(Long.valueOf(1), Constants.Type.NAT.getTypeName()));
                                                    comparison14.addOperand(comparison15);
                                        
                                            // Operand 2
                                                    // '=' Comparison
                                                    SQLWhereComparison comparison17 = new SQLWhereComparison(sql);
                                                    comparison17.setOperator("=");
                                                    
                                                    // Operand 1
                                                    SQLWhereOperandPathAttribute operand1118 = new SQLWhereOperandPathAttribute(PersonaConstants.ROLE_NAME_TIPOIDENTIFICACION + "." + TipoIdentificacionConstants.ATTR_NAME_TIPOIDENTIFICACIONNIT, Constants.Type.BOOL.getTypeName());
                                                    comparison17.setFirstOperand(operand1118);
                                                    
                                                    // Operand 2
                                                    comparison17.setSecondOperand(new SQLWhereOperandLiteral(Boolean.TRUE, Constants.Type.BOOL.getTypeName()));
                                                    comparison14.addOperand(comparison17);
                                            
                                            comparison8.addOperand(comparison14);
                                        }
                                    
                                    comparison5.addOperand(comparison8);
                                }
                            
                            comparison2.addOperand(comparison5);
                        }
                    
                    comparison1.addOperand(comparison2);
                }
        
            // Operand 2
                {
                    // 'and' Logical operator
                    SQLWhereConjunction comparison16 = new SQLWhereConjunction();
                
                    // Operand 1
                            // '=' Comparison
                            SQLWhereComparison comparison170 = new SQLWhereComparison(sql);
                            comparison170.setOperator("=");
                            
                            // Operand 1
                            if (solicitudTSUBIRDOCUMENTOATOpthisSolicitud.getTitularPersona().getPersonaIdentificador()!= null){
                            comparison170.setFirstOperand(new SQLWhereOperandLiteral(solicitudTSUBIRDOCUMENTOATOpthisSolicitud.getTitularPersona().getPersonaIdentificador(), Constants.Type.INT.getTypeName()));
                            } else {
                            comparison170.setFirstOperand(new SQLWhereOperandNull());
                            }
                            
                            // Operand 2
                            SQLWhereOperandPathAttribute operand2219 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONAIDENTIFICADOR, Constants.Type.INT.getTypeName());
                            comparison170.setSecondOperand(operand2219);
                            comparison16.addOperand(comparison170);
                
                    // Operand 2
                        {
                            // 'or' Logical operator
                            SQLWhereDisjunction comparison19 = new SQLWhereDisjunction();
                        
                            // Operand 1
                                {
                                    // 'or' Logical operator
                                    SQLWhereDisjunction comparison20 = new SQLWhereDisjunction();
                                
                                    // Operand 1
                                        {
                                            // 'or' Logical operator
                                            SQLWhereDisjunction comparison21 = new SQLWhereDisjunction();
                                        
                                            // Operand 1
                                                    // '=' Comparison
                                                    SQLWhereComparison comparison22 = new SQLWhereComparison(sql);
                                                    comparison22.setOperator("=");
                                                    
                                                    // Operand 1
                                                    SQLWhereOperandPathAttribute operand1123 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONACODIGO, Constants.Type.INT.getTypeName());
                                                    comparison22.setFirstOperand(operand1123);
                                                    
                                                    // Operand 2
                                                    comparison22.setSecondOperand(new SQLWhereOperandNull());
                                                    comparison21.addOperand(comparison22);
                                        
                                            // Operand 2
                                                    // '=' Comparison
                                                    SQLWhereComparison comparison23 = new SQLWhereComparison(sql);
                                                    comparison23.setOperator("=");
                                                    
                                                    // Operand 1
                                                    SQLWhereOperandPathAttribute operand1124 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONACODIGO, Constants.Type.INT.getTypeName());
                                                    comparison23.setFirstOperand(operand1124);
                                                    
                                                    // Operand 2
                                                    comparison23.setSecondOperand(new SQLWhereOperandLiteral(Long.valueOf(0), Constants.Type.NAT.getTypeName()));
                                                    comparison21.addOperand(comparison23);
                                            
                                            comparison20.addOperand(comparison21);
                                        }
                                
                                    // Operand 2
                                            // '=' Comparison
                                            SQLWhereComparison comparison24 = new SQLWhereComparison(sql);
                                            comparison24.setOperator("=");
                                            
                                            // Operand 1
                                            comparison24.setFirstOperand(new SQLWhereOperandLiteral(solicitudTSUBIRDOCUMENTOATOpthisSolicitud.getSolicitudTramite(), Constants.Type.NAT.getTypeName()));
                                            
                                            // Operand 2
                                            comparison24.setSecondOperand(new SQLWhereOperandLiteral(Long.valueOf(1), Constants.Type.NAT.getTypeName()));
                                            comparison20.addOperand(comparison24);
                                    
                                    comparison19.addOperand(comparison20);
                                }
                        
                            // Operand 2
                                    // '=' Comparison
                                    SQLWhereComparison comparison25 = new SQLWhereComparison(sql);
                                    comparison25.setOperator("=");
                                    
                                    // Operand 1
                                    if (solicitudTSUBIRDOCUMENTOATOpthisSolicitud.getSolicitudNumFamilia()!= null){
                                    comparison25.setFirstOperand(new SQLWhereOperandLiteral(solicitudTSUBIRDOCUMENTOATOpthisSolicitud.getSolicitudNumFamilia(), Constants.Type.STRING.getTypeName()));
                                    } else {
                                    comparison25.setFirstOperand(new SQLWhereOperandNull());
                                    }
                                    
                                    // Operand 2
                                    comparison25.setSecondOperand(new SQLWhereOperandNull());
                                    comparison19.addOperand(comparison25);
                            
                            comparison16.addOperand(comparison19);
                        }
                    
                    comparison1.addOperand(comparison16);
                }
            
            conjunction.addOperand(comparison1);
        }

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1431957012480960FiltNav75(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        TipoIdentificacion voTipoDoc = filterRequest.hasFilterVariable(SolicitudConstants.VAR_NAME_FSOLICITUDCONSULTARADICADO_VOTIPODOC) ? tipoIdentificacionSrv.getByOID(new TipoIdentificacionOid().fromJsonString(toJson(filterRequest.getFilterVariable(SolicitudConstants.VAR_NAME_FSOLICITUDCONSULTARADICADO_VOTIPODOC)))) : null;
        String vNumDoc = filterRequest.hasFilterVariable(SolicitudConstants.VAR_NAME_FSOLICITUDCONSULTARADICADO_VNUMDOC) ? (String) filterRequest.getFilterVariable(SolicitudConstants.VAR_NAME_FSOLICITUDCONSULTARADICADO_VNUMDOC, FilterVariableRequest.STRING) : null;

        // TipoIdentificacion = voTipoDoc
        if (voTipoDoc != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(PersonaConstants.ROLE_NAME_TIPOIDENTIFICACION, TipoIdentificacionConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(SolicitudConstants.VAR_NAME_FSOLICITUDCONSULTARADICADO_VOTIPODOC, TipoIdentificacionConstants.CLASS_NAME, TipoIdentificacionConstants.CLASS_NAME, voTipoDoc.getOid(), SolicitudConstants.VAR_NAME_FSOLICITUDCONSULTARADICADO_VOTIPODOC);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // NumIdentificacion LIKE vNumDoc
        if (vNumDoc != null) {
            // 'like' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("like");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONANUMIDENTIFICACION, Constants.Type.STRING.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(vNumDoc, Constants.Type.STRING.getTypeName()));
            conjunction.addOperand(comparison2);

        }        
        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUPersona
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Persona using displayset DSPersonaPIU that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.persona.ds.DSPersonaPIU> piuPIUPersona(QueryRequest queryRequest) {
        return solvePersonaTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.persona.ds.DSPersonaPIU::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUPersona
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUPersona
     */
    public int getCount4PIUPersona(QueryRequest queryRequest) {
        return solvePersonaTypedQuery(queryRequest).size();
    }

    // Service: InsDptoNacimientoPersona

    /*
     * Entry point for service InsDptoNacimientoPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insDptoNacimientoPersona(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.InsDptoNacimientoPersonaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaSrv.insDptoNacimientoPersonaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsDptoNacimientoPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insDptoNacimientoPersonaTxn(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.InsDptoNacimientoPersonaRequest request) throws ModelException {
        request.setPersonaInsDptoNacimientoPersonapthisPersonaInstance(personaSrv.getByOIDWithHV(request.getPersonaInsDptoNacimientoPersonapthisPersona()));
        request.setPersonaInsDptoNacimientoPersonapevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getPersonaInsDptoNacimientoPersonapevcDepartamento()));
        return personaSrv.insDptoNacimientoPersonaImplementation(request, true);
    }

    /*
     * Implements service InsDptoNacimientoPersona
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insDptoNacimientoPersonaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.InsDptoNacimientoPersonaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Persona personaInsDptoNacimientoPersonapthisPersona = request.getPersonaInsDptoNacimientoPersonapthisPersonaInstance();
        Departamento personaInsDptoNacimientoPersonapevcDepartamento = request.getPersonaInsDptoNacimientoPersonapevcDepartamentoInstance();

        request.checkArguments();
        Persona instance = personaInsDptoNacimientoPersonapthisPersona;

        instance.add2DepartamentoNacimiento(personaInsDptoNacimientoPersonapevcDepartamento);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDptoNacimientoPersonaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDptoNacimientoPersonaRequest();
            relatedRequest.setDepartamentoInsDptoNacimientoPersonapthisDepartamentoInstance(personaInsDptoNacimientoPersonapevcDepartamento);
            relatedRequest.setDepartamentoInsDptoNacimientoPersonapevcPersonaInstance(personaInsDptoNacimientoPersonapthisPersona);
            departamentoSrv.insDptoNacimientoPersonaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPersona());

        return response;
    }

    // Service: DelDptoNacimientoPersona

    /*
     * Entry point for service DelDptoNacimientoPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delDptoNacimientoPersona(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.DelDptoNacimientoPersonaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaSrv.delDptoNacimientoPersonaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelDptoNacimientoPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delDptoNacimientoPersonaTxn(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.DelDptoNacimientoPersonaRequest request) throws ModelException {
        request.setPersonaDelDptoNacimientoPersonapthisPersonaInstance(personaSrv.getByOIDWithHV(request.getPersonaDelDptoNacimientoPersonapthisPersona()));
        request.setPersonaDelDptoNacimientoPersonapevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getPersonaDelDptoNacimientoPersonapevcDepartamento()));
        return personaSrv.delDptoNacimientoPersonaImplementation(request, true);
    }

    /*
     * Implements service DelDptoNacimientoPersona
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delDptoNacimientoPersonaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.DelDptoNacimientoPersonaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Persona personaDelDptoNacimientoPersonapthisPersona = request.getPersonaDelDptoNacimientoPersonapthisPersonaInstance();
        Departamento personaDelDptoNacimientoPersonapevcDepartamento = request.getPersonaDelDptoNacimientoPersonapevcDepartamentoInstance();

        request.checkArguments();
        Persona instance = personaDelDptoNacimientoPersonapthisPersona;

        instance.add2DepartamentoNacimiento(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDptoNacimientoPersonaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDptoNacimientoPersonaRequest();
            relatedRequest.setDepartamentoDelDptoNacimientoPersonapthisDepartamentoInstance(personaDelDptoNacimientoPersonapevcDepartamento);
            relatedRequest.setDepartamentoDelDptoNacimientoPersonapevcPersonaInstance(personaDelDptoNacimientoPersonapthisPersona);
            departamentoSrv.delDptoNacimientoPersonaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPersona());

        return response;
    }

    // Service: InsMunicipioNacimientoPersona

    /*
     * Entry point for service InsMunicipioNacimientoPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insMunicipioNacimientoPersona(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.InsMunicipioNacimientoPersonaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaSrv.insMunicipioNacimientoPersonaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsMunicipioNacimientoPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insMunicipioNacimientoPersonaTxn(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.InsMunicipioNacimientoPersonaRequest request) throws ModelException {
        request.setPersonaInsMunicipioNacimientoPersonapthisPersonaInstance(personaSrv.getByOIDWithHV(request.getPersonaInsMunicipioNacimientoPersonapthisPersona()));
        request.setPersonaInsMunicipioNacimientoPersonapevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getPersonaInsMunicipioNacimientoPersonapevcMunicipio()));
        return personaSrv.insMunicipioNacimientoPersonaImplementation(request, true);
    }

    /*
     * Implements service InsMunicipioNacimientoPersona
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insMunicipioNacimientoPersonaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.InsMunicipioNacimientoPersonaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Persona personaInsMunicipioNacimientoPersonapthisPersona = request.getPersonaInsMunicipioNacimientoPersonapthisPersonaInstance();
        Municipio personaInsMunicipioNacimientoPersonapevcMunicipio = request.getPersonaInsMunicipioNacimientoPersonapevcMunicipioInstance();

        request.checkArguments();
        Persona instance = personaInsMunicipioNacimientoPersonapthisPersona;

        instance.add2MunicipioNacimiento(personaInsMunicipioNacimientoPersonapevcMunicipio);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioNacimientoPersonaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioNacimientoPersonaRequest();
            relatedRequest.setMunicipioInsMunicipioNacimientoPersonapthisMunicipioInstance(personaInsMunicipioNacimientoPersonapevcMunicipio);
            relatedRequest.setMunicipioInsMunicipioNacimientoPersonapevcPersonaInstance(personaInsMunicipioNacimientoPersonapthisPersona);
            municipioSrv.insMunicipioNacimientoPersonaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPersona());

        return response;
    }

    // Service: DelMunicipioNacimientoPersona

    /*
     * Entry point for service DelMunicipioNacimientoPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delMunicipioNacimientoPersona(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.DelMunicipioNacimientoPersonaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaSrv.delMunicipioNacimientoPersonaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelMunicipioNacimientoPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delMunicipioNacimientoPersonaTxn(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.DelMunicipioNacimientoPersonaRequest request) throws ModelException {
        request.setPersonaDelMunicipioNacimientoPersonapthisPersonaInstance(personaSrv.getByOIDWithHV(request.getPersonaDelMunicipioNacimientoPersonapthisPersona()));
        request.setPersonaDelMunicipioNacimientoPersonapevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getPersonaDelMunicipioNacimientoPersonapevcMunicipio()));
        return personaSrv.delMunicipioNacimientoPersonaImplementation(request, true);
    }

    /*
     * Implements service DelMunicipioNacimientoPersona
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delMunicipioNacimientoPersonaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.DelMunicipioNacimientoPersonaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Persona personaDelMunicipioNacimientoPersonapthisPersona = request.getPersonaDelMunicipioNacimientoPersonapthisPersonaInstance();
        Municipio personaDelMunicipioNacimientoPersonapevcMunicipio = request.getPersonaDelMunicipioNacimientoPersonapevcMunicipioInstance();

        request.checkArguments();
        Persona instance = personaDelMunicipioNacimientoPersonapthisPersona;

        instance.add2MunicipioNacimiento(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioNacimientoPersonaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioNacimientoPersonaRequest();
            relatedRequest.setMunicipioDelMunicipioNacimientoPersonapthisMunicipioInstance(personaDelMunicipioNacimientoPersonapevcMunicipio);
            relatedRequest.setMunicipioDelMunicipioNacimientoPersonapevcPersonaInstance(personaDelMunicipioNacimientoPersonapthisPersona);
            municipioSrv.delMunicipioNacimientoPersonaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPersona());

        return response;
    }

    // Service: InsEstadoCivilPersona

    /*
     * Entry point for service InsEstadoCivilPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insEstadoCivilPersona(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.InsEstadoCivilPersonaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaSrv.insEstadoCivilPersonaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsEstadoCivilPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insEstadoCivilPersonaTxn(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.InsEstadoCivilPersonaRequest request) throws ModelException {
        request.setPersonaInsEstadoCivilPersonapthisPersonaInstance(personaSrv.getByOIDWithHV(request.getPersonaInsEstadoCivilPersonapthisPersona()));
        request.setPersonaInsEstadoCivilPersonapevcEstadoCivilInstance(estadoCivilSrv.getByOIDWithHV(request.getPersonaInsEstadoCivilPersonapevcEstadoCivil()));
        return personaSrv.insEstadoCivilPersonaImplementation(request, true);
    }

    /*
     * Implements service InsEstadoCivilPersona
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insEstadoCivilPersonaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.InsEstadoCivilPersonaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Persona personaInsEstadoCivilPersonapthisPersona = request.getPersonaInsEstadoCivilPersonapthisPersonaInstance();
        EstadoCivil personaInsEstadoCivilPersonapevcEstadoCivil = request.getPersonaInsEstadoCivilPersonapevcEstadoCivilInstance();

        request.checkArguments();
        Persona instance = personaInsEstadoCivilPersonapthisPersona;

        instance.add2EstadoCivil(personaInsEstadoCivilPersonapevcEstadoCivil);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.InsEstadoCivilPersonaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.InsEstadoCivilPersonaRequest();
            relatedRequest.setEstadoCivilInsEstadoCivilPersonapthisEstadoCivilInstance(personaInsEstadoCivilPersonapevcEstadoCivil);
            relatedRequest.setEstadoCivilInsEstadoCivilPersonapevcPersonaInstance(personaInsEstadoCivilPersonapthisPersona);
            estadoCivilSrv.insEstadoCivilPersonaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPersona());

        return response;
    }

    // Service: DelEstadoCivilPersona

    /*
     * Entry point for service DelEstadoCivilPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delEstadoCivilPersona(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.DelEstadoCivilPersonaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaSrv.delEstadoCivilPersonaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelEstadoCivilPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delEstadoCivilPersonaTxn(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.DelEstadoCivilPersonaRequest request) throws ModelException {
        request.setPersonaDelEstadoCivilPersonapthisPersonaInstance(personaSrv.getByOIDWithHV(request.getPersonaDelEstadoCivilPersonapthisPersona()));
        request.setPersonaDelEstadoCivilPersonapevcEstadoCivilInstance(estadoCivilSrv.getByOIDWithHV(request.getPersonaDelEstadoCivilPersonapevcEstadoCivil()));
        return personaSrv.delEstadoCivilPersonaImplementation(request, true);
    }

    /*
     * Implements service DelEstadoCivilPersona
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delEstadoCivilPersonaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.DelEstadoCivilPersonaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Persona personaDelEstadoCivilPersonapthisPersona = request.getPersonaDelEstadoCivilPersonapthisPersonaInstance();
        EstadoCivil personaDelEstadoCivilPersonapevcEstadoCivil = request.getPersonaDelEstadoCivilPersonapevcEstadoCivilInstance();

        request.checkArguments();
        Persona instance = personaDelEstadoCivilPersonapthisPersona;

        instance.add2EstadoCivil(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.DelEstadoCivilPersonaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.DelEstadoCivilPersonaRequest();
            relatedRequest.setEstadoCivilDelEstadoCivilPersonapthisEstadoCivilInstance(personaDelEstadoCivilPersonapevcEstadoCivil);
            relatedRequest.setEstadoCivilDelEstadoCivilPersonapevcPersonaInstance(personaDelEstadoCivilPersonapthisPersona);
            estadoCivilSrv.delEstadoCivilPersonaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPersona());

        return response;
    }

    // Service: InsEstadoPersona

    /*
     * Entry point for service InsEstadoPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insEstadoPersona(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.InsEstadoPersonaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaSrv.insEstadoPersonaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsEstadoPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insEstadoPersonaTxn(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.InsEstadoPersonaRequest request) throws ModelException {
        request.setPersonaInsEstadoPersonapthisPersonaInstance(personaSrv.getByOIDWithHV(request.getPersonaInsEstadoPersonapthisPersona()));
        request.setPersonaInsEstadoPersonapevcEstadoPersonaInstance(estadoPersonaSrv.getByOIDWithHV(request.getPersonaInsEstadoPersonapevcEstadoPersona()));
        return personaSrv.insEstadoPersonaImplementation(request, true);
    }

    /*
     * Implements service InsEstadoPersona
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insEstadoPersonaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.InsEstadoPersonaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Persona personaInsEstadoPersonapthisPersona = request.getPersonaInsEstadoPersonapthisPersonaInstance();
        EstadoPersona personaInsEstadoPersonapevcEstadoPersona = request.getPersonaInsEstadoPersonapevcEstadoPersonaInstance();

        request.checkArguments();
        Persona instance = personaInsEstadoPersonapthisPersona;

        instance.add2EstadoPersona(personaInsEstadoPersonapevcEstadoPersona);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu.InsEstadoPersonaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu.InsEstadoPersonaRequest();
            relatedRequest.setEstadoPersonaInsEstadoPersonapthisEstadoPersonaInstance(personaInsEstadoPersonapevcEstadoPersona);
            relatedRequest.setEstadoPersonaInsEstadoPersonapevcPersonaInstance(personaInsEstadoPersonapthisPersona);
            estadoPersonaSrv.insEstadoPersonaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPersona());

        return response;
    }

    // Service: DelEstadoPersona

    /*
     * Entry point for service DelEstadoPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delEstadoPersona(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.DelEstadoPersonaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaSrv.delEstadoPersonaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelEstadoPersona
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delEstadoPersonaTxn(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.DelEstadoPersonaRequest request) throws ModelException {
        request.setPersonaDelEstadoPersonapthisPersonaInstance(personaSrv.getByOIDWithHV(request.getPersonaDelEstadoPersonapthisPersona()));
        request.setPersonaDelEstadoPersonapevcEstadoPersonaInstance(estadoPersonaSrv.getByOIDWithHV(request.getPersonaDelEstadoPersonapevcEstadoPersona()));
        return personaSrv.delEstadoPersonaImplementation(request, true);
    }

    /*
     * Implements service DelEstadoPersona
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delEstadoPersonaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.DelEstadoPersonaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Persona personaDelEstadoPersonapthisPersona = request.getPersonaDelEstadoPersonapthisPersonaInstance();
        EstadoPersona personaDelEstadoPersonapevcEstadoPersona = request.getPersonaDelEstadoPersonapevcEstadoPersonaInstance();

        request.checkArguments();
        Persona instance = personaDelEstadoPersonapthisPersona;

        instance.add2EstadoPersona(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu.DelEstadoPersonaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu.DelEstadoPersonaRequest();
            relatedRequest.setEstadoPersonaDelEstadoPersonapthisEstadoPersonaInstance(personaDelEstadoPersonapevcEstadoPersona);
            relatedRequest.setEstadoPersonaDelEstadoPersonapevcPersonaInstance(personaDelEstadoPersonapthisPersona);
            estadoPersonaSrv.delEstadoPersonaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPersona());

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
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.CambTipoIdentificacionRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaSrv.cambTipoIdentificacionTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.CambTipoIdentificacionRequest request) throws ModelException {
        request.setPersonaCambTipoIdentificacionpthisPersonaInstance(personaSrv.getByOIDWithHV(request.getPersonaCambTipoIdentificacionpthisPersona()));
        request.setPersonaCambTipoIdentificacionpevcTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(new TipoIdentificacionOid().fromJsonString(request.getPersonaCambTipoIdentificacionpevcTipoIdentificacion())));
        return personaSrv.cambTipoIdentificacionImplementation(request, true);
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
            com.keralty.aeconfiguraciones.viewmodel.persona.siu.CambTipoIdentificacionRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Persona personaCambTipoIdentificacionpthisPersona = request.getPersonaCambTipoIdentificacionpthisPersonaInstance();
        TipoIdentificacion personaCambTipoIdentificacionpevcTipoIdentificacion = request.getPersonaCambTipoIdentificacionpevcTipoIdentificacionInstance();

        request.checkArguments();
        Persona instance = personaCambTipoIdentificacionpthisPersona;
        if (personaCambTipoIdentificacionpevcTipoIdentificacion.getTipoIdentificacionNIT().equals(true)) {
            instance.setPersonaTipoPersona("J");
        } else {
            instance.setPersonaTipoPersona("N");
        }

        instance.add2TipoIdentificacion(personaCambTipoIdentificacionpevcTipoIdentificacion);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdentificacionRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdentificacionRequest();
            relatedRequest.setTipoIdentificacionCambTipoIdentificacionpthisTipoIdentificacionInstance(personaCambTipoIdentificacionpevcTipoIdentificacion);
            relatedRequest.setTipoIdentificacionCambTipoIdentificacionpevcPersonaInstance(personaCambTipoIdentificacionpthisPersona);
            tipoIdentificacionSrv.cambTipoIdentificacionImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICPersona());

        return response;
    }


    private void suppInfo4ICPersona(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(PersonaConstants.ROLE_NAME_TIPOIDENTIFICACION + "." + TipoIdentificacionConstants.ATTR_NAME_TIPOIDENTIFICACIONNOMBRECORTO, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison1);

            SQLWhereComparison comparison2 = new SQLWhereComparison(select);
            comparison2.setOperator("LIKE");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_NAME_PERSONANUMIDENTIFICACION, Constants.Type.STRING.getTypeName()));
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
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(PersonaConstants.CLASS_NAME, PersonaConstants.ATTR_FIELD_PERSONAIDENTIFICADOR, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildPersonaRelatedInstance(relatedInstanceJsonOid);
        }
        if (PersonaConstants.ROLE_NAME_SOLICITUDESCONTRATANTE.equalsIgnoreCase(relatedInstancePathFirstItem) || PersonaConstants.ROLE_NAME_SOLICITUDESTITULAR.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaRelatedInstance4Solicitud(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaConstants.ROLE_NAME_DIRECCIONES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaRelatedInstance4Direccion(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaConstants.ROLE_NAME_USUARIOS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaRelatedInstance4Usuario(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaConstants.ROLE_NAME_TIPOIDENTIFICACION.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaRelatedInstance4TipoIdentificacion(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaConstants.ROLE_NAME_DEPARTAMENTONACIMIENTO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaRelatedInstance4Departamento(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaConstants.ROLE_NAME_MUNICIPIONACIMIENTO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaRelatedInstance4Municipio(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaConstants.ROLE_NAME_ESTADOCIVIL.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaRelatedInstance4EstadoCivil(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaConstants.ROLE_NAME_ESTADOPERSONA.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaRelatedInstance4EstadoPersona(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildPersonaRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Persona(mapper.readValue(relatedInstanceJsonOid, PersonaOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaRelatedInstance4Solicitud(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Solicitud(mapper.readValue(relatedInstanceJsonOid, SolicitudOid.class)): Solicitud.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaRelatedInstance4Direccion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Direccion(mapper.readValue(relatedInstanceJsonOid, DireccionOid.class)): Direccion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaRelatedInstance4Usuario(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Usuario(mapper.readValue(relatedInstanceJsonOid, UsuarioOid.class)): Usuario.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaRelatedInstance4TipoIdentificacion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoIdentificacion(mapper.readValue(relatedInstanceJsonOid, TipoIdentificacionOid.class)): TipoIdentificacion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaRelatedInstance4Departamento(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Departamento(mapper.readValue(relatedInstanceJsonOid, DepartamentoOid.class)): Departamento.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaRelatedInstance4Municipio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Municipio(mapper.readValue(relatedInstanceJsonOid, MunicipioOid.class)): Municipio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaRelatedInstance4EstadoCivil(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new EstadoCivil(mapper.readValue(relatedInstanceJsonOid, EstadoCivilOid.class)): EstadoCivil.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaRelatedInstance4EstadoPersona(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new EstadoPersona(mapper.readValue(relatedInstanceJsonOid, EstadoPersonaOid.class)): EstadoPersona.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            PersonaOid oid = new PersonaOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(PersonaConstants.ATTR_FIELD_PERSONAIDENTIFICADOR, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getPersonaIdentificador(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(PersonaService.class).error(e.getMessage());
        }
    }

}
