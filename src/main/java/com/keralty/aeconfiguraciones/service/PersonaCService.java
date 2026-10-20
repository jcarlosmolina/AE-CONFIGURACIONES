package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.PersonaCConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.DireccionC;
import com.keralty.aeconfiguraciones.persistence.DirSucursalC;
import com.keralty.aeconfiguraciones.persistence.EstadoCivil;
import com.keralty.aeconfiguraciones.persistence.EstadoPersona;
import com.keralty.aeconfiguraciones.persistence.FamiliaC;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.BeneficiarioCOid;
import com.keralty.aeconfiguraciones.persistence.oid.ContratoCOid;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;
import com.keralty.aeconfiguraciones.persistence.oid.DireccionCOid;
import com.keralty.aeconfiguraciones.persistence.oid.DirSucursalCOid;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoCivilOid;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoPersonaOid;
import com.keralty.aeconfiguraciones.persistence.oid.FamiliaCOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaCOid;
import com.keralty.aeconfiguraciones.persistence.oid.PreexistenciaCOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoEntidadCOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoIdentificacionOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoInstitucionCOid;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.persistence.PreexistenciaC;
import com.keralty.aeconfiguraciones.persistence.TipoEntidadC;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.TipoInstitucionC;
import com.keralty.aeconfiguraciones.repository.PersonaCJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.viewmodel.FilterRequest;
import com.keralty.aeconfiguraciones.viewmodel.FilterVariableRequest;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.QueryResponse;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class PersonaCService extends AbstractService {

    PersonaCJpaRepository repository;

    PersonaCService personaCSrv;

    TipoInstitucionCService tipoInstitucionCSrv;

    TipoEntidadCService tipoEntidadCSrv;

    EstadoCivilService estadoCivilSrv;

    EstadoPersonaService estadoPersonaSrv;

    TipoIdentificacionService tipoIdentificacionSrv;

    MunicipioService municipioSrv;

    DepartamentoService departamentoSrv;

    DireccionCService direccionCSrv;

    DirSucursalCService dirSucursalCSrv;

    PreexistenciaCService preexistenciaCSrv;

    ContratoCService contratoCSrv;

    FamiliaCService familiaCSrv;

    BeneficiarioCService beneficiarioCSrv;

    @Inject
    public PersonaCService(
            PersonaCService personaCSrv,
            TipoInstitucionCService tipoInstitucionCSrv,
            TipoEntidadCService tipoEntidadCSrv,
            EstadoCivilService estadoCivilSrv,
            EstadoPersonaService estadoPersonaSrv,
            TipoIdentificacionService tipoIdentificacionSrv,
            MunicipioService municipioSrv,
            DepartamentoService departamentoSrv,
            DireccionCService direccionCSrv,
            DirSucursalCService dirSucursalCSrv,
            PreexistenciaCService preexistenciaCSrv,
            ContratoCService contratoCSrv,
            FamiliaCService familiaCSrv,
            BeneficiarioCService beneficiarioCSrv,
            PersonaCJpaRepository repository) {
        this.personaCSrv = personaCSrv;
        this.tipoInstitucionCSrv = tipoInstitucionCSrv;
        this.tipoEntidadCSrv = tipoEntidadCSrv;
        this.estadoCivilSrv = estadoCivilSrv;
        this.estadoPersonaSrv = estadoPersonaSrv;
        this.tipoIdentificacionSrv = tipoIdentificacionSrv;
        this.municipioSrv = municipioSrv;
        this.departamentoSrv = departamentoSrv;
        this.direccionCSrv = direccionCSrv;
        this.dirSucursalCSrv = dirSucursalCSrv;
        this.preexistenciaCSrv = preexistenciaCSrv;
        this.contratoCSrv = contratoCSrv;
        this.familiaCSrv = familiaCSrv;
        this.beneficiarioCSrv = beneficiarioCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, PersonaCConstants.CLASS_NAME);
    }

    private List<PersonaC> solvePersonaCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, PersonaCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solvePersonaCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, PersonaCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class PersonaC
     *
     * @return Population of class PersonaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PersonaC> get() {
        List<PersonaC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class PersonaC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class PersonaC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PersonaC> get(QueryRequest queryRequest) {
        return solvePersonaCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solvePersonaCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solvePersonaCTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel();
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
        return new PopulationResponseViewModel(solvePersonaCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of PersonaC by ID
     *
     * @param personaCID Identification field personaCID
     *
     * @return Instance of PersonaC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public PersonaC getById(Long personaCID) {
        return getByOID(new PersonaCOid(personaCID));
    }
    
    /*
     * Returns an instance of PersonaC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of PersonaC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public PersonaC getByOID(PersonaCOid oid) {
        Optional<PersonaC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of PersonaC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of PersonaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public PersonaC getByOIDWithHV(PersonaCOid oid) {
        Optional<PersonaC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<PersonaC> getInstanceByOid(PersonaCOid oid) {
        return oid != null && oid.getPersonaCID() != null
               ? repository.findById(oid.getPersonaCID())
               : Optional.empty();
    }

    /*
     * Returns an instance of PersonaC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of PersonaC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public PersonaC getByJsonOid(String jsonOid) {
        PersonaCOid oid = new PersonaCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of PersonaC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of PersonaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PersonaC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solvePersonaCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of PersonaC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of PersonaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PersonaC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of PersonaC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of PersonaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getPersonaCDynamic(PersonaCOid oid, String displaySetItems) {
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
    public String getSuppInfo(PersonaCOid oid, String dsName) {
        PersonaC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(PersonaC instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods



    // Service: InsTipoInstitucionCPersonaC

    /*
     * Entry point for service InsTipoInstitucionCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTipoInstitucionCPersonaC(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsTipoInstitucionCPersonaCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.insTipoInstitucionCPersonaCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTipoInstitucionCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTipoInstitucionCPersonaCTxn(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsTipoInstitucionCPersonaCRequest request) throws ModelException {
        request.setPersonaCInsTipoInstitucionCPersonaCpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCInsTipoInstitucionCPersonaCpthisPersonaC()));
        request.setPersonaCInsTipoInstitucionCPersonaCpevcTipoInstitucionCInstance(tipoInstitucionCSrv.getByOIDWithHV(request.getPersonaCInsTipoInstitucionCPersonaCpevcTipoInstitucionC()));
        return personaCSrv.insTipoInstitucionCPersonaCImplementation(request, true);
    }

    /*
     * Implements service InsTipoInstitucionCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTipoInstitucionCPersonaCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsTipoInstitucionCPersonaCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC personaCInsTipoInstitucionCPersonaCpthisPersonaC = request.getPersonaCInsTipoInstitucionCPersonaCpthisPersonaCInstance();
        TipoInstitucionC personaCInsTipoInstitucionCPersonaCpevcTipoInstitucionC = request.getPersonaCInsTipoInstitucionCPersonaCpevcTipoInstitucionCInstance();

        request.checkArguments();
        PersonaC instance = personaCInsTipoInstitucionCPersonaCpthisPersonaC;

        instance.add2TipoInstitucionC(personaCInsTipoInstitucionCPersonaCpevcTipoInstitucionC);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.InsTipoInstitucionCPersonaCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.InsTipoInstitucionCPersonaCRequest();
            relatedRequest.setTipoInstitucionCInsTipoInstitucionCPersonaCpthisTipoInstitucionCInstance(personaCInsTipoInstitucionCPersonaCpevcTipoInstitucionC);
            relatedRequest.setTipoInstitucionCInsTipoInstitucionCPersonaCpevcPersonaCInstance(personaCInsTipoInstitucionCPersonaCpthisPersonaC);
            tipoInstitucionCSrv.insTipoInstitucionCPersonaCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelTipoInstitucionCPersonaC

    /*
     * Entry point for service DelTipoInstitucionCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTipoInstitucionCPersonaC(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelTipoInstitucionCPersonaCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.delTipoInstitucionCPersonaCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTipoInstitucionCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTipoInstitucionCPersonaCTxn(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelTipoInstitucionCPersonaCRequest request) throws ModelException {
        request.setPersonaCDelTipoInstitucionCPersonaCpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCDelTipoInstitucionCPersonaCpthisPersonaC()));
        request.setPersonaCDelTipoInstitucionCPersonaCpevcTipoInstitucionCInstance(tipoInstitucionCSrv.getByOIDWithHV(request.getPersonaCDelTipoInstitucionCPersonaCpevcTipoInstitucionC()));
        return personaCSrv.delTipoInstitucionCPersonaCImplementation(request, true);
    }

    /*
     * Implements service DelTipoInstitucionCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTipoInstitucionCPersonaCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelTipoInstitucionCPersonaCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC personaCDelTipoInstitucionCPersonaCpthisPersonaC = request.getPersonaCDelTipoInstitucionCPersonaCpthisPersonaCInstance();
        TipoInstitucionC personaCDelTipoInstitucionCPersonaCpevcTipoInstitucionC = request.getPersonaCDelTipoInstitucionCPersonaCpevcTipoInstitucionCInstance();

        request.checkArguments();
        PersonaC instance = personaCDelTipoInstitucionCPersonaCpthisPersonaC;

        instance.add2TipoInstitucionC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.DelTipoInstitucionCPersonaCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.DelTipoInstitucionCPersonaCRequest();
            relatedRequest.setTipoInstitucionCDelTipoInstitucionCPersonaCpthisTipoInstitucionCInstance(personaCDelTipoInstitucionCPersonaCpevcTipoInstitucionC);
            relatedRequest.setTipoInstitucionCDelTipoInstitucionCPersonaCpevcPersonaCInstance(personaCDelTipoInstitucionCPersonaCpthisPersonaC);
            tipoInstitucionCSrv.delTipoInstitucionCPersonaCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsTipoEntidadCPersonaC

    /*
     * Entry point for service InsTipoEntidadCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTipoEntidadCPersonaC(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsTipoEntidadCPersonaCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.insTipoEntidadCPersonaCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTipoEntidadCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTipoEntidadCPersonaCTxn(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsTipoEntidadCPersonaCRequest request) throws ModelException {
        request.setPersonaCInsTipoEntidadCPersonaCpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCInsTipoEntidadCPersonaCpthisPersonaC()));
        request.setPersonaCInsTipoEntidadCPersonaCpevcTipoEntidadCInstance(tipoEntidadCSrv.getByOIDWithHV(request.getPersonaCInsTipoEntidadCPersonaCpevcTipoEntidadC()));
        return personaCSrv.insTipoEntidadCPersonaCImplementation(request, true);
    }

    /*
     * Implements service InsTipoEntidadCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTipoEntidadCPersonaCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsTipoEntidadCPersonaCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC personaCInsTipoEntidadCPersonaCpthisPersonaC = request.getPersonaCInsTipoEntidadCPersonaCpthisPersonaCInstance();
        TipoEntidadC personaCInsTipoEntidadCPersonaCpevcTipoEntidadC = request.getPersonaCInsTipoEntidadCPersonaCpevcTipoEntidadCInstance();

        request.checkArguments();
        PersonaC instance = personaCInsTipoEntidadCPersonaCpthisPersonaC;

        instance.add2TipoEntidadC(personaCInsTipoEntidadCPersonaCpevcTipoEntidadC);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.InsTipoEntidadCPersonaCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.InsTipoEntidadCPersonaCRequest();
            relatedRequest.setTipoEntidadCInsTipoEntidadCPersonaCpthisTipoEntidadCInstance(personaCInsTipoEntidadCPersonaCpevcTipoEntidadC);
            relatedRequest.setTipoEntidadCInsTipoEntidadCPersonaCpevcPersonaCInstance(personaCInsTipoEntidadCPersonaCpthisPersonaC);
            tipoEntidadCSrv.insTipoEntidadCPersonaCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelTipoEntidadCPersonaC

    /*
     * Entry point for service DelTipoEntidadCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTipoEntidadCPersonaC(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelTipoEntidadCPersonaCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.delTipoEntidadCPersonaCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTipoEntidadCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTipoEntidadCPersonaCTxn(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelTipoEntidadCPersonaCRequest request) throws ModelException {
        request.setPersonaCDelTipoEntidadCPersonaCpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCDelTipoEntidadCPersonaCpthisPersonaC()));
        request.setPersonaCDelTipoEntidadCPersonaCpevcTipoEntidadCInstance(tipoEntidadCSrv.getByOIDWithHV(request.getPersonaCDelTipoEntidadCPersonaCpevcTipoEntidadC()));
        return personaCSrv.delTipoEntidadCPersonaCImplementation(request, true);
    }

    /*
     * Implements service DelTipoEntidadCPersonaC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTipoEntidadCPersonaCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelTipoEntidadCPersonaCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC personaCDelTipoEntidadCPersonaCpthisPersonaC = request.getPersonaCDelTipoEntidadCPersonaCpthisPersonaCInstance();
        TipoEntidadC personaCDelTipoEntidadCPersonaCpevcTipoEntidadC = request.getPersonaCDelTipoEntidadCPersonaCpevcTipoEntidadCInstance();

        request.checkArguments();
        PersonaC instance = personaCDelTipoEntidadCPersonaCpthisPersonaC;

        instance.add2TipoEntidadC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.DelTipoEntidadCPersonaCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.DelTipoEntidadCPersonaCRequest();
            relatedRequest.setTipoEntidadCDelTipoEntidadCPersonaCpthisTipoEntidadCInstance(personaCDelTipoEntidadCPersonaCpevcTipoEntidadC);
            relatedRequest.setTipoEntidadCDelTipoEntidadCPersonaCpevcPersonaCInstance(personaCDelTipoEntidadCPersonaCpthisPersonaC);
            tipoEntidadCSrv.delTipoEntidadCPersonaCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsEstadoCivilC

    /*
     * Entry point for service InsEstadoCivilC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insEstadoCivilC(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsEstadoCivilCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.insEstadoCivilCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsEstadoCivilC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insEstadoCivilCTxn(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsEstadoCivilCRequest request) throws ModelException {
        request.setPersonaCInsEstadoCivilCpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCInsEstadoCivilCpthisPersonaC()));
        request.setPersonaCInsEstadoCivilCpevcEstadoCivilInstance(estadoCivilSrv.getByOIDWithHV(request.getPersonaCInsEstadoCivilCpevcEstadoCivil()));
        return personaCSrv.insEstadoCivilCImplementation(request, true);
    }

    /*
     * Implements service InsEstadoCivilC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insEstadoCivilCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsEstadoCivilCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC personaCInsEstadoCivilCpthisPersonaC = request.getPersonaCInsEstadoCivilCpthisPersonaCInstance();
        EstadoCivil personaCInsEstadoCivilCpevcEstadoCivil = request.getPersonaCInsEstadoCivilCpevcEstadoCivilInstance();

        request.checkArguments();
        PersonaC instance = personaCInsEstadoCivilCpthisPersonaC;

        instance.add2EstadoCivilC(personaCInsEstadoCivilCpevcEstadoCivil);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.InsEstadoCivilCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.InsEstadoCivilCRequest();
            relatedRequest.setEstadoCivilInsEstadoCivilCpthisEstadoCivilInstance(personaCInsEstadoCivilCpevcEstadoCivil);
            relatedRequest.setEstadoCivilInsEstadoCivilCpevcPersonaCInstance(personaCInsEstadoCivilCpthisPersonaC);
            estadoCivilSrv.insEstadoCivilCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelEstadoCivilC

    /*
     * Entry point for service DelEstadoCivilC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delEstadoCivilC(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelEstadoCivilCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.delEstadoCivilCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelEstadoCivilC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delEstadoCivilCTxn(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelEstadoCivilCRequest request) throws ModelException {
        request.setPersonaCDelEstadoCivilCpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCDelEstadoCivilCpthisPersonaC()));
        request.setPersonaCDelEstadoCivilCpevcEstadoCivilInstance(estadoCivilSrv.getByOIDWithHV(request.getPersonaCDelEstadoCivilCpevcEstadoCivil()));
        return personaCSrv.delEstadoCivilCImplementation(request, true);
    }

    /*
     * Implements service DelEstadoCivilC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delEstadoCivilCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelEstadoCivilCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC personaCDelEstadoCivilCpthisPersonaC = request.getPersonaCDelEstadoCivilCpthisPersonaCInstance();
        EstadoCivil personaCDelEstadoCivilCpevcEstadoCivil = request.getPersonaCDelEstadoCivilCpevcEstadoCivilInstance();

        request.checkArguments();
        PersonaC instance = personaCDelEstadoCivilCpthisPersonaC;

        instance.add2EstadoCivilC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.DelEstadoCivilCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.DelEstadoCivilCRequest();
            relatedRequest.setEstadoCivilDelEstadoCivilCpthisEstadoCivilInstance(personaCDelEstadoCivilCpevcEstadoCivil);
            relatedRequest.setEstadoCivilDelEstadoCivilCpevcPersonaCInstance(personaCDelEstadoCivilCpthisPersonaC);
            estadoCivilSrv.delEstadoCivilCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsEstadoPersonaC

    /*
     * Entry point for service InsEstadoPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insEstadoPersonaC(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsEstadoPersonaCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.insEstadoPersonaCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsEstadoPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insEstadoPersonaCTxn(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsEstadoPersonaCRequest request) throws ModelException {
        request.setPersonaCInsEstadoPersonaCpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCInsEstadoPersonaCpthisPersonaC()));
        request.setPersonaCInsEstadoPersonaCpevcEstadoPersonaInstance(estadoPersonaSrv.getByOIDWithHV(request.getPersonaCInsEstadoPersonaCpevcEstadoPersona()));
        return personaCSrv.insEstadoPersonaCImplementation(request, true);
    }

    /*
     * Implements service InsEstadoPersonaC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insEstadoPersonaCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsEstadoPersonaCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC personaCInsEstadoPersonaCpthisPersonaC = request.getPersonaCInsEstadoPersonaCpthisPersonaCInstance();
        EstadoPersona personaCInsEstadoPersonaCpevcEstadoPersona = request.getPersonaCInsEstadoPersonaCpevcEstadoPersonaInstance();

        request.checkArguments();
        PersonaC instance = personaCInsEstadoPersonaCpthisPersonaC;

        instance.add2EstadoPersonaC(personaCInsEstadoPersonaCpevcEstadoPersona);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu.InsEstadoPersonaCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu.InsEstadoPersonaCRequest();
            relatedRequest.setEstadoPersonaInsEstadoPersonaCpthisEstadoPersonaInstance(personaCInsEstadoPersonaCpevcEstadoPersona);
            relatedRequest.setEstadoPersonaInsEstadoPersonaCpevcPersonaCInstance(personaCInsEstadoPersonaCpthisPersonaC);
            estadoPersonaSrv.insEstadoPersonaCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelEstadoPersonaC

    /*
     * Entry point for service DelEstadoPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delEstadoPersonaC(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelEstadoPersonaCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.delEstadoPersonaCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelEstadoPersonaC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delEstadoPersonaCTxn(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelEstadoPersonaCRequest request) throws ModelException {
        request.setPersonaCDelEstadoPersonaCpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCDelEstadoPersonaCpthisPersonaC()));
        request.setPersonaCDelEstadoPersonaCpevcEstadoPersonaInstance(estadoPersonaSrv.getByOIDWithHV(request.getPersonaCDelEstadoPersonaCpevcEstadoPersona()));
        return personaCSrv.delEstadoPersonaCImplementation(request, true);
    }

    /*
     * Implements service DelEstadoPersonaC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delEstadoPersonaCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelEstadoPersonaCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC personaCDelEstadoPersonaCpthisPersonaC = request.getPersonaCDelEstadoPersonaCpthisPersonaCInstance();
        EstadoPersona personaCDelEstadoPersonaCpevcEstadoPersona = request.getPersonaCDelEstadoPersonaCpevcEstadoPersonaInstance();

        request.checkArguments();
        PersonaC instance = personaCDelEstadoPersonaCpthisPersonaC;

        instance.add2EstadoPersonaC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu.DelEstadoPersonaCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu.DelEstadoPersonaCRequest();
            relatedRequest.setEstadoPersonaDelEstadoPersonaCpthisEstadoPersonaInstance(personaCDelEstadoPersonaCpevcEstadoPersona);
            relatedRequest.setEstadoPersonaDelEstadoPersonaCpevcPersonaCInstance(personaCDelEstadoPersonaCpthisPersonaC);
            estadoPersonaSrv.delEstadoPersonaCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

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
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.CambTipoIdentificacionCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.cambTipoIdentificacionCTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.CambTipoIdentificacionCRequest request) throws ModelException {
        request.setPersonaCCambTipoIdentificacionCpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCCambTipoIdentificacionCpthisPersonaC()));
        request.setPersonaCCambTipoIdentificacionCpevcTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getPersonaCCambTipoIdentificacionCpevcTipoIdentificacion()));
        return personaCSrv.cambTipoIdentificacionCImplementation(request, true);
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
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.CambTipoIdentificacionCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC personaCCambTipoIdentificacionCpthisPersonaC = request.getPersonaCCambTipoIdentificacionCpthisPersonaCInstance();
        TipoIdentificacion personaCCambTipoIdentificacionCpevcTipoIdentificacion = request.getPersonaCCambTipoIdentificacionCpevcTipoIdentificacionInstance();

        request.checkArguments();
        PersonaC instance = personaCCambTipoIdentificacionCpthisPersonaC;

        instance.add2TipoIdentificacionC(personaCCambTipoIdentificacionCpevcTipoIdentificacion);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdentificacionCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CambTipoIdentificacionCRequest();
            relatedRequest.setTipoIdentificacionCambTipoIdentificacionCpthisTipoIdentificacionInstance(personaCCambTipoIdentificacionCpevcTipoIdentificacion);
            relatedRequest.setTipoIdentificacionCambTipoIdentificacionCpevcPersonaCInstance(personaCCambTipoIdentificacionCpthisPersonaC);
            tipoIdentificacionSrv.cambTipoIdentificacionCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsMunicipioCNac

    /*
     * Entry point for service InsMunicipioCNac
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insMunicipioCNac(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsMunicipioCNacRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.insMunicipioCNacTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsMunicipioCNac
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insMunicipioCNacTxn(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsMunicipioCNacRequest request) throws ModelException {
        request.setPersonaCInsMunicipioCNacpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCInsMunicipioCNacpthisPersonaC()));
        request.setPersonaCInsMunicipioCNacpevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getPersonaCInsMunicipioCNacpevcMunicipio()));
        return personaCSrv.insMunicipioCNacImplementation(request, true);
    }

    /*
     * Implements service InsMunicipioCNac
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insMunicipioCNacImplementation(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsMunicipioCNacRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC personaCInsMunicipioCNacpthisPersonaC = request.getPersonaCInsMunicipioCNacpthisPersonaCInstance();
        Municipio personaCInsMunicipioCNacpevcMunicipio = request.getPersonaCInsMunicipioCNacpevcMunicipioInstance();

        request.checkArguments();
        PersonaC instance = personaCInsMunicipioCNacpthisPersonaC;

        instance.add2MunicipioCNac(personaCInsMunicipioCNacpevcMunicipio);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioCNacRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioCNacRequest();
            relatedRequest.setMunicipioInsMunicipioCNacpthisMunicipioInstance(personaCInsMunicipioCNacpevcMunicipio);
            relatedRequest.setMunicipioInsMunicipioCNacpevcPersonaCInstance(personaCInsMunicipioCNacpthisPersonaC);
            municipioSrv.insMunicipioCNacImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelMunicipioCNac

    /*
     * Entry point for service DelMunicipioCNac
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delMunicipioCNac(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelMunicipioCNacRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.delMunicipioCNacTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelMunicipioCNac
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delMunicipioCNacTxn(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelMunicipioCNacRequest request) throws ModelException {
        request.setPersonaCDelMunicipioCNacpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCDelMunicipioCNacpthisPersonaC()));
        request.setPersonaCDelMunicipioCNacpevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getPersonaCDelMunicipioCNacpevcMunicipio()));
        return personaCSrv.delMunicipioCNacImplementation(request, true);
    }

    /*
     * Implements service DelMunicipioCNac
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delMunicipioCNacImplementation(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelMunicipioCNacRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC personaCDelMunicipioCNacpthisPersonaC = request.getPersonaCDelMunicipioCNacpthisPersonaCInstance();
        Municipio personaCDelMunicipioCNacpevcMunicipio = request.getPersonaCDelMunicipioCNacpevcMunicipioInstance();

        request.checkArguments();
        PersonaC instance = personaCDelMunicipioCNacpthisPersonaC;

        instance.add2MunicipioCNac(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioCNacRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioCNacRequest();
            relatedRequest.setMunicipioDelMunicipioCNacpthisMunicipioInstance(personaCDelMunicipioCNacpevcMunicipio);
            relatedRequest.setMunicipioDelMunicipioCNacpevcPersonaCInstance(personaCDelMunicipioCNacpthisPersonaC);
            municipioSrv.delMunicipioCNacImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsDepartamentoCNac

    /*
     * Entry point for service InsDepartamentoCNac
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insDepartamentoCNac(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsDepartamentoCNacRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.insDepartamentoCNacTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsDepartamentoCNac
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insDepartamentoCNacTxn(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsDepartamentoCNacRequest request) throws ModelException {
        request.setPersonaCInsDepartamentoCNacpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCInsDepartamentoCNacpthisPersonaC()));
        request.setPersonaCInsDepartamentoCNacpevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getPersonaCInsDepartamentoCNacpevcDepartamento()));
        return personaCSrv.insDepartamentoCNacImplementation(request, true);
    }

    /*
     * Implements service InsDepartamentoCNac
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insDepartamentoCNacImplementation(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.InsDepartamentoCNacRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC personaCInsDepartamentoCNacpthisPersonaC = request.getPersonaCInsDepartamentoCNacpthisPersonaCInstance();
        Departamento personaCInsDepartamentoCNacpevcDepartamento = request.getPersonaCInsDepartamentoCNacpevcDepartamentoInstance();

        request.checkArguments();
        PersonaC instance = personaCInsDepartamentoCNacpthisPersonaC;

        instance.add2DepartamentoCNac(personaCInsDepartamentoCNacpevcDepartamento);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoCNacRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoCNacRequest();
            relatedRequest.setDepartamentoInsDepartamentoCNacpthisDepartamentoInstance(personaCInsDepartamentoCNacpevcDepartamento);
            relatedRequest.setDepartamentoInsDepartamentoCNacpevcPersonaCInstance(personaCInsDepartamentoCNacpthisPersonaC);
            departamentoSrv.insDepartamentoCNacImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelDepartamentoCNac

    /*
     * Entry point for service DelDepartamentoCNac
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delDepartamentoCNac(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelDepartamentoCNacRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = personaCSrv.delDepartamentoCNacTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelDepartamentoCNac
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delDepartamentoCNacTxn(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelDepartamentoCNacRequest request) throws ModelException {
        request.setPersonaCDelDepartamentoCNacpthisPersonaCInstance(personaCSrv.getByOIDWithHV(request.getPersonaCDelDepartamentoCNacpthisPersonaC()));
        request.setPersonaCDelDepartamentoCNacpevcDepartamentoInstance(departamentoSrv.getByOIDWithHV(request.getPersonaCDelDepartamentoCNacpevcDepartamento()));
        return personaCSrv.delDepartamentoCNacImplementation(request, true);
    }

    /*
     * Implements service DelDepartamentoCNac
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delDepartamentoCNacImplementation(
            com.keralty.aeconfiguraciones.viewmodel.personac.siu.DelDepartamentoCNacRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PersonaC personaCDelDepartamentoCNacpthisPersonaC = request.getPersonaCDelDepartamentoCNacpthisPersonaCInstance();
        Departamento personaCDelDepartamentoCNacpevcDepartamento = request.getPersonaCDelDepartamentoCNacpevcDepartamentoInstance();

        request.checkArguments();
        PersonaC instance = personaCDelDepartamentoCNacpthisPersonaC;

        instance.add2DepartamentoCNac(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoCNacRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoCNacRequest();
            relatedRequest.setDepartamentoDelDepartamentoCNacpthisDepartamentoInstance(personaCDelDepartamentoCNacpevcDepartamento);
            relatedRequest.setDepartamentoDelDepartamentoCNacpevcPersonaCInstance(personaCDelDepartamentoCNacpthisPersonaC);
            departamentoSrv.delDepartamentoCNacImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

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
        select.getOrderByPart().addItem(Constants.normalizePath(PersonaCConstants.CLASS_NAME, PersonaCConstants.ATTR_FIELD_PERSONACID, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildPersonaCRelatedInstance(relatedInstanceJsonOid);
        }
        if (PersonaCConstants.ROLE_NAME_DIRECCIONESC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4DireccionC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_DIRSUCURSALESC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4DirSucursalC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_PREEXISTENCIASC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4PreexistenciaC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_TIPOINSTITUCIONC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4TipoInstitucionC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_TIPOENTIDADC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4TipoEntidadC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_ESTADOCIVILC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4EstadoCivil(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_ESTADOPERSONAC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4EstadoPersona(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_TIPOIDENTIFICACIONC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4TipoIdentificacion(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_MUNICIPIOCNAC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4Municipio(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_DEPARTAMENTOCNAC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4Departamento(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_CONTRATOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4ContratoC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_FAMILIASC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4FamiliaC(relatedInstanceJsonOid, remainingPath);
        }
        if (PersonaCConstants.ROLE_NAME_BENEFICIARIOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildPersonaCRelatedInstance4BeneficiarioC(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildPersonaCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new PersonaC(mapper.readValue(relatedInstanceJsonOid, PersonaCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4DireccionC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new DireccionC(mapper.readValue(relatedInstanceJsonOid, DireccionCOid.class)): DireccionC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4DirSucursalC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new DirSucursalC(mapper.readValue(relatedInstanceJsonOid, DirSucursalCOid.class)): DirSucursalC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4PreexistenciaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PreexistenciaC(mapper.readValue(relatedInstanceJsonOid, PreexistenciaCOid.class)): PreexistenciaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4TipoInstitucionC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoInstitucionC(mapper.readValue(relatedInstanceJsonOid, TipoInstitucionCOid.class)): TipoInstitucionC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4TipoEntidadC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoEntidadC(mapper.readValue(relatedInstanceJsonOid, TipoEntidadCOid.class)): TipoEntidadC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4EstadoCivil(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new EstadoCivil(mapper.readValue(relatedInstanceJsonOid, EstadoCivilOid.class)): EstadoCivil.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4EstadoPersona(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new EstadoPersona(mapper.readValue(relatedInstanceJsonOid, EstadoPersonaOid.class)): EstadoPersona.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4TipoIdentificacion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoIdentificacion(mapper.readValue(relatedInstanceJsonOid, TipoIdentificacionOid.class)): TipoIdentificacion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4Municipio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Municipio(mapper.readValue(relatedInstanceJsonOid, MunicipioOid.class)): Municipio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4Departamento(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Departamento(mapper.readValue(relatedInstanceJsonOid, DepartamentoOid.class)): Departamento.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4ContratoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ContratoC(mapper.readValue(relatedInstanceJsonOid, ContratoCOid.class)): ContratoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4FamiliaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new FamiliaC(mapper.readValue(relatedInstanceJsonOid, FamiliaCOid.class)): FamiliaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildPersonaCRelatedInstance4BeneficiarioC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new BeneficiarioC(mapper.readValue(relatedInstanceJsonOid, BeneficiarioCOid.class)): BeneficiarioC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            PersonaCOid oid = new PersonaCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(PersonaCConstants.ATTR_FIELD_PERSONACID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getPersonaCID(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(PersonaCService.class).error(e.getMessage());
        }
    }

}
