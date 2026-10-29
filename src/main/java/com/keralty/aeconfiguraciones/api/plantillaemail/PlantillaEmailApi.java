package com.keralty.aeconfiguraciones.api.plantillaemail;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.PlantillaEmailOid;
import com.keralty.aeconfiguraciones.persistence.PlantillaEmail;
import com.keralty.aeconfiguraciones.service.PlantillaEmailService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.plantillaemail.piu.PIUPlantillaEmailResponse;
import com.keralty.aeconfiguraciones.viewmodel.plantillaemail.piu.PIUPlantillasDominioResponse;
import com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu.DummyModificarRequest;
import com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu.ModificarRequest;
import com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu.ModificarTextoMensajeRequest;
import com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu.TNEWRequest;
import com.keralty.aeconfiguraciones.viewmodel.plantillaemail.siu.TNEWResponse;
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

@Path("/api/PlantillaEmailApi")
@RequestScoped
public class PlantillaEmailApi {

    PlantillaEmailService service;

    public PlantillaEmailApi(PlantillaEmailService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{plantillaEmailIdPlantilla}")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PlantillaEmail getInstance(Long plantillaEmailIdPlantilla) {
    	return service.getById(plantillaEmailIdPlantilla);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<PlantillaEmail> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<PreloadViewModel> getPlantillaEmailPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PlantillaEmail getByOid(PlantillaEmailOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PlantillaEmail getByOIDWithHV(PlantillaEmailOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetPlantillaEmailDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getPlantillaEmailDynamic(QueryRequest request) {
        PlantillaEmailOid oid = new PlantillaEmailOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getPlantillaEmailDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PreloadResponseViewModel getPlantillaEmailWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getPlantillaEmailPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse plantillaEmailEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/eliminarCheckPreconditions")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse plantillaEmailEliminarCheckPreconditions(EliminarRequest request) {

        return service.eliminarCheckPreconditions(request);
    }

    @POST
    @Path(value = "/modificar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse plantillaEmailModificar(ModificarRequest request) {
        return service.modificar(request);
    }
    

    @POST
    @Path(value = "/modificarTextoMensaje")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse plantillaEmailModificarTextoMensaje(ModificarTextoMensajeRequest request) {
        return service.modificarTextoMensaje(request);
    }
    

    @POST
    @Path(value = "/dummyModificar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse plantillaEmailDummyModificar(DummyModificarRequest request) {
        return service.dummyModificar(request);
    }
    

    @POST
    @Path(value = "/TNEW")
    @RolesAllowed({"SuperAdmin"})
    public TNEWResponse plantillaEmailTNEW(TNEWRequest request) {
        return service.tNEW(request);
    }
    


    @POST
    @Path(value = "/PIU_PlantillaEmail")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUPlantillaEmailResponse piuPIUPlantillaEmail(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUPlantillaEmailResponse response;
        try
        {
            response = new PIUPlantillaEmailResponse(service.piuPIUPlantillaEmail(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUPlantillaEmail(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUPlantillaEmailResponse();
            response.setException(e);
        }
        return response;
    }

    @POST
    @Path(value = "/PIU_PlantillasDominio")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUPlantillasDominioResponse piuPIUPlantillasDominio(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUPlantillasDominioResponse response;
        try
        {
            response = new PIUPlantillasDominioResponse(service.piuPIUPlantillasDominio(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUPlantillasDominio(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUPlantillasDominioResponse();
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
