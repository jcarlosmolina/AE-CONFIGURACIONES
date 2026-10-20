package com.keralty.aeconfiguraciones.api.dominio;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Dominio;
import com.keralty.aeconfiguraciones.persistence.oid.DominioOid;
import com.keralty.aeconfiguraciones.service.DominioService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioFirmaRespClausulado;
import com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioIIU;
import com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioMGrupoAreaMedica;
import com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioMGrupoAsesores;
import com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioMGruposUsuarios;
import com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioMLog;
import com.keralty.aeconfiguraciones.viewmodel.dominio.ds.DSDominioSolNovedad;
import com.keralty.aeconfiguraciones.viewmodel.dominio.piu.PIUDominioDIncompatiblesResponse;
import com.keralty.aeconfiguraciones.viewmodel.dominio.piu.PIUDominioObsRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.piu.PIUDominioObsResponse;
import com.keralty.aeconfiguraciones.viewmodel.dominio.piu.PIUDominioRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.piu.PIUDominioResponse;
import com.keralty.aeconfiguraciones.viewmodel.dominio.piu.PIUDominiosPorAsesorResponse;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CambiarDiasVigenciaRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.CrearResponse;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelFormaPagoRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelPeriodicidadDominioRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.DelRollDominioRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsFormaPagoRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsPeriodicidadDominioRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.InsRolDominioRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModDatosRespFirmaRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModificarConfSolNovedadRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.ModImagenFirmaRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDGRPASESORESRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDGRPMEDICORequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TADDREPORTERequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELGRPASESORESRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELGRPMEDICORequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TDELINCOMPATIBLERequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TELIMINARRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TINSINCOMPATIBLERequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TINSNOVEDADRequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TINSPLANTILLARequest;
import com.keralty.aeconfiguraciones.viewmodel.dominio.siu.TMODIFICARRequest;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import java.util.List;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/api/DominioApi")
@RequestScoped
public class DominioApi {

    DominioService service;

    public DominioApi(DominioService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{dominioNumero}")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public Dominio getInstance(Long dominioNumero) {
    	return service.getById(dominioNumero);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<Dominio> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<PreloadViewModel> getDominioPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public Dominio getByOid(DominioOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public Dominio getByOIDWithHV(DominioOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetDominioDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getDominioDynamic(QueryRequest request) {
        DominioOid oid = new DominioOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getDominioDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PreloadResponseViewModel getDominioWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getDominioPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"Admin","SuperAdmin"})
    public CrearResponse dominioCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/crearCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public CrearResponse dominioCrearCheckPreconditions(CrearRequest request) {

        return service.crearCheckPreconditions(request);
    }

    @POST
    @Path(value = "/InsPeriodicidadDominio")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioInsPeriodicidadDominio(InsPeriodicidadDominioRequest request) {
        return service.insPeriodicidadDominio(request);
    }
    

    @POST
    @Path(value = "/DelPeriodicidadDominio")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioDelPeriodicidadDominio(DelPeriodicidadDominioRequest request) {
        return service.delPeriodicidadDominio(request);
    }
    

    @POST
    @Path(value = "/InsRolDominio")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioInsRolDominio(InsRolDominioRequest request) {
        return service.insRolDominio(request);
    }
    

    @POST
    @Path(value = "/DelRollDominio")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioDelRollDominio(DelRollDominioRequest request) {
        return service.delRollDominio(request);
    }
    

    @POST
    @Path(value = "/modDatosRespFirma")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioModDatosRespFirma(ModDatosRespFirmaRequest request) {
        return service.modDatosRespFirma(request);
    }
    

    @POST
    @Path(value = "/modDatosRespFirmaCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioModDatosRespFirmaCheckPreconditions(ModDatosRespFirmaRequest request) {

        return service.modDatosRespFirmaCheckPreconditions(request);
    }

    @POST
    @Path(value = "/modImagenFirma")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioModImagenFirma(ModImagenFirmaRequest request) {
        return service.modImagenFirma(request);
    }
    

    @POST
    @Path(value = "/modImagenFirmaCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioModImagenFirmaCheckPreconditions(ModImagenFirmaRequest request) {

        return service.modImagenFirmaCheckPreconditions(request);
    }

    @POST
    @Path(value = "/InsFormaPago")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioInsFormaPago(InsFormaPagoRequest request) {
        return service.insFormaPago(request);
    }
    

    @POST
    @Path(value = "/DelFormaPago")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioDelFormaPago(DelFormaPagoRequest request) {
        return service.delFormaPago(request);
    }
    

    @POST
    @Path(value = "/cambiarDiasVigencia")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioCambiarDiasVigencia(CambiarDiasVigenciaRequest request) {
        return service.cambiarDiasVigencia(request);
    }
    

    @POST
    @Path(value = "/cambiarDiasVigenciaCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioCambiarDiasVigenciaCheckPreconditions(CambiarDiasVigenciaRequest request) {

        return service.cambiarDiasVigenciaCheckPreconditions(request);
    }

    @POST
    @Path(value = "/modificarConfSolNovedad")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioModificarConfSolNovedad(ModificarConfSolNovedadRequest request) {
        return service.modificarConfSolNovedad(request);
    }
    

    @POST
    @Path(value = "/TELIMINAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioTELIMINAR(TELIMINARRequest request) {
        return service.tELIMINAR(request);
    }
    

    @POST
    @Path(value = "/TINSPLANTILLA")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioTINSPLANTILLA(TINSPLANTILLARequest request) {
        return service.tINSPLANTILLA(request);
    }
    

    @POST
    @Path(value = "/TINSNOVEDAD")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioTINSNOVEDAD(TINSNOVEDADRequest request) {
        return service.tINSNOVEDAD(request);
    }
    

    @POST
    @Path(value = "/TADDGRPASESORES")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioTADDGRPASESORES(TADDGRPASESORESRequest request) {
        return service.tADDGRPASESORES(request);
    }
    

    @POST
    @Path(value = "/TDELGRPASESORES")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioTDELGRPASESORES(TDELGRPASESORESRequest request) {
        return service.tDELGRPASESORES(request);
    }
    

    @POST
    @Path(value = "/TADDGRPMEDICO")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioTADDGRPMEDICO(TADDGRPMEDICORequest request) {
        return service.tADDGRPMEDICO(request);
    }
    

    @POST
    @Path(value = "/TDELGRPMEDICO")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioTDELGRPMEDICO(TDELGRPMEDICORequest request) {
        return service.tDELGRPMEDICO(request);
    }
    

    @POST
    @Path(value = "/TADDREPORTE")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioTADDREPORTE(TADDREPORTERequest request) {
        return service.tADDREPORTE(request);
    }
    

    @POST
    @Path(value = "/TADDREPORTECheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioTADDREPORTECheckPreconditions(TADDREPORTERequest request) {

        return service.tADDREPORTECheckPreconditions(request);
    }

    @POST
    @Path(value = "/TINSINCOMPATIBLE")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioTINSINCOMPATIBLE(TINSINCOMPATIBLERequest request) {
        return service.tINSINCOMPATIBLE(request);
    }
    

    @POST
    @Path(value = "/TDELINCOMPATIBLE")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioTDELINCOMPATIBLE(TDELINCOMPATIBLERequest request) {
        return service.tDELINCOMPATIBLE(request);
    }
    

    @POST
    @Path(value = "/TMODIFICAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse dominioTMODIFICAR(TMODIFICARRequest request) {
        return service.tMODIFICAR(request);
    }
    

    @POST
    @Path(value = "/IIU_Dominio")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public DSDominioIIU iiuIIUDominio(DominioOid oid) {
        return service.iIUDominio(oid);
    }

    @POST
    @Path(value = "/IIU_DominioFirma")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public DSDominioFirmaRespClausulado iiuIIUDominioFirma(DominioOid oid) {
        return service.iIUDominioFirma(oid);
    }

    @POST
    @Path(value = "/IIU_Dominio_D_SolNovedad")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public DSDominioSolNovedad iiuIIUDominioDSolNovedad(DominioOid oid) {
        return service.iIUDominioDSolNovedad(oid);
    }

    @POST
    @Path(value = "/IIU_Dominio_M_GrupoAsesores")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public DSDominioMGrupoAsesores iiuIIUDominioMGrupoAsesores(DominioOid oid) {
        return service.iIUDominioMGrupoAsesores(oid);
    }

    @POST
    @Path(value = "/IIU_Dominio_M_GrupoAreaMedica")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public DSDominioMGrupoAreaMedica iiuIIUDominioMGrupoAreaMedica(DominioOid oid) {
        return service.iIUDominioMGrupoAreaMedica(oid);
    }

    @POST
    @Path(value = "/IIU_Dominio_M_GruposUsuarios")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public DSDominioMGruposUsuarios iiuIIUDominioMGruposUsuarios(DominioOid oid) {
        return service.iIUDominioMGruposUsuarios(oid);
    }

    @POST
    @Path(value = "/IIU_Dominio_M_Incompatibles")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public DSDominioMLog iiuIIUDominioMIncompatibles(DominioOid oid) {
        return service.iIUDominioMIncompatibles(oid);
    }

    @POST
    @Path(value = "/IIU_Dominio_M_LogDatos")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public DSDominioMLog iiuIIUDominioMLogDatos(DominioOid oid) {
        return service.iIUDominioMLogDatos(oid);
    }

    @POST
    @Path(value = "/PIU_Dominio")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUDominioResponse piuPIUDominio(PIUDominioRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUDominioResponse response;
        try
        {
            response = new PIUDominioResponse(service.piuPIUDominio(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUDominio(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUDominioResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_DominiosPorAsesor")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUDominiosPorAsesorResponse piuPIUDominiosPorAsesor(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUDominiosPorAsesorResponse response;
        try
        {
            response = new PIUDominiosPorAsesorResponse(service.piuPIUDominiosPorAsesor(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUDominiosPorAsesor(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUDominiosPorAsesorResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_Dominio_D_Incompatibles")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUDominioDIncompatiblesResponse piuPIUDominioDIncompatibles(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUDominioDIncompatiblesResponse response;
        try
        {
            response = new PIUDominioDIncompatiblesResponse(service.piuPIUDominioDIncompatibles(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUDominioDIncompatibles(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUDominioDIncompatiblesResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_Dominio_Obs")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUDominioObsResponse piuPIUDominioObs(PIUDominioObsRequest request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUDominioObsResponse response;
        try
        {
            response = new PIUDominioObsResponse(service.piuPIUDominioObs(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUDominioObs(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUDominioObsResponse();
            response.setException(e);
        }
        return response;
    }


    @GET
    @Path("buildRelatedInstance/{relatedInstanceJsonOid}/{relatedInstancePath}")
    @Operation(hidden = true)
    public AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePath) {
        return service.buildRelatedInstance(relatedInstanceJsonOid, relatedInstancePath);
    }
}
