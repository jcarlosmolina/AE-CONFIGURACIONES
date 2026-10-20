package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.FechaIniEPSConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.persistence.FechaIniEPS;
import com.keralty.aeconfiguraciones.persistence.oid.FechaIniEPSOid;
import com.keralty.aeconfiguraciones.repository.FechaIniEPSJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class FechaIniEPSService extends AbstractService {

    FechaIniEPSJpaRepository repository;

    FechaIniEPSService fechaIniEPSSrv;

    @Inject
    public FechaIniEPSService(
            FechaIniEPSService fechaIniEPSSrv,
            FechaIniEPSJpaRepository repository) {
        this.fechaIniEPSSrv = fechaIniEPSSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, FechaIniEPSConstants.CLASS_NAME);
    }

    private List<FechaIniEPS> solveFechaIniEPSTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, FechaIniEPSConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveFechaIniEPSTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, FechaIniEPSConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class FechaIniEPS
     *
     * @return Population of class FechaIniEPS
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<FechaIniEPS> get() {
        List<FechaIniEPS> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    /*
     * Returns the population of class FechaIniEPS that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class FechaIniEPS matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<FechaIniEPS> get(QueryRequest queryRequest) {
        return solveFechaIniEPSTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveFechaIniEPSTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveFechaIniEPSTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel();
        }
    }

    /*
     * Returns an instance of FechaIniEPS by ID
     *
     * @param fechaIniEPSId Identification field fechaIniEPSId
     *
     * @return Instance of FechaIniEPS
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public FechaIniEPS getById(Long fechaIniEPSId) {
        return getByOID(new FechaIniEPSOid(fechaIniEPSId));
    }
    
    /*
     * Returns an instance of FechaIniEPS by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of FechaIniEPS
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public FechaIniEPS getByOID(FechaIniEPSOid oid) {
        Optional<FechaIniEPS> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of FechaIniEPS by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of FechaIniEPS
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public FechaIniEPS getByOIDWithHV(FechaIniEPSOid oid) {
        Optional<FechaIniEPS> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<FechaIniEPS> getInstanceByOid(FechaIniEPSOid oid) {
        return oid != null && oid.getFechaIniEPSId() != null
               ? repository.findById(oid.getFechaIniEPSId())
               : Optional.empty();
    }

    /*
     * Returns an instance of FechaIniEPS by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of FechaIniEPS
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public FechaIniEPS getByJsonOid(String jsonOid) {
        FechaIniEPSOid oid = new FechaIniEPSOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns a set of displayset items for an instance of FechaIniEPS
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of FechaIniEPS
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getFechaIniEPSDynamic(FechaIniEPSOid oid, String displaySetItems) {
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
    public String getSuppInfo(FechaIniEPSOid oid, String dsName) {
        FechaIniEPS instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(FechaIniEPS instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (FechaIniEPSConstants.FILTER_NAME_FFECHAINIEPS.equalsIgnoreCase(filterRequest.getName()))
            fFechaIniEPS(filterRequest, sql);
    }


    private void fFechaIniEPS(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Long vfTramite = filterRequest.hasFilterVariable(FechaIniEPSConstants.VAR_NAME_FFECHAINIEPS_VFTRAMITE) ? (Long) filterRequest.getFilterVariable(FechaIniEPSConstants.VAR_NAME_FFECHAINIEPS_VFTRAMITE, FilterVariableRequest.NAT) : null;
        Long vfTipoContrato = filterRequest.hasFilterVariable(FechaIniEPSConstants.VAR_NAME_FFECHAINIEPS_VFTIPOCONTRATO) ? (Long) filterRequest.getFilterVariable(FechaIniEPSConstants.VAR_NAME_FFECHAINIEPS_VFTIPOCONTRATO, FilterVariableRequest.NAT) : null;
        Date vfFecha = filterRequest.hasFilterVariable(FechaIniEPSConstants.VAR_NAME_FFECHAINIEPS_VFFECHA) ? (Date) filterRequest.getFilterVariable(FechaIniEPSConstants.VAR_NAME_FFECHAINIEPS_VFFECHA, FilterVariableRequest.DATE) : null;

        // Tramite = vfTramite
        if (vfTramite != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand112 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTRAMITE, Constants.Type.NAT.getTypeName());
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(vfTramite, Constants.Type.NAT.getTypeName()));
            conjunction.addOperand(comparison1);

        }        

        // TipoContrato = vfTipoContrato
        if (vfTipoContrato != null) {
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTIPOCONTRATO, Constants.Type.NAT.getTypeName());
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(vfTipoContrato, Constants.Type.NAT.getTypeName()));
            conjunction.addOperand(comparison2);

        }        

        // Desde <= vfFecha
        if (vfFecha != null) {
            // '<=' Comparison
            SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
            comparison3.setOperator("<=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSDESDE, Constants.Type.DATE.getTypeName());
            comparison3.setFirstOperand(operand114);
            
            // Operand 2
            comparison3.setSecondOperand(new SQLWhereOperandLiteral(vfFecha, Constants.Type.DATE.getTypeName()));
            conjunction.addOperand(comparison3);

        }        

        // Hasta >= vfFecha
        if (vfFecha != null) {
            // '>=' Comparison
            SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
            comparison4.setOperator(">=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSHASTA, Constants.Type.DATE.getTypeName());
            comparison4.setFirstOperand(operand115);
            
            // Operand 2
            comparison4.setSecondOperand(new SQLWhereOperandLiteral(vfFecha, Constants.Type.DATE.getTypeName()));
            conjunction.addOperand(comparison4);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUFechaIniEPS
     *
     * @param queryRequest Query request
     * @return List of viewmodels for FechaIniEPS using displayset DSFechaIniEPS that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.fechainieps.ds.DSFechaIniEPS> piuPIUFechaIniEPS(QueryRequest queryRequest) {
        return solveFechaIniEPSTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.fechainieps.ds.DSFechaIniEPS::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUFechaIniEPS
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUFechaIniEPS
     */
    public int getCount4PIUFechaIniEPS(QueryRequest queryRequest) {
        return solveFechaIniEPSTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = fechaIniEPSSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.CrearRequest request) throws ModelException {
        return fechaIniEPSSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long fechaIniEPScrearpatrTramite = request.getFechaIniEPScrearpatrTramite();
        Long fechaIniEPScrearpatrTipoContrato = request.getFechaIniEPScrearpatrTipoContrato();
        Date fechaIniEPScrearpatrDesde = request.getFechaIniEPScrearpatrDesde();
        Date fechaIniEPScrearpatrHasta = request.getFechaIniEPScrearpatrHasta();
        Date fechaIniEPScrearpatrFechaInicioAplicar = request.getFechaIniEPScrearpatrFechaInicioAplicar();

        request.checkArguments();

        crearPreconditions(request);
        FechaIniEPS instance = new FechaIniEPS();
        instance.setFechaIniEPSId(Long.valueOf(0));
        instance.setFechaIniEPSTramite(fechaIniEPScrearpatrTramite);
        instance.setFechaIniEPSTipoContrato(fechaIniEPScrearpatrTipoContrato);
        instance.setFechaIniEPSDesde(fechaIniEPScrearpatrDesde);
        instance.setFechaIniEPSHasta(fechaIniEPScrearpatrHasta);
        instance.setFechaIniEPSFechaInicioAplicar(fechaIniEPScrearpatrFechaInicioAplicar);

        response.setInstance(repository.save(instance));

        return response;
    }

    private void crearPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.CrearRequest request) 
            throws ModelException {
        checkClas1655287709696534Pre1(request);
    }

    private void checkClas1655287709696534Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.CrearRequest request) 
            throws ModelException {
        // patrDesde <= patrHasta

        Date fechaIniEPScrearpatrDesde = request.getFechaIniEPScrearpatrDesde();
        Date fechaIniEPScrearpatrHasta = request.getFechaIniEPScrearpatrHasta();
        boolean preconditionHolds = (STDFunctions.dateBeforeFun(fechaIniEPScrearpatrDesde,fechaIniEPScrearpatrHasta) || STDFunctions.dateEqualsFun(fechaIniEPScrearpatrDesde, fechaIniEPScrearpatrHasta));
        if (!preconditionHolds) throw new PreconditionException("La fecha Desde no puede ser mayor que la fecha Hasta");
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
            com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = fechaIniEPSSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.EliminarRequest request) throws ModelException {
        request.setFechaIniEPSeliminarpthisFechaIniEPSInstance(fechaIniEPSSrv.getByOIDWithHV(request.getFechaIniEPSeliminarpthisFechaIniEPS()));
        return fechaIniEPSSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        FechaIniEPS fechaIniEPSeliminarpthisFechaIniEPS = request.getFechaIniEPSeliminarpthisFechaIniEPSInstance();

        request.checkArguments();
        FechaIniEPS instance = fechaIniEPSeliminarpthisFechaIniEPS;


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
            com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = fechaIniEPSSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.ModificarRequest request) throws ModelException {
        request.setFechaIniEPSmodificarpthisFechaIniEPSInstance(fechaIniEPSSrv.getByOIDWithHV(request.getFechaIniEPSmodificarpthisFechaIniEPS()));
        return fechaIniEPSSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        FechaIniEPS fechaIniEPSmodificarpthisFechaIniEPS = request.getFechaIniEPSmodificarpthisFechaIniEPSInstance();
        Date fechaIniEPSmodificarpDesde = request.getFechaIniEPSmodificarpDesde();
        Date fechaIniEPSmodificarpHasta = request.getFechaIniEPSmodificarpHasta();
        Date fechaIniEPSmodificarpFechaInicioAplicar = request.getFechaIniEPSmodificarpFechaInicioAplicar();

        request.checkArguments();

        modificarPreconditions(request);
        FechaIniEPS instance = fechaIniEPSmodificarpthisFechaIniEPS;
        instance.setFechaIniEPSDesde(fechaIniEPSmodificarpDesde);
        instance.setFechaIniEPSHasta(fechaIniEPSmodificarpHasta);
        instance.setFechaIniEPSFechaInicioAplicar(fechaIniEPSmodificarpFechaInicioAplicar);


        response.setInstance(repository.save(instance));

        return response;
    }

    private void modificarPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.ModificarRequest request) 
            throws ModelException {
        checkClas1655287709696534Pre2(request);
    }

    private void checkClas1655287709696534Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.ModificarRequest request) 
            throws ModelException {
        // pDesde <= pHasta

        Date fechaIniEPSmodificarpDesde = request.getFechaIniEPSmodificarpDesde();
        Date fechaIniEPSmodificarpHasta = request.getFechaIniEPSmodificarpHasta();
        boolean preconditionHolds = (STDFunctions.dateBeforeFun(fechaIniEPSmodificarpDesde,fechaIniEPSmodificarpHasta) || STDFunctions.dateEqualsFun(fechaIniEPSmodificarpDesde, fechaIniEPSmodificarpHasta));
        if (!preconditionHolds) throw new PreconditionException("La fecha Desde no puede ser mayor que la fecha Hasta");
    }

    @Override
    protected void applyOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        applyOCOCFechaIniEPS(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.ATTR_FIELD_FECHAINIEPSID, false), true);
    }


    private void applyOCOCFechaIniEPS(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_FechaIniEPS".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTRAMITE, false), true);
            select.getOrderByPart().addItem(Constants.normalizePath(FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSTIPOCONTRATO, false), true);
            select.getOrderByPart().addItem(Constants.normalizePath(FechaIniEPSConstants.CLASS_NAME, FechaIniEPSConstants.ATTR_NAME_FECHAINIEPSDESDE, false), true);
        }
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            FechaIniEPSOid oid = new FechaIniEPSOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(FechaIniEPSConstants.ATTR_FIELD_FECHAINIEPSID, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getFechaIniEPSId(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(FechaIniEPSService.class).error(e.getMessage());
        }
    }

}
