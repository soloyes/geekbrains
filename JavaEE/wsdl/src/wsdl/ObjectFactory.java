
package wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetProductById_QNAME = new QName("http://service.enterprise.solovev.xyz/", "getProductById");
    private final static QName _GetProductsByCategoryResponse_QNAME = new QName("http://service.enterprise.solovev.xyz/", "getProductsByCategoryResponse");
    private final static QName _GetProductByIdResponse_QNAME = new QName("http://service.enterprise.solovev.xyz/", "getProductByIdResponse");
    private final static QName _ResultDTO_QNAME = new QName("http://service.enterprise.solovev.xyz/", "resultDTO");
    private final static QName _CreateProduct_QNAME = new QName("http://service.enterprise.solovev.xyz/", "createProduct");
    private final static QName _RemoveProductByIdResponse_QNAME = new QName("http://service.enterprise.solovev.xyz/", "removeProductByIdResponse");
    private final static QName _GetProductsByName_QNAME = new QName("http://service.enterprise.solovev.xyz/", "getProductsByName");
    private final static QName _RemoveProductById_QNAME = new QName("http://service.enterprise.solovev.xyz/", "removeProductById");
    private final static QName _GetAllProductsResponse_QNAME = new QName("http://service.enterprise.solovev.xyz/", "getAllProductsResponse");
    private final static QName _GetProductsByCategory_QNAME = new QName("http://service.enterprise.solovev.xyz/", "getProductsByCategory");
    private final static QName _ProductsDTO_QNAME = new QName("http://service.enterprise.solovev.xyz/", "productsDTO");
    private final static QName _GetProductsByNameResponse_QNAME = new QName("http://service.enterprise.solovev.xyz/", "getProductsByNameResponse");
    private final static QName _CreateProductResponse_QNAME = new QName("http://service.enterprise.solovev.xyz/", "createProductResponse");
    private final static QName _GetAllProducts_QNAME = new QName("http://service.enterprise.solovev.xyz/", "getAllProducts");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RemoveProductById }
     * 
     */
    public RemoveProductById createRemoveProductById() {
        return new RemoveProductById();
    }

    /**
     * Create an instance of {@link GetProductsByName }
     * 
     */
    public GetProductsByName createGetProductsByName() {
        return new GetProductsByName();
    }

    /**
     * Create an instance of {@link RemoveProductByIdResponse }
     * 
     */
    public RemoveProductByIdResponse createRemoveProductByIdResponse() {
        return new RemoveProductByIdResponse();
    }

    /**
     * Create an instance of {@link CreateProduct }
     * 
     */
    public CreateProduct createCreateProduct() {
        return new CreateProduct();
    }

    /**
     * Create an instance of {@link ResultDTO }
     * 
     */
    public ResultDTO createResultDTO() {
        return new ResultDTO();
    }

    /**
     * Create an instance of {@link GetProductByIdResponse }
     * 
     */
    public GetProductByIdResponse createGetProductByIdResponse() {
        return new GetProductByIdResponse();
    }

    /**
     * Create an instance of {@link GetProductById }
     * 
     */
    public GetProductById createGetProductById() {
        return new GetProductById();
    }

    /**
     * Create an instance of {@link GetProductsByCategoryResponse }
     * 
     */
    public GetProductsByCategoryResponse createGetProductsByCategoryResponse() {
        return new GetProductsByCategoryResponse();
    }

    /**
     * Create an instance of {@link GetAllProducts }
     * 
     */
    public GetAllProducts createGetAllProducts() {
        return new GetAllProducts();
    }

    /**
     * Create an instance of {@link CreateProductResponse }
     * 
     */
    public CreateProductResponse createCreateProductResponse() {
        return new CreateProductResponse();
    }

    /**
     * Create an instance of {@link GetProductsByNameResponse }
     * 
     */
    public GetProductsByNameResponse createGetProductsByNameResponse() {
        return new GetProductsByNameResponse();
    }

    /**
     * Create an instance of {@link GetProductsByCategory }
     * 
     */
    public GetProductsByCategory createGetProductsByCategory() {
        return new GetProductsByCategory();
    }

    /**
     * Create an instance of {@link ProductsDTO }
     * 
     */
    public ProductsDTO createProductsDTO() {
        return new ProductsDTO();
    }

    /**
     * Create an instance of {@link GetAllProductsResponse }
     * 
     */
    public GetAllProductsResponse createGetAllProductsResponse() {
        return new GetAllProductsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.enterprise.solovev.xyz/", name = "getProductById")
    public JAXBElement<GetProductById> createGetProductById(GetProductById value) {
        return new JAXBElement<GetProductById>(_GetProductById_QNAME, GetProductById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductsByCategoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.enterprise.solovev.xyz/", name = "getProductsByCategoryResponse")
    public JAXBElement<GetProductsByCategoryResponse> createGetProductsByCategoryResponse(GetProductsByCategoryResponse value) {
        return new JAXBElement<GetProductsByCategoryResponse>(_GetProductsByCategoryResponse_QNAME, GetProductsByCategoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.enterprise.solovev.xyz/", name = "getProductByIdResponse")
    public JAXBElement<GetProductByIdResponse> createGetProductByIdResponse(GetProductByIdResponse value) {
        return new JAXBElement<GetProductByIdResponse>(_GetProductByIdResponse_QNAME, GetProductByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.enterprise.solovev.xyz/", name = "resultDTO")
    public JAXBElement<ResultDTO> createResultDTO(ResultDTO value) {
        return new JAXBElement<ResultDTO>(_ResultDTO_QNAME, ResultDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.enterprise.solovev.xyz/", name = "createProduct")
    public JAXBElement<CreateProduct> createCreateProduct(CreateProduct value) {
        return new JAXBElement<CreateProduct>(_CreateProduct_QNAME, CreateProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveProductByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.enterprise.solovev.xyz/", name = "removeProductByIdResponse")
    public JAXBElement<RemoveProductByIdResponse> createRemoveProductByIdResponse(RemoveProductByIdResponse value) {
        return new JAXBElement<RemoveProductByIdResponse>(_RemoveProductByIdResponse_QNAME, RemoveProductByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductsByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.enterprise.solovev.xyz/", name = "getProductsByName")
    public JAXBElement<GetProductsByName> createGetProductsByName(GetProductsByName value) {
        return new JAXBElement<GetProductsByName>(_GetProductsByName_QNAME, GetProductsByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveProductById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.enterprise.solovev.xyz/", name = "removeProductById")
    public JAXBElement<RemoveProductById> createRemoveProductById(RemoveProductById value) {
        return new JAXBElement<RemoveProductById>(_RemoveProductById_QNAME, RemoveProductById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.enterprise.solovev.xyz/", name = "getAllProductsResponse")
    public JAXBElement<GetAllProductsResponse> createGetAllProductsResponse(GetAllProductsResponse value) {
        return new JAXBElement<GetAllProductsResponse>(_GetAllProductsResponse_QNAME, GetAllProductsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductsByCategory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.enterprise.solovev.xyz/", name = "getProductsByCategory")
    public JAXBElement<GetProductsByCategory> createGetProductsByCategory(GetProductsByCategory value) {
        return new JAXBElement<GetProductsByCategory>(_GetProductsByCategory_QNAME, GetProductsByCategory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProductsDTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.enterprise.solovev.xyz/", name = "productsDTO")
    public JAXBElement<ProductsDTO> createProductsDTO(ProductsDTO value) {
        return new JAXBElement<ProductsDTO>(_ProductsDTO_QNAME, ProductsDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductsByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.enterprise.solovev.xyz/", name = "getProductsByNameResponse")
    public JAXBElement<GetProductsByNameResponse> createGetProductsByNameResponse(GetProductsByNameResponse value) {
        return new JAXBElement<GetProductsByNameResponse>(_GetProductsByNameResponse_QNAME, GetProductsByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.enterprise.solovev.xyz/", name = "createProductResponse")
    public JAXBElement<CreateProductResponse> createCreateProductResponse(CreateProductResponse value) {
        return new JAXBElement<CreateProductResponse>(_CreateProductResponse_QNAME, CreateProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProducts }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.enterprise.solovev.xyz/", name = "getAllProducts")
    public JAXBElement<GetAllProducts> createGetAllProducts(GetAllProducts value) {
        return new JAXBElement<GetAllProducts>(_GetAllProducts_QNAME, GetAllProducts.class, null, value);
    }

}
