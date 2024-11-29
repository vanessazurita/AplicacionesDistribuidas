using BLL;
using Entities;
using System.Web.Services;

public class ProductService : System.Web.Services.WebService
{
    private ProductLogic _productLogic;

    // Constructor
    public ProductService()
    {
        _productLogic = new ProductLogic();
    }

    [WebMethod]
    public Products CreateProduct(string productName, decimal price, int categoryID, int unitsInStock)
    {
        Products newProduct = new Products
        {
            ProductName = productName,
            Price = price,
            CategoryID = categoryID,
            UnitsInStock = unitsInStock
        };

        return _productLogic.Create(newProduct);
    }

    [WebMethod]
    public Products GetProductById(int id)
    {
        return _productLogic.RetrieveById(id);
    }

    [WebMethod]
    public bool UpdateProduct(int productID, string productName, decimal price, int categoryID, int unitsInStock)
    {
        Products productToUpdate = new Products
        {
            ProductID = productID,
            ProductName = productName,
            Price = price,
            CategoryID = categoryID,
            UnitsInStock = unitsInStock
        };

        return _productLogic.Update(productToUpdate);
    }

    [WebMethod]
    public bool DeleteProduct(int productID)
    {
        return _productLogic.Delete(productID);
    }

    [WebMethod]
    public List<Products> GetProductsByCategory(int categoryID)
    {
        return _productLogic.FilterByCategory(categoryID);
    }
}
