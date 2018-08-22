package xyz.solovev.enterprise.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.solovev.enterprise.dao.CategoriesDAO;
import xyz.solovev.enterprise.dto.CategoriesDTO;
import xyz.solovev.enterprise.dto.ProductsDTO;
import xyz.solovev.enterprise.dto.ResultDTO;
import xyz.solovev.enterprise.entity.Categories;
import xyz.solovev.enterprise.entity.Products;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebService
@Path("/categories")
public class CategoriesService {

    @Inject
    private CategoriesDAO categoriesDAO;

    @GET
    @NotNull
    @Path("/createCategory")
    @WebMethod(operationName = "createCategory")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO createCategory() {
        categoriesDAO.persists(new Categories());
        return new ResultDTO();
    }

    @GET
    @NotNull
    @Path("removeCategoryById")
    @WebMethod(operationName = "removeCategoryById")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO removeProductById(
            @QueryParam(value = "id")
            @WebParam(name = "id", partName = "id")
            @Nullable final String id
    ) {
        try {
            categoriesDAO.removeById(id);
            return new ResultDTO();
        } catch (Exception e) {
            return new ResultDTO(false, e.getMessage());
        }
    }

    @GET
    @NotNull
    @Path("/getAllCategories")
    @WebMethod(operationName = "getAllCategories")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoriesDTO> getListProducts() {
        List<Categories> list = categoriesDAO.getAll();
        if (list == null) return new ArrayList<>(Collections.singletonList(new CategoriesDTO()));
        return list.stream().map(CategoriesDTO::new).collect(Collectors.toList());
    }
}
