package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.DetalleTarifaConstants;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.global.ProductosConstants;
import com.keralty.aeconfiguraciones.global.TarifaConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.DetalleTarifa;
import com.keralty.aeconfiguraciones.persistence.oid.DetalleTarifaOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.TarifaOid;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Tarifa;
import com.keralty.aeconfiguraciones.repository.DetalleTarifaJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathInstance;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class DetalleTarifaService extends AbstractService {

    DetalleTarifaJpaRepository repository;

    DetalleTarifaService detalleTarifaSrv;

    TarifaService tarifaSrv;

    ProductosService productosSrv;

    PlanesService planesSrv;

    @Inject
    public DetalleTarifaService(
            DetalleTarifaService detalleTarifaSrv,
            TarifaService tarifaSrv,
            ProductosService productosSrv,
            PlanesService planesSrv,
            DetalleTarifaJpaRepository repository) {
        this.detalleTarifaSrv = detalleTarifaSrv;
        this.tarifaSrv = tarifaSrv;
        this.productosSrv = productosSrv;
        this.planesSrv = planesSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, DetalleTarifaConstants.CLASS_NAME);
    }

    private List<DetalleTarifa> solveDetalleTarifaTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, DetalleTarifaConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveDetalleTarifaTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, DetalleTarifaConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class DetalleTarifa
     *
     * @return Population of class DetalleTarifa
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DetalleTarifa> get() {
        List<DetalleTarifa> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class DetalleTarifa that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class DetalleTarifa matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DetalleTarifa> get(QueryRequest queryRequest) {
        return solveDetalleTarifaTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveDetalleTarifaTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveDetalleTarifaTypedCountQuery(queryRequest) : 0);
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
        return new PopulationResponseViewModel(solveDetalleTarifaTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of DetalleTarifa by ID
     *
     * @param detalleTarifaidDetalleTarifa Identification field detalleTarifaidDetalleTarifa
     *
     * @return Instance of DetalleTarifa
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public DetalleTarifa getById(Long detalleTarifaidDetalleTarifa) {
        return getByOID(new DetalleTarifaOid(detalleTarifaidDetalleTarifa));
    }
    
    /*
     * Returns an instance of DetalleTarifa by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of DetalleTarifa
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public DetalleTarifa getByOID(DetalleTarifaOid oid) {
        Optional<DetalleTarifa> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of DetalleTarifa by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of DetalleTarifa
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public DetalleTarifa getByOIDWithHV(DetalleTarifaOid oid) {
        Optional<DetalleTarifa> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<DetalleTarifa> getInstanceByOid(DetalleTarifaOid oid) {
        return oid != null && oid.getDetalleTarifaidDetalleTarifa() != null
               ? repository.findById(oid.getDetalleTarifaidDetalleTarifa())
               : Optional.empty();
    }

    /*
     * Returns an instance of DetalleTarifa by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of DetalleTarifa
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public DetalleTarifa getByJsonOid(String jsonOid) {
        DetalleTarifaOid oid = new DetalleTarifaOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of DetalleTarifa related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of DetalleTarifa
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DetalleTarifa> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveDetalleTarifaTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of DetalleTarifa related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of DetalleTarifa
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<DetalleTarifa> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of DetalleTarifa
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of DetalleTarifa
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getDetalleTarifaDynamic(DetalleTarifaOid oid, String displaySetItems) {
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
    public String getSuppInfo(DetalleTarifaOid oid, String dsName) {
        DetalleTarifa instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(DetalleTarifa instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (DetalleTarifaConstants.FILTER_NAME_IFTARIFASEGUNEDAD.equalsIgnoreCase(filterRequest.getName()))
            iFTarifaSegunEdad(filterRequest, sql);
    }


    private void iFTarifaSegunEdad(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Productos voProducto = filterRequest.hasFilterVariable(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VOPRODUCTO) ? productosSrv.getByOID(new ProductosOid().fromJsonString(toJson(filterRequest.getFilterVariable(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VOPRODUCTO)))) : null;
        Planes voPlan = filterRequest.hasFilterVariable(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VOPLAN) ? planesSrv.getByOID(new PlanesOid().fromJsonString(toJson(filterRequest.getFilterVariable(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VOPLAN)))) : null;
        Long vEdad = filterRequest.hasFilterVariable(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VEDAD) ? (Long) filterRequest.getFilterVariable(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VEDAD, FilterVariableRequest.INT) : null;
        Long vTipoContrato = filterRequest.hasFilterVariable(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VTIPOCONTRATO) ? (Long) filterRequest.getFilterVariable(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VTIPOCONTRATO, FilterVariableRequest.NAT) : null;
        Long vAnyoFechaContratacion = filterRequest.hasFilterVariable(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VANYOFECHACONTRATACION) ? (Long) filterRequest.getFilterVariable(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VANYOFECHACONTRATACION, FilterVariableRequest.INT) : null;

        // Tarifa.ProductoT = voProducto
        if (voProducto != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(DetalleTarifaConstants.ROLE_NAME_TARIFA + "." + TarifaConstants.ROLE_NAME_PRODUCTOT, ProductosConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VOPRODUCTO, ProductosConstants.CLASS_NAME, ProductosConstants.CLASS_NAME, voProducto.getOid(), DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VOPRODUCTO);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // Tarifa.PlanT = voPlan
        if (voPlan != null) {
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(DetalleTarifaConstants.ROLE_NAME_TARIFA + "." + TarifaConstants.ROLE_NAME_PLANT, PlanesConstants.CLASS_NAME);
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            SQLWhereOperandOVParameter operand224 = new SQLWhereOperandOVParameter(DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VOPLAN, PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, voPlan.getOid(), DetalleTarifaConstants.VAR_NAME_IFTARIFASEGUNEDAD_VOPLAN);
            comparison2.setSecondOperand(operand224);
            conjunction.addOperand(comparison2);

        }        

        // vEdad >= LimInferiorEdad
        if (vEdad != null) {
            // '>=' Comparison
            SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
            comparison3.setOperator(">=");
            
            // Operand 1
            comparison3.setFirstOperand(new SQLWhereOperandLiteral(vEdad, Constants.Type.INT.getTypeName()));
            
            // Operand 2
            SQLWhereOperandPathAttribute operand225 = new SQLWhereOperandPathAttribute(DetalleTarifaConstants.ATTR_NAME_DETALLETARIFALIMINFERIOREDAD, Constants.Type.INT.getTypeName());
            comparison3.setSecondOperand(operand225);
            conjunction.addOperand(comparison3);

        }        

        // vEdad <= LimSuperiorEdad
        if (vEdad != null) {
            // '<=' Comparison
            SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
            comparison4.setOperator("<=");
            
            // Operand 1
            comparison4.setFirstOperand(new SQLWhereOperandLiteral(vEdad, Constants.Type.INT.getTypeName()));
            
            // Operand 2
            SQLWhereOperandPathAttribute operand226 = new SQLWhereOperandPathAttribute(DetalleTarifaConstants.ATTR_NAME_DETALLETARIFALIMSUPERIOREDAD, Constants.Type.INT.getTypeName());
            comparison4.setSecondOperand(operand226);
            conjunction.addOperand(comparison4);

        }        

        // Tarifa.TipoContrato = vTipoContrato
        if (vTipoContrato != null) {
            // '=' Comparison
            SQLWhereComparison comparison5 = new SQLWhereComparison(sql);
            comparison5.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand116 = new SQLWhereOperandPathAttribute(DetalleTarifaConstants.ROLE_NAME_TARIFA + "." + TarifaConstants.ATTR_NAME_TARIFATIPOCONTRATO, Constants.Type.NAT.getTypeName());
            comparison5.setFirstOperand(operand116);
            
            // Operand 2
            comparison5.setSecondOperand(new SQLWhereOperandLiteral(vTipoContrato, Constants.Type.NAT.getTypeName()));
            conjunction.addOperand(comparison5);

        }        

        // Anyo = vAnyoFechaContratacion
        if (vAnyoFechaContratacion != null) {
            // '=' Comparison
            SQLWhereComparison comparison6 = new SQLWhereComparison(sql);
            comparison6.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand117 = new SQLWhereOperandPathAttribute(DetalleTarifaConstants.ATTR_NAME_DETALLETARIFAANYO, Constants.Type.INT.getTypeName());
            comparison6.setFirstOperand(operand117);
            
            // Operand 2
            comparison6.setSecondOperand(new SQLWhereOperandLiteral(vAnyoFechaContratacion, Constants.Type.INT.getTypeName()));
            conjunction.addOperand(comparison6);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUDetallesTarifa
     *
     * @param queryRequest Query request
     * @return List of viewmodels for DetalleTarifa using displayset DSDetallesTarifa that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.detalletarifa.ds.DSDetallesTarifa> piuPIUDetallesTarifa(QueryRequest queryRequest) {
        return solveDetalleTarifaTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.detalletarifa.ds.DSDetallesTarifa::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUDetallesTarifa
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUDetallesTarifa
     */
    public int getCount4PIUDetallesTarifa(QueryRequest queryRequest) {
        return solveDetalleTarifaTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = detalleTarifaSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.CrearRequest request) throws ModelException {
        request.setDetalleTarifacrearpagrTarifaInstance(tarifaSrv.getByOIDWithHV(request.getDetalleTarifacrearpagrTarifa()));
        return detalleTarifaSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Tarifa detalleTarifacrearpagrTarifa = request.getDetalleTarifacrearpagrTarifaInstance();
        Long detalleTarifacrearpatrAnyo = request.getDetalleTarifacrearpatrAnyo();
        Long detalleTarifacrearpatrLimInferiorEdad = request.getDetalleTarifacrearpatrLimInferiorEdad();
        Long detalleTarifacrearpatrLimSuperiorEdad = request.getDetalleTarifacrearpatrLimSuperiorEdad();
        Double detalleTarifacrearpatrValor = request.getDetalleTarifacrearpatrValor();

        request.checkArguments();
        DetalleTarifa instance = new DetalleTarifa();
        instance.add2Tarifa(detalleTarifacrearpagrTarifa);

        instance.setDetalleTarifaidDetalleTarifa(Long.valueOf(0));
        instance.setDetalleTarifaAnyo(detalleTarifacrearpatrAnyo);
        instance.setDetalleTarifaLimInferiorEdad(detalleTarifacrearpatrLimInferiorEdad);
        instance.setDetalleTarifaLimSuperiorEdad(detalleTarifacrearpatrLimSuperiorEdad);
        instance.setDetalleTarifaValor(detalleTarifacrearpatrValor);

        response.setInstance(repository.save(instance));

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
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = detalleTarifaSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.EliminarRequest request) throws ModelException {
        request.setDetalleTarifaeliminarpthisDetalleTarifaInstance(detalleTarifaSrv.getByOIDWithHV(request.getDetalleTarifaeliminarpthisDetalleTarifa()));
        return detalleTarifaSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DetalleTarifa detalleTarifaeliminarpthisDetalleTarifa = request.getDetalleTarifaeliminarpthisDetalleTarifaInstance();

        request.checkArguments();
        DetalleTarifa instance = detalleTarifaeliminarpthisDetalleTarifa;


        repository.delete(instance);
        
        response.setInstance(instance);

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
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = detalleTarifaSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.ModificarRequest request) throws ModelException {
        request.setDetalleTarifamodificarpthisDetalleTarifaInstance(detalleTarifaSrv.getByOIDWithHV(request.getDetalleTarifamodificarpthisDetalleTarifa()));
        return detalleTarifaSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DetalleTarifa detalleTarifamodificarpthisDetalleTarifa = request.getDetalleTarifamodificarpthisDetalleTarifaInstance();
        Long detalleTarifamodificarpAnyo = request.getDetalleTarifamodificarpAnyo();
        Long detalleTarifamodificarpLimInferiorEdad = request.getDetalleTarifamodificarpLimInferiorEdad();
        Long detalleTarifamodificarpLimSuperiorEdad = request.getDetalleTarifamodificarpLimSuperiorEdad();
        Double detalleTarifamodificarpValor = request.getDetalleTarifamodificarpValor();

        request.checkArguments();
        DetalleTarifa instance = detalleTarifamodificarpthisDetalleTarifa;
        instance.setDetalleTarifaAnyo(detalleTarifamodificarpAnyo);
        instance.setDetalleTarifaLimInferiorEdad(detalleTarifamodificarpLimInferiorEdad);
        instance.setDetalleTarifaLimSuperiorEdad(detalleTarifamodificarpLimSuperiorEdad);
        instance.setDetalleTarifaValor(detalleTarifamodificarpValor);


        response.setInstance(repository.save(instance));

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
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.TCREARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = detalleTarifaSrv.tCREARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.TCREARRequest request) throws ModelException {
        request.setDetalleTarifaTCREARpTarifaInstance(tarifaSrv.getByOIDWithHV(request.getDetalleTarifaTCREARpTarifa()));
        request.setDetalleTarifaTCREARpProductoInstance(productosSrv.getByOIDWithHV(request.getDetalleTarifaTCREARpProducto()));
        request.setDetalleTarifaTCREARpPlanInstance(planesSrv.getByOIDWithHV(request.getDetalleTarifaTCREARpPlan()));
        return detalleTarifaSrv.tCREARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.TCREARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Tarifa detalleTarifaTCREARpTarifa = request.getDetalleTarifaTCREARpTarifaInstance();
        Long detalleTarifaTCREARpAnyo = request.getDetalleTarifaTCREARpAnyo();
        Long detalleTarifaTCREARpLimInferiorEdad = request.getDetalleTarifaTCREARpLimInferiorEdad();
        Long detalleTarifaTCREARpLimSuperiorEdad = request.getDetalleTarifaTCREARpLimSuperiorEdad();
        Double detalleTarifaTCREARpValor = request.getDetalleTarifaTCREARpValor();

        request.checkArguments();
        DetalleTarifa instance;

        com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.CrearRequest();
        step1Request.setDetalleTarifacrearpagrTarifaInstance(detalleTarifaTCREARpTarifa);
        step1Request.setDetalleTarifacrearpatrAnyo(detalleTarifaTCREARpAnyo);
        step1Request.setDetalleTarifacrearpatrLimInferiorEdad(detalleTarifaTCREARpLimInferiorEdad);
        step1Request.setDetalleTarifacrearpatrLimSuperiorEdad(detalleTarifaTCREARpLimSuperiorEdad);
        step1Request.setDetalleTarifacrearpatrValor(detalleTarifaTCREARpValor);
        instance = (DetalleTarifa) detalleTarifaSrv.crearImplementation(step1Request).getInstance();

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    // Service: TMODIFICAR

    /*
     * Entry point for service TMODIFICAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tMODIFICAR(
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.TMODIFICARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = detalleTarifaSrv.tMODIFICARTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TMODIFICAR
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tMODIFICARTxn(
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.TMODIFICARRequest request) throws ModelException {
        request.setDetalleTarifaTMODIFICARpthisDetalleTarifaInstance(detalleTarifaSrv.getByOIDWithHV(request.getDetalleTarifaTMODIFICARpthisDetalleTarifa()));
        request.setDetalleTarifaTMODIFICARpProductoInstance(productosSrv.getByOIDWithHV(request.getDetalleTarifaTMODIFICARpProducto()));
        request.setDetalleTarifaTMODIFICARpPlanInstance(planesSrv.getByOIDWithHV(request.getDetalleTarifaTMODIFICARpPlan()));
        return detalleTarifaSrv.tMODIFICARImplementation(request);
    }

    /*
     * Implements service TMODIFICAR
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tMODIFICARImplementation(
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.TMODIFICARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long detalleTarifaTMODIFICARpAnyo = request.getDetalleTarifaTMODIFICARpAnyo();
        Long detalleTarifaTMODIFICARpLimInferiorEdad = request.getDetalleTarifaTMODIFICARpLimInferiorEdad();
        Long detalleTarifaTMODIFICARpLimSuperiorEdad = request.getDetalleTarifaTMODIFICARpLimSuperiorEdad();
        Double detalleTarifaTMODIFICARpValor = request.getDetalleTarifaTMODIFICARpValor();
        DetalleTarifa detalleTarifaTMODIFICARpthisDetalleTarifa = request.getDetalleTarifaTMODIFICARpthisDetalleTarifaInstance();

        request.checkArguments();
        DetalleTarifa instance = detalleTarifaTMODIFICARpthisDetalleTarifa;

        com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.ModificarRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.ModificarRequest();
        step1Request.setDetalleTarifamodificarpthisDetalleTarifaInstance(detalleTarifaTMODIFICARpthisDetalleTarifa);
        step1Request.setDetalleTarifamodificarpAnyo(detalleTarifaTMODIFICARpAnyo);
        step1Request.setDetalleTarifamodificarpLimInferiorEdad(detalleTarifaTMODIFICARpLimInferiorEdad);
        step1Request.setDetalleTarifamodificarpLimSuperiorEdad(detalleTarifaTMODIFICARpLimSuperiorEdad);
        step1Request.setDetalleTarifamodificarpValor(detalleTarifaTMODIFICARpValor);
        detalleTarifaSrv.modificarImplementation(step1Request);

        response.setInstance(getByOID(instance.getOid()));

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
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.TELIMINARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = detalleTarifaSrv.tELIMINARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.TELIMINARRequest request) throws ModelException {
        request.setDetalleTarifaTELIMINARpthisDetalleTarifaInstance(detalleTarifaSrv.getByOIDWithHV(request.getDetalleTarifaTELIMINARpthisDetalleTarifa()));
        return detalleTarifaSrv.tELIMINARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.TELIMINARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        DetalleTarifa detalleTarifaTELIMINARpthisDetalleTarifa = request.getDetalleTarifaTELIMINARpthisDetalleTarifaInstance();

        request.checkArguments();
        DetalleTarifa instance = detalleTarifaTELIMINARpthisDetalleTarifa;

        com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.EliminarRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.EliminarRequest();
        step1Request.setDetalleTarifaeliminarpthisDetalleTarifaInstance(detalleTarifaTELIMINARpthisDetalleTarifa);
        detalleTarifaSrv.eliminarImplementation(step1Request);

        response.setInstance(getByOID(instance.getOid()));

        return response;
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCDetallesTarifa(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(DetalleTarifaConstants.CLASS_NAME, DetalleTarifaConstants.ATTR_FIELD_DETALLETARIFAIDDETALLETARIFA, false), true);
    }


    private void applyOCOCDetallesTarifa(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_DetallesTarifa".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(DetalleTarifaConstants.CLASS_NAME, DetalleTarifaConstants.ATTR_NAME_DETALLETARIFAANYO, false), false);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildDetalleTarifaRelatedInstance(relatedInstanceJsonOid);
        }
        if (DetalleTarifaConstants.ROLE_NAME_TARIFA.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDetalleTarifaRelatedInstance4Tarifa(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildDetalleTarifaRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new DetalleTarifa(mapper.readValue(relatedInstanceJsonOid, DetalleTarifaOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDetalleTarifaRelatedInstance4Tarifa(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Tarifa(mapper.readValue(relatedInstanceJsonOid, TarifaOid.class)): Tarifa.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            DetalleTarifaOid oid = new DetalleTarifaOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(DetalleTarifaConstants.ATTR_FIELD_DETALLETARIFAIDDETALLETARIFA, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getDetalleTarifaidDetalleTarifa(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(DetalleTarifaService.class).error(e.getMessage());
        }
    }

}
