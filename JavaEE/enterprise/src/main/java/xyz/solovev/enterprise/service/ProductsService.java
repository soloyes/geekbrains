package xyz.solovev.enterprise.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.solovev.enterprise.dao.CategoriesDAO;
import xyz.solovev.enterprise.dao.ProductsDAO;
import xyz.solovev.enterprise.dto.ProductsDTO;
import xyz.solovev.enterprise.dto.ResultDTO;
import xyz.solovev.enterprise.entity.Products;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@WebService
@Path("/products")
public class ProductsService {

    @Inject
    private ProductsDAO productsDAO;

    @GET
    @NotNull
    @Path("/getAllProducts")
    @WebMethod(operationName = "getAllProducts")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductsDTO> getListProducts() {
        List<Products> list = productsDAO.getAll();
        if (list == null) return new ArrayList<>(Collections.singletonList(new ProductsDTO()));
        return list.stream().map(ProductsDTO::new).collect(Collectors.toList());
    }

    @GET
    @Nullable
    @Path("/getProductById")
    @WebMethod(operationName = "getProductById")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductsDTO getProductById(
            @QueryParam(value = "id")
            @WebParam(name = "id", partName = "id")
            @Nullable final String id
    ) {
        @Nullable final Products product = productsDAO.getById(id);
        if (product == null) return null;
        return new ProductsDTO(product);
    }

    @GET
    @Nullable
    @Path("/getProductsByName")
    @WebMethod(operationName = "getProductsByName")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductsDTO getProductByName(
            @QueryParam(value = "name")
            @WebParam(name = "name", partName = "name")
            @Nullable final String name
    ) {
        @Nullable final Products product = productsDAO.getByName(name);
        if (product == null) return null;
        return new ProductsDTO(product);
    }

    @GET
    @Nullable
    @Path("/getProductsByCategory")
    @WebMethod(operationName = "getProductsByCategory")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductsDTO> getProductsByCategory(
            @QueryParam(value = "category")
            @WebParam(name = "category", partName = "category")
            @Nullable final String category
    ) {
        List<Products> list = productsDAO.getAllByCategory(category);
        if (list == null) return new ArrayList<>(Collections.singletonList(new ProductsDTO()));
        return list.stream().map(ProductsDTO::new).collect(Collectors.toList());
    }

    @GET
    @NotNull
    @Path("/createProduct")
    @WebMethod(operationName = "createProduct")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO createProduct() {
        productsDAO.persists(new Products());
        return new ResultDTO();
    }

    @GET
    @NotNull
    @Path("removeProductById")
    @WebMethod(operationName = "removeProductById")
    @Produces(MediaType.APPLICATION_JSON)
    public ResultDTO removeProductById(
            @QueryParam(value = "id")
            @WebParam(name = "id", partName = "id")
            @Nullable final String id
    ) {
        try {
            productsDAO.removeById(id);
            return new ResultDTO();
        } catch (Exception e) {
            return new ResultDTO(false, e.getMessage());
        }
    }
}
