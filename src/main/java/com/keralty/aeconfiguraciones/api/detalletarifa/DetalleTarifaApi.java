package com.keralty.aeconfiguraciones.api.detalletarifa;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.DetalleTarifa;
import com.keralty.aeconfiguraciones.persistence.oid.DetalleTarifaOid;
import com.keralty.aeconfiguraciones.service.DetalleTarifaService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.detalletarifa.piu.PIUDetallesTarifaResponse;
import com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.TCREARRequest;
import com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.TELIMINARRequest;
import com.keralty.aeconfiguraciones.viewmodel.detalletarifa.siu.TMODIFICARRequest;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
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

@Path("/api/DetalleTarifaApi")
@RequestScoped
public class DetalleTarifaApi {

    DetalleTarifaService service;

    public DetalleTarifaApi(DetalleTarifaService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{detalleTarifaidDetalleTarifa}")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public DetalleTarifa getInstance(Long detalleTarifaidDetalleTarifa) {
    	return service.getById(detalleTarifaidDetalleTarifa);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<DetalleTarifa> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public DetalleTarifa getByOid(DetalleTarifaOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public DetalleTarifa getByOIDWithHV(DetalleTarifaOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetDetalleTarifaDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getDetalleTarifaDynamic(QueryRequest request) {
        DetalleTarifaOid oid = new DetalleTarifaOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getDetalleTarifaDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getDetalleTarifaPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/TCREAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse detalleTarifaTCREAR(TCREARRequest request) {
        return service.tCREAR(request);
    }
    

    @POST
    @Path(value = "/TMODIFICAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse detalleTarifaTMODIFICAR(TMODIFICARRequest request) {
        return service.tMODIFICAR(request);
    }
    

    @POST
    @Path(value = "/TELIMINAR")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse detalleTarifaTELIMINAR(TELIMINARRequest request) {
        return service.tELIMINAR(request);
    }
    


    @POST
    @Path(value = "/PIU_DetallesTarifa")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUDetallesTarifaResponse piuPIUDetallesTarifa(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUDetallesTarifaResponse response;
        try
        {
            response = new PIUDetallesTarifaResponse(service.piuPIUDetallesTarifa(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUDetallesTarifa(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUDetallesTarifaResponse();
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
