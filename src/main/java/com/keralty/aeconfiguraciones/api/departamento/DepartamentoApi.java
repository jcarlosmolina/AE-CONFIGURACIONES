package com.keralty.aeconfiguraciones.api.departamento;

import com.keralty.aeconfiguraciones.persistence.AbstractAppEntity;
import com.keralty.aeconfiguraciones.persistence.Departamento;
import com.keralty.aeconfiguraciones.persistence.oid.DepartamentoOid;
import com.keralty.aeconfiguraciones.service.DepartamentoService;
import com.keralty.aeconfiguraciones.utils.TokenUtilities;
import com.keralty.aeconfiguraciones.viewmodel.departamento.piu.PIUDepartamentoResponse;
import com.keralty.aeconfiguraciones.viewmodel.departamento.siu.CrearRequest;
import com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoCNacRequest;
import com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoCRequest;
import com.keralty.aeconfiguraciones.viewmodel.departamento.siu.DelDepartamentoDirSucursalCRequest;
import com.keralty.aeconfiguraciones.viewmodel.departamento.siu.EliminarRequest;
import com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoCNacRequest;
import com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoCRequest;
import com.keralty.aeconfiguraciones.viewmodel.departamento.siu.InsDepartamentoDirSucursalCRequest;
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

@Path("/api/DepartamentoApi")
@RequestScoped
public class DepartamentoApi {

    DepartamentoService service;

    public DepartamentoApi(DepartamentoService service, JsonWebToken jwt) {
        this.service = service;
        TokenUtilities.getInstance().setJsonWebToken(jwt);
    }

    @GET
    @Path(value="/{departamentoCodigo}")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public Departamento getInstance(Long departamentoCodigo) {
    	return service.getById(departamentoCodigo);
    }

    @GET
    @Path(value="/")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<Departamento> getPopulation(QueryRequest queryRequest){
    	return service.get(queryRequest);
    }

    @POST
    @Path(value = "/GetPreload")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public List<PreloadViewModel> getDepartamentoPreload(QueryRequest queryRequest) {
        return service.getPreload(queryRequest);
    }

    @GET
    @Path(value="/getByOid")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public Departamento getByOid(DepartamentoOid oid) {
    	return service.getByOID(oid);
    }

    @GET
    @Path(value="/getByOIDWithHV")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public Departamento getByOIDWithHV(DepartamentoOid oid) {
    	return service.getByOIDWithHV(oid);
    }
    
    @POST
    @Path(value = "/GetDepartamentoDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public InstanceResponseViewModel getDepartamentoDynamic(QueryRequest request) {
        DepartamentoOid oid = new DepartamentoOid();
        oid.fromJsonString(request.getJsonOID());
        return service.getDepartamentoDynamic(oid, request.getDisplaySetItems());
    }

    @POST
    @Path(value = "/GetWithDisplaySetDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PreloadResponseViewModel getDepartamentoWithDisplaySetDynamic(QueryRequest request) {
        return (PreloadResponseViewModel) service.getDynamic(request);
    }
    
    @POST
    @Path(value = "/GetPopulationDynamic")
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PopulationResponseViewModel getDepartamentoPopulationDynamic(QueryRequest request) {
        return (PopulationResponseViewModel) service.getDynamic(request);
    }

    @POST
    @Path(value = "/crear")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse departamentoCrear(CrearRequest request) {
        return service.crear(request);
    }
    

    @POST
    @Path(value = "/eliminar")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse departamentoEliminar(EliminarRequest request) {
        return service.eliminar(request);
    }
    

    @POST
    @Path(value = "/eliminarCheckPreconditions")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse departamentoEliminarCheckPreconditions(EliminarRequest request) {

        return service.eliminarCheckPreconditions(request);
    }

    @POST
    @Path(value = "/InsDepartamentoC")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse departamentoInsDepartamentoC(InsDepartamentoCRequest request) {
        return service.insDepartamentoC(request);
    }
    

    @POST
    @Path(value = "/DelDepartamentoC")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse departamentoDelDepartamentoC(DelDepartamentoCRequest request) {
        return service.delDepartamentoC(request);
    }
    

    @POST
    @Path(value = "/InsDepartamentoCNac")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse departamentoInsDepartamentoCNac(InsDepartamentoCNacRequest request) {
        return service.insDepartamentoCNac(request);
    }
    

    @POST
    @Path(value = "/DelDepartamentoCNac")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse departamentoDelDepartamentoCNac(DelDepartamentoCNacRequest request) {
        return service.delDepartamentoCNac(request);
    }
    

    @POST
    @Path(value = "/InsDepartamentoDirSucursalC")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse departamentoInsDepartamentoDirSucursalC(InsDepartamentoDirSucursalCRequest request) {
        return service.insDepartamentoDirSucursalC(request);
    }
    

    @POST
    @Path(value = "/DelDepartamentoDirSucursalC")
    @RolesAllowed({"SuperAdmin"})
    public ServiceResponse departamentoDelDepartamentoDirSucursalC(DelDepartamentoDirSucursalCRequest request) {
        return service.delDepartamentoDirSucursalC(request);
    }
    


    @POST
    @Path(value = "/PIU_Departamento")
    @Operation(hidden = true)
    @RolesAllowed({"Funcionario","Admin","SuperAdmin","Asesor","AreaMedica","Afiliaciones","RespRegional","Gestor","MesaDeApoyo"})
    public PIUDepartamentoResponse piuPIUDepartamento(PopulationRequestViewModel request) {
        QueryRequest queryRequest = request.buildQueryRequest();
        PIUDepartamentoResponse response;
        try
        {
            response = new PIUDepartamentoResponse(service.piuPIUDepartamento(queryRequest));
            if (queryRequest.getPageSize() > 0) {
                queryRequest.setPageSize(0);
                response.setTotalItems(service.getCount4PIUDepartamento(queryRequest));
            }
        } catch (Exception e) {
            org.slf4j.LoggerFactory.getLogger(this.getClass()).error(e.getMessage());
            response = new PIUDepartamentoResponse();
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
