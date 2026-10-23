package com.keralty.aeconfiguraciones.api.globalservices;

import com.keralty.aeconfiguraciones.service.GlobalServicesService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCEPSSRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCGRUPOSASOCIADOSRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCMUNICIPIOSRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCOMPANIASRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCRONIZARPARAMETRICASRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.OGSINCSUCURSALESRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TDELCOMBORequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TdelcontsindocsolRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGADDPLANTREPRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARCOMBORequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARCONTEXENTORequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARFINIEPSRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGCREARTARIFARequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGDELCONTRATOEXENTORequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGEDITARCOMBORequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGEDITPLANTREPRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGELIMINARTARIFARequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGFIJARDATOSSINCRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGMODFINIEPSRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1GRUPOASOCRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINC1MUNICIPIORequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCDEPARTAMENTOSRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCENTIDADESFINRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSBENEFICIARIORequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSCIVILESRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSCONTRATORequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCESTADOSPERSONARequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCFORMASPAGORequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPAISESRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPARENTESCOSRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPERIODICIDADESRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPLANESRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCPRODUCTOSRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCREGIONALESRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSDEENTIDADRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSDIRECCIONRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSIDENTIFICACIONRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSINSTITUCIONRequest;
import com.keralty.aeconfiguraciones.viewmodel.globalservices.siu.TGSINCTIPOSSUCURSALRequest;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.keralty.aeconfiguraciones.service.GlobalServicesItSelf;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/GlobalServicesApi")
@RequestScoped
public class GlobalServicesApi {

    GlobalServicesService service;

    @RestClient
    GlobalServicesItSelf globalServicesItSelf;

    public GlobalServicesApi(GlobalServicesService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @POST
    @Path(value = "/TGSINCPRODUCTOS")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCPRODUCTOS(TGSINCPRODUCTOSRequest request) {
        return service.tGSINCPRODUCTOS(request);
    }

    @POST
    @Path(value = "/TGSINCPLANES")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCPLANES(TGSINCPLANESRequest request) {
        return service.tGSINCPLANES(request);
    }

    @POST
    @Path(value = "/TGSINCPARENTESCOS")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCPARENTESCOS(TGSINCPARENTESCOSRequest request) {
        return service.tGSINCPARENTESCOS(request);
    }

    @POST
    @Path(value = "/TGSINCPERIODICIDADES")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCPERIODICIDADES(TGSINCPERIODICIDADESRequest request) {
        return service.tGSINCPERIODICIDADES(request);
    }

    @POST
    @Path(value = "/TGSINCESTADOSCONTRATO")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCESTADOSCONTRATO(TGSINCESTADOSCONTRATORequest request) {
        return service.tGSINCESTADOSCONTRATO(request);
    }

    @POST
    @Path(value = "/TGSINCESTADOSBENEFICIARIO")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCESTADOSBENEFICIARIO(TGSINCESTADOSBENEFICIARIORequest request) {
        return service.tGSINCESTADOSBENEFICIARIO(request);
    }

    @POST
    @Path(value = "/TGSINCREGIONALES")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCREGIONALES(TGSINCREGIONALESRequest request) {
        return service.tGSINCREGIONALES(request);
    }

    @POST
    @Path(value = "/OGSINCSUCURSALES")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse oGSINCSUCURSALES(OGSINCSUCURSALESRequest request) {
        return service.oGSINCSUCURSALES(request);
    }

    @POST
    @Path(value = "/TGSINC1GRUPOASOC")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse tGSINC1GRUPOASOC(TGSINC1GRUPOASOCRequest request) {
        return service.tGSINC1GRUPOASOC(request);
    }

    @POST
    @Path(value = "/OGSINCGRUPOSASOCIADOS")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse oGSINCGRUPOSASOCIADOS(OGSINCGRUPOSASOCIADOSRequest request) {
        return service.oGSINCGRUPOSASOCIADOS(request);
    }

    @POST
    @Path(value = "/TGSINCESTADOSCIVILES")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCESTADOSCIVILES(TGSINCESTADOSCIVILESRequest request) {
        return service.tGSINCESTADOSCIVILES(request);
    }

    @POST
    @Path(value = "/TGSINCDEPARTAMENTOS")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCDEPARTAMENTOS(TGSINCDEPARTAMENTOSRequest request) {
        return service.tGSINCDEPARTAMENTOS(request);
    }

    @POST
    @Path(value = "/OGSINCOMPANIAS")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse oGSINCOMPANIAS(OGSINCOMPANIASRequest request) {
        return service.oGSINCOMPANIAS(request);
    }

    @POST
    @Path(value = "/TGSINC1MUNICIPIO")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse tGSINC1MUNICIPIO(TGSINC1MUNICIPIORequest request) {
        return service.tGSINC1MUNICIPIO(request);
    }

    @POST
    @Path(value = "/OGSINCMUNICIPIOS")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse oGSINCMUNICIPIOS(OGSINCMUNICIPIOSRequest request) {
        return service.oGSINCMUNICIPIOS(request);
    }

    @POST
    @Path(value = "/TGSINCTIPOSDIRECCION")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCTIPOSDIRECCION(TGSINCTIPOSDIRECCIONRequest request) {
        return service.tGSINCTIPOSDIRECCION(request);
    }

    @POST
    @Path(value = "/TGSINCESTADOSPERSONA")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCESTADOSPERSONA(TGSINCESTADOSPERSONARequest request) {
        return service.tGSINCESTADOSPERSONA(request);
    }

    @POST
    @Path(value = "/TGSINCFORMASPAGO")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCFORMASPAGO(TGSINCFORMASPAGORequest request) {
        return service.tGSINCFORMASPAGO(request);
    }

    @POST
    @Path(value = "/TGSINCTIPOSIDENTIFICACION")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCTIPOSIDENTIFICACION(TGSINCTIPOSIDENTIFICACIONRequest request) {
        return service.tGSINCTIPOSIDENTIFICACION(request);
    }

    @POST
    @Path(value = "/OGSINCEPSS")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse oGSINCEPSS(OGSINCEPSSRequest request) {
        return service.oGSINCEPSS(request);
    }

    @POST
    @Path(value = "/TGSINCPAISES")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCPAISES(TGSINCPAISESRequest request) {
        return service.tGSINCPAISES(request);
    }

    @POST
    @Path(value = "/TGSINCENTIDADESFIN")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCENTIDADESFIN(TGSINCENTIDADESFINRequest request) {
        return service.tGSINCENTIDADESFIN(request);
    }

    @POST
    @Path(value = "/TGSINCTIPOSDEENTIDAD")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCTIPOSDEENTIDAD(TGSINCTIPOSDEENTIDADRequest request) {
        return service.tGSINCTIPOSDEENTIDAD(request);
    }

    @POST
    @Path(value = "/TGSINCTIPOSINSTITUCION")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCTIPOSINSTITUCION(TGSINCTIPOSINSTITUCIONRequest request) {
        return service.tGSINCTIPOSINSTITUCION(request);
    }

    @POST
    @Path(value = "/TGSINCTIPOSSUCURSAL")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGSINCTIPOSSUCURSAL(TGSINCTIPOSSUCURSALRequest request) {
        return service.tGSINCTIPOSSUCURSAL(request);
    }

    @POST
    @Path(value = "/TGCREARTARIFA")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGCREARTARIFA(TGCREARTARIFARequest request) {
        return service.tGCREARTARIFA(request);
    }

    @POST
    @Path(value = "/TGELIMINARTARIFA")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGELIMINARTARIFA(TGELIMINARTARIFARequest request) {
        return service.tGELIMINARTARIFA(request);
    }

    @POST
    @Path(value = "/tdelcontsindocsol")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tdelcontsindocsol(TdelcontsindocsolRequest request) {
        return service.tdelcontsindocsol(request);
    }

    @POST
    @Path(value = "/TGCREARCOMBO")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGCREARCOMBO(TGCREARCOMBORequest request) {
        return service.tGCREARCOMBO(request);
    }

    @POST
    @Path(value = "/TGCREARCOMBOCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGCREARCOMBOCheckPreconditions(TGCREARCOMBORequest request) {
        return service.tGCREARCOMBOCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TGEDITARCOMBO")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGEDITARCOMBO(TGEDITARCOMBORequest request) {
        return service.tGEDITARCOMBO(request);
    }

    @POST
    @Path(value = "/TGEDITARCOMBOCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGEDITARCOMBOCheckPreconditions(TGEDITARCOMBORequest request) {
        return service.tGEDITARCOMBOCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TDELCOMBO")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tDELCOMBO(TDELCOMBORequest request) {
        return service.tDELCOMBO(request);
    }

    @POST
    @Path(value = "/TGADDPLANTREP")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGADDPLANTREP(TGADDPLANTREPRequest request) {
        return service.tGADDPLANTREP(request);
    }

    @POST
    @Path(value = "/TGADDPLANTREPCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGADDPLANTREPCheckPreconditions(TGADDPLANTREPRequest request) {
        return service.tGADDPLANTREPCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TGEDITPLANTREP")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGEDITPLANTREP(TGEDITPLANTREPRequest request) {
        return service.tGEDITPLANTREP(request);
    }

    @POST
    @Path(value = "/TGEDITPLANTREPCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGEDITPLANTREPCheckPreconditions(TGEDITPLANTREPRequest request) {
        return service.tGEDITPLANTREPCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TGCREARFINIEPS")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGCREARFINIEPS(TGCREARFINIEPSRequest request) {
        return service.tGCREARFINIEPS(request);
    }

    @POST
    @Path(value = "/TGCREARFINIEPSCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGCREARFINIEPSCheckPreconditions(TGCREARFINIEPSRequest request) {
        return service.tGCREARFINIEPSCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TGMODFINIEPS")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGMODFINIEPS(TGMODFINIEPSRequest request) {
        return service.tGMODFINIEPS(request);
    }

    @POST
    @Path(value = "/TGMODFINIEPSCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse tGMODFINIEPSCheckPreconditions(TGMODFINIEPSRequest request) {
        return service.tGMODFINIEPSCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TGCREARCONTEXENTO")
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public ServiceResponse tGCREARCONTEXENTO(TGCREARCONTEXENTORequest request) {
        return service.tGCREARCONTEXENTO(request);
    }

    @POST
    @Path(value = "/TGCREARCONTEXENTOCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public ServiceResponse tGCREARCONTEXENTOCheckPreconditions(TGCREARCONTEXENTORequest request) {
        return service.tGCREARCONTEXENTOCheckPreconditions(request);
    }

    @POST
    @Path(value = "/TGDELCONTRATOEXENTO")
    @RolesAllowed({"Admin","SuperAdmin","MesaDeApoyo"})
    public ServiceResponse tGDELCONTRATOEXENTO(TGDELCONTRATOEXENTORequest request) {
        return service.tGDELCONTRATOEXENTO(request);
    }

    @POST
    @Path(value = "/OGSINCRONIZARPARAMETRICAS")
    @RolesAllowed({"SuperAdmin","UsuarioSistemaExterno"})
    public ServiceResponse oGSINCRONIZARPARAMETRICAS(OGSINCRONIZARPARAMETRICASRequest request) {
    	// Llama a un REST que es el mismo, pero sin esperar respuesta
    	globalServicesItSelf.oGSINCRONIZARPARAMETRICAS_Async(request);
		// Devolvemos Ok 
    	return new ServiceResponse();
    }

    @POST
    @Path(value = "/OGSINCRONIZARPARAMETRICAS_Async")
    @RolesAllowed({"SuperAdmin","UsuarioSistemaExterno"})
    public ServiceResponse oGSINCRONIZARPARAMETRICAS_Async(OGSINCRONIZARPARAMETRICASRequest request) {
        return service.oGSINCRONIZARPARAMETRICAS(request);
    }

    @POST
    @Path(value = "/TGFIJARDATOSSINC")
    public ServiceResponse tGFIJARDATOSSINC(TGFIJARDATOSSINCRequest request) {
        return service.tGFIJARDATOSSINC(request);
    }
}
