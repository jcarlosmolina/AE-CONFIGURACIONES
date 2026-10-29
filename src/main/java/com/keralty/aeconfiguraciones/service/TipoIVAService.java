package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.global.ProductosConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.global.TipoIVAConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoIVAOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.TipoIVA;
import com.keralty.aeconfiguraciones.repository.TipoIVAJpaRepository;
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
public class TipoIVAService extends AbstractService {

    TipoIVAJpaRepository repository;

    TipoIVAService tipoIVASrv;

    ProductosService productosSrv;

    PlanesService planesSrv;

    @Inject
    public TipoIVAService(
            TipoIVAService tipoIVASrv,
            ProductosService productosSrv,
            PlanesService planesSrv,
            TipoIVAJpaRepository repository) {
        this.tipoIVASrv = tipoIVASrv;
        this.productosSrv = productosSrv;
        this.planesSrv = planesSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, TipoIVAConstants.CLASS_NAME);
    }

    private List<TipoIVA> solveTipoIVATypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, TipoIVAConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveTipoIVATypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, TipoIVAConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class TipoIVA
     *
     * @return Population of class TipoIVA
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoIVA> get() {
        List<TipoIVA> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class TipoIVA
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class TipoIVA
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<TipoIVA> instances = get(queryRequest);
        if(TipoIVAConstants.DSICTIPOIVA.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICTipoIVA())).collect(Collectors.toList());
        }
        if(TipoIVAConstants.DSDSTIPOIVAPIU.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSTipoIVAPIU())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class TipoIVA that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class TipoIVA matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoIVA> get(QueryRequest queryRequest) {
        return solveTipoIVATypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveTipoIVATypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveTipoIVATypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveTipoIVATypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveTipoIVATypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of TipoIVA by ID
     *
     * @param tipoIVACodigo Identification field tipoIVACodigo
     *
     * @return Instance of TipoIVA
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoIVA getById(Long tipoIVACodigo) {
        return getByOID(new TipoIVAOid(tipoIVACodigo));
    }
    
    /*
     * Returns an instance of TipoIVA by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoIVA
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoIVA getByOID(TipoIVAOid oid) {
        Optional<TipoIVA> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of TipoIVA by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of TipoIVA
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public TipoIVA getByOIDWithHV(TipoIVAOid oid) {
        Optional<TipoIVA> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<TipoIVA> getInstanceByOid(TipoIVAOid oid) {
        return oid != null && oid.getTipoIVACodigo() != null
               ? repository.findById(oid.getTipoIVACodigo())
               : Optional.empty();
    }

    /*
     * Returns an instance of TipoIVA by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of TipoIVA
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public TipoIVA getByJsonOid(String jsonOid) {
        TipoIVAOid oid = new TipoIVAOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of TipoIVA related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TipoIVA
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoIVA> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveTipoIVATypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of TipoIVA related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of TipoIVA
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<TipoIVA> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of TipoIVA
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of TipoIVA
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getTipoIVADynamic(TipoIVAOid oid, String displaySetItems) {
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
    public String getSuppInfo(TipoIVAOid oid, String dsName) {
        TipoIVA instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(TipoIVA instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (TipoIVAConstants.DSICTIPOIVA.equals(dsName))
            return instance.buildICTipoIVA();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(TipoIVA instance, String dsName) {
        if (TipoIVAConstants.DSICTIPOIVA.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICTipoIVA());
        if (TipoIVAConstants.DSDSTIPOIVAPIU.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSTipoIVAPIU());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (TipoIVAConstants.FILTER_NAME_FTIPOIVAVARIOS.equalsIgnoreCase(filterRequest.getName()))
            fTipoIVAVarios(filterRequest, sql);
        if (TipoIVAConstants.FILTER_NAME_IFTIPOIVAPRODPLANANYO.equalsIgnoreCase(filterRequest.getName()))
            iFTipoIVAProdPlanAnyo(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_TipoIVA".equals(filterRequest.getName()))
            suppInfo4ICTipoIVA(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void fTipoIVAVarios(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Productos voProducto = filterRequest.hasFilterVariable(TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VOPRODUCTO) ? productosSrv.getByOID(new ProductosOid().fromJsonString(toJson(filterRequest.getFilterVariable(TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VOPRODUCTO)))) : null;
        Planes voPlan = filterRequest.hasFilterVariable(TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VOPLAN) ? planesSrv.getByOID(new PlanesOid().fromJsonString(toJson(filterRequest.getFilterVariable(TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VOPLAN)))) : null;
        Date vDesde = filterRequest.hasFilterVariable(TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VDESDE) ? (Date) filterRequest.getFilterVariable(TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VDESDE, FilterVariableRequest.DATE) : null;
        Date vHasta = filterRequest.hasFilterVariable(TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VHASTA) ? (Date) filterRequest.getFilterVariable(TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VHASTA, FilterVariableRequest.DATE) : null;

        // ProductoT = voProducto
        if (voProducto != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(TipoIVAConstants.ROLE_NAME_PRODUCTOT, ProductosConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VOPRODUCTO, ProductosConstants.CLASS_NAME, ProductosConstants.CLASS_NAME, voProducto.getOid(), TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VOPRODUCTO);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // PlanT = voPlan
        if (voPlan != null) {
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(TipoIVAConstants.ROLE_NAME_PLANT, PlanesConstants.CLASS_NAME);
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            SQLWhereOperandOVParameter operand224 = new SQLWhereOperandOVParameter(TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VOPLAN, PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, voPlan.getOid(), TipoIVAConstants.VAR_NAME_FTIPOIVAVARIOS_VOPLAN);
            comparison2.setSecondOperand(operand224);
            conjunction.addOperand(comparison2);

        }        

        // (FechaInicio <= vDesde AND vDesde <= FechaFin OR FechaInicio <= vHasta AND vHasta <= FechaFin)
        if (vDesde != null && vHasta != null) {
        {
            // 'and' Logical operator
            SQLWhereConjunction comparison3 = new SQLWhereConjunction();
        
            // Operand 1
                {
                    // 'or' Logical operator
                    SQLWhereDisjunction comparison4 = new SQLWhereDisjunction();
                
                    // Operand 1
                        {
                            // 'and' Logical operator
                            SQLWhereConjunction comparison5 = new SQLWhereConjunction();
                        
                            // Operand 1
                                    // '<=' Comparison
                                    SQLWhereComparison comparison6 = new SQLWhereComparison(sql);
                                    comparison6.setOperator("<=");
                                    
                                    // Operand 1
                                    SQLWhereOperandPathAttribute operand117 = new SQLWhereOperandPathAttribute(TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAINICIO, Constants.Type.DATE.getTypeName());
                                    comparison6.setFirstOperand(operand117);
                                    
                                    // Operand 2
                                    comparison6.setSecondOperand(new SQLWhereOperandLiteral(vDesde, Constants.Type.DATE.getTypeName()));
                                    comparison5.addOperand(comparison6);
                        
                            // Operand 2
                                    // '<=' Comparison
                                    SQLWhereComparison comparison8 = new SQLWhereComparison(sql);
                                    comparison8.setOperator("<=");
                                    
                                    // Operand 1
                                    comparison8.setFirstOperand(new SQLWhereOperandLiteral(vDesde, Constants.Type.DATE.getTypeName()));
                                    
                                    // Operand 2
                                    SQLWhereOperandPathAttribute operand2210 = new SQLWhereOperandPathAttribute(TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAFIN, Constants.Type.DATE.getTypeName());
                                    comparison8.setSecondOperand(operand2210);
                                    comparison5.addOperand(comparison8);
                            
                            comparison4.addOperand(comparison5);
                        }
                
                    // Operand 2
                            // '<=' Comparison
                            SQLWhereComparison comparison9 = new SQLWhereComparison(sql);
                            comparison9.setOperator("<=");
                            
                            // Operand 1
                            SQLWhereOperandPathAttribute operand1110 = new SQLWhereOperandPathAttribute(TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAINICIO, Constants.Type.DATE.getTypeName());
                            comparison9.setFirstOperand(operand1110);
                            
                            // Operand 2
                            comparison9.setSecondOperand(new SQLWhereOperandLiteral(vHasta, Constants.Type.DATE.getTypeName()));
                            comparison4.addOperand(comparison9);
                    
                    comparison3.addOperand(comparison4);
                }
        
            // Operand 2
                    // '<=' Comparison
                    SQLWhereComparison comparison10 = new SQLWhereComparison(sql);
                    comparison10.setOperator("<=");
                    
                    // Operand 1
                    comparison10.setFirstOperand(new SQLWhereOperandLiteral(vHasta, Constants.Type.DATE.getTypeName()));
                    
                    // Operand 2
                    SQLWhereOperandPathAttribute operand2212 = new SQLWhereOperandPathAttribute(TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAFIN, Constants.Type.DATE.getTypeName());
                    comparison10.setSecondOperand(operand2212);
                    comparison3.addOperand(comparison10);
            
            conjunction.addOperand(comparison3);
        }

        }        

        sql.addConjunction(conjunction);
    }

    private void iFTipoIVAProdPlanAnyo(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Productos voProducto = filterRequest.hasFilterVariable(TipoIVAConstants.VAR_NAME_IFTIPOIVAPRODPLANANYO_VOPRODUCTO) ? productosSrv.getByOID(new ProductosOid().fromJsonString(toJson(filterRequest.getFilterVariable(TipoIVAConstants.VAR_NAME_IFTIPOIVAPRODPLANANYO_VOPRODUCTO)))) : null;
        Planes voPlan = filterRequest.hasFilterVariable(TipoIVAConstants.VAR_NAME_IFTIPOIVAPRODPLANANYO_VOPLAN) ? planesSrv.getByOID(new PlanesOid().fromJsonString(toJson(filterRequest.getFilterVariable(TipoIVAConstants.VAR_NAME_IFTIPOIVAPRODPLANANYO_VOPLAN)))) : null;
        Date vFecha = filterRequest.hasFilterVariable(TipoIVAConstants.VAR_NAME_IFTIPOIVAPRODPLANANYO_VFECHA) ? (Date) filterRequest.getFilterVariable(TipoIVAConstants.VAR_NAME_IFTIPOIVAPRODPLANANYO_VFECHA, FilterVariableRequest.DATE) : null;

        // ProductoT = voProducto
        if (voProducto != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(TipoIVAConstants.ROLE_NAME_PRODUCTOT, ProductosConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(TipoIVAConstants.VAR_NAME_IFTIPOIVAPRODPLANANYO_VOPRODUCTO, ProductosConstants.CLASS_NAME, ProductosConstants.CLASS_NAME, voProducto.getOid(), TipoIVAConstants.VAR_NAME_IFTIPOIVAPRODPLANANYO_VOPRODUCTO);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // PlanT = voPlan
        if (voPlan != null) {
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(TipoIVAConstants.ROLE_NAME_PLANT, PlanesConstants.CLASS_NAME);
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            SQLWhereOperandOVParameter operand224 = new SQLWhereOperandOVParameter(TipoIVAConstants.VAR_NAME_IFTIPOIVAPRODPLANANYO_VOPLAN, PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, voPlan.getOid(), TipoIVAConstants.VAR_NAME_IFTIPOIVAPRODPLANANYO_VOPLAN);
            comparison2.setSecondOperand(operand224);
            conjunction.addOperand(comparison2);

        }        

        // FechaInicio <= vFecha
        if (vFecha != null) {
            // '<=' Comparison
            SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
            comparison3.setOperator("<=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAINICIO, Constants.Type.DATE.getTypeName());
            comparison3.setFirstOperand(operand114);
            
            // Operand 2
            comparison3.setSecondOperand(new SQLWhereOperandLiteral(vFecha, Constants.Type.DATE.getTypeName()));
            conjunction.addOperand(comparison3);

        }        

        // vFecha <= FechaFin
        if (vFecha != null) {
            // '<=' Comparison
            SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
            comparison4.setOperator("<=");
            
            // Operand 1
            comparison4.setFirstOperand(new SQLWhereOperandLiteral(vFecha, Constants.Type.DATE.getTypeName()));
            
            // Operand 2
            SQLWhereOperandPathAttribute operand226 = new SQLWhereOperandPathAttribute(TipoIVAConstants.ATTR_NAME_TIPOIVAFECHAFIN, Constants.Type.DATE.getTypeName());
            comparison4.setSecondOperand(operand226);
            conjunction.addOperand(comparison4);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUTipoIVA
     *
     * @param queryRequest Query request
     * @return List of viewmodels for TipoIVA using displayset DSTipoIVAPIU that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.tipoiva.ds.DSTipoIVAPIU> piuPIUTipoIVA(QueryRequest queryRequest) {
        return solveTipoIVATypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.tipoiva.ds.DSTipoIVAPIU::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUTipoIVA
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUTipoIVA
     */
    public int getCount4PIUTipoIVA(QueryRequest queryRequest) {
        return solveTipoIVATypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIVASrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.CrearRequest request) throws ModelException {
        request.setTipoIVAcrearpagrProductoTInstance(productosSrv.getByOIDWithHV(new ProductosOid().fromJsonString(request.getTipoIVAcrearpagrProductoT())));
        request.setTipoIVAcrearpagrPlanTInstance(planesSrv.getByOIDWithHV(new PlanesOid().fromJsonString(request.getTipoIVAcrearpagrPlanT())));
        return tipoIVASrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Productos tipoIVAcrearpagrProductoT = request.getTipoIVAcrearpagrProductoTInstance();
        Planes tipoIVAcrearpagrPlanT = request.getTipoIVAcrearpagrPlanTInstance();
        String tipoIVAcrearpatrDescripcion = request.getTipoIVAcrearpatrDescripcion();
        Double tipoIVAcrearpatrPorcentaje = request.getTipoIVAcrearpatrPorcentaje();
        Date tipoIVAcrearpatrFechaInicio = request.getTipoIVAcrearpatrFechaInicio();
        Date tipoIVAcrearpatrFechaFin = request.getTipoIVAcrearpatrFechaFin();

        request.checkArguments();

        crearPreconditions(request);
        TipoIVA instance = new TipoIVA();
        instance.add2ProductoT(tipoIVAcrearpagrProductoT);

        instance.add2PlanT(tipoIVAcrearpagrPlanT);

        instance.setTipoIVACodigo(Long.valueOf(0));
        instance.setTipoIVADescripcion(tipoIVAcrearpatrDescripcion);
        instance.setTipoIVAPorcentaje(tipoIVAcrearpatrPorcentaje);
        instance.setTipoIVAFechaInicio(tipoIVAcrearpatrFechaInicio);
        instance.setTipoIVAFechaFin(tipoIVAcrearpatrFechaFin);

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIVA());

        return response;
    }

    /*
     * Checks service crear preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse crearCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setTipoIVAcrearpagrProductoTInstance(productosSrv.getByOIDWithHV(new ProductosOid().fromJsonString(request.getTipoIVAcrearpagrProductoT())));
        request.setTipoIVAcrearpagrPlanTInstance(planesSrv.getByOIDWithHV(new PlanesOid().fromJsonString(request.getTipoIVAcrearpagrPlanT())));
        try {
            if("Clas_1431882432512691Pre_5".equals(request.getPreconditionId()))
                checkClas1431882432512691Pre5(request);
            if("Clas_1431882432512691Pre_1".equals(request.getPreconditionId()))
                checkClas1431882432512691Pre1(request);
            if("Clas_1431882432512691Pre_2".equals(request.getPreconditionId()))
                checkClas1431882432512691Pre2(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void crearPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.CrearRequest request) 
            throws ModelException {
        checkClas1431882432512691Pre5(request);
        checkClas1431882432512691Pre1(request);
        checkClas1431882432512691Pre2(request);
    }

    private void checkClas1431882432512691Pre5 (
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.CrearRequest request) 
            throws ModelException {
        // patrFechaInicio <= patrFechaFin

        Date tipoIVAcrearpatrFechaInicio = request.getTipoIVAcrearpatrFechaInicio();
        Date tipoIVAcrearpatrFechaFin = request.getTipoIVAcrearpatrFechaFin();
        boolean preconditionHolds = (STDFunctions.dateBeforeFun(tipoIVAcrearpatrFechaInicio,tipoIVAcrearpatrFechaFin) || STDFunctions.dateEqualsFun(tipoIVAcrearpatrFechaInicio, tipoIVAcrearpatrFechaFin));
        if (!preconditionHolds) throw new PreconditionException("La fecha de inicio debe ser anterior a la fecha fin");
    }

    private void checkClas1431882432512691Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.CrearRequest request) 
            throws ModelException {
        // COUNT( pagrProductoT.TiposIVA ) WHERE (pagrProductoT.TiposIVA.PlanT = pagrPlanT AND ((pagrProductoT.TiposIVA.FechaInicio >= patrFechaInicio AND pagrProductoT.TiposIVA.FechaInicio <= patrFechaFin) OR (pagrProductoT.TiposIVA.FechaFin >= patrFechaInicio AND pagrProductoT.TiposIVA.FechaFin <= patrFechaFin))) = 0

        Productos tipoIVAcrearpagrProductoT = request.getTipoIVAcrearpagrProductoTInstance();
        Planes tipoIVAcrearpagrPlanT = request.getTipoIVAcrearpagrPlanTInstance();
        Date tipoIVAcrearpatrFechaInicio = request.getTipoIVAcrearpatrFechaInicio();
        Date tipoIVAcrearpatrFechaFin = request.getTipoIVAcrearpatrFechaFin();
        try {
            boolean preconditionHolds = (repository.assocOperator000(tipoIVAcrearpagrProductoT, tipoIVAcrearpagrPlanT, tipoIVAcrearpatrFechaInicio, tipoIVAcrearpatrFechaFin)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Existe un Tipo de IVA para el Producto, Plan cuyas fechas solapan con las diligenciadas");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

    private void checkClas1431882432512691Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.CrearRequest request) 
            throws ModelException {
        // COUNT( pagrProductoT.TiposIVA ) WHERE (pagrProductoT.TiposIVA.PlanT = pagrPlanT AND pagrProductoT.TiposIVA.FechaInicio < patrFechaInicio AND pagrProductoT.TiposIVA.FechaFin > patrFechaFin) = 0

        Productos tipoIVAcrearpagrProductoT = request.getTipoIVAcrearpagrProductoTInstance();
        Planes tipoIVAcrearpagrPlanT = request.getTipoIVAcrearpagrPlanTInstance();
        Date tipoIVAcrearpatrFechaInicio = request.getTipoIVAcrearpatrFechaInicio();
        Date tipoIVAcrearpatrFechaFin = request.getTipoIVAcrearpatrFechaFin();
        try {
            boolean preconditionHolds = (repository.assocOperator001(tipoIVAcrearpagrProductoT, tipoIVAcrearpagrPlanT, tipoIVAcrearpatrFechaInicio, tipoIVAcrearpatrFechaFin)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Existe un Tipo de IVA para el Producto, Plan que contiene a las fechas diligenciadas");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
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
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIVASrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.EliminarRequest request) throws ModelException {
        request.setTipoIVAeliminarpthisTipoIVAInstance(tipoIVASrv.getByOIDWithHV(request.getTipoIVAeliminarpthisTipoIVA()));
        return tipoIVASrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIVA tipoIVAeliminarpthisTipoIVA = request.getTipoIVAeliminarpthisTipoIVAInstance();

        request.checkArguments();
        TipoIVA instance = tipoIVAeliminarpthisTipoIVA;


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICTipoIVA());

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
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tipoIVASrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.ModificarRequest request) throws ModelException {
        request.setTipoIVAmodificarpthisTipoIVAInstance(tipoIVASrv.getByOIDWithHV(request.getTipoIVAmodificarpthisTipoIVA()));
        return tipoIVASrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        TipoIVA tipoIVAmodificarpthisTipoIVA = request.getTipoIVAmodificarpthisTipoIVAInstance();
        String tipoIVAmodificarpDescripcion = request.getTipoIVAmodificarpDescripcion();
        Double tipoIVAmodificarpPorcentaje = request.getTipoIVAmodificarpPorcentaje();
        Date tipoIVAmodificarpFechaInicio = request.getTipoIVAmodificarpFechaInicio();
        Date tipoIVAmodificarpFechaFin = request.getTipoIVAmodificarpFechaFin();

        request.checkArguments();

        modificarPreconditions(request);
        TipoIVA instance = tipoIVAmodificarpthisTipoIVA;
        instance.setTipoIVADescripcion(tipoIVAmodificarpDescripcion);
        instance.setTipoIVAPorcentaje(tipoIVAmodificarpPorcentaje);
        instance.setTipoIVAFechaInicio(tipoIVAmodificarpFechaInicio);
        instance.setTipoIVAFechaFin(tipoIVAmodificarpFechaFin);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTipoIVA());

        return response;
    }

    /*
     * Checks service modificar preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse modificarCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setTipoIVAmodificarpthisTipoIVAInstance(tipoIVASrv.getByOIDWithHV(request.getTipoIVAmodificarpthisTipoIVA()));
        try {
            if("Clas_1431882432512691Pre_6".equals(request.getPreconditionId()))
                checkClas1431882432512691Pre6(request);
            if("Clas_1431882432512691Pre_3".equals(request.getPreconditionId()))
                checkClas1431882432512691Pre3(request);
            if("Clas_1431882432512691Pre_4".equals(request.getPreconditionId()))
                checkClas1431882432512691Pre4(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void modificarPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.ModificarRequest request) 
            throws ModelException {
        checkClas1431882432512691Pre6(request);
        checkClas1431882432512691Pre3(request);
        checkClas1431882432512691Pre4(request);
    }

    private void checkClas1431882432512691Pre6 (
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.ModificarRequest request) 
            throws ModelException {
        // pFechaInicio <= pFechaFin

        Date tipoIVAmodificarpFechaInicio = request.getTipoIVAmodificarpFechaInicio();
        Date tipoIVAmodificarpFechaFin = request.getTipoIVAmodificarpFechaFin();
        boolean preconditionHolds = (STDFunctions.dateBeforeFun(tipoIVAmodificarpFechaInicio,tipoIVAmodificarpFechaFin) || STDFunctions.dateEqualsFun(tipoIVAmodificarpFechaInicio, tipoIVAmodificarpFechaFin));
        if (!preconditionHolds) throw new PreconditionException("La fecha de inicio debe ser anterior a la fecha fin");
    }

    private void checkClas1431882432512691Pre3 (
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.ModificarRequest request) 
            throws ModelException {
        // COUNT( ProductoT.TiposIVA ) WHERE (ProductoT.TiposIVA <> pthisTipoIVA AND ProductoT.TiposIVA.PlanT = PlanT AND ((ProductoT.TiposIVA.FechaInicio >= pFechaInicio AND ProductoT.TiposIVA.FechaInicio <= pFechaFin) OR (ProductoT.TiposIVA.FechaFin >= pFechaInicio AND ProductoT.TiposIVA.FechaFin <= pFechaFin))) = 0

        TipoIVA tipoIVAmodificarpthisTipoIVA = request.getTipoIVAmodificarpthisTipoIVAInstance();
        Date tipoIVAmodificarpFechaInicio = request.getTipoIVAmodificarpFechaInicio();
        Date tipoIVAmodificarpFechaFin = request.getTipoIVAmodificarpFechaFin();
        try {
            boolean preconditionHolds = (repository.assocOperator002(tipoIVAmodificarpthisTipoIVA, tipoIVAmodificarpthisTipoIVA, tipoIVAmodificarpFechaInicio, tipoIVAmodificarpFechaFin)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Existe un Tipo de IVA para el Producto, Plan cuyas fechas solapan con las diligenciadas");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

    private void checkClas1431882432512691Pre4 (
            com.keralty.aeconfiguraciones.viewmodel.tipoiva.siu.ModificarRequest request) 
            throws ModelException {
        // COUNT( ProductoT.TiposIVA ) WHERE (ProductoT.TiposIVA <> pthisTipoIVA AND ProductoT.TiposIVA.PlanT = PlanT AND ProductoT.TiposIVA.FechaInicio < pFechaInicio AND ProductoT.TiposIVA.FechaFin > pFechaFin) = 0

        TipoIVA tipoIVAmodificarpthisTipoIVA = request.getTipoIVAmodificarpthisTipoIVAInstance();
        Date tipoIVAmodificarpFechaInicio = request.getTipoIVAmodificarpFechaInicio();
        Date tipoIVAmodificarpFechaFin = request.getTipoIVAmodificarpFechaFin();
        try {
            boolean preconditionHolds = (repository.assocOperator003(tipoIVAmodificarpthisTipoIVA, tipoIVAmodificarpthisTipoIVA, tipoIVAmodificarpFechaInicio, tipoIVAmodificarpFechaFin)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Existe un Tipo de IVA para el Producto, Plan que contiene a las fechas diligenciadas");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }


    private void suppInfo4ICTipoIVA(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TipoIVAConstants.ATTR_NAME_TIPOIVADESCRIPCION, Constants.Type.STRING.getTypeName()));
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
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(TipoIVAConstants.CLASS_NAME, TipoIVAConstants.ATTR_FIELD_TIPOIVACODIGO, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildTipoIVARelatedInstance(relatedInstanceJsonOid);
        }
        if (TipoIVAConstants.ROLE_NAME_PRODUCTOT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoIVARelatedInstance4Productos(relatedInstanceJsonOid, remainingPath);
        }
        if (TipoIVAConstants.ROLE_NAME_PLANT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTipoIVARelatedInstance4Planes(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildTipoIVARelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new TipoIVA(mapper.readValue(relatedInstanceJsonOid, TipoIVAOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoIVARelatedInstance4Productos(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Productos(mapper.readValue(relatedInstanceJsonOid, ProductosOid.class)): Productos.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTipoIVARelatedInstance4Planes(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Planes(mapper.readValue(relatedInstanceJsonOid, PlanesOid.class)): Planes.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            TipoIVAOid oid = new TipoIVAOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TipoIVAConstants.ATTR_FIELD_TIPOIVACODIGO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getTipoIVACodigo(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(TipoIVAService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
