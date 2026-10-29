package com.keralty.aeconfiguraciones.api.parentesco;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.ParentescoOid;
import com.keralty.aeconfiguraciones.persistence.Parentesco;
import com.keralty.aeconfiguraciones.service.ParentescoService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.parentesco.piu.PIUParentescoResponse;
import com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.DelParentescoNuevoUsuNovedadRequest;
import com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.DelParentescoUsuNovedadRequest;
import com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.InsParentescoNuevoUsuNovedadRequest;
import com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.InsParentescoUsuNovedadRequest;
import com.keralty.aeconfiguraciones.viewmodel.parentesco.siu.ModificarRequest;
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

@Path("/api/ParentescoApi")
@RequestScoped
public class ParentescoApi {

    ParentescoService service;

    public ParentescoApi(ParentescoService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{parentescoCodigo}")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public Parentesco getInstance(Long parentescoCodigo) {
    	return service.getById(parentescoCodigo);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<Parentesco> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<PreloadViewModel> getParentescoPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public Parentesco getByOid(ParentescoOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public Parentesco getByOIDWithHV(ParentescoOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetParentescoDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getParentescoDynamic(QueryRequest request) {
        ParentescoOid oid = new ParentescoOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getParentescoDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PreloadResponseViewModel getParentescoWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getParentescoPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse parentescoCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse parentescoEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/eliminarCheckPreconditions")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse parentescoEliminarCheckPreconditions(EliminarRequest request) {

        return service.eliminarCheckPreconditions(request);
    }

    @POST
    @Path(value = "/modificar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse parentescoModificar(ModificarRequest request) {
        return service.modificar(request);
    }
    

    @POST
    @Path(value = "/InsParentescoUsuNovedad")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse parentescoInsParentescoUsuNovedad(InsParentescoUsuNovedadRequest request) {
        return service.insParentescoUsuNovedad(request);
    }
    

    @POST
    @Path(value = "/DelParentescoUsuNovedad")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse parentescoDelParentescoUsuNovedad(DelParentescoUsuNovedadRequest request) {
        return service.delParentescoUsuNovedad(request);
    }
    

    @POST
    @Path(value = "/InsParentescoNuevoUsuNovedad")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse parentescoInsParentescoNuevoUsuNovedad(InsParentescoNuevoUsuNovedadRequest request) {
        return service.insParentescoNuevoUsuNovedad(request);
    }
    

    @POST
    @Path(value = "/DelParentescoNuevoUsuNovedad")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse parentescoDelParentescoNuevoUsuNovedad(DelParentescoNuevoUsuNovedadRequest request) {
        return service.delParentescoNuevoUsuNovedad(request);
    }
    


    @POST
    @Path(value = "/PIU_Parentesco")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUParentescoResponse piuPIUParentesco(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUParentescoResponse response;
        try
        {
            response = new PIUParentescoResponse(service.piuPIUParentesco(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUParentesco(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUParentescoResponse();
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
