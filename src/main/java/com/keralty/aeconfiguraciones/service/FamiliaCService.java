package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.ContratoCConstants;
import com.keralty.aeconfiguraciones.global.FamiliaCConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.FamiliaC;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.BeneficiarioCOid;
import com.keralty.aeconfiguraciones.persistence.oid.ContratoCOid;
import com.keralty.aeconfiguraciones.persistence.oid.FamiliaCOid;
import com.keralty.aeconfiguraciones.persistence.oid.MunicipioOid;
import com.keralty.aeconfiguraciones.persistence.oid.PersonaCOid;
import com.keralty.aeconfiguraciones.persistence.PersonaC;
import com.keralty.aeconfiguraciones.repository.FamiliaCJpaRepository;
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
public class FamiliaCService extends AbstractService {

    FamiliaCJpaRepository repository;

    FamiliaCService familiaCSrv;

    MunicipioService municipioSrv;

    PersonaCService personaCSrv;

    BeneficiarioCService beneficiarioCSrv;

    ContratoCService contratoCSrv;

    @Inject
    public FamiliaCService(
            FamiliaCService familiaCSrv,
            MunicipioService municipioSrv,
            PersonaCService personaCSrv,
            BeneficiarioCService beneficiarioCSrv,
            ContratoCService contratoCSrv,
            FamiliaCJpaRepository repository) {
        this.familiaCSrv = familiaCSrv;
        this.municipioSrv = municipioSrv;
        this.personaCSrv = personaCSrv;
        this.beneficiarioCSrv = beneficiarioCSrv;
        this.contratoCSrv = contratoCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, FamiliaCConstants.CLASS_NAME);
    }

    private List<FamiliaC> solveFamiliaCTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, FamiliaCConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveFamiliaCTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, FamiliaCConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class FamiliaC
     *
     * @return Population of class FamiliaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<FamiliaC> get() {
        List<FamiliaC> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class FamiliaC that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class FamiliaC matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<FamiliaC> get(QueryRequest queryRequest) {
        return solveFamiliaCTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveFamiliaCTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveFamiliaCTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveFamiliaCTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of FamiliaC by ID
     *
     * @param contratoCContratoCID Identification field contratoCContratoCID
     *
     * @param familiaCID Identification field familiaCID
     *
     * @return Instance of FamiliaC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public FamiliaC getById(Long contratoCContratoCID, Long familiaCID) {
        return getByOID(new FamiliaCOid(contratoCContratoCID, familiaCID));
    }
    
    /*
     * Returns an instance of FamiliaC by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of FamiliaC
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public FamiliaC getByOID(FamiliaCOid oid) {
        Optional<FamiliaC> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of FamiliaC by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of FamiliaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public FamiliaC getByOIDWithHV(FamiliaCOid oid) {
        Optional<FamiliaC> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<FamiliaC> getInstanceByOid(FamiliaCOid oid) {
        return oid != null && oid.getContratoCContratoCID() != null
               ? repository.findById(oid)
               : Optional.empty();
    }

    /*
     * Returns an instance of FamiliaC by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of FamiliaC
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public FamiliaC getByJsonOid(String jsonOid) {
        FamiliaCOid oid = new FamiliaCOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of FamiliaC related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of FamiliaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<FamiliaC> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveFamiliaCTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of FamiliaC related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of FamiliaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<FamiliaC> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of FamiliaC
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of FamiliaC
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getFamiliaCDynamic(FamiliaCOid oid, String displaySetItems) {
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
    public String getSuppInfo(FamiliaCOid oid, String dsName) {
        FamiliaC instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(FamiliaC instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }




    // IIU Methods



    // Service: InsMunicipioFamilia

    /*
     * Entry point for service InsMunicipioFamilia
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insMunicipioFamilia(
            com.keralty.aeconfiguraciones.viewmodel.familiac.siu.InsMunicipioFamiliaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = familiaCSrv.insMunicipioFamiliaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsMunicipioFamilia
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insMunicipioFamiliaTxn(
            com.keralty.aeconfiguraciones.viewmodel.familiac.siu.InsMunicipioFamiliaRequest request) throws ModelException {
        request.setFamiliaCInsMunicipioFamiliapthisFamiliaCInstance(familiaCSrv.getByOIDWithHV(request.getFamiliaCInsMunicipioFamiliapthisFamiliaC()));
        request.setFamiliaCInsMunicipioFamiliapevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getFamiliaCInsMunicipioFamiliapevcMunicipio()));
        return familiaCSrv.insMunicipioFamiliaImplementation(request, true);
    }

    /*
     * Implements service InsMunicipioFamilia
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insMunicipioFamiliaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.familiac.siu.InsMunicipioFamiliaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        FamiliaC familiaCInsMunicipioFamiliapthisFamiliaC = request.getFamiliaCInsMunicipioFamiliapthisFamiliaCInstance();
        Municipio familiaCInsMunicipioFamiliapevcMunicipio = request.getFamiliaCInsMunicipioFamiliapevcMunicipioInstance();

        request.checkArguments();
        FamiliaC instance = familiaCInsMunicipioFamiliapthisFamiliaC;

        instance.add2MunicipioC(familiaCInsMunicipioFamiliapevcMunicipio);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioFamiliaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.InsMunicipioFamiliaRequest();
            relatedRequest.setMunicipioInsMunicipioFamiliapthisMunicipioInstance(familiaCInsMunicipioFamiliapevcMunicipio);
            relatedRequest.setMunicipioInsMunicipioFamiliapevcFamiliaCInstance(familiaCInsMunicipioFamiliapthisFamiliaC);
            municipioSrv.insMunicipioFamiliaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    // Service: DelMunicipioFamilia

    /*
     * Entry point for service DelMunicipioFamilia
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delMunicipioFamilia(
            com.keralty.aeconfiguraciones.viewmodel.familiac.siu.DelMunicipioFamiliaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = familiaCSrv.delMunicipioFamiliaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelMunicipioFamilia
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delMunicipioFamiliaTxn(
            com.keralty.aeconfiguraciones.viewmodel.familiac.siu.DelMunicipioFamiliaRequest request) throws ModelException {
        request.setFamiliaCDelMunicipioFamiliapthisFamiliaCInstance(familiaCSrv.getByOIDWithHV(request.getFamiliaCDelMunicipioFamiliapthisFamiliaC()));
        request.setFamiliaCDelMunicipioFamiliapevcMunicipioInstance(municipioSrv.getByOIDWithHV(request.getFamiliaCDelMunicipioFamiliapevcMunicipio()));
        return familiaCSrv.delMunicipioFamiliaImplementation(request, true);
    }

    /*
     * Implements service DelMunicipioFamilia
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delMunicipioFamiliaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.familiac.siu.DelMunicipioFamiliaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        FamiliaC familiaCDelMunicipioFamiliapthisFamiliaC = request.getFamiliaCDelMunicipioFamiliapthisFamiliaCInstance();
        Municipio familiaCDelMunicipioFamiliapevcMunicipio = request.getFamiliaCDelMunicipioFamiliapevcMunicipioInstance();

        request.checkArguments();
        FamiliaC instance = familiaCDelMunicipioFamiliapthisFamiliaC;

        instance.add2MunicipioC(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioFamiliaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.DelMunicipioFamiliaRequest();
            relatedRequest.setMunicipioDelMunicipioFamiliapthisMunicipioInstance(familiaCDelMunicipioFamiliapevcMunicipio);
            relatedRequest.setMunicipioDelMunicipioFamiliapevcFamiliaCInstance(familiaCDelMunicipioFamiliapthisFamiliaC);
            municipioSrv.delMunicipioFamiliaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCFamiliaCNumero(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(FamiliaCConstants.CLASS_NAME, FamiliaCConstants.ROLE_NAME_CONTRATOC + "." + ContratoCConstants.ATTR_FIELD_CONTRATOCID, false), true);
        select.getOrderByPart().addItem(Constants.normalizePath(FamiliaCConstants.CLASS_NAME, FamiliaCConstants.ATTR_FIELD_FAMILIACID, false), true);
    }


    private void applyOCOCFamiliaCNumero(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_FamiliaCNumero".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(FamiliaCConstants.CLASS_NAME, FamiliaCConstants.ATTR_NAME_FAMILIACNUMFAMILIA, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildFamiliaCRelatedInstance(relatedInstanceJsonOid);
        }
        if (FamiliaCConstants.ROLE_NAME_PERSONACTITULAR.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFamiliaCRelatedInstance4PersonaC(relatedInstanceJsonOid, remainingPath);
        }
        if (FamiliaCConstants.ROLE_NAME_BENEFICIARIOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFamiliaCRelatedInstance4BeneficiarioC(relatedInstanceJsonOid, remainingPath);
        }
        if (FamiliaCConstants.ROLE_NAME_CONTRATOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFamiliaCRelatedInstance4ContratoC(relatedInstanceJsonOid, remainingPath);
        }
        if (FamiliaCConstants.ROLE_NAME_MUNICIPIOC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildFamiliaCRelatedInstance4Municipio(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildFamiliaCRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new FamiliaC(mapper.readValue(relatedInstanceJsonOid, FamiliaCOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildFamiliaCRelatedInstance4PersonaC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PersonaC(mapper.readValue(relatedInstanceJsonOid, PersonaCOid.class)): PersonaC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildFamiliaCRelatedInstance4BeneficiarioC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new BeneficiarioC(mapper.readValue(relatedInstanceJsonOid, BeneficiarioCOid.class)): BeneficiarioC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildFamiliaCRelatedInstance4ContratoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ContratoC(mapper.readValue(relatedInstanceJsonOid, ContratoCOid.class)): ContratoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildFamiliaCRelatedInstance4Municipio(String relatedInstanceJsonOid, String remainingPath) {
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
            FamiliaCOid oid = new FamiliaCOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(FamiliaCConstants.ROLE_NAME_CONTRATOC + "." + ContratoCConstants.ATTR_FIELD_CONTRATOCID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getContratoCContratoCID(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(FamiliaCConstants.ATTR_FIELD_FAMILIACID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(oid.getFamiliaCID(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison2);
        } catch (Exception e) {
            LoggerFactory.getLogger(FamiliaCService.class).error(e.getMessage());
        }
    }

}
