package com.keralty.aeconfiguraciones.api.parentescodom;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.oid.ParentescoDomOid;
import com.keralty.aeconfiguraciones.persistence.ParentescoDom;
import com.keralty.aeconfiguraciones.service.ParentescoDomService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
import com.keralty.aeconfiguraciones.viewmodel.parentescodom.piu.PIUParentescoDomResponse;
import com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.parentescodom.siu.ModificarRequest;
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

@Path("/api/ParentescoDomApi")
@RequestScoped
public class ParentescoDomApi {

    ParentescoDomService service;

    public ParentescoDomApi(ParentescoDomService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{dominioNumero}/{parentescoDomIdentificador}")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public ParentescoDom getInstance(Long dominioNumero, Long parentescoDomIdentificador) {
    	return service.getById(dominioNumero, parentescoDomIdentificador);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public List<ParentescoDom> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public ParentescoDom getByOid(ParentescoDomOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public ParentescoDom getByOIDWithHV(ParentescoDomOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetParentescoDomDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getParentescoDomDynamic(QueryRequest request) {
        ParentescoDomOid oid = new ParentescoDomOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getParentescoDomDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getParentescoDomPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse parentescoDomCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/crearCheckPreconditions")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse parentescoDomCrearCheckPreconditions(CrearRequest request) {

        return service.crearCheckPreconditions(request);
    }

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse parentescoDomEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/modificar")
    @RolesAllowed({"Admin","SuperAdmin"})
    public ServiceResponse parentescoDomModificar(ModificarRequest request) {
        return service.modificar(request);
    }
    


    @POST
    @Path(value = "/PIU_ParentescoDom")
    @Operation(hidden = true)
    @RolesAllowed({"Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","Gestor","MesaDeApoyo"})
    public PIUParentescoDomResponse piuPIUParentescoDom(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUParentescoDomResponse response;
        try
        {
            response = new PIUParentescoDomResponse(service.piuPIUParentescoDom(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUParentescoDom(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUParentescoDomResponse();
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
