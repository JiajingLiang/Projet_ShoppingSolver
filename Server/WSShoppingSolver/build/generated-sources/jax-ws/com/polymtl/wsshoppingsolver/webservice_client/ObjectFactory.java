
package com.polymtl.wsshoppingsolver.webservice_client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.polymtl.wsshoppingsolver.webservice_client package. 
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

    private final static QName _CreateProduct_QNAME = new QName("http://shopproduct_admin_webservice/", "createProduct");
    private final static QName _CreateProductCategory_QNAME = new QName("http://shopproduct_admin_webservice/", "createProductCategory");
    private final static QName _GetProductPriceInShopResponse_QNAME = new QName("http://shopproduct_admin_webservice/", "getProductPriceInShopResponse");
    private final static QName _FindAllProductResponse_QNAME = new QName("http://shopproduct_admin_webservice/", "findAllProductResponse");
    private final static QName _FindAllShopBranch_QNAME = new QName("http://shopproduct_admin_webservice/", "findAllShopBranch");
    private final static QName _AddProductToShopResponse_QNAME = new QName("http://shopproduct_admin_webservice/", "addProductToShopResponse");
    private final static QName _CreateShopBranchResponse_QNAME = new QName("http://shopproduct_admin_webservice/", "createShopBranchResponse");
    private final static QName _FindAllProduct_QNAME = new QName("http://shopproduct_admin_webservice/", "findAllProduct");
    private final static QName _FindAllShopBrand_QNAME = new QName("http://shopproduct_admin_webservice/", "findAllShopBrand");
    private final static QName _FindAllProductCategory_QNAME = new QName("http://shopproduct_admin_webservice/", "findAllProductCategory");
    private final static QName _GetProductPriceInShop_QNAME = new QName("http://shopproduct_admin_webservice/", "getProductPriceInShop");
    private final static QName _AddProductToShop_QNAME = new QName("http://shopproduct_admin_webservice/", "addProductToShop");
    private final static QName _CreateProductResponse_QNAME = new QName("http://shopproduct_admin_webservice/", "createProductResponse");
    private final static QName _CreateShopBranch_QNAME = new QName("http://shopproduct_admin_webservice/", "createShopBranch");
    private final static QName _GetAllProductsInShop_QNAME = new QName("http://shopproduct_admin_webservice/", "getAllProductsInShop");
    private final static QName _FindAllShopBrandResponse_QNAME = new QName("http://shopproduct_admin_webservice/", "findAllShopBrandResponse");
    private final static QName _GetAllProductsInShopResponse_QNAME = new QName("http://shopproduct_admin_webservice/", "getAllProductsInShopResponse");
    private final static QName _CreateShopBrandResponse_QNAME = new QName("http://shopproduct_admin_webservice/", "createShopBrandResponse");
    private final static QName _FindAllProductCategoryResponse_QNAME = new QName("http://shopproduct_admin_webservice/", "findAllProductCategoryResponse");
    private final static QName _CreateProductCategoryResponse_QNAME = new QName("http://shopproduct_admin_webservice/", "createProductCategoryResponse");
    private final static QName _CreateShopBrand_QNAME = new QName("http://shopproduct_admin_webservice/", "createShopBrand");
    private final static QName _FindAllShopBranchResponse_QNAME = new QName("http://shopproduct_admin_webservice/", "findAllShopBranchResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.polymtl.wsshoppingsolver.webservice_client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetProductPriceInShopResponse }
     * 
     */
    public GetProductPriceInShopResponse createGetProductPriceInShopResponse() {
        return new GetProductPriceInShopResponse();
    }

    /**
     * Create an instance of {@link CreateProductCategory }
     * 
     */
    public CreateProductCategory createCreateProductCategory() {
        return new CreateProductCategory();
    }

    /**
     * Create an instance of {@link CreateProduct }
     * 
     */
    public CreateProduct createCreateProduct() {
        return new CreateProduct();
    }

    /**
     * Create an instance of {@link FindAllShopBranch }
     * 
     */
    public FindAllShopBranch createFindAllShopBranch() {
        return new FindAllShopBranch();
    }

    /**
     * Create an instance of {@link FindAllProductResponse }
     * 
     */
    public FindAllProductResponse createFindAllProductResponse() {
        return new FindAllProductResponse();
    }

    /**
     * Create an instance of {@link AddProductToShop }
     * 
     */
    public AddProductToShop createAddProductToShop() {
        return new AddProductToShop();
    }

    /**
     * Create an instance of {@link CreateProductResponse }
     * 
     */
    public CreateProductResponse createCreateProductResponse() {
        return new CreateProductResponse();
    }

    /**
     * Create an instance of {@link GetProductPriceInShop }
     * 
     */
    public GetProductPriceInShop createGetProductPriceInShop() {
        return new GetProductPriceInShop();
    }

    /**
     * Create an instance of {@link FindAllProductCategory }
     * 
     */
    public FindAllProductCategory createFindAllProductCategory() {
        return new FindAllProductCategory();
    }

    /**
     * Create an instance of {@link AddProductToShopResponse }
     * 
     */
    public AddProductToShopResponse createAddProductToShopResponse() {
        return new AddProductToShopResponse();
    }

    /**
     * Create an instance of {@link CreateShopBranchResponse }
     * 
     */
    public CreateShopBranchResponse createCreateShopBranchResponse() {
        return new CreateShopBranchResponse();
    }

    /**
     * Create an instance of {@link FindAllProduct }
     * 
     */
    public FindAllProduct createFindAllProduct() {
        return new FindAllProduct();
    }

    /**
     * Create an instance of {@link FindAllShopBrand }
     * 
     */
    public FindAllShopBrand createFindAllShopBrand() {
        return new FindAllShopBrand();
    }

    /**
     * Create an instance of {@link CreateProductCategoryResponse }
     * 
     */
    public CreateProductCategoryResponse createCreateProductCategoryResponse() {
        return new CreateProductCategoryResponse();
    }

    /**
     * Create an instance of {@link CreateShopBrand }
     * 
     */
    public CreateShopBrand createCreateShopBrand() {
        return new CreateShopBrand();
    }

    /**
     * Create an instance of {@link FindAllShopBranchResponse }
     * 
     */
    public FindAllShopBranchResponse createFindAllShopBranchResponse() {
        return new FindAllShopBranchResponse();
    }

    /**
     * Create an instance of {@link CreateShopBrandResponse }
     * 
     */
    public CreateShopBrandResponse createCreateShopBrandResponse() {
        return new CreateShopBrandResponse();
    }

    /**
     * Create an instance of {@link FindAllProductCategoryResponse }
     * 
     */
    public FindAllProductCategoryResponse createFindAllProductCategoryResponse() {
        return new FindAllProductCategoryResponse();
    }

    /**
     * Create an instance of {@link FindAllShopBrandResponse }
     * 
     */
    public FindAllShopBrandResponse createFindAllShopBrandResponse() {
        return new FindAllShopBrandResponse();
    }

    /**
     * Create an instance of {@link GetAllProductsInShopResponse }
     * 
     */
    public GetAllProductsInShopResponse createGetAllProductsInShopResponse() {
        return new GetAllProductsInShopResponse();
    }

    /**
     * Create an instance of {@link CreateShopBranch }
     * 
     */
    public CreateShopBranch createCreateShopBranch() {
        return new CreateShopBranch();
    }

    /**
     * Create an instance of {@link GetAllProductsInShop }
     * 
     */
    public GetAllProductsInShop createGetAllProductsInShop() {
        return new GetAllProductsInShop();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "createProduct")
    public JAXBElement<CreateProduct> createCreateProduct(CreateProduct value) {
        return new JAXBElement<CreateProduct>(_CreateProduct_QNAME, CreateProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProductCategory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "createProductCategory")
    public JAXBElement<CreateProductCategory> createCreateProductCategory(CreateProductCategory value) {
        return new JAXBElement<CreateProductCategory>(_CreateProductCategory_QNAME, CreateProductCategory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductPriceInShopResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "getProductPriceInShopResponse")
    public JAXBElement<GetProductPriceInShopResponse> createGetProductPriceInShopResponse(GetProductPriceInShopResponse value) {
        return new JAXBElement<GetProductPriceInShopResponse>(_GetProductPriceInShopResponse_QNAME, GetProductPriceInShopResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "findAllProductResponse")
    public JAXBElement<FindAllProductResponse> createFindAllProductResponse(FindAllProductResponse value) {
        return new JAXBElement<FindAllProductResponse>(_FindAllProductResponse_QNAME, FindAllProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllShopBranch }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "findAllShopBranch")
    public JAXBElement<FindAllShopBranch> createFindAllShopBranch(FindAllShopBranch value) {
        return new JAXBElement<FindAllShopBranch>(_FindAllShopBranch_QNAME, FindAllShopBranch.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddProductToShopResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "addProductToShopResponse")
    public JAXBElement<AddProductToShopResponse> createAddProductToShopResponse(AddProductToShopResponse value) {
        return new JAXBElement<AddProductToShopResponse>(_AddProductToShopResponse_QNAME, AddProductToShopResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateShopBranchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "createShopBranchResponse")
    public JAXBElement<CreateShopBranchResponse> createCreateShopBranchResponse(CreateShopBranchResponse value) {
        return new JAXBElement<CreateShopBranchResponse>(_CreateShopBranchResponse_QNAME, CreateShopBranchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllProduct }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "findAllProduct")
    public JAXBElement<FindAllProduct> createFindAllProduct(FindAllProduct value) {
        return new JAXBElement<FindAllProduct>(_FindAllProduct_QNAME, FindAllProduct.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllShopBrand }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "findAllShopBrand")
    public JAXBElement<FindAllShopBrand> createFindAllShopBrand(FindAllShopBrand value) {
        return new JAXBElement<FindAllShopBrand>(_FindAllShopBrand_QNAME, FindAllShopBrand.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllProductCategory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "findAllProductCategory")
    public JAXBElement<FindAllProductCategory> createFindAllProductCategory(FindAllProductCategory value) {
        return new JAXBElement<FindAllProductCategory>(_FindAllProductCategory_QNAME, FindAllProductCategory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductPriceInShop }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "getProductPriceInShop")
    public JAXBElement<GetProductPriceInShop> createGetProductPriceInShop(GetProductPriceInShop value) {
        return new JAXBElement<GetProductPriceInShop>(_GetProductPriceInShop_QNAME, GetProductPriceInShop.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddProductToShop }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "addProductToShop")
    public JAXBElement<AddProductToShop> createAddProductToShop(AddProductToShop value) {
        return new JAXBElement<AddProductToShop>(_AddProductToShop_QNAME, AddProductToShop.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProductResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "createProductResponse")
    public JAXBElement<CreateProductResponse> createCreateProductResponse(CreateProductResponse value) {
        return new JAXBElement<CreateProductResponse>(_CreateProductResponse_QNAME, CreateProductResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateShopBranch }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "createShopBranch")
    public JAXBElement<CreateShopBranch> createCreateShopBranch(CreateShopBranch value) {
        return new JAXBElement<CreateShopBranch>(_CreateShopBranch_QNAME, CreateShopBranch.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsInShop }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "getAllProductsInShop")
    public JAXBElement<GetAllProductsInShop> createGetAllProductsInShop(GetAllProductsInShop value) {
        return new JAXBElement<GetAllProductsInShop>(_GetAllProductsInShop_QNAME, GetAllProductsInShop.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllShopBrandResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "findAllShopBrandResponse")
    public JAXBElement<FindAllShopBrandResponse> createFindAllShopBrandResponse(FindAllShopBrandResponse value) {
        return new JAXBElement<FindAllShopBrandResponse>(_FindAllShopBrandResponse_QNAME, FindAllShopBrandResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllProductsInShopResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "getAllProductsInShopResponse")
    public JAXBElement<GetAllProductsInShopResponse> createGetAllProductsInShopResponse(GetAllProductsInShopResponse value) {
        return new JAXBElement<GetAllProductsInShopResponse>(_GetAllProductsInShopResponse_QNAME, GetAllProductsInShopResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateShopBrandResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "createShopBrandResponse")
    public JAXBElement<CreateShopBrandResponse> createCreateShopBrandResponse(CreateShopBrandResponse value) {
        return new JAXBElement<CreateShopBrandResponse>(_CreateShopBrandResponse_QNAME, CreateShopBrandResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllProductCategoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "findAllProductCategoryResponse")
    public JAXBElement<FindAllProductCategoryResponse> createFindAllProductCategoryResponse(FindAllProductCategoryResponse value) {
        return new JAXBElement<FindAllProductCategoryResponse>(_FindAllProductCategoryResponse_QNAME, FindAllProductCategoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateProductCategoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "createProductCategoryResponse")
    public JAXBElement<CreateProductCategoryResponse> createCreateProductCategoryResponse(CreateProductCategoryResponse value) {
        return new JAXBElement<CreateProductCategoryResponse>(_CreateProductCategoryResponse_QNAME, CreateProductCategoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateShopBrand }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "createShopBrand")
    public JAXBElement<CreateShopBrand> createCreateShopBrand(CreateShopBrand value) {
        return new JAXBElement<CreateShopBrand>(_CreateShopBrand_QNAME, CreateShopBrand.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllShopBranchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://shopproduct_admin_webservice/", name = "findAllShopBranchResponse")
    public JAXBElement<FindAllShopBranchResponse> createFindAllShopBranchResponse(FindAllShopBranchResponse value) {
        return new JAXBElement<FindAllShopBranchResponse>(_FindAllShopBranchResponse_QNAME, FindAllShopBranchResponse.class, null, value);
    }

}
