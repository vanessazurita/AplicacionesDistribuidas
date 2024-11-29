using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ClientTest.ServiceCategory;

namespace ClientTest
{
    public class FuncionCategory
    {
        public void AgregarCategoria()
        {
            Category result = null;
            try
            {
                using (var r = new ServiceCategory.Service1Client())
                {
                    Category category = new Category();
                    category.CategoryName = "Comida";
                    category.Description = "Todo lo que es comida";
                    result = r.GetDataCategory(category);
                }
                Console.WriteLine("Categoria Ingresado con Exito");
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error al ingresar un categoria" + ex);
            }
        }

        public void EditarCategoria()
        {
            bool result = false;
            try
            {
                using (var r = new ServiceCategory.Service1Client())
                {
                    Category category = new Category();
                    category.CategoryID = 7;
                    category.CategoryName = "Bebida";
                    result = r.UpdateDataCategory(category);
                }
                Console.WriteLine("Categoria Editado con Exito");
            }
            catch (Exception)
            {
            }
        }

        public void EliminarCategoria()
        {
            bool result = false;
            try
            {
                using (var r = new ServiceCategory.Service1Client())
                {
                    Category category = new Category();
                    category.CategoryID = 4;
                    category.CategoryName = "Comida";
                    category.Description = "Todo lo que es comida";
                    result = r.DeleteDataCategory(category);
                }
                Console.WriteLine("Categoria eliminado con Exito");
            }
            catch (Exception)
            {
                Console.WriteLine("Error al eliminar el categoria");
            }
        }

        public static void FiltrarCategoryo()
        {
            Category[] result = null;
            try
            {
                using (var r = new ServiceCategory.Service1Client())
                {
                    Category category = new Category();
                    category.CategoryID = 1;
                    category.CategoryName = "CategoriaActualizado";
                    result = r.FilterCategory(category);
                }
                Console.WriteLine(result[0]);
            }
            catch (Exception ex)
            {
                Console.WriteLine("No se puede buscar la categoria" + ex);
            }
        }
    }
}
