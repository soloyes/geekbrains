import wsdl.ProductsDTO;
import wsdl.ProductsService;
import wsdl.ProductsServiceService;


public class Main {
    public static void main(String[] args) {
        ProductsService service = new ProductsServiceService().getPort(ProductsService.class);

        for (ProductsDTO p: service.getAllProducts()) {
            System.out.println(p.getId());
        }
    }
}
