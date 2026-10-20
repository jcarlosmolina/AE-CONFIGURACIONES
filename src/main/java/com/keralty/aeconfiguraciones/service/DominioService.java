package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.MinCardinalityException;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.DominioConstants;
import com.keralty.aeconfiguraciones.global.PlanesConstants;
import com.keralty.aeconfiguraciones.global.ProductosConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.AgentexProducto;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionANS;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacion;
import com.keralty.aeconfiguraciones.persistence.GrupoAsignacionNovedad;
import com.keralty.aeconfiguraciones.persistence.LogDatosDominio;
import com.keralty.aeconfiguraciones.persistence.LogGruposUsuarios;
import com.keralty.aeconfiguraciones.persistence.LogIncompatibilidades;
import com.keralty.aeconfiguraciones.persistence.Novedad;
import com.keralty.aeconfiguraciones.persistence.oid.AgentexProductoOid;
import com.keralty.aeconfiguraciones.persistence.oid.ConfiguracionANSOid;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.FormaPagoOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.GrupoAsignacionOid;
import com.keralty.aeconfiguraciones.persistence.oid.LogDatosDominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.LogGruposUsuariosOid;
import com.keralty.aeconfiguraciones.persistence.oid.LogIncompatibilidadesOid;
import com.keralty.aeconfiguraciones.persistence.oid.NovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.ParentescoDomOid;
import com.keralty.aeconfiguraciones.persistence.oid.PeriodicidadOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaEmailOid;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaReporteOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProrrateoOid;
import com.keralty.aeconfiguraciones.persistence.oid.ReportesDominioOid;
import com.keralty.aeconfiguraciones.persistence.oid.RolOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoNovedadOid;
import com.keralty.aeconfiguraciones.persistence.ParentescoDom;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.PlantillaEmail;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Prorrateo;
import com.keralty.aeconfiguraciones.persistence.ReportesDominio;
import com.keralty.aeconfiguraciones.persistence.Rol;
import com.keralty.aeconfiguraciones.persistence.TipoNovedad;
import com.keralty.aeconfiguraciones.repository.DominioJpaRepository;
import com.keralty.aeconfiguraciones.sqlutils.SQLSelect;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereComparison;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereConjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereDisjunction;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandLiteral;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandOVParameter;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathAttribute;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandPathInstance;
import com.keralty.aeconfiguraciones.sqlutils.SQLWhereOperandSubQuery;
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
public class DominioService extends AbstractService {

    DominioJpaRepository repository;

    DominioService dominioSrv;

    PlanesService planesSrv;

    ProductosService productosSrv;

    GrupoAsignacionService grupoAsignacionSrv;

    PeriodicidadService periodicidadSrv;

    RolService rolSrv;

    FormaPagoService formaPagoSrv;

    PlantillaEmailService plantillaEmailSrv;

    NovedadService novedadSrv;

    AgentexProductoService agentexProductoSrv;

    ParentescoDomService parentescoDomSrv;

    ProrrateoService prorrateoSrv;

    LogDatosDominioService logDatosDominioSrv;

    LogGruposUsuariosService logGruposUsuariosSrv;

    LogIncompatibilidadesService logIncompatibilidadesSrv;

    TipoNovedadService tipoNovedadSrv;

    PlantillaReporteService plantillaReporteSrv;

    ReportesDominioService reportesDominioSrv;

    GrupoAsignacionNovedadService grupoAsignacionNovedadSrv;

    ConfiguracionANSService configuracionANSSrv;

    @Inject
    public DominioService(
            DominioService dominioSrv,
            PlanesService planesSrv,
            ProductosService productosSrv,
            GrupoAsignacionService grupoAsignacionSrv,
            PeriodicidadService periodicidadSrv,
            RolService rolSrv,
            FormaPagoService formaPagoSrv,
            PlantillaEmailService plantillaEmailSrv,
            NovedadService novedadSrv,
            AgentexProductoService agentexProductoSrv,
            ParentescoDomService parentescoDomSrv,
            ProrrateoService prorrateoSrv,
            LogDatosDominioService logDatosDominioSrv,
            LogGruposUsuariosService logGruposUsuariosSrv,
            LogIncompatibilidadesService logIncompatibilidadesSrv,
            TipoNovedadService tipoNovedadSrv,
            PlantillaReporteService plantillaReporteSrv,
            ReportesDominioService reportesDominioSrv,
            GrupoAsignacionNovedadService grupoAsignacionNovedadSrv,
            ConfiguracionANSService configuracionANSSrv,
            DominioJpaRepository repository) {
        this.dominioSrv = dominioSrv;
        this.planesSrv = planesSrv;
        this.productosSrv = productosSrv;
        this.grupoAsignacionSrv = grupoAsignacionSrv;
        this.periodicidadSrv = periodicidadSrv;
        this.rolSrv = rolSrv;
        this.formaPagoSrv = formaPagoSrv;
        this.plantillaEmailSrv = plantillaEmailSrv;
        this.novedadSrv = novedadSrv;
        this.agentexProductoSrv = agentexProductoSrv;
        this.parentescoDomSrv = parentescoDomSrv;
        this.prorrateoSrv = prorrateoSrv;
        this.logDatosDominioSrv = logDatosDominioSrv;
        this.logGruposUsuariosSrv = logGruposUsuariosSrv;
        this.logIncompatibilidadesSrv = logIncompatibilidadesSrv;
        this.tipoNovedadSrv = tipoNovedadSrv;
        this.plantillaReporteSrv = plantillaReporteSrv;
        this.reportesDominioSrv = reportesDominioSrv;
        this.grupoAsignacionNovedadSrv = grupoAsignacionNovedadSrv;
        this.configuracionANSSrv = configuracionANSSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, DominioConstants.CLASS_NAME);
    }

    private List<Dominio> solveDominioTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, DominioConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveDominioTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, DominioConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class Dominio
     *
     * @return Population of class Dominio
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Dominio> get() {
        List<Dominio> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class Dominio
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class Dominio
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<Dominio> instances = get(queryRequest);
        if(DominioConstants.DSICDOMINIO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICDominio())).collect(Collectors.toList());
        }
        if(DominioConstants.DSDSDOMINIOPIU.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSDominioPIU())).collect(Collectors.toList());
        }
        if(DominioConstants.DSDSDOMINIOIIU.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSDominioIIU())).collect(Collectors.toList());
        }
        if(DominioConstants.DSDSDOMINIOIIUREPORTES.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSDominioIIUReportes())).collect(Collectors.toList());
        }
        if(DominioConstants.DSDSDOMINIOFIRMARESPCLAUSULADO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSDominioFirmaRespClausulado())).collect(Collectors.toList());
        }
        if(DominioConstants.DSDSDOMINIOSOLNOVEDAD.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSDominioSolNovedad())).collect(Collectors.toList());
        }
        if(DominioConstants.DSDSDOMINIOMGRUPOASESORES.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSDominioMGrupoAsesores())).collect(Collectors.toList());
        }
        if(DominioConstants.DSDSDOMINIOMGRUPOAREAMEDICA.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSDominioMGrupoAreaMedica())).collect(Collectors.toList());
        }
        if(DominioConstants.DSDSDOMINIOMGRUPOSUSUARIOS.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSDominioMGruposUsuarios())).collect(Collectors.toList());
        }
        if(DominioConstants.DSDSDOMINIOSPORASESOR.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSDominiosPorAsesor())).collect(Collectors.toList());
        }
        if(DominioConstants.DSDSDOMINIOMLOG.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSDominioMLog())).collect(Collectors.toList());
        }
        if(DominioConstants.DSDSDOMINIODINCOMPATIBLES.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSDominioDIncompatibles())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class Dominio that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class Dominio matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Dominio> get(QueryRequest queryRequest) {
        return solveDominioTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveDominioTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveDominioTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveDominioTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveDominioTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of Dominio by ID
     *
     * @param dominioNumero Identification field dominioNumero
     *
     * @return Instance of Dominio
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Dominio getById(Long dominioNumero) {
        return getByOID(new DominioOid(dominioNumero));
    }
    
    /*
     * Returns an instance of Dominio by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Dominio
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public Dominio getByOID(DominioOid oid) {
        Optional<Dominio> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of Dominio by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of Dominio
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public Dominio getByOIDWithHV(DominioOid oid) {
        Optional<Dominio> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<Dominio> getInstanceByOid(DominioOid oid) {
        return oid != null && oid.getDominioNumero() != null
               ? repository.findById(oid.getDominioNumero())
               : Optional.empty();
    }

    /*
     * Returns an instance of Dominio by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of Dominio
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Dominio getByJsonOid(String jsonOid) {
        DominioOid oid = new DominioOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of Dominio related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Dominio
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Dominio> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveDominioTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of Dominio related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of Dominio
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Dominio> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of Dominio
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of Dominio
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getDominioDynamic(DominioOid oid, String displaySetItems) {
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
    public String getSuppInfo(DominioOid oid, String dsName) {
        Dominio instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(Dominio instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (DominioConstants.DSICDOMINIO.equals(dsName))
            return instance.buildICDominio();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(Dominio instance, String dsName) {
        if (DominioConstants.DSICDOMINIO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICDominio());
        if (DominioConstants.DSDSDOMINIOPIU.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSDominioPIU());
        if (DominioConstants.DSDSDOMINIOIIU.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSDominioIIU());
        if (DominioConstants.DSDSDOMINIOIIUREPORTES.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSDominioIIUReportes());
        if (DominioConstants.DSDSDOMINIOFIRMARESPCLAUSULADO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSDominioFirmaRespClausulado());
        if (DominioConstants.DSDSDOMINIOSOLNOVEDAD.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSDominioSolNovedad());
        if (DominioConstants.DSDSDOMINIOMGRUPOASESORES.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSDominioMGrupoAsesores());
        if (DominioConstants.DSDSDOMINIOMGRUPOAREAMEDICA.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSDominioMGrupoAreaMedica());
        if (DominioConstants.DSDSDOMINIOMGRUPOSUSUARIOS.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSDominioMGruposUsuarios());
        if (DominioConstants.DSDSDOMINIOSPORASESOR.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSDominiosPorAsesor());
        if (DominioConstants.DSDSDOMINIOMLOG.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSDominioMLog());
        if (DominioConstants.DSDSDOMINIODINCOMPATIBLES.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSDominioDIncompatibles());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyQueryFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if (DominioConstants.FILTER_NAME_FDOMINIOVARIOS.equalsIgnoreCase(filterRequest.getName()))
            fDominioVarios(filterRequest, sql);
        if (DominioConstants.FILTER_NAME_IFDOMINIOPRODPLAN.equalsIgnoreCase(filterRequest.getName()))
            iFDominioProdPlan(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_Dominio".equals(filterRequest.getName()))
            suppInfo4ICDominio(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void fDominioVarios(FilterRequest filterRequest, SQLSelect sql) throws SystemException{
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Productos voProducto = filterRequest.hasFilterVariable(DominioConstants.VAR_NAME_FDOMINIOVARIOS_VOPRODUCTO) ? productosSrv.getByOID(new ProductosOid().fromJsonString(toJson(filterRequest.getFilterVariable(DominioConstants.VAR_NAME_FDOMINIOVARIOS_VOPRODUCTO)))) : null;
        Planes voPlan = filterRequest.hasFilterVariable(DominioConstants.VAR_NAME_FDOMINIOVARIOS_VOPLAN) ? planesSrv.getByOID(new PlanesOid().fromJsonString(toJson(filterRequest.getFilterVariable(DominioConstants.VAR_NAME_FDOMINIOVARIOS_VOPLAN)))) : null;
        Boolean vNuevaAfiliacion = filterRequest.hasFilterVariable(DominioConstants.VAR_NAME_FDOMINIOVARIOS_VNUEVAAFILIACION) ? (Boolean) filterRequest.getFilterVariable(DominioConstants.VAR_NAME_FDOMINIOVARIOS_VNUEVAAFILIACION, FilterVariableRequest.BOOL) : null;
        Boolean vInclusion = filterRequest.hasFilterVariable(DominioConstants.VAR_NAME_FDOMINIOVARIOS_VINCLUSION) ? (Boolean) filterRequest.getFilterVariable(DominioConstants.VAR_NAME_FDOMINIOVARIOS_VINCLUSION, FilterVariableRequest.BOOL) : null;
        Rol voRol = filterRequest.hasFilterVariable(DominioConstants.VAR_NAME_FDOMINIOVARIOS_VOROL) ? rolSrv.getByOID(new RolOid().fromJsonString(toJson(filterRequest.getFilterVariable(DominioConstants.VAR_NAME_FDOMINIOVARIOS_VOROL)))) : null;

        // ProductoT = voProducto
        if (voProducto != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(DominioConstants.ROLE_NAME_PRODUCTOT, ProductosConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(DominioConstants.VAR_NAME_FDOMINIOVARIOS_VOPRODUCTO, ProductosConstants.CLASS_NAME, ProductosConstants.CLASS_NAME, voProducto.getOid(), DominioConstants.VAR_NAME_FDOMINIOVARIOS_VOPRODUCTO);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // PlanT = voPlan
        if (voPlan != null) {
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(DominioConstants.ROLE_NAME_PLANT, PlanesConstants.CLASS_NAME);
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            SQLWhereOperandOVParameter operand224 = new SQLWhereOperandOVParameter(DominioConstants.VAR_NAME_FDOMINIOVARIOS_VOPLAN, PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, voPlan.getOid(), DominioConstants.VAR_NAME_FDOMINIOVARIOS_VOPLAN);
            comparison2.setSecondOperand(operand224);
            conjunction.addOperand(comparison2);

        }        

        // NuevaAfiliacion = vNuevaAfiliacion
        if (vNuevaAfiliacion != null) {
            // '=' Comparison
            SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
            comparison3.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(DominioConstants.ATTR_NAME_DOMINIONUEVAAFILIACION, Constants.Type.BOOL.getTypeName());
            comparison3.setFirstOperand(operand114);
            
            // Operand 2
            comparison3.setSecondOperand(new SQLWhereOperandLiteral(vNuevaAfiliacion, Constants.Type.BOOL.getTypeName()));
            conjunction.addOperand(comparison3);

        }        

        // Inclusion = vInclusion
        if (vInclusion != null) {
            // '=' Comparison
            SQLWhereComparison comparison4 = new SQLWhereComparison(sql);
            comparison4.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathAttribute operand115 = new SQLWhereOperandPathAttribute(DominioConstants.ATTR_NAME_DOMINIOINCLUSION, Constants.Type.BOOL.getTypeName());
            comparison4.setFirstOperand(operand115);
            
            // Operand 2
            comparison4.setSecondOperand(new SQLWhereOperandLiteral(vInclusion, Constants.Type.BOOL.getTypeName()));
            conjunction.addOperand(comparison4);

        }        

        // EXIST (Roles) WHERE (Roles = voRol) = true
        if (voRol != null) {
    // 'exist' Association operator
    SQLWhereOperandSubQuery operandSubQueryAssocOperator000 = new SQLWhereOperandSubQuery(repository.assocOperator000Range1Query(voRol, sql));
    conjunction.addOperand(operandSubQueryAssocOperator000);

        }        

        sql.addConjunction(conjunction);
    }

    private void iFDominioProdPlan(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        Productos voProducto = filterRequest.hasFilterVariable(DominioConstants.VAR_NAME_IFDOMINIOPRODPLAN_VOPRODUCTO) ? productosSrv.getByOID(new ProductosOid().fromJsonString(toJson(filterRequest.getFilterVariable(DominioConstants.VAR_NAME_IFDOMINIOPRODPLAN_VOPRODUCTO)))) : null;
        Planes voPlan = filterRequest.hasFilterVariable(DominioConstants.VAR_NAME_IFDOMINIOPRODPLAN_VOPLAN) ? planesSrv.getByOID(new PlanesOid().fromJsonString(toJson(filterRequest.getFilterVariable(DominioConstants.VAR_NAME_IFDOMINIOPRODPLAN_VOPLAN)))) : null;

        // ProductoT = voProducto
        if (voProducto != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(DominioConstants.ROLE_NAME_PRODUCTOT, ProductosConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(DominioConstants.VAR_NAME_IFDOMINIOPRODPLAN_VOPRODUCTO, ProductosConstants.CLASS_NAME, ProductosConstants.CLASS_NAME, voProducto.getOid(), DominioConstants.VAR_NAME_IFDOMINIOPRODPLAN_VOPRODUCTO);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // PlanT = voPlan
        if (voPlan != null) {
            // '=' Comparison
            SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
            comparison2.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand113 = new SQLWhereOperandPathInstance(DominioConstants.ROLE_NAME_PLANT, PlanesConstants.CLASS_NAME);
            comparison2.setFirstOperand(operand113);
            
            // Operand 2
            SQLWhereOperandOVParameter operand224 = new SQLWhereOperandOVParameter(DominioConstants.VAR_NAME_IFDOMINIOPRODPLAN_VOPLAN, PlanesConstants.CLASS_NAME, PlanesConstants.CLASS_NAME, voPlan.getOid(), DominioConstants.VAR_NAME_IFDOMINIOPRODPLAN_VOPLAN);
            comparison2.setSecondOperand(operand224);
            conjunction.addOperand(comparison2);

        }        

        sql.addConjunction(conjunction);
    }

    // IIU Methods

    /*
     * Returns a viewmodel for class Dominio with displayset DSDominioIIU for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class Dominio with displayset DSDominioIIU
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioIIU iIUDominio(DominioOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioIIU(getByOIDWithHV(oid));
    }

    /*
     * Returns a viewmodel for class Dominio with displayset DSDominioFirmaRespClausulado for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class Dominio with displayset DSDominioFirmaRespClausulado
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioFirmaRespClausulado iIUDominioFirma(DominioOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioFirmaRespClausulado(getByOIDWithHV(oid));
    }

    /*
     * Returns a viewmodel for class Dominio with displayset DSDominioSolNovedad for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class Dominio with displayset DSDominioSolNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioSolNovedad iIUDominioDSolNovedad(DominioOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioSolNovedad(getByOIDWithHV(oid));
    }

    /*
     * Returns a viewmodel for class Dominio with displayset DSDominioMGrupoAsesores for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class Dominio with displayset DSDominioMGrupoAsesores
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioMGrupoAsesores iIUDominioMGrupoAsesores(DominioOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioMGrupoAsesores(getByOIDWithHV(oid));
    }

    /*
     * Returns a viewmodel for class Dominio with displayset DSDominioMGrupoAreaMedica for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class Dominio with displayset DSDominioMGrupoAreaMedica
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioMGrupoAreaMedica iIUDominioMGrupoAreaMedica(DominioOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioMGrupoAreaMedica(getByOIDWithHV(oid));
    }

    /*
     * Returns a viewmodel for class Dominio with displayset DSDominioMGruposUsuarios for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class Dominio with displayset DSDominioMGruposUsuarios
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioMGruposUsuarios iIUDominioMGruposUsuarios(DominioOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioMGruposUsuarios(getByOIDWithHV(oid));
    }

    /*
     * Returns a viewmodel for class Dominio with displayset DSDominioMLog for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class Dominio with displayset DSDominioMLog
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioMLog iIUDominioMIncompatibles(DominioOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioMLog(getByOIDWithHV(oid));
    }

    /*
     * Returns a viewmodel for class Dominio with displayset DSDominioMLog for the instance with a given OID
     * @param oid Class oid
     * @return Viewmodel for class class Dominio with displayset DSDominioMLog
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioMLog iIUDominioMLogDatos(DominioOid oid) {
        return new com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioMLog(getByOIDWithHV(oid));
    }


    /*
     * Solves the query needed for PIUDominio
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Dominio using displayset DSDominioPIU that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioPIU> piuPIUDominio(QueryRequest queryRequest) {
        return solveDominioTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioPIU::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUDominio
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUDominio
     */
    public int getCount4PIUDominio(QueryRequest queryRequest) {
        return solveDominioTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUDominiosPorAsesor
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Dominio using displayset DSDominiosPorAsesor that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominiosPorAsesor> piuPIUDominiosPorAsesor(QueryRequest queryRequest) {
        return solveDominioTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominiosPorAsesor::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUDominiosPorAsesor
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUDominiosPorAsesor
     */
    public int getCount4PIUDominiosPorAsesor(QueryRequest queryRequest) {
        return solveDominioTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUDominioDIncompatibles
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Dominio using displayset DSDominioDIncompatibles that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioDIncompatibles> piuPIUDominioDIncompatibles(QueryRequest queryRequest) {
        return solveDominioTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioDIncompatibles::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUDominioDIncompatibles
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUDominioDIncompatibles
     */
    public int getCount4PIUDominioDIncompatibles(QueryRequest queryRequest) {
        return solveDominioTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUDominioObs
     *
     * @param queryRequest Query request
     * @return List of viewmodels for Dominio using displayset DSDominioPIU that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioPIU> piuPIUDominioObs(QueryRequest queryRequest) {
        return solveDominioTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioPIU::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUDominioObs
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUDominioObs
     */
    public int getCount4PIUDominioObs(QueryRequest queryRequest) {
        return solveDominioTypedQuery(queryRequest).size();
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
    public com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearResponse crear(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearRequest request) {
        com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearResponse response = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearResponse();
        try {
            response = dominioSrv.crearTxn(request);
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
    public com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearResponse crearTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearRequest request) throws ModelException {
        request.setDominiocrearpagrPlanTInstance(planesSrv.getByOIDWithHV(new PlanesOid().fromJsonString(request.getDominiocrearpagrPlanT())));
        request.setDominiocrearpagrProductoTInstance(productosSrv.getByOIDWithHV(new ProductosOid().fromJsonString(request.getDominiocrearpagrProductoT())));
        request.setDominiocrearpagrGrupoAsesoresInstance(grupoAsignacionSrv.getByOIDWithHV(request.getDominiocrearpagrGrupoAsesores()));
        request.setDominiocrearpagrGrupoAreaMedicaInstance(grupoAsignacionSrv.getByOIDWithHV(request.getDominiocrearpagrGrupoAreaMedica()));
        return dominioSrv.crearImplementation(request);
    }

    /*
     * Implements service crear
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearResponse crearImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearRequest request) throws ModelException {
        com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearResponse response = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearResponse();
        Planes dominiocrearpagrPlanT = request.getDominiocrearpagrPlanTInstance();
        Productos dominiocrearpagrProductoT = request.getDominiocrearpagrProductoTInstance();
        GrupoAsignacion dominiocrearpagrGrupoAsesores = request.getDominiocrearpagrGrupoAsesoresInstance();
        GrupoAsignacion dominiocrearpagrGrupoAreaMedica = request.getDominiocrearpagrGrupoAreaMedicaInstance();
        Boolean dominiocrearpatrReqCuesMed = request.getDominiocrearpatrReqCuesMed();
        Boolean dominiocrearpatrNoCuesMedTras = request.getDominiocrearpatrNoCuesMedTras();
        Boolean dominiocrearpatrNuevaAfiliacion = request.getDominiocrearpatrNuevaAfiliacion();
        Boolean dominiocrearpatrInclusion = request.getDominiocrearpatrInclusion();
        Boolean dominiocrearpatrAcepCuesMat = request.getDominiocrearpatrAcepCuesMat();
        Boolean dominiocrearpatrAcepCuesNeo = request.getDominiocrearpatrAcepCuesNeo();
        Boolean dominiocrearpatrAcepBBGestante = request.getDominiocrearpatrAcepBBGestante();
        Boolean dominiocrearpatrMostrarPreexAnexos = request.getDominiocrearpatrMostrarPreexAnexos();
        Boolean dominiocrearpatrMostrarPreexContrato = request.getDominiocrearpatrMostrarPreexContrato();
        Boolean dominiocrearpatrIgnorarPreexistencias = request.getDominiocrearpatrIgnorarPreexistencias();
        Boolean dominiocrearpatrProrrateoUsuExistente = request.getDominiocrearpatrProrrateoUsuExistente();
        Boolean dominiocrearpatrInclusionModGrupoAsoc = request.getDominiocrearpatrInclusionModGrupoAsoc();
        Boolean dominiocrearpatrPermiteRechazadosAfi = request.getDominiocrearpatrPermiteRechazadosAfi();
        Boolean dominiocrearpatrAcepPolizaInter = request.getDominiocrearpatrAcepPolizaInter();
        Boolean dominiocrearpatrNovExcepcionReqDoc = request.getDominiocrearpatrNovExcepcionReqDoc();
        String dominiocrearpatrSolNovColorOscuro = request.getDominiocrearpatrSolNovColorOscuro();
        String dominiocrearpatrSolNovColorClaro = request.getDominiocrearpatrSolNovColorClaro();
        String dominiocrearpatrSolNovLogo = request.getDominiocrearpatrSolNovLogo();
        Boolean dominiocrearpatrTramiteEnLinea = request.getDominiocrearpatrTramiteEnLinea();
        Boolean dominiocrearpatrVigenciaRadicacion = request.getDominiocrearpatrVigenciaRadicacion();

        request.checkArguments();

        crearPreconditions(request);
        Dominio instance = new Dominio();
        instance.add2PlanT(dominiocrearpagrPlanT);

        instance.add2ProductoT(dominiocrearpagrProductoT);

        if (dominiocrearpagrGrupoAsesores != null && !dominiocrearpagrGrupoAsesores.isNull()) {
            instance.add2GrupoAsesores(dominiocrearpagrGrupoAsesores);
        }

        if (dominiocrearpagrGrupoAreaMedica != null && !dominiocrearpagrGrupoAreaMedica.isNull()) {
            instance.add2GrupoAreaMedica(dominiocrearpagrGrupoAreaMedica);
        }

        instance.setDominioNumero(Long.valueOf(0));
        instance.setDominioRespClauNombre(null);
        instance.setDominioRespClauCelula(null);
        instance.setDominioRespClauExpCelula(null);
        instance.setDominioRespClauCargo(null);
        instance.setDominioDiaIniVigencia(null);
        instance.setDominioDiaFinVigencia(null);
        instance.setDominioDiasCaducidad(Long.valueOf(0));
        instance.setDominioReqCuesMed(dominiocrearpatrReqCuesMed);
        instance.setDominioNoCuesMedTras(dominiocrearpatrNoCuesMedTras);
        instance.setDominioNuevaAfiliacion(dominiocrearpatrNuevaAfiliacion);
        instance.setDominioInclusion(dominiocrearpatrInclusion);
        instance.setDominioAcepCuesMat(dominiocrearpatrAcepCuesMat);
        instance.setDominioAcepCuesNeo(dominiocrearpatrAcepCuesNeo);
        instance.setDominioAcepBBGestante(dominiocrearpatrAcepBBGestante);
        instance.setDominioMostrarPreexAnexos(dominiocrearpatrMostrarPreexAnexos);
        instance.setDominioMostrarPreexContrato(dominiocrearpatrMostrarPreexContrato);
        instance.setDominioIgnorarPreexistencias(dominiocrearpatrIgnorarPreexistencias);
        instance.setDominioProrrateoUsuExistente(dominiocrearpatrProrrateoUsuExistente);
        instance.setDominioInclusionModGrupoAsoc(dominiocrearpatrInclusionModGrupoAsoc);
        instance.setDominioPermiteRechazadosAfi(dominiocrearpatrPermiteRechazadosAfi);
        instance.setDominioAcepPolizaInter(dominiocrearpatrAcepPolizaInter);
        instance.setDominioNovExcepcionReqDoc(dominiocrearpatrNovExcepcionReqDoc);
        instance.setDominioSolNovColorOscuro(dominiocrearpatrSolNovColorOscuro);
        instance.setDominioSolNovColorClaro(dominiocrearpatrSolNovColorClaro);
        instance.setDominioSolNovLogo(dominiocrearpatrSolNovLogo);
        instance.setDominioTramiteEnLinea(dominiocrearpatrTramiteEnLinea);
        instance.setDominioVigenciaRadicacion(dominiocrearpatrVigenciaRadicacion);

        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        // Value expressions for outgoing argument 'dominiocrearpsDominio'
        // DEFAULT  : THIS
        response.setDominiocrearpsDominioInstance(instance);


        return response;
    }

    /*
     * Checks service crear preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearResponse crearCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearRequest request) {
        com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearResponse response = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearResponse();
        request.setDominiocrearpagrPlanTInstance(planesSrv.getByOIDWithHV(new PlanesOid().fromJsonString(request.getDominiocrearpagrPlanT())));
        request.setDominiocrearpagrProductoTInstance(productosSrv.getByOIDWithHV(new ProductosOid().fromJsonString(request.getDominiocrearpagrProductoT())));
        request.setDominiocrearpagrGrupoAsesoresInstance(grupoAsignacionSrv.getByOIDWithHV(request.getDominiocrearpagrGrupoAsesores()));
        request.setDominiocrearpagrGrupoAreaMedicaInstance(grupoAsignacionSrv.getByOIDWithHV(request.getDominiocrearpagrGrupoAreaMedica()));
        try {
            if("Clas_1431701422080423Pre_1".equals(request.getPreconditionId()))
                checkClas1431701422080423Pre1(request);
            if("Clas_1431701422080423Pre_2".equals(request.getPreconditionId()))
                checkClas1431701422080423Pre2(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void crearPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearRequest request) 
            throws ModelException {
        checkClas1431701422080423Pre1(request);
        checkClas1431701422080423Pre2(request);
    }

    private void checkClas1431701422080423Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearRequest request) 
            throws ModelException {
        // EXIST( pagrProductoT.Dominios ) WHERE (pagrProductoT.Dominios.PlanT = pagrPlanT) = false

        Productos dominiocrearpagrProductoT = request.getDominiocrearpagrProductoTInstance();
        Planes dominiocrearpagrPlanT = request.getDominiocrearpagrPlanTInstance();
        try {
            boolean preconditionHolds = repository.assocOperator001(dominiocrearpagrProductoT, dominiocrearpagrPlanT).equals(false);
            if (!preconditionHolds) throw new PreconditionException("Ya existe un dominio definido para el producto y plan indicado");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    private void checkClas1431701422080423Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearRequest request) 
            throws ModelException {
        // EXIST( pagrPlanT.Dominios ) WHERE (pagrPlanT.Dominios.ProductoT = pagrProductoT) = false

        Planes dominiocrearpagrPlanT = request.getDominiocrearpagrPlanTInstance();
        Productos dominiocrearpagrProductoT = request.getDominiocrearpagrProductoTInstance();
        try {
            boolean preconditionHolds = repository.assocOperator002(dominiocrearpagrPlanT, dominiocrearpagrProductoT).equals(false);
            if (!preconditionHolds) throw new PreconditionException("Ya existe un dominio definido para el producto y plan indicado");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
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
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.EliminarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.eliminarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.EliminarRequest request) throws ModelException {
        request.setDominioeliminarpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioeliminarpthisDominio()));
        return dominioSrv.eliminarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.EliminarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioeliminarpthisDominio = request.getDominioeliminarpthisDominioInstance();

        request.checkArguments();
        Dominio instance = dominioeliminarpthisDominio;
        if(dominioeliminarpthisDominio.getParentescosDom().size() > 0) {
            throw new MinCardinalityException(DominioConstants.CLASS_ALIAS, DominioConstants.ROLE_ALIAS_PARENTESCOSDOM);
        }
        if(dominioeliminarpthisDominio.getProrrateos().size() > 0) {
            throw new MinCardinalityException(DominioConstants.CLASS_ALIAS, DominioConstants.ROLE_ALIAS_PRORRATEOS);
        }
        if(dominioeliminarpthisDominio.getLogGruposUsuarios().size() > 0) {
            throw new MinCardinalityException(DominioConstants.CLASS_ALIAS, DominioConstants.ROLE_ALIAS_LOGGRUPOSUSUARIOS);
        }
        if(dominioeliminarpthisDominio.getGrupoAsignacionNovedad().size() > 0) {
            throw new MinCardinalityException(DominioConstants.CLASS_ALIAS, DominioConstants.ROLE_ALIAS_GRUPOASIGNACIONNOVEDAD);
        }
        if(dominioeliminarpthisDominio.getConfiguracionesANS().size() > 0) {
            throw new MinCardinalityException(DominioConstants.CLASS_ALIAS, DominioConstants.ROLE_ALIAS_CONFIGURACIONESANS);
        }
        if(dominioeliminarpthisDominio.getReportesDominio().size() > 0) {
            throw new MinCardinalityException(DominioConstants.CLASS_ALIAS, DominioConstants.ROLE_ALIAS_REPORTESDOMINIO);
        }
        if(dominioeliminarpthisDominio.getLogIncompatibilidades().size() > 0) {
            throw new MinCardinalityException(DominioConstants.CLASS_ALIAS, DominioConstants.ROLE_ALIAS_LOGINCOMPATIBILIDADES);
        }
        if(dominioeliminarpthisDominio.getLogDatosDominio().size() > 0) {
            throw new MinCardinalityException(DominioConstants.CLASS_ALIAS, DominioConstants.ROLE_ALIAS_LOGDATOSDOMINIO);
        }


        repository.delete(instance);
        
        response.setInstance(instance);
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: InsPeriodicidadDominio

    /*
     * Entry point for service InsPeriodicidadDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPeriodicidadDominio(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsPeriodicidadDominioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.insPeriodicidadDominioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPeriodicidadDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPeriodicidadDominioTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsPeriodicidadDominioRequest request) throws ModelException {
        request.setDominioInsPeriodicidadDominiopthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioInsPeriodicidadDominiopthisDominio()));
        request.setDominioInsPeriodicidadDominiopevcPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(request.getDominioInsPeriodicidadDominiopevcPeriodicidad()));
        return dominioSrv.insPeriodicidadDominioImplementation(request, true);
    }

    /*
     * Implements service InsPeriodicidadDominio
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPeriodicidadDominioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsPeriodicidadDominioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioInsPeriodicidadDominiopthisDominio = request.getDominioInsPeriodicidadDominiopthisDominioInstance();
        Periodicidad dominioInsPeriodicidadDominiopevcPeriodicidad = request.getDominioInsPeriodicidadDominiopevcPeriodicidadInstance();

        request.checkArguments();
        Dominio instance = dominioInsPeriodicidadDominiopthisDominio;

        instance.getPeriodicidades().add(dominioInsPeriodicidadDominiopevcPeriodicidad);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadDominioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.InsPeriodicidadDominioRequest();
            relatedRequest.setPeriodicidadInsPeriodicidadDominiopthisPeriodicidadInstance(dominioInsPeriodicidadDominiopevcPeriodicidad);
            relatedRequest.setPeriodicidadInsPeriodicidadDominiopevcDominioInstance(dominioInsPeriodicidadDominiopthisDominio);
            periodicidadSrv.insPeriodicidadDominioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: DelPeriodicidadDominio

    /*
     * Entry point for service DelPeriodicidadDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delPeriodicidadDominio(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelPeriodicidadDominioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.delPeriodicidadDominioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelPeriodicidadDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delPeriodicidadDominioTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelPeriodicidadDominioRequest request) throws ModelException {
        request.setDominioDelPeriodicidadDominiopthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioDelPeriodicidadDominiopthisDominio()));
        request.setDominioDelPeriodicidadDominiopevcPeriodicidadInstance(periodicidadSrv.getByOIDWithHV(new PeriodicidadOid().fromJsonString(request.getDominioDelPeriodicidadDominiopevcPeriodicidad())));
        return dominioSrv.delPeriodicidadDominioImplementation(request, true);
    }

    /*
     * Implements service DelPeriodicidadDominio
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delPeriodicidadDominioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelPeriodicidadDominioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioDelPeriodicidadDominiopthisDominio = request.getDominioDelPeriodicidadDominiopthisDominioInstance();
        Periodicidad dominioDelPeriodicidadDominiopevcPeriodicidad = request.getDominioDelPeriodicidadDominiopevcPeriodicidadInstance();

        request.checkArguments();
        Dominio instance = dominioDelPeriodicidadDominiopthisDominio;

        instance.getPeriodicidades().remove(dominioDelPeriodicidadDominiopevcPeriodicidad);
        dominioDelPeriodicidadDominiopevcPeriodicidad.getDominios().remove(instance);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadDominioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.DelPeriodicidadDominioRequest();
            relatedRequest.setPeriodicidadDelPeriodicidadDominiopthisPeriodicidadInstance(dominioDelPeriodicidadDominiopevcPeriodicidad);
            relatedRequest.setPeriodicidadDelPeriodicidadDominiopevcDominioInstance(dominioDelPeriodicidadDominiopthisDominio);
            periodicidadSrv.delPeriodicidadDominioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: InsRolDominio

    /*
     * Entry point for service InsRolDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insRolDominio(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsRolDominioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.insRolDominioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsRolDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insRolDominioTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsRolDominioRequest request) throws ModelException {
        request.setDominioInsRolDominiopthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioInsRolDominiopthisDominio()));
        request.setDominioInsRolDominiopevcRolInstance(rolSrv.getByOIDWithHV(request.getDominioInsRolDominiopevcRol()));
        return dominioSrv.insRolDominioImplementation(request, true);
    }

    /*
     * Implements service InsRolDominio
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insRolDominioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsRolDominioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioInsRolDominiopthisDominio = request.getDominioInsRolDominiopthisDominioInstance();
        Rol dominioInsRolDominiopevcRol = request.getDominioInsRolDominiopevcRolInstance();

        request.checkArguments();
        Dominio instance = dominioInsRolDominiopthisDominio;

        instance.getRoles().add(dominioInsRolDominiopevcRol);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.rol.siu.InsRolDominioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.rol.siu.InsRolDominioRequest();
            relatedRequest.setRolInsRolDominiopthisRolInstance(dominioInsRolDominiopevcRol);
            relatedRequest.setRolInsRolDominiopevcDominioInstance(dominioInsRolDominiopthisDominio);
            rolSrv.insRolDominioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: DelRollDominio

    /*
     * Entry point for service DelRollDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delRollDominio(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelRollDominioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.delRollDominioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelRollDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delRollDominioTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelRollDominioRequest request) throws ModelException {
        request.setDominioDelRollDominiopthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioDelRollDominiopthisDominio()));
        request.setDominioDelRollDominiopevcRolInstance(rolSrv.getByOIDWithHV(new RolOid().fromJsonString(request.getDominioDelRollDominiopevcRol())));
        return dominioSrv.delRollDominioImplementation(request, true);
    }

    /*
     * Implements service DelRollDominio
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delRollDominioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelRollDominioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioDelRollDominiopthisDominio = request.getDominioDelRollDominiopthisDominioInstance();
        Rol dominioDelRollDominiopevcRol = request.getDominioDelRollDominiopevcRolInstance();

        request.checkArguments();
        Dominio instance = dominioDelRollDominiopthisDominio;

        instance.getRoles().remove(dominioDelRollDominiopevcRol);
        dominioDelRollDominiopevcRol.getDominios().remove(instance);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.rol.siu.DelRollDominioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.rol.siu.DelRollDominioRequest();
            relatedRequest.setRolDelRollDominiopthisRolInstance(dominioDelRollDominiopevcRol);
            relatedRequest.setRolDelRollDominiopevcDominioInstance(dominioDelRollDominiopthisDominio);
            rolSrv.delRollDominioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: modDatosRespFirma

    /*
     * Entry point for service modDatosRespFirma
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse modDatosRespFirma(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModDatosRespFirmaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.modDatosRespFirmaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service modDatosRespFirma
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse modDatosRespFirmaTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModDatosRespFirmaRequest request) throws ModelException {
        request.setDominiomodDatosRespFirmapthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominiomodDatosRespFirmapthisDominio()));
        return dominioSrv.modDatosRespFirmaImplementation(request);
    }

    /*
     * Implements service modDatosRespFirma
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse modDatosRespFirmaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModDatosRespFirmaRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominiomodDatosRespFirmapthisDominio = request.getDominiomodDatosRespFirmapthisDominioInstance();
        String dominiomodDatosRespFirmapNombre = request.getDominiomodDatosRespFirmapNombre();
        String dominiomodDatosRespFirmapNumCelula = request.getDominiomodDatosRespFirmapNumCelula();
        String dominiomodDatosRespFirmapExpedicionCelula = request.getDominiomodDatosRespFirmapExpedicionCelula();
        String dominiomodDatosRespFirmapCargo = request.getDominiomodDatosRespFirmapCargo();

        request.checkArguments();

        modDatosRespFirmaPreconditions(request);
        Dominio instance = dominiomodDatosRespFirmapthisDominio;
        instance.setDominioRespClauNombre(dominiomodDatosRespFirmapNombre);
        instance.setDominioRespClauCelula(dominiomodDatosRespFirmapNumCelula);
        instance.setDominioRespClauExpCelula(dominiomodDatosRespFirmapExpedicionCelula);
        instance.setDominioRespClauCargo(dominiomodDatosRespFirmapCargo);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    /*
     * Checks service modDatosRespFirma preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse modDatosRespFirmaCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModDatosRespFirmaRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setDominiomodDatosRespFirmapthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominiomodDatosRespFirmapthisDominio()));
        try {
            if("Clas_1431701422080423Pre_3".equals(request.getPreconditionId()))
                checkClas1431701422080423Pre3(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void modDatosRespFirmaPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModDatosRespFirmaRequest request) 
            throws ModelException {
        checkClas1431701422080423Pre3(request);
    }

    private void checkClas1431701422080423Pre3 (
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModDatosRespFirmaRequest request) 
            throws ModelException {
        // PlanT.EsVoluntario = true

        Dominio dominiomodDatosRespFirmapthisDominio = request.getDominiomodDatosRespFirmapthisDominioInstance();
        boolean preconditionHolds = dominiomodDatosRespFirmapthisDominio.getPlanT().getPlanesEsVoluntario().equals(true);
        if (!preconditionHolds) throw new PreconditionException("Los datos del responsable de firma tan solo están permitidos para los planes voluntarios");
    }

    // Service: modImagenFirma

    /*
     * Entry point for service modImagenFirma
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse modImagenFirma(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModImagenFirmaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.modImagenFirmaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service modImagenFirma
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse modImagenFirmaTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModImagenFirmaRequest request) throws ModelException {
        request.setDominiomodImagenFirmapthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominiomodImagenFirmapthisDominio()));
        return dominioSrv.modImagenFirmaImplementation(request);
    }

    /*
     * Implements service modImagenFirma
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse modImagenFirmaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModImagenFirmaRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominiomodImagenFirmapthisDominio = request.getDominiomodImagenFirmapthisDominioInstance();
        byte[] dominiomodImagenFirmapFirma = request.getDominiomodImagenFirmapFirma();

        request.checkArguments();

        modImagenFirmaPreconditions(request);
        Dominio instance = dominiomodImagenFirmapthisDominio;
        instance.setDominioRespClauFirma(dominiomodImagenFirmapFirma);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    /*
     * Checks service modImagenFirma preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse modImagenFirmaCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModImagenFirmaRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setDominiomodImagenFirmapthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominiomodImagenFirmapthisDominio()));
        try {
            if("Clas_1431701422080423Pre_4".equals(request.getPreconditionId()))
                checkClas1431701422080423Pre4(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void modImagenFirmaPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModImagenFirmaRequest request) 
            throws ModelException {
        checkClas1431701422080423Pre4(request);
    }

    private void checkClas1431701422080423Pre4 (
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModImagenFirmaRequest request) 
            throws ModelException {
        // PlanT.EsVoluntario = true

        Dominio dominiomodImagenFirmapthisDominio = request.getDominiomodImagenFirmapthisDominioInstance();
        boolean preconditionHolds = dominiomodImagenFirmapthisDominio.getPlanT().getPlanesEsVoluntario().equals(true);
        if (!preconditionHolds) throw new PreconditionException("Los datos del responsable de firma tan solo están permitidos para los planes voluntarios");
    }

    // Service: InsFormaPago

    /*
     * Entry point for service InsFormaPago
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insFormaPago(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsFormaPagoRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.insFormaPagoTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsFormaPago
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insFormaPagoTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsFormaPagoRequest request) throws ModelException {
        request.setDominioInsFormaPagopthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioInsFormaPagopthisDominio()));
        request.setDominioInsFormaPagopevcFormaPagoInstance(formaPagoSrv.getByOIDWithHV(request.getDominioInsFormaPagopevcFormaPago()));
        return dominioSrv.insFormaPagoImplementation(request, true);
    }

    /*
     * Implements service InsFormaPago
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insFormaPagoImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsFormaPagoRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioInsFormaPagopthisDominio = request.getDominioInsFormaPagopthisDominioInstance();
        FormaPago dominioInsFormaPagopevcFormaPago = request.getDominioInsFormaPagopevcFormaPagoInstance();

        request.checkArguments();
        Dominio instance = dominioInsFormaPagopthisDominio;

        instance.getFormasPago().add(dominioInsFormaPagopevcFormaPago);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.formapago.siu.InsFormaPagoRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.formapago.siu.InsFormaPagoRequest();
            relatedRequest.setFormaPagoInsFormaPagopthisFormaPagoInstance(dominioInsFormaPagopevcFormaPago);
            relatedRequest.setFormaPagoInsFormaPagopevcDominioInstance(dominioInsFormaPagopthisDominio);
            formaPagoSrv.insFormaPagoImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: DelFormaPago

    /*
     * Entry point for service DelFormaPago
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delFormaPago(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelFormaPagoRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.delFormaPagoTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelFormaPago
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delFormaPagoTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelFormaPagoRequest request) throws ModelException {
        request.setDominioDelFormaPagopthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioDelFormaPagopthisDominio()));
        request.setDominioDelFormaPagopevcFormaPagoInstance(formaPagoSrv.getByOIDWithHV(request.getDominioDelFormaPagopevcFormaPago()));
        return dominioSrv.delFormaPagoImplementation(request, true);
    }

    /*
     * Implements service DelFormaPago
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delFormaPagoImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelFormaPagoRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioDelFormaPagopthisDominio = request.getDominioDelFormaPagopthisDominioInstance();
        FormaPago dominioDelFormaPagopevcFormaPago = request.getDominioDelFormaPagopevcFormaPagoInstance();

        request.checkArguments();
        Dominio instance = dominioDelFormaPagopthisDominio;

        instance.getFormasPago().remove(dominioDelFormaPagopevcFormaPago);
        dominioDelFormaPagopevcFormaPago.getDominios().remove(instance);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.formapago.siu.DelFormaPagoRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.formapago.siu.DelFormaPagoRequest();
            relatedRequest.setFormaPagoDelFormaPagopthisFormaPagoInstance(dominioDelFormaPagopevcFormaPago);
            relatedRequest.setFormaPagoDelFormaPagopevcDominioInstance(dominioDelFormaPagopthisDominio);
            formaPagoSrv.delFormaPagoImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: cambiarDiasVigencia

    /*
     * Entry point for service cambiarDiasVigencia
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse cambiarDiasVigencia(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CambiarDiasVigenciaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.cambiarDiasVigenciaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service cambiarDiasVigencia
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse cambiarDiasVigenciaTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CambiarDiasVigenciaRequest request) throws ModelException {
        request.setDominiocambiarDiasVigenciapthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominiocambiarDiasVigenciapthisDominio()));
        return dominioSrv.cambiarDiasVigenciaImplementation(request);
    }

    /*
     * Implements service cambiarDiasVigencia
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse cambiarDiasVigenciaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CambiarDiasVigenciaRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominiocambiarDiasVigenciapthisDominio = request.getDominiocambiarDiasVigenciapthisDominioInstance();
        Long dominiocambiarDiasVigenciapIniVigencia = request.getDominiocambiarDiasVigenciapIniVigencia();
        Long dominiocambiarDiasVigenciapFinVigencia = request.getDominiocambiarDiasVigenciapFinVigencia();
        Long dominiocambiarDiasVigenciapCaducidad = request.getDominiocambiarDiasVigenciapCaducidad();

        request.checkArguments();

        cambiarDiasVigenciaPreconditions(request);
        Dominio instance = dominiocambiarDiasVigenciapthisDominio;
        instance.setDominioDiaIniVigencia(dominiocambiarDiasVigenciapIniVigencia);
        instance.setDominioDiaFinVigencia(dominiocambiarDiasVigenciapFinVigencia);
        instance.setDominioDiasCaducidad(dominiocambiarDiasVigenciapCaducidad);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    /*
     * Checks service cambiarDiasVigencia preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse cambiarDiasVigenciaCheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CambiarDiasVigenciaRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setDominiocambiarDiasVigenciapthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominiocambiarDiasVigenciapthisDominio()));
        try {
            if("Clas_1431701422080423Pre_5".equals(request.getPreconditionId()))
                checkClas1431701422080423Pre5(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void cambiarDiasVigenciaPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CambiarDiasVigenciaRequest request) 
            throws ModelException {
        checkClas1431701422080423Pre5(request);
    }

    private void checkClas1431701422080423Pre5 (
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CambiarDiasVigenciaRequest request) 
            throws ModelException {
        // PlanT.EsVoluntario = true

        Dominio dominiocambiarDiasVigenciapthisDominio = request.getDominiocambiarDiasVigenciapthisDominioInstance();
        boolean preconditionHolds = dominiocambiarDiasVigenciapthisDominio.getPlanT().getPlanesEsVoluntario().equals(true);
        if (!preconditionHolds) throw new PreconditionException("Los días de vigencia solo están permitidos para los planes voluntarios");
    }

    // Service: InsPlantilla

    /*
     * Entry point for service InsPlantilla
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insPlantilla(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsPlantillaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.insPlantillaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsPlantilla
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insPlantillaTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsPlantillaRequest request) throws ModelException {
        request.setDominioInsPlantillapthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioInsPlantillapthisDominio()));
        request.setDominioInsPlantillapevcPlantillaEmailInstance(plantillaEmailSrv.getByOIDWithHV(request.getDominioInsPlantillapevcPlantillaEmail()));
        return dominioSrv.insPlantillaImplementation(request, true);
    }

    /*
     * Implements service InsPlantilla
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insPlantillaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsPlantillaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioInsPlantillapthisDominio = request.getDominioInsPlantillapthisDominioInstance();
        PlantillaEmail dominioInsPlantillapevcPlantillaEmail = request.getDominioInsPlantillapevcPlantillaEmailInstance();

        request.checkArguments();
        Dominio instance = dominioInsPlantillapthisDominio;

        instance.getPlantillas().add(dominioInsPlantillapevcPlantillaEmail);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu.InsPlantillaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu.InsPlantillaRequest();
            relatedRequest.setPlantillaEmailInsPlantillapthisPlantillaEmailInstance(dominioInsPlantillapevcPlantillaEmail);
            relatedRequest.setPlantillaEmailInsPlantillapevcDominioInstance(dominioInsPlantillapthisDominio);
            plantillaEmailSrv.insPlantillaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: DelPlantilla

    /*
     * Entry point for service DelPlantilla
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delPlantilla(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelPlantillaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.delPlantillaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelPlantilla
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delPlantillaTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelPlantillaRequest request) throws ModelException {
        request.setDominioDelPlantillapthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioDelPlantillapthisDominio()));
        request.setDominioDelPlantillapevcPlantillaEmailInstance(plantillaEmailSrv.getByOIDWithHV(request.getDominioDelPlantillapevcPlantillaEmail()));
        return dominioSrv.delPlantillaImplementation(request, true);
    }

    /*
     * Implements service DelPlantilla
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delPlantillaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelPlantillaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioDelPlantillapthisDominio = request.getDominioDelPlantillapthisDominioInstance();
        PlantillaEmail dominioDelPlantillapevcPlantillaEmail = request.getDominioDelPlantillapevcPlantillaEmailInstance();

        request.checkArguments();
        Dominio instance = dominioDelPlantillapthisDominio;

        instance.getPlantillas().remove(dominioDelPlantillapevcPlantillaEmail);
        dominioDelPlantillapevcPlantillaEmail.getDominios().remove(instance);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu.DelPlantillaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu.DelPlantillaRequest();
            relatedRequest.setPlantillaEmailDelPlantillapthisPlantillaEmailInstance(dominioDelPlantillapevcPlantillaEmail);
            relatedRequest.setPlantillaEmailDelPlantillapevcDominioInstance(dominioDelPlantillapthisDominio);
            plantillaEmailSrv.delPlantillaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: InsNovedad

    /*
     * Entry point for service InsNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insNovedad(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.insNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insNovedadTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsNovedadRequest request) throws ModelException {
        request.setDominioInsNovedadpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioInsNovedadpthisDominio()));
        request.setDominioInsNovedadpevcNovedadInstance(novedadSrv.getByOIDWithHV(request.getDominioInsNovedadpevcNovedad()));
        return dominioSrv.insNovedadImplementation(request, true);
    }

    /*
     * Implements service InsNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insNovedadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioInsNovedadpthisDominio = request.getDominioInsNovedadpthisDominioInstance();
        Novedad dominioInsNovedadpevcNovedad = request.getDominioInsNovedadpevcNovedadInstance();

        request.checkArguments();
        Dominio instance = dominioInsNovedadpthisDominio;

        instance.getNovedades().add(dominioInsNovedadpevcNovedad);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.novedad.siu.InsNovedadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.novedad.siu.InsNovedadRequest();
            relatedRequest.setNovedadInsNovedadpthisNovedadInstance(dominioInsNovedadpevcNovedad);
            relatedRequest.setNovedadInsNovedadpevcDominioInstance(dominioInsNovedadpthisDominio);
            novedadSrv.insNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: DelNovedad

    /*
     * Entry point for service DelNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delNovedad(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.delNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delNovedadTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelNovedadRequest request) throws ModelException {
        request.setDominioDelNovedadpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioDelNovedadpthisDominio()));
        request.setDominioDelNovedadpevcNovedadInstance(novedadSrv.getByOIDWithHV(request.getDominioDelNovedadpevcNovedad()));
        return dominioSrv.delNovedadImplementation(request, true);
    }

    /*
     * Implements service DelNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delNovedadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioDelNovedadpthisDominio = request.getDominioDelNovedadpthisDominioInstance();
        Novedad dominioDelNovedadpevcNovedad = request.getDominioDelNovedadpevcNovedadInstance();

        request.checkArguments();
        Dominio instance = dominioDelNovedadpthisDominio;

        instance.getNovedades().remove(dominioDelNovedadpevcNovedad);
        dominioDelNovedadpevcNovedad.getDominios().remove(instance);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.novedad.siu.DelNovedadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.novedad.siu.DelNovedadRequest();
            relatedRequest.setNovedadDelNovedadpthisNovedadInstance(dominioDelNovedadpevcNovedad);
            relatedRequest.setNovedadDelNovedadpevcDominioInstance(dominioDelNovedadpthisDominio);
            novedadSrv.delNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: modificarConfSolNovedad

    /*
     * Entry point for service modificarConfSolNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse modificarConfSolNovedad(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModificarConfSolNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.modificarConfSolNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service modificarConfSolNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse modificarConfSolNovedadTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModificarConfSolNovedadRequest request) throws ModelException {
        request.setDominiomodificarConfSolNovedadpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominiomodificarConfSolNovedadpthisDominio()));
        return dominioSrv.modificarConfSolNovedadImplementation(request);
    }

    /*
     * Implements service modificarConfSolNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse modificarConfSolNovedadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModificarConfSolNovedadRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominiomodificarConfSolNovedadpthisDominio = request.getDominiomodificarConfSolNovedadpthisDominioInstance();
        Boolean dominiomodificarConfSolNovedadpNovExcepcionReqDoc = request.getDominiomodificarConfSolNovedadpNovExcepcionReqDoc();
        String dominiomodificarConfSolNovedadpSolNovColorOscuro = request.getDominiomodificarConfSolNovedadpSolNovColorOscuro();
        String dominiomodificarConfSolNovedadpSolNovColorClaro = request.getDominiomodificarConfSolNovedadpSolNovColorClaro();
        String dominiomodificarConfSolNovedadpSolNovLogo = request.getDominiomodificarConfSolNovedadpSolNovLogo();

        request.checkArguments();
        Dominio instance = dominiomodificarConfSolNovedadpthisDominio;
        instance.setDominioNovExcepcionReqDoc(dominiomodificarConfSolNovedadpNovExcepcionReqDoc);
        instance.setDominioSolNovColorOscuro(dominiomodificarConfSolNovedadpSolNovColorOscuro);
        instance.setDominioSolNovColorClaro(dominiomodificarConfSolNovedadpSolNovColorClaro);
        instance.setDominioSolNovLogo(dominiomodificarConfSolNovedadpSolNovLogo);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: InsGrupoAsesores

    /*
     * Entry point for service InsGrupoAsesores
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insGrupoAsesores(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsGrupoAsesoresRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.insGrupoAsesoresTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsGrupoAsesores
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insGrupoAsesoresTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsGrupoAsesoresRequest request) throws ModelException {
        request.setDominioInsGrupoAsesorespthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioInsGrupoAsesorespthisDominio()));
        request.setDominioInsGrupoAsesorespevcGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getDominioInsGrupoAsesorespevcGrupoAsignacion()));
        return dominioSrv.insGrupoAsesoresImplementation(request, true);
    }

    /*
     * Implements service InsGrupoAsesores
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insGrupoAsesoresImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsGrupoAsesoresRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioInsGrupoAsesorespthisDominio = request.getDominioInsGrupoAsesorespthisDominioInstance();
        GrupoAsignacion dominioInsGrupoAsesorespevcGrupoAsignacion = request.getDominioInsGrupoAsesorespevcGrupoAsignacionInstance();

        request.checkArguments();
        Dominio instance = dominioInsGrupoAsesorespthisDominio;

        instance.add2GrupoAsesores(dominioInsGrupoAsesorespevcGrupoAsignacion);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.InsGrupoAsesoresRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.InsGrupoAsesoresRequest();
            relatedRequest.setGrupoAsignacionInsGrupoAsesorespthisGrupoAsignacionInstance(dominioInsGrupoAsesorespevcGrupoAsignacion);
            relatedRequest.setGrupoAsignacionInsGrupoAsesorespevcDominioInstance(dominioInsGrupoAsesorespthisDominio);
            grupoAsignacionSrv.insGrupoAsesoresImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: DelGrupoAsesores

    /*
     * Entry point for service DelGrupoAsesores
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delGrupoAsesores(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelGrupoAsesoresRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.delGrupoAsesoresTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelGrupoAsesores
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delGrupoAsesoresTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelGrupoAsesoresRequest request) throws ModelException {
        request.setDominioDelGrupoAsesorespthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioDelGrupoAsesorespthisDominio()));
        request.setDominioDelGrupoAsesorespevcGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getDominioDelGrupoAsesorespevcGrupoAsignacion()));
        return dominioSrv.delGrupoAsesoresImplementation(request, true);
    }

    /*
     * Implements service DelGrupoAsesores
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delGrupoAsesoresImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelGrupoAsesoresRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioDelGrupoAsesorespthisDominio = request.getDominioDelGrupoAsesorespthisDominioInstance();
        GrupoAsignacion dominioDelGrupoAsesorespevcGrupoAsignacion = request.getDominioDelGrupoAsesorespevcGrupoAsignacionInstance();

        request.checkArguments();
        Dominio instance = dominioDelGrupoAsesorespthisDominio;

        instance.add2GrupoAsesores(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelGrupoAsesoresRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelGrupoAsesoresRequest();
            relatedRequest.setGrupoAsignacionDelGrupoAsesorespthisGrupoAsignacionInstance(dominioDelGrupoAsesorespevcGrupoAsignacion);
            relatedRequest.setGrupoAsignacionDelGrupoAsesorespevcDominioInstance(dominioDelGrupoAsesorespthisDominio);
            grupoAsignacionSrv.delGrupoAsesoresImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: InsGrupoAreaMedica

    /*
     * Entry point for service InsGrupoAreaMedica
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insGrupoAreaMedica(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsGrupoAreaMedicaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.insGrupoAreaMedicaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsGrupoAreaMedica
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insGrupoAreaMedicaTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsGrupoAreaMedicaRequest request) throws ModelException {
        request.setDominioInsGrupoAreaMedicapthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioInsGrupoAreaMedicapthisDominio()));
        request.setDominioInsGrupoAreaMedicapevcGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getDominioInsGrupoAreaMedicapevcGrupoAsignacion()));
        return dominioSrv.insGrupoAreaMedicaImplementation(request, true);
    }

    /*
     * Implements service InsGrupoAreaMedica
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insGrupoAreaMedicaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsGrupoAreaMedicaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioInsGrupoAreaMedicapthisDominio = request.getDominioInsGrupoAreaMedicapthisDominioInstance();
        GrupoAsignacion dominioInsGrupoAreaMedicapevcGrupoAsignacion = request.getDominioInsGrupoAreaMedicapevcGrupoAsignacionInstance();

        request.checkArguments();
        Dominio instance = dominioInsGrupoAreaMedicapthisDominio;

        instance.add2GrupoAreaMedica(dominioInsGrupoAreaMedicapevcGrupoAsignacion);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.InsGrupoAreaMedicaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.InsGrupoAreaMedicaRequest();
            relatedRequest.setGrupoAsignacionInsGrupoAreaMedicapthisGrupoAsignacionInstance(dominioInsGrupoAreaMedicapevcGrupoAsignacion);
            relatedRequest.setGrupoAsignacionInsGrupoAreaMedicapevcDominioInstance(dominioInsGrupoAreaMedicapthisDominio);
            grupoAsignacionSrv.insGrupoAreaMedicaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: DelGrupoAreaMedica

    /*
     * Entry point for service DelGrupoAreaMedica
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delGrupoAreaMedica(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelGrupoAreaMedicaRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.delGrupoAreaMedicaTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelGrupoAreaMedica
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delGrupoAreaMedicaTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelGrupoAreaMedicaRequest request) throws ModelException {
        request.setDominioDelGrupoAreaMedicapthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioDelGrupoAreaMedicapthisDominio()));
        request.setDominioDelGrupoAreaMedicapevcGrupoAsignacionInstance(grupoAsignacionSrv.getByOIDWithHV(request.getDominioDelGrupoAreaMedicapevcGrupoAsignacion()));
        return dominioSrv.delGrupoAreaMedicaImplementation(request, true);
    }

    /*
     * Implements service DelGrupoAreaMedica
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delGrupoAreaMedicaImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelGrupoAreaMedicaRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioDelGrupoAreaMedicapthisDominio = request.getDominioDelGrupoAreaMedicapthisDominioInstance();
        GrupoAsignacion dominioDelGrupoAreaMedicapevcGrupoAsignacion = request.getDominioDelGrupoAreaMedicapevcGrupoAsignacionInstance();

        request.checkArguments();
        Dominio instance = dominioDelGrupoAreaMedicapthisDominio;

        instance.add2GrupoAreaMedica(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelGrupoAreaMedicaRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.grupoasignacion.siu.DelGrupoAreaMedicaRequest();
            relatedRequest.setGrupoAsignacionDelGrupoAreaMedicapthisGrupoAsignacionInstance(dominioDelGrupoAreaMedicapevcGrupoAsignacion);
            relatedRequest.setGrupoAsignacionDelGrupoAreaMedicapevcDominioInstance(dominioDelGrupoAreaMedicapthisDominio);
            grupoAsignacionSrv.delGrupoAreaMedicaImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: InsDominio

    /*
     * Entry point for service InsDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insDominio(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsDominioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.insDominioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insDominioTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsDominioRequest request) throws ModelException {
        request.setDominioInsDominiopthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioInsDominiopthisDominio()));
        request.setDominioInsDominiopevcAgentexProductoInstance(agentexProductoSrv.getByOIDWithHV(request.getDominioInsDominiopevcAgentexProducto()));
        return dominioSrv.insDominioImplementation(request, true);
    }

    /*
     * Implements service InsDominio
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insDominioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsDominioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioInsDominiopthisDominio = request.getDominioInsDominiopthisDominioInstance();
        AgentexProducto dominioInsDominiopevcAgentexProducto = request.getDominioInsDominiopevcAgentexProductoInstance();

        request.checkArguments();
        Dominio instance = dominioInsDominiopthisDominio;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.agentexproducto.siu.InsDominioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.agentexproducto.siu.InsDominioRequest();
            relatedRequest.setAgentexProductoInsDominiopthisAgentexProductoInstance(dominioInsDominiopevcAgentexProducto);
            relatedRequest.setAgentexProductoInsDominiopevcDominioInstance(dominioInsDominiopthisDominio);
            agentexProductoSrv.insDominioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: DelDominio

    /*
     * Entry point for service DelDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delDominio(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelDominioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.delDominioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelDominio
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delDominioTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelDominioRequest request) throws ModelException {
        request.setDominioDelDominiopthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioDelDominiopthisDominio()));
        request.setDominioDelDominiopevcAgentexProductoInstance(agentexProductoSrv.getByOIDWithHV(request.getDominioDelDominiopevcAgentexProducto()));
        return dominioSrv.delDominioImplementation(request, true);
    }

    /*
     * Implements service DelDominio
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delDominioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelDominioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioDelDominiopthisDominio = request.getDominioDelDominiopthisDominioInstance();
        AgentexProducto dominioDelDominiopevcAgentexProducto = request.getDominioDelDominiopevcAgentexProductoInstance();

        request.checkArguments();
        Dominio instance = dominioDelDominiopthisDominio;

        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.agentexproducto.siu.DelDominioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.agentexproducto.siu.DelDominioRequest();
            relatedRequest.setAgentexProductoDelDominiopthisAgentexProductoInstance(dominioDelDominiopevcAgentexProducto);
            relatedRequest.setAgentexProductoDelDominiopevcDominioInstance(dominioDelDominiopthisDominio);
            agentexProductoSrv.delDominioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: InsIncompatible

    /*
     * Entry point for service InsIncompatible
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insIncompatible(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsIncompatibleRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.insIncompatibleTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsIncompatible
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insIncompatibleTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsIncompatibleRequest request) throws ModelException {
        request.setDominioInsIncompatiblepthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioInsIncompatiblepthisDominio()));
        request.setDominioInsIncompatiblepevcDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioInsIncompatiblepevcDominio()));
        return dominioSrv.insIncompatibleImplementation(request, true);
    }

    /*
     * Implements service InsIncompatible
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insIncompatibleImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsIncompatibleRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioInsIncompatiblepthisDominio = request.getDominioInsIncompatiblepthisDominioInstance();
        Dominio dominioInsIncompatiblepevcDominio = request.getDominioInsIncompatiblepevcDominioInstance();

        request.checkArguments();

        insIncompatiblePreconditions(request);
        Dominio instance = dominioInsIncompatiblepthisDominio;

        instance.getDominiosIncompatibles().add(dominioInsIncompatiblepevcDominio);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    private void insIncompatiblePreconditions(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsIncompatibleRequest request) 
            throws ModelException {
        checkClas1431701422080423Pre10(request);
        checkClas1431701422080423Pre11(request);
    }

    private void checkClas1431701422080423Pre10 (
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsIncompatibleRequest request) 
            throws ModelException {
        // pevcDominio.Numero <> Numero

        Dominio dominioInsIncompatiblepevcDominio = request.getDominioInsIncompatiblepevcDominioInstance();
        Dominio dominioInsIncompatiblepthisDominio = request.getDominioInsIncompatiblepthisDominioInstance();
        boolean preconditionHolds = !(dominioInsIncompatiblepevcDominio.getDominioNumero()).equals(dominioInsIncompatiblepthisDominio.getDominioNumero());
        if (!preconditionHolds) throw new PreconditionException("Asociación no válida. todo Dominio es incompatible consigo mismo");
    }

    private void checkClas1431701422080423Pre11 (
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsIncompatibleRequest request) 
            throws ModelException {
        // COUNT(DominiosIncompatibles) WHERE (DominiosIncompatibles = pevcDominio) = 0

        Dominio dominioInsIncompatiblepevcDominio = request.getDominioInsIncompatiblepevcDominioInstance();
        Dominio dominioInsIncompatiblepthisDominio = request.getDominioInsIncompatiblepthisDominioInstance();
        try {
            boolean preconditionHolds = (repository.assocOperator005(dominioInsIncompatiblepthisDominio, dominioInsIncompatiblepevcDominio)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Asociación inválida. El Dominio ya es incompatible");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    // Service: DelIncompatible

    /*
     * Entry point for service DelIncompatible
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delIncompatible(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelIncompatibleRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.delIncompatibleTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelIncompatible
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delIncompatibleTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelIncompatibleRequest request) throws ModelException {
        request.setDominioDelIncompatiblepthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioDelIncompatiblepthisDominio()));
        request.setDominioDelIncompatiblepevcDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioDelIncompatiblepevcDominio()));
        return dominioSrv.delIncompatibleImplementation(request, true);
    }

    /*
     * Implements service DelIncompatible
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delIncompatibleImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelIncompatibleRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioDelIncompatiblepthisDominio = request.getDominioDelIncompatiblepthisDominioInstance();
        Dominio dominioDelIncompatiblepevcDominio = request.getDominioDelIncompatiblepevcDominioInstance();

        request.checkArguments();
        Dominio instance = dominioDelIncompatiblepthisDominio;

        instance.getDominiosIncompatibles().remove(dominioDelIncompatiblepevcDominio);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

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
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModificarRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.modificarTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModificarRequest request) throws ModelException {
        request.setDominiomodificarpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominiomodificarpthisDominio()));
        return dominioSrv.modificarImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModificarRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominiomodificarpthisDominio = request.getDominiomodificarpthisDominioInstance();
        Boolean dominiomodificarpReqCuesMed = request.getDominiomodificarpReqCuesMed();
        Boolean dominiomodificarpNoCuesMedTras = request.getDominiomodificarpNoCuesMedTras();
        Boolean dominiomodificarpNuevaAfiliacion = request.getDominiomodificarpNuevaAfiliacion();
        Boolean dominiomodificarpInclusion = request.getDominiomodificarpInclusion();
        Boolean dominiomodificarpAcepCuesMat = request.getDominiomodificarpAcepCuesMat();
        Boolean dominiomodificarpAcepCuesNeo = request.getDominiomodificarpAcepCuesNeo();
        Boolean dominiomodificarpAcepBBGestante = request.getDominiomodificarpAcepBBGestante();
        Boolean dominiomodificarpMostrarPreexAnexos = request.getDominiomodificarpMostrarPreexAnexos();
        Boolean dominiomodificarpMostrarPreexContrato = request.getDominiomodificarpMostrarPreexContrato();
        Boolean dominiomodificarpIgnorarPreexistencias = request.getDominiomodificarpIgnorarPreexistencias();
        Boolean dominiomodificarpProrrateoUsuExistente = request.getDominiomodificarpProrrateoUsuExistente();
        Boolean dominiomodificarpInclusionModGrupoAsoc = request.getDominiomodificarpInclusionModGrupoAsoc();
        Boolean dominiomodificarpPermiteRechazadosAfi = request.getDominiomodificarpPermiteRechazadosAfi();
        Boolean dominiomodificarpAcepPolizaInter = request.getDominiomodificarpAcepPolizaInter();
        Boolean dominiomodificarpTramiteEnLinea = request.getDominiomodificarpTramiteEnLinea();
        Boolean dominiomodificarpeVigenciaRadicacion = request.getDominiomodificarpeVigenciaRadicacion();

        request.checkArguments();
        Dominio instance = dominiomodificarpthisDominio;
        instance.setDominioReqCuesMed(dominiomodificarpReqCuesMed);
        instance.setDominioNoCuesMedTras(dominiomodificarpNoCuesMedTras);
        instance.setDominioNuevaAfiliacion(dominiomodificarpNuevaAfiliacion);
        instance.setDominioInclusion(dominiomodificarpInclusion);
        instance.setDominioAcepCuesMat(dominiomodificarpAcepCuesMat);
        instance.setDominioAcepCuesNeo(dominiomodificarpAcepCuesNeo);
        instance.setDominioAcepBBGestante(dominiomodificarpAcepBBGestante);
        instance.setDominioMostrarPreexAnexos(dominiomodificarpMostrarPreexAnexos);
        instance.setDominioMostrarPreexContrato(dominiomodificarpMostrarPreexContrato);
        instance.setDominioIgnorarPreexistencias(dominiomodificarpIgnorarPreexistencias);
        instance.setDominioProrrateoUsuExistente(dominiomodificarpProrrateoUsuExistente);
        instance.setDominioInclusionModGrupoAsoc(dominiomodificarpInclusionModGrupoAsoc);
        instance.setDominioPermiteRechazadosAfi(dominiomodificarpPermiteRechazadosAfi);
        instance.setDominioAcepPolizaInter(dominiomodificarpAcepPolizaInter);
        instance.setDominioTramiteEnLinea(dominiomodificarpTramiteEnLinea);
        instance.setDominioVigenciaRadicacion(dominiomodificarpeVigenciaRadicacion);


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICDominio());

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
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TELIMINARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.tELIMINARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TELIMINARRequest request) throws ModelException, SystemException {
        request.setDominioTELIMINARpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioTELIMINARpthisDominio()));
        return dominioSrv.tELIMINARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TELIMINARRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioTELIMINARpthisDominio = request.getDominioTELIMINARpthisDominioInstance();

        request.checkArguments();
        Dominio instance = dominioTELIMINARpthisDominio;

        for(ParentescoDom step1Entity : repository.assocOperator006(dominioTELIMINARpthisDominio)) {
            com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.EliminarRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.EliminarRequest();
            step1Request.setParentescoDomeliminarpthisParentescoDomInstance(step1Entity);
            parentescoDomSrv.eliminarImplementation(step1Request);
        }

        for(Periodicidad step2Entity : repository.assocOperator007(dominioTELIMINARpthisDominio, dominioTELIMINARpthisDominio)) {
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelPeriodicidadDominioRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelPeriodicidadDominioRequest();
            step2Request.setDominioDelPeriodicidadDominiopthisDominioInstance(dominioTELIMINARpthisDominio);
            step2Request.setDominioDelPeriodicidadDominiopevcPeriodicidadInstance(step2Entity);
            dominioSrv.delPeriodicidadDominioImplementation(step2Request, true);
        }

        for(Rol step3Entity : repository.assocOperator008(dominioTELIMINARpthisDominio, dominioTELIMINARpthisDominio)) {
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelRollDominioRequest step3Request = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelRollDominioRequest();
            step3Request.setDominioDelRollDominiopthisDominioInstance(dominioTELIMINARpthisDominio);
            step3Request.setDominioDelRollDominiopevcRolInstance(step3Entity);
            dominioSrv.delRollDominioImplementation(step3Request, true);
        }

        for(Prorrateo step4Entity : repository.assocOperator009(dominioTELIMINARpthisDominio)) {
            com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.EliminarRequest step4Request = new com.keralty.aeconfiguraciones.viewmodel.prorrateo.siu.EliminarRequest();
            step4Request.setProrrateoeliminarpthisProrrateoInstance(step4Entity);
            prorrateoSrv.eliminarImplementation(step4Request);
        }

        for(LogDatosDominio step5Entity : repository.assocOperator010(dominioTELIMINARpthisDominio)) {
            com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.EliminarRequest step5Request = new com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.EliminarRequest();
            step5Request.setLogDatosDominioeliminarpthisLogDatosDominioInstance(step5Entity);
            logDatosDominioSrv.eliminarImplementation(step5Request);
        }

        for(LogGruposUsuarios step6Entity : repository.assocOperator011(dominioTELIMINARpthisDominio)) {
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.EliminarRequest step6Request = new com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.EliminarRequest();
            step6Request.setLogGruposUsuarioseliminarpthisLogGruposUsuariosInstance(step6Entity);
            logGruposUsuariosSrv.eliminarImplementation(step6Request);
        }

        for(LogIncompatibilidades step7Entity : repository.assocOperator012(dominioTELIMINARpthisDominio)) {
            com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.EliminarRequest step7Request = new com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.EliminarRequest();
            step7Request.setLogIncompatibilidadeseliminarpthisLogIncompatibilidadesInstance(step7Entity);
            logIncompatibilidadesSrv.eliminarImplementation(step7Request);
        }

        com.keralty.aeconfiguraciones.viewmodel.dominio.siu.EliminarRequest step8Request = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.EliminarRequest();
        step8Request.setDominioeliminarpthisDominioInstance(dominioTELIMINARpthisDominio);
        dominioSrv.eliminarImplementation(step8Request);

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: TINSPLANTILLA

    /*
     * Entry point for service TINSPLANTILLA
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tINSPLANTILLA(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TINSPLANTILLARequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.tINSPLANTILLATxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TINSPLANTILLA
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tINSPLANTILLATxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TINSPLANTILLARequest request) throws ModelException, SystemException {
        request.setDominioTINSPLANTILLApthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioTINSPLANTILLApthisDominio()));
        request.setDominioTINSPLANTILLAptPlantillaInstance(plantillaEmailSrv.getByOIDWithHV(request.getDominioTINSPLANTILLAptPlantilla()));
        return dominioSrv.tINSPLANTILLAImplementation(request);
    }

    /*
     * Implements service TINSPLANTILLA
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tINSPLANTILLAImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TINSPLANTILLARequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        PlantillaEmail dominioTINSPLANTILLAptPlantilla = request.getDominioTINSPLANTILLAptPlantillaInstance();
        Dominio dominioTINSPLANTILLApthisDominio = request.getDominioTINSPLANTILLApthisDominioInstance();

        request.checkArguments();
        Dominio instance = dominioTINSPLANTILLApthisDominio;

        for(PlantillaEmail step1Entity : repository.assocOperator013(dominioTINSPLANTILLApthisDominio, dominioTINSPLANTILLApthisDominio, dominioTINSPLANTILLAptPlantilla)) {
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelPlantillaRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelPlantillaRequest();
            step1Request.setDominioDelPlantillapthisDominioInstance(dominioTINSPLANTILLApthisDominio);
            step1Request.setDominioDelPlantillapevcPlantillaEmailInstance(step1Entity);
            dominioSrv.delPlantillaImplementation(step1Request, true);
        }

        com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsPlantillaRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsPlantillaRequest();
        step2Request.setDominioInsPlantillapthisDominioInstance(dominioTINSPLANTILLApthisDominio);
        step2Request.setDominioInsPlantillapevcPlantillaEmailInstance(dominioTINSPLANTILLAptPlantilla);
        dominioSrv.insPlantillaImplementation(step2Request, true);

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: TINSNOVEDAD

    /*
     * Entry point for service TINSNOVEDAD
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tINSNOVEDAD(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TINSNOVEDADRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.tINSNOVEDADTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TINSNOVEDAD
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tINSNOVEDADTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TINSNOVEDADRequest request) throws ModelException {
        request.setDominioTINSNOVEDADpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioTINSNOVEDADpthisDominio()));
        request.setDominioTINSNOVEDADptpevcNovedadInstance(novedadSrv.getByOIDWithHV(new NovedadOid().fromJsonString(request.getDominioTINSNOVEDADptpevcNovedad())));
        request.setDominioTINSNOVEDADptTipoInstance(tipoNovedadSrv.getByOIDWithHV(new TipoNovedadOid().fromJsonString(request.getDominioTINSNOVEDADptTipo())));
        return dominioSrv.tINSNOVEDADImplementation(request);
    }

    /*
     * Implements service TINSNOVEDAD
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tINSNOVEDADImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TINSNOVEDADRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Novedad dominioTINSNOVEDADptpevcNovedad = request.getDominioTINSNOVEDADptpevcNovedadInstance();
        Dominio dominioTINSNOVEDADpthisDominio = request.getDominioTINSNOVEDADpthisDominioInstance();

        request.checkArguments();
        Dominio instance = dominioTINSNOVEDADpthisDominio;

        com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsNovedadRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsNovedadRequest();
        step1Request.setDominioInsNovedadpthisDominioInstance(dominioTINSNOVEDADpthisDominio);
        step1Request.setDominioInsNovedadpevcNovedadInstance(dominioTINSNOVEDADptpevcNovedad);
        dominioSrv.insNovedadImplementation(step1Request, true);

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: TADDGRPASESORES

    /*
     * Entry point for service TADDGRPASESORES
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tADDGRPASESORES(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDGRPASESORESRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.tADDGRPASESORESTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TADDGRPASESORES
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tADDGRPASESORESTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDGRPASESORESRequest request) throws ModelException, SystemException {
        request.setDominioTADDGRPASESORESpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioTADDGRPASESORESpthisDominio()));
        request.setDominioTADDGRPASESORESptGrupoInstance(grupoAsignacionSrv.getByOIDWithHV(request.getDominioTADDGRPASESORESptGrupo()));
        return dominioSrv.tADDGRPASESORESImplementation(request);
    }

    /*
     * Implements service TADDGRPASESORES
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tADDGRPASESORESImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDGRPASESORESRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion dominioTADDGRPASESORESptGrupo = request.getDominioTADDGRPASESORESptGrupoInstance();
        Dominio dominioTADDGRPASESORESpthisDominio = request.getDominioTADDGRPASESORESpthisDominioInstance();

        request.checkArguments();
        Dominio instance = dominioTADDGRPASESORESpthisDominio;

        /* Action 1: IF (GrupoAsesores <> ptGrupo) */
        boolean ifCondition1 = !(dominioTADDGRPASESORESpthisDominio.getGrupoAsesores().equalsObject(dominioTADDGRPASESORESptGrupo));
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELGRPASESORESRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELGRPASESORESRequest();
            step10Request.setDominioTDELGRPASESORESpthisDominioInstance(dominioTADDGRPASESORESpthisDominio);
            dominioSrv.tDELGRPASESORESImplementation(step10Request);
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsGrupoAsesoresRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsGrupoAsesoresRequest();
            step11Request.setDominioInsGrupoAsesorespthisDominioInstance(dominioTADDGRPASESORESpthisDominio);
            step11Request.setDominioInsGrupoAsesorespevcGrupoAsignacionInstance(dominioTADDGRPASESORESptGrupo);
            dominioSrv.insGrupoAsesoresImplementation(step11Request, true);
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.TCREARRequest step12Request = new com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.TCREARRequest();
            step12Request.setLogGruposUsuariosTCREARptpagrDominioInstance(dominioTADDGRPASESORESpthisDominio);
            step12Request.setLogGruposUsuariosTCREARptpatrTipoGrupo(dominioTADDGRPASESORESpthisDominio.getGrupoAsesores().getGrupoAsignacionTipo());
            step12Request.setLogGruposUsuariosTCREARptpatrAccion("Agregar grupo Asesores/Afiliaciones");
            step12Request.setLogGruposUsuariosTCREARptpatrGrupo(dominioTADDGRPASESORESpthisDominio.getGrupoAsesores().getGrupoAsignacionNombre());
            logGruposUsuariosSrv.tCREARImplementation(step12Request);
        }

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: TDELGRPASESORES

    /*
     * Entry point for service TDELGRPASESORES
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tDELGRPASESORES(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELGRPASESORESRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.tDELGRPASESORESTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TDELGRPASESORES
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tDELGRPASESORESTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELGRPASESORESRequest request) throws ModelException, SystemException {
        request.setDominioTDELGRPASESORESpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioTDELGRPASESORESpthisDominio()));
        return dominioSrv.tDELGRPASESORESImplementation(request);
    }

    /*
     * Implements service TDELGRPASESORES
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tDELGRPASESORESImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELGRPASESORESRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioTDELGRPASESORESpthisDominio = request.getDominioTDELGRPASESORESpthisDominioInstance();

        request.checkArguments();
        Dominio instance = dominioTDELGRPASESORESpthisDominio;

        /* Action 1: IF (EXIST (GrupoAsesores) = true) */
        boolean ifCondition1 = repository.assocOperator015(dominioTDELGRPASESORESpthisDominio).equals(true);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.TCREARRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.TCREARRequest();
            step10Request.setLogGruposUsuariosTCREARptpagrDominioInstance(dominioTDELGRPASESORESpthisDominio);
            step10Request.setLogGruposUsuariosTCREARptpatrTipoGrupo(dominioTDELGRPASESORESpthisDominio.getGrupoAsesores().getGrupoAsignacionTipo());
            step10Request.setLogGruposUsuariosTCREARptpatrAccion("Eliminar grupo Asesores/Afiliaciones");
            step10Request.setLogGruposUsuariosTCREARptpatrGrupo(dominioTDELGRPASESORESpthisDominio.getGrupoAsesores().getGrupoAsignacionNombre());
            logGruposUsuariosSrv.tCREARImplementation(step10Request);
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelGrupoAsesoresRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelGrupoAsesoresRequest();
            step11Request.setDominioDelGrupoAsesorespthisDominioInstance(dominioTDELGRPASESORESpthisDominio);
            step11Request.setDominioDelGrupoAsesorespevcGrupoAsignacionInstance(dominioTDELGRPASESORESpthisDominio.getGrupoAsesores());
            dominioSrv.delGrupoAsesoresImplementation(step11Request, true);
        }

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: TADDGRPMEDICO

    /*
     * Entry point for service TADDGRPMEDICO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tADDGRPMEDICO(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDGRPMEDICORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.tADDGRPMEDICOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TADDGRPMEDICO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tADDGRPMEDICOTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDGRPMEDICORequest request) throws ModelException, SystemException {
        request.setDominioTADDGRPMEDICOpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioTADDGRPMEDICOpthisDominio()));
        request.setDominioTADDGRPMEDICOptGrupoInstance(grupoAsignacionSrv.getByOIDWithHV(request.getDominioTADDGRPMEDICOptGrupo()));
        return dominioSrv.tADDGRPMEDICOImplementation(request);
    }

    /*
     * Implements service TADDGRPMEDICO
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tADDGRPMEDICOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDGRPMEDICORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        GrupoAsignacion dominioTADDGRPMEDICOptGrupo = request.getDominioTADDGRPMEDICOptGrupoInstance();
        Dominio dominioTADDGRPMEDICOpthisDominio = request.getDominioTADDGRPMEDICOpthisDominioInstance();

        request.checkArguments();
        Dominio instance = dominioTADDGRPMEDICOpthisDominio;

        /* Action 1: IF (GrupoAreaMedica <> ptGrupo) */
        boolean ifCondition1 = !(dominioTADDGRPMEDICOpthisDominio.getGrupoAreaMedica().equalsObject(dominioTADDGRPMEDICOptGrupo));
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELGRPMEDICORequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELGRPMEDICORequest();
            step10Request.setDominioTDELGRPMEDICOpthisDominioInstance(dominioTADDGRPMEDICOpthisDominio);
            dominioSrv.tDELGRPMEDICOImplementation(step10Request);
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsGrupoAreaMedicaRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsGrupoAreaMedicaRequest();
            step11Request.setDominioInsGrupoAreaMedicapthisDominioInstance(dominioTADDGRPMEDICOpthisDominio);
            step11Request.setDominioInsGrupoAreaMedicapevcGrupoAsignacionInstance(dominioTADDGRPMEDICOptGrupo);
            dominioSrv.insGrupoAreaMedicaImplementation(step11Request, true);
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.TCREARRequest step12Request = new com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.TCREARRequest();
            step12Request.setLogGruposUsuariosTCREARptpagrDominioInstance(dominioTADDGRPMEDICOpthisDominio);
            step12Request.setLogGruposUsuariosTCREARptpatrTipoGrupo(dominioTADDGRPMEDICOpthisDominio.getGrupoAreaMedica().getGrupoAsignacionTipo());
            step12Request.setLogGruposUsuariosTCREARptpatrAccion("Agregar grupo Área Médica");
            step12Request.setLogGruposUsuariosTCREARptpatrGrupo(dominioTADDGRPMEDICOpthisDominio.getGrupoAreaMedica().getGrupoAsignacionNombre());
            logGruposUsuariosSrv.tCREARImplementation(step12Request);
        }

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: TDELGRPMEDICO

    /*
     * Entry point for service TDELGRPMEDICO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tDELGRPMEDICO(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELGRPMEDICORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.tDELGRPMEDICOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TDELGRPMEDICO
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tDELGRPMEDICOTxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELGRPMEDICORequest request) throws ModelException, SystemException {
        request.setDominioTDELGRPMEDICOpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioTDELGRPMEDICOpthisDominio()));
        return dominioSrv.tDELGRPMEDICOImplementation(request);
    }

    /*
     * Implements service TDELGRPMEDICO
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tDELGRPMEDICOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELGRPMEDICORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioTDELGRPMEDICOpthisDominio = request.getDominioTDELGRPMEDICOpthisDominioInstance();

        request.checkArguments();
        Dominio instance = dominioTDELGRPMEDICOpthisDominio;

        /* Action 1: IF (EXIST (GrupoAreaMedica) = true) */
        boolean ifCondition1 = repository.assocOperator017(dominioTDELGRPMEDICOpthisDominio).equals(true);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.TCREARRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.loggruposusuarios.siu.TCREARRequest();
            step10Request.setLogGruposUsuariosTCREARptpagrDominioInstance(dominioTDELGRPMEDICOpthisDominio);
            step10Request.setLogGruposUsuariosTCREARptpatrTipoGrupo(dominioTDELGRPMEDICOpthisDominio.getGrupoAreaMedica().getGrupoAsignacionTipo());
            step10Request.setLogGruposUsuariosTCREARptpatrAccion("Eliminar grupo Área Médica");
            step10Request.setLogGruposUsuariosTCREARptpatrGrupo(dominioTDELGRPMEDICOpthisDominio.getGrupoAreaMedica().getGrupoAsignacionNombre());
            logGruposUsuariosSrv.tCREARImplementation(step10Request);
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelGrupoAreaMedicaRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelGrupoAreaMedicaRequest();
            step11Request.setDominioDelGrupoAreaMedicapthisDominioInstance(dominioTDELGRPMEDICOpthisDominio);
            step11Request.setDominioDelGrupoAreaMedicapevcGrupoAsignacionInstance(dominioTDELGRPMEDICOpthisDominio.getGrupoAreaMedica());
            dominioSrv.delGrupoAreaMedicaImplementation(step11Request, true);
        }

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: TADDREPORTE

    /*
     * Entry point for service TADDREPORTE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tADDREPORTE(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDREPORTERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.tADDREPORTETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TADDREPORTE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tADDREPORTETxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDREPORTERequest request) throws ModelException {
        request.setDominioTADDREPORTEpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioTADDREPORTEpthisDominio()));
        request.setDominioTADDREPORTEptpagrPlantillaReporteInstance(plantillaReporteSrv.getByOIDWithHV(request.getDominioTADDREPORTEptpagrPlantillaReporte()));
        return dominioSrv.tADDREPORTEImplementation(request);
    }

    /*
     * Implements service TADDREPORTE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tADDREPORTEImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDREPORTERequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte = request.getDominioTADDREPORTEptpagrPlantillaReporteInstance();
        Boolean dominioTADDREPORTEptpatrEnviarAuto = request.getDominioTADDREPORTEptpatrEnviarAuto();
        Dominio dominioTADDREPORTEpthisDominio = request.getDominioTADDREPORTEpthisDominioInstance();

        request.checkArguments();

        tADDREPORTEPreconditions(request);
        Dominio instance = dominioTADDREPORTEpthisDominio;

        com.keralty.aeconfiguraciones.viewmodel.reportesdominio.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.reportesdominio.siu.CrearRequest();
        step1Request.setReportesDominiocrearpagrPlantillaReporteInstance(dominioTADDREPORTEptpagrPlantillaReporte);
        step1Request.setReportesDominiocrearpagrDominioInstance(dominioTADDREPORTEpthisDominio);
        step1Request.setReportesDominiocrearpatrEnviarAuto(dominioTADDREPORTEptpatrEnviarAuto);
        reportesDominioSrv.crearImplementation(step1Request);

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    /*
     * Checks service TADDREPORTE preconditions individually
     *
     * @param request Service request viewmodel
     *
     * @return Service preconditions evaluation result
     */
    public ServiceResponse tADDREPORTECheckPreconditions(com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDREPORTERequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setDominioTADDREPORTEpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioTADDREPORTEpthisDominio()));
        request.setDominioTADDREPORTEptpagrPlantillaReporteInstance(plantillaReporteSrv.getByOIDWithHV(request.getDominioTADDREPORTEptpagrPlantillaReporte()));
        try {
            if("Clas_1431701422080423Pre_8".equals(request.getPreconditionId()))
                checkClas1431701422080423Pre8(request);
            if("Clas_1431701422080423Pre_9".equals(request.getPreconditionId()))
                checkClas1431701422080423Pre9(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tADDREPORTEPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDREPORTERequest request) 
            throws ModelException {
        checkClas1431701422080423Pre8(request);
        checkClas1431701422080423Pre9(request);
    }

    private void checkClas1431701422080423Pre8 (
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDREPORTERequest request) 
            throws ModelException {
        // COUNT( ReportesDominio ) WHERE (ReportesDominio.PlantillaReporte = ptpagrPlantillaReporte) = 0

        PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte = request.getDominioTADDREPORTEptpagrPlantillaReporteInstance();
        Dominio dominioTADDREPORTEpthisDominio = request.getDominioTADDREPORTEpthisDominioInstance();
        try {
            boolean preconditionHolds = (repository.assocOperator003(dominioTADDREPORTEpthisDominio, dominioTADDREPORTEptpagrPlantillaReporte)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("El reporte seleccionado ya está asociado al Dominio");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    private void checkClas1431701422080423Pre9 (
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDREPORTERequest request) 
            throws ModelException {
        // COUNT( ReportesDominio ) WHERE (ReportesDominio.PlantillaReporte.Tipo = ptpagrPlantillaReporte.Tipo AND ReportesDominio.PlantillaReporte.FechaVigencia = ptpagrPlantillaReporte.FechaVigencia) = 0

        PlantillaReporte dominioTADDREPORTEptpagrPlantillaReporte = request.getDominioTADDREPORTEptpagrPlantillaReporteInstance();
        Dominio dominioTADDREPORTEpthisDominio = request.getDominioTADDREPORTEpthisDominioInstance();
        try {
            boolean preconditionHolds = (repository.assocOperator004(dominioTADDREPORTEpthisDominio, dominioTADDREPORTEptpagrPlantillaReporte)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("El Dominio ya tiene asociado un reporte del tipo seleccionado con la misma fecha de vigencia. No puede asociarse al Dominio");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    // Service: TINSINCOMPATIBLE

    /*
     * Entry point for service TINSINCOMPATIBLE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tINSINCOMPATIBLE(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TINSINCOMPATIBLERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.tINSINCOMPATIBLETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TINSINCOMPATIBLE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tINSINCOMPATIBLETxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TINSINCOMPATIBLERequest request) throws ModelException {
        request.setDominioTINSINCOMPATIBLEpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioTINSINCOMPATIBLEpthisDominio()));
        request.setDominioTINSINCOMPATIBLEptpevcDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioTINSINCOMPATIBLEptpevcDominio()));
        return dominioSrv.tINSINCOMPATIBLEImplementation(request);
    }

    /*
     * Implements service TINSINCOMPATIBLE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tINSINCOMPATIBLEImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TINSINCOMPATIBLERequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioTINSINCOMPATIBLEptpevcDominio = request.getDominioTINSINCOMPATIBLEptpevcDominioInstance();
        Dominio dominioTINSINCOMPATIBLEpthisDominio = request.getDominioTINSINCOMPATIBLEpthisDominioInstance();

        request.checkArguments();
        Dominio instance = dominioTINSINCOMPATIBLEpthisDominio;

        com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsIncompatibleRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsIncompatibleRequest();
        step1Request.setDominioInsIncompatiblepthisDominioInstance(dominioTINSINCOMPATIBLEpthisDominio);
        step1Request.setDominioInsIncompatiblepevcDominioInstance(dominioTINSINCOMPATIBLEptpevcDominio);
        dominioSrv.insIncompatibleImplementation(step1Request, true);

        com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.TCREARRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.TCREARRequest();
        step2Request.setLogIncompatibilidadesTCREARptpagrDominioInstance(dominioTINSINCOMPATIBLEpthisDominio);
        step2Request.setLogIncompatibilidadesTCREARptpatrAccion("Asociar");
        step2Request.setLogIncompatibilidadesTCREARptpatrNomDominio("Producto:" + dominioTINSINCOMPATIBLEptpevcDominio.getProductoT().getProductosCodigoLegal() + "  Plan:" + dominioTINSINCOMPATIBLEptpevcDominio.getPlanT().getPlanesCodigoLegal());
        logIncompatibilidadesSrv.tCREARImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }

    // Service: TDELINCOMPATIBLE

    /*
     * Entry point for service TDELINCOMPATIBLE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tDELINCOMPATIBLE(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELINCOMPATIBLERequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.tDELINCOMPATIBLETxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service TDELINCOMPATIBLE
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tDELINCOMPATIBLETxn(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELINCOMPATIBLERequest request) throws ModelException {
        request.setDominioTDELINCOMPATIBLEpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioTDELINCOMPATIBLEpthisDominio()));
        request.setDominioTDELINCOMPATIBLEptpevcDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioTDELINCOMPATIBLEptpevcDominio()));
        return dominioSrv.tDELINCOMPATIBLEImplementation(request);
    }

    /*
     * Implements service TDELINCOMPATIBLE
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tDELINCOMPATIBLEImplementation(
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELINCOMPATIBLERequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Dominio dominioTDELINCOMPATIBLEptpevcDominio = request.getDominioTDELINCOMPATIBLEptpevcDominioInstance();
        Dominio dominioTDELINCOMPATIBLEpthisDominio = request.getDominioTDELINCOMPATIBLEpthisDominioInstance();

        request.checkArguments();
        Dominio instance = dominioTDELINCOMPATIBLEpthisDominio;

        com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelIncompatibleRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelIncompatibleRequest();
        step1Request.setDominioDelIncompatiblepthisDominioInstance(dominioTDELINCOMPATIBLEpthisDominio);
        step1Request.setDominioDelIncompatiblepevcDominioInstance(dominioTDELINCOMPATIBLEptpevcDominio);
        dominioSrv.delIncompatibleImplementation(step1Request, true);

        com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.TCREARRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.logincompatibilidades.siu.TCREARRequest();
        step2Request.setLogIncompatibilidadesTCREARptpagrDominioInstance(dominioTDELINCOMPATIBLEpthisDominio);
        step2Request.setLogIncompatibilidadesTCREARptpatrAccion("Desasociar");
        step2Request.setLogIncompatibilidadesTCREARptpatrNomDominio("Producto:" + dominioTDELINCOMPATIBLEptpevcDominio.getProductoT().getProductosCodigoLegal() + "  Plan:" + dominioTDELINCOMPATIBLEptpevcDominio.getPlanT().getPlanesCodigoLegal());
        logIncompatibilidadesSrv.tCREARImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICDominio());

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
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TMODIFICARRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = dominioSrv.tMODIFICARTxn(request);
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
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TMODIFICARRequest request) throws ModelException {
        request.setDominioTMODIFICARpthisDominioInstance(dominioSrv.getByOIDWithHV(request.getDominioTMODIFICARpthisDominio()));
        return dominioSrv.tMODIFICARImplementation(request);
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
            com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TMODIFICARRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Boolean dominioTMODIFICARpTramiteEnLinea = request.getDominioTMODIFICARpTramiteEnLinea();
        Boolean dominioTMODIFICARptVigenciaRadicacion = request.getDominioTMODIFICARptVigenciaRadicacion();
        Boolean dominioTMODIFICARpReqCuesMed = request.getDominioTMODIFICARpReqCuesMed();
        Boolean dominioTMODIFICARpNoCuesMedTras = request.getDominioTMODIFICARpNoCuesMedTras();
        Boolean dominioTMODIFICARpNuevaAfiliacion = request.getDominioTMODIFICARpNuevaAfiliacion();
        Boolean dominioTMODIFICARpInclusion = request.getDominioTMODIFICARpInclusion();
        Boolean dominioTMODIFICARpAcepCuesMat = request.getDominioTMODIFICARpAcepCuesMat();
        Boolean dominioTMODIFICARpAcepCuesNeo = request.getDominioTMODIFICARpAcepCuesNeo();
        Boolean dominioTMODIFICARpAcepBBGestante = request.getDominioTMODIFICARpAcepBBGestante();
        Boolean dominioTMODIFICARpIgnorarPreexistencias = request.getDominioTMODIFICARpIgnorarPreexistencias();
        Boolean dominioTMODIFICARpProrrateoUsuExistente = request.getDominioTMODIFICARpProrrateoUsuExistente();
        Boolean dominioTMODIFICARpInclusionModGrupoAsoc = request.getDominioTMODIFICARpInclusionModGrupoAsoc();
        Boolean dominioTMODIFICARpPermiteRechazadosAfi = request.getDominioTMODIFICARpPermiteRechazadosAfi();
        Boolean dominioTMODIFICARpAcepPolizaInter = request.getDominioTMODIFICARpAcepPolizaInter();
        Boolean dominioTMODIFICARpMostrarPreexAnexos = request.getDominioTMODIFICARpMostrarPreexAnexos();
        Boolean dominioTMODIFICARpMostrarPreexContrato = request.getDominioTMODIFICARpMostrarPreexContrato();
        Dominio dominioTMODIFICARpthisDominio = request.getDominioTMODIFICARpthisDominioInstance();

        request.checkArguments();
        Dominio instance = dominioTMODIFICARpthisDominio;

        /* Action 1: IF (pTramiteEnLinea <> TramiteEnLinea OR VigenciaRadicacion <> ptVigenciaRadicacion) */
        boolean ifCondition1 = !(dominioTMODIFICARpTramiteEnLinea).equals(dominioTMODIFICARpthisDominio.getDominioTramiteEnLinea()) || !(dominioTMODIFICARpthisDominio.getDominioVigenciaRadicacion()).equals(dominioTMODIFICARptVigenciaRadicacion);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.TCREARRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.logdatosdominio.siu.TCREARRequest();
            step10Request.setLogDatosDominioTCREARptpatrTramiteEnLinea(dominioTMODIFICARpTramiteEnLinea);
            step10Request.setLogDatosDominioTCREARptpagrDominioInstance(dominioTMODIFICARpthisDominio);
            step10Request.setLogDatosDominioTCREARptVigenciaRadicacion(dominioTMODIFICARptVigenciaRadicacion);
            logDatosDominioSrv.tCREARImplementation(step10Request);
        }

        com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModificarRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModificarRequest();
        step2Request.setDominiomodificarpthisDominioInstance(dominioTMODIFICARpthisDominio);
        step2Request.setDominiomodificarpReqCuesMed(dominioTMODIFICARpReqCuesMed);
        step2Request.setDominiomodificarpNoCuesMedTras(dominioTMODIFICARpNoCuesMedTras);
        step2Request.setDominiomodificarpNuevaAfiliacion(dominioTMODIFICARpNuevaAfiliacion);
        step2Request.setDominiomodificarpInclusion(dominioTMODIFICARpInclusion);
        step2Request.setDominiomodificarpAcepCuesMat(dominioTMODIFICARpAcepCuesMat);
        step2Request.setDominiomodificarpAcepCuesNeo(dominioTMODIFICARpAcepCuesNeo);
        step2Request.setDominiomodificarpAcepBBGestante(dominioTMODIFICARpAcepBBGestante);
        step2Request.setDominiomodificarpIgnorarPreexistencias(dominioTMODIFICARpIgnorarPreexistencias);
        step2Request.setDominiomodificarpProrrateoUsuExistente(dominioTMODIFICARpProrrateoUsuExistente);
        step2Request.setDominiomodificarpInclusionModGrupoAsoc(dominioTMODIFICARpInclusionModGrupoAsoc);
        step2Request.setDominiomodificarpPermiteRechazadosAfi(dominioTMODIFICARpPermiteRechazadosAfi);
        step2Request.setDominiomodificarpAcepPolizaInter(dominioTMODIFICARpAcepPolizaInter);
        step2Request.setDominiomodificarpMostrarPreexAnexos(dominioTMODIFICARpMostrarPreexAnexos);
        step2Request.setDominiomodificarpMostrarPreexContrato(dominioTMODIFICARpMostrarPreexContrato);
        step2Request.setDominiomodificarpTramiteEnLinea(dominioTMODIFICARpTramiteEnLinea);
        step2Request.setDominiomodificarpeVigenciaRadicacion(dominioTMODIFICARptVigenciaRadicacion);
        dominioSrv.modificarImplementation(step2Request);

        response.setInstance(getByOID(instance.getOid()));
        response.setInstanceSuppInfo(instance.buildICDominio());

        return response;
    }


    private void suppInfo4ICDominio(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(DominioConstants.ROLE_NAME_PRODUCTOT + "." + ProductosConstants.ATTR_NAME_PRODUCTOSDESCRIPCION, Constants.Type.STRING.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison1);

            SQLWhereComparison comparison2 = new SQLWhereComparison(select);
            comparison2.setOperator("LIKE");
            comparison2.setFirstOperand(new SQLWhereOperandPathAttribute(DominioConstants.ROLE_NAME_PLANT + "." + PlanesConstants.ATTR_NAME_PLANESNOMBRE, Constants.Type.STRING.getTypeName()));
            comparison2.setSecondOperand(new SQLWhereOperandLiteral(searchText, Constants.Type.STRING.getTypeName()));
            disjunction.addComparison(comparison2);

            SQLWhereConjunction conjunction = new SQLWhereConjunction();
            conjunction.addDisjunction(disjunction);
            select.addConjunction(conjunction);
        } catch (Exception e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
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
        select.getOrderByPart().addItem(Constants.normalizePath(DominioConstants.CLASS_NAME, DominioConstants.ATTR_FIELD_DOMINIONUMERO, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildDominioRelatedInstance(relatedInstanceJsonOid);
        }
        if (DominioConstants.ROLE_NAME_DOMINIOSCONINCOMPATIBILIDAD.equalsIgnoreCase(relatedInstancePathFirstItem) || DominioConstants.ROLE_NAME_DOMINIOSINCOMPATIBLES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4Dominio(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_PARENTESCOSDOM.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4ParentescoDom(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_AGENTEXPRODUCTO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4AgentexProducto(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_PRORRATEOS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4Prorrateo(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_LOGGRUPOSUSUARIOS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4LogGruposUsuarios(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_GRUPOASIGNACIONNOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4GrupoAsignacionNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_CONFIGURACIONESANS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4ConfiguracionANS(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_REPORTESDOMINIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4ReportesDominio(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_LOGINCOMPATIBILIDADES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4LogIncompatibilidades(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_LOGDATOSDOMINIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4LogDatosDominio(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_PLANT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4Planes(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_PRODUCTOT.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4Productos(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_PERIODICIDADES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4Periodicidad(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_ROLES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4Rol(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_FORMASPAGO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4FormaPago(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_PLANTILLAS.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4PlantillaEmail(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_NOVEDADES.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4Novedad(relatedInstanceJsonOid, remainingPath);
        }
        if (DominioConstants.ROLE_NAME_GRUPOASESORES.equalsIgnoreCase(relatedInstancePathFirstItem) || DominioConstants.ROLE_NAME_GRUPOAREAMEDICA.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildDominioRelatedInstance4GrupoAsignacion(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildDominioRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new Dominio(mapper.readValue(relatedInstanceJsonOid, DominioOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4Dominio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Dominio(mapper.readValue(relatedInstanceJsonOid, DominioOid.class)): Dominio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4ParentescoDom(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ParentescoDom(mapper.readValue(relatedInstanceJsonOid, ParentescoDomOid.class)): ParentescoDom.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4AgentexProducto(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new AgentexProducto(mapper.readValue(relatedInstanceJsonOid, AgentexProductoOid.class)): AgentexProducto.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4Prorrateo(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Prorrateo(mapper.readValue(relatedInstanceJsonOid, ProrrateoOid.class)): Prorrateo.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4LogGruposUsuarios(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new LogGruposUsuarios(mapper.readValue(relatedInstanceJsonOid, LogGruposUsuariosOid.class)): LogGruposUsuarios.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4GrupoAsignacionNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new GrupoAsignacionNovedad(mapper.readValue(relatedInstanceJsonOid, GrupoAsignacionNovedadOid.class)): GrupoAsignacionNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4ConfiguracionANS(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ConfiguracionANS(mapper.readValue(relatedInstanceJsonOid, ConfiguracionANSOid.class)): ConfiguracionANS.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4ReportesDominio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new ReportesDominio(mapper.readValue(relatedInstanceJsonOid, ReportesDominioOid.class)): ReportesDominio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4LogIncompatibilidades(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new LogIncompatibilidades(mapper.readValue(relatedInstanceJsonOid, LogIncompatibilidadesOid.class)): LogIncompatibilidades.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4LogDatosDominio(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new LogDatosDominio(mapper.readValue(relatedInstanceJsonOid, LogDatosDominioOid.class)): LogDatosDominio.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4Planes(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Planes(mapper.readValue(relatedInstanceJsonOid, PlanesOid.class)): Planes.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4Productos(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Productos(mapper.readValue(relatedInstanceJsonOid, ProductosOid.class)): Productos.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4Periodicidad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Periodicidad(mapper.readValue(relatedInstanceJsonOid, PeriodicidadOid.class)): Periodicidad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4Rol(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Rol(mapper.readValue(relatedInstanceJsonOid, RolOid.class)): Rol.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4FormaPago(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new FormaPago(mapper.readValue(relatedInstanceJsonOid, FormaPagoOid.class)): FormaPago.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4PlantillaEmail(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new PlantillaEmail(mapper.readValue(relatedInstanceJsonOid, PlantillaEmailOid.class)): PlantillaEmail.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4Novedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Novedad(mapper.readValue(relatedInstanceJsonOid, NovedadOid.class)): Novedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    private AbstractAppEntity buildDominioRelatedInstance4GrupoAsignacion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new GrupoAsignacion(mapper.readValue(relatedInstanceJsonOid, GrupoAsignacionOid.class)): GrupoAsignacion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
    		return null;
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            DominioOid oid = new DominioOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(DominioConstants.ATTR_FIELD_DOMINIONUMERO, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getDominioNumero(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(DominioService.class).error(e.getMessage());
        }
    }

}
