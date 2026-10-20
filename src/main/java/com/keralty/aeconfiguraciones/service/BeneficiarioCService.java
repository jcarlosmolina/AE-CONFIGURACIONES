package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.global.BeneficiarioCConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.ContratoCConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.AnexoC;
import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.Companyia;
import com.keralty.aeconfiguraciones.persistence.ConceptoC;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.Eps;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.FamiliaC;
import com.keralty.aeconfiguraciones.persistence.oid.AnexoCOid;
import com.keralty.aeconfiguraciones.persistence.oid.BeneficiarioCOid;
import com.keralty.aeconfiguraciones.persistence.oid.CompanyiaOid;
import com.keralty.aeconfiguraciones.persistence.oid.ConceptoCOid;
import com.keralty.aeconfiguraciones.persistence.oid.ContratoCOid;
import com.keralty.aeconfiguraciones.persistence.oid.EpsOid;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioOid;
import com.keralty.aeconfiguraciones.persistence.oid.FamiliaCOid;
import com.keralty.aeconfiguraciones.persistence.oid.ParentescoOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaCOid;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.repository.BeneficiarioCJpaRepository;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class BeneficiarioCService extends AbstractService {

    BeneficiarioCJpaRepository repository;

    BeneficiarioCService beneficiarioCSrv;

    EstadoBeneficiarioService estadoBeneficiarioSrv;

    ParentescoService parentescoSrv;

    EpsService epsSrv;

    CompanyiaService companyiaSrv;

    PersonaCService personaCSrv;

    AnexoCService anexoCSrv;

    ConceptoCService conceptoCSrv;

    ContratoCService contratoCSrv;

    FamiliaCService familiaCSrv;

    @Inject
    public BeneficiarioCService(
            BeneficiarioCService beneficiarioCSrv,
            EstadoBeneficiarioService estadoBeneficiarioSrv,
            ParentescoService parentescoSrv,
            EpsService epsSrv,
            CompanyiaService companyiaSrv,
            PersonaCService personaCSrv,
            AnexoCService anexoCSrv,
            ConceptoCService conceptoCSrv,
            ContratoCService contratoCSrv,
            FamiliaCService familiaCSrv,
            BeneficiarioCJpaRepository repository) {
        this.beneficiarioCSrv = beneficiarioCSrv;
        this.estadoBeneficiarioSrv = estadoBeneficiarioSrv;
        this.parentescoSrv = parentescoSrv;
        this.epsSrv = epsSrv;
        this.companyiaSrv = companyiaSrv;
        this.personaCSrv = personaCSrv;
        this.anexoCSrv = anexoCSrv;
        this.conceptoCSrv = conceptoCSrv;
        this.contratoCSrv = contratoCSrv;
        this.familiaCSrv = familiaCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, BeneficiarioCConstants.CLASS_NAME);
    }

    private List<BeneficiarioC> solveBeneficiarioCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, BeneficiarioCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveBeneficiarioCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, BeneficiarioCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class BeneficiarioC
     *
     * @return Population of class BeneficiarioC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<BeneficiarioC> get() {
        List<BeneficiarioC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class BeneficiarioC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class BeneficiarioC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<BeneficiarioC> get(QueryRequest queryRequest) {
        return solveBeneficiarioCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveBeneficiarioCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveBeneficiarioCTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveBeneficiarioCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of BeneficiarioC by ID
     *
     * @param contratoCContratoCID Identification field contratoCContratoCID
     *
     * @param beneficiarioCID Identification field beneficiarioCID
     *
     * @return Instance of BeneficiarioC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public BeneficiarioC getById(Long contratoCContratoCID, Long beneficiarioCID) {
        return getByOID(new BeneficiarioCOid(contratoCContratoCID, beneficiarioCID));
    }
    
    /*
     * Returns an instance of BeneficiarioC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of BeneficiarioC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public BeneficiarioC getByOID(BeneficiarioCOid oid) {
        Optional<BeneficiarioC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of BeneficiarioC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of BeneficiarioC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public BeneficiarioC getByOIDWithHV(BeneficiarioCOid oid) {
        Optional<BeneficiarioC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<BeneficiarioC> getInstanceByOid(BeneficiarioCOid oid) {
        return oid != null && oid.getContratoCContratoCID() != null
               ? repository.findById(oid)
               : Optional.empty();
    }

    /*
     * Returns an instance of BeneficiarioC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of BeneficiarioC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public BeneficiarioC getByJsonOid(String jsonOid) {
        BeneficiarioCOid oid = new BeneficiarioCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of BeneficiarioC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of BeneficiarioC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<BeneficiarioC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveBeneficiarioCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of BeneficiarioC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of BeneficiarioC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<BeneficiarioC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of BeneficiarioC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of BeneficiarioC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getBeneficiarioCDynamic(BeneficiarioCOid oid, String displaySetItems) {
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
    public String getSuppInfo(BeneficiarioCOid oid, String dsName) {
        BeneficiarioC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(BeneficiarioC instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods



    // Service: InsEstadoBeneficiarioC

    /*
     * Entry point for service InsEstadoBeneficiarioC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insEstadoBeneficiarioC(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.InsEstadoBeneficiarioCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = beneficiarioCSrv.insEstadoBeneficiarioCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsEstadoBeneficiarioC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insEstadoBeneficiarioCTxn(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.InsEstadoBeneficiarioCRequest request) throws ModelException {
        request.setBeneficiarioCInsEstadoBeneficiarioCpthisBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getBeneficiarioCInsEstadoBeneficiarioCpthisBeneficiarioC()));
        request.setBeneficiarioCInsEstadoBeneficiarioCpevcEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getBeneficiarioCInsEstadoBeneficiarioCpevcEstadoBeneficiario()));
        return beneficiarioCSrv.insEstadoBeneficiarioCImplementation(request, true);
    }

    /*
     * Implements service InsEstadoBeneficiarioC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insEstadoBeneficiarioCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.InsEstadoBeneficiarioCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        BeneficiarioC beneficiarioCInsEstadoBeneficiarioCpthisBeneficiarioC = request.getBeneficiarioCInsEstadoBeneficiarioCpthisBeneficiarioCInstance();
        EstadoBeneficiario beneficiarioCInsEstadoBeneficiarioCpevcEstadoBeneficiario = request.getBeneficiarioCInsEstadoBeneficiarioCpevcEstadoBeneficiarioInstance();

        request.checkArguments();
        BeneficiarioC instance = beneficiarioCInsEstadoBeneficiarioCpthisBeneficiarioC;

        instance.add2EstadoBeneficiarioC(beneficiarioCInsEstadoBeneficiarioCpevcEstadoBeneficiario);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioCRequest();
            relatedRequest.setEstadoBeneficiarioInsEstadoBeneficiarioCpthisEstadoBeneficiarioInstance(beneficiarioCInsEstadoBeneficiarioCpevcEstadoBeneficiario);
            relatedRequest.setEstadoBeneficiarioInsEstadoBeneficiarioCpevcBeneficiarioCInstance(beneficiarioCInsEstadoBeneficiarioCpthisBeneficiarioC);
            estadoBeneficiarioSrv.insEstadoBeneficiarioCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelEstadoBeneficiarioC

    /*
     * Entry point for service DelEstadoBeneficiarioC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delEstadoBeneficiarioC(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.DelEstadoBeneficiarioCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = beneficiarioCSrv.delEstadoBeneficiarioCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelEstadoBeneficiarioC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delEstadoBeneficiarioCTxn(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.DelEstadoBeneficiarioCRequest request) throws ModelException {
        request.setBeneficiarioCDelEstadoBeneficiarioCpthisBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getBeneficiarioCDelEstadoBeneficiarioCpthisBeneficiarioC()));
        request.setBeneficiarioCDelEstadoBeneficiarioCpevcEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getBeneficiarioCDelEstadoBeneficiarioCpevcEstadoBeneficiario()));
        return beneficiarioCSrv.delEstadoBeneficiarioCImplementation(request, true);
    }

    /*
     * Implements service DelEstadoBeneficiarioC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delEstadoBeneficiarioCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.DelEstadoBeneficiarioCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        BeneficiarioC beneficiarioCDelEstadoBeneficiarioCpthisBeneficiarioC = request.getBeneficiarioCDelEstadoBeneficiarioCpthisBeneficiarioCInstance();
        EstadoBeneficiario beneficiarioCDelEstadoBeneficiarioCpevcEstadoBeneficiario = request.getBeneficiarioCDelEstadoBeneficiarioCpevcEstadoBeneficiarioInstance();

        request.checkArguments();
        BeneficiarioC instance = beneficiarioCDelEstadoBeneficiarioCpthisBeneficiarioC;

        instance.add2EstadoBeneficiarioC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.DelEstadoBeneficiarioCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.DelEstadoBeneficiarioCRequest();
            relatedRequest.setEstadoBeneficiarioDelEstadoBeneficiarioCpthisEstadoBeneficiarioInstance(beneficiarioCDelEstadoBeneficiarioCpevcEstadoBeneficiario);
            relatedRequest.setEstadoBeneficiarioDelEstadoBeneficiarioCpevcBeneficiarioCInstance(beneficiarioCDelEstadoBeneficiarioCpthisBeneficiarioC);
            estadoBeneficiarioSrv.delEstadoBeneficiarioCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: chgParentescoC

    /*
     * Entry point for service chgParentescoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse chgParentescoC(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.ChgParentescoCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = beneficiarioCSrv.chgParentescoCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service chgParentescoC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse chgParentescoCTxn(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.ChgParentescoCRequest request) throws ModelException {
        request.setBeneficiarioCchgParentescoCpthisBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getBeneficiarioCchgParentescoCpthisBeneficiarioC()));
        request.setBeneficiarioCchgParentescoCpevcParentescoInstance(parentescoSrv.getByOIDWithHV(request.getBeneficiarioCchgParentescoCpevcParentesco()));
        return beneficiarioCSrv.chgParentescoCImplementation(request, true);
    }

    /*
     * Implements service chgParentescoC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse chgParentescoCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.ChgParentescoCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        BeneficiarioC beneficiarioCchgParentescoCpthisBeneficiarioC = request.getBeneficiarioCchgParentescoCpthisBeneficiarioCInstance();
        Parentesco beneficiarioCchgParentescoCpevcParentesco = request.getBeneficiarioCchgParentescoCpevcParentescoInstance();

        request.checkArguments();
        BeneficiarioC instance = beneficiarioCchgParentescoCpthisBeneficiarioC;

        instance.add2ParentescoC(beneficiarioCchgParentescoCpevcParentesco);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.ChgParentescoCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.ChgParentescoCRequest();
            relatedRequest.setParentescochgParentescoCpthisParentescoInstance(beneficiarioCchgParentescoCpevcParentesco);
            relatedRequest.setParentescochgParentescoCpevcBeneficiarioCInstance(beneficiarioCchgParentescoCpthisBeneficiarioC);
            parentescoSrv.chgParentescoCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsEPSC

    /*
     * Entry point for service InsEPSC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insEPSC(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.InsEPSCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = beneficiarioCSrv.insEPSCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsEPSC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insEPSCTxn(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.InsEPSCRequest request) throws ModelException {
        request.setBeneficiarioCInsEPSCpthisBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getBeneficiarioCInsEPSCpthisBeneficiarioC()));
        request.setBeneficiarioCInsEPSCpevcEpsInstance(epsSrv.getByOIDWithHV(request.getBeneficiarioCInsEPSCpevcEps()));
        return beneficiarioCSrv.insEPSCImplementation(request, true);
    }

    /*
     * Implements service InsEPSC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insEPSCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.InsEPSCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        BeneficiarioC beneficiarioCInsEPSCpthisBeneficiarioC = request.getBeneficiarioCInsEPSCpthisBeneficiarioCInstance();
        Eps beneficiarioCInsEPSCpevcEps = request.getBeneficiarioCInsEPSCpevcEpsInstance();

        request.checkArguments();
        BeneficiarioC instance = beneficiarioCInsEPSCpthisBeneficiarioC;

        instance.add2EPSC(beneficiarioCInsEPSCpevcEps);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.eps.siu.InsEPSCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.eps.siu.InsEPSCRequest();
            relatedRequest.setEpsInsEPSCpthisEPSInstance(beneficiarioCInsEPSCpevcEps);
            relatedRequest.setEpsInsEPSCpevcBeneficiarioCInstance(beneficiarioCInsEPSCpthisBeneficiarioC);
            epsSrv.insEPSCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelEPSC

    /*
     * Entry point for service DelEPSC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delEPSC(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.DelEPSCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = beneficiarioCSrv.delEPSCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelEPSC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delEPSCTxn(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.DelEPSCRequest request) throws ModelException {
        request.setBeneficiarioCDelEPSCpthisBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getBeneficiarioCDelEPSCpthisBeneficiarioC()));
        request.setBeneficiarioCDelEPSCpevcEpsInstance(epsSrv.getByOIDWithHV(request.getBeneficiarioCDelEPSCpevcEps()));
        return beneficiarioCSrv.delEPSCImplementation(request, true);
    }

    /*
     * Implements service DelEPSC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delEPSCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.DelEPSCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        BeneficiarioC beneficiarioCDelEPSCpthisBeneficiarioC = request.getBeneficiarioCDelEPSCpthisBeneficiarioCInstance();
        Eps beneficiarioCDelEPSCpevcEps = request.getBeneficiarioCDelEPSCpevcEpsInstance();

        request.checkArguments();
        BeneficiarioC instance = beneficiarioCDelEPSCpthisBeneficiarioC;

        instance.add2EPSC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.eps.siu.DelEPSCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.eps.siu.DelEPSCRequest();
            relatedRequest.setEpsDelEPSCpthisEPSInstance(beneficiarioCDelEPSCpevcEps);
            relatedRequest.setEpsDelEPSCpevcBeneficiarioCInstance(beneficiarioCDelEPSCpthisBeneficiarioC);
            epsSrv.delEPSCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: InsCompanyiaMPC

    /*
     * Entry point for service InsCompanyiaMPC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insCompanyiaMPC(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.InsCompanyiaMPCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = beneficiarioCSrv.insCompanyiaMPCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsCompanyiaMPC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insCompanyiaMPCTxn(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.InsCompanyiaMPCRequest request) throws ModelException {
        request.setBeneficiarioCInsCompanyiaMPCpthisBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getBeneficiarioCInsCompanyiaMPCpthisBeneficiarioC()));
        request.setBeneficiarioCInsCompanyiaMPCpevcCompanyiaInstance(companyiaSrv.getByOIDWithHV(request.getBeneficiarioCInsCompanyiaMPCpevcCompanyia()));
        return beneficiarioCSrv.insCompanyiaMPCImplementation(request, true);
    }

    /*
     * Implements service InsCompanyiaMPC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insCompanyiaMPCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.InsCompanyiaMPCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        BeneficiarioC beneficiarioCInsCompanyiaMPCpthisBeneficiarioC = request.getBeneficiarioCInsCompanyiaMPCpthisBeneficiarioCInstance();
        Companyia beneficiarioCInsCompanyiaMPCpevcCompanyia = request.getBeneficiarioCInsCompanyiaMPCpevcCompanyiaInstance();

        request.checkArguments();
        BeneficiarioC instance = beneficiarioCInsCompanyiaMPCpthisBeneficiarioC;

        instance.add2CompanyiaMPC(beneficiarioCInsCompanyiaMPCpevcCompanyia);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.InsCompanyiaMPCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.companyia.siu.InsCompanyiaMPCRequest();
            relatedRequest.setCompanyiaInsCompanyiaMPCpthisCompanyiaInstance(beneficiarioCInsCompanyiaMPCpevcCompanyia);
            relatedRequest.setCompanyiaInsCompanyiaMPCpevcBeneficiarioCInstance(beneficiarioCInsCompanyiaMPCpthisBeneficiarioC);
            companyiaSrv.insCompanyiaMPCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelCompanyiaMPC

    /*
     * Entry point for service DelCompanyiaMPC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delCompanyiaMPC(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.DelCompanyiaMPCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = beneficiarioCSrv.delCompanyiaMPCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelCompanyiaMPC
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delCompanyiaMPCTxn(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.DelCompanyiaMPCRequest request) throws ModelException {
        request.setBeneficiarioCDelCompanyiaMPCpthisBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getBeneficiarioCDelCompanyiaMPCpthisBeneficiarioC()));
        request.setBeneficiarioCDelCompanyiaMPCpevcCompanyiaInstance(companyiaSrv.getByOIDWithHV(request.getBeneficiarioCDelCompanyiaMPCpevcCompanyia()));
        return beneficiarioCSrv.delCompanyiaMPCImplementation(request, true);
    }

    /*
     * Implements service DelCompanyiaMPC
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delCompanyiaMPCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.DelCompanyiaMPCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        BeneficiarioC beneficiarioCDelCompanyiaMPCpthisBeneficiarioC = request.getBeneficiarioCDelCompanyiaMPCpthisBeneficiarioCInstance();
        Companyia beneficiarioCDelCompanyiaMPCpevcCompanyia = request.getBeneficiarioCDelCompanyiaMPCpevcCompanyiaInstance();

        request.checkArguments();
        BeneficiarioC instance = beneficiarioCDelCompanyiaMPCpthisBeneficiarioC;

        instance.add2CompanyiaMPC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.DelCompanyiaMPCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.companyia.siu.DelCompanyiaMPCRequest();
            relatedRequest.setCompanyiaDelCompanyiaMPCpthisCompanyiaInstance(beneficiarioCDelCompanyiaMPCpevcCompanyia);
            relatedRequest.setCompanyiaDelCompanyiaMPCpevcBeneficiarioCInstance(beneficiarioCDelCompanyiaMPCpthisBeneficiarioC);
            companyiaSrv.delCompanyiaMPCImplementation(relatedRequest, false);
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
        select.getOrderByPart().addItem(Constants.normalizePath(BeneficiarioCConstants.CLASS_NAME, BeneficiarioCConstants.ROLE_NAME_CONTRATOC + "." + ContratoCConstants.ATTR_FIELD_CONTRATOCID, false), true);
        select.getOrderByPart().addItem(Constants.normalizePath(BeneficiarioCConstants.CLASS_NAME, BeneficiarioCConstants.ATTR_FIELD_BENEFICIARIOCID, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildBeneficiarioCRelatedInstance(relatedInstanceJsonOid);
        }
        if (BeneficiarioCConstants.ROLE_NAME_PERSONAC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4PersonaC(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_ANEXOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4AnexoC(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_CONCEPTOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4ConceptoC(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_CONTRATOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4ContratoC(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_FAMILIASC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4FamiliaC(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_EPSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4Eps(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_COMPANYIAMPC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4Companyia(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_PARENTESCOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4Parentesco(relatedInstanceJsonOid, remainingPath);
        }
        if (BeneficiarioCConstants.ROLE_NAME_ESTADOBENEFICIARIOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildBeneficiarioCRelatedInstance4EstadoBeneficiario(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildBeneficiarioCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new BeneficiarioC(mapper.readValue(relatedInstanceJsonOid, BeneficiarioCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4PersonaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PersonaC(mapper.readValue(relatedInstanceJsonOid, PersonaCOid.class)): PersonaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4AnexoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new AnexoC(mapper.readValue(relatedInstanceJsonOid, AnexoCOid.class)): AnexoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4ConceptoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ConceptoC(mapper.readValue(relatedInstanceJsonOid, ConceptoCOid.class)): ConceptoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4ContratoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ContratoC(mapper.readValue(relatedInstanceJsonOid, ContratoCOid.class)): ContratoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4FamiliaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new FamiliaC(mapper.readValue(relatedInstanceJsonOid, FamiliaCOid.class)): FamiliaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4Eps(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Eps(mapper.readValue(relatedInstanceJsonOid, EpsOid.class)): Eps.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4Companyia(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Companyia(mapper.readValue(relatedInstanceJsonOid, CompanyiaOid.class)): Companyia.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4Parentesco(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Parentesco(mapper.readValue(relatedInstanceJsonOid, ParentescoOid.class)): Parentesco.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildBeneficiarioCRelatedInstance4EstadoBeneficiario(String relatedInstanceJsonOid, String remainingPath) {
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
            BeneficiarioCOid oid = new BeneficiarioCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(BeneficiarioCConstants.ROLE_NAME_CONTRATOC + "." + ContratoCConstants.ATTR_FIELD_CONTRATOCID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getContratoCContratoCID(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(BeneficiarioCConstants.ATTR_FIELD_BENEFICIARIOCID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(oid.getBeneficiarioCID(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison2);
        } catch (Exception e) {
            LoggerFactory.getLogger(BeneficiarioCService.class).error(e.getMessage());
        }
    }

}
