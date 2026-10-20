package com.keralty.aeconfiguraciones.api.paramreporte;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.ParamReporteOid;
import com.keralty.aeconfiguraciones.persistence.ParamReporte;
import com.keralty.aeconfiguraciones.service.ParamReporteService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.paramreporte.piu.PIUDParametrosResponse;
import com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.ModificarRequest;
import com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TBAJARRequest;
import com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TDELETERequest;
import com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TNEWRequest;
import com.keralty.aeconfiguraciones.viewmodel.paramreporte.siu.TSUBIRRequest;
import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
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

@Path("/api/ParamReporteApi")
@RequestScoped
public class ParamReporteApi {

    ParamReporteService service;

    public ParamReporteApi(ParamReporteService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{paramReporteidParamReporte}")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ParamReporte getInstance(Long paramReporteidParamReporte) {
    	return service.getById(paramReporteidParamReporte);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<ParamReporte> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ParamReporte getByOid(ParamReporteOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public ParamReporte getByOIDWithHV(ParamReporteOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetParamReporteDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getParamReporteDynamic(QueryRequest request) {
        ParamReporteOid oid = new ParamReporteOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getParamReporteDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getParamReportePopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/modificar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse paramReporteModificar(ModificarRequest request) {
        return service.modificar(request);
    }
    

    @POST
    @Path(value = "/TNEW")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse paramReporteTNEW(TNEWRequest request) {
        return service.tNEW(request);
    }
    

    @POST
    @Path(value = "/TDELETE")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse paramReporteTDELETE(TDELETERequest request) {
        return service.tDELETE(request);
    }
    

    @POST
    @Path(value = "/TSUBIR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse paramReporteTSUBIR(TSUBIRRequest request) {
        return service.tSUBIR(request);
    }
    

    @POST
    @Path(value = "/TBAJAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse paramReporteTBAJAR(TBAJARRequest request) {
        return service.tBAJAR(request);
    }
    


    @POST
    @Path(value = "/PIU_D_Parametros")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUDParametrosResponse piuPIUDParametros(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUDParametrosResponse response;
        try
        {
            response = new PIUDParametrosResponse(service.piuPIUDParametros(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUDParametros(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUDParametrosResponse();
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
