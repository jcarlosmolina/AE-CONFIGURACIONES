package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.CausalConstants;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.GrupoAsignacionNovedadConstants;
import com.keralty.aeconfiguraciones.global.NovedadConstants;
import com.keralty.aeconfiguraciones.global.SolicitudNovedadConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Causal;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.CausalOid;
import com.keralty.aeconfiguraciones.persistence.oid.ConfiguracionANSOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudNovedadOid;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import com.keralty.aeconfiguraciones.repository.CausalJpaRepository;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class CausalService extends AbstractService {

    CausalJpaRepository repository;

    CausalService causalSrv;

    NovedadService novedadSrv;

    SolicitudNovedadService solicitudNovedadSrv;

    GrupoAsignacionNovedadService grupoAsignacionNovedadSrv;

    ConfiguracionANSService configuracionANSSrv;

    @Inject
    public CausalService(
            CausalService causalSrv,
            NovedadService novedadSrv,
            SolicitudNovedadService solicitudNovedadSrv,
            GrupoAsignacionNovedadService grupoAsignacionNovedadSrv,
            ConfiguracionANSService configuracionANSSrv,
            CausalJpaRepository repository) {
        this.causalSrv = causalSrv;
        this.novedadSrv = novedadSrv;
        this.solicitudNovedadSrv = solicitudNovedadSrv;
        this.grupoAsignacionNovedadSrv = grupoAsignacionNovedadSrv;
        this.configuracionANSSrv = configuracionANSSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, CausalConstants.CLASS_NAME);
    }

    private List<Causal> solveCausalTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, CausalConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveCausalTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, CausalConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Causal
     *
     * @return Population of class Causal
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Causal> get() {
        List<Causal> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class Causal
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class Causal
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<Causal> instances = get(queryRequest);
        if(CausalConstants.DSDSCAUSAL.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSCausal())).collect(Collectors.toList());
        }
        if(CausalConstants.DSICCAUSAL.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICCausal())).collect(Collectors.toList());
        }
        if(CausalConstants.DSDSCAUSALNULL.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSCausalNull())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class Causal that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Causal matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Causal> get(QueryRequest queryRequest) {
        return solveCausalTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveCausalTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveCausalTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveCausalTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveCausalTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Causal by ID
     *
     * @param causalidCausal Identification field causalidCausal
     *
     * @return Instance of Causal
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Causal getById(Long causalidCausal) {
        return getByOID(new CausalOid(causalidCausal));
    }
    
    /*
     * Returns an instance of Causal by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Causal
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Causal getByOID(CausalOid oid) {
        Optional<Causal> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Causal by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Causal
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Causal getByOIDWithHV(CausalOid oid) {
        Optional<Causal> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Causal> getInstanceByOid(CausalOid oid) {
        return oid != null && oid.getCausalidCausal() != null
               ? repository.findById(oid.getCausalidCausal())
               : Optional.empty();
    }

    /*
     * Returns an instance of Causal by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Causal
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Causal getByJsonOid(String jsonOid) {
        CausalOid oid = new CausalOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Causal related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Causal
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Causal> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveCausalTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Causal related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Causal
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Causal> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Causal
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Causal
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getCausalDynamic(CausalOid oid, String displaySetItems) {
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
    public String getSuppInfo(CausalOid oid, String dsName) {
        Causal instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Causal instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (CausalConstants.DSICCAUSAL.equals(dsName))
            return instance.buildICCausal();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(Causal instance, String dsName) {
        if (CausalConstants.DSDSCAUSAL.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSCausal());
        if (CausalConstants.DSICCAUSAL.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICCausal());
        if (CausalConstants.DSDSCAUSALNULL.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSCausalNull());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyNavigationalFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if ("SolicitudNovedad_TCREAR_pt_p_agrCausal_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1581421428736234FiltNav3(filterRequest, sql);
        if ("SolicitudNovedad_TCREAREXCEPCION_pt_p_agrCausal_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1581421428736234FiltNav14(filterRequest, sql);
        if ("GrupoAsignacionNovedad_TCREAR_pt_p_agrCausal_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1586852003840652FiltNav2(filterRequest, sql);
        if ("ConfiguracionANS_TCREAR_pt_p_agrCausal_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1586859868160432FiltNav2(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_Causal".equals(filterRequest.getName()))
            suppInfo4ICCausal(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void clas1581421428736234FiltNav3(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Novedad solicitudNovedadTCREARptpagrNovedad = filterRequest.hasFilterVariable(SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTPAGRNOVEDAD) ? novedadSrv.getByOID(new NovedadOid().fromJsonString(toJson(filterRequest.getFilterVariable(SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTPAGRNOVEDAD)))) : null;

        // Novedad = ptpagrNovedad
        if (solicitudNovedadTCREARptpagrNovedad != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(CausalConstants.ROLE_NAME_NOVEDAD, NovedadConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTPAGRNOVEDAD, NovedadConstants.CLASS_NAME, NovedadConstants.CLASS_NAME, solicitudNovedadTCREARptpagrNovedad.getOid(), SolicitudNovedadConstants.ARG_NAME_TCREAR_SOLICITUDNOVEDADTCREARPTPAGRNOVEDAD);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1581421428736234FiltNav14(FilterRequest filterRequest, SQLSelect sql) {
        clas1581421428736234FiltNav3(filterRequest, sql);
    }

    private void clas1586852003840652FiltNav2(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Novedad grupoAsignacionNovedadTCREARptNovedad = filterRequest.hasFilterVariable(GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTNOVEDAD) ? novedadSrv.getByOID(new NovedadOid().fromJsonString(toJson(filterRequest.getFilterVariable(GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTNOVEDAD)))) : null;

        // Novedad = ptNovedad
        if (grupoAsignacionNovedadTCREARptNovedad != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(CausalConstants.ROLE_NAME_NOVEDAD, NovedadConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTNOVEDAD, NovedadConstants.CLASS_NAME, NovedadConstants.CLASS_NAME, grupoAsignacionNovedadTCREARptNovedad.getOid(), GrupoAsignacionNovedadConstants.ARG_NAME_TCREAR_GRUPOASIGNACIONNOVEDADTCREARPTNOVEDAD);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        
        sql.addConjunction(conjunction);
    }

    private void clas1586859868160432FiltNav2(FilterRequest filterRequest, SQLSelect sql) {
        clas1581421428736234FiltNav3(filterRequest, sql);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUCausalDMDNovedad
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Causal using displayset DSCausal that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.causal.ds.DSCausal> piuPIUCausalDMDNovedad(QueryRequest queryRequest) {
        return solveCausalTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.causal.ds.DSCausal::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUCausalDMDNovedad
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUCausalDMDNovedad
     */
    public int getCount4PIUCausalDMDNovedad(QueryRequest queryRequest) {
        return solveCausalTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUCausalNull
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Causal using displayset DSCausalNull that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.causal.ds.DSCausalNull> piuPIUCausalNull(QueryRequest queryRequest) {
        return solveCausalTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.causal.ds.DSCausalNull::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUCausalNull
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUCausalNull
     */
    public int getCount4PIUCausalNull(QueryRequest queryRequest) {
        return solveCausalTypedQuery(queryRequest).size();
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
            com.keralty.aeconfiguraciones.viewmodel.causal.siu.CrearRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = causalSrv.crearTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.causal.siu.CrearRequest request) throws ModelException {
        request.setCausalcrearpagrNovedadInstance(novedadSrv.getByOIDWithHV(request.getCausalcrearpagrNovedad()));
        return causalSrv.crearImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.causal.siu.CrearRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Novedad causalcrearpagrNovedad = request.getCausalcrearpagrNovedadInstance();
        String causalcrearpatrAliasCausal = request.getCausalcrearpatrAliasCausal();
        String causalcrearpatrNombre = request.getCausalcrearpatrNombre();
        String causalcrearpatrDescripcion = request.getCausalcrearpatrDescripcion();
        String causalcrearpatrCodigoLegal = request.getCausalcrearpatrCodigoLegal();
        Boolean causalcrearpatrReqDocPersona = request.getCausalcrearpatrReqDocPersona();
        Boolean causalcrearpatrReqDocSolicitud = request.getCausalcrearpatrReqDocSolicitud();

        request.checkArguments();
        Causal instance = new Causal();
        instance.add2Novedad(causalcrearpagrNovedad);

        instance.setCausalidCausal(Long.valueOf(0));
        instance.setCausalAliasCausal(causalcrearpatrAliasCausal);
        instance.setCausalNombre(causalcrearpatrNombre);
        instance.setCausalDescripcion(causalcrearpatrDescripcion);
        instance.setCausalCodigoLegal(causalcrearpatrCodigoLegal);
        instance.setCausalReqDocPersona(causalcrearpatrReqDocPersona);
        instance.setCausalReqDocSolicitud(causalcrearpatrReqDocSolicitud);

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICCausal());

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
            com.keralty.aeconfiguraciones.viewmodel.causal.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = causalSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.causal.siu.EliminarRequest request) throws ModelException {
        request.setCausaleliminarpthisCausalInstance(causalSrv.getByOIDWithHV(request.getCausaleliminarpthisCausal()));
        return causalSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.causal.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Causal causaleliminarpthisCausal = request.getCausaleliminarpthisCausalInstance();

        request.checkArguments();
        Causal instance = causaleliminarpthisCausal;
        if(causaleliminarpthisCausal.getSolicitudes().size() > 0) {
            throw new MinCardinalityException(CausalConstants.CLASS_ALIAS, CausalConstants.ROLE_ALIAS_SOLICITUDES);
        }
        if(causaleliminarpthisCausal.getGrupoAsignacionNovedad().size() > 0) {
            throw new MinCardinalityException(CausalConstants.CLASS_ALIAS, CausalConstants.ROLE_ALIAS_GRUPOASIGNACIONNOVEDAD);
        }
        if(causaleliminarpthisCausal.getConfiguracionesANS().size() > 0) {
            throw new MinCardinalityException(CausalConstants.CLASS_ALIAS, CausalConstants.ROLE_ALIAS_CONFIGURACIONESANS);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICCausal());

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
            com.keralty.aeconfiguraciones.viewmodel.causal.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = causalSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.causal.siu.ModificarRequest request) throws ModelException {
        request.setCausalmodificarpthisCausalInstance(causalSrv.getByOIDWithHV(request.getCausalmodificarpthisCausal()));
        return causalSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.causal.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Causal causalmodificarpthisCausal = request.getCausalmodificarpthisCausalInstance();
        String causalmodificarpAliasCausal = request.getCausalmodificarpAliasCausal();
        String causalmodificarpNombre = request.getCausalmodificarpNombre();
        String causalmodificarpDescripcion = request.getCausalmodificarpDescripcion();
        String causalmodificarpCodigoLegal = request.getCausalmodificarpCodigoLegal();
        Boolean causalmodificarpReqDocPersona = request.getCausalmodificarpReqDocPersona();
        Boolean causalmodificarpReqDocSolicitud = request.getCausalmodificarpReqDocSolicitud();

        request.checkArguments();
        Causal instance = causalmodificarpthisCausal;
        instance.setCausalAliasCausal(causalmodificarpAliasCausal);
        instance.setCausalNombre(causalmodificarpNombre);
        instance.setCausalDescripcion(causalmodificarpDescripcion);
        instance.setCausalCodigoLegal(causalmodificarpCodigoLegal);
        instance.setCausalReqDocPersona(causalmodificarpReqDocPersona);
        instance.setCausalReqDocSolicitud(causalmodificarpReqDocSolicitud);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICCausal());

        return response;
    }


    private void suppInfo4ICCausal(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(CausalConstants.ATTR_NAME_CAUSALNOMBRE, Constants.Type.STRING.getTypeName()));
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
        applyOCOCCausal(queryRequest, select);
        applyDSItemSort(queryRequest, select);
        applyDefaultOrderCriteria(queryRequest, select);
    }

    private void applyDefaultOrderCriteria(QueryRequest queryRequest, SQLSelect select) {
        if (queryRequest.getOrderCriteriaName() != null && !queryRequest.getOrderCriteriaName().isBlank())
            return;
        if (queryRequest.getDsItemSort() != null && !queryRequest.getDsItemSort().isBlank())
            return;
        select.getOrderByPart().addItem(Constants.normalizePath(CausalConstants.CLASS_NAME, CausalConstants.ATTR_FIELD_CAUSALIDCAUSAL, false), true);
    }


    private void applyOCOCCausal(QueryRequest queryRequest, SQLSelect select) {
        if ("OC_Causal".equalsIgnoreCase(queryRequest.getOrderCriteriaName())) {
            select.getOrderByPart().addItem(Constants.normalizePath(CausalConstants.CLASS_NAME, CausalConstants.ATTR_NAME_CAUSALNOMBRE, false), true);
        }
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildCausalRelatedInstance(relatedInstanceJsonOid);
        }
        if (CausalConstants.ROLE_NAME_SOLICITUDES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildCausalRelatedInstance4SolicitudNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (CausalConstants.ROLE_NAME_GRUPOASIGNACIONNOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildCausalRelatedInstance4GrupoAsignacionNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (CausalConstants.ROLE_NAME_CONFIGURACIONESANS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildCausalRelatedInstance4ConfiguracionANS(relatedInstanceJsonOid, remainingPath);
        }
        if (CausalConstants.ROLE_NAME_NOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildCausalRelatedInstance4Novedad(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildCausalRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Causal(mapper.readValue(relatedInstanceJsonOid, CausalOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildCausalRelatedInstance4SolicitudNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new SolicitudNovedad(mapper.readValue(relatedInstanceJsonOid, SolicitudNovedadOid.class)): SolicitudNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildCausalRelatedInstance4GrupoAsignacionNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new GrupoAsignacionNovedad(mapper.readValue(relatedInstanceJsonOid, GrupoAsignacionNovedadOid.class)): GrupoAsignacionNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildCausalRelatedInstance4ConfiguracionANS(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ConfiguracionANS(mapper.readValue(relatedInstanceJsonOid, ConfiguracionANSOid.class)): ConfiguracionANS.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildCausalRelatedInstance4Novedad(String relatedInstanceJsonOid, String remainingPath) {
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
            CausalOid oid = new CausalOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(CausalConstants.ATTR_FIELD_CAUSALIDCAUSAL, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getCausalidCausal(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(CausalService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
