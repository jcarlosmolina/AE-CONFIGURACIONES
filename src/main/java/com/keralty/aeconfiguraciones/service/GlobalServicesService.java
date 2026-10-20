package com.keralty.aeconfiguraciones.service;

import com.keralty.aeconfiguraciones.exception.ModelException;
import com.keralty.aeconfiguraciones.exception.PreconditionException;
import com.keralty.aeconfiguraciones.exception.SystemException;
import com.keralty.aeconfiguraciones.global.FuncionarioConstants;
import com.keralty.aeconfiguraciones.global.STDFunctions;
import com.keralty.aeconfiguraciones.global.UserFunctions;
import com.keralty.aeconfiguraciones.global.UsuarioSistemaExternoConstants;
import com.keralty.aeconfiguraciones.persistence.Companyia;
import com.keralty.aeconfiguraciones.persistence.ContratoCombo;
import com.keralty.aeconfiguraciones.persistence.ContratosExentosFIngreso;
import com.keralty.aeconfiguraciones.persistence.ContSinDocSol;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.EntidadFinancieraC;
import com.keralty.aeconfiguraciones.persistence.Eps;
import com.keralty.aeconfiguraciones.persistence.EstadoBeneficiario;
import com.keralty.aeconfiguraciones.persistence.EstadoCivil;
import com.keralty.aeconfiguraciones.persistence.EstadoContrato;
import com.keralty.aeconfiguraciones.persistence.EstadoPersona;
import com.keralty.aeconfiguraciones.persistence.FechaIniEPS;
import com.keralty.aeconfiguraciones.persistence.FormaPago;
import com.keralty.aeconfiguraciones.persistence.FrecuenciaAct;
import com.keralty.aeconfiguraciones.persistence.Funcionario;
import com.keralty.aeconfiguraciones.persistence.GrupoAsociado;
import com.keralty.aeconfiguraciones.persistence.Municipio;
import com.keralty.aeconfiguraciones.persistence.oid.PlanesOid;
import com.keralty.aeconfiguraciones.persistence.oid.ProductosOid;
import com.keralty.aeconfiguraciones.persistence.Pais;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.persistence.Periodicidad;
import com.keralty.aeconfiguraciones.persistence.Planes;
import com.keralty.aeconfiguraciones.persistence.PlantillaReporte;
import com.keralty.aeconfiguraciones.persistence.Productos;
import com.keralty.aeconfiguraciones.persistence.Regional;
import com.keralty.aeconfiguraciones.persistence.Sucursal;
import com.keralty.aeconfiguraciones.persistence.Tarifa;
import com.keralty.aeconfiguraciones.persistence.TipoDireccion;
import com.keralty.aeconfiguraciones.persistence.TipoEntidadC;
import com.keralty.aeconfiguraciones.persistence.TipoIdentificacion;
import com.keralty.aeconfiguraciones.persistence.TipoInstitucionC;
import com.keralty.aeconfiguraciones.persistence.TipoSucursalC;
import com.keralty.aeconfiguraciones.persistence.UsuarioSistemaExterno;
import com.keralty.aeconfiguraciones.repository.GlobalServicesJpaRepository;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.sql.Date;
import org.slf4j.LoggerFactory;

@Transactional
@ApplicationScoped
public class GlobalServicesService extends AbstractService {

    GlobalServicesService globalServicesSrv;

    UserFunctions userFunctionsSrv;

    ContratoComboService contratoComboSrv;

    LogContratoComboService logContratoComboSrv;

    ContSinDocSolService contSinDocSolSrv;

    LogContSinDocSolService logContSinDocSolSrv;

    PlantillaReporteService plantillaReporteSrv;

    ContratosExentosFIngresoService contratosExentosFIngresoSrv;

    LogContratosExentosFIngresoService logContratosExentosFIngresoSrv;

    FechaIniEPSService fechaIniEPSSrv;

    ProductosService productosSrv;

    PlanesService planesSrv;

    TarifaService tarifaSrv;

    CompanyiaService companyiaSrv;

    DepartamentoService departamentoSrv;

    EntidadFinancieraCService entidadFinancieraCSrv;

    EpsService epsSrv;

    EstadoBeneficiarioService estadoBeneficiarioSrv;

    EstadoCivilService estadoCivilSrv;

    EstadoContratoService estadoContratoSrv;

    EstadoPersonaService estadoPersonaSrv;

    FormaPagoService formaPagoSrv;

    GrupoAsociadoService grupoAsociadoSrv;

    MunicipioService municipioSrv;

    PaisService paisSrv;

    ParentescoService parentescoSrv;

    PeriodicidadService periodicidadSrv;

    RegionalService regionalSrv;

    SucursalService sucursalSrv;

    TipoDireccionService tipoDireccionSrv;

    TipoEntidadCService tipoEntidadCSrv;

    TipoIdentificacionService tipoIdentificacionSrv;

    TipoInstitucionCService tipoInstitucionCSrv;

    TipoSucursalCService tipoSucursalCSrv;

    FrecuenciaActService frecuenciaActSrv;

    GlobalServicesJpaRepository globalServicesRepository;

    @Inject
    public GlobalServicesService(
            ContratoComboService contratoComboSrv,
            LogContratoComboService logContratoComboSrv,
            ContSinDocSolService contSinDocSolSrv,
            LogContSinDocSolService logContSinDocSolSrv,
            PlantillaReporteService plantillaReporteSrv,
            ContratosExentosFIngresoService contratosExentosFIngresoSrv,
            LogContratosExentosFIngresoService logContratosExentosFIngresoSrv,
            FechaIniEPSService fechaIniEPSSrv,
            ProductosService productosSrv,
            PlanesService planesSrv,
            TarifaService tarifaSrv,
            CompanyiaService companyiaSrv,
            DepartamentoService departamentoSrv,
            EntidadFinancieraCService entidadFinancieraCSrv,
            EpsService epsSrv,
            EstadoBeneficiarioService estadoBeneficiarioSrv,
            EstadoCivilService estadoCivilSrv,
            EstadoContratoService estadoContratoSrv,
            EstadoPersonaService estadoPersonaSrv,
            FormaPagoService formaPagoSrv,
            GrupoAsociadoService grupoAsociadoSrv,
            MunicipioService municipioSrv,
            PaisService paisSrv,
            ParentescoService parentescoSrv,
            PeriodicidadService periodicidadSrv,
            RegionalService regionalSrv,
            SucursalService sucursalSrv,
            TipoDireccionService tipoDireccionSrv,
            TipoEntidadCService tipoEntidadCSrv,
            TipoIdentificacionService tipoIdentificacionSrv,
            TipoInstitucionCService tipoInstitucionCSrv,
            TipoSucursalCService tipoSucursalCSrv,
            FrecuenciaActService frecuenciaActSrv,
            GlobalServicesJpaRepository globalServicesRepository,
            UserFunctions userFunctionsSrv,
            GlobalServicesService globalServicesSrv) {
        this.contratoComboSrv = contratoComboSrv;
        this.logContratoComboSrv = logContratoComboSrv;
        this.contSinDocSolSrv = contSinDocSolSrv;
        this.logContSinDocSolSrv = logContSinDocSolSrv;
        this.plantillaReporteSrv = plantillaReporteSrv;
        this.contratosExentosFIngresoSrv = contratosExentosFIngresoSrv;
        this.logContratosExentosFIngresoSrv = logContratosExentosFIngresoSrv;
        this.fechaIniEPSSrv = fechaIniEPSSrv;
        this.productosSrv = productosSrv;
        this.planesSrv = planesSrv;
        this.tarifaSrv = tarifaSrv;
        this.companyiaSrv = companyiaSrv;
        this.departamentoSrv = departamentoSrv;
        this.entidadFinancieraCSrv = entidadFinancieraCSrv;
        this.epsSrv = epsSrv;
        this.estadoBeneficiarioSrv = estadoBeneficiarioSrv;
        this.estadoCivilSrv = estadoCivilSrv;
        this.estadoContratoSrv = estadoContratoSrv;
        this.estadoPersonaSrv = estadoPersonaSrv;
        this.formaPagoSrv = formaPagoSrv;
        this.grupoAsociadoSrv = grupoAsociadoSrv;
        this.municipioSrv = municipioSrv;
        this.paisSrv = paisSrv;
        this.parentescoSrv = parentescoSrv;
        this.periodicidadSrv = periodicidadSrv;
        this.regionalSrv = regionalSrv;
        this.sucursalSrv = sucursalSrv;
        this.tipoDireccionSrv = tipoDireccionSrv;
        this.tipoEntidadCSrv = tipoEntidadCSrv;
        this.tipoIdentificacionSrv = tipoIdentificacionSrv;
        this.tipoInstitucionCSrv = tipoInstitucionCSrv;
        this.tipoSucursalCSrv = tipoSucursalCSrv;
        this.frecuenciaActSrv = frecuenciaActSrv;
        this.globalServicesRepository = globalServicesRepository;
        this.userFunctionsSrv = userFunctionsSrv;
        this.globalServicesSrv = globalServicesSrv;
    }

    /*
     * Implements global service TGSINC1PRODUCTO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1PRODUCTOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PRODUCTORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1PRODUCTOptpatrNumero = request.getTGSINC1PRODUCTOptpatrNumero();
        String tGSINC1PRODUCTOptpatrDescripcion = request.getTGSINC1PRODUCTOptpatrDescripcion();
        String tGSINC1PRODUCTOptpatrCodigoLegal = request.getTGSINC1PRODUCTOptpatrCodigoLegal();

        request.checkArguments();

        /* Action 1: IF (EXIST (Productos) WHERE (Productos.Producto = ptpatrNumero) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator002(tGSINC1PRODUCTOptpatrNumero).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.productos.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.productos.siu.CrearRequest();
            step10Request.setProductoscrearpatrProducto(tGSINC1PRODUCTOptpatrNumero);
            step10Request.setProductoscrearpatrDescripcion(tGSINC1PRODUCTOptpatrDescripcion);
            step10Request.setProductoscrearpatrCodAnexoMat(null);
            step10Request.setProductoscrearpatrCodAnexoNeo(null);
            step10Request.setProductoscrearpatrNIT(null);
            step10Request.setProductoscrearpatrCodigoLegal(tGSINC1PRODUCTOptpatrCodigoLegal);
            productosSrv.crearImplementation(step10Request);
        } else {
            for(Productos step111Entity : globalServicesRepository.assocOperator001(tGSINC1PRODUCTOptpatrNumero)) {
                com.keralty.aeconfiguraciones.viewmodel.productos.siu.SincronizarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.productos.siu.SincronizarRequest();
                step11Request.setProductossincronizarpthisProductoInstance(step111Entity);
                step11Request.setProductossincronizarpDescripcion(tGSINC1PRODUCTOptpatrDescripcion);
                step11Request.setProductossincronizarpCodigoLegal(tGSINC1PRODUCTOptpatrCodigoLegal);
                productosSrv.sincronizarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service TGSINCPRODUCTOS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCPRODUCTOS(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPRODUCTOSRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCPRODUCTOSTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCPRODUCTOS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCPRODUCTOSTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPRODUCTOSRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCPRODUCTOSImplementation(request);
    }

    /*
     * Implements global service TGSINCPRODUCTOS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCPRODUCTOSImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPRODUCTOSRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PRODUCTORequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PRODUCTORequest();
        step1Request.setTGSINC1PRODUCTOptpatrNumero(Long.valueOf(0));
        step1Request.setTGSINC1PRODUCTOptpatrDescripcion("0");
        step1Request.setTGSINC1PRODUCTOptpatrCodigoLegal("0");
        globalServicesSrv.tGSINC1PRODUCTOImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(2));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1PLAN
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1PLANImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PLANRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1PLANptpatrNumero = request.getTGSINC1PLANptpatrNumero();
        Long tGSINC1PLANptpatrCodigo = request.getTGSINC1PLANptpatrCodigo();
        String tGSINC1PLANptpatrNombre = request.getTGSINC1PLANptpatrNombre();
        String tGSINC1PLANptpatrCodigoLegal = request.getTGSINC1PLANptpatrCodigoLegal();

        request.checkArguments();

        /* Action 1: IF (EXIST (Productos) WHERE (Productos.Producto = ptpatrNumero) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator005(tGSINC1PLANptpatrNumero).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.productos.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.productos.siu.CrearRequest();
            step10Request.setProductoscrearpatrProducto(tGSINC1PLANptpatrNumero);
            step10Request.setProductoscrearpatrDescripcion("Pendiente de sincronización");
            step10Request.setProductoscrearpatrCodAnexoMat(null);
            step10Request.setProductoscrearpatrCodAnexoNeo(null);
            step10Request.setProductoscrearpatrNIT(null);
            step10Request.setProductoscrearpatrCodigoLegal("XXX");
            productosSrv.crearImplementation(step10Request);
        }

        for(Productos step2Entity : globalServicesRepository.assocOperator004(tGSINC1PLANptpatrNumero)) {
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PLANAUXRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PLANAUXRequest();
            step2Request.setTGSINC1PLANAUXptpagrProductoInstance(step2Entity);
            step2Request.setTGSINC1PLANAUXptpatrCodigo(tGSINC1PLANptpatrCodigo);
            step2Request.setTGSINC1PLANAUXptpatrNombre(tGSINC1PLANptpatrNombre);
            step2Request.setTGSINC1PLANAUXptpatrCodigoLegal(tGSINC1PLANptpatrCodigoLegal);
            globalServicesSrv.tGSINC1PLANAUXImplementation(step2Request);
        }
        return response;
    }

    /*
     * Implements global service TGSINC1PLANAUX
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1PLANAUXImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PLANAUXRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Productos tGSINC1PLANAUXptpagrProducto = request.getTGSINC1PLANAUXptpagrProductoInstance();
        Long tGSINC1PLANAUXptpatrCodigo = request.getTGSINC1PLANAUXptpatrCodigo();
        String tGSINC1PLANAUXptpatrNombre = request.getTGSINC1PLANAUXptpatrNombre();
        String tGSINC1PLANAUXptpatrCodigoLegal = request.getTGSINC1PLANAUXptpatrCodigoLegal();

        request.checkArguments();

        /* Action 1: IF (EXIST (Planes) WHERE (Planes.Plan = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator008(tGSINC1PLANAUXptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.planes.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.planes.siu.CrearRequest();
            step10Request.setPlanescrearpagrProductoTInstance(tGSINC1PLANAUXptpagrProducto);
            step10Request.setPlanescrearpatrPlan(tGSINC1PLANAUXptpatrCodigo);
            step10Request.setPlanescrearpatrNombre(tGSINC1PLANAUXptpatrNombre);
            step10Request.setPlanescrearpatrEsBancoRep(Boolean.FALSE);
            step10Request.setPlanescrearpatrCodigoLegal(tGSINC1PLANAUXptpatrCodigoLegal);
            planesSrv.crearImplementation(step10Request);
        } else {
            for(Planes step111Entity : globalServicesRepository.assocOperator007(tGSINC1PLANAUXptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.planes.siu.TSINCRONIZARRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.planes.siu.TSINCRONIZARRequest();
                step11Request.setPlanesTSINCRONIZARpthisPlanInstance(step111Entity);
                step11Request.setPlanesTSINCRONIZARptpNombre(tGSINC1PLANAUXptpatrNombre);
                step11Request.setPlanesTSINCRONIZARptpevcProductoInstance(tGSINC1PLANAUXptpagrProducto);
                step11Request.setPlanesTSINCRONIZARpCodigoLegal(tGSINC1PLANAUXptpatrCodigoLegal);
                planesSrv.tSINCRONIZARImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service TGSINCPLANES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCPLANES(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPLANESRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCPLANESTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCPLANES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCPLANESTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPLANESRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCPLANESImplementation(request);
    }

    /*
     * Implements global service TGSINCPLANES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCPLANESImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPLANESRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PLANRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PLANRequest();
        step1Request.setTGSINC1PLANptpatrNumero(Long.valueOf(0));
        step1Request.setTGSINC1PLANptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1PLANptpatrNombre("0");
        step1Request.setTGSINC1PLANptpatrCodigoLegal("0");
        globalServicesSrv.tGSINC1PLANImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(1));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1PARENTESCO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1PARENTESCOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PARENTESCORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1PARENTESCOptpatrCodigo = request.getTGSINC1PARENTESCOptpatrCodigo();
        String tGSINC1PARENTESCOptpatrDescripcion = request.getTGSINC1PARENTESCOptpatrDescripcion();
        String tGSINC1PARENTESCOptpatrNombreCorto = request.getTGSINC1PARENTESCOptpatrNombreCorto();

        request.checkArguments();

        /* Action 1: IF (EXIST (Parentesco) WHERE (Parentesco.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator011(tGSINC1PARENTESCOptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.CrearRequest();
            step10Request.setParentescocrearpatrCodigo(tGSINC1PARENTESCOptpatrCodigo);
            step10Request.setParentescocrearpatrDescripcion(tGSINC1PARENTESCOptpatrDescripcion);
            step10Request.setParentescocrearpatrEsTitular(Boolean.FALSE);
            step10Request.setParentescocrearpatrEsConyuge(Boolean.FALSE);
            step10Request.setParentescocrearpatrEsPadre(Boolean.FALSE);
            step10Request.setParentescocrearpatrEsHijo(Boolean.FALSE);
            step10Request.setParentescocrearpatrNombreCorto(tGSINC1PARENTESCOptpatrNombreCorto);
            parentescoSrv.crearImplementation(step10Request);
        } else {
            for(Parentesco step111Entity : globalServicesRepository.assocOperator010(tGSINC1PARENTESCOptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.SincronizarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.SincronizarRequest();
                step11Request.setParentescosincronizarpthisParentescoInstance(step111Entity);
                step11Request.setParentescosincronizarpDescripcion(tGSINC1PARENTESCOptpatrDescripcion);
                step11Request.setParentescosincronizarpNombreCorto(tGSINC1PARENTESCOptpatrNombreCorto);
                parentescoSrv.sincronizarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service TGSINCPARENTESCOS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCPARENTESCOS(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPARENTESCOSRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCPARENTESCOSTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCPARENTESCOS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCPARENTESCOSTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPARENTESCOSRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCPARENTESCOSImplementation(request);
    }

    /*
     * Implements global service TGSINCPARENTESCOS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCPARENTESCOSImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPARENTESCOSRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PARENTESCORequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PARENTESCORequest();
        step1Request.setTGSINC1PARENTESCOptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1PARENTESCOptpatrDescripcion("0");
        step1Request.setTGSINC1PARENTESCOptpatrNombreCorto("0");
        globalServicesSrv.tGSINC1PARENTESCOImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(10));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1PERIODICIDAD
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1PERIODICIDADImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PERIODICIDADRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1PERIODICIDADptpatrCodigo = request.getTGSINC1PERIODICIDADptpatrCodigo();
        String tGSINC1PERIODICIDADptpatrDescripcion = request.getTGSINC1PERIODICIDADptpatrDescripcion();

        request.checkArguments();

        /* Action 1: IF (EXIST (Periodicidad) WHERE (Periodicidad.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator014(tGSINC1PERIODICIDADptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.CrearRequest();
            step10Request.setPeriodicidadcrearpatrCodigo(tGSINC1PERIODICIDADptpatrCodigo);
            step10Request.setPeriodicidadcrearpatrDescripcion(tGSINC1PERIODICIDADptpatrDescripcion);
            periodicidadSrv.crearImplementation(step10Request);
        } else {
            for(Periodicidad step111Entity : globalServicesRepository.assocOperator013(tGSINC1PERIODICIDADptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.SincronizarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.periodicidad.siu.SincronizarRequest();
                step11Request.setPeriodicidadsincronizarpthisPeriodicidadInstance(step111Entity);
                step11Request.setPeriodicidadsincronizarpDescripcion(tGSINC1PERIODICIDADptpatrDescripcion);
                periodicidadSrv.sincronizarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service TGSINCPERIODICIDADES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCPERIODICIDADES(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPERIODICIDADESRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCPERIODICIDADESTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCPERIODICIDADES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCPERIODICIDADESTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPERIODICIDADESRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCPERIODICIDADESImplementation(request);
    }

    /*
     * Implements global service TGSINCPERIODICIDADES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCPERIODICIDADESImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPERIODICIDADESRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PERIODICIDADRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PERIODICIDADRequest();
        step1Request.setTGSINC1PERIODICIDADptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1PERIODICIDADptpatrDescripcion("0");
        globalServicesSrv.tGSINC1PERIODICIDADImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(12));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1ESTADOCONTRATO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1ESTADOCONTRATOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1ESTADOCONTRATORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1ESTADOCONTRATOptpatrCodigo = request.getTGSINC1ESTADOCONTRATOptpatrCodigo();
        String tGSINC1ESTADOCONTRATOptpatrDescripcion = request.getTGSINC1ESTADOCONTRATOptpatrDescripcion();

        request.checkArguments();

        /* Action 1: IF (EXIST (EstadoContrato) WHERE (EstadoContrato.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator017(tGSINC1ESTADOCONTRATOptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.CrearRequest();
            step10Request.setEstadoContratocrearpatrCodigo(tGSINC1ESTADOCONTRATOptpatrCodigo);
            step10Request.setEstadoContratocrearpatrDescripcion(tGSINC1ESTADOCONTRATOptpatrDescripcion);
            estadoContratoSrv.crearImplementation(step10Request);
        } else {
            for(EstadoContrato step111Entity : globalServicesRepository.assocOperator016(tGSINC1ESTADOCONTRATOptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.SincronizarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.estadocontrato.siu.SincronizarRequest();
                step11Request.setEstadoContratosincronizarpthisEstadoContratoInstance(step111Entity);
                step11Request.setEstadoContratosincronizarpDescripcion(tGSINC1ESTADOCONTRATOptpatrDescripcion);
                estadoContratoSrv.sincronizarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service TGSINCESTADOSCONTRATO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCESTADOSCONTRATO(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSCONTRATORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCESTADOSCONTRATOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCESTADOSCONTRATO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCESTADOSCONTRATOTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSCONTRATORequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCESTADOSCONTRATOImplementation(request);
    }

    /*
     * Implements global service TGSINCESTADOSCONTRATO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCESTADOSCONTRATOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSCONTRATORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1ESTADOCONTRATORequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1ESTADOCONTRATORequest();
        step1Request.setTGSINC1ESTADOCONTRATOptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1ESTADOCONTRATOptpatrDescripcion("0");
        globalServicesSrv.tGSINC1ESTADOCONTRATOImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(17));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1ESTADOBENEFICIARIO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1ESTADOBENEFICIARIOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1ESTADOBENEFICIARIORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1ESTADOBENEFICIARIOptpatrCodigo = request.getTGSINC1ESTADOBENEFICIARIOptpatrCodigo();
        String tGSINC1ESTADOBENEFICIARIOptpatrDescripcion = request.getTGSINC1ESTADOBENEFICIARIOptpatrDescripcion();

        request.checkArguments();

        /* Action 1: IF (EXIST (EstadoBeneficiario) WHERE (EstadoBeneficiario.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator020(tGSINC1ESTADOBENEFICIARIOptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.CrearRequest();
            step10Request.setEstadoBeneficiariocrearpatrCodigo(tGSINC1ESTADOBENEFICIARIOptpatrCodigo);
            step10Request.setEstadoBeneficiariocrearpatrDescripcion(tGSINC1ESTADOBENEFICIARIOptpatrDescripcion);
            estadoBeneficiarioSrv.crearImplementation(step10Request);
        } else {
            for(EstadoBeneficiario step111Entity : globalServicesRepository.assocOperator019(tGSINC1ESTADOBENEFICIARIOptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.SincronizarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.estadobeneficiario.siu.SincronizarRequest();
                step11Request.setEstadoBeneficiariosincronizarpthisEstadoBeneficiarioInstance(step111Entity);
                step11Request.setEstadoBeneficiariosincronizarpDescripcion(tGSINC1ESTADOBENEFICIARIOptpatrDescripcion);
                estadoBeneficiarioSrv.sincronizarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service TGSINCESTADOSBENEFICIARIO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCESTADOSBENEFICIARIO(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSBENEFICIARIORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCESTADOSBENEFICIARIOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCESTADOSBENEFICIARIO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCESTADOSBENEFICIARIOTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSBENEFICIARIORequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCESTADOSBENEFICIARIOImplementation(request);
    }

    /*
     * Implements global service TGSINCESTADOSBENEFICIARIO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCESTADOSBENEFICIARIOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSBENEFICIARIORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1ESTADOBENEFICIARIORequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1ESTADOBENEFICIARIORequest();
        step1Request.setTGSINC1ESTADOBENEFICIARIOptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1ESTADOBENEFICIARIOptpatrDescripcion("0");
        globalServicesSrv.tGSINC1ESTADOBENEFICIARIOImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(20));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1REGIONAL
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1REGIONALImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1REGIONALRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1REGIONALptpatrCodigo = request.getTGSINC1REGIONALptpatrCodigo();
        String tGSINC1REGIONALptpatrDescripcion = request.getTGSINC1REGIONALptpatrDescripcion();

        request.checkArguments();

        /* Action 1: IF (EXIST (Regional) WHERE (Regional.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator023(tGSINC1REGIONALptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.regional.siu.CrearRequest();
            step10Request.setRegionalcrearpagrRespRegionalInstance(null);
            step10Request.setRegionalcrearpatrCodigo(tGSINC1REGIONALptpatrCodigo);
            step10Request.setRegionalcrearpatrDescripcion(tGSINC1REGIONALptpatrDescripcion);
            regionalSrv.crearImplementation(step10Request);
        } else {
            for(Regional step111Entity : globalServicesRepository.assocOperator022(tGSINC1REGIONALptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.regional.siu.SincronizarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.regional.siu.SincronizarRequest();
                step11Request.setRegionalsincronizarpthisRegionalInstance(step111Entity);
                step11Request.setRegionalsincronizarpDescripcion(tGSINC1REGIONALptpatrDescripcion);
                regionalSrv.sincronizarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service TGSINCREGIONALES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCREGIONALES(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCREGIONALESRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCREGIONALESTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCREGIONALES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCREGIONALESTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCREGIONALESRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCREGIONALESImplementation(request);
    }

    /*
     * Implements global service TGSINCREGIONALES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCREGIONALESImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCREGIONALESRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1REGIONALRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1REGIONALRequest();
        step1Request.setTGSINC1REGIONALptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1REGIONALptpatrDescripcion("0");
        globalServicesSrv.tGSINC1REGIONALImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(21));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1SUCURSAL
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1SUCURSALImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1SUCURSALRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1SUCURSALptpatrCodigo = request.getTGSINC1SUCURSALptpatrCodigo();
        String tGSINC1SUCURSALptpatrDescripcion = request.getTGSINC1SUCURSALptpatrDescripcion();
        Long tGSINC1SUCURSALptpatrCodigoSucursal = request.getTGSINC1SUCURSALptpatrCodigoSucursal();

        request.checkArguments();

        /* Action 1: IF (EXIST (Regional) WHERE (Regional.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator026(tGSINC1SUCURSALptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.regional.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.regional.siu.CrearRequest();
            step10Request.setRegionalcrearpagrRespRegionalInstance(null);
            step10Request.setRegionalcrearpatrCodigo(tGSINC1SUCURSALptpatrCodigo);
            step10Request.setRegionalcrearpatrDescripcion("Pendiente de sincronización");
            regionalSrv.crearImplementation(step10Request);
        }

        for(Regional step2Entity : globalServicesRepository.assocOperator025(tGSINC1SUCURSALptpatrCodigo)) {
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1SUCURSALAUXRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1SUCURSALAUXRequest();
            step2Request.setTGSINC1SUCURSALAUXptpagrRegionalInstance(step2Entity);
            step2Request.setTGSINC1SUCURSALAUXptpatrCodigo(tGSINC1SUCURSALptpatrCodigoSucursal);
            step2Request.setTGSINC1SUCURSALAUXptpatrDescripcion(tGSINC1SUCURSALptpatrDescripcion);
            globalServicesSrv.tGSINC1SUCURSALAUXImplementation(step2Request);
        }
        return response;
    }

    /*
     * Implements global service TGSINC1SUCURSALAUX
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1SUCURSALAUXImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1SUCURSALAUXRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Regional tGSINC1SUCURSALAUXptpagrRegional = request.getTGSINC1SUCURSALAUXptpagrRegionalInstance();
        Long tGSINC1SUCURSALAUXptpatrCodigo = request.getTGSINC1SUCURSALAUXptpatrCodigo();
        String tGSINC1SUCURSALAUXptpatrDescripcion = request.getTGSINC1SUCURSALAUXptpatrDescripcion();

        request.checkArguments();

        /* Action 1: IF (EXIST (Sucursal) WHERE (Sucursal.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator029(tGSINC1SUCURSALAUXptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.sucursal.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.sucursal.siu.CrearRequest();
            step10Request.setSucursalcrearpagrRegionalInstance(tGSINC1SUCURSALAUXptpagrRegional);
            step10Request.setSucursalcrearpatrCodigo(tGSINC1SUCURSALAUXptpatrCodigo);
            step10Request.setSucursalcrearpatrDescripcion(tGSINC1SUCURSALAUXptpatrDescripcion);
            sucursalSrv.crearImplementation(step10Request);
        } else {
            for(Sucursal step111Entity : globalServicesRepository.assocOperator028(tGSINC1SUCURSALAUXptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.sucursal.siu.TSINCRONIZARRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.sucursal.siu.TSINCRONIZARRequest();
                step11Request.setSucursalTSINCRONIZARpthisSucursalInstance(step111Entity);
                step11Request.setSucursalTSINCRONIZARptpDescripcion(tGSINC1SUCURSALAUXptpatrDescripcion);
                step11Request.setSucursalTSINCRONIZARptpevcRegionalInstance(tGSINC1SUCURSALAUXptpagrRegional);
                sucursalSrv.tSINCRONIZARImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service OGSINCSUCURSALES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse oGSINCSUCURSALES(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCSUCURSALESRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.oGSINCSUCURSALESTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service OGSINCSUCURSALES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse oGSINCSUCURSALESTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCSUCURSALESRequest request) throws ModelException, SystemException {
        return globalServicesSrv.oGSINCSUCURSALESImplementation(request);
    }

    /*
     * Implements global service OGSINCSUCURSALES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse oGSINCSUCURSALESImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCSUCURSALESRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1SUCURSALRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1SUCURSALRequest();
        step1Request.setTGSINC1SUCURSALptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1SUCURSALptpatrDescripcion("0");
        step1Request.setTGSINC1SUCURSALptpatrCodigoSucursal(Long.valueOf(0));
        globalServicesSrv.tGSINC1SUCURSALImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(16));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1GRUPOASOCAUX2
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1GRUPOASOCAUX2Implementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1GRUPOASOCAUX2Request request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Productos tGSINC1GRUPOASOCAUX2ptpagrProducto = request.getTGSINC1GRUPOASOCAUX2ptpagrProductoInstance();
        Planes tGSINC1GRUPOASOCAUX2ptpagrPlan = request.getTGSINC1GRUPOASOCAUX2ptpagrPlanInstance();
        String tGSINC1GRUPOASOCAUX2ptpatrCodigoLegal = request.getTGSINC1GRUPOASOCAUX2ptpatrCodigoLegal();
        String tGSINC1GRUPOASOCAUX2ptpatrDescripcion = request.getTGSINC1GRUPOASOCAUX2ptpatrDescripcion();
        Date tGSINC1GRUPOASOCAUX2ptpatrFIniVigencia = request.getTGSINC1GRUPOASOCAUX2ptpatrFIniVigencia();
        Date tGSINC1GRUPOASOCAUX2ptpatrFFinVigencia = request.getTGSINC1GRUPOASOCAUX2ptpatrFFinVigencia();
        Long tGSINC1GRUPOASOCAUX2ptpatrEstado = request.getTGSINC1GRUPOASOCAUX2ptpatrEstado();
        String tGSINC1GRUPOASOCAUX2ptCodTarifaCORE = request.getTGSINC1GRUPOASOCAUX2ptCodTarifaCORE();
        Long tGSINC1GRUPOASOCAUX2ptpatrCodigo = request.getTGSINC1GRUPOASOCAUX2ptpatrCodigo();

        request.checkArguments();

        /* Action 1: IF (EXIST (GrupoAsociado) WHERE (GrupoAsociado.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator032(tGSINC1GRUPOASOCAUX2ptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu.CrearRequest();
            step10Request.setGrupoAsociadocrearpagrProductoTInstance(tGSINC1GRUPOASOCAUX2ptpagrProducto);
            step10Request.setGrupoAsociadocrearpagrPlanTInstance(tGSINC1GRUPOASOCAUX2ptpagrPlan);
            step10Request.setGrupoAsociadocrearpatrCodigoLegal(tGSINC1GRUPOASOCAUX2ptpatrCodigoLegal);
            step10Request.setGrupoAsociadocrearpatrDescripcion(tGSINC1GRUPOASOCAUX2ptpatrDescripcion);
            step10Request.setGrupoAsociadocrearpatrFIniVigencia(tGSINC1GRUPOASOCAUX2ptpatrFIniVigencia);
            step10Request.setGrupoAsociadocrearpatrFFinVigencia(tGSINC1GRUPOASOCAUX2ptpatrFFinVigencia);
            step10Request.setGrupoAsociadocrearpatrEstado(tGSINC1GRUPOASOCAUX2ptpatrEstado);
            step10Request.setGrupoAsociadocrearpatrDisminucionCarencia(Long.valueOf(0));
            step10Request.setGrupoAsociadocrearpatrCodTarifaCORE(tGSINC1GRUPOASOCAUX2ptCodTarifaCORE);
            step10Request.setGrupoAsociadocrearpatrCodigo(tGSINC1GRUPOASOCAUX2ptpatrCodigo);
            grupoAsociadoSrv.crearImplementation(step10Request);
        } else {
            for(GrupoAsociado step111Entity : globalServicesRepository.assocOperator031(tGSINC1GRUPOASOCAUX2ptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu.TSINCRONIZARRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.grupoasociado.siu.TSINCRONIZARRequest();
                step11Request.setGrupoAsociadoTSINCRONIZARpthisGrupoAsociadoInstance(step111Entity);
                step11Request.setGrupoAsociadoTSINCRONIZARptpDescripcion(tGSINC1GRUPOASOCAUX2ptpatrDescripcion);
                step11Request.setGrupoAsociadoTSINCRONIZARptpFIniVigencia(tGSINC1GRUPOASOCAUX2ptpatrFIniVigencia);
                step11Request.setGrupoAsociadoTSINCRONIZARptpFFinVigencia(tGSINC1GRUPOASOCAUX2ptpatrFFinVigencia);
                step11Request.setGrupoAsociadoTSINCRONIZARptpEstado(tGSINC1GRUPOASOCAUX2ptpatrEstado);
                step11Request.setGrupoAsociadoTSINCRONIZARptpevcPlanInstance(tGSINC1GRUPOASOCAUX2ptpagrPlan);
                step11Request.setGrupoAsociadoTSINCRONIZARptpevcProductoInstance(tGSINC1GRUPOASOCAUX2ptpagrProducto);
                step11Request.setGrupoAsociadoTSINCRONIZARptCodTarifaCORE(tGSINC1GRUPOASOCAUX2ptCodTarifaCORE);
                step11Request.setGrupoAsociadoTSINCRONIZARptpCodigoLegal(tGSINC1GRUPOASOCAUX2ptpatrCodigoLegal);
                grupoAsociadoSrv.tSINCRONIZARImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Implements global service TGSINC1GRUPOASOCAUX
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1GRUPOASOCAUXImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1GRUPOASOCAUXRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Productos tGSINC1GRUPOASOCAUXptpagrProducto = request.getTGSINC1GRUPOASOCAUXptpagrProductoInstance();
        String tGSINC1GRUPOASOCAUXptpatrCodigo = request.getTGSINC1GRUPOASOCAUXptpatrCodigo();
        String tGSINC1GRUPOASOCAUXptpatrCodigoLegal = request.getTGSINC1GRUPOASOCAUXptpatrCodigoLegal();
        String tGSINC1GRUPOASOCAUXptpatrDescripcion = request.getTGSINC1GRUPOASOCAUXptpatrDescripcion();
        Date tGSINC1GRUPOASOCAUXptpatrFIniVigencia = request.getTGSINC1GRUPOASOCAUXptpatrFIniVigencia();
        Date tGSINC1GRUPOASOCAUXptpatrFFinVigencia = request.getTGSINC1GRUPOASOCAUXptpatrFFinVigencia();
        Long tGSINC1GRUPOASOCAUXptpatrEstado = request.getTGSINC1GRUPOASOCAUXptpatrEstado();
        String tGSINC1GRUPOASOCAUXptCodTarifaCORE = request.getTGSINC1GRUPOASOCAUXptCodTarifaCORE();
        Long tGSINC1GRUPOASOCAUXptpatrCodigoGA = request.getTGSINC1GRUPOASOCAUXptpatrCodigoGA();

        request.checkArguments();

        for(Planes step1Entity : globalServicesRepository.assocOperator033(tGSINC1GRUPOASOCAUXptpatrCodigo, tGSINC1GRUPOASOCAUXptpagrProducto)) {
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1GRUPOASOCAUX2Request step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1GRUPOASOCAUX2Request();
            step1Request.setTGSINC1GRUPOASOCAUX2ptpagrProductoInstance(tGSINC1GRUPOASOCAUXptpagrProducto);
            step1Request.setTGSINC1GRUPOASOCAUX2ptpagrPlanInstance(step1Entity);
            step1Request.setTGSINC1GRUPOASOCAUX2ptpatrCodigoLegal(tGSINC1GRUPOASOCAUXptpatrCodigoLegal);
            step1Request.setTGSINC1GRUPOASOCAUX2ptpatrDescripcion(tGSINC1GRUPOASOCAUXptpatrDescripcion);
            step1Request.setTGSINC1GRUPOASOCAUX2ptpatrFIniVigencia(tGSINC1GRUPOASOCAUXptpatrFIniVigencia);
            step1Request.setTGSINC1GRUPOASOCAUX2ptpatrFFinVigencia(tGSINC1GRUPOASOCAUXptpatrFFinVigencia);
            step1Request.setTGSINC1GRUPOASOCAUX2ptpatrEstado(tGSINC1GRUPOASOCAUXptpatrEstado);
            step1Request.setTGSINC1GRUPOASOCAUX2ptCodTarifaCORE(tGSINC1GRUPOASOCAUXptCodTarifaCORE);
            step1Request.setTGSINC1GRUPOASOCAUX2ptpatrCodigo(tGSINC1GRUPOASOCAUXptpatrCodigoGA);
            globalServicesSrv.tGSINC1GRUPOASOCAUX2Implementation(step1Request);
        }
        return response;
    }

    /*
     * Entry point for global service TGSINC1GRUPOASOC
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINC1GRUPOASOC(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1GRUPOASOCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINC1GRUPOASOCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINC1GRUPOASOC
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINC1GRUPOASOCTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1GRUPOASOCRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINC1GRUPOASOCImplementation(request);
    }

    /*
     * Implements global service TGSINC1GRUPOASOC
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1GRUPOASOCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1GRUPOASOCRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        String tGSINC1GRUPOASOCptpatrNumero = request.getTGSINC1GRUPOASOCptpatrNumero();
        String tGSINC1GRUPOASOCptpatrCodigo = request.getTGSINC1GRUPOASOCptpatrCodigo();
        String tGSINC1GRUPOASOCptpatrCodigoLegal = request.getTGSINC1GRUPOASOCptpatrCodigoLegal();
        String tGSINC1GRUPOASOCptpatrDescripcion = request.getTGSINC1GRUPOASOCptpatrDescripcion();
        Date tGSINC1GRUPOASOCptpatrFIniVigencia = request.getTGSINC1GRUPOASOCptpatrFIniVigencia();
        Date tGSINC1GRUPOASOCptpatrFFinVigencia = request.getTGSINC1GRUPOASOCptpatrFFinVigencia();
        Long tGSINC1GRUPOASOCptpatrEstado = request.getTGSINC1GRUPOASOCptpatrEstado();
        String tGSINC1GRUPOASOCptCodTarifaCORE = request.getTGSINC1GRUPOASOCptCodTarifaCORE();
        Long tGSINC1GRUPOASOCptpatrCodigoGA = request.getTGSINC1GRUPOASOCptpatrCodigoGA();

        request.checkArguments();

        for(Productos step1Entity : globalServicesRepository.assocOperator034(tGSINC1GRUPOASOCptpatrNumero)) {
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1GRUPOASOCAUXRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1GRUPOASOCAUXRequest();
            step1Request.setTGSINC1GRUPOASOCAUXptpagrProductoInstance(step1Entity);
            step1Request.setTGSINC1GRUPOASOCAUXptpatrCodigo(tGSINC1GRUPOASOCptpatrCodigo);
            step1Request.setTGSINC1GRUPOASOCAUXptpatrCodigoLegal(tGSINC1GRUPOASOCptpatrCodigoLegal);
            step1Request.setTGSINC1GRUPOASOCAUXptpatrDescripcion(tGSINC1GRUPOASOCptpatrDescripcion);
            step1Request.setTGSINC1GRUPOASOCAUXptpatrFIniVigencia(tGSINC1GRUPOASOCptpatrFIniVigencia);
            step1Request.setTGSINC1GRUPOASOCAUXptpatrFFinVigencia(tGSINC1GRUPOASOCptpatrFFinVigencia);
            step1Request.setTGSINC1GRUPOASOCAUXptpatrEstado(tGSINC1GRUPOASOCptpatrEstado);
            step1Request.setTGSINC1GRUPOASOCAUXptCodTarifaCORE(tGSINC1GRUPOASOCptCodTarifaCORE);
            step1Request.setTGSINC1GRUPOASOCAUXptpatrCodigoGA(tGSINC1GRUPOASOCptpatrCodigoGA);
            globalServicesSrv.tGSINC1GRUPOASOCAUXImplementation(step1Request);
        }
        return response;
    }

    /*
     * Entry point for global service OGSINCGRUPOSASOCIADOS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse oGSINCGRUPOSASOCIADOS(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCGRUPOSASOCIADOSRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.oGSINCGRUPOSASOCIADOSTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service OGSINCGRUPOSASOCIADOS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse oGSINCGRUPOSASOCIADOSTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCGRUPOSASOCIADOSRequest request) throws ModelException, SystemException {
        return globalServicesSrv.oGSINCGRUPOSASOCIADOSImplementation(request);
    }

    /*
     * Implements global service OGSINCGRUPOSASOCIADOS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse oGSINCGRUPOSASOCIADOSImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCGRUPOSASOCIADOSRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        try {
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1GRUPOASOCRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1GRUPOASOCRequest();
            step1Request.setTGSINC1GRUPOASOCptpatrNumero("0");
            step1Request.setTGSINC1GRUPOASOCptpatrCodigo("0");
            step1Request.setTGSINC1GRUPOASOCptpatrCodigoLegal("0");
            step1Request.setTGSINC1GRUPOASOCptpatrDescripcion("0");
            step1Request.setTGSINC1GRUPOASOCptpatrFIniVigencia(STDFunctions.systemDateFun());
            step1Request.setTGSINC1GRUPOASOCptpatrFFinVigencia(STDFunctions.systemDateFun());
            step1Request.setTGSINC1GRUPOASOCptpatrEstado(Long.valueOf(0));
            step1Request.setTGSINC1GRUPOASOCptCodTarifaCORE("0");
            step1Request.setTGSINC1GRUPOASOCptpatrCodigoGA(Long.valueOf(0));
            globalServicesSrv.tGSINC1GRUPOASOCTxn(step1Request);
        } catch (Exception e) { }

        try {
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
            step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(3));
            step2Request.setTGFIJARDATOSSINCptResultado("Exito");
            globalServicesSrv.tGFIJARDATOSSINCTxn(step2Request);
        } catch (Exception e) { }
        return response;
    }

    /*
     * Implements global service TGSINC1ESTADOCIVIL
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1ESTADOCIVILImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1ESTADOCIVILRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1ESTADOCIVILptpatrCodigo = request.getTGSINC1ESTADOCIVILptpatrCodigo();
        String tGSINC1ESTADOCIVILptpatrDescripcion = request.getTGSINC1ESTADOCIVILptpatrDescripcion();
        String tGSINC1ESTADOCIVILptpatrNombreCorto = request.getTGSINC1ESTADOCIVILptpatrNombreCorto();

        request.checkArguments();

        /* Action 1: IF (EXIST (EstadoCivil) WHERE (EstadoCivil.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator037(tGSINC1ESTADOCIVILptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.CrearRequest();
            step10Request.setEstadoCivilcrearpatrCodigo(tGSINC1ESTADOCIVILptpatrCodigo);
            step10Request.setEstadoCivilcrearpatrDescripcion(tGSINC1ESTADOCIVILptpatrDescripcion);
            step10Request.setEstadoCivilcrearpatrNombreCorto(tGSINC1ESTADOCIVILptpatrNombreCorto);
            estadoCivilSrv.crearImplementation(step10Request);
        } else {
            for(EstadoCivil step111Entity : globalServicesRepository.assocOperator036(tGSINC1ESTADOCIVILptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.SincronizarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.estadocivil.siu.SincronizarRequest();
                step11Request.setEstadoCivilsincronizarpthisEstadoCivilInstance(step111Entity);
                step11Request.setEstadoCivilsincronizarpDescripcion(tGSINC1ESTADOCIVILptpatrDescripcion);
                step11Request.setEstadoCivilsincronizarpNombreCorto(tGSINC1ESTADOCIVILptpatrNombreCorto);
                estadoCivilSrv.sincronizarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service TGSINCESTADOSCIVILES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCESTADOSCIVILES(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSCIVILESRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCESTADOSCIVILESTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCESTADOSCIVILES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCESTADOSCIVILESTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSCIVILESRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCESTADOSCIVILESImplementation(request);
    }

    /*
     * Implements global service TGSINCESTADOSCIVILES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCESTADOSCIVILESImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSCIVILESRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1ESTADOCIVILRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1ESTADOCIVILRequest();
        step1Request.setTGSINC1ESTADOCIVILptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1ESTADOCIVILptpatrDescripcion("0");
        step1Request.setTGSINC1ESTADOCIVILptpatrNombreCorto("0");
        globalServicesSrv.tGSINC1ESTADOCIVILImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(7));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1DEPARTAMENTO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1DEPARTAMENTOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1DEPARTAMENTORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1DEPARTAMENTOptpatrCodigo = request.getTGSINC1DEPARTAMENTOptpatrCodigo();
        String tGSINC1DEPARTAMENTOptpatrDescripcion = request.getTGSINC1DEPARTAMENTOptpatrDescripcion();
        String tGSINC1DEPARTAMENTOptpatrCodigoLegal = request.getTGSINC1DEPARTAMENTOptpatrCodigoLegal();

        request.checkArguments();

        /* Action 1: IF (EXIST (Departamento) WHERE (Departamento.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator040(tGSINC1DEPARTAMENTOptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.departamento.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.CrearRequest();
            step10Request.setDepartamentocrearpatrCodigo(tGSINC1DEPARTAMENTOptpatrCodigo);
            step10Request.setDepartamentocrearpatrDescripcion(tGSINC1DEPARTAMENTOptpatrDescripcion);
            step10Request.setDepartamentocrearpatrCodigoLegal(tGSINC1DEPARTAMENTOptpatrCodigoLegal);
            departamentoSrv.crearImplementation(step10Request);
        } else {
            for(Departamento step111Entity : globalServicesRepository.assocOperator039(tGSINC1DEPARTAMENTOptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.departamento.siu.SincronizarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.departamento.siu.SincronizarRequest();
                step11Request.setDepartamentosincronizarpthisDepartamentoInstance(step111Entity);
                step11Request.setDepartamentosincronizarpDescripcion(tGSINC1DEPARTAMENTOptpatrDescripcion);
                departamentoSrv.sincronizarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service TGSINCDEPARTAMENTOS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCDEPARTAMENTOS(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCDEPARTAMENTOSRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCDEPARTAMENTOSTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCDEPARTAMENTOS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCDEPARTAMENTOSTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCDEPARTAMENTOSRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCDEPARTAMENTOSImplementation(request);
    }

    /*
     * Implements global service TGSINCDEPARTAMENTOS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCDEPARTAMENTOSImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCDEPARTAMENTOSRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1DEPARTAMENTORequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1DEPARTAMENTORequest();
        step1Request.setTGSINC1DEPARTAMENTOptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1DEPARTAMENTOptpatrDescripcion("0");
        step1Request.setTGSINC1DEPARTAMENTOptpatrCodigoLegal("0");
        globalServicesSrv.tGSINC1DEPARTAMENTOImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(5));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1COMPANIA
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1COMPANIAImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1COMPANIARequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1COMPANIAptpatrCodigo = request.getTGSINC1COMPANIAptpatrCodigo();
        String tGSINC1COMPANIAptpatrNombre = request.getTGSINC1COMPANIAptpatrNombre();

        request.checkArguments();

        /* Action 1: IF (EXIST (Companyia) WHERE (Companyia.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator048(tGSINC1COMPANIAptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.companyia.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.companyia.siu.CrearRequest();
            step10Request.setCompanyiacrearpatrCodigo(tGSINC1COMPANIAptpatrCodigo);
            step10Request.setCompanyiacrearpatrNombre(tGSINC1COMPANIAptpatrNombre);
            step10Request.setCompanyiacrearpatrEsDeMP(Boolean.FALSE);
            companyiaSrv.crearImplementation(step10Request);
        } else {
            for(Companyia step111Entity : globalServicesRepository.assocOperator047(tGSINC1COMPANIAptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.companyia.siu.SincronizarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.companyia.siu.SincronizarRequest();
                step11Request.setCompanyiasincronizarpthisCompanyiaInstance(step111Entity);
                step11Request.setCompanyiasincronizarpNombre(tGSINC1COMPANIAptpatrNombre);
                companyiaSrv.sincronizarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service OGSINCOMPANIAS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse oGSINCOMPANIAS(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCOMPANIASRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.oGSINCOMPANIASTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service OGSINCOMPANIAS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse oGSINCOMPANIASTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCOMPANIASRequest request) throws ModelException, SystemException {
        return globalServicesSrv.oGSINCOMPANIASImplementation(request);
    }

    /*
     * Implements global service OGSINCOMPANIAS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse oGSINCOMPANIASImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCOMPANIASRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1COMPANIARequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1COMPANIARequest();
        step1Request.setTGSINC1COMPANIAptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1COMPANIAptpatrNombre("0");
        globalServicesSrv.tGSINC1COMPANIAImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(11));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Entry point for global service TGSINC1MUNICIPIO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINC1MUNICIPIO(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1MUNICIPIORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINC1MUNICIPIOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINC1MUNICIPIO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINC1MUNICIPIOTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1MUNICIPIORequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINC1MUNICIPIOImplementation(request);
    }

    /*
     * Implements global service TGSINC1MUNICIPIO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1MUNICIPIOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1MUNICIPIORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1MUNICIPIOptpatrCodigo = request.getTGSINC1MUNICIPIOptpatrCodigo();
        String tGSINC1MUNICIPIOptpatrDescripcion = request.getTGSINC1MUNICIPIOptpatrDescripcion();
        String tGSINC1MUNICIPIOptpatrCodigoLegal = request.getTGSINC1MUNICIPIOptpatrCodigoLegal();
        Boolean tGSINC1MUNICIPIOptpatrEsValidoEPS = request.getTGSINC1MUNICIPIOptpatrEsValidoEPS();

        request.checkArguments();

        /* Action 1: IF (EXIST (Municipio) WHERE (Municipio.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator051(tGSINC1MUNICIPIOptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.municipio.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.CrearRequest();
            step10Request.setMunicipiocrearpagrDepartamentoInstance(userFunctionsSrv.fugetDepartamentoCLFun(STDFunctions.substringFun(tGSINC1MUNICIPIOptpatrCodigoLegal, Long.valueOf(1), Long.valueOf(2))));
            step10Request.setMunicipiocrearpatrCodigo(tGSINC1MUNICIPIOptpatrCodigo);
            step10Request.setMunicipiocrearpatrDescripcion(tGSINC1MUNICIPIOptpatrDescripcion);
            step10Request.setMunicipiocrearpatrCodigoLegal(tGSINC1MUNICIPIOptpatrCodigoLegal);
            step10Request.setMunicipiocrearpatrEsValidoEPS(tGSINC1MUNICIPIOptpatrEsValidoEPS);
            step10Request.setMunicipiocrearpatrAplicaIVA(Boolean.TRUE);
            municipioSrv.crearImplementation(step10Request);
        } else {
            for(Municipio step111Entity : globalServicesRepository.assocOperator050(tGSINC1MUNICIPIOptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.municipio.siu.TSINCRONIZARRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.municipio.siu.TSINCRONIZARRequest();
                step11Request.setMunicipioTSINCRONIZARpthisMunicipioInstance(step111Entity);
                step11Request.setMunicipioTSINCRONIZARptpDescripcion(tGSINC1MUNICIPIOptpatrDescripcion);
                step11Request.setMunicipioTSINCRONIZARptpCodigoLegal(tGSINC1MUNICIPIOptpatrCodigoLegal);
                step11Request.setMunicipioTSINCRONIZARptpEsValidoEPS(tGSINC1MUNICIPIOptpatrEsValidoEPS);
                municipioSrv.tSINCRONIZARImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service OGSINCMUNICIPIOS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse oGSINCMUNICIPIOS(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCMUNICIPIOSRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.oGSINCMUNICIPIOSTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service OGSINCMUNICIPIOS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse oGSINCMUNICIPIOSTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCMUNICIPIOSRequest request) throws ModelException, SystemException {
        return globalServicesSrv.oGSINCMUNICIPIOSImplementation(request);
    }

    /*
     * Implements global service OGSINCMUNICIPIOS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse oGSINCMUNICIPIOSImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCMUNICIPIOSRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        try {
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCDEPARTAMENTOSRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCDEPARTAMENTOSRequest();
            globalServicesSrv.tGSINCDEPARTAMENTOSTxn(step1Request);
        } catch (Exception e) { }

        try {
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1MUNICIPIORequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1MUNICIPIORequest();
            step2Request.setTGSINC1MUNICIPIOptpatrCodigo(Long.valueOf(0));
            step2Request.setTGSINC1MUNICIPIOptpatrDescripcion("");
            step2Request.setTGSINC1MUNICIPIOptpatrCodigoLegal("");
            step2Request.setTGSINC1MUNICIPIOptpatrEsValidoEPS(Boolean.FALSE);
            globalServicesSrv.tGSINC1MUNICIPIOTxn(step2Request);
        } catch (Exception e) { }

        try {
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step3Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
            step3Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(6));
            step3Request.setTGFIJARDATOSSINCptResultado("Exito");
            globalServicesSrv.tGFIJARDATOSSINCTxn(step3Request);
        } catch (Exception e) { }
        return response;
    }

    /*
     * Implements global service TGSINC1TIPODIRECCION
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1TIPODIRECCIONImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1TIPODIRECCIONRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1TIPODIRECCIONptpatrCodigo = request.getTGSINC1TIPODIRECCIONptpatrCodigo();
        String tGSINC1TIPODIRECCIONptpatrDescripcion = request.getTGSINC1TIPODIRECCIONptpatrDescripcion();

        request.checkArguments();

        /* Action 1: IF (EXIST (TipoDireccion) WHERE (TipoDireccion.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator054(tGSINC1TIPODIRECCIONptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu.CrearRequest();
            step10Request.setTipoDireccioncrearpatrCodigo(tGSINC1TIPODIRECCIONptpatrCodigo);
            step10Request.setTipoDireccioncrearpatrDescripcion(tGSINC1TIPODIRECCIONptpatrDescripcion);
            step10Request.setTipoDireccioncrearpatrEsResidencia(Boolean.FALSE);
            step10Request.setTipoDireccioncrearpatrEsCorrespondencia(Boolean.FALSE);
            tipoDireccionSrv.crearImplementation(step10Request);
        } else {
            for(TipoDireccion step111Entity : globalServicesRepository.assocOperator053(tGSINC1TIPODIRECCIONptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu.SincronizarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.tipodireccion.siu.SincronizarRequest();
                step11Request.setTipoDireccionsincronizarpthisTipoDireccionInstance(step111Entity);
                step11Request.setTipoDireccionsincronizarpDescripcion(tGSINC1TIPODIRECCIONptpatrDescripcion);
                tipoDireccionSrv.sincronizarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service TGSINCTIPOSDIRECCION
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCTIPOSDIRECCION(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSDIRECCIONRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCTIPOSDIRECCIONTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCTIPOSDIRECCION
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCTIPOSDIRECCIONTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSDIRECCIONRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCTIPOSDIRECCIONImplementation(request);
    }

    /*
     * Implements global service TGSINCTIPOSDIRECCION
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCTIPOSDIRECCIONImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSDIRECCIONRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1TIPODIRECCIONRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1TIPODIRECCIONRequest();
        step1Request.setTGSINC1TIPODIRECCIONptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1TIPODIRECCIONptpatrDescripcion("0");
        globalServicesSrv.tGSINC1TIPODIRECCIONImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(18));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1ESTADOPERSONA
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1ESTADOPERSONAImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1ESTADOPERSONARequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1ESTADOPERSONAptpatrCodigo = request.getTGSINC1ESTADOPERSONAptpatrCodigo();
        String tGSINC1ESTADOPERSONAptpatrDescripcion = request.getTGSINC1ESTADOPERSONAptpatrDescripcion();

        request.checkArguments();

        /* Action 1: IF (EXIST (EstadoPersona) WHERE (EstadoPersona.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator057(tGSINC1ESTADOPERSONAptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu.CrearRequest();
            step10Request.setEstadoPersonacrearpatrCodigo(tGSINC1ESTADOPERSONAptpatrCodigo);
            step10Request.setEstadoPersonacrearpatrDescripcion(tGSINC1ESTADOPERSONAptpatrDescripcion);
            step10Request.setEstadoPersonacrearpatrUnBeneficiarioDistino(Boolean.FALSE);
            step10Request.setEstadoPersonacrearpatrProhibidoBeneficiarios(Boolean.FALSE);
            step10Request.setEstadoPersonacrearpatrProhibidoContratante(Boolean.FALSE);
            step10Request.setEstadoPersonacrearpatrProhibidoTitular(Boolean.FALSE);
            estadoPersonaSrv.crearImplementation(step10Request);
        } else {
            for(EstadoPersona step111Entity : globalServicesRepository.assocOperator056(tGSINC1ESTADOPERSONAptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu.SincronizarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.estadopersona.siu.SincronizarRequest();
                step11Request.setEstadoPersonasincronizarpthisEstadoPersonaInstance(step111Entity);
                step11Request.setEstadoPersonasincronizarpDescripcion(tGSINC1ESTADOPERSONAptpatrDescripcion);
                estadoPersonaSrv.sincronizarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service TGSINCESTADOSPERSONA
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCESTADOSPERSONA(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSPERSONARequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCESTADOSPERSONATxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCESTADOSPERSONA
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCESTADOSPERSONATxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSPERSONARequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCESTADOSPERSONAImplementation(request);
    }

    /*
     * Implements global service TGSINCESTADOSPERSONA
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCESTADOSPERSONAImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSPERSONARequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1ESTADOPERSONARequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1ESTADOPERSONARequest();
        step1Request.setTGSINC1ESTADOPERSONAptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1ESTADOPERSONAptpatrDescripcion("0");
        globalServicesSrv.tGSINC1ESTADOPERSONAImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(22));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1FORMAPAGO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1FORMAPAGOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1FORMAPAGORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1FORMAPAGOptpatrCodigo = request.getTGSINC1FORMAPAGOptpatrCodigo();
        String tGSINC1FORMAPAGOptpatrDescripcion = request.getTGSINC1FORMAPAGOptpatrDescripcion();

        request.checkArguments();

        /* Action 1: IF (EXIST (FormaPago) WHERE (FormaPago.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator060(tGSINC1FORMAPAGOptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.formapago.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.formapago.siu.CrearRequest();
            step10Request.setFormaPagocrearpatrCodigo(tGSINC1FORMAPAGOptpatrCodigo);
            step10Request.setFormaPagocrearpatrDescripcion(tGSINC1FORMAPAGOptpatrDescripcion);
            step10Request.setFormaPagocrearpatrAbrirURL(Boolean.FALSE);
            step10Request.setFormaPagocrearpatrEsVisible(Boolean.TRUE);
            formaPagoSrv.crearImplementation(step10Request);
        } else {
            for(FormaPago step111Entity : globalServicesRepository.assocOperator059(tGSINC1FORMAPAGOptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.formapago.siu.SincronizarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.formapago.siu.SincronizarRequest();
                step11Request.setFormaPagosincronizarpthisFormaPagoInstance(step111Entity);
                step11Request.setFormaPagosincronizarpDescripcion(tGSINC1FORMAPAGOptpatrDescripcion);
                formaPagoSrv.sincronizarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service TGSINCFORMASPAGO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCFORMASPAGO(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCFORMASPAGORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCFORMASPAGOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCFORMASPAGO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCFORMASPAGOTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCFORMASPAGORequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCFORMASPAGOImplementation(request);
    }

    /*
     * Implements global service TGSINCFORMASPAGO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCFORMASPAGOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCFORMASPAGORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1FORMAPAGORequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1FORMAPAGORequest();
        step1Request.setTGSINC1FORMAPAGOptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1FORMAPAGOptpatrDescripcion("0");
        globalServicesSrv.tGSINC1FORMAPAGOImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(15));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1TIPOIDENTIFICACION
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1TIPOIDENTIFICACIONImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1TIPOIDENTIFICACIONRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1TIPOIDENTIFICACIONptpatrCodigo = request.getTGSINC1TIPOIDENTIFICACIONptpatrCodigo();
        String tGSINC1TIPOIDENTIFICACIONptpatrDescripcion = request.getTGSINC1TIPOIDENTIFICACIONptpatrDescripcion();
        String tGSINC1TIPOIDENTIFICACIONptpatrNombreCorto = request.getTGSINC1TIPOIDENTIFICACIONptpatrNombreCorto();

        request.checkArguments();

        /* Action 1: IF (EXIST (TipoIdentificacion) WHERE (TipoIdentificacion.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator063(tGSINC1TIPOIDENTIFICACIONptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.CrearRequest();
            step10Request.setTipoIdentificacioncrearpatrCodigo(tGSINC1TIPOIDENTIFICACIONptpatrCodigo);
            step10Request.setTipoIdentificacioncrearpatrDescripcion(tGSINC1TIPOIDENTIFICACIONptpatrDescripcion);
            step10Request.setTipoIdentificacioncrearpatrNombreCorto(tGSINC1TIPOIDENTIFICACIONptpatrNombreCorto);
            step10Request.setTipoIdentificacioncrearpatrValidoContratante(Boolean.TRUE);
            step10Request.setTipoIdentificacioncrearpatrValidoTitular(Boolean.TRUE);
            step10Request.setTipoIdentificacioncrearpatrDiplomatico(Boolean.FALSE);
            step10Request.setTipoIdentificacioncrearpatrNIT(Boolean.FALSE);
            step10Request.setTipoIdentificacioncrearpatrHijoDe(Boolean.FALSE);
            step10Request.setTipoIdentificacioncrearpatrRequiereEPS(Boolean.TRUE);
            step10Request.setTipoIdentificacioncrearpatrMenorSinIdentificar(Boolean.FALSE);
            step10Request.setTipoIdentificacioncrearpatrEsPasaporte(Boolean.FALSE);
            step10Request.setTipoIdentificacioncrearpatrVisibleValidadorUsu(Boolean.TRUE);
            tipoIdentificacionSrv.crearImplementation(step10Request);
        } else {
            for(TipoIdentificacion step111Entity : globalServicesRepository.assocOperator062(tGSINC1TIPOIDENTIFICACIONptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.SincronizarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.tipoidentificacion.siu.SincronizarRequest();
                step11Request.setTipoIdentificacionsincronizarpthisTipoIdentificacionInstance(step111Entity);
                step11Request.setTipoIdentificacionsincronizarpDescripcion(tGSINC1TIPOIDENTIFICACIONptpatrDescripcion);
                step11Request.setTipoIdentificacionsincronizarpNombreCorto(tGSINC1TIPOIDENTIFICACIONptpatrNombreCorto);
                tipoIdentificacionSrv.sincronizarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service TGSINCTIPOSIDENTIFICACION
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCTIPOSIDENTIFICACION(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSIDENTIFICACIONRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCTIPOSIDENTIFICACIONTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCTIPOSIDENTIFICACION
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCTIPOSIDENTIFICACIONTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSIDENTIFICACIONRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCTIPOSIDENTIFICACIONImplementation(request);
    }

    /*
     * Implements global service TGSINCTIPOSIDENTIFICACION
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCTIPOSIDENTIFICACIONImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSIDENTIFICACIONRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1TIPOIDENTIFICACIONRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1TIPOIDENTIFICACIONRequest();
        step1Request.setTGSINC1TIPOIDENTIFICACIONptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1TIPOIDENTIFICACIONptpatrDescripcion("0");
        step1Request.setTGSINC1TIPOIDENTIFICACIONptpatrNombreCorto("0");
        globalServicesSrv.tGSINC1TIPOIDENTIFICACIONImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(19));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1EPS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1EPSImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1EPSRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1EPSptpatrCodigo = request.getTGSINC1EPSptpatrCodigo();
        String tGSINC1EPSptpatrDescripcion = request.getTGSINC1EPSptpatrDescripcion();

        request.checkArguments();

        /* Action 1: IF (EXIST (Eps) WHERE (Eps.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator066(tGSINC1EPSptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.eps.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.eps.siu.CrearRequest();
            step10Request.setEpscrearpatrCodigo(tGSINC1EPSptpatrCodigo);
            step10Request.setEpscrearpatrDescripcion(tGSINC1EPSptpatrDescripcion);
            step10Request.setEpscrearpatrEpsSanitas(Boolean.FALSE);
            epsSrv.crearImplementation(step10Request);
        } else {
            for(Eps step111Entity : globalServicesRepository.assocOperator065(tGSINC1EPSptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.eps.siu.SincronizarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.eps.siu.SincronizarRequest();
                step11Request.setEpssincronizarpthisEPSInstance(step111Entity);
                step11Request.setEpssincronizarpDescripcion(tGSINC1EPSptpatrDescripcion);
                epsSrv.sincronizarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service OGSINCEPSS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse oGSINCEPSS(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCEPSSRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.oGSINCEPSSTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service OGSINCEPSS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse oGSINCEPSSTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCEPSSRequest request) throws ModelException, SystemException {
        return globalServicesSrv.oGSINCEPSSImplementation(request);
    }

    /*
     * Implements global service OGSINCEPSS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse oGSINCEPSSImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCEPSSRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1EPSRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1EPSRequest();
        step1Request.setTGSINC1EPSptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1EPSptpatrDescripcion("0");
        globalServicesSrv.tGSINC1EPSImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(9));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1PAIS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1PAISImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PAISRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        String tGSINC1PAISptpatrCodigo = request.getTGSINC1PAISptpatrCodigo();
        String tGSINC1PAISptpatrDescripcion = request.getTGSINC1PAISptpatrDescripcion();

        request.checkArguments();

        /* Action 1: IF (EXIST (Pais) WHERE (Pais.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator069(tGSINC1PAISptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.pais.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.pais.siu.CrearRequest();
            step10Request.setPaiscrearpatrCodigo(tGSINC1PAISptpatrCodigo);
            step10Request.setPaiscrearpatrDescripcion(tGSINC1PAISptpatrDescripcion);
            paisSrv.crearImplementation(step10Request);
        } else {
            for(Pais step111Entity : globalServicesRepository.assocOperator068(tGSINC1PAISptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.pais.siu.SincronizarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.pais.siu.SincronizarRequest();
                step11Request.setPaissincronizarpthisPaisInstance(step111Entity);
                step11Request.setPaissincronizarpDescripcion(tGSINC1PAISptpatrDescripcion);
                paisSrv.sincronizarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service TGSINCPAISES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCPAISES(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPAISESRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCPAISESTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCPAISES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCPAISESTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPAISESRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCPAISESImplementation(request);
    }

    /*
     * Implements global service TGSINCPAISES
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCPAISESImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPAISESRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PAISRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1PAISRequest();
        step1Request.setTGSINC1PAISptpatrCodigo("0");
        step1Request.setTGSINC1PAISptpatrDescripcion("0");
        globalServicesSrv.tGSINC1PAISImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(23));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Implements global service TGSINC1ENTIDADFIN
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1ENTIDADFINImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1ENTIDADFINRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1ENTIDADFINptpatrCodigo = request.getTGSINC1ENTIDADFINptpatrCodigo();
        String tGSINC1ENTIDADFINptpatrDescripcion = request.getTGSINC1ENTIDADFINptpatrDescripcion();

        request.checkArguments();

        /* Action 1: IF (EXIST (EntidadFinancieraC) WHERE (EntidadFinancieraC.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator080(tGSINC1ENTIDADFINptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.siu.CrearRequest();
            step10Request.setEntidadFinancieraCcrearpatrCodigo(tGSINC1ENTIDADFINptpatrCodigo);
            step10Request.setEntidadFinancieraCcrearpatrDescripcion(tGSINC1ENTIDADFINptpatrDescripcion);
            entidadFinancieraCSrv.crearImplementation(step10Request);
        } else {
            for(EntidadFinancieraC step111Entity : globalServicesRepository.assocOperator079(tGSINC1ENTIDADFINptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.siu.ModificarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.entidadfinancierac.siu.ModificarRequest();
                step11Request.setEntidadFinancieraCmodificarpthisEntidadFinancieraCInstance(step111Entity);
                step11Request.setEntidadFinancieraCmodificarpDescripcion(tGSINC1ENTIDADFINptpatrDescripcion);
                entidadFinancieraCSrv.modificarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Implements global service TGSINC1TIPOENTIDAD
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1TIPOENTIDADImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1TIPOENTIDADRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1TIPOENTIDADptpatrCodigo = request.getTGSINC1TIPOENTIDADptpatrCodigo();
        String tGSINC1TIPOENTIDADptpatrDescripcion = request.getTGSINC1TIPOENTIDADptpatrDescripcion();

        request.checkArguments();

        /* Action 1: IF (EXIST (TipoEntidadC) WHERE (TipoEntidadC.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator083(tGSINC1TIPOENTIDADptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.CrearRequest();
            step10Request.setTipoEntidadCcrearpatrCodigo(tGSINC1TIPOENTIDADptpatrCodigo);
            step10Request.setTipoEntidadCcrearpatrDescripcion(tGSINC1TIPOENTIDADptpatrDescripcion);
            tipoEntidadCSrv.crearImplementation(step10Request);
        } else {
            for(TipoEntidadC step111Entity : globalServicesRepository.assocOperator082(tGSINC1TIPOENTIDADptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.ModificarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.tipoentidadc.siu.ModificarRequest();
                step11Request.setTipoEntidadCmodificarpthisTipoEntidadCInstance(step111Entity);
                step11Request.setTipoEntidadCmodificarpDescripcion(tGSINC1TIPOENTIDADptpatrDescripcion);
                tipoEntidadCSrv.modificarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Implements global service TGSINC1TIPOINSTITUCION
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1TIPOINSTITUCIONImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1TIPOINSTITUCIONRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1TIPOINSTITUCIONptpatrCodigo = request.getTGSINC1TIPOINSTITUCIONptpatrCodigo();
        String tGSINC1TIPOINSTITUCIONptpatrNombre = request.getTGSINC1TIPOINSTITUCIONptpatrNombre();

        request.checkArguments();

        /* Action 1: IF (EXIST (TipoInstitucionC) WHERE (TipoInstitucionC.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator086(tGSINC1TIPOINSTITUCIONptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.CrearRequest();
            step10Request.setTipoInstitucionCcrearpatrCodigo(tGSINC1TIPOINSTITUCIONptpatrCodigo);
            step10Request.setTipoInstitucionCcrearpatrNombre(tGSINC1TIPOINSTITUCIONptpatrNombre);
            tipoInstitucionCSrv.crearImplementation(step10Request);
        } else {
            for(TipoInstitucionC step111Entity : globalServicesRepository.assocOperator085(tGSINC1TIPOINSTITUCIONptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.ModificarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.tipoinstitucionc.siu.ModificarRequest();
                step11Request.setTipoInstitucionCmodificarpthisTipoInstitucionCInstance(step111Entity);
                step11Request.setTipoInstitucionCmodificarpNombre(tGSINC1TIPOINSTITUCIONptpatrNombre);
                tipoInstitucionCSrv.modificarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Implements global service TGSINC1TIPOSUCURSAL
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINC1TIPOSUCURSALImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1TIPOSUCURSALRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGSINC1TIPOSUCURSALptpatrCodigo = request.getTGSINC1TIPOSUCURSALptpatrCodigo();
        String tGSINC1TIPOSUCURSALptpatrDescripcion = request.getTGSINC1TIPOSUCURSALptpatrDescripcion();

        request.checkArguments();

        /* Action 1: IF (EXIST (TipoSucursalC) WHERE (TipoSucursalC.Codigo = ptpatrCodigo) = false) */
        boolean ifCondition1 = globalServicesRepository.assocOperator089(tGSINC1TIPOSUCURSALptpatrCodigo).equals(false);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.CrearRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.CrearRequest();
            step10Request.setTipoSucursalCcrearpatrCodigo(tGSINC1TIPOSUCURSALptpatrCodigo);
            step10Request.setTipoSucursalCcrearpatrDescripcion(tGSINC1TIPOSUCURSALptpatrDescripcion);
            tipoSucursalCSrv.crearImplementation(step10Request);
        } else {
            for(TipoSucursalC step111Entity : globalServicesRepository.assocOperator088(tGSINC1TIPOSUCURSALptpatrCodigo)) {
                com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.ModificarRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.tiposucursalc.siu.ModificarRequest();
                step11Request.setTipoSucursalCmodificarpthisTipoSucursalCInstance(step111Entity);
                step11Request.setTipoSucursalCmodificarpDescripcion(tGSINC1TIPOSUCURSALptpatrDescripcion);
                tipoSucursalCSrv.modificarImplementation(step11Request);
            }
        }
        return response;
    }

    /*
     * Entry point for global service TGSINCENTIDADESFIN
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCENTIDADESFIN(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCENTIDADESFINRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCENTIDADESFINTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCENTIDADESFIN
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCENTIDADESFINTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCENTIDADESFINRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCENTIDADESFINImplementation(request);
    }

    /*
     * Implements global service TGSINCENTIDADESFIN
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCENTIDADESFINImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCENTIDADESFINRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1ENTIDADFINRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1ENTIDADFINRequest();
        step1Request.setTGSINC1ENTIDADFINptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1ENTIDADFINptpatrDescripcion("0");
        globalServicesSrv.tGSINC1ENTIDADFINImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(24));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Entry point for global service TGSINCTIPOSDEENTIDAD
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCTIPOSDEENTIDAD(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSDEENTIDADRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCTIPOSDEENTIDADTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCTIPOSDEENTIDAD
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCTIPOSDEENTIDADTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSDEENTIDADRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCTIPOSDEENTIDADImplementation(request);
    }

    /*
     * Implements global service TGSINCTIPOSDEENTIDAD
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCTIPOSDEENTIDADImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSDEENTIDADRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1TIPOENTIDADRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1TIPOENTIDADRequest();
        step1Request.setTGSINC1TIPOENTIDADptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1TIPOENTIDADptpatrDescripcion("0");
        globalServicesSrv.tGSINC1TIPOENTIDADImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(25));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Entry point for global service TGSINCTIPOSINSTITUCION
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCTIPOSINSTITUCION(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSINSTITUCIONRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCTIPOSINSTITUCIONTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCTIPOSINSTITUCION
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCTIPOSINSTITUCIONTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSINSTITUCIONRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCTIPOSINSTITUCIONImplementation(request);
    }

    /*
     * Implements global service TGSINCTIPOSINSTITUCION
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCTIPOSINSTITUCIONImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSINSTITUCIONRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1TIPOINSTITUCIONRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1TIPOINSTITUCIONRequest();
        step1Request.setTGSINC1TIPOINSTITUCIONptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1TIPOINSTITUCIONptpatrNombre("0");
        globalServicesSrv.tGSINC1TIPOINSTITUCIONImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(26));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Entry point for global service TGSINCTIPOSSUCURSAL
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGSINCTIPOSSUCURSAL(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSSUCURSALRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGSINCTIPOSSUCURSALTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGSINCTIPOSSUCURSAL
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGSINCTIPOSSUCURSALTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSSUCURSALRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGSINCTIPOSSUCURSALImplementation(request);
    }

    /*
     * Implements global service TGSINCTIPOSSUCURSAL
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGSINCTIPOSSUCURSALImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSSUCURSALRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1TIPOSUCURSALRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1TIPOSUCURSALRequest();
        step1Request.setTGSINC1TIPOSUCURSALptpatrCodigo(Long.valueOf(0));
        step1Request.setTGSINC1TIPOSUCURSALptpatrDescripcion("0");
        globalServicesSrv.tGSINC1TIPOSUCURSALImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest();
        step2Request.setTGFIJARDATOSSINCptNumParametrica(Long.valueOf(27));
        step2Request.setTGFIJARDATOSSINCptResultado("Exito");
        globalServicesSrv.tGFIJARDATOSSINCImplementation(step2Request);
        return response;
    }

    /*
     * Entry point for global service TGCREARTARIFA
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGCREARTARIFA(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARTARIFARequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGCREARTARIFATxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGCREARTARIFA
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGCREARTARIFATxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARTARIFARequest request) throws ModelException {
        request.setTGCREARTARIFApProductoInstance(productosSrv.getByOIDWithHV(new ProductosOid().fromJsonString(request.getTGCREARTARIFApProducto())));
        request.setTGCREARTARIFApPlanInstance(planesSrv.getByOIDWithHV(new PlanesOid().fromJsonString(request.getTGCREARTARIFApPlan())));
        return globalServicesSrv.tGCREARTARIFAImplementation(request);
    }

    /*
     * Implements global service TGCREARTARIFA
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGCREARTARIFAImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARTARIFARequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Productos tGCREARTARIFApProducto = request.getTGCREARTARIFApProductoInstance();
        Planes tGCREARTARIFApPlan = request.getTGCREARTARIFApPlanInstance();
        Long tGCREARTARIFApTipoContrato = request.getTGCREARTARIFApTipoContrato();
        String tGCREARTARIFApCodigo = request.getTGCREARTARIFApCodigo();

        request.checkArguments();

        com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.TCREARRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.TCREARRequest();
        step1Request.setTarifaTCREARpProductoInstance(tGCREARTARIFApProducto);
        step1Request.setTarifaTCREARpPlanInstance(tGCREARTARIFApPlan);
        step1Request.setTarifaTCREARpTipoContrato(tGCREARTARIFApTipoContrato);
        step1Request.setTarifaTCREARpCodigo(tGCREARTARIFApCodigo);
        tarifaSrv.tCREARImplementation(step1Request);
        return response;
    }

    /*
     * Entry point for global service TGELIMINARTARIFA
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGELIMINARTARIFA(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGELIMINARTARIFARequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGELIMINARTARIFATxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGELIMINARTARIFA
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGELIMINARTARIFATxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGELIMINARTARIFARequest request) throws ModelException, SystemException {
        request.setTGELIMINARTARIFApTarifaInstance(tarifaSrv.getByOIDWithHV(request.getTGELIMINARTARIFApTarifa()));
        return globalServicesSrv.tGELIMINARTARIFAImplementation(request);
    }

    /*
     * Implements global service TGELIMINARTARIFA
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGELIMINARTARIFAImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGELIMINARTARIFARequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Tarifa tGELIMINARTARIFApTarifa = request.getTGELIMINARTARIFApTarifaInstance();

        request.checkArguments();

        com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.TELIMINARRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.tarifa.siu.TELIMINARRequest();
        step1Request.setTarifaTELIMINARpthisTarifaInstance(tGELIMINARTARIFApTarifa);
        tarifaSrv.tELIMINARImplementation(step1Request);
        return response;
    }

    /*
     * Entry point for global service tdelcontsindocsol
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tdelcontsindocsol(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TdelcontsindocsolRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tdelcontsindocsolTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service tdelcontsindocsol
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tdelcontsindocsolTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TdelcontsindocsolRequest request) throws ModelException {
        request.setTdelcontsindocsolptpthisContSinDocSolInstance(contSinDocSolSrv.getByOIDWithHV(request.getTdelcontsindocsolptpthisContSinDocSol()));
        return globalServicesSrv.tdelcontsindocsolImplementation(request);
    }

    /*
     * Implements global service tdelcontsindocsol
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tdelcontsindocsolImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TdelcontsindocsolRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        ContSinDocSol tdelcontsindocsolptpthisContSinDocSol = request.getTdelcontsindocsolptpthisContSinDocSolInstance();

        request.checkArguments();

        com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.logcontsindocsol.siu.CrearRequest();
        step1Request.setLogContSinDocSolcrearpatrNumContrato(tdelcontsindocsolptpthisContSinDocSol.getContSinDocSolNumContrato());
        step1Request.setLogContSinDocSolcrearpatrUsuario(((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionarioUsuarioLDAP());
        logContSinDocSolSrv.crearImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.contsindocsol.siu.EliminarRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.contsindocsol.siu.EliminarRequest();
        step2Request.setContSinDocSoleliminarpthisContSinDocSolInstance(tdelcontsindocsolptpthisContSinDocSol);
        contSinDocSolSrv.eliminarImplementation(step2Request);
        return response;
    }

    /*
     * Entry point for global service TGCREARCOMBO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGCREARCOMBO(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARCOMBORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGCREARCOMBOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGCREARCOMBO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGCREARCOMBOTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARCOMBORequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGCREARCOMBOImplementation(request);
    }

    /*
     * Implements global service TGCREARCOMBO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGCREARCOMBOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARCOMBORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        String tGCREARCOMBOptpatrContratoIntegral = request.getTGCREARCOMBOptpatrContratoIntegral();
        String tGCREARCOMBOptpatrContratoOdontologico = request.getTGCREARCOMBOptpatrContratoOdontologico();

        request.checkArguments();

        tGCREARCOMBOPreconditions(request);

        com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu.CrearRequest();
        step1Request.setContratoCombocrearpatrContratoIntegral(tGCREARCOMBOptpatrContratoIntegral);
        step1Request.setContratoCombocrearpatrContratoOdontologico(tGCREARCOMBOptpatrContratoOdontologico);
        contratoComboSrv.crearImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.TCREARRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.TCREARRequest();
        step2Request.setLogContratoComboTCREARptpatrContratoIntegral(tGCREARCOMBOptpatrContratoIntegral);
        step2Request.setLogContratoComboTCREARptpatrContratoOdontologico(tGCREARCOMBOptpatrContratoOdontologico);
        step2Request.setLogContratoComboTCREARptpatrAccion("Creación");
        logContratoComboSrv.tCREARImplementation(step2Request);
        return response;
    }

    /*
     * Checks service TGCREARCOMBO preconditions individually
     * @param request Service request viewmodel
     * @returns Service response viewmodel with the execution results
     */
    public ServiceResponse tGCREARCOMBOCheckPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARCOMBORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            if("IGl_1587633717248106Pre_1".equals(request.getPreconditionId()))
                checkIGl1587633717248106Pre1(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tGCREARCOMBOPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARCOMBORequest request) 
            throws ModelException {
        checkIGl1587633717248106Pre1(request);
    }

    private void checkIGl1587633717248106Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARCOMBORequest request) 
            throws ModelException {
        // COUNT(LogContratoCombo) WHERE (LogContratoCombo.ContratoIntegral = pt_p_atrContratoIntegral AND LogContratoCombo.ContratoOdontologico = pt_p_atrContratoOdontologico) = 0

        String tGCREARCOMBOptpatrContratoIntegral = request.getTGCREARCOMBOptpatrContratoIntegral();
        String tGCREARCOMBOptpatrContratoOdontologico = request.getTGCREARCOMBOptpatrContratoOdontologico();
        try {
            boolean preconditionHolds = (globalServicesRepository.assocOperator139(tGCREARCOMBOptpatrContratoIntegral, tGCREARCOMBOptpatrContratoOdontologico)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Ya existe una configuración para los números de contratos");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    /*
     * Entry point for global service TGEDITARCOMBO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGEDITARCOMBO(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGEDITARCOMBORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGEDITARCOMBOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGEDITARCOMBO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGEDITARCOMBOTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGEDITARCOMBORequest request) throws ModelException, SystemException {
        request.setTGEDITARCOMBOptpthisContratoComboInstance(contratoComboSrv.getByOIDWithHV(request.getTGEDITARCOMBOptpthisContratoCombo()));
        return globalServicesSrv.tGEDITARCOMBOImplementation(request);
    }

    /*
     * Implements global service TGEDITARCOMBO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGEDITARCOMBOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGEDITARCOMBORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        ContratoCombo tGEDITARCOMBOptpthisContratoCombo = request.getTGEDITARCOMBOptpthisContratoComboInstance();
        String tGEDITARCOMBOptpContratoIntegral = request.getTGEDITARCOMBOptpContratoIntegral();
        String tGEDITARCOMBOptpContratoOdontologico = request.getTGEDITARCOMBOptpContratoOdontologico();

        request.checkArguments();

        tGEDITARCOMBOPreconditions(request);

        /* Action 1: IF (ptpthisContratoCombo.ContratoIntegral <> ptpContratoIntegral OR ptpthisContratoCombo.ContratoOdontologico <> ptpContratoOdontologico) */
        boolean ifCondition1 = !(tGEDITARCOMBOptpthisContratoCombo.getContratoComboContratoIntegral()).equals(tGEDITARCOMBOptpContratoIntegral) || !(tGEDITARCOMBOptpthisContratoCombo.getContratoComboContratoOdontologico()).equals(tGEDITARCOMBOptpContratoOdontologico);
        if (ifCondition1) {
            com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu.ModificarRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu.ModificarRequest();
            step10Request.setContratoCombomodificarpthisContratoComboInstance(tGEDITARCOMBOptpthisContratoCombo);
            step10Request.setContratoCombomodificarpContratoIntegral(tGEDITARCOMBOptpContratoIntegral);
            step10Request.setContratoCombomodificarpContratoOdontologico(tGEDITARCOMBOptpContratoOdontologico);
            contratoComboSrv.modificarImplementation(step10Request);
            com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.TCREARRequest step11Request = new com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.TCREARRequest();
            step11Request.setLogContratoComboTCREARptpatrContratoIntegral(tGEDITARCOMBOptpContratoIntegral);
            step11Request.setLogContratoComboTCREARptpatrContratoOdontologico(tGEDITARCOMBOptpContratoOdontologico);
            step11Request.setLogContratoComboTCREARptpatrAccion("Modificación");
            logContratoComboSrv.tCREARImplementation(step11Request);
        }
        return response;
    }

    /*
     * Checks service TGEDITARCOMBO preconditions individually
     * @param request Service request viewmodel
     * @returns Service response viewmodel with the execution results
     */
    public ServiceResponse tGEDITARCOMBOCheckPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGEDITARCOMBORequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setTGEDITARCOMBOptpthisContratoComboInstance(contratoComboSrv.getByOIDWithHV(request.getTGEDITARCOMBOptpthisContratoCombo()));
        try {
            if("IGl_1587633979392848Pre_1".equals(request.getPreconditionId()))
                checkIGl1587633979392848Pre1(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tGEDITARCOMBOPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGEDITARCOMBORequest request) 
            throws ModelException {
        checkIGl1587633979392848Pre1(request);
    }

    private void checkIGl1587633979392848Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGEDITARCOMBORequest request) 
            throws ModelException {
        // COUNT( LogContratoCombo ) WHERE (LogContratoCombo.Id <> pt_p_thisContratoCombo.Id AND LogContratoCombo.ContratoIntegral = pt_p_ContratoIntegral AND LogContratoCombo.ContratoOdontologico = pt_p_ContratoOdontologico) = 0

        ContratoCombo tGEDITARCOMBOptpthisContratoCombo = request.getTGEDITARCOMBOptpthisContratoComboInstance();
        String tGEDITARCOMBOptpContratoIntegral = request.getTGEDITARCOMBOptpContratoIntegral();
        String tGEDITARCOMBOptpContratoOdontologico = request.getTGEDITARCOMBOptpContratoOdontologico();
        try {
            boolean preconditionHolds = (globalServicesRepository.assocOperator140(tGEDITARCOMBOptpthisContratoCombo, tGEDITARCOMBOptpContratoIntegral, tGEDITARCOMBOptpContratoOdontologico)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Ya existe una configuración para los números de contratos");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    /*
     * Entry point for global service TDELCOMBO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tDELCOMBO(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TDELCOMBORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tDELCOMBOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TDELCOMBO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tDELCOMBOTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TDELCOMBORequest request) throws ModelException, SystemException {
        request.setTDELCOMBOptpthisContratoComboInstance(contratoComboSrv.getByOIDWithHV(request.getTDELCOMBOptpthisContratoCombo()));
        return globalServicesSrv.tDELCOMBOImplementation(request);
    }

    /*
     * Implements global service TDELCOMBO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tDELCOMBOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TDELCOMBORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        ContratoCombo tDELCOMBOptpthisContratoCombo = request.getTDELCOMBOptpthisContratoComboInstance();

        request.checkArguments();

        com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.TCREARRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.logcontratocombo.siu.TCREARRequest();
        step1Request.setLogContratoComboTCREARptpatrContratoIntegral(tDELCOMBOptpthisContratoCombo.getContratoComboContratoIntegral());
        step1Request.setLogContratoComboTCREARptpatrContratoOdontologico(tDELCOMBOptpthisContratoCombo.getContratoComboContratoOdontologico());
        step1Request.setLogContratoComboTCREARptpatrAccion("Eliminar");
        logContratoComboSrv.tCREARImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu.EliminarRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.contratocombo.siu.EliminarRequest();
        step2Request.setContratoComboeliminarpthisContratoComboInstance(tDELCOMBOptpthisContratoCombo);
        contratoComboSrv.eliminarImplementation(step2Request);
        return response;
    }

    /*
     * Entry point for global service TGADDPLANTREP
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGADDPLANTREP(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGADDPLANTREPRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGADDPLANTREPTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGADDPLANTREP
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGADDPLANTREPTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGADDPLANTREPRequest request) throws ModelException {
        return globalServicesSrv.tGADDPLANTREPImplementation(request);
    }

    /*
     * Implements global service TGADDPLANTREP
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGADDPLANTREPImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGADDPLANTREPRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long tGADDPLANTREPptpatrTipo = request.getTGADDPLANTREPptpatrTipo();
        String tGADDPLANTREPptpatrTitulo = request.getTGADDPLANTREPptpatrTitulo();
        Date tGADDPLANTREPptpatrFechaVigencia = request.getTGADDPLANTREPptpatrFechaVigencia();
        String tGADDPLANTREPptpatrRutaArchivos = request.getTGADDPLANTREPptpatrRutaArchivos();
        String tGADDPLANTREPptpatrNombreArchivoPDF = request.getTGADDPLANTREPptpatrNombreArchivoPDF();

        request.checkArguments();

        tGADDPLANTREPPreconditions(request);

        com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.CrearRequest();
        step1Request.setPlantillaReportecrearpatrTipo(tGADDPLANTREPptpatrTipo);
        step1Request.setPlantillaReportecrearpatrTitulo(tGADDPLANTREPptpatrTitulo);
        step1Request.setPlantillaReportecrearpatrFechaVigencia(tGADDPLANTREPptpatrFechaVigencia);
        step1Request.setPlantillaReportecrearpatrRutaArchivos(tGADDPLANTREPptpatrRutaArchivos);
        step1Request.setPlantillaReportecrearpatrNombreArchivoPDF(tGADDPLANTREPptpatrNombreArchivoPDF);
        plantillaReporteSrv.crearImplementation(step1Request);
        return response;
    }

    /*
     * Checks service TGADDPLANTREP preconditions individually
     * @param request Service request viewmodel
     * @returns Service response viewmodel with the execution results
     */
    public ServiceResponse tGADDPLANTREPCheckPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGADDPLANTREPRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            if("IGl_1623834492928280Pre_2".equals(request.getPreconditionId()))
                checkIGl1623834492928280Pre2(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tGADDPLANTREPPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGADDPLANTREPRequest request) 
            throws ModelException {
        checkIGl1623834492928280Pre2(request);
    }

    private void checkIGl1623834492928280Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGADDPLANTREPRequest request) 
            throws ModelException {
        // COUNT(PlantillaReporte) WHERE (PlantillaReporte.Tipo <> pt_p_atrTipo AND PlantillaReporte.NombreArchivoPDF = pt_p_atrNombreArchivoPDF) = 0

        Long tGADDPLANTREPptpatrTipo = request.getTGADDPLANTREPptpatrTipo();
        String tGADDPLANTREPptpatrNombreArchivoPDF = request.getTGADDPLANTREPptpatrNombreArchivoPDF();
        try {
            boolean preconditionHolds = (globalServicesRepository.assocOperator143(tGADDPLANTREPptpatrTipo, tGADDPLANTREPptpatrNombreArchivoPDF)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("No puede crearse el reporte porque ya existe uno de otro tipo con el mismo nombre para el fichero pdf. Utilice otro nombre de fichero");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    /*
     * Entry point for global service TGEDITPLANTREP
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGEDITPLANTREP(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGEDITPLANTREPRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGEDITPLANTREPTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGEDITPLANTREP
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGEDITPLANTREPTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGEDITPLANTREPRequest request) throws ModelException {
        request.setTGEDITPLANTREPptpthisPlantillaReporteInstance(plantillaReporteSrv.getByOIDWithHV(request.getTGEDITPLANTREPptpthisPlantillaReporte()));
        return globalServicesSrv.tGEDITPLANTREPImplementation(request);
    }

    /*
     * Implements global service TGEDITPLANTREP
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGEDITPLANTREPImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGEDITPLANTREPRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        PlantillaReporte tGEDITPLANTREPptpthisPlantillaReporte = request.getTGEDITPLANTREPptpthisPlantillaReporteInstance();
        String tGEDITPLANTREPptpTitulo = request.getTGEDITPLANTREPptpTitulo();
        Date tGEDITPLANTREPptpFechaVigencia = request.getTGEDITPLANTREPptpFechaVigencia();
        String tGEDITPLANTREPptpRutaArchivos = request.getTGEDITPLANTREPptpRutaArchivos();
        String tGEDITPLANTREPptpNombreArchivoPDF = request.getTGEDITPLANTREPptpNombreArchivoPDF();

        request.checkArguments();

        tGEDITPLANTREPPreconditions(request);

        com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.ModificarRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.plantillareporte.siu.ModificarRequest();
        step1Request.setPlantillaReportemodificarpthisPlantillaReporteInstance(tGEDITPLANTREPptpthisPlantillaReporte);
        step1Request.setPlantillaReportemodificarpTitulo(tGEDITPLANTREPptpTitulo);
        step1Request.setPlantillaReportemodificarpFechaVigencia(tGEDITPLANTREPptpFechaVigencia);
        step1Request.setPlantillaReportemodificarpRutaArchivos(tGEDITPLANTREPptpRutaArchivos);
        step1Request.setPlantillaReportemodificarpNombreArchivoPDF(tGEDITPLANTREPptpNombreArchivoPDF);
        plantillaReporteSrv.modificarImplementation(step1Request);
        return response;
    }

    /*
     * Checks service TGEDITPLANTREP preconditions individually
     * @param request Service request viewmodel
     * @returns Service response viewmodel with the execution results
     */
    public ServiceResponse tGEDITPLANTREPCheckPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGEDITPLANTREPRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setTGEDITPLANTREPptpthisPlantillaReporteInstance(plantillaReporteSrv.getByOIDWithHV(request.getTGEDITPLANTREPptpthisPlantillaReporte()));
        try {
            if("IGl_1623834755072701Pre_2".equals(request.getPreconditionId()))
                checkIGl1623834755072701Pre2(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tGEDITPLANTREPPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGEDITPLANTREPRequest request) 
            throws ModelException {
        checkIGl1623834755072701Pre2(request);
    }

    private void checkIGl1623834755072701Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGEDITPLANTREPRequest request) 
            throws ModelException {
        // COUNT(PlantillaReporte) WHERE (PlantillaReporte.Tipo <> pt_p_thisPlantillaReporte.Tipo AND PlantillaReporte.NombreArchivoPDF = pt_p_NombreArchivoPDF) = 0

        PlantillaReporte tGEDITPLANTREPptpthisPlantillaReporte = request.getTGEDITPLANTREPptpthisPlantillaReporteInstance();
        String tGEDITPLANTREPptpNombreArchivoPDF = request.getTGEDITPLANTREPptpNombreArchivoPDF();
        try {
            boolean preconditionHolds = (globalServicesRepository.assocOperator144(tGEDITPLANTREPptpthisPlantillaReporte, tGEDITPLANTREPptpNombreArchivoPDF)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("No puede crearse el reporte porque ya existe uno de otro tipo con el mismo nombre para el fichero pdf. Utilice otro nombre de fichero");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    /*
     * Entry point for global service TGCREARFINIEPS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGCREARFINIEPS(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARFINIEPSRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGCREARFINIEPSTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGCREARFINIEPS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGCREARFINIEPSTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARFINIEPSRequest request) throws ModelException {
        return globalServicesSrv.tGCREARFINIEPSImplementation(request);
    }

    /*
     * Implements global service TGCREARFINIEPS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGCREARFINIEPSImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARFINIEPSRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        Long tGCREARFINIEPSptpatrTramite = request.getTGCREARFINIEPSptpatrTramite();
        Long tGCREARFINIEPSptpatrTipoContrato = request.getTGCREARFINIEPSptpatrTipoContrato();
        Date tGCREARFINIEPSptpatrDesde = request.getTGCREARFINIEPSptpatrDesde();
        Date tGCREARFINIEPSptpatrHasta = request.getTGCREARFINIEPSptpatrHasta();
        Date tGCREARFINIEPSptpatrFechaInicioAplicar = request.getTGCREARFINIEPSptpatrFechaInicioAplicar();

        request.checkArguments();

        tGCREARFINIEPSPreconditions(request);

        com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.CrearRequest();
        step1Request.setFechaIniEPScrearpatrTramite(tGCREARFINIEPSptpatrTramite);
        step1Request.setFechaIniEPScrearpatrTipoContrato(tGCREARFINIEPSptpatrTipoContrato);
        step1Request.setFechaIniEPScrearpatrDesde(tGCREARFINIEPSptpatrDesde);
        step1Request.setFechaIniEPScrearpatrHasta(tGCREARFINIEPSptpatrHasta);
        step1Request.setFechaIniEPScrearpatrFechaInicioAplicar(tGCREARFINIEPSptpatrFechaInicioAplicar);
        fechaIniEPSSrv.crearImplementation(step1Request);
        return response;
    }

    /*
     * Checks service TGCREARFINIEPS preconditions individually
     * @param request Service request viewmodel
     * @returns Service response viewmodel with the execution results
     */
    public ServiceResponse tGCREARFINIEPSCheckPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARFINIEPSRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            if("IGl_1655288233984471Pre_1".equals(request.getPreconditionId()))
                checkIGl1655288233984471Pre1(request);
            if("IGl_1655288233984471Pre_2".equals(request.getPreconditionId()))
                checkIGl1655288233984471Pre2(request);
            if("IGl_1655288233984471Pre_3".equals(request.getPreconditionId()))
                checkIGl1655288233984471Pre3(request);
            if("IGl_1655288233984471Pre_4".equals(request.getPreconditionId()))
                checkIGl1655288233984471Pre4(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tGCREARFINIEPSPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARFINIEPSRequest request) 
            throws ModelException {
        checkIGl1655288233984471Pre1(request);
        checkIGl1655288233984471Pre2(request);
        checkIGl1655288233984471Pre3(request);
        checkIGl1655288233984471Pre4(request);
    }

    private void checkIGl1655288233984471Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARFINIEPSRequest request) 
            throws ModelException {
        // COUNT( FechaIniEPS ) WHERE (FechaIniEPS.Desde >= pt_p_atrDesde AND FechaIniEPS.Desde <= pt_p_atrHasta AND FechaIniEPS.Tramite = pt_p_atrTramite AND FechaIniEPS.TipoContrato = pt_p_atrTipoContrato) = 0

        Date tGCREARFINIEPSptpatrDesde = request.getTGCREARFINIEPSptpatrDesde();
        Date tGCREARFINIEPSptpatrHasta = request.getTGCREARFINIEPSptpatrHasta();
        Long tGCREARFINIEPSptpatrTramite = request.getTGCREARFINIEPSptpatrTramite();
        Long tGCREARFINIEPSptpatrTipoContrato = request.getTGCREARFINIEPSptpatrTipoContrato();
        try {
            boolean preconditionHolds = (globalServicesRepository.assocOperator145(tGCREARFINIEPSptpatrDesde, tGCREARFINIEPSptpatrHasta, tGCREARFINIEPSptpatrTramite, tGCREARFINIEPSptpatrTipoContrato)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Ya existe otro registro para el mismo trámite y tipo de contrato que se solapa en fechas");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    private void checkIGl1655288233984471Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARFINIEPSRequest request) 
            throws ModelException {
        // COUNT( FechaIniEPS ) WHERE (FechaIniEPS.Hasta >= pt_p_atrDesde AND FechaIniEPS.Hasta <= pt_p_atrHasta AND FechaIniEPS.Tramite = pt_p_atrTramite AND FechaIniEPS.TipoContrato = pt_p_atrTipoContrato) = 0

        Date tGCREARFINIEPSptpatrDesde = request.getTGCREARFINIEPSptpatrDesde();
        Date tGCREARFINIEPSptpatrHasta = request.getTGCREARFINIEPSptpatrHasta();
        Long tGCREARFINIEPSptpatrTramite = request.getTGCREARFINIEPSptpatrTramite();
        Long tGCREARFINIEPSptpatrTipoContrato = request.getTGCREARFINIEPSptpatrTipoContrato();
        try {
            boolean preconditionHolds = (globalServicesRepository.assocOperator146(tGCREARFINIEPSptpatrDesde, tGCREARFINIEPSptpatrHasta, tGCREARFINIEPSptpatrTramite, tGCREARFINIEPSptpatrTipoContrato)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Ya existe otro registro para el mismo trámite y tipo de contrato que se solapa en fechas");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    private void checkIGl1655288233984471Pre3 (
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARFINIEPSRequest request) 
            throws ModelException {
        // COUNT( FechaIniEPS ) WHERE (FechaIniEPS.Desde <= pt_p_atrDesde AND FechaIniEPS.Hasta >= pt_p_atrHasta AND FechaIniEPS.Tramite = pt_p_atrTramite AND FechaIniEPS.TipoContrato = pt_p_atrTipoContrato) = 0

        Date tGCREARFINIEPSptpatrDesde = request.getTGCREARFINIEPSptpatrDesde();
        Date tGCREARFINIEPSptpatrHasta = request.getTGCREARFINIEPSptpatrHasta();
        Long tGCREARFINIEPSptpatrTramite = request.getTGCREARFINIEPSptpatrTramite();
        Long tGCREARFINIEPSptpatrTipoContrato = request.getTGCREARFINIEPSptpatrTipoContrato();
        try {
            boolean preconditionHolds = (globalServicesRepository.assocOperator147(tGCREARFINIEPSptpatrDesde, tGCREARFINIEPSptpatrHasta, tGCREARFINIEPSptpatrTramite, tGCREARFINIEPSptpatrTipoContrato)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Ya existe otro registro para el mismo trámite y tipo de contrato que se solapa en fechas");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    private void checkIGl1655288233984471Pre4 (
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARFINIEPSRequest request) 
            throws ModelException {
        // COUNT( FechaIniEPS ) WHERE (FechaIniEPS.Desde >= pt_p_atrDesde AND FechaIniEPS.Hasta <= pt_p_atrHasta AND FechaIniEPS.Tramite = pt_p_atrTramite AND FechaIniEPS.TipoContrato = pt_p_atrTipoContrato) = 0

        Date tGCREARFINIEPSptpatrDesde = request.getTGCREARFINIEPSptpatrDesde();
        Date tGCREARFINIEPSptpatrHasta = request.getTGCREARFINIEPSptpatrHasta();
        Long tGCREARFINIEPSptpatrTramite = request.getTGCREARFINIEPSptpatrTramite();
        Long tGCREARFINIEPSptpatrTipoContrato = request.getTGCREARFINIEPSptpatrTipoContrato();
        try {
            boolean preconditionHolds = (globalServicesRepository.assocOperator148(tGCREARFINIEPSptpatrDesde, tGCREARFINIEPSptpatrHasta, tGCREARFINIEPSptpatrTramite, tGCREARFINIEPSptpatrTipoContrato)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Ya existe otro registro para el mismo trámite y tipo de contrato que se solapa en fechas");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    /*
     * Entry point for global service TGMODFINIEPS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGMODFINIEPS(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGMODFINIEPSRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGMODFINIEPSTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGMODFINIEPS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGMODFINIEPSTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGMODFINIEPSRequest request) throws ModelException {
        request.setTGMODFINIEPSptpthisFechaIniEPSInstance(fechaIniEPSSrv.getByOIDWithHV(request.getTGMODFINIEPSptpthisFechaIniEPS()));
        return globalServicesSrv.tGMODFINIEPSImplementation(request);
    }

    /*
     * Implements global service TGMODFINIEPS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGMODFINIEPSImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGMODFINIEPSRequest request) throws ModelException {
        ServiceResponse response = new ServiceResponse();
        FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS = request.getTGMODFINIEPSptpthisFechaIniEPSInstance();
        Date tGMODFINIEPSptpDesde = request.getTGMODFINIEPSptpDesde();
        Date tGMODFINIEPSptpHasta = request.getTGMODFINIEPSptpHasta();
        Date tGMODFINIEPSptpFechaInicioAplicar = request.getTGMODFINIEPSptpFechaInicioAplicar();
        Long tGMODFINIEPSptTratime = request.getTGMODFINIEPSptTratime();
        Long tGMODFINIEPSptTipoContrato = request.getTGMODFINIEPSptTipoContrato();

        request.checkArguments();

        tGMODFINIEPSPreconditions(request);

        com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.ModificarRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.fechainieps.siu.ModificarRequest();
        step1Request.setFechaIniEPSmodificarpthisFechaIniEPSInstance(tGMODFINIEPSptpthisFechaIniEPS);
        step1Request.setFechaIniEPSmodificarpDesde(tGMODFINIEPSptpDesde);
        step1Request.setFechaIniEPSmodificarpHasta(tGMODFINIEPSptpHasta);
        step1Request.setFechaIniEPSmodificarpFechaInicioAplicar(tGMODFINIEPSptpFechaInicioAplicar);
        fechaIniEPSSrv.modificarImplementation(step1Request);
        return response;
    }

    /*
     * Checks service TGMODFINIEPS preconditions individually
     * @param request Service request viewmodel
     * @returns Service response viewmodel with the execution results
     */
    public ServiceResponse tGMODFINIEPSCheckPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGMODFINIEPSRequest request) {
        ServiceResponse response = new ServiceResponse();
        request.setTGMODFINIEPSptpthisFechaIniEPSInstance(fechaIniEPSSrv.getByOIDWithHV(request.getTGMODFINIEPSptpthisFechaIniEPS()));
        try {
            if("IGl_1655288365056783Pre_1".equals(request.getPreconditionId()))
                checkIGl1655288365056783Pre1(request);
            if("IGl_1655288365056783Pre_2".equals(request.getPreconditionId()))
                checkIGl1655288365056783Pre2(request);
            if("IGl_1655288365056783Pre_3".equals(request.getPreconditionId()))
                checkIGl1655288365056783Pre3(request);
            if("IGl_1655288365056783Pre_4".equals(request.getPreconditionId()))
                checkIGl1655288365056783Pre4(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tGMODFINIEPSPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGMODFINIEPSRequest request) 
            throws ModelException {
        checkIGl1655288365056783Pre1(request);
        checkIGl1655288365056783Pre2(request);
        checkIGl1655288365056783Pre3(request);
        checkIGl1655288365056783Pre4(request);
    }

    private void checkIGl1655288365056783Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGMODFINIEPSRequest request) 
            throws ModelException {
        // COUNT( FechaIniEPS ) WHERE (FechaIniEPS.Id <> pt_p_thisFechaIniEPS.Id AND FechaIniEPS.Desde >= pt_p_Desde AND FechaIniEPS.Desde <= pt_p_Hasta AND FechaIniEPS.Tramite = pt_p_thisFechaIniEPS.Tramite AND FechaIniEPS.TipoContrato = pt_p_thisFechaIniEPS.TipoContrato) = 0

        FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS = request.getTGMODFINIEPSptpthisFechaIniEPSInstance();
        Date tGMODFINIEPSptpDesde = request.getTGMODFINIEPSptpDesde();
        Date tGMODFINIEPSptpHasta = request.getTGMODFINIEPSptpHasta();
        try {
            boolean preconditionHolds = (globalServicesRepository.assocOperator149(tGMODFINIEPSptpthisFechaIniEPS, tGMODFINIEPSptpDesde, tGMODFINIEPSptpHasta)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Ya existe otro registro para el mismo trámite y tipo de contrato que se solapa en fechas");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    private void checkIGl1655288365056783Pre2 (
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGMODFINIEPSRequest request) 
            throws ModelException {
        // COUNT( FechaIniEPS ) WHERE (FechaIniEPS.Id <> pt_p_thisFechaIniEPS.Id AND FechaIniEPS.Hasta >= pt_p_Desde AND FechaIniEPS.Hasta <= pt_p_Hasta AND FechaIniEPS.Tramite = pt_p_thisFechaIniEPS.Tramite AND FechaIniEPS.TipoContrato = pt_p_thisFechaIniEPS.TipoContrato) = 0

        FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS = request.getTGMODFINIEPSptpthisFechaIniEPSInstance();
        Date tGMODFINIEPSptpDesde = request.getTGMODFINIEPSptpDesde();
        Date tGMODFINIEPSptpHasta = request.getTGMODFINIEPSptpHasta();
        try {
            boolean preconditionHolds = (globalServicesRepository.assocOperator150(tGMODFINIEPSptpthisFechaIniEPS, tGMODFINIEPSptpDesde, tGMODFINIEPSptpHasta)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Ya existe otro registro para el mismo trámite y tipo de contrato que se solapa en fechas");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    private void checkIGl1655288365056783Pre3 (
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGMODFINIEPSRequest request) 
            throws ModelException {
        // COUNT( FechaIniEPS ) WHERE (FechaIniEPS.Id <> pt_p_thisFechaIniEPS.Id AND FechaIniEPS.Desde <= pt_p_Desde AND FechaIniEPS.Hasta >= pt_p_Hasta AND FechaIniEPS.Tramite = pt_p_thisFechaIniEPS.Tramite AND FechaIniEPS.TipoContrato = pt_p_thisFechaIniEPS.TipoContrato) = 0

        FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS = request.getTGMODFINIEPSptpthisFechaIniEPSInstance();
        Date tGMODFINIEPSptpDesde = request.getTGMODFINIEPSptpDesde();
        Date tGMODFINIEPSptpHasta = request.getTGMODFINIEPSptpHasta();
        try {
            boolean preconditionHolds = (globalServicesRepository.assocOperator151(tGMODFINIEPSptpthisFechaIniEPS, tGMODFINIEPSptpDesde, tGMODFINIEPSptpHasta)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Ya existe otro registro para el mismo trámite y tipo de contrato que se solapa en fechas");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    private void checkIGl1655288365056783Pre4 (
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGMODFINIEPSRequest request) 
            throws ModelException {
        // COUNT( FechaIniEPS ) WHERE (FechaIniEPS.Id <> pt_p_thisFechaIniEPS.Id AND FechaIniEPS.Desde >= pt_p_Desde AND FechaIniEPS.Hasta <= pt_p_Hasta AND FechaIniEPS.Tramite = pt_p_thisFechaIniEPS.Tramite AND FechaIniEPS.TipoContrato = pt_p_thisFechaIniEPS.TipoContrato) = 0

        FechaIniEPS tGMODFINIEPSptpthisFechaIniEPS = request.getTGMODFINIEPSptpthisFechaIniEPSInstance();
        Date tGMODFINIEPSptpDesde = request.getTGMODFINIEPSptpDesde();
        Date tGMODFINIEPSptpHasta = request.getTGMODFINIEPSptpHasta();
        try {
            boolean preconditionHolds = (globalServicesRepository.assocOperator152(tGMODFINIEPSptpthisFechaIniEPS, tGMODFINIEPSptpDesde, tGMODFINIEPSptpHasta)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("Ya existe otro registro para el mismo trámite y tipo de contrato que se solapa en fechas");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    /*
     * Entry point for global service TGCREARCONTEXENTO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGCREARCONTEXENTO(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARCONTEXENTORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGCREARCONTEXENTOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGCREARCONTEXENTO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGCREARCONTEXENTOTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARCONTEXENTORequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGCREARCONTEXENTOImplementation(request);
    }

    /*
     * Implements global service TGCREARCONTEXENTO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGCREARCONTEXENTOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARCONTEXENTORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        String tGCREARCONTEXENTOptpatrNumContrato = request.getTGCREARCONTEXENTOptpatrNumContrato();

        request.checkArguments();

        tGCREARCONTEXENTOPreconditions(request);

        com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.siu.CrearRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.siu.CrearRequest();
        step1Request.setContratosExentosFIngresocrearpatrNumContrato(tGCREARCONTEXENTOptpatrNumContrato);
        contratosExentosFIngresoSrv.crearImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.TCREARRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.TCREARRequest();
        step2Request.setLogContratosExentosFIngresoTCREARptpatrAccion("Inclusión");
        step2Request.setLogContratosExentosFIngresoTCREARptpatrNumContrato(tGCREARCONTEXENTOptpatrNumContrato);
        logContratosExentosFIngresoSrv.tCREARImplementation(step2Request);
        return response;
    }

    /*
     * Checks service TGCREARCONTEXENTO preconditions individually
     * @param request Service request viewmodel
     * @returns Service response viewmodel with the execution results
     */
    public ServiceResponse tGCREARCONTEXENTOCheckPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARCONTEXENTORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            if("IGl_1658397786112231Pre_1".equals(request.getPreconditionId()))
                checkIGl1658397786112231Pre1(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    private void tGCREARCONTEXENTOPreconditions(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARCONTEXENTORequest request) 
            throws ModelException {
        checkIGl1658397786112231Pre1(request);
    }

    private void checkIGl1658397786112231Pre1 (
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARCONTEXENTORequest request) 
            throws ModelException {
        // COUNT(ContratosExentosFIngreso) WHERE (ContratosExentosFIngreso.NumContrato = pt_p_atrNumContrato) = 0

        String tGCREARCONTEXENTOptpatrNumContrato = request.getTGCREARCONTEXENTOptpatrNumContrato();
        try {
            boolean preconditionHolds = (globalServicesRepository.assocOperator153(tGCREARCONTEXENTOptpatrNumContrato)).equals(Long.valueOf(0));
            if (!preconditionHolds) throw new PreconditionException("El número de contrato indicado ya está añadido a la paramétrica");
        } catch (SystemException e) {
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
        }
    }

    /*
     * Entry point for global service TGDELCONTRATOEXENTO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGDELCONTRATOEXENTO(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGDELCONTRATOEXENTORequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGDELCONTRATOEXENTOTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGDELCONTRATOEXENTO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGDELCONTRATOEXENTOTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGDELCONTRATOEXENTORequest request) throws ModelException, SystemException {
        request.setTGDELCONTRATOEXENTOptpthisContratosExentosFIngrInstance(contratosExentosFIngresoSrv.getByOIDWithHV(request.getTGDELCONTRATOEXENTOptpthisContratosExentosFIngr()));
        return globalServicesSrv.tGDELCONTRATOEXENTOImplementation(request);
    }

    /*
     * Implements global service TGDELCONTRATOEXENTO
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGDELCONTRATOEXENTOImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGDELCONTRATOEXENTORequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        ContratosExentosFIngreso tGDELCONTRATOEXENTOptpthisContratosExentosFIngr = request.getTGDELCONTRATOEXENTOptpthisContratosExentosFIngrInstance();

        request.checkArguments();

        com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.TCREARRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.logcontratosexentosfingreso.siu.TCREARRequest();
        step1Request.setLogContratosExentosFIngresoTCREARptpatrAccion("Exclusión");
        step1Request.setLogContratosExentosFIngresoTCREARptpatrNumContrato(tGDELCONTRATOEXENTOptpthisContratosExentosFIngr.getContratosExentosFIngresoNumContrato());
        logContratosExentosFIngresoSrv.tCREARImplementation(step1Request);

        com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.siu.EliminarRequest step2Request = new com.keralty.aeconfiguraciones.viewmodel.contratosexentosfingreso.siu.EliminarRequest();
        step2Request.setContratosExentosFIngresoeliminarpthisContratosExentosFIngresoInstance(tGDELCONTRATOEXENTOptpthisContratosExentosFIngr);
        contratosExentosFIngresoSrv.eliminarImplementation(step2Request);
        return response;
    }

    /*
     * Entry point for global service OGSINCRONIZARPARAMETRICAS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse oGSINCRONIZARPARAMETRICAS(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCRONIZARPARAMETRICASRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.oGSINCRONIZARPARAMETRICASTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service OGSINCRONIZARPARAMETRICAS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse oGSINCRONIZARPARAMETRICASTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCRONIZARPARAMETRICASRequest request) throws ModelException, SystemException {
        return globalServicesSrv.oGSINCRONIZARPARAMETRICASImplementation(request);
    }

    /*
     * Implements global service OGSINCRONIZARPARAMETRICAS
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse oGSINCRONIZARPARAMETRICASImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCRONIZARPARAMETRICASRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();

        try {
            for(FrecuenciaAct step1Entity : globalServicesRepository.assocOperator158()) {
                try {
                    com.keralty.aeconfiguraciones.viewmodel.frecuenciaact.siu.ModificarRequest step1Request = new com.keralty.aeconfiguraciones.viewmodel.frecuenciaact.siu.ModificarRequest();
                    step1Request.setFrecuenciaActmodificarpthisFrecuenciaActInstance(step1Entity);
                    step1Request.setFrecuenciaActmodificarpFrecuencia(Long.valueOf(0));
                    frecuenciaActSrv.modificarTxn(step1Request);
                } catch (Exception e) { }
            }
        } catch (Exception e) { }
        return response;
    }

    /*
     * Entry point for global service TGFIJARDATOSSINC
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public ServiceResponse tGFIJARDATOSSINC(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest request) {
        ServiceResponse response = new ServiceResponse();
        try {
            response = globalServicesSrv.tGFIJARDATOSSINCTxn(request);
        } catch (Exception e) {
            response.setException(e);
        }
        return response;
    }

    /*
     * Transactional entry point for global service TGFIJARDATOSSINC
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public ServiceResponse tGFIJARDATOSSINCTxn(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest request) throws ModelException, SystemException {
        return globalServicesSrv.tGFIJARDATOSSINCImplementation(request);
    }

    /*
     * Implements global service TGFIJARDATOSSINC
     * @param request Service request viewmodel
     * @return Service response viewmodel with the execution results
     */
    @Transactional(Transactional.TxType.REQUIRED)
    public ServiceResponse tGFIJARDATOSSINCImplementation(
            com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest request) throws ModelException, SystemException {
        ServiceResponse response = new ServiceResponse();
        Long tGFIJARDATOSSINCptNumParametrica = request.getTGFIJARDATOSSINCptNumParametrica();
        String tGFIJARDATOSSINCptResultado = request.getTGFIJARDATOSSINCptResultado();

        request.checkArguments();

        /* Action 1: IF (AGENTFuncionario <> NULL) */
        boolean ifCondition1 = (((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)) != null && !((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).isNull());
        if (ifCondition1) {
            for(FrecuenciaAct step110Entity : globalServicesRepository.assocOperator159(tGFIJARDATOSSINCptNumParametrica)) {
                com.keralty.aeconfiguraciones.viewmodel.frecuenciaact.siu.FijarSincronizacionRequest step10Request = new com.keralty.aeconfiguraciones.viewmodel.frecuenciaact.siu.FijarSincronizacionRequest();
                step10Request.setFrecuenciaActfijarSincronizacionpthisFrecuenciaActInstance(step110Entity);
                step10Request.setFrecuenciaActfijarSincronizacionpFechaUltimaSinc(STDFunctions.systemDateFun());
                step10Request.setFrecuenciaActfijarSincronizacionpResultadoSinc(tGFIJARDATOSSINCptResultado);
                step10Request.setFrecuenciaActfijarSincronizacionpUsuario(((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionarioUsuario());
                step10Request.setFrecuenciaActfijarSincronizacionpNombreUsuario(((Funcionario) getAgentInstance(FuncionarioConstants.CLASS_NAME)).getFuncionariodrvNombreCompleto());
                frecuenciaActSrv.fijarSincronizacionImplementation(step10Request);
            }
        } else {
            /* Action 11: IF (AGENTUsuarioSistemaExterno <> NULL) */
            boolean ifCondition11 = (((UsuarioSistemaExterno) getAgentInstance(UsuarioSistemaExternoConstants.CLASS_NAME)) != null && !((UsuarioSistemaExterno) getAgentInstance(UsuarioSistemaExternoConstants.CLASS_NAME)).isNull());
            if (ifCondition11) {
                for(FrecuenciaAct step1210Entity : globalServicesRepository.assocOperator160(tGFIJARDATOSSINCptNumParametrica)) {
                    com.keralty.aeconfiguraciones.viewmodel.frecuenciaact.siu.FijarSincronizacionRequest step110Request = new com.keralty.aeconfiguraciones.viewmodel.frecuenciaact.siu.FijarSincronizacionRequest();
                    step110Request.setFrecuenciaActfijarSincronizacionpthisFrecuenciaActInstance(step1210Entity);
                    step110Request.setFrecuenciaActfijarSincronizacionpFechaUltimaSinc(STDFunctions.systemDateFun());
                    step110Request.setFrecuenciaActfijarSincronizacionpResultadoSinc(tGFIJARDATOSSINCptResultado);
                    step110Request.setFrecuenciaActfijarSincronizacionpUsuario(((UsuarioSistemaExterno) getAgentInstance(UsuarioSistemaExternoConstants.CLASS_NAME)).getUsuarioSistemaExternoLogin());
                    step110Request.setFrecuenciaActfijarSincronizacionpNombreUsuario("");
                    frecuenciaActSrv.fijarSincronizacionImplementation(step110Request);
                }
            } else {
                for(FrecuenciaAct step1211Entity : globalServicesRepository.assocOperator161(tGFIJARDATOSSINCptNumParametrica)) {
                    com.keralty.aeconfiguraciones.viewmodel.frecuenciaact.siu.FijarSincronizacionRequest step111Request = new com.keralty.aeconfiguraciones.viewmodel.frecuenciaact.siu.FijarSincronizacionRequest();
                    step111Request.setFrecuenciaActfijarSincronizacionpthisFrecuenciaActInstance(step1211Entity);
                    step111Request.setFrecuenciaActfijarSincronizacionpFechaUltimaSinc(STDFunctions.systemDateFun());
                    step111Request.setFrecuenciaActfijarSincronizacionpResultadoSinc(tGFIJARDATOSSINCptResultado);
                    step111Request.setFrecuenciaActfijarSincronizacionpUsuario("Auto");
                    step111Request.setFrecuenciaActfijarSincronizacionpNombreUsuario("");
                    frecuenciaActSrv.fijarSincronizacionImplementation(step111Request);
                }
            }
        }
        return response;
    }
}
