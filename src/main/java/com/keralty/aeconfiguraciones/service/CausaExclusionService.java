package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.CausaExclusionConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.CausaExclusion;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.CausaExclusionOid;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadOid;
import com.keralty.aeconfiguraciones.repository.CausaExclusionJpaRepository;
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
public class CausaExclusionService extends AbstractService {

    CausaExclusionJpaRepository repository;

    CausaExclusionService causaExclusionSrv;

    NovedadService novedadSrv;

    @Inject
    public CausaExclusionService(
            CausaExclusionService causaExclusionSrv,
            NovedadService novedadSrv,
            CausaExclusionJpaRepository repository) {
        this.causaExclusionSrv = causaExclusionSrv;
        this.novedadSrv = novedadSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, CausaExclusionConstants.CLASS_NAME);
    }

    private List<CausaExclusion> solveCausaExclusionTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, CausaExclusionConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveCausaExclusionTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, CausaExclusionConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class CausaExclusion
     *
     * @return Population of class CausaExclusion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<CausaExclusion> get() {
        List<CausaExclusion> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class CausaExclusion
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class CausaExclusion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<CausaExclusion> instances = get(queryRequest);
        if(CausaExclusionConstants.DSICCAUSAEXCLUSION.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICCausaExclusion())).collect(Collectors.toList());
        }
        if(CausaExclusionConstants.DSDSCAUSAEXCLUSION.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSCausaExclusion())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class CausaExclusion that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class CausaExclusion matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<CausaExclusion> get(QueryRequest queryRequest) {
        return solveCausaExclusionTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveCausaExclusionTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveCausaExclusionTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveCausaExclusionTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveCausaExclusionTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of CausaExclusion by ID
     *
     * @param causaExclusionidCausaExclusion Identification field causaExclusionidCausaExclusion
     *
     * @return Instance of CausaExclusion
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public CausaExclusion getById(Long causaExclusionidCausaExclusion) {
        return getByOID(new CausaExclusionOid(causaExclusionidCausaExclusion));
    }
    
    /*
     * Returns an instance of CausaExclusion by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of CausaExclusion
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public CausaExclusion getByOID(CausaExclusionOid oid) {
        Optional<CausaExclusion> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of CausaExclusion by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of CausaExclusion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public CausaExclusion getByOIDWithHV(CausaExclusionOid oid) {
        Optional<CausaExclusion> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<CausaExclusion> getInstanceByOid(CausaExclusionOid oid) {
        return oid != null && oid.getCausaExclusionidCausaExclusion() != null
               ? repository.findById(oid.getCausaExclusionidCausaExclusion())
               : Optional.empty();
    }

    /*
     * Returns an instance of CausaExclusion by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of CausaExclusion
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public CausaExclusion getByJsonOid(String jsonOid) {
        CausaExclusionOid oid = new CausaExclusionOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of CausaExclusion related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of CausaExclusion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<CausaExclusion> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveCausaExclusionTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of CausaExclusion related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of CausaExclusion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<CausaExclusion> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of CausaExclusion
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of CausaExclusion
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getCausaExclusionDynamic(CausaExclusionOid oid, String displaySetItems) {
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
    public String getSuppInfo(CausaExclusionOid oid, String dsName) {
        CausaExclusion instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(CausaExclusion instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (CausaExclusionConstants.DSICCAUSAEXCLUSION.equals(dsName))
            return instance.buildICCausaExclusion();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(CausaExclusion instance, String dsName) {
        if (CausaExclusionConstants.DSICCAUSAEXCLUSION.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICCausaExclusion());
        if (CausaExclusionConstants.DSDSCAUSAEXCLUSION.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSCausaExclusion());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_CausaExclusion".equals(filterRequest.getName()))
            suppInfo4ICCausaExclusion(filterRequest.getFilterVariable("searchText").toString(), sql);
    }



    // IIU Methods


    /*
     * Solves the query needed for PIUCausaExclusion
     *
     * @param queryRequest Query request
     * @return List of viewmodels for CausaExclusion using displayset DSCausaExclusion that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.causaexclusion.ds.DSCausaExclusion> piuPIUCausaExclusion(QueryRequest queryRequest) {
        return solveCausaExclusionTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.causaexclusion.ds.DSCausaExclusion::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUCausaExclusion
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUCausaExclusion
     */
    public int getCount4PIUCausaExclusion(QueryRequest queryRequest) {
        return solveCausaExclusionTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.causaexclusion.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = causaExclusionSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.causaexclusion.siu.CrearRequest request) throws ModelException {
        request.setCausaExclusioncrearpagrNovedadInstance(novedadSrv.getByOIDWithHV(request.getCausaExclusioncrearpagrNovedad()));
        return causaExclusionSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.causaexclusion.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Novedad causaExclusioncrearpagrNovedad = request.getCausaExclusioncrearpagrNovedadInstance();
        String causaExclusioncrearpatrAliasCausa = request.getCausaExclusioncrearpatrAliasCausa();
        String causaExclusioncrearpatrDescripcion = request.getCausaExclusioncrearpatrDescripcion();

        request.checkArguments();
        CausaExclusion instance = new CausaExclusion();
        instance.add2Novedad(causaExclusioncrearpagrNovedad);

        instance.setCausaExclusionidCausaExclusion(Long.valueOf(0));
        instance.setCausaExclusionAliasCausa(causaExclusioncrearpatrAliasCausa);
        instance.setCausaExclusionDescripcion(causaExclusioncrearpatrDescripcion);

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICCausaExclusion());

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
            com.keralty.aeconfiguraciones.viewmodel.causaexclusion.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = causaExclusionSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.causaexclusion.siu.EliminarRequest request) throws ModelException {
        request.setCausaExclusioneliminarpthisCausaExclusionInstance(causaExclusionSrv.getByOIDWithHV(request.getCausaExclusioneliminarpthisCausaExclusion()));
        return causaExclusionSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.causaexclusion.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        CausaExclusion causaExclusioneliminarpthisCausaExclusion = request.getCausaExclusioneliminarpthisCausaExclusionInstance();

        request.checkArguments();
        CausaExclusion instance = causaExclusioneliminarpthisCausaExclusion;


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICCausaExclusion());

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
            com.keralty.aeconfiguraciones.viewmodel.causaexclusion.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = causaExclusionSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.causaexclusion.siu.ModificarRequest request) throws ModelException {
        request.setCausaExclusionmodificarpthisCausaExclusionInstance(causaExclusionSrv.getByOIDWithHV(request.getCausaExclusionmodificarpthisCausaExclusion()));
        return causaExclusionSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.causaexclusion.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        CausaExclusion causaExclusionmodificarpthisCausaExclusion = request.getCausaExclusionmodificarpthisCausaExclusionInstance();
        String causaExclusionmodificarpAliasCausa = request.getCausaExclusionmodificarpAliasCausa();
        String causaExclusionmodificarpDescripcion = request.getCausaExclusionmodificarpDescripcion();

        request.checkArguments();
        CausaExclusion instance = causaExclusionmodificarpthisCausaExclusion;
        instance.setCausaExclusionAliasCausa(causaExclusionmodificarpAliasCausa);
        instance.setCausaExclusionDescripcion(causaExclusionmodificarpDescripcion);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICCausaExclusion());

        return response;
    }


    private void suppInfo4ICCausaExclusion(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(CausaExclusionConstants.ATTR_NAME_CAUSAEXCLUSIONDESCRIPCION, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison1);

            SQLWhereConjunction conjunction = new SQLWhereConjunction();
            conjunction.addDisjunction(disjunction);
            select.addConjunction(conjunction);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCCausaExclusion(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(CausaExclusionConstants.CLASS_NAME, CausaExclusionConstants.ATTR_FIELD_CAUSAEXCLUSIONIDCAUSAEXCLUSION, false), true);
    }


    private void applyOCOCCausaExclusion(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_CausaExclusion".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(CausaExclusionConstants.CLASS_NAME, CausaExclusionConstants.ATTR_NAME_CAUSAEXCLUSIONDESCRIPCION, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildCausaExclusionRelatedInstance(relatedInstanceJsonOid);
        }
        if (CausaExclusionConstants.ROLE_NAME_NOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildCausaExclusionRelatedInstance4Novedad(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildCausaExclusionRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new CausaExclusion(mapper.readValue(relatedInstanceJsonOid, CausaExclusionOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildCausaExclusionRelatedInstance4Novedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Novedad(mapper.readValue(relatedInstanceJsonOid, NovedadOid.class)): Novedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            CausaExclusionOid oid = new CausaExclusionOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(CausaExclusionConstants.ATTR_FIELD_CAUSAEXCLUSIONIDCAUSAEXCLUSION, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getCausaExclusionidCausaExclusion(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(CausaExclusionService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
