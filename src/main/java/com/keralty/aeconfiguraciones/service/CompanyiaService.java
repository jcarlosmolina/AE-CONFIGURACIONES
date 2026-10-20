package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.InstanceExistsException;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.CompanyiaConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.BeneficiarioC;
import com.keralty.aeconfiguraciones.persistence.Companyia;
import com.keralty.aeconfiguraciones.persistence.oid.BeneficiarioCOid;
import com.keralty.aeconfiguraciones.persistence.oid.CompanyiaOid;
import com.keralty.aeconfiguraciones.persistence.oid.UsuarioOid;
import com.keralty.aeconfiguraciones.persistence.Usuario;
import com.keralty.aeconfiguraciones.repository.CompanyiaJpaRepository;
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
public class CompanyiaService extends AbstractService {

    CompanyiaJpaRepository repository;

    CompanyiaService companyiaSrv;

    UsuarioService usuarioSrv;

    BeneficiarioCService beneficiarioCSrv;

    @Inject
    public CompanyiaService(
            CompanyiaService companyiaSrv,
            UsuarioService usuarioSrv,
            BeneficiarioCService beneficiarioCSrv,
            CompanyiaJpaRepository repository) {
        this.companyiaSrv = companyiaSrv;
        this.usuarioSrv = usuarioSrv;
        this.beneficiarioCSrv = beneficiarioCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, CompanyiaConstants.CLASS_NAME);
    }

    private List<Companyia> solveCompanyiaTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, CompanyiaConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveCompanyiaTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, CompanyiaConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Companyia
     *
     * @return Population of class Companyia
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Companyia> get() {
        List<Companyia> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class Companyia
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class Companyia
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<Companyia> instances = get(queryRequest);
        if(CompanyiaConstants.DSICCOMPANYIA.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICCompanyia())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class Companyia that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Companyia matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Companyia> get(QueryRequest queryRequest) {
        return solveCompanyiaTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveCompanyiaTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveCompanyiaTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveCompanyiaTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveCompanyiaTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Companyia by ID
     *
     * @param companyiaCodigo Identification field companyiaCodigo
     *
     * @return Instance of Companyia
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Companyia getById(Long companyiaCodigo) {
        return getByOID(new CompanyiaOid(companyiaCodigo));
    }
    
    /*
     * Returns an instance of Companyia by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Companyia
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Companyia getByOID(CompanyiaOid oid) {
        Optional<Companyia> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Companyia by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Companyia
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Companyia getByOIDWithHV(CompanyiaOid oid) {
        Optional<Companyia> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Companyia> getInstanceByOid(CompanyiaOid oid) {
        return oid != null && oid.getCompanyiaCodigo() != null
               ? repository.findById(oid.getCompanyiaCodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of Companyia by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Companyia
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Companyia getByJsonOid(String jsonOid) {
        CompanyiaOid oid = new CompanyiaOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Companyia related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Companyia
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Companyia> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveCompanyiaTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Companyia related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Companyia
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Companyia> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Companyia
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Companyia
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getCompanyiaDynamic(CompanyiaOid oid, String displaySetItems) {
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
    public String getSuppInfo(CompanyiaOid oid, String dsName) {
        Companyia instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Companyia instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (CompanyiaConstants.DSICCOMPANYIA.equals(dsName))
            return instance.buildICCompanyia();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(Companyia instance, String dsName) {
        if (CompanyiaConstants.DSICCOMPANYIA.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICCompanyia());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyNavigationalFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if ("Usuario_TMODIFICARATTR_pt_p_evcCompanyia_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1432633737216826FiltNav1(sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_Companyia".equals(filterRequest.getName()))
            suppInfo4ICCompanyia(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void clas1432633737216826FiltNav1(SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();

        // EsDeMP = true
        // '=' Comparison
        SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
        comparison1.setOperator("=");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(CompanyiaConstants.ATTR_NAME_COMPANYIAESDEMP, Constants.Type.BOOL.getTypeName());
        comparison1.setFirstOperand(operand112);
        
        // Operand 2
        comparison1.setSecondOperand(new SQLWhereOperandLiteral(Boolean.TRUE, Constants.Type.BOOL.getTypeName()));
        conjunction.addOperand(comparison1);

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUCompanyia
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Companyia using displayset AutoDS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.companyia.ds.AutoDS> piuPIUCompanyia(QueryRequest queryRequest) {
        return solveCompanyiaTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.companyia.ds.AutoDS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUCompanyia
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUCompanyia
     */
    public int getCount4PIUCompanyia(QueryRequest queryRequest) {
        return solveCompanyiaTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = companyiaSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.CrearRequest request) throws ModelException {
        return companyiaSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long companyiacrearpatrCodigo = request.getCompanyiacrearpatrCodigo();
        String companyiacrearpatrNombre = request.getCompanyiacrearpatrNombre();
        Boolean companyiacrearpatrEsDeMP = request.getCompanyiacrearpatrEsDeMP();

        request.checkArguments();
        Companyia instance = new Companyia();
        instance.setCompanyiaCodigo(companyiacrearpatrCodigo);
        instance.setCompanyiaNombre(companyiacrearpatrNombre);
        instance.setCompanyiaEsDeMP(companyiacrearpatrEsDeMP);
        if (companyiaSrv.getByOID(instance.getOid()) != null) {
            throw new InstanceExistsException(CompanyiaConstants.CLASS_ALIAS);
        }

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICCompanyia());

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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = companyiaSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.EliminarRequest request) throws ModelException {
        request.setCompanyiaeliminarpthisCompanyiaInstance(companyiaSrv.getByOIDWithHV(request.getCompanyiaeliminarpthisCompanyia()));
        return companyiaSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Companyia companyiaeliminarpthisCompanyia = request.getCompanyiaeliminarpthisCompanyiaInstance();

        request.checkArguments();
        Companyia instance = companyiaeliminarpthisCompanyia;
        if(companyiaeliminarpthisCompanyia.getAtributosUsuario().size() > 0) {
            throw new MinCardinalityException(CompanyiaConstants.CLASS_ALIAS, CompanyiaConstants.ROLE_ALIAS_ATRIBUTOSUSUARIO);
        }
        if(companyiaeliminarpthisCompanyia.getBeneficiariosC().size() > 0) {
            throw new MinCardinalityException(CompanyiaConstants.CLASS_ALIAS, CompanyiaConstants.ROLE_ALIAS_BENEFICIARIOSC);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICCompanyia());

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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = companyiaSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.ModificarRequest request) throws ModelException {
        request.setCompanyiamodificarpthisCompanyiaInstance(companyiaSrv.getByOIDWithHV(request.getCompanyiamodificarpthisCompanyia()));
        return companyiaSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Companyia companyiamodificarpthisCompanyia = request.getCompanyiamodificarpthisCompanyiaInstance();
        Boolean companyiamodificarpEsDeMP = request.getCompanyiamodificarpEsDeMP();

        request.checkArguments();
        Companyia instance = companyiamodificarpthisCompanyia;
        instance.setCompanyiaEsDeMP(companyiamodificarpEsDeMP);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICCompanyia());

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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.SincronizarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = companyiaSrv.sincronizarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.SincronizarRequest request) throws ModelException {
        request.setCompanyiasincronizarpthisCompanyiaInstance(companyiaSrv.getByOIDWithHV(request.getCompanyiasincronizarpthisCompanyia()));
        return companyiaSrv.sincronizarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.SincronizarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Companyia companyiasincronizarpthisCompanyia = request.getCompanyiasincronizarpthisCompanyiaInstance();
        String companyiasincronizarpNombre = request.getCompanyiasincronizarpNombre();

        request.checkArguments();
        Companyia instance = companyiasincronizarpthisCompanyia;
        instance.setCompanyiaNombre(companyiasincronizarpNombre);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICCompanyia());

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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.InsCompanyiaUsuarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = companyiaSrv.insCompanyiaUsuarioTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.InsCompanyiaUsuarioRequest request) throws ModelException {
        request.setCompanyiaInsCompanyiaUsuariopthisCompanyiaInstance(companyiaSrv.getByOIDWithHV(request.getCompanyiaInsCompanyiaUsuariopthisCompanyia()));
        request.setCompanyiaInsCompanyiaUsuariopevcUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getCompanyiaInsCompanyiaUsuariopevcUsuario()));
        return companyiaSrv.insCompanyiaUsuarioImplementation(request, true);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.InsCompanyiaUsuarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Companyia companyiaInsCompanyiaUsuariopthisCompanyia = request.getCompanyiaInsCompanyiaUsuariopthisCompanyiaInstance();
        Usuario companyiaInsCompanyiaUsuariopevcUsuario = request.getCompanyiaInsCompanyiaUsuariopevcUsuarioInstance();

        request.checkArguments();
        Companyia instance = companyiaInsCompanyiaUsuariopthisCompanyia;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsCompanyiaUsuarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.usuario.siu.InsCompanyiaUsuarioRequest();
            relatedRequest.setUsuarioInsCompanyiaUsuariopthisAttrUsuarioInstance(companyiaInsCompanyiaUsuariopevcUsuario);
            relatedRequest.setUsuarioInsCompanyiaUsuariopevcCompanyiaInstance(companyiaInsCompanyiaUsuariopthisCompanyia);
            usuarioSrv.insCompanyiaUsuarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICCompanyia());

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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.DelCompanyiaUsuarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = companyiaSrv.delCompanyiaUsuarioTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.DelCompanyiaUsuarioRequest request) throws ModelException {
        request.setCompanyiaDelCompanyiaUsuariopthisCompanyiaInstance(companyiaSrv.getByOIDWithHV(request.getCompanyiaDelCompanyiaUsuariopthisCompanyia()));
        request.setCompanyiaDelCompanyiaUsuariopevcUsuarioInstance(usuarioSrv.getByOIDWithHV(request.getCompanyiaDelCompanyiaUsuariopevcUsuario()));
        return companyiaSrv.delCompanyiaUsuarioImplementation(request, true);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.DelCompanyiaUsuarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Companyia companyiaDelCompanyiaUsuariopthisCompanyia = request.getCompanyiaDelCompanyiaUsuariopthisCompanyiaInstance();
        Usuario companyiaDelCompanyiaUsuariopevcUsuario = request.getCompanyiaDelCompanyiaUsuariopevcUsuarioInstance();

        request.checkArguments();
        Companyia instance = companyiaDelCompanyiaUsuariopthisCompanyia;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelCompanyiaUsuarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.usuario.siu.DelCompanyiaUsuarioRequest();
            relatedRequest.setUsuarioDelCompanyiaUsuariopthisAttrUsuarioInstance(companyiaDelCompanyiaUsuariopevcUsuario);
            relatedRequest.setUsuarioDelCompanyiaUsuariopevcCompanyiaInstance(companyiaDelCompanyiaUsuariopthisCompanyia);
            usuarioSrv.delCompanyiaUsuarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICCompanyia());

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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.InsCompanyiaMPCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = companyiaSrv.insCompanyiaMPCTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.InsCompanyiaMPCRequest request) throws ModelException {
        request.setCompanyiaInsCompanyiaMPCpthisCompanyiaInstance(companyiaSrv.getByOIDWithHV(request.getCompanyiaInsCompanyiaMPCpthisCompanyia()));
        request.setCompanyiaInsCompanyiaMPCpevcBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getCompanyiaInsCompanyiaMPCpevcBeneficiarioC()));
        return companyiaSrv.insCompanyiaMPCImplementation(request, true);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.InsCompanyiaMPCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Companyia companyiaInsCompanyiaMPCpthisCompanyia = request.getCompanyiaInsCompanyiaMPCpthisCompanyiaInstance();
        BeneficiarioC companyiaInsCompanyiaMPCpevcBeneficiarioC = request.getCompanyiaInsCompanyiaMPCpevcBeneficiarioCInstance();

        request.checkArguments();
        Companyia instance = companyiaInsCompanyiaMPCpthisCompanyia;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.InsCompanyiaMPCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.InsCompanyiaMPCRequest();
            relatedRequest.setBeneficiarioCInsCompanyiaMPCpthisBeneficiarioCInstance(companyiaInsCompanyiaMPCpevcBeneficiarioC);
            relatedRequest.setBeneficiarioCInsCompanyiaMPCpevcCompanyiaInstance(companyiaInsCompanyiaMPCpthisCompanyia);
            beneficiarioCSrv.insCompanyiaMPCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICCompanyia());

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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.DelCompanyiaMPCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = companyiaSrv.delCompanyiaMPCTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.DelCompanyiaMPCRequest request) throws ModelException {
        request.setCompanyiaDelCompanyiaMPCpthisCompanyiaInstance(companyiaSrv.getByOIDWithHV(request.getCompanyiaDelCompanyiaMPCpthisCompanyia()));
        request.setCompanyiaDelCompanyiaMPCpevcBeneficiarioCInstance(beneficiarioCSrv.getByOIDWithHV(request.getCompanyiaDelCompanyiaMPCpevcBeneficiarioC()));
        return companyiaSrv.delCompanyiaMPCImplementation(request, true);
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
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.DelCompanyiaMPCRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Companyia companyiaDelCompanyiaMPCpthisCompanyia = request.getCompanyiaDelCompanyiaMPCpthisCompanyiaInstance();
        BeneficiarioC companyiaDelCompanyiaMPCpevcBeneficiarioC = request.getCompanyiaDelCompanyiaMPCpevcBeneficiarioCInstance();

        request.checkArguments();
        Companyia instance = companyiaDelCompanyiaMPCpthisCompanyia;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.DelCompanyiaMPCRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.beneficiarioc.siu.DelCompanyiaMPCRequest();
            relatedRequest.setBeneficiarioCDelCompanyiaMPCpthisBeneficiarioCInstance(companyiaDelCompanyiaMPCpevcBeneficiarioC);
            relatedRequest.setBeneficiarioCDelCompanyiaMPCpevcCompanyiaInstance(companyiaDelCompanyiaMPCpthisCompanyia);
            beneficiarioCSrv.delCompanyiaMPCImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICCompanyia());

        return response;
    }


    private void suppInfo4ICCompanyia(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(CompanyiaConstants.ATTR_NAME_COMPANYIANOMBRE, Constants.Type.STRING.getTypeName()));
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
        applyOCOCCompanyia(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(CompanyiaConstants.CLASS_NAME, CompanyiaConstants.ATTR_FIELD_COMPANYIACODIGO, false), true);
    }


    private void applyOCOCCompanyia(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_Companyia".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(CompanyiaConstants.CLASS_NAME, CompanyiaConstants.ATTR_NAME_COMPANYIANOMBRE, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildCompanyiaRelatedInstance(relatedInstanceJsonOid);
        }
        if (CompanyiaConstants.ROLE_NAME_ATRIBUTOSUSUARIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildCompanyiaRelatedInstance4Usuario(relatedInstanceJsonOid, remainingPath);
        }
        if (CompanyiaConstants.ROLE_NAME_BENEFICIARIOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildCompanyiaRelatedInstance4BeneficiarioC(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildCompanyiaRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Companyia(mapper.readValue(relatedInstanceJsonOid, CompanyiaOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildCompanyiaRelatedInstance4Usuario(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Usuario(mapper.readValue(relatedInstanceJsonOid, UsuarioOid.class)): Usuario.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildCompanyiaRelatedInstance4BeneficiarioC(String relatedInstanceJsonOid, String remainingPath) {
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
            CompanyiaOid oid = new CompanyiaOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(CompanyiaConstants.ATTR_FIELD_COMPANYIACODIGO, Constants.Type.INT.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getCompanyiaCodigo(), Constants.Type.INT.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(CompanyiaService.class).error(e.getMessage());
        }
    }

}
