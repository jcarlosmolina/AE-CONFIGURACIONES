package com.keralty.aeconfiguraciones.api.cuestionariomed;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.CuestionarioMed;
import com.keralty.aeconfiguraciones.persistence.oid.CuestionarioMedOid;
import com.keralty.aeconfiguraciones.service.CuestionarioMedService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.ds.DSCuestionarioMedIIU;
import com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.piu.PIUCuestionarioMedResponse;
import com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.cuestionariomed.siu.EliminarRequest;
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

@Path("/api/CuestionarioMedApi")
@RequestScoped
public class CuestionarioMedApi {

    CuestionarioMedService service;

    public CuestionarioMedApi(CuestionarioMedService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{cuestionarioMedNumero}")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public CuestionarioMed getInstance(Long cuestionarioMedNumero) {
    	return service.getById(cuestionarioMedNumero);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<CuestionarioMed> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<PreloadViewModel> getCuestionarioMedPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public CuestionarioMed getByOid(CuestionarioMedOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public CuestionarioMed getByOIDWithHV(CuestionarioMedOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetCuestionarioMedDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getCuestionarioMedDynamic(QueryRequest request) {
        CuestionarioMedOid oid = new CuestionarioMedOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getCuestionarioMedDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PreloadResponseViewModel getCuestionarioMedWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getCuestionarioMedPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse cuestionarioMedCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse cuestionarioMedEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/IIU_CuestionarioMed")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public DSCuestionarioMedIIU iiuIIUCuestionarioMed(CuestionarioMedOid oid) {
        return service.iIUCuestionarioMed(oid);
    }

    @POST
    @Path(value = "/PIU_CuestionarioMed")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUCuestionarioMedResponse piuPIUCuestionarioMed(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUCuestionarioMedResponse response;
        try
        {
            response = new PIUCuestionarioMedResponse(service.piuPIUCuestionarioMed(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUCuestionarioMed(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUCuestionarioMedResponse();
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
