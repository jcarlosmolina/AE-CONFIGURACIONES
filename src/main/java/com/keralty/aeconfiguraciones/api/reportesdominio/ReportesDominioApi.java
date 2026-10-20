package com.keralty.aeconfiguraciones.api.reportesdominio;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.ReportesDominioOid;
import com.keralty.aeconfiguraciones.persistence.ReportesDominio;
import com.keralty.aeconfiguraciones.service.ReportesDominioService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationRequestViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PopulationResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.PreloadViewModel;
import com.keralty.aeconfiguraciones.viewmodel.QueryRequest;
import com.keralty.aeconfiguraciones.viewmodel.reportesdominio.piu.PIUReportesDominioResponse;
import com.keralty.aeconfiguraciones.viewmodel.reportesdominio.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.reportesdominio.siu.ModificarRequest;
import com.keralty.aeconfiguraciones.viewmodel.ServiceResponse;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import java.util.List;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.Operation;

@Path("/api/ReportesDominioApi")
@RequestScoped
public class ReportesDominioApi {

    ReportesDominioService service;

    public ReportesDominioApi(ReportesDominioService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{dominioNumero}/{plantillaReporteidPlantillaReporte}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public ReportesDominio getInstance(Long dominioNumero, Long plantillaReporteidPlantillaReporte) {
    	return service.getById(dominioNumero, plantillaReporteidPlantillaReporte);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public List<ReportesDominio> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public List<PreloadViewModel> getReportesDominioPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public ReportesDominio getByOid(ReportesDominioOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public ReportesDominio getByOIDWithHV(ReportesDominioOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetReportesDominioDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getReportesDominioDynamic(QueryRequest request) {
        ReportesDominioOid oid = new ReportesDominioOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getReportesDominioDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PreloadResponseViewModel getReportesDominioWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getReportesDominioPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse reportesDominioEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/modificar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse reportesDominioModificar(ModificarRequest request) {
        return service.modificar(request);
    }
    


    @POST
    @Path(value = "/PIU_ReportesDominio")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PIUReportesDominioResponse piuPIUReportesDominio(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUReportesDominioResponse response;
        try
        {
            response = new PIUReportesDominioResponse(service.piuPIUReportesDominio(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUReportesDominio(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUReportesDominioResponse();
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
