package com.keralty.aeconfiguraciones.api.configuracionfestivos;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.ConfiguracionFestivos;
import com.keralty.aeconfiguraciones.persistence.oid.ConfiguracionFestivosOid;
import com.keralty.aeconfiguraciones.service.ConfiguracionFestivosService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.configuracionfestivos.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.configuracionfestivos.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.InstanceResponseViewModel;
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

@Path("/api/ConfiguracionFestivosApi")
@RequestScoped
public class ConfiguracionFestivosApi {

    ConfiguracionFestivosService service;

    public ConfiguracionFestivosApi(ConfiguracionFestivosService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{configuracionFestivosId}")
    @Operation(hidden = true)
    @RolesAllowed({"SuperAdmin","Gestor"})
    public ConfiguracionFestivos getInstance(Long configuracionFestivosId) {
    	return service.getById(configuracionFestivosId);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"SuperAdmin","Gestor"})
    public List<ConfiguracionFestivos> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"SuperAdmin","Gestor"})
    public ConfiguracionFestivos getByOid(ConfiguracionFestivosOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"SuperAdmin","Gestor"})
    public ConfiguracionFestivos getByOIDWithHV(ConfiguracionFestivosOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetConfiguracionFestivosDynamic")
    @RolesAllowed({"SuperAdmin","Gestor"})
    public InstanceResponseViewModel getConfiguracionFestivosDynamic(QueryRequest request) {
        ConfiguracionFestivosOid oid = new ConfiguracionFestivosOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getConfiguracionFestivosDynamic(oid, request.getDisplaySetItems());
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"SuperAdmin","Gestor"})
    public PopulationResponseViewModel getConfiguracionFestivosPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse configuracionFestivosCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse configuracionFestivosEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    




    @GET
    @Path("buildRelatedInstance/{relatedInstanceJsonOid}/{relatedInstancePath}")
    @Operation(hidden = true)
    public AbstractAppEntity buildRelatedInstance(String relatedInstanceJsonOid, String relatedInstancePath) {
        return service.buildRelatedInstance(relatedInstanceJsonOid, relatedInstancePath);
    }
}
