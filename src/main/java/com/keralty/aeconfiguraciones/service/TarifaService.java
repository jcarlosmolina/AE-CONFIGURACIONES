package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.global.ProductosConstants;
import com.keralty.aeconfiguraciones.global.TarifaConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.ContratoC;
import com.keralty.aeconfiguraciones.persistence.DetalleTarifa;
import com.keralty.aeconfiguraciones.persistence.InstitucionMatNeo;
import com.keralty.aeconfiguraciones.persistence.oid.ContratoCOid;
import com.keralty.aeconfiguraciones.persistence.oid.DetalleTarifaOid;
import com.keralty.aeconfiguraciones.persistence.oid.InstitucionMatNeoOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Tarifa;
import com.keralty.aeconfiguraciones.repository.TarifaJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathInstance;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandSubQuery;
import com.keralty.aeconfiguraciones.utils.Utilities;
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
public class TarifaService extends AbstractService {

    TarifaJpaRepository repository;

    TarifaService tarifaSrv;

    PlanesService planesSrv;

    ProductosService productosSrv;

    InstitucionMatNeoService institucionMatNeoSrv;

    DetalleTarifaService detalleTarifaSrv;

    ContratoCService contratoCSrv;

    @Inject
    public TarifaService(
            TarifaService tarifaSrv,
            PlanesService planesSrv,
            ProductosService productosSrv,
            InstitucionMatNeoService institucionMatNeoSrv,
            DetalleTarifaService detalleTarifaSrv,
            ContratoCService contratoCSrv,
            TarifaJpaRepository repository) {
        this.tarifaSrv = tarifaSrv;
        this.planesSrv = planesSrv;
        this.productosSrv = productosSrv;
        this.institucionMatNeoSrv = institucionMatNeoSrv;
        this.detalleTarifaSrv = detalleTarifaSrv;
        this.contratoCSrv = contratoCSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, TarifaConstants.CLASS_NAME);
    }

    private List<Tarifa> solveTarifaTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, TarifaConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveTarifaTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, TarifaConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Tarifa
     *
     * @return Population of class Tarifa
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Tarifa> get() {
        List<Tarifa> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class Tarifa
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class Tarifa
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<Tarifa> instances = get(queryRequest);
        if(TarifaConstants.DSICTARIFA.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICTarifa())).collect(Collectors.toList());
        }
        if(TarifaConstants.DSDSTARIFAS.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSTarifas())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class Tarifa that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Tarifa matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Tarifa> get(QueryRequest queryRequest) {
        return solveTarifaTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveTarifaTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveTarifaTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveTarifaTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveTarifaTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Tarifa by ID
     *
     * @param tarifaId Identification field tarifaId
     *
     * @return Instance of Tarifa
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Tarifa getById(Long tarifaId) {
        return getByOID(new TarifaOid(tarifaId));
    }
    
    /*
     * Returns an instance of Tarifa by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Tarifa
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Tarifa getByOID(TarifaOid oid) {
        Optional<Tarifa> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Tarifa by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Tarifa
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Tarifa getByOIDWithHV(TarifaOid oid) {
        Optional<Tarifa> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Tarifa> getInstanceByOid(TarifaOid oid) {
        return oid != null && oid.getTarifaId() != null
               ? repository.findById(oid.getTarifaId())
               : Optional.empty();
    }

    /*
     * Returns an instance of Tarifa by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Tarifa
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Tarifa getByJsonOid(String jsonOid) {
        TarifaOid oid = new TarifaOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Tarifa related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Tarifa
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Tarifa> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveTarifaTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Tarifa related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Tarifa
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Tarifa> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Tarifa
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Tarifa
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getTarifaDynamic(TarifaOid oid, String displaySetItems) {
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
    public String getSuppInfo(TarifaOid oid, String dsName) {
        Tarifa instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Tarifa instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (TarifaConstants.DSICTARIFA.equals(dsName))
            return instance.buildICTarifa();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(Tarifa instance, String dsName) {
        if (TarifaConstants.DSICTARIFA.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICTarifa());
        if (TarifaConstants.DSDSTARIFAS.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSTarifas());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (TarifaConstants.FILTER_NAME_FTARIFAVARIOS.equalsIgnoreCase(filterRequest.getName()))
            fTarifaVarios(filterRequest, sql);
        if (TarifaConstants.FILTER_NAME_IFTARIFAEXTRAECODIGO.equalsIgnoreCase(filterRequest.getName()))
            iFTarifaExtraeCodigo(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_Tarifa".equals(filterRequest.getName()))
            suppInfo4ICTarifa(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void fTarifaVarios(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Productos voProducto = filterRequest.hasFilterVariable(TarifaConstants.VAR_NAME_FTARIFAVARIOS_VOPRODUCTO) ? productosSrv.getByOID(new ProductosOid().fromJsonString(toJson(filterRequest.getFilterVariable(TarifaConstants.VAR_NAME_FTARIFAVARIOS_VOPRODUCTO)))) : null;
        Planes voPlan = filterRequest.hasFilterVariable(TarifaConstants.VAR_NAME_FTARIFAVARIOS_VOPLAN) ? planesSrv.getByOID(new PlanesOid().fromJsonString(toJson(filterRequest.getFilterVariable(TarifaConstants.VAR_NAME_FTARIFAVARIOS_VOPLAN)))) : null;

        // ProductoT = voProducto
        if (voProducto != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(TarifaConstants.ROLE_NAME_PRODUCTOT, ProductosConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(TarifaConstants.VAR_NAME_FTARIFAVARIOS_VOPRODUCTO, ProductosConstants.CLASS_NAME, ProductosConstants.CLASS_NAME, voProducto.getOid(), TarifaConstants.VAR_NAME_FTARIFAVARIOS_VOPRODUCTO);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // PlanT = voPlan
        if (voPlan != null) {
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(TarifaConstants.ROLE_NAME_PLANT, PlanesConstants.CLASS_NAME);
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            SQLWhereOperandOVParameter operand224 = new SQLWhereOperandOVParameter(TarifaConstants.VAR_NAME_FTARIFAVARIOS_VOPLAN, PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, voPlan.getOid(), TarifaConstants.VAR_NAME_FTARIFAVARIOS_VOPLAN);
            comparison2.setSecondOperand(operand224);
            conjunction.addOperand(comparison2);

        }        

        sql.addConjunction(conjunction);
    }

    private void iFTarifaExtraeCodigo(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Productos voProducto = filterRequest.hasFilterVariable(TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VOPRODUCTO) ? productosSrv.getByOID(new ProductosOid().fromJsonString(toJson(filterRequest.getFilterVariable(TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VOPRODUCTO)))) : null;
        Planes voPlan = filterRequest.hasFilterVariable(TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VOPLAN) ? planesSrv.getByOID(new PlanesOid().fromJsonString(toJson(filterRequest.getFilterVariable(TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VOPLAN)))) : null;
        Long vTipoContrato = filterRequest.hasFilterVariable(TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VTIPOCONTRATO) ? (Long) filterRequest.getFilterVariable(TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VTIPOCONTRATO, FilterVariableRequest.NAT) : null;
        Long vfAnyo = filterRequest.hasFilterVariable(TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VFANYO) ? (Long) filterRequest.getFilterVariable(TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VFANYO, FilterVariableRequest.INT) : null;

        // ProductoT = voProducto
        if (voProducto != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(TarifaConstants.ROLE_NAME_PRODUCTOT, ProductosConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VOPRODUCTO, ProductosConstants.CLASS_NAME, ProductosConstants.CLASS_NAME, voProducto.getOid(), TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VOPRODUCTO);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // PlanT = voPlan
        if (voPlan != null) {
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(TarifaConstants.ROLE_NAME_PLANT, PlanesConstants.CLASS_NAME);
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            SQLWhereOperandOVParameter operand224 = new SQLWhereOperandOVParameter(TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VOPLAN, PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, voPlan.getOid(), TarifaConstants.VAR_NAME_IFTARIFAEXTRAECODIGO_VOPLAN);
            comparison2.setSecondOperand(operand224);
            conjunction.addOperand(comparison2);

        }        

        // TipoContrato = vTipoContrato
        if (vTipoContrato != null) {
            // '=' Comparison
            SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
            comparison3.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(TarifaConstants.ATTR_NAME_TARIFATIPOCONTRATO, Constants.Type.NAT.getTypeName());
            comparison3.setFirstOperand(operand114);
            
            // Operand 2
            comparison3.setSecondOperand(new SQLWhereOperandLiteral(vTipoContrato, Constants.Type.NAT.getTypeName()));
            conjunction.addOperand(comparison3);

        }        

        // EXIST (DetallesTarifa) WHERE (DetallesTarifa.Anyo = vfAnyo) = true
        if (vfAnyo != null) {
    // 'exist' Association operator
    SQLWhereOperandSubQuery operandSubQueryAssocOperator000 = new SQLWhereOperandSubQuery(repository.assocOperator000Range1Query(vfAnyo, sql));
    conjunction.addOperand(operandSubQueryAssocOperator000);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUTarifas
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Tarifa using displayset DSTarifas that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.tarifa.ds.DSTarifas> piuPIUTarifas(QueryRequest queryRequest) {
        return solveTarifaTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.tarifa.ds.DSTarifas::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUTarifas
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUTarifas
     */
    public int getCount4PIUTarifas(QueryRequest queryRequest) {
        return solveTarifaTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tarifaSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CrearRequest request) throws ModelException {
        request.setTarifacrearpagrPlanTInstance(planesSrv.getByOIDWithHV(request.getTarifacrearpagrPlanT()));
        request.setTarifacrearpagrProductoTInstance(productosSrv.getByOIDWithHV(request.getTarifacrearpagrProductoT()));
        return tarifaSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Planes tarifacrearpagrPlanT = request.getTarifacrearpagrPlanTInstance();
        Productos tarifacrearpagrProductoT = request.getTarifacrearpagrProductoTInstance();
        String tarifacrearpatrCodigo = request.getTarifacrearpatrCodigo();
        Long tarifacrearpatrTipoContrato = request.getTarifacrearpatrTipoContrato();

        request.checkArguments();

        crearPreconditions(request);
        Tarifa instance = new Tarifa();
        instance.add2PlanT(tarifacrearpagrPlanT);

        instance.add2ProductoT(tarifacrearpagrProductoT);

        instance.setTarifaId(Long.valueOf(0));
        instance.setTarifaCodigo(tarifacrearpatrCodigo);
        instance.setTarifaTipoContrato(tarifacrearpatrTipoContrato);

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTarifa());

        return response;
    }

    /*
     * Checks service crear preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse crearCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setTarifacrearpagrPlanTInstance(planesSrv.getByOIDWithHV(request.getTarifacrearpagrPlanT()));
        request.setTarifacrearpagrProductoTInstance(productosSrv.getByOIDWithHV(request.getTarifacrearpagrProductoT()));
        try {
            if("Clas_1431714529280565Pre_4".equals(request.getPreconditionId()))
                checkClas1431714529280565Pre4(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void crearPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CrearRequest request) 
            throws ModelException {
        checkClas1431714529280565Pre4(request);
    }

    private void checkClas1431714529280565Pre4 (
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CrearRequest request) 
            throws ModelException {
        // pagrPlanT.ProductoT = pagrProductoT

        Planes tarifacrearpagrPlanT = request.getTarifacrearpagrPlanTInstance();
        Productos tarifacrearpagrProductoT = request.getTarifacrearpagrProductoTInstance();
        boolean preconditionHolds = Utilities.isEqual(tarifacrearpagrPlanT.getProductoT(), tarifacrearpagrProductoT);
        if (!preconditionHolds) throw new PreconditionException("El Plan seleccionado no pertenece al Producto");
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
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tarifaSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.EliminarRequest request) throws ModelException {
        request.setTarifaeliminarpthisTarifaInstance(tarifaSrv.getByOIDWithHV(request.getTarifaeliminarpthisTarifa()));
        return tarifaSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Tarifa tarifaeliminarpthisTarifa = request.getTarifaeliminarpthisTarifaInstance();

        request.checkArguments();

        eliminarPreconditions(request);
        Tarifa instance = tarifaeliminarpthisTarifa;
        if(tarifaeliminarpthisTarifa.getContratosC().size() > 0) {
            throw new MinCardinalityException(TarifaConstants.CLASS_ALIAS, TarifaConstants.ROLE_ALIAS_CONTRATOSC);
        }
        if(tarifaeliminarpthisTarifa.getDetallesTarifa().size() > 0) {
            throw new MinCardinalityException(TarifaConstants.CLASS_ALIAS, TarifaConstants.ROLE_ALIAS_DETALLESTARIFA);
        }
        if(tarifaeliminarpthisTarifa.getInstitucionMatNeo().size() > 0) {
            throw new MinCardinalityException(TarifaConstants.CLASS_ALIAS, TarifaConstants.ROLE_ALIAS_INSTITUCIONMATNEO);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICTarifa());

        return response;
    }

    /*
     * Checks service eliminar preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse eliminarCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setTarifaeliminarpthisTarifaInstance(tarifaSrv.getByOIDWithHV(request.getTarifaeliminarpthisTarifa()));
        try {
            if("Clas_1431714529280565Pre_2".equals(request.getPreconditionId()))
                checkClas1431714529280565Pre2(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void eliminarPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.EliminarRequest request) 
            throws ModelException {
        checkClas1431714529280565Pre2(request);
    }

    private void checkClas1431714529280565Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.EliminarRequest request) 
            throws ModelException {
        // EXIST(InstitucionMatNeo) = FALSE

        Tarifa tarifaeliminarpthisTarifa = request.getTarifaeliminarpthisTarifaInstance();
        try {
            boolean preconditionHolds = repository.assocOperator001(tarifaeliminarpthisTarifa).equals(false);
            if (!preconditionHolds) throw new PreconditionException("No se puede eliminar la tarifa porque existen instituciones de maternidad o neonatal que la tienen especificada");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
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
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.SincronizarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tarifaSrv.sincronizarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.SincronizarRequest request) throws ModelException {
        request.setTarifasincronizarpthisTarifaInstance(tarifaSrv.getByOIDWithHV(request.getTarifasincronizarpthisTarifa()));
        return tarifaSrv.sincronizarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.SincronizarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Tarifa tarifasincronizarpthisTarifa = request.getTarifasincronizarpthisTarifaInstance();
        Long tarifasincronizarpTipoContrato = request.getTarifasincronizarpTipoContrato();

        request.checkArguments();
        Tarifa instance = tarifasincronizarpthisTarifa;
        instance.setTarifaTipoContrato(tarifasincronizarpTipoContrato);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTarifa());

        return response;
    }

    // Service: CambPlanTarifa

    /*
     * Entry point for service CambPlanTarifa
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambPlanTarifa(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CambPlanTarifaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tarifaSrv.cambPlanTarifaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambPlanTarifa
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambPlanTarifaTxn(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CambPlanTarifaRequest request) throws ModelException {
        request.setTarifaCambPlanTarifapthisTarifaInstance(tarifaSrv.getByOIDWithHV(request.getTarifaCambPlanTarifapthisTarifa()));
        request.setTarifaCambPlanTarifapevcPlanesInstance(planesSrv.getByOIDWithHV(request.getTarifaCambPlanTarifapevcPlanes()));
        return tarifaSrv.cambPlanTarifaImplementation(request, true);
    }

    /*
     * Implements service CambPlanTarifa
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambPlanTarifaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CambPlanTarifaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Tarifa tarifaCambPlanTarifapthisTarifa = request.getTarifaCambPlanTarifapthisTarifaInstance();
        Planes tarifaCambPlanTarifapevcPlanes = request.getTarifaCambPlanTarifapevcPlanesInstance();

        request.checkArguments();
        Tarifa instance = tarifaCambPlanTarifapthisTarifa;

        instance.add2PlanT(tarifaCambPlanTarifapevcPlanes);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.planes.siu.CambPlanTarifaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.planes.siu.CambPlanTarifaRequest();
            relatedRequest.setPlanesCambPlanTarifapthisPlanInstance(tarifaCambPlanTarifapevcPlanes);
            relatedRequest.setPlanesCambPlanTarifapevcTarifaInstance(tarifaCambPlanTarifapthisTarifa);
            planesSrv.cambPlanTarifaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTarifa());

        return response;
    }

    // Service: CambProductoTarifa

    /*
     * Entry point for service CambProductoTarifa
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambProductoTarifa(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CambProductoTarifaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tarifaSrv.cambProductoTarifaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambProductoTarifa
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambProductoTarifaTxn(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CambProductoTarifaRequest request) throws ModelException {
        request.setTarifaCambProductoTarifapthisTarifaInstance(tarifaSrv.getByOIDWithHV(request.getTarifaCambProductoTarifapthisTarifa()));
        request.setTarifaCambProductoTarifapevcProductosInstance(productosSrv.getByOIDWithHV(request.getTarifaCambProductoTarifapevcProductos()));
        return tarifaSrv.cambProductoTarifaImplementation(request, true);
    }

    /*
     * Implements service CambProductoTarifa
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambProductoTarifaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CambProductoTarifaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Tarifa tarifaCambProductoTarifapthisTarifa = request.getTarifaCambProductoTarifapthisTarifaInstance();
        Productos tarifaCambProductoTarifapevcProductos = request.getTarifaCambProductoTarifapevcProductosInstance();

        request.checkArguments();
        Tarifa instance = tarifaCambProductoTarifapthisTarifa;

        instance.add2ProductoT(tarifaCambProductoTarifapevcProductos);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.productos.siu.CambProductoTarifaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.productos.siu.CambProductoTarifaRequest();
            relatedRequest.setProductosCambProductoTarifapthisProductoInstance(tarifaCambProductoTarifapevcProductos);
            relatedRequest.setProductosCambProductoTarifapevcTarifaInstance(tarifaCambProductoTarifapthisTarifa);
            productosSrv.cambProductoTarifaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTarifa());

        return response;
    }

    // Service: CambiarTarifa

    /*
     * Entry point for service CambiarTarifa
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambiarTarifa(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CambiarTarifaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tarifaSrv.cambiarTarifaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service CambiarTarifa
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambiarTarifaTxn(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CambiarTarifaRequest request) throws ModelException {
        request.setTarifaCambiarTarifapthisTarifaInstance(tarifaSrv.getByOIDWithHV(request.getTarifaCambiarTarifapthisTarifa()));
        request.setTarifaCambiarTarifapevcInstitucionMatNeoInstance(institucionMatNeoSrv.getByOIDWithHV(request.getTarifaCambiarTarifapevcInstitucionMatNeo()));
        return tarifaSrv.cambiarTarifaImplementation(request, true);
    }

    /*
     * Implements service CambiarTarifa
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambiarTarifaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CambiarTarifaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Tarifa tarifaCambiarTarifapthisTarifa = request.getTarifaCambiarTarifapthisTarifaInstance();
        InstitucionMatNeo tarifaCambiarTarifapevcInstitucionMatNeo = request.getTarifaCambiarTarifapevcInstitucionMatNeoInstance();

        request.checkArguments();
        Tarifa instance = tarifaCambiarTarifapthisTarifa;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarTarifaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.institucionmatneo.siu.CambiarTarifaRequest();
            relatedRequest.setInstitucionMatNeoCambiarTarifapthisInstitucionMatNeoInstance(tarifaCambiarTarifapevcInstitucionMatNeo);
            relatedRequest.setInstitucionMatNeoCambiarTarifapevcTarifaInstance(tarifaCambiarTarifapthisTarifa);
            institucionMatNeoSrv.cambiarTarifaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICTarifa());

        return response;
    }

    // Service: TSINCRONIZAR

    /*
     * Entry point for service TSINCRONIZAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tSINCRONIZAR(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.TSINCRONIZARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tarifaSrv.tSINCRONIZARTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TSINCRONIZAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tSINCRONIZARTxn(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.TSINCRONIZARRequest request) throws ModelException {
        request.setTarifaTSINCRONIZARpthisTarifaInstance(tarifaSrv.getByOIDWithHV(request.getTarifaTSINCRONIZARpthisTarifa()));
        request.setTarifaTSINCRONIZARptpevcPlanInstance(planesSrv.getByOIDWithHV(request.getTarifaTSINCRONIZARptpevcPlan()));
        request.setTarifaTSINCRONIZARptpevcProductoInstance(productosSrv.getByOIDWithHV(request.getTarifaTSINCRONIZARptpevcProducto()));
        return tarifaSrv.tSINCRONIZARImplementation(request);
    }

    /*
     * Implements service TSINCRONIZAR
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tSINCRONIZARImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.TSINCRONIZARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long tarifaTSINCRONIZARptpTipoContrato = request.getTarifaTSINCRONIZARptpTipoContrato();
        Planes tarifaTSINCRONIZARptpevcPlan = request.getTarifaTSINCRONIZARptpevcPlanInstance();
        Productos tarifaTSINCRONIZARptpevcProducto = request.getTarifaTSINCRONIZARptpevcProductoInstance();
        Tarifa tarifaTSINCRONIZARpthisTarifa = request.getTarifaTSINCRONIZARpthisTarifaInstance();

        request.checkArguments();
        Tarifa instance = tarifaTSINCRONIZARpthisTarifa;

        com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.SincronizarRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.SincronizarRequest();
        step1Request.setTarifasincronizarpthisTarifaInstance(tarifaTSINCRONIZARpthisTarifa);
        step1Request.setTarifasincronizarpTipoContrato(tarifaTSINCRONIZARptpTipoContrato);
        tarifaSrv.sincronizarImplementation(step1Request);

        boolean guard2 = !Utilities.isEqual(tarifaTSINCRONIZARptpevcPlan, tarifaTSINCRONIZARpthisTarifa.getPlanT());

        if (guard2) {
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CambPlanTarifaRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CambPlanTarifaRequest();
            step2Request.setTarifaCambPlanTarifapthisTarifaInstance(tarifaTSINCRONIZARpthisTarifa);
            step2Request.setTarifaCambPlanTarifapevcPlanesInstance(tarifaTSINCRONIZARptpevcPlan);
            tarifaSrv.cambPlanTarifaImplementation(step2Request, true);
        }

        boolean guard3 = !Utilities.isEqual(tarifaTSINCRONIZARptpevcProducto, tarifaTSINCRONIZARpthisTarifa.getProductoT());

        if (guard3) {
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CambProductoTarifaRequest step3Request = new com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CambProductoTarifaRequest();
            step3Request.setTarifaCambProductoTarifapthisTarifaInstance(tarifaTSINCRONIZARpthisTarifa);
            step3Request.setTarifaCambProductoTarifapevcProductosInstance(tarifaTSINCRONIZARptpevcProducto);
            tarifaSrv.cambProductoTarifaImplementation(step3Request, true);
        }

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICTarifa());

        return response;
    }

    // Service: TCREAR

    /*
     * Entry point for service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tCREAR(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tarifaSrv.tCREARTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tCREARTxn(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.TCREARRequest request) throws ModelException {
        request.setTarifaTCREARpProductoInstance(productosSrv.getByOIDWithHV(new ProductosOid().fromJsonString(request.getTarifaTCREARpProducto())));
        request.setTarifaTCREARpPlanInstance(planesSrv.getByOIDWithHV(new PlanesOid().fromJsonString(request.getTarifaTCREARpPlan())));
        return tarifaSrv.tCREARImplementation(request);
    }

    /*
     * Implements service TCREAR
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tCREARImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.TCREARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Planes tarifaTCREARpPlan = request.getTarifaTCREARpPlanInstance();
        Productos tarifaTCREARpProducto = request.getTarifaTCREARpProductoInstance();
        String tarifaTCREARpCodigo = request.getTarifaTCREARpCodigo();
        Long tarifaTCREARpTipoContrato = request.getTarifaTCREARpTipoContrato();

        request.checkArguments();
        Tarifa instance;

        com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.CrearRequest();
        step1Request.setTarifacrearpagrPlanTInstance(tarifaTCREARpPlan);
        step1Request.setTarifacrearpagrProductoTInstance(tarifaTCREARpProducto);
        step1Request.setTarifacrearpatrCodigo(tarifaTCREARpCodigo);
        step1Request.setTarifacrearpatrTipoContrato(tarifaTCREARpTipoContrato);
        instance = (Tarifa) tarifaSrv.crearImplementation(step1Request).getInstance();

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICTarifa());

        return response;
    }

    // Service: TELIMINAR

    /*
     * Entry point for service TELIMINAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tELIMINAR(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.TELIMINARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = tarifaSrv.tELIMINARTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TELIMINAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tELIMINARTxn(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.TELIMINARRequest request) throws ModelException, SystemException {
        request.setTarifaTELIMINARpthisTarifaInstance(tarifaSrv.getByOIDWithHV(request.getTarifaTELIMINARpthisTarifa()));
        return tarifaSrv.tELIMINARImplementation(request);
    }

    /*
     * Implements service TELIMINAR
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tELIMINARImplementation(
            com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.TELIMINARRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Tarifa tarifaTELIMINARpthisTarifa = request.getTarifaTELIMINARpthisTarifaInstance();

        request.checkArguments();
        Tarifa instance = tarifaTELIMINARpthisTarifa;

        for(DetalleTarifa step1Entity : repository.assocOperator002(tarifaTELIMINARpthisTarifa)) {
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.TELIMINARRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.TELIMINARRequest();
            step1Request.setDetalleTarifaTELIMINARpthisDetalleTarifaInstance(step1Entity);
            detalleTarifaSrv.tELIMINARImplementation(step1Request);
        }

        com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.EliminarRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.EliminarRequest();
        step2Request.setTarifaeliminarpthisTarifaInstance(tarifaTELIMINARpthisTarifa);
        tarifaSrv.eliminarImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICTarifa());

        return response;
    }


    private void suppInfo4ICTarifa(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TarifaConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ATTR_NAME_PRODUCTOSDESCRIPCION, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison1);

            SQLWhereComparison comparison2 = new SQLWhereComparison(select);
            comparison2.setOperator("LIKE");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(TarifaConstants.ROLE_NAME_PLANT + "." + PlanesConstants.ATTR_NAME_PLANESNOMBRE, Constants.Type.STRING.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison2);

            SQLWhereComparison comparison3 = new SQLWhereComparison(select);
            comparison3.setOperator("LIKE");
            comparison3.setFirstOperand(new SQLWhereOperandPathAttribute(TarifaConstants.ATTR_NAME_TARIFACODIGO, Constants.Type.STRING.getTypeName()));
            comparison3.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison3);

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
        select.getOrderByPart().addItem(Constants.normalizePath(TarifaConstants.CLASS_NAME, TarifaConstants.ATTR_FIELD_TARIFAID, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildTarifaRelatedInstance(relatedInstanceJsonOid);
        }
        if (TarifaConstants.ROLE_NAME_CONTRATOSC.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTarifaRelatedInstance4ContratoC(relatedInstanceJsonOid, remainingPath);
        }
        if (TarifaConstants.ROLE_NAME_DETALLESTARIFA.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTarifaRelatedInstance4DetalleTarifa(relatedInstanceJsonOid, remainingPath);
        }
        if (TarifaConstants.ROLE_NAME_INSTITUCIONMATNEO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTarifaRelatedInstance4InstitucionMatNeo(relatedInstanceJsonOid, remainingPath);
        }
        if (TarifaConstants.ROLE_NAME_PRODUCTOT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTarifaRelatedInstance4Productos(relatedInstanceJsonOid, remainingPath);
        }
        if (TarifaConstants.ROLE_NAME_PLANT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildTarifaRelatedInstance4Planes(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildTarifaRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Tarifa(mapper.readValue(relatedInstanceJsonOid, TarifaOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTarifaRelatedInstance4ContratoC(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ContratoC(mapper.readValue(relatedInstanceJsonOid, ContratoCOid.class)): ContratoC.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTarifaRelatedInstance4DetalleTarifa(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new DetalleTarifa(mapper.readValue(relatedInstanceJsonOid, DetalleTarifaOid.class)): DetalleTarifa.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTarifaRelatedInstance4InstitucionMatNeo(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new InstitucionMatNeo(mapper.readValue(relatedInstanceJsonOid, InstitucionMatNeoOid.class)): InstitucionMatNeo.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTarifaRelatedInstance4Productos(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Productos(mapper.readValue(relatedInstanceJsonOid, ProductosOid.class)): Productos.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildTarifaRelatedInstance4Planes(String relatedInstanceJsonOid, String remainingPath) {
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
            TarifaOid oid = new TarifaOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(TarifaConstants.ATTR_FIELD_TARIFAID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getTarifaId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(TarifaService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
