package com.keralty.aeconfiguraciones.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.Constants;
import com.keralty.aeconfiguraciones.global.DocumentoNovedadConstants;
import com.keralty.aeconfiguraciones.global.EstadoBeneficiarioConstants;
import com.keralty.aeconfiguraciones.global.FamiliaNovedadConstants;
import com.keralty.aeconfiguraciones.global.ParentescoConstants;
import com.keralty.aeconfiguraciones.global.SolicitudNovedadConstants;
import com.keralty.aeconfiguraciones.global.UsuarioNovedadConstants;
import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.FamiliaNovedad;
import com.keralty.aeconfiguraciones.persistence.oid.EstadoBeneficiarioOid;
import com.keralty.aeconfiguraciones.persistence.oid.FamiliaNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.ParentescoOid;
import com.keralty.aeconfiguraciones.persistence.oid.SolicitudNovedadOid;
import com.keralty.aeconfiguraciones.persistence.oid.TipoIdentificacionOid;
import com.keralty.aeconfiguraciones.persistence.oid.UsuarioNovedadOid;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.persistence.SolicitudNovedad;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.UsuarioNovedad;
import com.keralty.aeconfiguraciones.repository.UsuarioNovedadJpaRepository;
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
public class UsuarioNovedadService extends AbstractService {

    UsuarioNovedadJpaRepository repository;

    UsuarioNovedadService usuarioNovedadSrv;

    TipoIdentificacionService tipoIdentificacionSrv;

    ParentescoService parentescoSrv;

    EstadoBeneficiarioService estadoBeneficiarioSrv;

    FamiliaNovedadService familiaNovedadSrv;

    SolicitudNovedadService solicitudNovedadSrv;

    @Inject
    public UsuarioNovedadService(
            UsuarioNovedadService usuarioNovedadSrv,
            TipoIdentificacionService tipoIdentificacionSrv,
            ParentescoService parentescoSrv,
            EstadoBeneficiarioService estadoBeneficiarioSrv,
            FamiliaNovedadService familiaNovedadSrv,
            SolicitudNovedadService solicitudNovedadSrv,
            UsuarioNovedadJpaRepository repository) {
        this.usuarioNovedadSrv = usuarioNovedadSrv;
        this.tipoIdentificacionSrv = tipoIdentificacionSrv;
        this.parentescoSrv = parentescoSrv;
        this.estadoBeneficiarioSrv = estadoBeneficiarioSrv;
        this.familiaNovedadSrv = familiaNovedadSrv;
        this.solicitudNovedadSrv = solicitudNovedadSrv;
        this.repository = repository;
    }

    public List<Map<String, Object>> solveQuery(QueryRequest request) {
        return solveGenericQuery(request, UsuarioNovedadConstants.CLASS_NAME);
    }

    private List<UsuarioNovedad> solveUsuarioNovedadTypedQuery(QueryRequest request) {
        return repository.solveQuery(processQueryRequest(request, UsuarioNovedadConstants.CLASS_NAME), request.getPageSize(), request.getPageNumber());
    }
    
    private int solveUsuarioNovedadTypedCountQuery(QueryRequest request) {
        return repository.solveCountQuery(processCountQueryRequest(request, UsuarioNovedadConstants.CLASS_NAME));
    }

    /*
     * Returns the population of class UsuarioNovedad
     *
     * @return Population of class UsuarioNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<UsuarioNovedad> get() {
        List<UsuarioNovedad> results = new java.util.ArrayList<>();
        repository.findAll().forEach(results::add);
        return results;
    }
    
    /*
     * Gets the preloaded list of objects of class UsuarioNovedad
     *
     * @param queryRequest Request object that dictates the search conditions to 
     * retrieve objects
     *
     * @return Preloaded list of objects of class UsuarioNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<PreloadViewModel> getPreload(QueryRequest queryRequest) {
        List<PreloadViewModel> results = new ArrayList<>();
        List<UsuarioNovedad> instances = get(queryRequest);
        if(UsuarioNovedadConstants.DSICUSUARIONOVEDAD.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildICUsuarioNovedad())).collect(Collectors.toList());
        }
        if(UsuarioNovedadConstants.DSDSUSUNOVTIPOTITULAR.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSUsuNovTipoTitular())).collect(Collectors.toList());
        }
        if(UsuarioNovedadConstants.DSDSUSUNOVTIPOUSUARIO.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSUsuNovTipoUsuario())).collect(Collectors.toList());
        }
        if(UsuarioNovedadConstants.DSDSUSUARIONOVEDADNULL.equals(queryRequest.getDisplaySetName())) {
            results = instances.stream().map(x -> new PreloadViewModel(x.getOid().toJson(), x.buildDSUsuarioNovedadNull())).collect(Collectors.toList());
        }
        return results;
    }
    /*
     * Returns the population of class UsuarioNovedad that matches certain search criteria
     *
     * @param queryRequest Search criteria
     *
     * @return Population of class UsuarioNovedad matching the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<UsuarioNovedad> get(QueryRequest queryRequest) {
        return solveUsuarioNovedadTypedQuery(queryRequest);
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
            return new PopulationResponseViewModel(solveUsuarioNovedadTypedQuery(queryRequest).stream()
                    .map(instance -> buildDynamicObject(instance, queryRequest.getDisplaySetItems()))
                    .collect(Collectors.toList()), queryRequest.getPageSize() != 0 ? solveUsuarioNovedadTypedCountQuery(queryRequest) : 0);
        } else {
            return new PreloadResponseViewModel(solveUsuarioNovedadTypedQuery(queryRequest).stream()
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
        return new PopulationResponseViewModel(solveUsuarioNovedadTypedQuery(queryRequest).stream()
                .map(instance -> buildDynamicObject(instance, displaySetItems))
                .collect(Collectors.toList()));
    }

    /*
     * Returns an instance of UsuarioNovedad by ID
     *
     * @param usuarioNovedadidUsuarioNovedad Identification field usuarioNovedadidUsuarioNovedad
     *
     * @return Instance of UsuarioNovedad
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public UsuarioNovedad getById(Long usuarioNovedadidUsuarioNovedad) {
        return getByOID(new UsuarioNovedadOid(usuarioNovedadidUsuarioNovedad));
    }
    
    /*
     * Returns an instance of UsuarioNovedad by OID
     *
     * @param oid Object IDentifier
     *
     * @return Instance of UsuarioNovedad
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public UsuarioNovedad getByOID(UsuarioNovedadOid oid) {
        Optional<UsuarioNovedad> instance = getInstanceByOid(oid);
        return instance.isPresent() ? instance.get() : null;
    }
    
    /*
     * Returns an instance of UsuarioNovedad by OID applying horizontal visibility constraints
     *
     * @param oid Object IDentifier
     *
     * @return Instance of UsuarioNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public UsuarioNovedad getByOIDWithHV(UsuarioNovedadOid oid) {
        Optional<UsuarioNovedad> instance = getInstanceByOid(oid);
    	return instance.isPresent() && instance.get().checkHorizontalVisibility(getAgentInstance()) 
    		 ? instance.get() 
    		 : null;
    }
    
    private Optional<UsuarioNovedad> getInstanceByOid(UsuarioNovedadOid oid) {
        return oid != null && oid.getUsuarioNovedadidUsuarioNovedad() != null
               ? repository.findById(oid.getUsuarioNovedadidUsuarioNovedad())
               : Optional.empty();
    }

    /*
     * Returns an instance of UsuarioNovedad by OID in JSON format
     *
     * @param jsonOid Object IDentifier in JSON format
     *
     * @return Instance of UsuarioNovedad
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public UsuarioNovedad getByJsonOid(String jsonOid) {
        UsuarioNovedadOid oid = new UsuarioNovedadOid();
        oid.fromJsonString(jsonOid);
        return getByOIDWithHV(oid);
    }

    /*
     * Returns the list of instances of UsuarioNovedad related to a given instance through 
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of UsuarioNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<UsuarioNovedad> getByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        QueryRequest queryRequest = new QueryRequest(new FilterRequest(Constants.FILTER_BY_RELATED,
                Arrays.asList(new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_PATH, relatedInstancePath),
                        new FilterVariableRequest(Constants.FILTER_BY_RELATED_FVAR_OID, relatedInstance.getOid().toJson()))));
        return solveUsuarioNovedadTypedQuery(queryRequest);
    }

    /*
     * Returns the list of instances of UsuarioNovedad related to a given instance through
     * a given path
     *
     * @param relatedInstance Related instance
     *
     * @param relatedInstancePath Related instance path
     *
     * @return List of instances of UsuarioNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<UsuarioNovedad> getInstancesByRelated(AbstractAppEntity relatedInstance, String relatedInstancePath) {
        return getByRelated(relatedInstance, relatedInstancePath);
    }

    /*
     * Returns a set of displayset items for an instance of UsuarioNovedad
     *
     * @param oid Object IDentifier
     *
     * @param displaySetItems List of comma-separated displayset items
     *
     * @return Set of displayset items for an instance of UsuarioNovedad
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public InstanceResponseViewModel getUsuarioNovedadDynamic(UsuarioNovedadOid oid, String displaySetItems) {
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
    public String getSuppInfo(UsuarioNovedadOid oid, String dsName) {
        UsuarioNovedad instance = getByOIDWithHV(oid);
        return (instance != null ? getSuppInfo(instance, dsName): "");
    }

    private String getSuppInfo(UsuarioNovedad instance, String dsName) {
        if (instance == null || instance.isNull()) return "";
        if (UsuarioNovedadConstants.DSICUSUARIONOVEDAD.equals(dsName))
            return instance.buildICUsuarioNovedad();
        return dsName.isEmpty() ? instance.getOid().toJson() : "";
    }

    public static PreloadViewModel buildPreloadViewModel(UsuarioNovedad instance, String dsName) {
        if (UsuarioNovedadConstants.DSICUSUARIONOVEDAD.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildICUsuarioNovedad());
        if (UsuarioNovedadConstants.DSDSUSUNOVTIPOTITULAR.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSUsuNovTipoTitular());
        if (UsuarioNovedadConstants.DSDSUSUNOVTIPOUSUARIO.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSUsuNovTipoUsuario());
        if (UsuarioNovedadConstants.DSDSUSUARIONOVEDADNULL.equals(dsName))
            return new PreloadViewModel(instance.getOid().toJson(), instance.buildDSUsuarioNovedadNull());
        return new PreloadViewModel(instance.getOid().toJson(), "");
    }


    @Override
    protected void applyNavigationalFilters(FilterRequest filterRequest, SQLSelect sql) throws SystemException {
        if ("FamiliaNovedad_TADDTITULAR_ptUsuExistente_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1581424574464890FiltNav5(filterRequest, sql);
        if ("DocumentoNovedad_TCREARATO_ptUsuario_NavFilter0".equalsIgnoreCase(filterRequest.getName()))
            clas1582535933952300FiltNav2(filterRequest, sql);
    }

    @Override
    protected void applyServiceSIFilters(FilterRequest filterRequest, SQLSelect sql) {
        if ("SuppInfo_IC_UsuarioNovedad".equals(filterRequest.getName()))
            suppInfo4ICUsuarioNovedad(filterRequest.getFilterVariable("searchText").toString(), sql);
    }


    private void clas1581424574464890FiltNav5(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        FamiliaNovedad familiaNovedadTADDTITULARpthisFamiliaNovedad = filterRequest.hasFilterVariable(FamiliaNovedadConstants.ARG_NAME_TADDTITULAR_FAMILIANOVEDADTADDTITULARPTHISFAMILIANOVEDAD) ? familiaNovedadSrv.getByOID(new FamiliaNovedadOid().fromJsonString(toJson(filterRequest.getFilterVariable(FamiliaNovedadConstants.ARG_NAME_TADDTITULAR_FAMILIANOVEDADTADDTITULARPTHISFAMILIANOVEDAD)))) : null;

        // FamiliaNovedad = pthisFamiliaNovedad
        if (familiaNovedadTADDTITULARpthisFamiliaNovedad != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(UsuarioNovedadConstants.ROLE_NAME_FAMILIANOVEDAD, FamiliaNovedadConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(FamiliaNovedadConstants.ARG_NAME_TADDTITULAR_FAMILIANOVEDADTADDTITULARPTHISFAMILIANOVEDAD, FamiliaNovedadConstants.CLASS_NAME, FamiliaNovedadConstants.CLASS_NAME, familiaNovedadTADDTITULARpthisFamiliaNovedad.getOid(), FamiliaNovedadConstants.ARG_NAME_TADDTITULAR_FAMILIANOVEDADTADDTITULARPTHISFAMILIANOVEDAD);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        

        // Parentesco.EsTitular = false
        // '=' Comparison
        SQLWhereComparison comparison2 = new SQLWhereComparison(sql);
        comparison2.setOperator("=");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand113 = new SQLWhereOperandPathAttribute(UsuarioNovedadConstants.ROLE_NAME_PARENTESCO + "." + ParentescoConstants.ATTR_NAME_PARENTESCOESTITULAR, Constants.Type.BOOL.getTypeName());
        comparison2.setFirstOperand(operand113);
        
        // Operand 2
        comparison2.setSecondOperand(new SQLWhereOperandLiteral(Boolean.FALSE, Constants.Type.BOOL.getTypeName()));
        conjunction.addOperand(comparison2);


        // EstadoBeneficiario.EsActivo = true
        // '=' Comparison
        SQLWhereComparison comparison3 = new SQLWhereComparison(sql);
        comparison3.setOperator("=");
        
        // Operand 1
        SQLWhereOperandPathAttribute operand114 = new SQLWhereOperandPathAttribute(UsuarioNovedadConstants.ROLE_NAME_ESTADOBENEFICIARIO + "." + EstadoBeneficiarioConstants.ATTR_NAME_ESTADOBENEFICIARIOESACTIVO, Constants.Type.BOOL.getTypeName());
        comparison3.setFirstOperand(operand114);
        
        // Operand 2
        comparison3.setSecondOperand(new SQLWhereOperandLiteral(Boolean.TRUE, Constants.Type.BOOL.getTypeName()));
        conjunction.addOperand(comparison3);

        sql.addConjunction(conjunction);
    }

    private void clas1582535933952300FiltNav2(FilterRequest filterRequest, SQLSelect sql) {
        SQLWhereConjunction conjunction = new SQLWhereConjunction();
        SolicitudNovedad documentoNovedadTCREARATOptSolicitud = filterRequest.hasFilterVariable(DocumentoNovedadConstants.ARG_NAME_TCREARATO_DOCUMENTONOVEDADTCREARATOPTSOLICITUD) ? solicitudNovedadSrv.getByOID(new SolicitudNovedadOid().fromJsonString(toJson(filterRequest.getFilterVariable(DocumentoNovedadConstants.ARG_NAME_TCREARATO_DOCUMENTONOVEDADTCREARATOPTSOLICITUD)))) : null;

        // FamiliaNovedad.Solicitud = ptSolicitud
        if (documentoNovedadTCREARATOptSolicitud != null) {
            // '=' Comparison
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            
            // Operand 1
            SQLWhereOperandPathInstance operand112 = new SQLWhereOperandPathInstance(UsuarioNovedadConstants.ROLE_NAME_FAMILIANOVEDAD + "." + FamiliaNovedadConstants.ROLE_NAME_SOLICITUD, SolicitudNovedadConstants.CLASS_NAME);
            comparison1.setFirstOperand(operand112);
            
            // Operand 2
            SQLWhereOperandOVParameter operand223 = new SQLWhereOperandOVParameter(DocumentoNovedadConstants.ARG_NAME_TCREARATO_DOCUMENTONOVEDADTCREARATOPTSOLICITUD, SolicitudNovedadConstants.CLASS_NAME, SolicitudNovedadConstants.CLASS_NAME, documentoNovedadTCREARATOptSolicitud.getOid(), DocumentoNovedadConstants.ARG_NAME_TCREARATO_DOCUMENTONOVEDADTCREARATOPTSOLICITUD);
            comparison1.setSecondOperand(operand223);
            conjunction.addOperand(comparison1);

        }        
        sql.addConjunction(conjunction);
    }

    // IIU Methods


    /*
     * Solves the query needed for PIUUsuNovTipoTitular
     *
     * @param queryRequest Query request
     * @return List of viewmodels for UsuarioNovedad using displayset DSUsuNovTipoTitular that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.ds.DSUsuNovTipoTitular> piuPIUUsuNovTipoTitular(QueryRequest queryRequest) {
        return solveUsuarioNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.ds.DSUsuNovTipoTitular::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUUsuNovTipoTitular
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUUsuNovTipoTitular
     */
    public int getCount4PIUUsuNovTipoTitular(QueryRequest queryRequest) {
        return solveUsuarioNovedadTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUUsuNovTipoUsuario
     *
     * @param queryRequest Query request
     * @return List of viewmodels for UsuarioNovedad using displayset DSUsuNovTipoUsuario that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.ds.DSUsuNovTipoUsuario> piuPIUUsuNovTipoUsuario(QueryRequest queryRequest) {
        return solveUsuarioNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.ds.DSUsuNovTipoUsuario::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUUsuNovTipoUsuario
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUUsuNovTipoUsuario
     */
    public int getCount4PIUUsuNovTipoUsuario(QueryRequest queryRequest) {
        return solveUsuarioNovedadTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUUsuarioNovedadNull
     *
     * @param queryRequest Query request
     * @return List of viewmodels for UsuarioNovedad using displayset DSUsuarioNovedadNull that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.ds.DSUsuarioNovedadNull> piuPIUUsuarioNovedadNull(QueryRequest queryRequest) {
        return solveUsuarioNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.ds.DSUsuarioNovedadNull::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUUsuarioNovedadNull
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUUsuarioNovedadNull
     */
    public int getCount4PIUUsuarioNovedadNull(QueryRequest queryRequest) {
        return solveUsuarioNovedadTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUUsuNovTipoTitularObs
     *
     * @param queryRequest Query request
     * @return List of viewmodels for UsuarioNovedad using displayset DSUsuNovTipoTitular that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.ds.DSUsuNovTipoTitular> piuPIUUsuNovTipoTitularObs(QueryRequest queryRequest) {
        return solveUsuarioNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.ds.DSUsuNovTipoTitular::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUUsuNovTipoTitularObs
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUUsuNovTipoTitularObs
     */
    public int getCount4PIUUsuNovTipoTitularObs(QueryRequest queryRequest) {
        return solveUsuarioNovedadTypedQuery(queryRequest).size();
    }

    /*
     * Solves the query needed for PIUUsuNovTipoUsuarioObs
     *
     * @param queryRequest Query request
     * @return List of viewmodels for UsuarioNovedad using displayset DSUsuNovTipoUsuario that 
     * match the search criteria
     */
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.ds.DSUsuNovTipoUsuario> piuPIUUsuNovTipoUsuarioObs(QueryRequest queryRequest) {
        return solveUsuarioNovedadTypedQuery(queryRequest).stream()
                .map(com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.ds.DSUsuNovTipoUsuario::new)
                .collect(Collectors.toList());
    }

    /*
     * Solves the COUNT query needed for PIUUsuNovTipoUsuarioObs
     *
     * @param queryRequest Query request
     * @returns Count of instances for PIUUsuNovTipoUsuarioObs
     */
    public int getCount4PIUUsuNovTipoUsuarioObs(QueryRequest queryRequest) {
        return solveUsuarioNovedadTypedQuery(queryRequest).size();
    }

    // Service: InsTipoIdentUsuNovedad

    /*
     * Entry point for service InsTipoIdentUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTipoIdentUsuNovedad(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsTipoIdentUsuNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioNovedadSrv.insTipoIdentUsuNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTipoIdentUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTipoIdentUsuNovedadTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsTipoIdentUsuNovedadRequest request) throws ModelException {
        request.setUsuarioNovedadInsTipoIdentUsuNovedadpthisUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getUsuarioNovedadInsTipoIdentUsuNovedadpthisUsuarioNovedad()));
        request.setUsuarioNovedadInsTipoIdentUsuNovedadpevcTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getUsuarioNovedadInsTipoIdentUsuNovedadpevcTipoIdentificacion()));
        return usuarioNovedadSrv.insTipoIdentUsuNovedadImplementation(request, true);
    }

    /*
     * Implements service InsTipoIdentUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTipoIdentUsuNovedadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsTipoIdentUsuNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        UsuarioNovedad usuarioNovedadInsTipoIdentUsuNovedadpthisUsuarioNovedad = request.getUsuarioNovedadInsTipoIdentUsuNovedadpthisUsuarioNovedadInstance();
        TipoIdentificacion usuarioNovedadInsTipoIdentUsuNovedadpevcTipoIdentificacion = request.getUsuarioNovedadInsTipoIdentUsuNovedadpevcTipoIdentificacionInstance();

        request.checkArguments();
        UsuarioNovedad instance = usuarioNovedadInsTipoIdentUsuNovedadpthisUsuarioNovedad;

        instance.add2TipoIdentificacion(usuarioNovedadInsTipoIdentUsuNovedadpevcTipoIdentificacion);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentUsuNovedadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdentUsuNovedadRequest();
            relatedRequest.setTipoIdentificacionInsTipoIdentUsuNovedadpthisTipoIdentificacionInstance(usuarioNovedadInsTipoIdentUsuNovedadpevcTipoIdentificacion);
            relatedRequest.setTipoIdentificacionInsTipoIdentUsuNovedadpevcUsuarioNovedadInstance(usuarioNovedadInsTipoIdentUsuNovedadpthisUsuarioNovedad);
            tipoIdentificacionSrv.insTipoIdentUsuNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuarioNovedad());

        return response;
    }

    // Service: DelTipoIdentUsuNovedad

    /*
     * Entry point for service DelTipoIdentUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTipoIdentUsuNovedad(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelTipoIdentUsuNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioNovedadSrv.delTipoIdentUsuNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTipoIdentUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTipoIdentUsuNovedadTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelTipoIdentUsuNovedadRequest request) throws ModelException {
        request.setUsuarioNovedadDelTipoIdentUsuNovedadpthisUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getUsuarioNovedadDelTipoIdentUsuNovedadpthisUsuarioNovedad()));
        request.setUsuarioNovedadDelTipoIdentUsuNovedadpevcTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getUsuarioNovedadDelTipoIdentUsuNovedadpevcTipoIdentificacion()));
        return usuarioNovedadSrv.delTipoIdentUsuNovedadImplementation(request, true);
    }

    /*
     * Implements service DelTipoIdentUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTipoIdentUsuNovedadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelTipoIdentUsuNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        UsuarioNovedad usuarioNovedadDelTipoIdentUsuNovedadpthisUsuarioNovedad = request.getUsuarioNovedadDelTipoIdentUsuNovedadpthisUsuarioNovedadInstance();
        TipoIdentificacion usuarioNovedadDelTipoIdentUsuNovedadpevcTipoIdentificacion = request.getUsuarioNovedadDelTipoIdentUsuNovedadpevcTipoIdentificacionInstance();

        request.checkArguments();
        UsuarioNovedad instance = usuarioNovedadDelTipoIdentUsuNovedadpthisUsuarioNovedad;

        instance.add2TipoIdentificacion(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentUsuNovedadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdentUsuNovedadRequest();
            relatedRequest.setTipoIdentificacionDelTipoIdentUsuNovedadpthisTipoIdentificacionInstance(usuarioNovedadDelTipoIdentUsuNovedadpevcTipoIdentificacion);
            relatedRequest.setTipoIdentificacionDelTipoIdentUsuNovedadpevcUsuarioNovedadInstance(usuarioNovedadDelTipoIdentUsuNovedadpthisUsuarioNovedad);
            tipoIdentificacionSrv.delTipoIdentUsuNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuarioNovedad());

        return response;
    }

    // Service: InsParentescoUsuNovedad

    /*
     * Entry point for service InsParentescoUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insParentescoUsuNovedad(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsParentescoUsuNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioNovedadSrv.insParentescoUsuNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsParentescoUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insParentescoUsuNovedadTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsParentescoUsuNovedadRequest request) throws ModelException {
        request.setUsuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getUsuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad()));
        request.setUsuarioNovedadInsParentescoUsuNovedadpevcParentescoInstance(parentescoSrv.getByOIDWithHV(request.getUsuarioNovedadInsParentescoUsuNovedadpevcParentesco()));
        return usuarioNovedadSrv.insParentescoUsuNovedadImplementation(request, true);
    }

    /*
     * Implements service InsParentescoUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insParentescoUsuNovedadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsParentescoUsuNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        UsuarioNovedad usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad = request.getUsuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedadInstance();
        Parentesco usuarioNovedadInsParentescoUsuNovedadpevcParentesco = request.getUsuarioNovedadInsParentescoUsuNovedadpevcParentescoInstance();

        request.checkArguments();
        UsuarioNovedad instance = usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad;

        instance.add2Parentesco(usuarioNovedadInsParentescoUsuNovedadpevcParentesco);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.InsParentescoUsuNovedadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.InsParentescoUsuNovedadRequest();
            relatedRequest.setParentescoInsParentescoUsuNovedadpthisParentescoInstance(usuarioNovedadInsParentescoUsuNovedadpevcParentesco);
            relatedRequest.setParentescoInsParentescoUsuNovedadpevcUsuarioNovedadInstance(usuarioNovedadInsParentescoUsuNovedadpthisUsuarioNovedad);
            parentescoSrv.insParentescoUsuNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuarioNovedad());

        return response;
    }

    // Service: DelParentescoUsuNovedad

    /*
     * Entry point for service DelParentescoUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delParentescoUsuNovedad(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelParentescoUsuNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioNovedadSrv.delParentescoUsuNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelParentescoUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delParentescoUsuNovedadTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelParentescoUsuNovedadRequest request) throws ModelException {
        request.setUsuarioNovedadDelParentescoUsuNovedadpthisUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getUsuarioNovedadDelParentescoUsuNovedadpthisUsuarioNovedad()));
        request.setUsuarioNovedadDelParentescoUsuNovedadpevcParentescoInstance(parentescoSrv.getByOIDWithHV(request.getUsuarioNovedadDelParentescoUsuNovedadpevcParentesco()));
        return usuarioNovedadSrv.delParentescoUsuNovedadImplementation(request, true);
    }

    /*
     * Implements service DelParentescoUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delParentescoUsuNovedadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelParentescoUsuNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        UsuarioNovedad usuarioNovedadDelParentescoUsuNovedadpthisUsuarioNovedad = request.getUsuarioNovedadDelParentescoUsuNovedadpthisUsuarioNovedadInstance();
        Parentesco usuarioNovedadDelParentescoUsuNovedadpevcParentesco = request.getUsuarioNovedadDelParentescoUsuNovedadpevcParentescoInstance();

        request.checkArguments();
        UsuarioNovedad instance = usuarioNovedadDelParentescoUsuNovedadpthisUsuarioNovedad;

        instance.add2Parentesco(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.DelParentescoUsuNovedadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.DelParentescoUsuNovedadRequest();
            relatedRequest.setParentescoDelParentescoUsuNovedadpthisParentescoInstance(usuarioNovedadDelParentescoUsuNovedadpevcParentesco);
            relatedRequest.setParentescoDelParentescoUsuNovedadpevcUsuarioNovedadInstance(usuarioNovedadDelParentescoUsuNovedadpthisUsuarioNovedad);
            parentescoSrv.delParentescoUsuNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuarioNovedad());

        return response;
    }

    // Service: InsEstadoBeneficiario

    /*
     * Entry point for service InsEstadoBeneficiario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insEstadoBeneficiario(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsEstadoBeneficiarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioNovedadSrv.insEstadoBeneficiarioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsEstadoBeneficiario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insEstadoBeneficiarioTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsEstadoBeneficiarioRequest request) throws ModelException {
        request.setUsuarioNovedadInsEstadoBeneficiariopthisUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getUsuarioNovedadInsEstadoBeneficiariopthisUsuarioNovedad()));
        request.setUsuarioNovedadInsEstadoBeneficiariopevcEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getUsuarioNovedadInsEstadoBeneficiariopevcEstadoBeneficiario()));
        return usuarioNovedadSrv.insEstadoBeneficiarioImplementation(request, true);
    }

    /*
     * Implements service InsEstadoBeneficiario
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insEstadoBeneficiarioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsEstadoBeneficiarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        UsuarioNovedad usuarioNovedadInsEstadoBeneficiariopthisUsuarioNovedad = request.getUsuarioNovedadInsEstadoBeneficiariopthisUsuarioNovedadInstance();
        EstadoBeneficiario usuarioNovedadInsEstadoBeneficiariopevcEstadoBeneficiario = request.getUsuarioNovedadInsEstadoBeneficiariopevcEstadoBeneficiarioInstance();

        request.checkArguments();
        UsuarioNovedad instance = usuarioNovedadInsEstadoBeneficiariopthisUsuarioNovedad;

        instance.add2EstadoBeneficiario(usuarioNovedadInsEstadoBeneficiariopevcEstadoBeneficiario);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.InsEstadoBeneficiarioRequest();
            relatedRequest.setEstadoBeneficiarioInsEstadoBeneficiariopthisEstadoBeneficiarioInstance(usuarioNovedadInsEstadoBeneficiariopevcEstadoBeneficiario);
            relatedRequest.setEstadoBeneficiarioInsEstadoBeneficiariopevcUsuarioNovedadInstance(usuarioNovedadInsEstadoBeneficiariopthisUsuarioNovedad);
            estadoBeneficiarioSrv.insEstadoBeneficiarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuarioNovedad());

        return response;
    }

    // Service: DelEstadoBeneficiario

    /*
     * Entry point for service DelEstadoBeneficiario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delEstadoBeneficiario(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelEstadoBeneficiarioRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioNovedadSrv.delEstadoBeneficiarioTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelEstadoBeneficiario
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delEstadoBeneficiarioTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelEstadoBeneficiarioRequest request) throws ModelException {
        request.setUsuarioNovedadDelEstadoBeneficiariopthisUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getUsuarioNovedadDelEstadoBeneficiariopthisUsuarioNovedad()));
        request.setUsuarioNovedadDelEstadoBeneficiariopevcEstadoBeneficiarioInstance(estadoBeneficiarioSrv.getByOIDWithHV(request.getUsuarioNovedadDelEstadoBeneficiariopevcEstadoBeneficiario()));
        return usuarioNovedadSrv.delEstadoBeneficiarioImplementation(request, true);
    }

    /*
     * Implements service DelEstadoBeneficiario
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delEstadoBeneficiarioImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelEstadoBeneficiarioRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        UsuarioNovedad usuarioNovedadDelEstadoBeneficiariopthisUsuarioNovedad = request.getUsuarioNovedadDelEstadoBeneficiariopthisUsuarioNovedadInstance();
        EstadoBeneficiario usuarioNovedadDelEstadoBeneficiariopevcEstadoBeneficiario = request.getUsuarioNovedadDelEstadoBeneficiariopevcEstadoBeneficiarioInstance();

        request.checkArguments();
        UsuarioNovedad instance = usuarioNovedadDelEstadoBeneficiariopthisUsuarioNovedad;

        instance.add2EstadoBeneficiario(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.DelEstadoBeneficiarioRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.DelEstadoBeneficiarioRequest();
            relatedRequest.setEstadoBeneficiarioDelEstadoBeneficiariopthisEstadoBeneficiarioInstance(usuarioNovedadDelEstadoBeneficiariopevcEstadoBeneficiario);
            relatedRequest.setEstadoBeneficiarioDelEstadoBeneficiariopevcUsuarioNovedadInstance(usuarioNovedadDelEstadoBeneficiariopthisUsuarioNovedad);
            estadoBeneficiarioSrv.delEstadoBeneficiarioImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuarioNovedad());

        return response;
    }

    // Service: InsParentescoNuevoUsuNovedad

    /*
     * Entry point for service InsParentescoNuevoUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insParentescoNuevoUsuNovedad(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsParentescoNuevoUsuNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioNovedadSrv.insParentescoNuevoUsuNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsParentescoNuevoUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insParentescoNuevoUsuNovedadTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsParentescoNuevoUsuNovedadRequest request) throws ModelException {
        request.setUsuarioNovedadInsParentescoNuevoUsuNovedadpthisUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getUsuarioNovedadInsParentescoNuevoUsuNovedadpthisUsuarioNovedad()));
        request.setUsuarioNovedadInsParentescoNuevoUsuNovedadpevcParentescoInstance(parentescoSrv.getByOIDWithHV(request.getUsuarioNovedadInsParentescoNuevoUsuNovedadpevcParentesco()));
        return usuarioNovedadSrv.insParentescoNuevoUsuNovedadImplementation(request, true);
    }

    /*
     * Implements service InsParentescoNuevoUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insParentescoNuevoUsuNovedadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsParentescoNuevoUsuNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        UsuarioNovedad usuarioNovedadInsParentescoNuevoUsuNovedadpthisUsuarioNovedad = request.getUsuarioNovedadInsParentescoNuevoUsuNovedadpthisUsuarioNovedadInstance();
        Parentesco usuarioNovedadInsParentescoNuevoUsuNovedadpevcParentesco = request.getUsuarioNovedadInsParentescoNuevoUsuNovedadpevcParentescoInstance();

        request.checkArguments();
        UsuarioNovedad instance = usuarioNovedadInsParentescoNuevoUsuNovedadpthisUsuarioNovedad;

        instance.add2ParentescoNuevo(usuarioNovedadInsParentescoNuevoUsuNovedadpevcParentesco);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.InsParentescoNuevoUsuNovedadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.InsParentescoNuevoUsuNovedadRequest();
            relatedRequest.setParentescoInsParentescoNuevoUsuNovedadpthisParentescoInstance(usuarioNovedadInsParentescoNuevoUsuNovedadpevcParentesco);
            relatedRequest.setParentescoInsParentescoNuevoUsuNovedadpevcUsuarioNovedadInstance(usuarioNovedadInsParentescoNuevoUsuNovedadpthisUsuarioNovedad);
            parentescoSrv.insParentescoNuevoUsuNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuarioNovedad());

        return response;
    }

    // Service: DelParentescoNuevoUsuNovedad

    /*
     * Entry point for service DelParentescoNuevoUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delParentescoNuevoUsuNovedad(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelParentescoNuevoUsuNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioNovedadSrv.delParentescoNuevoUsuNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelParentescoNuevoUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delParentescoNuevoUsuNovedadTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelParentescoNuevoUsuNovedadRequest request) throws ModelException {
        request.setUsuarioNovedadDelParentescoNuevoUsuNovedadpthisUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getUsuarioNovedadDelParentescoNuevoUsuNovedadpthisUsuarioNovedad()));
        request.setUsuarioNovedadDelParentescoNuevoUsuNovedadpevcParentescoInstance(parentescoSrv.getByOIDWithHV(request.getUsuarioNovedadDelParentescoNuevoUsuNovedadpevcParentesco()));
        return usuarioNovedadSrv.delParentescoNuevoUsuNovedadImplementation(request, true);
    }

    /*
     * Implements service DelParentescoNuevoUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delParentescoNuevoUsuNovedadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelParentescoNuevoUsuNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        UsuarioNovedad usuarioNovedadDelParentescoNuevoUsuNovedadpthisUsuarioNovedad = request.getUsuarioNovedadDelParentescoNuevoUsuNovedadpthisUsuarioNovedadInstance();
        Parentesco usuarioNovedadDelParentescoNuevoUsuNovedadpevcParentesco = request.getUsuarioNovedadDelParentescoNuevoUsuNovedadpevcParentescoInstance();

        request.checkArguments();
        UsuarioNovedad instance = usuarioNovedadDelParentescoNuevoUsuNovedadpthisUsuarioNovedad;

        instance.add2ParentescoNuevo(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.DelParentescoNuevoUsuNovedadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.DelParentescoNuevoUsuNovedadRequest();
            relatedRequest.setParentescoDelParentescoNuevoUsuNovedadpthisParentescoInstance(usuarioNovedadDelParentescoNuevoUsuNovedadpevcParentesco);
            relatedRequest.setParentescoDelParentescoNuevoUsuNovedadpevcUsuarioNovedadInstance(usuarioNovedadDelParentescoNuevoUsuNovedadpthisUsuarioNovedad);
            parentescoSrv.delParentescoNuevoUsuNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuarioNovedad());

        return response;
    }

    // Service: InsTipoIdCDUsuNovedad

    /*
     * Entry point for service InsTipoIdCDUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse insTipoIdCDUsuNovedad(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsTipoIdCDUsuNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioNovedadSrv.insTipoIdCDUsuNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service InsTipoIdCDUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse insTipoIdCDUsuNovedadTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsTipoIdCDUsuNovedadRequest request) throws ModelException {
        request.setUsuarioNovedadInsTipoIdCDUsuNovedadpthisUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getUsuarioNovedadInsTipoIdCDUsuNovedadpthisUsuarioNovedad()));
        request.setUsuarioNovedadInsTipoIdCDUsuNovedadpevcTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getUsuarioNovedadInsTipoIdCDUsuNovedadpevcTipoIdentificacion()));
        return usuarioNovedadSrv.insTipoIdCDUsuNovedadImplementation(request, true);
    }

    /*
     * Implements service InsTipoIdCDUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse insTipoIdCDUsuNovedadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.InsTipoIdCDUsuNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        UsuarioNovedad usuarioNovedadInsTipoIdCDUsuNovedadpthisUsuarioNovedad = request.getUsuarioNovedadInsTipoIdCDUsuNovedadpthisUsuarioNovedadInstance();
        TipoIdentificacion usuarioNovedadInsTipoIdCDUsuNovedadpevcTipoIdentificacion = request.getUsuarioNovedadInsTipoIdCDUsuNovedadpevcTipoIdentificacionInstance();

        request.checkArguments();
        UsuarioNovedad instance = usuarioNovedadInsTipoIdCDUsuNovedadpthisUsuarioNovedad;

        instance.add2TipoIdentificacionCD(usuarioNovedadInsTipoIdCDUsuNovedadpevcTipoIdentificacion);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdCDUsuNovedadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.InsTipoIdCDUsuNovedadRequest();
            relatedRequest.setTipoIdentificacionInsTipoIdCDUsuNovedadpthisTipoIdentificacionInstance(usuarioNovedadInsTipoIdCDUsuNovedadpevcTipoIdentificacion);
            relatedRequest.setTipoIdentificacionInsTipoIdCDUsuNovedadpevcUsuarioNovedadInstance(usuarioNovedadInsTipoIdCDUsuNovedadpthisUsuarioNovedad);
            tipoIdentificacionSrv.insTipoIdCDUsuNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuarioNovedad());

        return response;
    }

    // Service: DelTipoIdCDUsuNovedad

    /*
     * Entry point for service DelTipoIdCDUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse delTipoIdCDUsuNovedad(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelTipoIdCDUsuNovedadRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = usuarioNovedadSrv.delTipoIdCDUsuNovedadTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for service DelTipoIdCDUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @returns Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse delTipoIdCDUsuNovedadTxn(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelTipoIdCDUsuNovedadRequest request) throws ModelException {
        request.setUsuarioNovedadDelTipoIdCDUsuNovedadpthisUsuarioNovedadInstance(usuarioNovedadSrv.getByOIDWithHV(request.getUsuarioNovedadDelTipoIdCDUsuNovedadpthisUsuarioNovedad()));
        request.setUsuarioNovedadDelTipoIdCDUsuNovedadpevcTipoIdentificacionInstance(tipoIdentificacionSrv.getByOIDWithHV(request.getUsuarioNovedadDelTipoIdCDUsuNovedadpevcTipoIdentificacion()));
        return usuarioNovedadSrv.delTipoIdCDUsuNovedadImplementation(request, true);
    }

    /*
     * Implements service DelTipoIdCDUsuNovedad
     *
     * @param request Service request viewmodel
     *
     * @return Service response ViewModel encapsulating the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse delTipoIdCDUsuNovedadImplementation(
            com.keralty.aeconfiguraciones.viewmodel.usuarionovedad.siu.DelTipoIdCDUsuNovedadRequest request, boolean isEntryPoint) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        UsuarioNovedad usuarioNovedadDelTipoIdCDUsuNovedadpthisUsuarioNovedad = request.getUsuarioNovedadDelTipoIdCDUsuNovedadpthisUsuarioNovedadInstance();
        TipoIdentificacion usuarioNovedadDelTipoIdCDUsuNovedadpevcTipoIdentificacion = request.getUsuarioNovedadDelTipoIdCDUsuNovedadpevcTipoIdentificacionInstance();

        request.checkArguments();
        UsuarioNovedad instance = usuarioNovedadDelTipoIdCDUsuNovedadpthisUsuarioNovedad;

        instance.add2TipoIdentificacionCD(null);
        if (isEntryPoint) {
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdCDUsuNovedadRequest relatedRequest = new com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.DelTipoIdCDUsuNovedadRequest();
            relatedRequest.setTipoIdentificacionDelTipoIdCDUsuNovedadpthisTipoIdentificacionInstance(usuarioNovedadDelTipoIdCDUsuNovedadpevcTipoIdentificacion);
            relatedRequest.setTipoIdentificacionDelTipoIdCDUsuNovedadpevcUsuarioNovedadInstance(usuarioNovedadDelTipoIdCDUsuNovedadpthisUsuarioNovedad);
            tipoIdentificacionSrv.delTipoIdCDUsuNovedadImplementation(relatedRequest, false);
        }


        response.setInstance(repository.save(instance));
        response.setInstanceSuppInfo(instance.buildICUsuarioNovedad());

        return response;
    }


    private void suppInfo4ICUsuarioNovedad(String searchText, SQLSelect select) {
        try {
            SQLWhereDisjunction disjunction = new SQLWhereDisjunction();

            SQLWhereComparison comparison1 = new SQLWhereComparison(select);
            comparison1.setOperator("LIKE");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(UsuarioNovedadConstants.ATTR_NAME_USUARIONOVEDADDRVNOMBRECOMPLETO, Constants.Type.STRING.getTypeName()));
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
        select.getOrderByPart().addItem(Constants.normalizePath(UsuarioNovedadConstants.CLASS_NAME, UsuarioNovedadConstants.ATTR_FIELD_USUARIONOVEDADIDUSUARIONOVEDAD, false), true);
    }
    
    @Override
    protected AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePathFirstItem, String remainingPath) {
        AbstractAppEntity relatedInstance = null;
        if (relatedInstancePathFirstItem.isEmpty()) {
            relatedInstance = buildUsuarioNovedadRelatedInstance(relatedInstanceJsonOid);
        }
        if (UsuarioNovedadConstants.ROLE_NAME_FAMILIANOVEDAD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildUsuarioNovedadRelatedInstance4FamiliaNovedad(relatedInstanceJsonOid, remainingPath);
        }
        if (UsuarioNovedadConstants.ROLE_NAME_TIPOIDENTIFICACION.equalsIgnoreCase(relatedInstancePathFirstItem) || UsuarioNovedadConstants.ROLE_NAME_TIPOIDENTIFICACIONCD.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildUsuarioNovedadRelatedInstance4TipoIdentificacion(relatedInstanceJsonOid, remainingPath);
        }
        if (UsuarioNovedadConstants.ROLE_NAME_PARENTESCO.equalsIgnoreCase(relatedInstancePathFirstItem) || UsuarioNovedadConstants.ROLE_NAME_PARENTESCONUEVO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildUsuarioNovedadRelatedInstance4Parentesco(relatedInstanceJsonOid, remainingPath);
        }
        if (UsuarioNovedadConstants.ROLE_NAME_ESTADOBENEFICIARIO.equalsIgnoreCase(relatedInstancePathFirstItem)) {
            relatedInstance = buildUsuarioNovedadRelatedInstance4EstadoBeneficiario(relatedInstanceJsonOid, remainingPath);
        }
        return relatedInstance;
    }
    
    private AbstractAppEntity buildUsuarioNovedadRelatedInstance(String relatedInstanceJsonOid) {
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		return new UsuarioNovedad(mapper.readValue(relatedInstanceJsonOid, UsuarioNovedadOid.class));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildUsuarioNovedadRelatedInstance4FamiliaNovedad(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new FamiliaNovedad(mapper.readValue(relatedInstanceJsonOid, FamiliaNovedadOid.class)): FamiliaNovedad.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildUsuarioNovedadRelatedInstance4TipoIdentificacion(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new TipoIdentificacion(mapper.readValue(relatedInstanceJsonOid, TipoIdentificacionOid.class)): TipoIdentificacion.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildUsuarioNovedadRelatedInstance4Parentesco(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new Parentesco(mapper.readValue(relatedInstanceJsonOid, ParentescoOid.class)): Parentesco.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    private AbstractAppEntity buildUsuarioNovedadRelatedInstance4EstadoBeneficiario(String relatedInstanceJsonOid, String remainingPath) {
        ObjectMapper mapper = new ObjectMapper();
    	try {
            return (remainingPath.isEmpty() ? new EstadoBeneficiario(mapper.readValue(relatedInstanceJsonOid, EstadoBeneficiarioOid.class)): EstadoBeneficiario.buildRelatedInstance(relatedInstanceJsonOid, remainingPath));
    	} catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            throw new SystemException(e.getMessage());
    	}
    }

    @Override
    protected void applyFilterByOid(String jsonOid, SQLSelect sql) {
        try {
            UsuarioNovedadOid oid = new UsuarioNovedadOid().fromJsonString(jsonOid);
            SQLWhereComparison comparison1 = new SQLWhereComparison(sql);
            comparison1.setOperator("=");
            comparison1.setFirstOperand(new SQLWhereOperandPathAttribute(UsuarioNovedadConstants.ATTR_FIELD_USUARIONOVEDADIDUSUARIONOVEDAD, Constants.Type.AUTONUMERIC.getTypeName()));
            comparison1.setSecondOperand(new SQLWhereOperandLiteral(oid.getUsuarioNovedadidUsuarioNovedad(), Constants.Type.AUTONUMERIC.getTypeName()));
            sql.addComparison(comparison1);
        } catch (Exception e) {
            LoggerFactory.getLogger(UsuarioNovedadService.class).error(e.getMessage());
            throw new SystemException(e.getMessage());
        }
    }

}
